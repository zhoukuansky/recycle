package com.recycle.controller;

import com.recycle.aop.SystemControllerLog;
import com.recycle.exception.ExceptionHandle;
import com.recycle.exception.ResultUtil;
import com.recycle.jjwtToken.CurrentUser;
import com.recycle.model.Result;
import com.recycle.model.user_buy;
import com.recycle.model.user_cre;
import com.recycle.server.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"普通用户自身操作"})
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService service;

    @Autowired
    private ExceptionHandle handle;

    @GetMapping("/userInformation")
    @SystemControllerLog(logAction = "userInformation", logContent = "普通用户查看自己信息")
    @ApiOperation(value = "普通用户查看自己信息", notes = "普通用户查看自己信息")
    @ApiImplicitParams({
    })
    public Result userInformation(@CurrentUser String[] tokenData) {
        Result result = ResultUtil.success();
        int id = Integer.parseInt(tokenData[1]);
        int type = Integer.parseInt(tokenData[0]);
        try {
            result = ResultUtil.success(service.findUserInformation(id, type));
        } catch (Exception e) {
            result = handle.exceptionGet(e);
            //result = ResultUtil.error(ExceptionEnum.USER_EXIST);
        }
        return result;
    }

    @PutMapping("/updateUser")
    @SystemControllerLog(logAction = "updateUser", logContent = "普通用户更新自身信息")
    @ApiOperation(value = "普通用户更新自身信息", notes = "普通用户更新自身信息")
    @ApiImplicitParams({
    })
    public Result updateUser(@RequestBody user_cre user, @CurrentUser String[] tokenData) {
        Result result = ResultUtil.success();
        int id = Integer.parseInt(tokenData[1]);
        try {
            result = ResultUtil.success(service.updateUser(id, user));
        } catch (Exception e) {
            result = handle.exceptionGet(e);
            //result = ResultUtil.error(ExceptionEnum.USER_EXIST);
        }
        return result;
    }

    @PutMapping("/updateRecycleUser")
    @SystemControllerLog(logAction = "updateRecycleUser", logContent = "更新回收站信息")
    @ApiOperation(value = "更新回收站信息", notes = "更新回收站信息")
    @ApiImplicitParams({
    })
    public Result updateRecycleUser(@RequestBody user_buy user, @CurrentUser String[] tokenData) {
        Result result = ResultUtil.success();
        int id = Integer.parseInt(tokenData[1]);
        try {
            result = ResultUtil.success(service.updateRecycleUser(id, user));
        } catch (Exception e) {
            result = handle.exceptionGet(e);
            //result = ResultUtil.error(ExceptionEnum.USER_EXIST);
        }
        return result;
    }
}

