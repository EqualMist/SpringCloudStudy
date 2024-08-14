package com.zzy.client;

import com.zzy.entity.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "book-service", fallback = BookFallbackClient.class)
public interface BookClient {

    @RequestMapping("/book/{bid}")
    Book findBookById(@PathVariable("bid") int bid);

}
