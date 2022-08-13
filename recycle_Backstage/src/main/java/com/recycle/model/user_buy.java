package com.recycle.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class user_buy {
    private Integer id;

    private String tel;

    @JsonIgnore
    private String password;

    private String address;

    private String name;

    private String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getMail() {
        return email;
    }

    public void setMail(String email) {
        this.email = email == null ? null : email.trim();
    }
}