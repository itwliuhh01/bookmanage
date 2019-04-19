package com.limai.requestVo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName BookTypeRequestVo
 * @Description TODO
 * @Author LiuHaihua
 * @Date 2018/11/6 21:21
 * @Version 1.0
 **/
@ApiModel(value = "bookTypeRequestVo")
public class BookTypeRequestVo {

    @ApiModelProperty(notes = "图书类型",example = "A")
    private String bookTypeId;

    public String getBookTypeId() {
        return bookTypeId;
    }

    public void setBookTypeId(String bookTypeId) {
        this.bookTypeId = bookTypeId;
    }
}
