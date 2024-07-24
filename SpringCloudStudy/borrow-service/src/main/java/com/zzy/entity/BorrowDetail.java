package com.zzy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@AllArgsConstructor
public class BorrowDetail {
    User user;
    List<Book> bookList;

}
