package jmu.lsk.service;

import jmu.lsk.bean.User;

public interface UserService {
    User doLogin(String userName, String passWord);
}
