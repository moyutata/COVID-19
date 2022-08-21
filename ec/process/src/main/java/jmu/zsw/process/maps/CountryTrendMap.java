package jmu.zsw.process.maps;

import com.alibaba.fastjson.JSON;
import jmu.zsw.bean.ProvinceBean;
import jmu.zsw.bean.StatisticsDataBean;
import jmu.zsw.utils.TimeUtil;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.sql.Time;
import java.util.List;

public class CountryTrendMap extends Mapper<LongWritable, Text, Text, StatisticsDataBean> {

    Text outKey = null;
    StatisticsDataBean outValue = null;

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String jsonStr = value.toString();
        ProvinceBean provinceBean = JSON.parseObject(jsonStr, ProvinceBean.class);
        String statisticsStr = provinceBean.getStatisticsData();
        List<StatisticsDataBean> statisticsDataBeans = JSON.parseArray(statisticsStr, StatisticsDataBean.class);
        for(StatisticsDataBean statisticsDataBean: statisticsDataBeans){
            String[] split = statisticsDataBean.toString().split("\t");
            outValue = new StatisticsDataBean(
                    Integer.parseInt(split[1]),Integer.parseInt(split[2]),
                    Integer.parseInt(split[3]),Integer.parseInt(split[4]),
                    Integer.parseInt(split[5])
            );
            outKey = new Text(split[0]);
            context.write(outKey,outValue);
        }
    }
}
