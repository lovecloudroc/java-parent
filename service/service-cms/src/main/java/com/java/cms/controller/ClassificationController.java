package com.java.cms.controller;


import com.java.cms.entity.Classification;
import com.java.cms.service.ClassificationService;
import com.java.cms.vo.BookQuery;
import com.java.commonutils.api.APICODE;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "一二级分类")
@RestController
@CrossOrigin//解决跨域
@RequestMapping("/cms/classification")
public class ClassificationController {

    @Autowired
    private ClassificationService classificationService;

    @GetMapping("{pcode}")
    public APICODE findAll(@RequestBody(required = false) BookQuery bookQuery,
                           @PathVariable(value = "pcode") String pcode,
                           @PathVariable(value = "code") String code){
        List<Classification> classificationList = classificationService.findClassification(bookQuery,pcode,code);
        return APICODE.OK().data("total",classificationList);
    }

}
