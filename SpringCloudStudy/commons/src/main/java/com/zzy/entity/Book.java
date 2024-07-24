package com.zzy.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Book {
    int bid;
    String title;
    String description;
}