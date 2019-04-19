package com.limai.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.limai.dao.UserMapper;
import com.limai.entity.User;
import com.limai.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("UserService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User login(String username) {
        return userMapper.login(username);
    }

    @Override
    public User selectByPrimaryKey(String id) {
        return userMapper.selectByPrimaryKey(id);
    }
    /**
     * @method 根据mobile查询用户
     * @author LiuHaihua
     * @Description
     * @Date 18:55 2018/11/10
     * @Param mobile 电话号码
     * @return 用户信息
     **/
    public User selectUserByMobile(String mobile){
        logger.info("selectUserByMobile入参：{}",mobile);
        User user = userMapper.selectUserByMobile(mobile);
        logger.info("selectUserByMobile出参：{}", JSONObject.toJSONString(user));
        return user;
    }
}
