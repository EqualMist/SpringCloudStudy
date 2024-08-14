package com.zzy.client;

import com.zzy.entity.Book;
import org.springframework.stereotype.Component;

@Component
public class BookFallbackClient implements BookClient {

    @Override
    public Book findBookById(int bid) {
        Book book = new Book();
        book.setTitle("备用图书");
        return book;
    }
}
