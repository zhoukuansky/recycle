package com.recycle.dao;

import com.recycle.model.user_buy;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface user_buyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(String tel, String password);

    int insertSelective(user_buy record);

    user_buy selectByPrimaryKey(Integer id);

    user_buy login(String tel, String password);

    int updateByPrimaryKeySelective(user_buy record);

    int updateByPrimaryKey(user_buy record);

    user_buy findByTel(String tel);

    List<user_buy> findAll();

    user_buy findUserInformation(Integer id);
}