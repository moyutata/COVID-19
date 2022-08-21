package jmu.zsw.process.reduces;

import jmu.zsw.bean.ProvinceBean;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class ProvinceReduce extends Reducer<NullWritable, ProvinceBean, ProvinceBean, NullWritable> {
    @Override
    protected void reduce(NullWritable key, Iterable<ProvinceBean> values, Context context) throws IOException, InterruptedException {
        for(ProvinceBean bean:values){
            context.write(bean,NullWritable.get());
        }
    }
}
