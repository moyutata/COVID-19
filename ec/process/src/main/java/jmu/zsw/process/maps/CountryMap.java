package jmu.zsw.process.maps;

import com.alibaba.fastjson.JSON;
import jmu.zsw.bean.CountryBean;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class CountryMap extends Mapper<LongWritable, Text, NullWritable, CountryBean> {

    NullWritable outKey = NullWritable.get();
    CountryBean outValue = null;

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String jsonStr = value.toString();
        CountryBean bean = JSON.parseObject(jsonStr, CountryBean.class);
        String[] split = bean.toString().split("\t");
        outValue = new CountryBean(
                split[0],Integer.parseInt(split[1]),Integer.parseInt(split[2]),
                Integer.parseInt(split[3]),Integer.parseInt(split[4])
        );
        context.write(outKey,outValue);
    }
}
