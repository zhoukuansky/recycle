package com.recycle.server;

import com.github.pagehelper.PageHelper;
import com.recycle.dao.user_buy_workerMapper;
import com.recycle.exception.DescribeException;
import com.recycle.exception.ExceptionEnum;
import com.recycle.model.PageResultBean;
import com.recycle.model.user_buy;
import com.recycle.model.user_buy_worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RecycleWorkerService {
    @Autowired
    private user_buy_workerMapper mapper;

    @Autowired
    private UserService service;

    public Map insert(String tel, String name, int recycleId) throws Exception {
        Object recycle = service.findUserInformation(recycleId, 1);
        if (recycle == null) {
            throw new DescribeException(ExceptionEnum.USER_NOT_FOUND);
        }
        Map insertMap = new HashMap();
        insertMap.put("tel", tel);
        insertMap.put("name", name);
        insertMap.put("user_b_id", recycleId);
        mapper.insert(insertMap);
        return insertMap;
    }

    public Object findAllwordker(Integer pageNum, Integer pageSize) {
        //Sort sort = new Sort(Sort.Direction.ASC, "Id");
        PageHelper.startPage(pageNum, pageSize, "id ASC");
        return new PageResultBean<user_buy>(mapper.findAll());
    }

    public Object findWorkInformation(Integer id) {
        return mapper.findUserInformation(id);
    }

    public Object updateWorker(Integer id, user_buy_worker worker) {
        try {
            mapper.updateByPrimaryKeySelective(worker);
        } catch (Exception e) {
            throw new DescribeException(ExceptionEnum.UPDATE_ERROR);
        }
        return findWorkInformation(id);
    }

    public void deleteUser(int id) {
        mapper.deleteByPrimaryKey(id);
    }
}
