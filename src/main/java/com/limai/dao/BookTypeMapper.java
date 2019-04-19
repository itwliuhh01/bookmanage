package com.limai.dao;

import com.limai.entity.BookType;
import com.limai.responseVo.BookTypeResponseVo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface BookTypeMapper {
    /**
     * @method selectALL
     * @author LiuHaihua
     * @Description 查询所有的图书类型
     * @Date 16:36 2018/11/5
     * @return BookTypeResponseVo 所有的图书类型
     **/
    public List<BookType> selectALL();
    /**
     * @method queryBookTypeNameById
     * @author LiuHaihua
     * @Description 
     * @Date 11:05 2018/11/7
     * @Param bookType
     * @return BookType
     **/
    public BookType queryBookTypeNameById(BookType bookType);
}