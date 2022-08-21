package jmu.zsw.process.maps;

import com.alibaba.fastjson.JSON;
import jmu.zsw.bean.ProvinceBean;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class ProvinceMap extends Mapper<LongWritable, Text, NullWritable, ProvinceBean> {

    NullWritable outKey = NullWritable.get();
    ProvinceBean outValue = null;

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String jsonStr = value.toString();
        ProvinceBean provinceBean = JSON.parseObject(jsonStr, ProvinceBean.class);
        String[] split = provinceBean.toString().split("\t");
        outValue = new ProvinceBean(
                split[0],Integer.parseInt(split[1]),split[2],
                Integer.parseInt(split[3]),Integer.parseInt(split[4]),
                Integer.parseInt(split[5]),Integer.parseInt(split[6]),
                Integer.parseInt(split[7])
        );
        context.write(outKey,outValue);
    }
}
