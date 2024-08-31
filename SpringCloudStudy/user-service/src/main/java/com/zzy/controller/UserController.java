package com.zzy.controller;

import com.zzy.entity.User;
import com.zzy.service.UserService;
import io.seata.core.context.RootContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RefreshScope
public class UserController {

    @Resource
    UserService service;

    @Value("${test.test-content}")
    String testContent;

    //这里以RESTFul风格为例
    @RequestMapping("/user/{uid}")
    public User findUserById(@PathVariable("uid") int uid){
        System.out.println(testContent);
        System.out.println("UserController");
        return service.getUserById(uid);
    }

    @RequestMapping("/user/remain/{uid}")
    public Integer userRemain(@PathVariable("uid") int uid){
        System.out.println("userRemain XID: " + RootContext.getXID());
        return service.getRemain(uid);
    }

    @RequestMapping("/user/borrow/{uid}")
    public boolean userBorrow(@PathVariable("uid") int uid){
        int remain = service.getRemain(uid);
        return service.setRemain(uid, remain - 1);
    }
}
