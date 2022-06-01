package com.svv.jys.code.common.entity;

import com.svv.jys.code.common.base.BaseEntity;

/**
 * Created by js on 2018/5/28.
 */

public class UserCoinInfo extends BaseEntity{
    /**
     * cny_price : 0
     * freeze : 5.64256060
     * able : 100.64256060
     */

    private String cny_price;
    private String freeze;
    private String able;
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCny_price() {
        return cny_price;
    }

    public void setCny_price(String cny_price) {
        this.cny_price = cny_price;
    }

    public String getFreeze() {
        return freeze;
    }

    public void setFreeze(String freeze) {
        this.freeze = freeze;
    }

    public String getAble() {
        return able;
    }

    public void setAble(String able) {
        this.able = able;
    }
}
