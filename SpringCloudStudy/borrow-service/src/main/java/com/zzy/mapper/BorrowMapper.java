package com.zzy.mapper;

import java.util.List;
import com.zzy.entity.Borrow;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface BorrowMapper {

    @Select("select * from DB_BORROW where uid = #{uid}")
    List<Borrow> getBorrowsByUid(int uid);

    @Select("select * from DB_BORROW where bid = #{bid}")
    List<Borrow> getBorrowsByBid(int bid);

    @Select("select * from DB_BORROW where bid = #{bid} and uid = #{uid}")
    Borrow getBorrow(@Param("uid") int uid, @Param("bid") int bid);

    @Insert("insert into DB_BORROW(uid, bid) values(#{uid}, #{bid})")
    Integer addBorrow(@Param("uid") int uid, @Param("bid") int bid);

}