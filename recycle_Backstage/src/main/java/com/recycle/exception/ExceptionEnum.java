package com.recycle.exception;

public enum ExceptionEnum {
    SUCCESS(0, "成功"),
    LOGIN_ERROR(2, "登陆失败"),
    USER_NOT_FOUND(3, "用户不存在"),
    USER_EXIST(4, "用户已经存在"),
    ILLEGAL_ARGUMENT(5, "角色异常"),
    UPDATE_ERROR(11, "更新用户信息失败"),
    NEED_LOGIN(400, "没有token，请登陆后操作"),
    METHOD_FAILED(401, "不支持的请求方法"),
    TOKEN_OUTTIME(402, "token过期"),
    NEED_ROLES(403, "你没有权限"),
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
