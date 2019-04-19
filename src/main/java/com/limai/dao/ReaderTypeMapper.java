package com.limai.dao;

import com.limai.entity.ReaderType;
import org.mapstruct.Mapper;

@Mapper
public interface ReaderTypeMapper {
    int deleteByPrimaryKey(Integer typeid);

    int insert(ReaderType record);

    int insertSelective(ReaderType record);

    ReaderType selectByPrimaryKey(Integer typeid);

    int updateByPrimaryKeySelective(ReaderType record);

    int updateByPrimaryKey(ReaderType record);
}