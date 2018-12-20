package com.adidas.dao;

import com.adidas.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface UserEntityDao  {
    @Select("SELECT * FROM adidas_user WHERE username=#{username}")
     UserEntity ue(@Param("username") String username);
     @Select("SELECT * FROM adidas_user WHERE id=#{id}")
     UserEntity ueId(@Param("id")int id);
}
