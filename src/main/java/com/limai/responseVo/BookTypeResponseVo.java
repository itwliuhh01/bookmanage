package com.limai.responseVo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.limai.entity.BookType;
import io.swagger.annotations.ApiModel;

import java.util.List;

/**
 * @ClassName BookTypeResponseVo
 * @Description 图书类型出参实体类
 * @Author LiuHaihua
 * @Date 2018/11/5 14:43
 * @Version 1.0
 **/
@ApiModel(value = "图书类型返回实体")
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookTypeResponseVo extends BaseResponse{
    private List<BookType> data;

    public List<BookType> getData() {
        return data;
    }

    public void setData(List<BookType> data) {
        this.data = data;
    }
}
