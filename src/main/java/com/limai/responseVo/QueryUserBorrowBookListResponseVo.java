package com.limai.responseVo;

import com.limai.entity.BookType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @ClassName QueryUserBorrowBooksResponse
 * @Description 查询用户借阅图书出参实体
 * @Author LiuHaihua
 * @Date 2018/11/8 10:03
 * @Version 1.0
 **/
@ApiModel(value = "QueryUserBorrowBooksResponseVo")
public class QueryUserBorrowBookListResponseVo{
    @ApiModelProperty(value = "用户借阅图书出参")
    private List<QueryUserBorrowBookResponseVo> bookTypes;

    public List<QueryUserBorrowBookResponseVo> getBookTypes() {
        return bookTypes;
    }

    public void setBookTypes(List<QueryUserBorrowBookResponseVo> bookTypes) {
        this.bookTypes = bookTypes;
    }
}
