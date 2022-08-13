package com.recycle.controller;

import com.recycle.aop.SystemControllerLog;
import com.recycle.exception.ExceptionHandle;
import com.recycle.exception.ResultUtil;
import com.recycle.model.Result;
import com.recycle.model.rubbish;
import com.recycle.server.RubbishService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Api(tags = {"垃圾类型操作"})
@RestController
@RequestMapping("/rubbish")
public class RubishController {
    @Autowired
    private RubbishService service;

    @Autowired
    private ExceptionHandle handle;

    @PostMapping("/insertRubbish")
    @SystemControllerLog(logAction = "insertRubbish", logContent = "新建垃圾类型")
    @ApiOperation(value = "新建垃圾类型", notes = "新建垃圾类型")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "垃圾类型描述", required = true, dataType = "String"),
            @ApiImplicitParam(name = "price", value = "价格", required = true, dataType = "int"),
    })
    public Result insertRubbish(@RequestParam("type") String type, @RequestParam("price") String price) {
        Result result = ResultUtil.success();
        try {
            Map insertMap = service.insert(type, price);
            result = ResultUtil.success(insertMap);
        } catch (Exception e) {
            result = handle.exceptionGet(e);
            //result = ResultUtil.error(ExceptionEnum.USER_EXIST);
        }
        return result;
    }

    @GetMapping("/rubbishList")
    @SystemControllerLog(logAction = "rubbishList", logContent = "垃圾列表")
    @ApiOperation(value = "垃圾列表", notes = "垃圾列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页数", required = false, dataType = "String"),
            @ApiImplicitParam(name = "pageSize", value = "页面大小", required = false, dataType = "String"),
    })
    public Result rubbishList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        Result result = ResultUtil.success();
        try {
            result = ResultUtil.success(service.findAllRubbish(pageNum, pageSize));
        } catch (Exception e) {
            result = handle.exceptionGet(e);
            //result = ResultUtil.error(ExceptionEnum.USER_EXIST);
        }
        return result;
    }

    @GetMapping("/rubbishInformation")
    @SystemControllerLog(logAction = "rubbishInformation", logContent = "垃圾类型详细信息")
    @ApiOperation(value = "垃圾类型详细信息", notes = "垃圾类型详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "int"),
    })
    public Result rubbishInformation(@RequestParam("id") Integer id) {
        Result result = ResultUtil.success();
        try {
            result = ResultUtil.success(service.findRubbishInformation(id));
        } catch (Exception e) {
            result = handle.exceptionGet(e);
            //result = ResultUtil.error(ExceptionEnum.USER_EXIST);
        }
        return result;
    }

    @PutMapping("/updateRubbish")
    @SystemControllerLog(logAction = "updateRubbish", logContent = "更新垃圾类型")
    @ApiOperation(value = "更新垃圾类型", notes = "更新垃圾类型")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "int"),
    })
    public Result updateRubbish(@RequestParam("id") Integer id, @RequestBody rubbish rubbish) {
        Result result = ResultUtil.success();
        try {
            result = ResultUtil.success(service.updateRubbish(id, rubbish));
        } catch (Exception e) {
            result = handle.exceptionGet(e);
            //result = ResultUtil.error(ExceptionEnum.USER_EXIST);
        }
        return result;
    }

    @DeleteMapping("/deleteRubbish")
    @SystemControllerLog(logAction = "deleteRubbish", logContent = "删除垃圾类型")
    @ApiOperation(value = "删除垃圾类型", notes = "删除垃圾类型")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "int"),
    })
    public Result deleteRubbish(@RequestParam("id") int id) {
        //System.out.println(tokenData[0]);
        //System.out.println(tokenData[1]);
        Result result = ResultUtil.success();
        try {
            service.deleteRubbish(id);
            result = ResultUtil.success();
        } catch (Exception e) {
            result = handle.exceptionGet(e);
            //result = ResultUtil.error(ExceptionEnum.USER_EXIST);
        }
        return result;
    }
}
