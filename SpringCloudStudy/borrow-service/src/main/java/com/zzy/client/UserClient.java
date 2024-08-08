package com.zzy.client;

import com.zzy.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("user-service")   //声明为userservice服务的HTTP请求客户端
public interface UserClient {

    @RequestMapping("/user/{uid}")
    public User findUserById(@PathVariable("uid") int uid);
}