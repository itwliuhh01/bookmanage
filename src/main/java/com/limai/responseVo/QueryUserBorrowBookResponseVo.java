package com.limai.responseVo;

import com.limai.entity.BookType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName QueryUserBorrowBookResponseVo
 * @Description 用户借阅图书综合出参（关联book、book_type、bookuser表）
 * @Author LiuHaihua
 * @Date 2018/11/8 10:14
 * @Version 1.0
 **/
@ApiModel(value = "QueryUserBorrowBookResponseVo")
public class QueryUserBorrowBookResponseVo {
    @ApiModelProperty(notes = "图书ISBN编号(主键)",example = "123")
    private String isbn;
    @ApiModelProperty(notes = "图书类型",example = "1")
    private String typeId;
    @ApiModelProperty(notes = "图书名称",example = "")
    private String bookName;
    @ApiModelProperty(notes = "作者",example = "")
    private String author;
    @ApiModelProperty(notes = "出版社",example = "")
    private String publish;
    @ApiModelProperty(notes = "价格",example = "")
    private Double price;
    @ApiModelProperty(notes = "出版日期",example = "")
    private String publicationdate;
    @ApiModelProperty(notes = "图书状态1可借阅0不能借阅",example = "")
    private String bookStatus;
    @ApiModelProperty(notes = "印次",example = "")
    private String edition;
    @ApiModelProperty(notes = "图书描述",example = "")
    private String description;
    @ApiModelProperty(notes = "图书是否下架,1:正常0:下架表示删除图书",example = "1")
    private String undercarriage;
    @ApiModelProperty(value = "图书类型名称",example = "")
    private String typename;
    @ApiModelProperty(notes = "图书借阅状态",example = "")
    private String borrowStatus;
    @ApiModelProperty(notes = "图书归还时间",example = "")
    private String returnDate;
    @ApiModelProperty(notes = "图书借阅时间",example = "")
    private String borrowDate;
    @ApiModelProperty(notes = "主键id",example = "")
    private String bookUserId;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(String bookStatus) {
        this.bookStatus = bookStatus;
    }

    public String getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }

    public String getBorrowStatus() {
        return borrowStatus;
    }

    public void setBorrowStatus(String borrowStatus) {
        this.borrowStatus = borrowStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPublicationdate() {
        return publicationdate;
    }

    public void setPublicationdate(String publicationdate) {
        this.publicationdate = publicationdate;
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public String getUndercarriage() {
        return undercarriage;
    }

    public void setUndercarriage(String undercarriage) {
        this.undercarriage = undercarriage;
    }

    public String getBookUserId() {
        return bookUserId;
    }

    public void setBookUserId(String bookUserId) {
        this.bookUserId = bookUserId;
    }
}
