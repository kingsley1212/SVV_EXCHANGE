package com.svv.jys.code.common.entity;

import com.svv.jys.code.common.base.BaseEntity;

public class CoinAddressEntity extends BaseEntity {

    /**
     * address : 896882da2c5d270b8d4c615fa1715198
     * name : btc
     * memo : {"type":"address","address":"896882da2c5d270b8d4c615fa1715198"}
     * tips : 321
     * coin : btc
     */

    private String address;
    private String name;
    private String memo;
    private String tips;
    private String coin;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }
}
