package jmu.zsw.spider;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import jmu.zsw.bean.CovidBean;
import jmu.zsw.template.HadoopTemplate;
import jmu.zsw.utils.HttpUtil;
import jmu.zsw.utils.TimeUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class CovidDataSpider {

    @Autowired
    private HadoopTemplate hadoopTemplate;

    private static String pFile = "province.data";
    private static String cFile = "cities.data";
    private static String countryFile = "country.data";


    @Scheduled(cron = "0 40 8 * * ?")    //每天8点定时执行
//    @Scheduled(cron = "0 5 11 * * ?")
    public void spider(){
        String datetime = TimeUtil.format(System.currentTimeMillis(), "yyyy-MM-dd");

        System.out.println("爬取数据中......");
        //1. 爬取指定页面
        String html = HttpUtil.getHtml("https://ncov.dxy.cn/ncovh5/view/pneumonia");

        //2. 解析页面数据-即id(getAreaStat)中的数据
        Document doc = Jsoup.parse(html);
        String text = doc.select("script[id=getAreaStat]").toString();
        String countryText = doc.select("script[id=getListByCountryTypeService2true]").toString();

        //3. 使用正则表达式获取json格式数据
        //3.1 定义正则规则
        String pattern = "\\[(.*)\\]";
        //3.2 编译正则对象
        Pattern compile = Pattern.compile(pattern);
        //3.3 匹配text
        Matcher matcher = compile.matcher(text);
        Matcher matcher1 = compile.matcher(countryText);
        //3.4 判断是否匹配成功
        String jsonStr = "";
        String countryStr = "";
        if(matcher.find()){
            jsonStr = matcher.group(0);
        }else {
            System.out.println("no match");
        }
        if(matcher1.find()){
            countryStr = matcher1.group(0);
        }else {
            System.out.println("no match");
        }
        System.out.println("爬取完成......!");
        System.out.println("开始上传数据......");

        hadoopTemplate.delFile(pFile);
        hadoopTemplate.delFile(cFile);
        hadoopTemplate.delFile(countryFile);

        //解析全国疫情数据
        List<CovidBean> CovidBeans = JSON.parseArray(countryStr, CovidBean.class);
        for(CovidBean c : CovidBeans) {
            if (c.getProvinceName().equals("中国")) {
                c.setStatisticsData("");
                c.setDatetime(datetime);
            } else {
                continue;
            }
            String counStr = JSON.toJSONString(c);
            hadoopTemplate.append(countryFile,counStr);
        }
        //4. 对json进行进一步解析
        //4.1 将第一层(省份数据)解析为Javabean
        List<CovidBean> pCovidBeans = JSON.parseArray(jsonStr, CovidBean.class);
        for(CovidBean pBean:pCovidBeans) {
            //设置时间字段
            pBean.setDatetime(datetime);
            //获取cities
            String cityStr = pBean.getCities();
            //4.2 将第二层(城市数据)解析为JavaBean
            List<CovidBean> cCovidBeans = JSON.parseArray(cityStr, CovidBean.class);
            for(CovidBean cBean: cCovidBeans){
                cBean.setDatetime(datetime);
                cBean.setPid(pBean.getLocationId());    //省份id作为城市pid
                cBean.setProvinceShortName(pBean.getProvinceShortName());
                //后续需要将数据发送给HDFS
                String jsonString = JSON.toJSONString(cBean);
                hadoopTemplate.append(cFile,jsonString+"\n");
            }
            //4.2 获取第一层(省份数据)json中的每一天的统计数据
            String statisticsDataUrl = pBean.getStatisticsData();
            String statisticsDataStr = HttpUtil.getHtml(statisticsDataUrl);
            //4.2.1 获取statisticsDataStr中的data字段
            JSONObject jsonObject = JSON.parseObject(statisticsDataStr);
            String dataStr = jsonObject.getString("data");
            //4.2.2 将解析出来的每一天的所有数据设置回statisticsData字段中
            pBean.setStatisticsData(dataStr);
            pBean.setCities(null);
            //后续需要将数据发送给HDFS
            String pString = JSON.toJSONString(pBean);
            hadoopTemplate.append(pFile,pString+"\n");
        }
        System.out.println("上传完成......!");
    }

}
