package jmu.zsw.utils;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class HttpUtil {

    //声明HTTPClient连接池
    private static PoolingHttpClientConnectionManager cm;
    private static List<String> userAgentList = null;
    private static RequestConfig requestConfig = null;

    //在类被加载时执行
    static{
        //连接池设置
        cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(200);
        cm.setDefaultMaxPerRoute(20);

        //requestConfig设置
        requestConfig = RequestConfig.custom()
                .setSocketTimeout(10000)    //设置连接超时时间
                .setConnectTimeout(10000)   //设置创建连接超时时间
                .setConnectionRequestTimeout(10000) //设置请求超时时间
                .build();

        //userAgentList
        userAgentList = new ArrayList<String>();
        userAgentList.add("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36");
        userAgentList.add("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.15; rv:73.0) Gecko/20100101 Firefox/73.0");
        userAgentList.add("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_3) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.0.5 Safari/605.1.15");
        userAgentList.add("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36 Edge/16.16299");
        userAgentList.add("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36");
        userAgentList.add("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:63.0) Gecko/20100101 Firefox/63.0");

    }

    public static String getHtml(String url) {
        String html = "";
        //1. 从连接池中获取连接对象
        CloseableHttpClient client = HttpClients.custom().setConnectionManager(cm).build();
        //2. 创建HttpGet请求并设置
        HttpGet get = new HttpGet(url);
        get.setConfig(requestConfig);
        get.setHeader("User-Agent",userAgentList.get(new Random().nextInt(userAgentList.size())));
        //3. 发起请求
        CloseableHttpResponse response = null;
        try {
            response = client.execute(get);
            //4. 判断响应状态并获取响应数据
            //4.1 200表示响应成功
            if(response.getStatusLine().getStatusCode()==200){
                if(response.getEntity()!=null){
                    html = EntityUtils.toString(response.getEntity(),"UTF-8");
                    return html;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //5. 关闭资源
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return html;
    }

    public static void main(String[] args) {
        String html = HttpUtil.getHtml("https://ncov.dxy.cn/ncovh5/view/pneumonia");
        System.out.println(html);
    }
}
