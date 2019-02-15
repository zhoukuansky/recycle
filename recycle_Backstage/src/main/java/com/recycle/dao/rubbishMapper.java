package com.recycle.dao;

import com.recycle.model.rubbish;
import com.recycle.model.user_buy;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface rubbishMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Map record);

    int insertSelective(rubbish record);

    rubbish selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(rubbish record);

    int updateByPrimaryKey(rubbish record);

    List<user_buy> findAll();

    Object findUserInformation(Integer id);
}