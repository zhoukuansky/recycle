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
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"超级用户操作选项"})
@RestController
@RequestMapping("/superUser")
public class SuperUserController {
    @Autowired
    private UserService service;

    @Autowired
    private ExceptionHandle handle;

    @DeleteMapping("/deleteUser")
    @SystemControllerLog(logAction = "deleteUser", logContent = "删除用户")
    @ApiOperation(value = "删除用户信息", notes = "删除用户")
    @ApiImplicitParams({
            //@ApiImplicitParam(name = "token", value = "token", required = true, dataType = "String", paramType = "header"),
            @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "int"),
            @ApiImplicitParam(name = "type", value = "账户角色", required = true, dataType = "int"),
    })
    public Result deleteUser(@RequestParam("id") int id, @RequestParam("type") int type, @CurrentUser String[] tokenData) {
        //System.out.println(tokenData[0]);
        //System.out.println(tokenData[1]);
        Result result = ResultUtil.success();
        try {
            service.deleteUser(id, type);
            result = ResultUtil.success();
        } catch (Exception e) {
            result = handle.exceptionGet(e);
            //result = ResultUtil.error(ExceptionEnum.USER_EXIST);
        }
        return result;
    }

    @GetMapping("/userList")
    @SystemControllerLog(logAction = "userList", logContent = "用户列表")
    @ApiOperation(value = "用户列表", notes = "用户列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页数", required = false, dataType = "String"),
            @ApiImplicitParam(name = "pageSize", value = "页面大小", required = false, dataType = "String"),
            @ApiImplicitParam(name = "type", value = "账户角色", required = true, dataType = "int"),
    })
    public Result userList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize, @RequestParam("type") int type) {
        Result result = ResultUtil.success();
        try {
            result = ResultUtil.success(service.findAllUser(pageNum, pageSize, type));
        } catch (Exception e) {
            result = handle.exceptionGet(e);
            //result = ResultUtil.error(ExceptionEnum.USER_EXIST);
        }
        return result;
    }

    @GetMapping("/userInformation")
    @SystemControllerLog(logAction = "userInformation", logContent = "用户详细信息")
    @ApiOperation(value = "用户详细信息", notes = "用户详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "int"),
            @ApiImplicitParam(name = "type", value = "账户角色", required = true, dataType = "int"),
    })
    public Result userInformation(@RequestParam("id") Integer id, @RequestParam("type") int type) {
        Result result = ResultUtil.success();
        try {
            result = ResultUtil.success(service.findUserInformation(id, type));
        } catch (Exception e) {
            result = handle.exceptionGet(e);
            //result = ResultUtil.error(ExceptionEnum.USER_EXIST);
        }
        return result;
    }

    @PutMapping("/updateUser")
    @SystemControllerLog(logAction = "updateUser", logContent = "更新用户信息")
    @ApiOperation(value = "更新用户信息", notes = "更新用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "int"),
    })
    public Result updateUser(@RequestParam("id") Integer id, @RequestBody user_cre user) {
        Result result = ResultUtil.success();
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
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "int"),
    })
    public Result updateRecycleUser(@RequestParam("id") Integer id, @RequestBody user_buy user) {
        Result result = ResultUtil.success();
        try {
            result = ResultUtil.success(service.updateRecycleUser(id, user));
        } catch (Exception e) {
            result = handle.exceptionGet(e);
            //result = ResultUtil.error(ExceptionEnum.USER_EXIST);
        }
        return result;
    }
}
