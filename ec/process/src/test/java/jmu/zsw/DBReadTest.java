package jmu.zsw;

import jmu.zsw.bean.CountryTrendBean;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.TaskCounter;
import org.apache.hadoop.mapreduce.lib.db.DBConfiguration;
import org.apache.hadoop.mapreduce.lib.db.DBInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
public class DBReadTest {

    @Test
    public void read() throws IOException, ClassNotFoundException, InterruptedException {
        Configuration configuration = new Configuration();
        configuration.set("fs.default.name","hdfs://192.168.69.130:9000");
        DBConfiguration.configureDB(configuration,"com.mysql.cj.jdbc.Driver","jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf-8&useSSL=true&serverTimezone=Asia/Shanghai","root","zhangsiwen");

        Job job = Job.getInstance(configuration,"获取数据");
        job.setJarByClass(DBReadTest.class);
        job.setMapperClass(DBMapper.class);
        job.setOutputKeyClass(LongWritable.class);
        job.setOutputValueClass(Text.class);

        job.setNumReduceTasks(0);
        FileOutputFormat.setOutputPath(job,new Path("dataprocess"));
        job.setInputFormatClass(DBInputFormat.class);
        DBInputFormat.setInput(job, CountryTrendBean.class,"select * from covid19_3","select count(*) from covid19_3");
        job.waitForCompletion(true);
    }


    private static class DBMapper extends Mapper<LongWritable, CountryTrendBean, LongWritable, Text> {

        Text outValue = new Text();

        @Override
        protected void map(LongWritable key, CountryTrendBean value, Context context) throws IOException, InterruptedException {
            outValue.set(value.toString());
            context.write(key,outValue);
        }
    }
}
