package com.svv.jys.code.common.entity;

import com.svv.jys.code.common.base.BaseEntity;

public class RewardEntity extends BaseEntity {

    /**
     * market : eth_usdt
     * from_id : 81
     * user_id : 80
     * user_name : 22@qq.com
     * num : 0.00010000
     * id : 35
     * dept : 1
     * from_name : 33@****om
     * add_time : 2019-05-07 08:56:18
     * order_id : 1
     * coin : usdt
     */

    private String market;
    private String from_id;
    private String user_id;
    private String user_name;
    private String num;
    private String id;
    private String dept;
    private String from_name;
    private String add_time;
    private String order_id;
    private String coin;

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getFrom_id() {
        return from_id;
    }

    public void setFrom_id(String from_id) {
        this.from_id = from_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getFrom_name() {
        return from_name;
    }

    public void setFrom_name(String from_name) {
        this.from_name = from_name;
    }

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }
}
