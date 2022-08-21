package jmu.zsw.process.reduces;

import jmu.zsw.bean.CityBean;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class CityReduce extends Reducer<NullWritable, CityBean, CityBean, NullWritable> {

    CityBean outValue = null;

    @Override
    protected void reduce(NullWritable key, Iterable<CityBean> values, Context context) throws IOException, InterruptedException {
        for(CityBean bean:values){
            if(bean.getLocationId()==-1){
                bean.setLocationId(0);
            }
            outValue = new CityBean(
                    bean.getDatetime(),bean.getProvinceShortName(),bean.getPid(),
                    bean.getCityName(), bean.getLocationId(),bean.getConfirmedCount(),bean.getCuredCount(),
                    bean.getCurrentConfirmedCount(),bean.getDeadCount(),bean.getSuspectedCount()
            );
            context.write(outValue,NullWritable.get());
        }
    }
}
