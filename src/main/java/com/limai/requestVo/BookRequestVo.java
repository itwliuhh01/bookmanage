package com.limai.requestVo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

@ApiModel(value = "图书查询请求报文BookRequestVo")
public class BookRequestVo {
    @ApiModelProperty(notes = "图书唯一编号")
    @NotBlank(message = "isbn不能为空")
    private String isbn;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

}
