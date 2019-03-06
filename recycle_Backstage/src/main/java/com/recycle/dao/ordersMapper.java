package com.recycle.dao;

import com.recycle.model.orders;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ordersMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(orders record);

    int insertSelective(Map record);

    orders selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(orders record);

    int updateByPrimaryKey(orders record);

    List<orders> findNewOrders();

    List<orders> findUserAll(int user_c_id,int status);

    List<orders> findRecycleAll(int user_b_id,int status);

    List<orders> findAllOrders(int status);
}