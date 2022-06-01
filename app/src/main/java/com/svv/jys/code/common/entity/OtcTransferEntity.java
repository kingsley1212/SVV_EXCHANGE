package com.svv.jys.code.common.entity;

import com.svv.jys.code.common.base.BaseEntity;

public class OtcTransferEntity extends BaseEntity {

    /**
     * user_id : 20
     * num : 100.00000000
     * logo : http://ds6yjy.hzarjc.com/public/static/upload/coin_logo/201902/262b2bd127aa4e2a1bb8199205c9c3e0.PNG
     * id : 25
     * type : 币币转法币
     * add_time : 2019-05-08 14:56:08
     * coin : btc
     */
    private String status;
    private String user_id;
    private String num;
    private String logo;
    private String id;
    private String type;
    private String add_time;
    private String coin;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }

    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }
}
