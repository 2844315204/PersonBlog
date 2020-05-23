package com.lxn.code.service;

import com.lxn.code.bean.User;
import com.lxn.code.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService{

    @Resource
    private UserDao userDao;

    @Override
    public User checkUser(String username, String password) {
        return userDao.checkUser(username, DigestUtils.md5DigestAsHex(password.getBytes()));
    }
}
