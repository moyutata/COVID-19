package jmu.zsw.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.fs.FileSystem;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URI;


@Configuration
@Slf4j
public class HadoopConfig {

    public static org.apache.hadoop.conf.Configuration configuration;
    public static String nameNode = "hdfs://192.168.69.130:9000";

    @Bean("fileSystem")
    public FileSystem createFs() throws Exception {
        configuration = new org.apache.hadoop.conf.Configuration();
        configuration.set("fs.default.name",nameNode);
        configuration.set("dfs.replication","2");
        URI uri = new URI(nameNode.trim());
        FileSystem fs = FileSystem.get(uri,configuration,"hadoop");
        log.info("file system loaded...");
        System.out.println("fs.default.name:"+configuration.get("fs.default.name"));
        return fs;
    }

}
