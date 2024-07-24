package com.zzy.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Borrow {
    int id;
    int uid;
    int bid;
}
