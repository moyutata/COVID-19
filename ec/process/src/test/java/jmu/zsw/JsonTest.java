package jmu.zsw;

import com.alibaba.fastjson.JSON;
import jmu.zsw.bean.CountryBean;
import jmu.zsw.bean.StatisticsDataBean;
import jmu.zsw.utils.TimeUtil;
import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonTest {
    @Test
    public void test(){
        String str = "{\"cityName\":\"\",\"confirmedCount\":4839118,\"confirmedIncr\":25120,\"curedCount\":300729,\"currentConfirmedCount\":4515545,\"currentConfirmedIncr\":234,\"datetime\":\"2022-07-18\",\"deadCount\":22844,\"deadIncr\":77,\"incrVo\":\"\",\"locationId\":951001,\"provinceId\":999,\"provinceName\":\"中国\",\"provinceShortName\":\"\",\"statisticsData\":\"\",\"suspectedCount\":0}";

        CountryBean countryBean = JSON.parseObject(str, CountryBean.class);
        System.out.println(countryBean);
    }

    @Test
    public void patternTest(){
        String jsonStr = "{\"cityName\":\"\",\"confirmedCount\":4839118,\"confirmedIncr\":25120,\"curedCount\":300729,\"currentConfirmedCount\":4515545,\"currentConfirmedIncr\":234,\"datetime\":\"2022-07-18\",\"deadCount\":22844,\"deadIncr\":77,\"incrVo\":\"\",\"locationId\":951001,\"provinceId\":999,\"provinceName\":\"中国\",\"provinceShortName\":\"\",\"statisticsData\":\"\",\"suspectedCount\":0}";

        String str = "";
        String pattern = "\\{(.*)\\}";

        Pattern compile = Pattern.compile(pattern);
        Matcher matcher = compile.matcher(jsonStr);

        if(matcher.find()){
            str = matcher.group(0);
        } else {
            System.out.println("no match");
        }
        System.out.println(str);
    }

    @Test
    public void parseTest(){
        String string = "{\"curedCount\":0,\"currentConfirmedIncr\":1,\"midDangerCount\":0,\"confirmedIncr\":1,\"suspectedCountIncr\":0,\"deadIncr\":0,\"suspectedCount\":0,\"confirmedCount\":1,\"currentConfirmedCount\":1,\"curedIncr\":0,\"dateId\":20200129,\"highDangerCount\":0,\"deadCount\":0}";

        StatisticsDataBean bean = JSON.parseObject(string,StatisticsDataBean.class);
        String[] split = bean.toString().split("\t");
        System.out.println(split[0]);
    }


    @Test
    public void dateTest(){
        String datetime = TimeUtil.format(System.currentTimeMillis(), "yyyyMMdd");
        System.out.println(datetime);
    }
}
