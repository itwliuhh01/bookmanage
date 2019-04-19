package com.limai.service;

import com.limai.responseVo.BaseResponse;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ClassName UserAuthenticationService
 * @Description 用户认证接口
 * @Author LiuHaihua
 * @Date 2018/11/4 16:08
 * @Version 1.0
 **/
public interface UserAuthenticationService {
    public BaseResponse login(@RequestParam String username, @RequestParam String password);
    public BaseResponse logout(String token);
}
