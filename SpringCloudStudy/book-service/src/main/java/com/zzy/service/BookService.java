package com.zzy.service;

import com.zzy.entity.Book;

public interface BookService {

    Book getBookById(int bid);

    Integer getRemain(int bid);

    boolean setRemain(int bid, int remain);
}
