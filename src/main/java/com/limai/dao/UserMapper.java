package com.limai.dao;

import com.limai.entity.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(String id);

    int insert(User record);

    User selectByPrimaryKey(String id);

    User login(String password);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectUserByMobile(String mobile);
    User selectUserByUserId(String userId);
}