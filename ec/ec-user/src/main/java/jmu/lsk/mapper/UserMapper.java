package jmu.lsk.mapper;


import jmu.lsk.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("select * from user where userName=#{userName} and passWord=#{passWord}")
    User doLogin(String userName, String passWord);
}
