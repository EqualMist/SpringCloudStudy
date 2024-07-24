package com.zzy.controller;

import com.zzy.entity.BorrowDetail;
import com.zzy.service.BorrowService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class BorrowController {

    @Resource
    BorrowService service;

    @RequestMapping("/borrow/{uid}")
    BorrowDetail findUserBorrows(@PathVariable("uid") int uid){
        BorrowDetail borrowDetail =service.getUserBorrowDetailByUid(uid);
        return borrowDetail;
    }

}
