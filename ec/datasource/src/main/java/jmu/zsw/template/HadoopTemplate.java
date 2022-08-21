package jmu.zsw.template;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Component
@ConditionalOnClass(FileSystem.class)
@Slf4j
public class HadoopTemplate {

    @Autowired
    private FileSystem fileSystem;

    private static String nameNode = "hdfs://192.168.48.134:9000";
    private static String nameSpace = "/user/hadoop/datatest1";

    @PostConstruct
    public void init(){
        existDir(nameSpace,true);
    }

    public void uploadFile(String srcFile){
        copyFileToHDFS(false,true,srcFile,nameSpace);
    }

    public void delFile(String fileName){
        rmdir(nameSpace,fileName) ;
    }


    public void append(String fileName, String content) {
        appendContent(nameSpace, fileName, content);
    }

    public boolean existDir(String filePath, boolean create){
        boolean flag = false;
        if(StringUtils.isEmpty(filePath)){
            throw new IllegalArgumentException("filePath不能为空");
        }
        try{
            Path path = new Path(filePath);
            if (create){
                if (!fileSystem.exists(path)){
                    fileSystem.mkdirs(path);
                }
            }
            if (fileSystem.isDirectory(path)){
                flag = true;
            }
        }catch (Exception e){
            log.error("", e);
        }
        return flag;
    }

    /**
     * 文件上传至 HDFS
     * @param delSrc       指是否删除源文件，true为删除，默认为false
     * @param overwrite    是否重写
     * @param srcFile      源文件，上传文件路径
     * @param destPath     hdfs的目的路径
     */
    public void copyFileToHDFS(boolean delSrc, boolean overwrite,String srcFile,String destPath) {
        // 源文件路径是Linux下的路径，如果在 windows 下测试，需要改写为Windows下的路径，比如D://hadoop/weibo.txt
        Path srcPath = new Path(srcFile);
        log.info("@@@@@ 上传文件路径："+srcFile);
        // 目的路径
        if(StringUtils.isNotBlank(nameNode)){
            destPath = nameNode + destPath;
        }
        Path dstPath = new Path(destPath);
        // 实现文件上传
        try {
            // 获取FileSystem对象
            //fileSystem.copyFromLocalFile(srcPath, dstPath);
            log.info("@@@@@ 开始上传");
            fileSystem.copyFromLocalFile(delSrc,overwrite,srcPath, dstPath);
            log.info("@@@@@ 上传完成");
            //释放资源
            //    fileSystem.close();
        } catch (IOException e) {
            log.error("", e);
        }
    }

    /**
     * 删除文件或者文件目录
     *
     * @param filePath          文件路径
     */
    public void rmdir(String filePath,String fileName) {
        try {
            // 返回FileSystem对象
            if(StringUtils.isNotBlank(nameNode)){
                filePath = nameNode + filePath;
            }
            if(StringUtils.isNotBlank(fileName)){
                filePath =  filePath + "/" +fileName;
            }
            Path path = new Path(filePath);
            // 删除文件或者文件目录  delete(Path f) 此方法已经弃用
            if (fileSystem.exists(path)){
                fileSystem.delete(path,false);
            }
        } catch (IllegalArgumentException | IOException e) {
            log.error("", e);
        }
    }

    /**
     * 向文件中追加内容
     *
     * @param filePath      文件路径
     * @param fileName      文件名
     * @param content       写入内容
     */
    public void appendContent(String filePath, String fileName, String content) {
        FSDataOutputStream outputStream = null;
        try {
            // 返回FileSystem对象
            if(StringUtils.isNotBlank(nameNode)){
                filePath = nameNode + filePath;
            }
            if(StringUtils.isNotBlank(fileName)){
                filePath =  filePath + "/" +fileName;
            }
            if(fileSystem.exists(new Path(filePath))){
                outputStream = fileSystem.append(new Path(filePath));
            } else {
                outputStream = fileSystem.create(new Path(filePath));
            }
            outputStream.write(content.getBytes());
        } catch (IllegalArgumentException | IOException e) {
            log.error("", e);
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public String getNameSpace(){
        return nameSpace;
    }
}
