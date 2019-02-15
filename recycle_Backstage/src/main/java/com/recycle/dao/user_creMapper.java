package com.recycle.dao;

import com.recycle.model.user_cre;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface user_creMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(String tel, String password);

    int insertSelective(user_cre record);

    user_cre selectByPrimaryKey(Integer id);

    user_cre login(String tel, String password);

    int updateByPrimaryKeySelective(user_cre record);

    int updateByPrimaryKey(user_cre record);

    user_cre findByTel(String tel);

    List<user_cre> findAll();

    user_cre findUserInformation(Integer id);
}