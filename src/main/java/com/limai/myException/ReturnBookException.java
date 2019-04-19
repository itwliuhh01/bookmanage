package com.limai.myException;

import com.limai.enumObj.ResMsgEnum;

/**
 * @ClassName ReturnBookException
 * @Description 还书发生异常
 * @Author LiuHaihua
 * @Date 2018/11/5 20:35
 * @Version 1.0
 **/
public class ReturnBookException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    private String code;
    public ReturnBookException(){}
    public ReturnBookException(ResMsgEnum resMsgEnum){
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
