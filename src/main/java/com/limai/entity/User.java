package com.limai.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "User")
public class User implements Serializable{
    @ApiModelProperty(notes = "id(主键)",example = "123")
    private String id;
    @ApiModelProperty(notes = "用戶名",example = "admin")
    private String username;
    @ApiModelProperty(notes = "密碼",example = "password")
    private String password;
    @ApiModelProperty(notes = "用户编号",example = "")
    private String userId;
    @ApiModelProperty(notes = "电话",example = "")
    private String mobile;
    @ApiModelProperty(notes = "用户状态",example = "")
    private int active;
    @ApiModelProperty(notes = "用户部门",example = "")
    private String departmentId;
    @ApiModelProperty(notes = "职位",example = "")
    private String position;
    @ApiModelProperty(notes = "员工编号",example = "")
    private String jobNumber;
    @ApiModelProperty(notes = "入职日期",example = "")
    private String hiredDate;
    @ApiModelProperty(notes = "企业id",example = "")
    private String corpId;
    @ApiModelProperty(notes = "钉钉令牌token",example = "")
    private String dingToken;


    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getHiredDate() {
        return hiredDate;
    }

    public void setHiredDate(String hiredDate) {
        this.hiredDate = hiredDate;
    }



    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    public String getCorpId() {
        return corpId;
    }

    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    public String getDingToken() {
        return dingToken;
    }

    public void setDingToken(String dingToken) {
        this.dingToken = dingToken;
    }
}