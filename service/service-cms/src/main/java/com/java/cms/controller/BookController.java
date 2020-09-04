package com.java.cms.controller;

import com.java.cms.entity.Book;
import com.java.cms.service.BookService;
import com.java.cms.vo.BookQuery;
import com.java.commonutils.api.APICODE;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "书籍管理")
@RestController
@CrossOrigin//解决跨域
@RequestMapping("/cms/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @ApiOperation(value = "查询所有书籍")
    @GetMapping
    public APICODE findBook() {
//        查询
        //int o = 1/0;//设置异常
        List<Book> list = bookService.findBook();
        return APICODE.OK().data("items",list);
    }

    @ApiOperation(value = "根据条件进行分页查询")
    @PostMapping("{pageNo}/{pageSize}")
//                             @RequestBody(required = false) 查询条件可以为空
//                             @PathVariable(value = "pageSize") 为保万无一失
    public APICODE findPageBook(@RequestBody(required = false) BookQuery bookQuery,
                                @PathVariable(value = "pageNo") int pageNo,
                                @PathVariable(value = "pageSize") int pageSize){

//        调用service的方法，进行分页查询
       Page<Book> page = bookService.findPageBook(bookQuery,pageNo,pageSize);
       long totalElements = page.getTotalElements();//获取总数
        List<Book> list = page.getContent();//当前页查询的数据集合
        return APICODE.OK().data("total",totalElements).data("items",list);
    }

    @PostMapping("saveBook")
    @ApiOperation(value = "书籍添加")
    public APICODE saveBook(@RequestBody Book book){
        bookService.saveOrUpdate(book);
        return APICODE.OK();
    }

    @ApiOperation(value = "根据Id查询书籍")
    @GetMapping("getBookById/{bookId}")
    public APICODE updateBook( @PathVariable String bookId){
        Book book = bookService.getById(bookId);
        return APICODE.OK().data("book",book);
    }

    @ApiOperation(value = "修改书籍")
    @PostMapping("updateBook")
    public APICODE updateBook(@RequestBody Book book){
        bookService.saveOrUpdate(book);
        return APICODE.OK();
    }

    @ApiOperation(value = "根据ID对书籍进行删除")
    @DeleteMapping("deleteById/{bookId}")
    public APICODE deleteBook(@PathVariable String bookId){
        bookService.removeById(bookId);
        return APICODE.OK();
    }

    @ApiOperation(value = "设置书籍上下架")
    @PutMapping("{bookId}/{state}")
    public APICODE upOrDownBook(@PathVariable(name = "bookId") String bookId, @PathVariable(name = "state") Integer state) {
        // ## 根据id查询书籍数据
        Book book = bookService.getById(bookId);
//        book.setId(bookId);
        book.setState(state);
        // ## 修改数据
        bookService.saveOrUpdate(book);
        return APICODE.OK();
    }


}