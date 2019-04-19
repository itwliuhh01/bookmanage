package com.limai.dao;

import com.limai.entity.Book;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface BookMapper {
    int deleteByPrimaryKey(String isbn);

    int insert(Book record);

    int insertSelective(Book record);

    Book selectByPrimaryKey(String isbn);

    int updateBookStatus(Book record);

    int updateByPrimaryKey(Book record);

    List<Book> findAll();

    List<Book> queryBookKindsByName(Book record);

    List<Book> queryBookByName(Book record);

    Integer updateBook(Book record);
}