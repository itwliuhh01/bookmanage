package com.limai.service;


import com.limai.entity.User;

public interface UserService {
    public User login(String username);

    public User selectByPrimaryKey(String id);
    public User selectUserByMobile(String mobile);
}
