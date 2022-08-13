package com.recycle.model;

public class user_buy_worker {
    private Integer id;

    private String name;

    private Integer user_b_id;

    private String tel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser_b_w_name() {
        return name;
    }

    public void setUser_b_w_name(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getUser_b_id() {
        return user_b_id;
    }

    public void setUser_b_id(Integer user_b_id) {
        this.user_b_id = user_b_id;
    }

    public String getUser_b_w_tel() {
        return tel;
    }

    public void setUser_b_w_tel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }
}