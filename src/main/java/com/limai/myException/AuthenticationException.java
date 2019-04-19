package com.limai.myException;

import com.limai.enumObj.ResMsgEnum;

/**
 * @ClassName AuthenticationException
 * @Description 用户校验token失效异常
 * @Author LiuHaihua
 * @Date 2018/11/12 13:47
 * @Version 1.0
 **/
public class AuthenticationException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    private String code;
    public AuthenticationException(){}
    public AuthenticationException(ResMsgEnum resMsgEnum){
        super(resMsgEnum.getMessage());
        this.code = resMsgEnum.getStatus();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
