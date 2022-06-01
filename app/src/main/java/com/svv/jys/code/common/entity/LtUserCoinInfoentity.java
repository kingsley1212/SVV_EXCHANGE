package com.svv.jys.code.common.entity;

import com.svv.jys.code.common.base.BaseEntity;

/**
 * Created by js on 2018/5/28.
 */

public class LtUserCoinInfoentity extends BaseEntity{
    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    /**
     * id : 37
     * borrow_sell : 0.00000000
     * buy_coin_freeze : 0.00000000
     * price : 0.00000000
     * market : btc_usdt
     * risk_ratio : 0.00
     * sell_coin_freeze : 0.00000000
     * buy_coin_debit : 0.00000000
     * sell_coin : 0.00000000
     * user_id : 12
     * borrow_buy : 0.00000000
     * buy_coin : 0.00000000
     * sell_coin_debit : 0.00000000
     */
    private String percent;
    private String id;
    private String borrow_sell;
    private String buy_coin_freeze;
    private String price;
    private String market;
    private String risk_ratio;
    private String sell_coin_freeze;
    private String buy_coin_debit;
    private String sell_coin;
    private String user_id;
    private String borrow_buy;
    private String buy_coin;
    private String sell_coin_debit;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBorrow_sell() {
        return borrow_sell;
    }

    public void setBorrow_sell(String borrow_sell) {
        this.borrow_sell = borrow_sell;
    }

    public String getBuy_coin_freeze() {
        return buy_coin_freeze;
    }

    public void setBuy_coin_freeze(String buy_coin_freeze) {
        this.buy_coin_freeze = buy_coin_freeze;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getRisk_ratio() {
        return risk_ratio;
    }

    public void setRisk_ratio(String risk_ratio) {
        this.risk_ratio = risk_ratio;
    }

    public String getSell_coin_freeze() {
        return sell_coin_freeze;
    }

    public void setSell_coin_freeze(String sell_coin_freeze) {
        this.sell_coin_freeze = sell_coin_freeze;
    }

    public String getBuy_coin_debit() {
        return buy_coin_debit;
    }

    public void setBuy_coin_debit(String buy_coin_debit) {
        this.buy_coin_debit = buy_coin_debit;
    }

    public String getSell_coin() {
        return sell_coin;
    }

    public void setSell_coin(String sell_coin) {
        this.sell_coin = sell_coin;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getBorrow_buy() {
        return borrow_buy;
    }

    public void setBorrow_buy(String borrow_buy) {
        this.borrow_buy = borrow_buy;
    }

    public String getBuy_coin() {
        return buy_coin;
    }

    public void setBuy_coin(String buy_coin) {
        this.buy_coin = buy_coin;
    }

    public String getSell_coin_debit() {
        return sell_coin_debit;
    }

    public void setSell_coin_debit(String sell_coin_debit) {
        this.sell_coin_debit = sell_coin_debit;
    }


}
