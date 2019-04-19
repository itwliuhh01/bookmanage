package com.limai.config;

import com.alibaba.fastjson.JSONObject;
import com.limai.enumObj.ResMsgEnum;
import com.limai.myException.BorrowBookException;
import com.limai.myException.LoginException;
import com.limai.myException.ReturnBookException;
import com.limai.responseVo.BaseResponse;
import com.limai.responseVo.BookResponseVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName GlobalDefaultExceptionHandler
 * @Description 全局的异常处理类，当抛出异常的时候返回给前台的格式不统一
 * 使用全局异常来处理
 * @Author LiuHaihua
 * @Date 2018/11/5 20:08
 * @Version 1.0
 **/
@ControllerAdvice
public class GlobalDefaultExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(GlobalDefaultExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public BaseResponse defaultExceptionHandler(HttpServletRequest request, Exception e){
        logger.error(">>>>>>>>>>>>>全局异常处理捕获到异常>>>>>>>>>>>>>>>");
        e.printStackTrace();;
        BaseResponse baseResponse = new BaseResponse();
         if (e instanceof BorrowBookException){
            baseResponse.setMessage(e.getMessage());
            baseResponse.setStatus(((BorrowBookException) e).getCode());
        }else if(e instanceof ReturnBookException){
            baseResponse.setMessage(e.getMessage());
            baseResponse.setStatus(((ReturnBookException) e).getCode());
        }else if(e instanceof RuntimeException ){
            baseResponse.setMessage(ResMsgEnum.C00002.getMessage());
            baseResponse.setStatus(ResMsgEnum.C00002.getStatus());
        }else if(e instanceof MethodArgumentNotValidException){
             BindingResult bindingResult = ((MethodArgumentNotValidException) e).getBindingResult();
             String errorMesssage = "校验失败:";
             for (FieldError fieldError : bindingResult.getFieldErrors()) {
                 errorMesssage += fieldError.getDefaultMessage() + ", ";
             }
             baseResponse.setMessage(errorMesssage);
             baseResponse.setStatus(ResMsgEnum.C00004.getStatus());
         }else if(e instanceof LoginException){
             //登录失败
             baseResponse.setMessage(e.getMessage());
             baseResponse.setStatus(((LoginException) e).getCode());
         }
        baseResponse.setData(false);
        logger.error("全局异常处理返回结果:{}", JSONObject.toJSONString(baseResponse));
        return baseResponse;
    }
}
