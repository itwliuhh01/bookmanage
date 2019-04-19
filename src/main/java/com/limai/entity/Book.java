package com.limai.entity;

import com.limai.utils.TimeUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.Date;

@ApiModel(value = "Book")
public class Book {
    @NotBlank(message = "ISBN不能为空")
    @ApiModelProperty(notes = "图书ISBN编号(主键)",example = "123")
    private String isbn;
    @ApiModelProperty(notes = "图书类型",example = "1")
    private String typeId;
    @NotBlank(message = "图书名称不能为空")
    @ApiModelProperty(notes = "图书名称",example = "")
    private String bookName;
    @NotBlank(message = "作者不能为空")
    @ApiModelProperty(notes = "作者",example = "")
    private String author;
    @NotBlank(message = "出版社不能为空")
    @ApiModelProperty(notes = "出版社",example = "")
    private String publish;
    @ApiModelProperty(notes = "价格",example = "")
    private Double price;
    @ApiModelProperty(notes = "出版日期",example = "")
    private String publicationdate;
    @ApiModelProperty(notes = "图书状态1可借阅0不能借阅",example = "")
    private String bookStatus;
    private String bookStatusName;
    @NotBlank(message = "印次不能为空")
    @ApiModelProperty(notes = "印次",example = "")
    private String edition;
    @ApiModelProperty(notes = "图书描述",example = "")
    private String description;
    @ApiModelProperty(notes = "图书是否下架,1:正常0:下架表示删除图书",example = "1")
    private String undercarriage;

    public String getBookStatusName() {
        return bookStatusName;
    }

    public void setBookStatusName(String bookStatusName) {
        this.bookStatusName = bookStatusName;
    }

    @ApiModelProperty(notes = "图书类型实体",example = "")
    private BookType bookType;

    public String getUndercarriage() {
        return undercarriage;
    }

    public void setUndercarriage(String undercarriage) {
        this.undercarriage = undercarriage;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn == null ? null : isbn.trim();
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish == null ? null : publish.trim();
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
        this.publicationdate = TimeUtils.formatDateStr(publicationdate);;
    }

    public String getBookStatus() { return bookStatus;}

    public void setBookStatus(String bookStatus) {
        this.bookStatus = bookStatus;
        if("1".equals(bookStatus)){
            this.bookStatusName = "可借";
        }else{
            this.bookStatusName = "已借";
        }
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BookType getBookType() {
        return bookType;
    }

    public void setBookType(BookType bookType) {
        this.bookType = bookType;
    }
}