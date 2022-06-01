package com.svv.jys.code.common.entity;

import com.svv.jys.code.common.base.BaseEntity;

/**
 * Created by lzj on 2018/5/28.
 */

public class CoinMyTradeEntity extends BaseEntity{
    /**
     * id : 30
     * user_id : 11
     * market : eth_btc
     * price : 1.00000000
     * num : 3.00000000
     * deal : 0.00000000
     * mum : 3.02100000
     * fee : 0.02100000
     * type : 1
     * add_time : 1527471290
     * endtime : 0
     * status : 0
     * buy : btc
     * sell : eth
     * memo :
     * fee_percent : 0.70
     * round : 8
     * trade_type : 0
     * account_type : 0
     * status_name : 交易中
     */

    private String id;
    private String user_id;
    private String market;
    private String price;
    private String num;
    private String deal;
    private String mum;
    private String fee;
    private String type;
    private String add_time;
    private String endtime;
    private String status;
    private String buy;
    private String sell;
    private String memo;
    private String fee_percent;
    private String round;
    private String trade_type;
    private String account_type;
    private String status_name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getDeal() {
        return deal;
    }

    public void setDeal(String deal) {
        this.deal = deal;
    }

    public String getMum() {
        return mum;
    }

    public void setMum(String mum) {
        this.mum = mum;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
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

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBuy() {
        return buy;
    }

    public void setBuy(String buy) {
        this.buy = buy;
    }

    public String getSell() {
        return sell;
    }

    public void setSell(String sell) {
        this.sell = sell;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getFee_percent() {
        return fee_percent;
    }

    public void setFee_percent(String fee_percent) {
        this.fee_percent = fee_percent;
    }

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public String getAccount_type() {
        return account_type;
    }

    public void setAccount_type(String account_type) {
        this.account_type = account_type;
    }

    public String getStatus_name() {
        return status_name;
    }

    public void setStatus_name(String status_name) {
        this.status_name = status_name;
    }
}
