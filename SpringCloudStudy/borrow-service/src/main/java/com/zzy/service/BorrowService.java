package com.zzy.service;

import com.zzy.entity.BorrowDetail;

public interface BorrowService {

    BorrowDetail getUserBorrowDetailByUid(int uid);

    boolean doBorrow(int uid, int bid);
}
