package com.limai.entity;

public class ReaderType {
    private Integer typeid;

    private String typename;

    private Integer limitdate;

    private Integer borrowmax;

    public Integer getTypeid() {
        return typeid;
    }

    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename == null ? null : typename.trim();
    }

    public Integer getLimitdate() {
        return limitdate;
    }

    public void setLimitdate(Integer limitdate) {
        this.limitdate = limitdate;
    }

    public Integer getBorrowmax() {
        return borrowmax;
    }

    public void setBorrowmax(Integer borrowmax) {
        this.borrowmax = borrowmax;
    }
}