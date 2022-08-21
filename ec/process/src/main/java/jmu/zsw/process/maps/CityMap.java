package jmu.zsw.process.maps;

import com.alibaba.fastjson.JSON;
import jmu.zsw.bean.CityBean;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class CityMap extends Mapper<LongWritable, Text, NullWritable, CityBean> {

    NullWritable outKey = NullWritable.get();
    CityBean outValue = null;

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String jsonStr = value.toString();
        outValue = JSON.parseObject(jsonStr,CityBean.class);
        context.write(outKey,outValue);
    }
}
