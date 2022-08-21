package jmu.zsw.process.maps;

import com.alibaba.fastjson.JSON;
import jmu.zsw.bean.CityBean;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class AbroadMap extends Mapper<LongWritable, Text, Text, CityBean> {

    Text outKey = null;
    CityBean outValue = null;

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String jsonStr = value.toString();
        outValue = JSON.parseObject(jsonStr, CityBean.class);
        if(outValue.getCityName().contains("境外输入")){
            String keys = outValue.getDatetime()+"\t"+outValue.getProvinceShortName()+"\t"+outValue.getPid();
            outKey = new Text(keys);
            context.write(outKey, outValue);
        }
    }
}
