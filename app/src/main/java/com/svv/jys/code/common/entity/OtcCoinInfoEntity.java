package com.svv.jys.code.common.entity;

import com.svv.jys.code.common.base.BaseEntity;

/**
 * Created by js on 2018/6/7.
 */

public class OtcCoinInfoEntity extends BaseEntity{
    /**
     * able : 991.77723972
     * freeze : 0.00000000
     * cny_price : 6.5
     * address : 8qp8RdFwbtnywSc8BwSR2lEYRqXS7FG
     */

    private String able;
    private String freeze;
    private String cny_price;
    private String address;
    private String digital_able;

    public String getDigital_able() {
        return digital_able;
    }

    public void setDigital_able(String digital_able) {
        this.digital_able = digital_able;
    }

    public String getAble() {
        return able;
    }

    public void setAble(String able) {
        this.able = able;
    }

    public String getFreeze() {
        return freeze;
    }

    public void setFreeze(String freeze) {
        this.freeze = freeze;
    }

    public String getCny_price() {
        return cny_price;
    }

    public void setCny_price(String cny_price) {
        this.cny_price = cny_price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
