package com.limai.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.limai.dao.BookMapper;
import com.limai.entity.Book;
import com.limai.enumObj.ResMsgEnum;
import com.limai.requestVo.BookPageRequestVo;
import com.limai.requestVo.BookRequestVo;
import com.limai.responseVo.BaseResponse;
import com.limai.responseVo.BookListResponseVo;
import com.limai.responseVo.BookResponseVo;
import com.limai.service.BookService;
import com.limai.utils.TimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

/**
 * Created by Administrator on 2018/10/26.
 */
@Service("bookService")
public class BookServiceImpl implements BookService {
    @Autowired
    private BookMapper bookMapper;
    private Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);
    
    /**
     * @method insert
     * @author LiuHaihua
     * @Description 图书入库
     * @Date 14:57 2018/11/3
     * @Param book
     * @return 是否
     **/
    @Override
    public BaseResponse insert(Book book) {
        logger.info("insert方法入参{}",JSONObject.toJSONString(book));
        BaseResponse baseResponse = new BaseResponse();
        try{
            //查询图书是否存在，存在提示图书isbn号已存在
            String isbn = book.getIsbn();
            Book b = bookMapper.selectByPrimaryKey(isbn);
            if(b == null){
                int i = bookMapper.insert(book);
                if(i>0){
                    baseResponse.setStatus(ResMsgEnum.C00001.getStatus());
                    baseResponse.setMessage(ResMsgEnum.C00001.getMessage());
                    baseResponse.setData(true);
                }else{
                    baseResponse.setStatus(ResMsgEnum.C00005.getStatus());
                    baseResponse.setMessage(ResMsgEnum.C00005.getMessage());
                    baseResponse.setData(false);
                }
            }else{
                baseResponse.setStatus(ResMsgEnum.C00005.getStatus());
                baseResponse.setMessage(ResMsgEnum.C00005.getMessage());
                baseResponse.setData(false);
            }
        }catch (Exception e){
            logger.error("insert方法发生异常{}:",e.getMessage());
            baseResponse.setStatus(ResMsgEnum.C00002.getStatus());
            baseResponse.setMessage(ResMsgEnum.C00002.getMessage());
        }
        logger.info("insert方法出参{}",JSONObject.toJSONString(baseResponse));
        return baseResponse;
    }

//    /**
//     * @method 根据主键修改图书信息
//     * @author LiuHaihua
//     * @Description
//     * @Date 16:21 2018/11/4
//     * @Param
//     * @return BaseResponse
//     **/
//    public BaseResponse updateBookById(Book book){
//        logger.info("updateBookById方法入参{}:", JSONObject.toJSONString(bookRequestVo));
//        try{
//            String isbn = book.getIsbn();
//            if(!StringUtils.isEmpty(isbn)){
//                Book resultBook =bookMapper.selectByPrimaryKey(isbn);
//                BeanUtils.copyProperties(book,resultBook);
//            }
//
//        }catch (Exception e){
//
//        }
//        return null;
//    }

    /**
     * @method deleteBookById
     * @author LiuHaihua
     * @Description 根据isbn号删除指定的图书
     * @Date 14:25 2018/11/3
     * @Param bookRequestVo 请求实体
     * @return BaseResponse 返回删除是否成功
     **/
    public BaseResponse deleteBookById(BookRequestVo bookRequestVo){
        logger.info("deleteBookById方法入参{}:", JSONObject.toJSONString(bookRequestVo));
        BaseResponse baseResponse = new  BaseResponse();
        try{
            String isbn = bookRequestVo.getIsbn();
            if (!StringUtils.isEmpty(isbn)){
                int resultInt = bookMapper.deleteByPrimaryKey(isbn);
                baseResponse.setStatus(ResMsgEnum.C00001.getStatus());
                baseResponse.setMessage(ResMsgEnum.C00001.getMessage());
                baseResponse.setData(true);
            }else{
                baseResponse.setStatus(ResMsgEnum.C00004.getStatus());
                baseResponse.setMessage(ResMsgEnum.C00004.getMessage());
                baseResponse.setData(true);
            }
        }catch (Exception e){
            logger.error("deleteBookById发生异常{}:",e.getMessage());
            baseResponse.setStatus(ResMsgEnum.C00002.getStatus());
            baseResponse.setMessage(ResMsgEnum.C00002.getMessage());
        }
        logger.info("deleteBookById方法出参{}:", JSONObject.toJSONString(baseResponse));
        return  baseResponse;
    }

    @Override
    public BookResponseVo queryBook(BookRequestVo bookRequestVo) {
        logger.info("queryBook方法入参{}:", JSONObject.toJSONString(bookRequestVo));
        BookResponseVo bookResponseVo = new BookResponseVo();
        bookResponseVo.setStatus(ResMsgEnum.C00001.getStatus());
        bookResponseVo.setMessage(ResMsgEnum.C00001.getMessage());
        try{
            String isbn = bookRequestVo.getIsbn();
            Book book = null;
            if (!StringUtils.isEmpty(isbn)){
                book = bookMapper.selectByPrimaryKey(bookRequestVo.getIsbn());
                book.setPublicationdate(TimeUtils.formatDateStr(book.getPublicationdate()));
                bookResponseVo.setData(book);
            }
        }catch (Exception e){
            logger.error("queryBook发生异常{}:",e.getMessage());
            bookResponseVo.setStatus(ResMsgEnum.C00002.getStatus());
            bookResponseVo.setMessage(ResMsgEnum.C00002.getMessage());
        }
        logger.info("queryBook方法出参{}:", JSONObject.toJSONString(bookResponseVo));
        return bookResponseVo;
    }

    @Override
    public BookListResponseVo getAll(BookPageRequestVo bookPageRequestVo) {
        BookListResponseVo bookListResponseVo = new BookListResponseVo();
        try{
            PageHelper.startPage(bookPageRequestVo.getPageNum(),bookPageRequestVo.getPageSize());
            List<Book> bookList = bookMapper.findAll();
            PageInfo pageInfo = new PageInfo(bookList);
            bookListResponseVo.setData(pageInfo);
            bookListResponseVo.setStatus(ResMsgEnum.C00001.getStatus());
            bookListResponseVo.setMessage(ResMsgEnum.C00001.getMessage());
        }catch (Exception e){
            logger.error("getAll发生异常{}:",e.getMessage());
            bookListResponseVo.setStatus(ResMsgEnum.C00002.getStatus());
            bookListResponseVo.setMessage(ResMsgEnum.C00002.getMessage());
        }
        return bookListResponseVo;
    }
    /**
     * @method queryBookByName
     * @author LiuHaihua
     * @Description 根据图书名称，模糊查询有多少种书
     * @Date 13:44 2018/11/4
     * @Param bookPageRequestVo
     * @return BookListResponseVo 分页响应实体
     **/
    @Override
    public BookListResponseVo queryBookKindsByName(BookPageRequestVo bookPageRequestVo) {
        logger.info("queryBookKindsByName方法入参:{}",JSONObject.toJSONString(bookPageRequestVo));
        BookListResponseVo bookListResponseVo = new BookListResponseVo();
        try{
            Book book = new Book();
            book.setBookName(bookPageRequestVo.getBookName());
            PageHelper.startPage(bookPageRequestVo.getPageNum(),bookPageRequestVo.getPageSize());
            List<Book> bookList = bookMapper.queryBookKindsByName(book);
            PageInfo pageInfo = new PageInfo(bookList);
            bookListResponseVo.setData(pageInfo);
            bookListResponseVo.setStatus(ResMsgEnum.C00001.getStatus());
            bookListResponseVo.setMessage(ResMsgEnum.C00001.getMessage());
        }catch (Exception e){
            logger.error("queryBookKindsByName发生异常{}:",e.getMessage());
            bookListResponseVo.setStatus(ResMsgEnum.C00002.getStatus());
            bookListResponseVo.setMessage(ResMsgEnum.C00002.getMessage());
        }
        logger.info("queryBookKindsByName方法出参:{}",JSONObject.toJSONString(bookListResponseVo));
        return bookListResponseVo;
    }
    /**
     * 根据图书的书名作者出版社查询图书信息
     * @method queryBookByName
     * @author LiuHaihua
     * @Description
     * @Date 14:32 2018/11/4
     * @Param bookPageRequestVo
     * @return BookListResponseVo
     **/
    @Override
    public BookListResponseVo queryBookByName(BookPageRequestVo bookPageRequestVo){
        logger.info("queryBookByName方法入参{}:",JSONObject.toJSONString(bookPageRequestVo));
        BookListResponseVo bookListResponseVo = new BookListResponseVo();
        try {
            Book book = new Book();
            BeanUtils.copyProperties(bookPageRequestVo,book);
            PageHelper.startPage(1, Integer.MAX_VALUE);
            List<Book> bookList = bookMapper.queryBookByName(book);
            PageInfo pageInfo = new PageInfo(bookList);
            bookListResponseVo.setStatus(ResMsgEnum.C00001.getStatus());
            bookListResponseVo.setMessage(ResMsgEnum.C00001.getMessage());
            bookListResponseVo.setData(pageInfo);
        }catch (Exception e){
            logger.error("queryBookByName方法发生异常:{}",e.getMessage());
            bookListResponseVo.setStatus(ResMsgEnum.C00002.getStatus());
            bookListResponseVo.setMessage(ResMsgEnum.C00002.getMessage());
        }
        logger.info("queryBookByName方法出参{}:",JSONObject.toJSONString(bookListResponseVo));
        return  bookListResponseVo;
    }

    @Override
    public BaseResponse updateBook(Book book) {
        BaseResponse baseResponse = new BaseResponse();
        logger.info("updateBook方法入参:{}",JSONObject.toJSONString(book));
        try{
           Integer result =  bookMapper.updateBook(book);
            if (result>0){
                baseResponse.setStatus(ResMsgEnum.C00001.getStatus());
                baseResponse.setMessage(ResMsgEnum.C00001.getMessage());
                baseResponse.setData(true);
            }else{
                baseResponse.setStatus(ResMsgEnum.C00005.getStatus());
                baseResponse.setMessage(ResMsgEnum.C00005.getMessage());
                baseResponse.setData(true);
            }
        }catch (Exception e){
            logger.error("updateBook发生异常:{}",e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        logger.info("updateBook方法出参:{}",JSONObject.toJSONString(baseResponse));
        return baseResponse;
    }
}
