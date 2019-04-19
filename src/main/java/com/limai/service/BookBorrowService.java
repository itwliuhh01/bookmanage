package com.limai.service;

import com.limai.requestVo.BorrowBookRequestVo;
import com.limai.requestVo.QueryUserBorrowBookRequestVo;
import com.limai.responseVo.BaseResponse;

/**
 * Created by Administrator on 2018/11/4.
 */
public interface BookBorrowService {
    public BaseResponse borrowBook(String token,BorrowBookRequestVo borrowBookRequestVo);
    public BaseResponse returnBook(BorrowBookRequestVo borrowBookRequestVo);
    public BaseResponse renewBook(BorrowBookRequestVo borrowBookRequestVo);
    public BaseResponse queryUserBorrowedBooks(String token,QueryUserBorrowBookRequestVo queryUserBorrBookRequestVo);
}
