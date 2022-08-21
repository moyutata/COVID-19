package jmu.zsw;

import jmu.zsw.bean.*;
import jmu.zsw.process.MapReduceEntrance;
import jmu.zsw.process.maps.*;
import jmu.zsw.process.reduces.*;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.db.DBConfiguration;
import org.apache.hadoop.mapreduce.lib.db.DBOutputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
public class DBTest {

    @Test
    public void CountryMapReduce() throws IOException, ClassNotFoundException, InterruptedException {
        Configuration configuration = new Configuration();
        configuration.set("fs.default.name","hdfs://192.168.48.134:9000");
        DBConfiguration.configureDB(configuration,"com.mysql.cj.jdbc.Driver","jdbc:mysql://localhost:3306/covid19?useUnicode=true&characterEncoding=utf-8&useSSL=true&serverTimezone=Asia/Shanghai","root","123456");

        String filePath = "datatest1/country.data";
        Job job = Job.getInstance(configuration,"全国疫情数据");
        job.setJarByClass(MapReduceEntrance.class);
        job.setMapperClass(CountryMap.class);
        job.setReducerClass(CountryReduce.class);
        job.setMapOutputKeyClass(NullWritable.class);
        job.setMapOutputValueClass(CountryBean.class);
        job.setOutputKeyClass(CountryBean.class);
        job.setOutputValueClass(NullWritable.class);

        //设置输入文件路径
        FileInputFormat.setInputPaths(job,new Path(filePath));
        job.setOutputFormatClass(DBOutputFormat.class);
        DBOutputFormat.setOutput(job,"country","datetime","currentConfirmedCount",
                "confirmedCount","curedCount","deadCount");

        System.exit(job.waitForCompletion(true)? 0 : 1);
    }

    @Test
    public void ProvinceMapReduce() throws IOException, ClassNotFoundException, InterruptedException {
        Configuration configuration = new Configuration();
        configuration.set("fs.default.name","hdfs://192.168.48.134:9000");
        DBConfiguration.configureDB(configuration,"com.mysql.cj.jdbc.Driver","jdbc:mysql://localhost:3306/covid19?useUnicode=true&characterEncoding=utf-8&useSSL=true&serverTimezone=Asia/Shanghai","root","123456");

        String filePath = "datatest1/province.data";
        Job job = Job.getInstance(configuration,"全国各省份累计确诊数据");
        job.setJarByClass(MapReduceEntrance.class);
        job.setMapperClass(ProvinceMap.class);
        job.setReducerClass(ProvinceReduce.class);
        job.setMapOutputKeyClass(NullWritable.class);
        job.setMapOutputValueClass(ProvinceBean.class);
        job.setOutputKeyClass(ProvinceBean.class);
        job.setOutputValueClass(NullWritable.class);

        //设置输入文件路径
        FileInputFormat.setInputPaths(job,new Path(filePath));
        job.setOutputFormatClass(DBOutputFormat.class);
        DBOutputFormat.setOutput(job,"province","datetime","locationId","provinceShortName","currentConfirmedCount",
                "confirmedCount","suspectedCount","curedCount","deadCount");

        System.exit(job.waitForCompletion(true)? 0 : 1);
    }

    @Test
    public void CountryTrendMapReduce() throws IOException, ClassNotFoundException, InterruptedException {
        Configuration configuration = new Configuration();
        configuration.set("fs.default.name","hdfs://192.168.48.134:9000");
        DBConfiguration.configureDB(configuration,"com.mysql.cj.jdbc.Driver","jdbc:mysql://localhost:3306/covid19?useUnicode=true&characterEncoding=utf-8&useSSL=true&serverTimezone=Asia/Shanghai","root","123456");

        String filePath = "datatest1/province.data";
        Job job = Job.getInstance(configuration,"全国疫情趋势");
        job.setJarByClass(MapReduceEntrance.class);
        job.setMapperClass(CountryTrendMap.class);
        job.setReducerClass(CountryTrendReduce.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(StatisticsDataBean.class);
        job.setOutputKeyClass(CountryTrendBean.class);
        job.setOutputValueClass(NullWritable.class);

        //设置输入文件路径
        FileInputFormat.setInputPaths(job,new Path(filePath));
        job.setOutputFormatClass(DBOutputFormat.class);
        DBOutputFormat.setOutput(job,"countrytrend","dateId","confirmedIncr",
                "confirmedCount","suspectedCount","curedCount","deadCount");

        System.exit(job.waitForCompletion(true)? 0 : 1);
    }

    @Test
    public void AbroadMapReduce() throws IOException, ClassNotFoundException, InterruptedException {
        Configuration configuration = new Configuration();
        configuration.set("fs.default.name","hdfs://192.168.48.134:9000");
        DBConfiguration.configureDB(configuration,"com.mysql.cj.jdbc.Driver","jdbc:mysql://localhost:3306/covid19?useUnicode=true&characterEncoding=utf-8&useSSL=true&serverTimezone=Asia/Shanghai","root","123456");

        String filePath = "datatest1/cities.data";
        Job job = Job.getInstance(configuration,"境外输入排行");
        job.setJarByClass(MapReduceEntrance.class);
        job.setMapperClass(AbroadMap.class);
        job.setReducerClass(AbroadReduce.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(CityBean.class);
        job.setOutputKeyClass(AbroadBean.class);
        job.setOutputValueClass(NullWritable.class);

        //设置输入文件路径
        FileInputFormat.setInputPaths(job,new Path(filePath));
        job.setOutputFormatClass(DBOutputFormat.class);
        DBOutputFormat.setOutput(job,"abroad","datetime","provinceShortName",
                "pid","confirmedCount");

        System.exit(job.waitForCompletion(true)? 0 : 1);
    }

    @Test
    public void CityMapReduce() throws IOException, ClassNotFoundException, InterruptedException {
        Configuration configuration = new Configuration();
        configuration.set("fs.default.name","hdfs://192.168.48.134:9000");
        DBConfiguration.configureDB(configuration,"com.mysql.cj.jdbc.Driver","jdbc:mysql://localhost:3306/covid19?useUnicode=true&characterEncoding=utf-8&useSSL=true&serverTimezone=Asia/Shanghai","root","123456");

        String filePath = "datatest1/cities.data";
        Job job = Job.getInstance(configuration,"城市疫情数据");
        job.setJarByClass(MapReduceEntrance.class);
        job.setMapperClass(CityMap.class);
        job.setReducerClass(CityReduce.class);
        job.setMapOutputKeyClass(NullWritable.class);
        job.setMapOutputValueClass(CityBean.class);
        job.setOutputKeyClass(CityBean.class);
        job.setOutputValueClass(NullWritable.class);

        //设置输入文件路径
        FileInputFormat.setInputPaths(job,new Path(filePath));
        job.setOutputFormatClass(DBOutputFormat.class);
        DBOutputFormat.setOutput(job,"city","datetime","pId","provinceShortName",
                "locationId","cityName","currentConfirmedCount","confirmedCount",
                "suspectedCount","curedCount","deadCount");

        System.exit(job.waitForCompletion(true)? 0 : 1);
    }
}
