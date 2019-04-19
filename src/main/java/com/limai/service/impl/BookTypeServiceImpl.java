package com.limai.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.limai.dao.BookTypeMapper;
import com.limai.entity.BookType;
import com.limai.enumObj.ResMsgEnum;
import com.limai.requestVo.BookTypeRequestVo;
import com.limai.responseVo.BaseResponse;
import com.limai.responseVo.BookTypeResponseVo;
import com.limai.service.BookTypeService;
import com.limai.service.RedisService;
import com.limai.utils.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName BookTypeServiceImpl
 * @Description 查询所有图书类型service
 * @Author LiuHaihua
 * @Date 2018/11/5 14:34
 * @Version 1.0
 **/
@Service
public class BookTypeServiceImpl implements BookTypeService {

    @Autowired
    private BookTypeMapper bookTypeMapper;
    @Autowired
    private RedisService redisService;
    private Logger logger = LoggerFactory.getLogger(BookTypeServiceImpl.class);
    
    /**
     * @method 获取数据库中所有的图书类型
     * @author LiuHaihua
     * @Description 
     * @Date 14:37 2018/11/5
     * @return 返回所有的图书类型
     **/
    @Override
    public BookTypeResponseVo queryBookType() {
        BookTypeResponseVo bookTypeResponseVo = new BookTypeResponseVo();
        try{
//            先从redis中查询
            Object object =  redisService.getObject("bookTypeList");
            List<BookType> bookTypeList = null;
            if(null == object){
                logger.info("queryBookType方法从redis中没有查询到所有图书类型的key值");
                bookTypeList = bookTypeMapper.selectALL();
                bookTypeResponseVo.setData(bookTypeList);
                bookTypeResponseVo.setStatus(ResMsgEnum.C00001.getStatus());
                bookTypeResponseVo.setMessage(ResMsgEnum.C00001.getMessage());
                redisService.set("bookTypeList",bookTypeList);
            }else{
                logger.info("queryBookType方法所有图书类型是从redis中获取");
                if(object instanceof  String){
                    bookTypeList = JSON.parseArray((String)object,BookType.class);
                }
            }
            bookTypeResponseVo.setStatus(ResMsgEnum.C00001.getStatus());
            bookTypeResponseVo.setMessage(ResMsgEnum.C00001.getMessage());
            bookTypeResponseVo.setData(bookTypeList);
            logger.info("queryBookType方法出参:{}", JSONObject.toJSONString(bookTypeResponseVo));
        }catch (Exception e){
            logger.error("queryBookType方法发生异常:{}",e.getMessage());
            bookTypeResponseVo.setStatus(ResMsgEnum.C00002.getStatus());
            bookTypeResponseVo.setMessage(ResMsgEnum.C00002.getMessage());
        }
        return bookTypeResponseVo;
    }

    @Override
    public BookTypeResponseVo getBookTypeById(BookTypeRequestVo bookTypeRequestVo) {
        BookTypeResponseVo bookTypeResponseVo = new BookTypeResponseVo();
        try{
            logger.info("getBookTypeById方法入参{}:",JSONObject.toJSONString(bookTypeRequestVo));
            bookTypeMapper.queryBookTypeNameById(new BookType());
            //TODO
        }catch (Exception e){
            //TODO
        }
        return null;
    }
}
