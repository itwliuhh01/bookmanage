package com.limai.service;

import com.limai.entity.Book;
import com.limai.requestVo.BookPageRequestVo;
import com.limai.requestVo.BookRequestVo;
import com.limai.responseVo.BaseResponse;
import com.limai.responseVo.BookListResponseVo;
import com.limai.responseVo.BookResponseVo;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface BookService {
    public BaseResponse insert(Book book);
    public BookResponseVo queryBook(BookRequestVo bookRequestVo);
    public BaseResponse deleteBookById(BookRequestVo bookRequestVo);
    public BookListResponseVo getAll(BookPageRequestVo bookPageRequestVo);
    public BookListResponseVo queryBookKindsByName(BookPageRequestVo bookPageRequestVo);
    public BookListResponseVo queryBookByName(BookPageRequestVo bookPageRequestVo);
    public BaseResponse updateBook(Book book);
}
