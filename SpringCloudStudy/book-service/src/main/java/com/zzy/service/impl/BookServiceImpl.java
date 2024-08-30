package com.zzy.service.impl;

import com.zzy.entity.Book;
import com.zzy.mapper.BookMapper;
import com.zzy.service.BookService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BookServiceImpl implements BookService {

    @Resource
    BookMapper mapper;

    @Override
    public Book getBookById(int bid) {
        return mapper.getBookById(bid);
    }

    @Override
    public Integer getRemain(int bid) {
        return mapper.getRemain(bid);
    }

    @Override
    public boolean setRemain(int bid, int remain) {
        return mapper.setRemain(bid, remain) > 0;
    }
}
