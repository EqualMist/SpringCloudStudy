package com.zzy.service.impl;

import com.zzy.mapper.UserMapper;
import com.zzy.entity.User;
import com.zzy.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper mapper;

    @Override
    public User getUserById(int uid) {
        return mapper.getUserById(uid);
    }
}
