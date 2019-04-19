package com.limai.entity;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class BookType implements Serializable{
    private static final  long serialVersionUID = 1L;
    @ApiModelProperty(value = "图书类别",example = "")
    private String typeId;
    @ApiModelProperty(value = "图书类型名称",example = "")
    private String typename;

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename == null ? null : typename.trim();
    }
}