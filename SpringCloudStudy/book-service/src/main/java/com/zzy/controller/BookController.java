package com.zzy.controller;

import com.zzy.entity.Book;
import com.zzy.service.BookService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class BookController {

    @Resource
    BookService service;

    @RequestMapping("/book/{bid}")
    Book findBookById(@PathVariable("bid") int bid){
        System.out.println("BookServiceController");
        return service.getBookById(bid);
    }

    @RequestMapping("/book/remain/{bid}")
    public Integer bookRemain(@PathVariable("bid") int bid){
        return service.getRemain(bid);
    }

    @RequestMapping("/book/borrow/{bid}")
    public boolean bookBorrow(@PathVariable("bid") int bid){
        int remain = service.getRemain(bid);
        return service.setRemain(bid, remain - 1);
    }
}
