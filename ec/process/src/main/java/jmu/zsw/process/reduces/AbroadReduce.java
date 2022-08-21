package jmu.zsw.process.reduces;

import jmu.zsw.bean.AbroadBean;
import jmu.zsw.bean.CityBean;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class AbroadReduce extends Reducer<Text, CityBean, AbroadBean, NullWritable> {

    AbroadBean outValue = null;

    @Override
    protected void reduce(Text key, Iterable<CityBean> values, Context context) throws IOException, InterruptedException {
        String[] split = key.toString().split("\t");
        Integer confirmedCount = 0;
        for(CityBean bean:values){
            confirmedCount+=bean.getConfirmedCount();
        }
        outValue = new AbroadBean(
                split[0],split[1],Integer.parseInt(split[2]),confirmedCount
        );
        context.write(outValue, NullWritable.get());
    }
}
