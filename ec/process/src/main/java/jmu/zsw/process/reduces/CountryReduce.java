package jmu.zsw.process.reduces;

import jmu.zsw.bean.CountryBean;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class CountryReduce extends Reducer<NullWritable, CountryBean, CountryBean, NullWritable> {

    @Override
    protected void reduce(NullWritable key, Iterable<CountryBean> values, Context context) throws IOException, InterruptedException {
        for(CountryBean bean:values){
            context.write(bean,NullWritable.get());
        }
    }
}
