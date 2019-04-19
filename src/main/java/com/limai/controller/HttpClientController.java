package com.limai.controller;

import com.limai.entity.Person;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

@RestController
@Api("httpClient测试方法")
@RequestMapping(value = "/httpClient")
public class HttpClientController {

    @RequestMapping(value = "/postObjectAndCommonParam",method = RequestMethod.POST)
    public String postObjectAndCommonParam(@RequestBody Person person,String method){
        return "httpClient测试post请求发送对象以及普通参数："+person.toString()+",method："+method;
    }

    @RequestMapping(value = "/postObjectParam",method = RequestMethod.POST)
    public String postObjectParam(@RequestBody Person person){
        return "httpClient测试post发送对象"+person.toString();
    }

    @RequestMapping(value = "/postCommonParam",method = RequestMethod.POST)
    public String postCommonParam(String name,String age){
        return "post请求，普通参数，接收参数name="+name+",age="+age;
    }

    @RequestMapping(value = "/postNoParam",method = RequestMethod.POST)
    public String postNoParam(){
        return "httpClient测试post无参数";
    }


}
