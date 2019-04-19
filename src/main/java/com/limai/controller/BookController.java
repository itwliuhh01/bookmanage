package com.limai.controller;


import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.limai.entity.Book;
import com.limai.requestVo.BookPageRequestVo;
import com.limai.requestVo.BookRequestVo;
import com.limai.responseVo.BaseResponse;
import com.limai.responseVo.BookListResponseVo;
import com.limai.responseVo.BookResponseVo;
import com.limai.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Api(value = "图书管理")
@RequestMapping(value = "/book")
@CrossOrigin
public class BookController {
    @Autowired
    private BookService bookService;
    private Logger logger = LoggerFactory.getLogger(BookController.class);

    @ApiOperation(value = "图书入库",notes = "图书入库")
    @RequestMapping(value = "/storageBook",method = RequestMethod.POST)
    public BaseResponse storageBook(@Valid@RequestBody Book book){
        logger.info("图书入库操作入参：{}",JSONObject.toJSONString(book));
        return bookService.insert(book);
    }

    @ApiOperation(value = "根据isbn号删除图书",notes = "根据isbn号删除图书")
    @RequestMapping(value = "/deleteBookById",method = RequestMethod.POST)
    public BaseResponse deleteBookById(@Valid@RequestBody BookRequestVo bookRequestVo){
        return bookService.deleteBookById(bookRequestVo);
    }

    @ApiOperation(value = "根据图书编号查询图书",notes = "根据图书编号查询图书")
    @RequestMapping(value = "/queryBookById",method = RequestMethod.POST)
    public BookResponseVo queryBookById(@RequestBody BookRequestVo bookRequestVo){
        BookResponseVo bookResponseVo = bookService.queryBook(bookRequestVo);
        return  bookResponseVo;
    }

    @ApiOperation(value = "分页查询所有图书",notes = "分页查询所有图书")
    @RequestMapping(value = "/pageQueryAllBookList",method = RequestMethod.POST)
    public BookListResponseVo pageQueryAllBookList(@RequestBody BookPageRequestVo bookPageRequestVo){
        return bookService.getAll(bookPageRequestVo);
    }

    @ApiOperation(value = "按图书名称查询所有包含名字的图书",notes = "按图书名称查询所有包含名字的图书")
    @PostMapping(value = "/queryBookKindsByName")
    public BookListResponseVo queryBookKindsByName(@RequestBody BookPageRequestVo bookPageRequestVo){
        return bookService.queryBookKindsByName(bookPageRequestVo);
    }

    @ApiOperation(value = "",notes = "按图书名称查询指定的图书")
    @PostMapping(value = "/queryBookByName")
    public BookListResponseVo queryBookByName(@RequestBody BookPageRequestVo bookPageRequestVo){
        return bookService.queryBookByName(bookPageRequestVo);
    }

    @ApiOperation(value = "更新图书信息",notes = "更新图书信息")
    @RequestMapping(value = "/updateBook",method = RequestMethod.POST)
    public BaseResponse updateBook(@RequestBody Book book){
        return bookService.updateBook(book);
    }
}
