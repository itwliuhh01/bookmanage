package com.limai.myException;

import com.limai.enumObj.ResMsgEnum;

/**
 * @ClassName BorrowBookException
 * @Description 借阅图书时发生异常
 * @Author LiuHaihua
 * @Date 2018/11/5 20:28
 * @Version 1.0
 **/
public class BorrowBookException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    private String code;
    public BorrowBookException(){}
    public BorrowBookException(ResMsgEnum resMsgEnum){
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
