package jmu.lsk.controller;

import com.alibaba.fastjson.JSON;
import jmu.lsk.bean.User;
import jmu.lsk.service.UserService;
import jmu.lsk.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("doLogin")
    public String doLogin(String userName,String userPassword){
        User user=userService.doLogin(userName,userPassword);
        Map<String,String> map =new HashMap<String,String>();
        if(user!=null){
            String token = JwtUtil.createJWT(UUID.randomUUID().toString(), userName, null);
            System.out.println(token);
            map.put("user",user.getRealName());
            map.put("result","true");
            map.put("token",token);
        }else{
            map.put("result","false");
        }
        return JSON.toJSONString(map);
    }
}
