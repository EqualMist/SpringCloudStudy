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
//        RestTemplate restTemplate = new RestTemplate();
        User user = userClient.findUserById(uid);
        //获取每一本书的详细信息
        List<Book> bookList = borrowList
                .stream()
                .map(borrow -> bookClient.findBookById(borrow.getBid()))
                .collect(Collectors.toList());
        return new BorrowDetail(user, bookList);
    }


    @Override
    public boolean doBorrow(int uid, int bid) {
        //1. 判断图书和用户是否都支持借阅
        if(bookClient.bookRemain(bid) < 1)
            throw new RuntimeException("图书数量不足");
        if(userClient.userRemain(uid) < 1)
            throw new RuntimeException("用户借阅量不足");
        //2. 首先将图书的数量-1
        if(!bookClient.bookBorrow(bid))
            throw new RuntimeException("在借阅图书时出现错误！");
        //3. 添加借阅信息
        if(mapper.getBorrow(uid, bid) != null)
            throw new RuntimeException("此书籍已经被此用户借阅了！");
        if(mapper.addBorrow(uid, bid) <= 0)
            throw new RuntimeException("在录入借阅信息时出现错误！");
        //4. 用户可借阅-1
        if(!userClient.userBorrow(uid))
            throw new RuntimeException("在借阅时出现错误！");
        //完成
        return true;
    }

}
