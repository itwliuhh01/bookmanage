package com.limai.requestVo;/**
 * Created by Administrator on 2018/11/8.
 */

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName QueryUserBorrowBookRequestVo
 * @Description c查询用户借阅数据入参实体
 * @Author LiuHaihua
 * @Date 2018/11/8 8:57
 * @Version 1.0
 **/
@ApiModel(value = "QueryUserBorrowBookRequestVo")
public class QueryUserBorrowBookRequestVo {
    @ApiModelProperty(value = "用户唯一id",example = "")
    private String userId;
    @ApiModelProperty(value = "查询标识'ALL':查询用户借阅所有借书,'DGH':查询用户待归还图书,'XJ':查询用户续借图书'YGH':查询用户已归还图书")
    private String queryFlag = "ALL";
    @ApiModelProperty(notes = "当前页",example = "1")
    private int pageNum=1;
    @ApiModelProperty(notes = "页大小",example = "10")
    private int pageSize=10;
    @ApiModelProperty(notes = "图书isbn号",example = "")
    private String isbn;

    public String getQueryFlag() {
        return queryFlag;
    }

    public void setQueryFlag(String queryFlag) {
        this.queryFlag = queryFlag;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

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

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
