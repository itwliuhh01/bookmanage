package com.limai.controller;

import com.limai.entity.User;
import com.limai.responseVo.BaseResponse;
import com.limai.service.EmployeeService;
import com.limai.service.RedisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName organizationController
 * @Description 返回藜麦所有的部门结构
 * @Author LiuHaihua
 * @Date 2018/11/12 13:32
 * @Version 1.0
 **/
@Api("组织结构")
@CrossOrigin
@RestController
@RequestMapping(value = "/organization")
public class organizationController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private RedisService redisService;

    @ApiOperation(value = "所有部门",notes = "所有部门")
    @RequestMapping(value = "queryAllDepts",method = RequestMethod.POST)
    public BaseResponse queryAllDepts(@RequestParam String token){
        User user = (User)redisService.getObject("bookmanage:authentication:"+token);
        return employeeService.queryALLDepts(user.getCorpId(),user.getDingToken());
    }
}
