package com.limai.controller;

import com.limai.requestVo.BookTypeRequestVo;
import com.limai.responseVo.BookTypeResponseVo;
import com.limai.service.BookTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName BookTypeController
 * @Description 查询所有的图书类型
 * @Author LiuHaihua
 * @Date 2018/11/5 16:41
 * @Version 1.0
 **/
@RestController
@RequestMapping("/bookType")
@Api(value = "BookTypeController")
@CrossOrigin
public class BookTypeController {

    @Autowired
    private BookTypeService bookTypeService;

    @ApiOperation(value = "查询所有图书类型",notes = "查询所有图书类型")
    @RequestMapping(value = "/getAllBookTypes",method = RequestMethod.POST)
    public BookTypeResponseVo getAllBookTypes(){
        return bookTypeService.queryBookType();
    }

    @ApiOperation(value = "根据图书类型id查询图书类型名称",notes = "根据图书类型id查询图书类型名称")
    @RequestMapping(value = "bookTypeNameById",method = RequestMethod.POST)
    public BookTypeResponseVo bookTypeNameById(@RequestBody BookTypeRequestVo bookTypeRequestVo){
        return bookTypeService.getBookTypeById(bookTypeRequestVo);
    }

}
