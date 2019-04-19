package com.limai.dao;

import com.limai.entity.BorrowBook;
import com.limai.entity.BorrowBookKey;
import org.mapstruct.Mapper;

@Mapper
public interface BorrowBookMapper {
    int deleteByPrimaryKey(BorrowBookKey key);

    int insert(BorrowBook record);

    int insertSelective(BorrowBook record);

    BorrowBook selectByPrimaryKey(BorrowBookKey key);

    int updateByPrimaryKeySelective(BorrowBook record);

    int updateByPrimaryKey(BorrowBook record);
}