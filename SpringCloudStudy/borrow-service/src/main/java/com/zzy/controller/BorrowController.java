package com.zzy.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.zzy.entity.BorrowDetail;
import com.zzy.service.BorrowService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collections;

@RestController
public class BorrowController {

    @Resource
    BorrowService service;

//    @HystrixCommand(fallbackMethod = "onError")
    @RequestMapping("/borrow/{uid}")
    BorrowDetail findUserBorrows(@PathVariable("uid") int uid){
        BorrowDetail borrowDetail =service.getUserBorrowDetailByUid(uid);
        return borrowDetail;
    }

//    //备选方案，这里直接返回空列表了
//    //注意参数和返回值要和上面的一致
//    BorrowDetail onError(int uid){
//        return new BorrowDetail(null, Collections.emptyList());
//    }

}
