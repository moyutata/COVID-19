package jmu.zsw;

import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.fs.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
@Slf4j
public class HadoopTest {

    @Autowired
    private FileSystem fileSystem;

    private static String nameSpace = "/user/hadoop/input";

    @Test
    public void filesInfo() throws IOException {
        RemoteIterator<LocatedFileStatus> files = fileSystem.listFiles(new Path(nameSpace), true);
        while (files.hasNext()){
            LocatedFileStatus fileStatus = files.next();
            log.info("名字: "+fileStatus.getPath().getName());
            log.info("文件分组: "+fileStatus.getGroup());
            log.info("文件长度: "+String.valueOf(fileStatus.getLen()));
            log.info("文件权限: "+String.valueOf(fileStatus.getPermission()));
            BlockLocation[] blockLocations = fileStatus.getBlockLocations();
            log.info("block 数："+blockLocations.length);
            for (BlockLocation blockLocation : blockLocations) {
                String[] hosts = blockLocation.getHosts();
                for (String host : hosts) {
                    System.out.println(fileStatus.getPath().getName()+"block主机节点："+host);
                }
            }
        }
    }
}
