package com.java.cms.vo;

import lombok.Data;

@Data
public class BookQuery {

    private String title;

    //开始时间
    private String beginDate;

    //结束时间
    private String endDate;

}
