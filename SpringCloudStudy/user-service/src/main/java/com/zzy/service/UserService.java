package com.zzy.service;

import com.zzy.entity.User;

public interface UserService {
    User getUserById(int uid);

    Integer getRemain(int uid);

    boolean setRemain(int uid, int count);
}
