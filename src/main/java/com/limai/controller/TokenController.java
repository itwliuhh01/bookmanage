package com.limai.controller;

import com.limai.responseVo.BaseResponse;
import com.limai.service.EmployeeService;
import com.limai.service.UserAuthenticationService;
import com.limai.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 获取和删除token的请求地址，在Restful设计中其实就对应着登录和退出登录的资源映射
 *
 */
@RestController
@RequestMapping("/tokens")
@CrossOrigin
public class TokenController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private UserAuthenticationService userAuthenticationService;

    private Logger logger = LoggerFactory.getLogger(TokenController.class);

    @CrossOrigin
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ApiOperation(value = "登录")
    public BaseResponse login(@RequestParam String username, @RequestParam String password){
        return userAuthenticationService.login(username,password);
    }

    @RequestMapping(value = "logout",method = RequestMethod.POST)
    @ApiOperation(value = "退出登录")
    public BaseResponse logout(@RequestParam String token) {
        return  userAuthenticationService.logout(token);
    }

}
