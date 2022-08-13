package com.recycle.controller;

import com.recycle.aop.SystemControllerLog;
import com.recycle.exception.DescribeException;
import com.recycle.exception.ExceptionEnum;
import com.recycle.exception.ExceptionHandle;
import com.recycle.exception.ResultUtil;
import com.recycle.jjwtToken.CurrentUser;
import com.recycle.model.Result;
import com.recycle.server.OrdersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Api(tags = {"订单操作"})
@RestController
@RequestMapping("/odrers")
public class OrdersController {
    @Autowired
    private OrdersService service;

    @Autowired
    private ExceptionHandle handle;

    @PostMapping("/insertOrders")
    @SystemControllerLog(logAction = "insertOrders", logContent = "新建订单")
    @ApiOperation(value = "新建订单", notes = "新建订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "rub_id", value = "垃圾类型id", required = true, dataType = "int"),
            @ApiImplicitParam(name = "order_rub_infor", value = "垃圾信息描述", required = false, dataType = "String"),
            @ApiImplicitParam(name = "moneny", value = "总价格", required = true, dataType = "int"),
    })
    public Result insertOrders(@RequestParam("rub_id") int rub_id, @CurrentUser String[] tokenData, @RequestParam(value = "order_rub_infor", defaultValue = "") String order_rub_infor, @RequestParam("moneny") int order_moneny) {
        Result result = ResultUtil.success();
        int user_c_id = Integer.parseInt(tokenData[1]);
        //int type=Integer.parseInt(tokenData[0]);
        try {
            Map insertMap = service.insert(user_c_id, rub_id, order_rub_infor, order_moneny);
            result = ResultUtil.success(insertMap);
        } catch (Exception e) {
            result = handle.exceptionGet(e);
            //result = ResultUtil.error(ExceptionEnum.USER_EXIST);
        }
        return result;
    }

    @GetMapping("/newOrdersList")
    @SystemControllerLog(logAction = "newOrdersList", logContent = "回收站或超级管理员可查看未接订单列表")
    @ApiOperation(value = "回收站或超级管理员可查看未接订单列表", notes = "回收站或超级管理员可查看未接订单列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页数", required = false, dataType = "String"),
            @ApiImplicitParam(name = "pageSize", value = "页面大小", required = false, dataType = "String"),
    })
    public Result newOrdersList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize, @CurrentUser String[] tokenData) throws Exception {
        Result result = ResultUtil.success();
        //int user_b_id = Integer.parseInt(tokenData[1]);
        int type = Integer.parseInt(tokenData[0]);
        if (type == 2) {
            throw new DescribeException(ExceptionEnum.NEED_ROLES);
        }
        try {
            result = ResultUtil.success(service.findNewOrders(pageNum, pageSize));
        } catch (Exception e) {
            result = handle.exceptionGet(e);
            //result = ResultUtil.error(ExceptionEnum.USER_EXIST);
        }
        return result;
    }

    @GetMapping("/userFindOrdersList")
    @SystemControllerLog(logAction = "userFindOrdersList", logContent = "用户自身可查看订单列表")
    @ApiOperation(value = "用户自身可查看订单列表", notes = "用户自身可查看订单列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页数", required = false, dataType = "String"),
            @ApiImplicitParam(name = "pageSize", value = "页面大小", required = false, dataType = "String"),
            @ApiImplicitParam(name = "status", value = "需要查询的状态", required = true, dataType = "int"),
    })
    public Result userFindOrdersList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize, @RequestParam("status") int status, @CurrentUser String[] tokenData) throws Exception {
        Result result = ResultUtil.success();
        int type = Integer.parseInt(tokenData[0]);
        if (type != 2) {
            throw new DescribeException(ExceptionEnum.NEED_ROLES);
        }
        int user_c_id = Integer.parseInt(tokenData[1]);
        try {
            result = ResultUtil.success(service.findUserAllOrders(user_c_id, pageNum, pageSize, status));
        } catch (Exception e) {
            result = handle.exceptionGet(e);
            //result = ResultUtil.error(ExceptionEnum.USER_EXIST);
        }
        return result;
    }

    @PutMapping("/dealOrders")
    @SystemControllerLog(logAction = "dealOrders", logContent = "回收站处理订单")
    @ApiOperation(value = "回收站处理订单", notes = "回收站处理订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "订单id", required = true, dataType = "int"),
    })
    public Result dealOrders(@CurrentUser String[] tokenData, @RequestParam("id") int id) throws Exception {
        Result result = ResultUtil.success();
        int user_b_id = Integer.parseInt(tokenData[1]);
        int type = Integer.parseInt(tokenData[0]);
        if (type != 1) {
            throw new DescribeException(ExceptionEnum.NEED_ROLES);
        }
        try {
            result = ResultUtil.success(service.dealOrders(id, user_b_id));
        } catch (Exception e) {
            result = handle.exceptionGet(e);
            //result = ResultUtil.error(ExceptionEnum.USER_EXIST);
        }
        return result;
    }

    @GetMapping("/recycleFindOrdersList")
    @SystemControllerLog(logAction = "recycleFindOrdersList", logContent = "回收站查看已接订单列表")
    @ApiOperation(value = "回收站查看已接订单列表", notes = "回收站查看已接订单列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页数", required = false, dataType = "String"),
            @ApiImplicitParam(name = "pageSize", value = "页面大小", required = false, dataType = "String"),
            @ApiImplicitParam(name = "status", value = "需要查询的状态", required = true, dataType = "int"),
    })
    public Result recycleFindOrdersList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize, @RequestParam("status") int status, @CurrentUser String[] tokenData) throws Exception {
        Result result = ResultUtil.success();
        int type = Integer.parseInt(tokenData[0]);
        if (type != 1) {
            throw new DescribeException(ExceptionEnum.NEED_ROLES);
        }
        int user_b_id = Integer.parseInt(tokenData[1]);
        try {
            result = ResultUtil.success(service.findRecycleAllOrders(user_b_id, pageNum, pageSize, status));
        } catch (Exception e) {
            result = handle.exceptionGet(e);
            //result = ResultUtil.error(ExceptionEnum.USER_EXIST);
        }
        return result;
    }

    @GetMapping("/OrdersList")
    @SystemControllerLog(logAction = "OrdersList", logContent = "超级管理员可查看所有订单列表")
    @ApiOperation(value = "超级管理员可查看所有订单列表", notes = "超级管理员可查看所有订单列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页数", required = false, dataType = "String"),
            @ApiImplicitParam(name = "pageSize", value = "页面大小", required = false, dataType = "String"),
            @ApiImplicitParam(name = "status", value = "需要查询的状态", required = true, dataType = "int"),
    })
    public Result OrdersList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize, @RequestParam("status") int status, @CurrentUser String[] tokenData) throws Exception {
        Result result = ResultUtil.success();
        //int user_b_id = Integer.parseInt(tokenData[1]);
        int type = Integer.parseInt(tokenData[0]);
        if (type != 3) {
            throw new DescribeException(ExceptionEnum.NEED_ROLES);
        }
        try {
            result = ResultUtil.success(service.findAllOrders(pageNum, pageSize, status));
        } catch (Exception e) {
            result = handle.exceptionGet(e);
            //result = ResultUtil.error(ExceptionEnum.USER_EXIST);
        }
        return result;
    }

    @PutMapping("/finishOrders")
    @SystemControllerLog(logAction = "finishOrders", logContent = "回收站完成订单")
    @ApiOperation(value = "回收站完成订单", notes = "回收站完成订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "订单id", required = true, dataType = "int"),
    })
    public Result finishOrders(@CurrentUser String[] tokenData, @RequestParam("id") int id) throws Exception {
        Result result = ResultUtil.success();
        int user_b_id = Integer.parseInt(tokenData[1]);
        int type = Integer.parseInt(tokenData[0]);
        if (type != 1) {
            throw new DescribeException(ExceptionEnum.NEED_ROLES);
        }
        try {
            result = ResultUtil.success(service.finishOrders(id));
        } catch (Exception e) {
            result = handle.exceptionGet(e);
            //result = ResultUtil.error(ExceptionEnum.USER_EXIST);
        }
        return result;
    }

    @DeleteMapping("/deleteOrders")
    @SystemControllerLog(logAction = "deleteOrders", logContent = "超级管理员和回收站可删除订单，用户只能删除状态为1的订单")
    @ApiOperation(value = "超级管理员和回收站可删除订单，用户只能删除状态为1的订单", notes = "超级管理员和回收站可删除订单，用户只能删除状态为1的订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "int"),
    })
    public Result deleteOrders(@RequestParam("id") int id, @CurrentUser String[] tokenData) throws Exception {
        Result result = ResultUtil.success();
        int type = Integer.parseInt(tokenData[0]);
        try {
            service.deleteOrders(type, id);
            result = ResultUtil.success();
        } catch (Exception e) {
            result = handle.exceptionGet(e);
            //result = ResultUtil.error(ExceptionEnum.USER_EXIST);
        }
        return result;
    }
}
