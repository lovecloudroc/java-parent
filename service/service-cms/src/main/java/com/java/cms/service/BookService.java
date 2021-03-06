package com.java.cms.service;

import com.java.cms.dao.BookDao;
import com.java.cms.entity.Book;
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
public class BookService extends BaseService<BookDao,Book> {

    @Autowired
    private BookDao bookDao;

    /**
     * 查询所有书籍
     * @return
     */
    public List<Book> findBook() {
        return bookDao.findAll();
    }

    /**
     * 带条件的分页查询
     * @param bookQuery 封装查询条件
     * @param pageNo 当前页
     * @param pageSize 最大页
     * @return 查询到的数据
     */
    public Page<Book> findPageBook(BookQuery bookQuery,int pageNo,int pageSize){
        SimpleSpecificationBuilder simpleSpecificationBuilder = new SimpleSpecificationBuilder();

        if (null != bookQuery){
            if (!StringUtils.isEmpty(bookQuery.getTitle())){
//                                                   : 的意思是like模糊查询
                simpleSpecificationBuilder.and("title",":",bookQuery.getTitle());
            }
            if (!StringUtils.isEmpty(bookQuery.getBeginDate())){
                simpleSpecificationBuilder.and("startTime","ge",bookQuery.getBeginDate());
            }
            if (!StringUtils.isEmpty(bookQuery.getEndDate())){
                simpleSpecificationBuilder.and("startTime","lt",bookQuery.getEndDate());
            }
            if (!StringUtils.isEmpty(bookQuery.getAuthor())) {
                simpleSpecificationBuilder.and("author",":",bookQuery.getAuthor());
            }
            if (bookQuery.getSerialize() != null && (bookQuery.getSerialize().equals(1) || bookQuery.getSerialize().equals(0))) {
                simpleSpecificationBuilder.and("serialize","=",bookQuery.getSerialize());
            }
            if (bookQuery.getStatus() != null && (bookQuery.getStatus().equals(1) || bookQuery.getStatus().equals(0))){
                simpleSpecificationBuilder.and("status","=",bookQuery.getStatus());
            }
            if (bookQuery.getOriginal() != null && (bookQuery.getOriginal().equals(1) || bookQuery.getOriginal().equals(0))){
                simpleSpecificationBuilder.and("original","=",bookQuery.getOriginal());
            }
        }
        Specification<Book> specification = simpleSpecificationBuilder.getSpecification();

//        查询
       Page<Book> page = dao.findAll(specification,PageRequest.of(pageNo - 1,pageSize));
        return page;
    }

}