package com.recycle.dao;

import com.recycle.model.user_buy;
import com.recycle.model.user_buy_worker;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface user_buy_workerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Map record);

    int insertSelective(user_buy_worker record);

    user_buy_worker selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(user_buy_worker record);

    int updateByPrimaryKey(user_buy_worker record);

    List<user_buy> findAll();

    Object findUserInformation(Integer id);

    int deleteByRecycleID(Integer RecycleID);
}