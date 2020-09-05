package com.java.cms.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "cms_book")
@EntityListeners(AuditingEntityListener.class)
public class Book {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "generator_uuid")
    @GenericGenerator(name = "generator_uuid",strategy = "uuid")
    @ApiModelProperty(value = "书籍主键,采用hibernate的uuid生成32位字符串")
    private String id;
//
    @Column(name = "title")
    @ApiModelProperty(value = "书名")
    private String title;

    @Column(name = "author")
    @ApiModelProperty(value = "作者")
    private String author;

    @Column(name = "first_sort")
    @ApiModelProperty(value = "一级分类")
    private String firstSort;

    @Column(name = "secondary")
    @ApiModelProperty(value = "二级分类")
    private String secondary;

    @Column(name = "is_serial")
    @ApiModelProperty(value = "连载")
    private int serial;

    @Column(name = "work_number")
    @ApiModelProperty(value = "字数")
    private int workNumber;
//
    @Column(name = "is_state")
    @ApiModelProperty(value = "是否上线")
    private int state;

    @Column(name = "is_full_cost")
    @ApiModelProperty(value = "是否收费")
    private int fullCost;


    @Column(name = "gmt_create",nullable = false,updatable = false)
    @CreatedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "授权开始时间",example = "2020-02-02 9:00:00")
    private Date gmtCreate;

    @Column(name = "gmt_modified",nullable = false,insertable = false)
    @LastModifiedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "授权结束时间", example = "2020-12-12 9:00:00")
    private Date gmtModified;

    @Column(name = "is_original")
    @ApiModelProperty(value = "是否原创")
    private int original;

    @Column(name = "is_grant_state")
    @ApiModelProperty(value = "授权状态")
    private int grantState;
}
