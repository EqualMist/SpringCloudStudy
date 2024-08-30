package com.zzy.mapper;

import com.zzy.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {
    @Select("select * from DB_USER where uid = #{uid}")
    User getUserById(int uid);

    @Select("select book_count from DB_USER where uid = #{uid}")
    Integer getUserBookRemain(int uid);

    @Update("update DB_USER set book_count = #{book_count} where uid = #{uid}")
    Integer updateBookCount(@Param("uid") int uid, @Param("book_count") int book_count);
}
