package com.java.cms.entity;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Data
@Entity
@Table(name = "classification")
public class Classification {

    @Id
    @Column(name = "id")
    @ApiModelProperty(value = "一级分类所用到的Id主键")
    private int id;

    @Column(name = "p_code")
    @ApiModelProperty(value = "上一级编码")
    private String pcode;

    @Column(name = "code")
    @ApiModelProperty(value = "编码")
    private String code;

    @Column(name = "name")
    @ApiModelProperty(value = "名称")
    private String name;

    @Column(name = "level")
    @ApiModelProperty(value = "级别")
    private int level;

}
