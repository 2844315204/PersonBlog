package com.lxn.code.service;

import com.lxn.code.bean.User;

public interface UserService {
    User checkUser(String username,String password);
}
