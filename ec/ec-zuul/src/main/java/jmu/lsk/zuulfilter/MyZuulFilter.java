package jmu.lsk.zuulfilter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import jmu.lsk.util.JwtUtil;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class MyZuulFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();//获取当此被拦截的请求
        String token = request.getHeader("token");
        System.out.println(token);
        //获取get post delete option等请求方式
        String method = request.getMethod();
        //获取对应的请求地址 登录页面不需要校验token
        String path = request.getServletPath();
        if(path.contains("doLogin") || method.equals("OPTIONS")){
            requestContext.setSendZuulResponse(true);
            requestContext.setResponseStatusCode(200);
        }else {
            if (token == null || token.isEmpty()) {
//                throw new RuntimeException("您还未登录，请登录！");
                System.out.println("您还未登录，请登录！");
                requestContext.setSendZuulResponse(false);//过滤该请求不对其进行路由
                requestContext.setResponseStatusCode(404);//返回错误码
            } else {
                System.out.println(token);
                try {
                    JwtUtil.parseJWT(token);
                    requestContext.setSendZuulResponse(true);
                    requestContext.setResponseStatusCode(200);
                } catch (Exception e) {
                    e.printStackTrace();
                    requestContext.setSendZuulResponse(false);//过滤该请求不对其进行路由
                    requestContext.setResponseStatusCode(404);//返回错误码
                }
            }
        }
        return null;
    }
}
