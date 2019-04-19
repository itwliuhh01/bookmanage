package com.limai.service;

import com.limai.entity.BookType;
import com.limai.requestVo.BookTypeRequestVo;
import com.limai.responseVo.BaseResponse;
import com.limai.responseVo.BookTypeResponseVo;

/**
/**
 * @ClassName BookTypeService
 * @Description 获取图书类型service
 * @Author LiuHaihua
 * @Date 2018/11/5 14:29
 * @Version 1.0
 **/
public interface BookTypeService {
    public BookTypeResponseVo queryBookType();
    public BookTypeResponseVo getBookTypeById(BookTypeRequestVo bookTypeRequestVo);
}
