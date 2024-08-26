package com.zzy.service.impl;

import com.zzy.entity.Book;
import com.zzy.entity.Borrow;
import com.zzy.entity.BorrowDetail;
import com.zzy.entity.User;
import com.zzy.mapper.BorrowMapper;
import com.zzy.service.BorrowService;
import com.zzy.service.client.BookClient;
import com.zzy.service.client.UserClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BorrowServiceImpl implements BorrowService {

    @Resource
    BorrowMapper mapper;

    @Resource
    UserClient userClient;
    @Resource
    BookClient bookClient;

    @Override
    public BorrowDetail getUserBorrowDetailByUid(int uid) {
        List<Borrow> borrowList = mapper.getBorrowsByUid(uid);
        //那么问题来了，现在拿到借阅关联信息了，怎么调用其他服务获取信息呢？
        //RestTemplate支持多种方式的远程调用
        RestTemplate restTemplate = new RestTemplate();
        User user = userClient.findUserById(uid);
        //获取每一本书的详细信息
        List<Book> bookList = borrowList
                .stream()
                .map(borrow -> bookClient.findBookById(borrow.getBid()))
                .collect(Collectors.toList());
        return new BorrowDetail(user, bookList);
    }

}
