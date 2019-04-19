package com.limai.dao;

import com.limai.entity.Reader;
import org.mapstruct.Mapper;

@Mapper
public interface ReaderMapper {
    int deleteByPrimaryKey(String readerid);

    int insert(Reader record);

    int insertSelective(Reader record);

    Reader selectByPrimaryKey(String readerid);

    int updateByPrimaryKeySelective(Reader record);

    int updateByPrimaryKey(Reader record);
}