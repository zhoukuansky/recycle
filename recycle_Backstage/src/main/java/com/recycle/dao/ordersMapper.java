package com.recycle.dao;

import com.recycle.model.orders;
import org.springframework.stereotype.Repository;

@Repository
public interface ordersMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(orders record);

    int insertSelective(orders record);

    orders selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(orders record);

    int updateByPrimaryKey(orders record);
}