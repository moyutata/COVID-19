package jmu.lsk.service.impl;

import jmu.lsk.bean.User;
import jmu.lsk.mapper.UserMapper;
import jmu.lsk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("UserService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User doLogin(String userName, String passWord) {
        User data =userMapper.doLogin(userName,passWord);
        return data;
    }
}
