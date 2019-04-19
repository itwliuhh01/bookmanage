package com.limai.controller;

import com.limai.requestVo.BookPageRequestVo;
import com.limai.requestVo.BorrowBookRequestVo;
import com.limai.requestVo.QueryUserBorrowBookRequestVo;
import com.limai.responseVo.BaseResponse;
import com.limai.service.BookBorrowService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName BookBorrowController
 * @Description 图书借阅控制类
 * @Author LiuHaihua
 * @Date 2018/11/4 13:12
 * @Version 1.0
 **/
@RequestMapping(value = "/borrow")
@RestController
@CrossOrigin
public class BookBorrowController {

    @Autowired
    private BookBorrowService bookBorrowService;

    @ApiOperation(value = "借书",notes = "借书")
    @RequestMapping(value = "/bookBorrow",method = RequestMethod.POST)
    public BaseResponse bookBorrow(@RequestParam  String token,@RequestBody BorrowBookRequestVo borrowBookRequestVo){
        return bookBorrowService.borrowBook(token,borrowBookRequestVo);
    }

    @ApiOperation(value = "还书",notes = "还书")
    @RequestMapping(value = "/returnBook",method = RequestMethod.POST)
    public BaseResponse returnBook(@RequestBody BorrowBookRequestVo borrowBookRequestVo){
        return bookBorrowService.returnBook(borrowBookRequestVo);
    }

    @ApiOperation(value = "续借",notes = "续借")
    @RequestMapping(value = "/renewBook",method = RequestMethod.POST)
    public BaseResponse renewBook(@RequestBody BorrowBookRequestVo borrowBookRequestVo){
        return bookBorrowService.renewBook(borrowBookRequestVo);
    }

    @ApiOperation(value = "查询用户已借阅的图书",notes = "查询用户已借阅的图书")
    @RequestMapping(value = "/queryUserBorrowedBooks",method = RequestMethod.POST)
    public BaseResponse queryUserBorrowedBooks(@RequestParam String token,@RequestBody QueryUserBorrowBookRequestVo queryUserBorrBookRequestVo){
        return  bookBorrowService.queryUserBorrowedBooks(token,queryUserBorrBookRequestVo);
    }
}
