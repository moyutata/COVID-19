package jmu.zsw.process;

import jmu.zsw.bean.*;
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
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MapReduceEntrance {


    @Scheduled(cron = "0 45 8 * * ?")
//    @Scheduled(cron = "0 5 16 * * ?")    //测试用
    public void mapReduce() throws IOException, InterruptedException, ClassNotFoundException {
        System.out.println("开始更新数据库(0%)......");
        CountryMapReduce();
        System.out.println("开始更新数据库(20%)......");
        ProvinceMapReduce();
        System.out.println("开始更新数据库(40%)......");
        CountryTrendMapReduce();
        System.out.println("开始更新数据库(60%)......");
        AbroadMapReduce();
        System.out.println("开始更新数据库(80%)......");
        CityMapReduce();
        System.out.println("开始更新数据库(100%)......");
        System.out.println("更新数据库完成......!");
    }

    //全国疫情数据
    public int CountryMapReduce() throws IOException, ClassNotFoundException, InterruptedException {
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

        return (job.waitForCompletion(true)? 0 : 1);
    }

    //全国各省份累计确诊数据
    public int ProvinceMapReduce() throws IOException, ClassNotFoundException, InterruptedException {
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

        return (job.waitForCompletion(true)? 0 : 1);
    }

    //全国疫情趋势
    public int CountryTrendMapReduce() throws IOException, ClassNotFoundException, InterruptedException {
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
        DBOutputFormat.setOutput(job,"countryTrend","dateId","confirmedIncr",
                "confirmedCount","suspectedCount","curedCount","deadCount");

        return (job.waitForCompletion(true)? 0 : 1);
    }

    //境外输入排行
    public int AbroadMapReduce() throws IOException, ClassNotFoundException, InterruptedException {
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

        return (job.waitForCompletion(true)? 0 : 1);
    }

    //城市疫情数据
    public int CityMapReduce() throws IOException, ClassNotFoundException, InterruptedException {
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

        return (job.waitForCompletion(true)? 0 : 1);
    }
}
