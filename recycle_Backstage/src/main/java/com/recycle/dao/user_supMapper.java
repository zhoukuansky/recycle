package com.recycle.dao;

import com.recycle.model.user_sup;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface user_supMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(String tel, String password);

    int insertSelective(user_sup record);

    user_sup selectByPrimaryKey(Integer id);

    user_sup login(String tel, String password);

    int updateByPrimaryKeySelective(user_sup record);

    int updateByPrimaryKey(user_sup record);

    user_sup findByTel(String tel);

    List<user_sup> findAll();

    user_sup findUserInformation(Integer id);
}