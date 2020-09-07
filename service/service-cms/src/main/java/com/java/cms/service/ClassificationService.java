package com.java.cms.service;

import com.google.gson.Gson;
import com.java.cms.dao.ClassificationDao;
import com.java.cms.entity.Classification;
import com.java.cms.vo.BookQuery;
import com.java.commonutils.jpa.base.service.BaseService;
import com.java.commonutils.jpa.dynamic.SimpleSpecificationBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class ClassificationService extends BaseService<ClassificationDao,Classification> {

    @Autowired
    private ClassificationDao classificationDao;

    /**
     * 查询分类状态码
     * @return
     */
    public List<Classification> findAll(String pcode,String code){
        return classificationDao.findAll();
    }

    public List<Classification> findClassification(BookQuery bookQuery,String pcode,String code){
        SimpleSpecificationBuilder simpleSpecificationBuilder = new SimpleSpecificationBuilder();
        if (null != bookQuery){
            if (!StringUtils.isEmpty(bookQuery.getPcode())){
                simpleSpecificationBuilder.and("pcode","=",bookQuery.getPcode());
            }
        }
        Specification<Classification> specification = simpleSpecificationBuilder.getSpecification();
        List<Classification> classificationList = dao.findAll(specification);
        return classificationList;
    }
}
