package com.lxn.code.dao;

import com.lxn.code.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserDao {
    User checkUser(@Param("username") String username,@Param("password") String password);
}
