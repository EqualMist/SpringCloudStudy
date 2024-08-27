package com.zzy.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
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
import java.util.Collections;
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

    @SentinelResource(value = "borrowServiceImpl", blockHandler = "blocked")
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

    // 替代方案，注意参数和返回值需要保持一致，并且参数最后还需要额外添加一个BlockException
    public BorrowDetail blocked(int uid, BlockException e) {
        return new BorrowDetail(null, Collections.emptyList());
    }

}
