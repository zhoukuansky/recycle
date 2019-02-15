package com.recycle.model;

public class grades {
    private Integer id;

    private Integer grades_server;

    private Integer grades_time;

    private String grades_describ;

    private Integer ord_order_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGrades_server() {
        return grades_server;
    }

    public void setGrades_server(Integer grades_server) {
        this.grades_server = grades_server;
    }

    public Integer getGrades_time() {
        return grades_time;
    }

    public void setGrades_time(Integer grades_time) {
        this.grades_time = grades_time;
    }

    public String getGrades_describ() {
        return grades_describ;
    }

    public void setGrades_describ(String grades_describ) {
        this.grades_describ = grades_describ == null ? null : grades_describ.trim();
    }

    public Integer getOrd_order_id() {
        return ord_order_id;
    }

    public void setOrd_order_id(Integer ord_order_id) {
        this.ord_order_id = ord_order_id;
    }
}