package com.recycle.controller;

import com.recycle.aop.SystemControllerLog;
import com.recycle.exception.ExceptionHandle;
import com.recycle.exception.ResultUtil;
import com.recycle.jjwtToken.CurrentUser;
import com.recycle.model.Result;
import com.recycle.model.user_buy_worker;
import com.recycle.server.RecycleWorkerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Api(tags = {"回收站工作人员"})
@RestController
@RequestMapping("/recycleWorker")
public class RecycleWorkerController {
    @Autowired
    private RecycleWorkerService service;

    @Autowired
    private ExceptionHandle handle;

    @PostMapping("/insertRecycleWorker")
    @SystemControllerLog(logAction = "insertRecycleWorker", logContent = "新建回收站工作人员")
    @ApiOperation(value = "新建回收站工作人员", notes = "新建回收站工作人员")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tel", value = "工作人员tel", required = true, dataType = "String"),
            @ApiImplicitParam(name = "name", value = "姓名", required = true, dataType = "String"),
    })
    public Result insertRecycleWorker(@RequestParam("tel") String tel, @RequestParam("name") String name, @CurrentUser String[] tokenData) {
        Result result = ResultUtil.success();
        int recycleId = Integer.parseInt(tokenData[1]);
        try {
            Map insertMap = service.insert(tel, name, recycleId);
            result = ResultUtil.success(insertMap);
        } catch (Exception e) {
            result = handle.exceptionGet(e);
            //result = ResultUtil.error(ExceptionEnum.USER_EXIST);
        }
        return result;
    }

    @GetMapping("/RecycleWorkerList")
    @SystemControllerLog(logAction = "RecycleWorkerList", logContent = "回收站工作人员列表")
    @ApiOperation(value = "回收站工作人员列表", notes = "回收站工作人员列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页数", required = false, dataType = "String"),
            @ApiImplicitParam(name = "pageSize", value = "页面大小", required = false, dataType = "String"),
    })
    public Result RecycleWorkerList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        Result result = ResultUtil.success();
        try {
            result = ResultUtil.success(service.findAllwordker(pageNum, pageSize));
        } catch (Exception e) {
            result = handle.exceptionGet(e);
            //result = ResultUtil.error(ExceptionEnum.USER_EXIST);
        }
        return result;
    }

    @GetMapping("/workerInformation")
    @SystemControllerLog(logAction = "workerInformation", logContent = "工作人员详细信息")
    @ApiOperation(value = "工作人员详细信息", notes = "工作人员详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "int"),
    })
    public Result workerInformation(@RequestParam("id") Integer id) {
        Result result = ResultUtil.success();
        try {
            result = ResultUtil.success(service.findWorkInformation(id));
        } catch (Exception e) {
            result = handle.exceptionGet(e);
            //result = ResultUtil.error(ExceptionEnum.USER_EXIST);
        }
        return result;
    }

    @PutMapping("/updateWorker")
    @SystemControllerLog(logAction = "updateWorker", logContent = "更新工作人员信息")
    @ApiOperation(value = "更新工作人员信息", notes = "更新工作人员信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "int"),
    })
    public Result updateWorker(@RequestParam("id") Integer id, @RequestBody user_buy_worker worker) {
        Result result = ResultUtil.success();
        try {
            result = ResultUtil.success(service.updateWorker(id, worker));
        } catch (Exception e) {
            result = handle.exceptionGet(e);
            //result = ResultUtil.error(ExceptionEnum.USER_EXIST);
        }
        return result;
    }

    @DeleteMapping("/deleteWorker")
    @SystemControllerLog(logAction = "deleteWorker", logContent = "删除工作人员")
    @ApiOperation(value = "删除工作人员", notes = "删除工作人员")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "int"),
    })
    public Result deleteWorker(@RequestParam("id") int id) {
        //System.out.println(tokenData[0]);
        //System.out.println(tokenData[1]);
        Result result = ResultUtil.success();
        try {
            service.deleteUser(id);
            result = ResultUtil.success();
        } catch (Exception e) {
            result = handle.exceptionGet(e);
            //result = ResultUtil.error(ExceptionEnum.USER_EXIST);
        }
        return result;
    }
}
