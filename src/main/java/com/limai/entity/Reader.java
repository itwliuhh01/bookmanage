package com.limai.entity;

import java.util.Date;

public class Reader {
    private String readerid;

    private String readername;

    private String readersex;

    private String phone;

    private String dept;

    private Integer type;

    private Date regdate;

    public String getReaderid() {
        return readerid;
    }

    public void setReaderid(String readerid) {
        this.readerid = readerid == null ? null : readerid.trim();
    }

    public String getReadername() {
        return readername;
    }

    public void setReadername(String readername) {
        this.readername = readername == null ? null : readername.trim();
    }

    public String getReadersex() {
        return readersex;
    }

    public void setReadersex(String readersex) {
        this.readersex = readersex == null ? null : readersex.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept == null ? null : dept.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getRegdate() {
        return regdate;
    }

    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }
}