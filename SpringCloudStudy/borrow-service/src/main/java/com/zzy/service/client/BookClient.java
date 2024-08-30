package com.zzy.service.client;

import com.zzy.entity.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("book-service")
public interface BookClient {

    @RequestMapping("/book/{bid}")
    Book findBookById(@PathVariable("bid") int bid);

    @RequestMapping("/book/remain/{bid}")
    Integer bookRemain(@PathVariable("bid") int bid);

    @RequestMapping("/book/borrow/{bid}")
    boolean bookBorrow(@PathVariable("bid") int bid);

}