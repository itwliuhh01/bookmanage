package com.limai.responseVo;


import com.limai.entity.Book;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "图书查询入参BookResponseVo")
public class BookResponseVo extends BaseResponse{
    @ApiModelProperty(notes = "",value = "")
    private Book data;

    public Book getData() {
        return data;
    }

    public void setData(Book data) {
        this.data = data;
    }
}
