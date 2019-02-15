package com.recycle.server;

import com.github.pagehelper.PageHelper;
import com.recycle.dao.rubbishMapper;
import com.recycle.dao.user_buy_workerMapper;
import com.recycle.exception.DescribeException;
import com.recycle.exception.ExceptionEnum;
import com.recycle.model.PageResultBean;
import com.recycle.model.rubbish;
import com.recycle.model.user_buy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RubbishService {
    @Autowired
    private rubbishMapper mapper;

    public Map insert(String type, String price) {
        Map insertMap = new HashMap();
        insertMap.put("type", type);
        insertMap.put("price", price);
        mapper.insert(insertMap);
        return insertMap;
    }

    public Object findAllRubbish(Integer pageNum, Integer pageSize) {
        Sort sort = new Sort(Sort.Direction.ASC, "Id");
        PageHelper.startPage(pageNum, pageSize, "id ASC");
        return new PageResultBean<user_buy>(mapper.findAll());
    }

    public Object findRubbishInformation(Integer id) {
        return mapper.findUserInformation(id);
    }

    public Object updateRubbish(Integer id, rubbish rubbish) {
        try {
            mapper.updateByPrimaryKeySelective(rubbish);
        } catch (Exception e) {
            throw new DescribeException(ExceptionEnum.UPDATE_ERROR);
        }
        return findRubbishInformation(id);
    }

    public void deleteRubbish(int id) {
        mapper.deleteByPrimaryKey(id);
    }
}
