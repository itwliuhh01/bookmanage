package com.limai.myException;

import com.limai.enumObj.ResMsgEnum;

/**
 * @ClassName LoginException
 * @Description 自定义用户登录异常
 * @Author LiuHaihua
 * @Date 2018/11/10 15:25
 * @Version 1.0
 **/
public class LoginException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    private String code;
    public LoginException(){}
    public LoginException(ResMsgEnum resMsgEnum){
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
