package com.limai.dao;

import com.limai.entity.BookUser;
import com.limai.requestVo.BorrowBookRequestVo;
import com.limai.requestVo.QueryUserBorrowBookRequestVo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface BookUserMapper {
    int insert(BookUser record);
    List<BookUser> queryUserBorrowBooks(QueryUserBorrowBookRequestVo queryUserBorrowBookRequestVo);
    Integer updateBookUser( BookUser bookUser);
}
