package com.svv.jys.code.common.entity;

import com.svv.jys.code.common.base.BaseEntity;

public class TransactionEntity extends BaseEntity {

    /**
     * amount : 0.07710244
     * price : 19.98870000
     * tid :
     * time : 1557977221
     * type : sell
     */

    private String amount;
    private String price;
    private String tid;
    private String time;
    private String type;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
