package com.recycle.model;

public class rubbish {
    private Integer id;

    private String rubbis_type;

    private Float rub_price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRubbis_type() {
        return rubbis_type;
    }

    public void setRubbis_type(String rubbis_type) {
        this.rubbis_type = rubbis_type == null ? null : rubbis_type.trim();
    }

    public Float getRub_price() {
        return rub_price;
    }

    public void setRub_price(Float rub_price) {
        this.rub_price = rub_price;
    }
}