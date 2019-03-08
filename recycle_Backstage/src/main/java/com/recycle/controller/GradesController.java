package com.recycle.controller;

import com.recycle.aop.SystemControllerLog;
import com.recycle.exception.ExceptionHandle;
import com.recycle.exception.ResultUtil;
import com.recycle.model.Result;
import com.recycle.model.grades;
import com.recycle.server.GradesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"用户评分"})
@RestController
@RequestMapping("/grades")
public class GradesController {
    @Autowired
    private GradesService service;

    @Autowired
    private ExceptionHandle handle;

    @PostMapping("/insertGrades")
    @SystemControllerLog(logAction = "insertGrades", logContent = "新建订单评分")
    @ApiOperation(value = "新建订单评分", notes = "新建订单评分")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "order_id", value = "订单id", required = true, dataType = "int"),
            @ApiImplicitParam(name = "server", value = "服务评分", required = true, dataType = "int"),
            @ApiImplicitParam(name = "describe", value = "评分描述", required = false, dataType = "String"),
    })
    public Result insertGrades(@RequestParam("order_id") int order_id, @RequestParam("server") int server, @RequestParam(value = "describe", defaultValue = "") String describe) {
        Result result = ResultUtil.success();
        try {
            grades insertMap = service.insert(order_id, server, describe);
            result = ResultUtil.success(insertMap);
        } catch (Exception e) {
            result = handle.exceptionGet(e);
            //result = ResultUtil.error(ExceptionEnum.USER_EXIST);
        }
        return result;
    }

    @GetMapping("/gradesInformation")
    @SystemControllerLog(logAction = "gradesInformation", logContent = "评分详细信息(根据订单id查询)")
    @ApiOperation(value = "评分详细信息(根据订单id查询)", notes = "评分详细信息(根据订单id查询)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "order_id", value = "order_id", required = true, dataType = "int"),
    })
    public Result gradesInformation(@RequestParam("order_id") int order_id) {
        Result result = ResultUtil.success();
        try {
            result = ResultUtil.success(service.gradesInformation(order_id));
        } catch (Exception e) {
            result = handle.exceptionGet(e);
            //result = ResultUtil.error(ExceptionEnum.USER_EXIST);
        }
        return result;
    }

    @DeleteMapping("/deleteGrade")
    @SystemControllerLog(logAction = "deleteGrade", logContent = "删除评分")
    @ApiOperation(value = "删除评分", notes = "删除评分")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "int"),
    })
    public Result deleteGrade(@RequestParam("id") int id) {
        //System.out.println(tokenData[0]);
        //System.out.println(tokenData[1]);
        Result result = ResultUtil.success();
        try {
            service.deleteGrade(id);
            result = ResultUtil.success();
        } catch (Exception e) {
            result = handle.exceptionGet(e);
            //result = ResultUtil.error(ExceptionEnum.USER_EXIST);
        }
        return result;
    }
}
