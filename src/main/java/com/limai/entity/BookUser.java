package com.limai.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
/**
 * @ClassName BookUser
 * @Description 图书借阅关系实体
 * @Author LiuHaihua
 * @Date 2018/11/4 15:47
 * @Version 1.0
 **/
@ApiModel(value = "BookUser")
public class BookUser {
    @ApiModelProperty(notes = "主键id",example = "")
    private String id;
    @ApiModelProperty(notes = "图书唯一编号",example = "")
    private String bookIsbn;
    @ApiModelProperty(notes = "用户id",example = "")
    private String userId;
    @ApiModelProperty(notes = "图书借阅状态",example = "")
    private String borrowStatus;
    @ApiModelProperty(notes = "图书归还时间",example = "")
    private String returnDate;
    @ApiModelProperty(notes = "图书借阅时间",example = "")
    private String borrowDate;
    @ApiModelProperty(notes = "图书实体",example = "")
    private Book book;


    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getBookIsbn() {
        return bookIsbn;
    }

    public void setBookIsbn(String bookIsbn) {
        this.bookIsbn = bookIsbn;
    }

    public String getBorrowStatus() {
        return borrowStatus;
    }

    public void setBorrowStatus(String borrowStatus) {
        this.borrowStatus = borrowStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
