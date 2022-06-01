package com.svv.jys.code.common.entity;


import com.svv.jys.code.common.base.BaseEntity;

public class SuoCangLiXiEntity extends BaseEntity {

    /**
     * day_money : 1.00000000
     * return_money : 15.00000000
     */

    private String day_money;
    private String return_money;

    public String getDay_money() {
        return day_money;
    }

    public void setDay_money(String day_money) {
        this.day_money = day_money;
    }

    public String getReturn_money() {
        return return_money;
    }

    public void setReturn_money(String return_money) {
        this.return_money = return_money;
    }
}
