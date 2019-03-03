package com.recycle.server;

import com.github.pagehelper.PageHelper;
import com.recycle.dao.ordersMapper;
import com.recycle.exception.DescribeException;
import com.recycle.exception.ExceptionEnum;
import com.recycle.model.PageResultBean;
import com.recycle.model.orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class OrdersService {
    @Autowired
    private ordersMapper mapper;

    public Map insert(int user_c_id, int rub_id, String order_rub_infor, int order_moneny) {
        Map insertMap = new HashMap();
        insertMap.put("user_c_id", user_c_id);
        insertMap.put("rub_id", rub_id);
        insertMap.put("order_rub_infor", order_rub_infor);
        insertMap.put("order_moneny", order_moneny);
        insertMap.put("status", 1);
        mapper.insertSelective(insertMap);
        return insertMap;
    }

    public Object findNewOrders(Integer pageNum, Integer pageSize) {
        Sort sort = new Sort(Sort.Direction.ASC, "Id");
        PageHelper.startPage(pageNum, pageSize, "id ASC");
        return new PageResultBean<orders>(mapper.findNewOrders());
    }

    public Object findUserAllOrders(int user_c_id, Integer pageNum, Integer pageSize) {
        Sort sort = new Sort(Sort.Direction.ASC, "Id");
        PageHelper.startPage(pageNum, pageSize, "id ASC");
        return new PageResultBean<orders>(mapper.findUserAll(user_c_id));
    }

    public Object dealOrders(int id, int user_b_id) throws Exception {
        orders res = mapper.selectByPrimaryKey(id);
        if (res.getStatus() != 1) {
            throw new DescribeException(ExceptionEnum.DEAL_ERROR);
        }
        orders order = new orders();
        order.setId(id);
        order.setUser_b_id(user_b_id);
        order.setStatus(2);
        order.setOrder_time_deal(new Date());
        mapper.updateByPrimaryKeySelective(order);
        return mapper.selectByPrimaryKey(id);
    }

    public Object findRecycleAllOrders(int user_b_id, Integer pageNum, Integer pageSize) {
        Sort sort = new Sort(Sort.Direction.ASC, "Id");
        PageHelper.startPage(pageNum, pageSize, "id ASC");
        return new PageResultBean<orders>(mapper.findRecycleAll(user_b_id));
    }

    public Object findAllOrders(Integer pageNum, Integer pageSize) {
        Sort sort = new Sort(Sort.Direction.ASC, "Id");
        PageHelper.startPage(pageNum, pageSize, "id ASC");
        return new PageResultBean<orders>(mapper.findAllOrders());
    }

    public Object finishOrders(int id) throws Exception {
        orders res = mapper.selectByPrimaryKey(id);
        if (res.getStatus() != 2) {
            throw new DescribeException(ExceptionEnum.DEAL_ERROR);
        }
        orders order = new orders();
        order.setId(id);
        order.setStatus(3);
        order.setOrder_time_finish(new Date());
        mapper.updateByPrimaryKeySelective(order);
        return mapper.selectByPrimaryKey(id);
    }

    public void deleteOrders(int type, int id) {
        orders res = mapper.selectByPrimaryKey(id);
        if (res.getStatus() == 1) {
            mapper.deleteByPrimaryKey(id);
            return;
        } else {
            if (type == 2) {
                throw new DescribeException(ExceptionEnum.NEED_ROLES);
            }
            mapper.deleteByPrimaryKey(id);
            return;
        }
    }
}
