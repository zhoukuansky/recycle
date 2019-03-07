package com.recycle.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class orders {
    private Integer id;

    private Integer user_c_id;

    private Integer user_b_id;

    private Integer rub_id;

    private String order_rub_infor;

    private Float order_moneny;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date order_time_begin;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date order_time_deal;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date order_time_finish;

    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_c_id() {
        return user_c_id;
    }

    public void setUser_c_id(Integer user_c_id) {
        this.user_c_id = user_c_id;
    }

    public Integer getUser_b_id() {
        return user_b_id;
    }

    public void setUser_b_id(Integer user_b_id) {
        this.user_b_id = user_b_id;
    }

    public Integer getRub_id() {
        return rub_id;
    }

    public void setRub_id(Integer rub_id) {
        this.rub_id = rub_id;
    }

    public String getOrder_rub_infor() {
        return order_rub_infor;
    }

    public void setOrder_rub_infor(String order_rub_infor) {
        this.order_rub_infor = order_rub_infor == null ? null : order_rub_infor.trim();
    }

    public Float getOrder_moneny() {
        return order_moneny;
    }

    public void setOrder_moneny(Float order_moneny) {
        this.order_moneny = order_moneny;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    public Date getOrder_time_begin() {
        return order_time_begin;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    public void setOrder_time_begin(Date order_time_begin) {
        this.order_time_begin = order_time_begin;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    public Date getOrder_time_deal() {
        return order_time_deal;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    public void setOrder_time_deal(Date order_time_deal) {
        this.order_time_deal = order_time_deal;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    public Date getOrder_time_finish() {
        return order_time_finish;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    public void setOrder_time_finish(Date order_time_finish) {
        this.order_time_finish = order_time_finish;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}