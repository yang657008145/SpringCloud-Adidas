package com.adidas.dao;

import com.adidas.entity.Notice;
import com.adidas.entity.NoticeType;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface NoticeDao {
    @Select("SELECT * FROM noticetype WHERE id=#{id}")
    NoticeType noticetypeid(@Param("id") int id);
    @Select("SELECT * FROM notice where notice.id=#{id}")
    @Results({
            @Result(id=true,column="id",property="id"),
            @Result(id=true,column="name",property="name"),
            @Result(id=true,column="content",property="content"),
            @Result(column="pid",property="noticetype",
                    one=@One(
                            select="com.adidas.dao.NoticeDao.noticetypeid",
                            fetchType= FetchType.EAGER
                    ))
    })
    Notice noticeId(@Param("id") int id);

    @Update("UPDATE notice SET content=#{context} WHERE id=#{id}")
    int noticeUd(@Param("id")int id,@Param("context") String context);
}
