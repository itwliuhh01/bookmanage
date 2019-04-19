package com.limai.requestVo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 图分页查询入参实体
 * @ClassName BookPageRequestVo
 * @Description 图分页查询入参实体
 * @Author LiuHaihua
 * @Date 2018/11/4 11:35
 * @Version 1.0
 **/
@ApiModel(value = "图书分页请求入参实体")
public class BookPageRequestVo {
    @ApiModelProperty(notes = "当前页",example = "1")
    private int pageNum=1;
    @ApiModelProperty(notes = "页大小",example = "10")
    private int pageSize=10;
    @ApiModelProperty(notes = "图书名称",example = "")
    private String bookName;
//    @ApiModelProperty(notes = "借阅人id",example = "")
//    private String userId;
    @ApiModelProperty(notes = "作者",example = "")
    private String author;
    @ApiModelProperty(notes = "出版社",example = "")
    private String publish;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

//    public String getUserId() {
//        return userId;
//    }
//
//    public void setUserId(String userId) {
//        this.userId = userId;
//    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }
}
