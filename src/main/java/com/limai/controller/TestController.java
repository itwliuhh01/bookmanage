package com.limai.controller;

import com.alibaba.fastjson.JSONObject;
import com.limai.responseVo.BaseResponse;
import com.limai.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2018/10/23.
 */
//@Api(value = "测试controller")
//@RestController
public class TestController {

//    @ApiOperation(value = "测试swagger",notes="测试swagger")
//    @RequestMapping(value = "/test",method = {RequestMethod.GET,RequestMethod.POST})
    public JSONObject test(){
        JSONObject json = new JSONObject();
        json.put("name","haihua");
        return json;
    }


    @Autowired
    private EmployeeService employeeService;

//    @ApiOperation(value = "测试swagger",notes="测试swagger")
//    @RequestMapping(value = "/test2",method = {RequestMethod.GET,RequestMethod.POST})
    public BaseResponse test2(@RequestParam String corpId, @RequestParam String Authorization){
        return employeeService.queryAlLEmployee(corpId,Authorization);
    }
//    @ApiOperation(value = "测试swagger",notes="测试swagger")
//    @RequestMapping(value = "/test3",method = {RequestMethod.GET,RequestMethod.POST})
    public BaseResponse test3(@RequestParam String corpId, @RequestParam String Authorization){
        return employeeService.queryALLDepts(corpId,Authorization);
    }
}
