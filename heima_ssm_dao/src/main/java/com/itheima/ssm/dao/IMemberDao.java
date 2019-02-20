package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Member;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;


public interface IMemberDao {

    @Select("select * from member where id = #{id}")
    public Member findById(String id);
}
