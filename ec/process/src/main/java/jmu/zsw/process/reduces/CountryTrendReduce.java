package jmu.zsw.process.reduces;

import jmu.zsw.bean.CountryTrendBean;
import jmu.zsw.bean.StatisticsDataBean;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class CountryTrendReduce extends Reducer<Text, StatisticsDataBean, CountryTrendBean, NullWritable> {

    CountryTrendBean outValue = null;
    @Override
    protected void reduce(Text key, Iterable<StatisticsDataBean> values, Context context) throws IOException, InterruptedException {
        String dateId = "";
        Integer confirmedIncrCount = 0;
        Integer confirmedCountCount = 0;
        Integer suspectedCountCount = 0;
        Integer curedCountCount = 0;
        Integer deadCountCount = 0;

        dateId = key.toString();
        for(StatisticsDataBean bean: values){
            confirmedIncrCount += bean.getConfirmedIncr();
            confirmedCountCount += bean.getConfirmedCount();
            suspectedCountCount += bean.getSuspectedCount();
            curedCountCount += bean.getCuredCount();
            deadCountCount += bean.getDeadCount();
        }
        outValue = new CountryTrendBean(
                dateId,confirmedIncrCount,confirmedCountCount,
                suspectedCountCount,curedCountCount,deadCountCount
        );
        context.write(outValue,NullWritable.get());
    }
}
