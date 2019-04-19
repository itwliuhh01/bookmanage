package com.limai.enumObj;

/**
 * Created by Administrator on 2018/11/2.
 */
public enum ResMsgEnum {
    C00001("1","返回成功"),
    C00002("2","服务器网络异常"),
    C00003("3","返回数据为空"),
    C00004("4","参数错误"),
    E00001("5","借书操作发生异常"),
    E00002("6","还书操作发生异常"),
    E00003("14","登录失效"),
    E00004("16","token不能为空"),
    L00002("8","管理员登陆成功"),
    L00003("9", "用户名或密码错误"),
    L00004("10", "用户不存在"),
    L00005("11", "用户未登录"),
    L00006("12","用户登录发生错误，稍后重试"),
    C00005("7","没有更新任何数据"),
    C00006("13","查询不到该用户"),
    C00007("15","图书isbn已存在");

    private String status;
    private String message;

    ResMsgEnum(String status, String message){
        this.status = status;
        this.message = message;
    }

    public static ResMsgEnum getByStatus(String status){
        return ResMsgEnum.valueOf(status.toUpperCase());
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
