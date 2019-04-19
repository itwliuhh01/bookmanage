package com.limai.entity;

public class BorrowBookKey {
    private String readerid;

    private String isbn;

    public String getReaderid() {
        return readerid;
    }

    public void setReaderid(String readerid) {
        this.readerid = readerid == null ? null : readerid.trim();
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn == null ? null : isbn.trim();
    }
}