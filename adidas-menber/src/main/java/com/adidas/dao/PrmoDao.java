package com.adidas.dao;

import com.adidas.entity.Prmo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface PrmoDao {
	@Select("SELECT id,`name` FROM yrz_prmo where id=#{id}")
	Prmo findUserById(@Param("id") int id);
	@Insert("INSERT INTO yrz_prmo VALUES (#{id},#{name})")
	int inserPrmo(@Param("id")int id,@Param("name")String name);
}
