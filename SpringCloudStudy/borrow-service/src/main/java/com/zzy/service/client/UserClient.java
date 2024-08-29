package com.zzy.service.client;

import com.zzy.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "user-service", fallback = UserClientFallback.class)
public interface UserClient {

    @RequestMapping("/user/{uid}")
    User findUserById(@PathVariable("uid") int uid);
}