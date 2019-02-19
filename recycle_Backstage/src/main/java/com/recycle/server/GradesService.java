package com.recycle.server;

import com.recycle.dao.gradesMapper;
import com.recycle.dao.ordersMapper;
import com.recycle.exception.DescribeException;
import com.recycle.exception.ExceptionEnum;
import com.recycle.model.grades;
import com.recycle.model.orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GradesService {
    @Autowired
    private ordersMapper orMapper;

    @Autowired
    private gradesMapper mapper;

    public grades insert(int order_id, int server, String describe) throws Exception {
        orders res = orMapper.selectByPrimaryKey(order_id);
        if (res.getStatus() != 3) {
            throw new DescribeException(ExceptionEnum.DEAL_ERROR);
        }
        grades gre = new grades();
        gre.setGrades_server(server);
        gre.setGrades_describ(describe);
        gre.setOrd_order_id(order_id);
        mapper.insertSelective(gre);

        orders order = new orders();
        order.setId(order_id);
        order.setStatus(4);
        orMapper.updateByPrimaryKeySelective(order);
        return gre;
    }


    public void deleteGrade(int id) {
        mapper.deleteByPrimaryKey(id);
    }

    public Object gradesInformation(Integer order_id) {
        return mapper.selectByOrder(order_id);
    }
}
