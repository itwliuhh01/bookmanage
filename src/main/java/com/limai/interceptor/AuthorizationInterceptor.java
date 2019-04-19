package com.limai.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.limai.entity.User;
import com.limai.enumObj.ResMsgEnum;
import com.limai.responseVo.BaseResponse;
import com.limai.service.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @ClassName authorizationInterceptor
 * @Description 自定义登录拦截器，校验用户是否登录。
 * @Author LiuHaihua
 * @Date 2018/11/12 10:32
 * @Version 1.0
 **/
@Component("myAuthorizationInterceptor")
public class AuthorizationInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisService redisService;

    private Logger logger = LoggerFactory.getLogger(AuthorizationInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //判断用户是否登录
        httpServletResponse.addHeader("Access-Control-Allow-Headers","Content-Type");
        httpServletResponse.setCharacterEncoding("UTF-8");
        PrintWriter printWriter = new PrintWriter(httpServletResponse.getOutputStream());
        ObjectMapper objectMapper = new ObjectMapper();
        boolean result = false;
        String token = httpServletRequest.getParameter("token");
        if(!StringUtils.isEmpty(token)){
            User user = (User) redisService.getObject("bookmanage:authentication:"+token);
            if (user != null){
                result = true;
            }else{
                BaseResponse baseResponse = new BaseResponse();
                baseResponse.setMessage(ResMsgEnum.E00003.getMessage());
                baseResponse.setStatus(ResMsgEnum.E00003.getStatus());
                baseResponse.setData(false);
                logger.info("校验用户是否登录失败",JSONObject.toJSONString(baseResponse));
                printWriter.write(objectMapper.writeValueAsString(baseResponse));
                printWriter.flush();
                printWriter.close();
            }
        }else {
            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setMessage(ResMsgEnum.E00004.getMessage());
            baseResponse.setStatus(ResMsgEnum.E00004.getStatus());
            baseResponse.setData(false);
            logger.info("校验用户是否登录失败",JSONObject.toJSONString(baseResponse));
            printWriter.write(objectMapper.writeValueAsString(baseResponse));
            printWriter.flush();
            printWriter.close();
        }
        return result;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

}
