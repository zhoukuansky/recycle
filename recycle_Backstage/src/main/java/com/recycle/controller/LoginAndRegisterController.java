package com.recycle.controller;

import com.recycle.aop.SystemControllerLog;
import com.recycle.exception.ExceptionHandle;
import com.recycle.exception.ResultUtil;
import com.recycle.model.Result;
import com.recycle.server.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
@Api(tags = {"注册和登陆"})
@RestController
@RequestMapping("/loginAndRegister")
public class LoginAndRegisterController {
    @Autowired
    private UserService service;

    @Autowired
    private ExceptionHandle handle;

    @PostMapping("/register")
    @SystemControllerLog(logAction = "insertUser/register", logContent = "新建用户")
    @ApiOperation(value = "注册用户信息", notes = "注册用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tel", value = "用户tel", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "type", value = "账户角色", required = true, dataType = "int"),
    })
    public Result insertUser(@RequestParam("tel") String tel, @RequestParam("password") String password, @RequestParam("type") int type) {
        Result result = ResultUtil.success();
        try {
            Map insertMap = service.insertUser(tel, password, type);
            result = ResultUtil.success(insertMap);
        } catch (Exception e) {
            result = handle.exceptionGet(e);
            //result = ResultUtil.error(ExceptionEnum.USER_EXIST);
        }
        return result;
    }

    @GetMapping("/login")
    @SystemControllerLog(logAction = "login", logContent = "用户登陆")
    @ApiOperation(value = "用户登陆", notes = "用户登陆")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tel", value = "用户tel", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "type", value = "账户角色", required = true, dataType = "int"),
    })
    public Result login(@RequestParam("tel") String tel, @RequestParam("password") String password, @RequestParam("type") int type) {
        Result result = ResultUtil.success();
        try {
            Map loginMap = service.login(tel, password, type);
            result = ResultUtil.success(loginMap);
        } catch (Exception e) {
            result = handle.exceptionGet(e);
            //result = ResultUtil.error(ExceptionEnum.USER_EXIST);
        }
        return result;
    }
}
