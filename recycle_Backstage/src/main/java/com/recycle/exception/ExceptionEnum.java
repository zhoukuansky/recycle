package com.recycle.exception;

public enum ExceptionEnum {
    SUCCESS(0, "成功"),
    LOGIN_ERROR(2, "登陆失败"),
    USER_NOT_FOUND(3, "用户不存在"),
    USER_EXIST(4, "用户已经存在"),
    ILLEGAL_ARGUMENT(5, "角色异常"),
    UPDATE_ERROR(11, "更新用户信息失败"),
    UPDATE_PASSWORD_ERROR(12, "更新用户密码失败"),
    NEED_LOGIN(400, "没有token，请登陆后操作"),
    METHOD_FAILED(401, "请求方法错误，请尝试其他请求方法"),
    TOKEN_OUTTIME(402, "token过期"),
    NEED_ROLES(403, "你没有权限"),
    DEAL_ERROR(404, "订单处理不符合规定"),
    PARAMETER_ERROR(405, "上传参数不正确，请重新请求"),
    UNKNOW_ERROR(100, "未知错误"),
    ;

    private Integer code;

    private String msg;

    ExceptionEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
