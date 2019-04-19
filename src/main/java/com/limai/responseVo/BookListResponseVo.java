package com.limai.responseVo;

import com.github.pagehelper.PageInfo;
import com.limai.entity.Book;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @ClassName BookListResponseVo
 * @Description 图书分页请求实体
 * @Author LiuHaihua
 * @Date 2018/11/3 16:53
 * @Version 1.0
 **/
@ApiModel(value = "图书分页请求")
public class BookListResponseVo extends BaseResponse{
    @ApiModelProperty(notes = "")
    PageInfo<List> data;

    public PageInfo<List> getData() {
        return data;
    }

    public void setData(PageInfo<List> data) {
        this.data = data;
    }
}
