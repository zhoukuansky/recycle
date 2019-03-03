package com.recycle.server;

import com.github.pagehelper.PageHelper;
import com.recycle.dao.user_buyMapper;
import com.recycle.dao.user_buy_workerMapper;
import com.recycle.dao.user_creMapper;
import com.recycle.dao.user_supMapper;
import com.recycle.exception.DescribeException;
import com.recycle.exception.ExceptionEnum;
import com.recycle.jjwtToken.JwtToken;
import com.recycle.model.PageResultBean;
import com.recycle.model.user_buy;
import com.recycle.model.user_cre;
import com.recycle.model.user_sup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    private user_buyMapper userBuyMapper;
    @Autowired
    private user_creMapper userCreMapper;
    @Autowired
    private user_supMapper useSuprMapper;
    @Autowired
    private user_buy_workerMapper workerMapper;

    //注册用户
    public Map insertUser(String tel, String password, int type) throws Exception {
        Map insertMap = new HashMap();
        //password=DigestUtils.md5DigestAsHex(password.getBytes());
        user_buy table1 = userBuyMapper.findByTel(tel);
        user_cre table2 = userCreMapper.findByTel(tel);
        user_sup table3 = useSuprMapper.findByTel(tel);
        if (table1 != null || table2 != null || table3 != null) {
            throw new DescribeException(ExceptionEnum.USER_EXIST);
        }
        insertMap.put("tel", tel);
        if (1 == type) {
            userBuyMapper.insert(tel, password);
            insertMap.put("type", "回收站");
        }
        if (2 == type) {
            userCreMapper.insert(tel, password);
            insertMap.put("type", "用户");
        }
        if (3 == type) {
            useSuprMapper.insert(tel, password);
            insertMap.put("type", "超级管理员");
        }
        return insertMap;
    }

    //删除用户
    public void deleteUser(int id, int type) {
        if (1 == type) {
            userBuyMapper.deleteByPrimaryKey(id);
            workerMapper.deleteByRecycleID(id);
        }
        if (2 == type) {
            userCreMapper.deleteByPrimaryKey(id);
        }
        if (3 == type) {
            useSuprMapper.deleteByPrimaryKey(id);
        }
    }

    public Map login(String tel, String password, int type) throws Exception {
        Map loginMap = new HashMap();
        //Object loginUser=null;
        //password=DigestUtils.md5DigestAsHex(password.getBytes());
        if (1 == type) {
            user_buy loginUser = userBuyMapper.login(tel, password);
            if (loginUser == null) {
                throw new DescribeException(ExceptionEnum.LOGIN_ERROR);
            }
            loginMap.put("id", loginUser.getId());
            loginMap.put("type", 1);
            loginMap.put("token", JwtToken.createToken(loginMap));
            loginMap.put("tel", loginUser.getTel());
            loginMap.put("roles", "回收站");

//            System.out.println(a);
//            for (Object v : loginMap.values()) {
//                System.out.println("value= " + v);
//            }
            return loginMap;
        }
        if (2 == type) {
            user_cre loginUser = userCreMapper.login(tel, password);
            if (loginUser == null) {
                throw new DescribeException(ExceptionEnum.LOGIN_ERROR);
            }
            loginMap.put("id", loginUser.getId());
            loginMap.put("type", 2);
            loginMap.put("token", JwtToken.createToken(loginMap));
            loginMap.put("tel", loginUser.getTel());
            loginMap.put("roles", "用户");
            return loginMap;
        }
        if (3 == type) {
            user_sup loginUser = useSuprMapper.login(tel, password);
            if (loginUser == null) {
                throw new DescribeException(ExceptionEnum.LOGIN_ERROR);
            }
            loginMap.put("id", loginUser.getId());
            loginMap.put("type", 3);
            loginMap.put("token", JwtToken.createToken(loginMap));
            loginMap.put("tel", loginUser.getTel());
            loginMap.put("roles", "超级管理员");
            return loginMap;
        }
        return loginMap;
    }

    public Object findAllUser(Integer pageNum, Integer pageSize, int type) throws Exception {
        if (1 == type) {
            Sort sort = new Sort(Sort.Direction.ASC, "Id");
            PageHelper.startPage(pageNum, pageSize, "id ASC");
            return new PageResultBean<user_buy>(userBuyMapper.findAll());
        }
        if (2 == type) {
            Sort sort = new Sort(Sort.Direction.ASC, "Id");
            PageHelper.startPage(pageNum, pageSize, "id ASC");
            return new PageResultBean<user_cre>(userCreMapper.findAll());
        }
        if (3 == type) {
            Sort sort = new Sort(Sort.Direction.ASC, "Id");
            PageHelper.startPage(pageNum, pageSize, "id ASC");
            return new PageResultBean<user_sup>(useSuprMapper.findAll());
        }
        throw new DescribeException(ExceptionEnum.ILLEGAL_ARGUMENT);
    }

    public Object findUserInformation(Integer id, int type) throws Exception {
        if (1 == type) {
            return userBuyMapper.findUserInformation(id);
        }
        if (2 == type) {
            return userCreMapper.findUserInformation(id);
        }
        if (3 == type) {
            return useSuprMapper.findUserInformation(id);
        }
        throw new DescribeException(ExceptionEnum.USER_NOT_FOUND);
    }

    //更新回收站信息
    public Object updateRecycleUser(Integer id, user_buy user) throws Exception {
        try {
            user.setId(id);
            userBuyMapper.updateByPrimaryKeySelective(user);
        } catch (Exception e) {
            throw new DescribeException(ExceptionEnum.UPDATE_ERROR);
        }
        return findUserInformation(id, 1);
    }

    //更新普通用户信息
    public Object updateUser(Integer id, user_cre user) throws Exception {
        try {
            user.setId(id);
            userCreMapper.updateByPrimaryKeySelective(user);
        } catch (Exception e) {
            throw new DescribeException(ExceptionEnum.UPDATE_ERROR);
        }
        return findUserInformation(id, 2);
    }
}
