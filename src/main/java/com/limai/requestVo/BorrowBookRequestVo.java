package com.limai.requestVo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName BorrowBookRequestVo
 * @Description 图书借阅请求实体类
 * @Author LiuHaihua
 * @Date 2018/11/4 16:35
 * @Version 1.0
 **/
@ApiModel(value = "BorrowBookRequestVo")
public class BorrowBookRequestVo {
    @ApiModelProperty(notes = "图书唯一编号",example = "")
    private String isbn;
    @ApiModelProperty(notes = "用户唯一标识",example = "")
    private String userId;
//    @ApiModelProperty(notes = "归还日期")
//    private String returnDate;
//    @ApiModelProperty(notes = "借阅日期")
//    private String borrowDate;
    @ApiModelProperty(notes = "图书借阅表唯一标识id")
    private String bookUserid;

//    public String getBorrowDate() {
//        return borrowDate;
//    }
//
//    public void setBorrowDate(String borrowDate) {
//        this.borrowDate = borrowDate;
//    }

//    public String getReturnDate() {
//        return returnDate;
//    }
//
//    public void setReturnDate(String returnDate) {
//        this.returnDate = returnDate;
//    }


    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBookUserid() {
        return bookUserid;
    }

    public void setBookUserid(String bookUserid) {
        this.bookUserid = bookUserid;
    }
}
