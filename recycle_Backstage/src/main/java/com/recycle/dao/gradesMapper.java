package com.recycle.dao;

import com.recycle.model.grades;
import org.springframework.stereotype.Repository;

@Repository
public interface gradesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(grades record);

    int insertSelective(grades record);

    grades selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(grades record);

    int updateByPrimaryKey(grades record);
}