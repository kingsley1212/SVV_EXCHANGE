package com.svv.jys.code.common.entity;

import com.svv.jys.code.common.base.BaseEntity;

import java.util.List;

/**
 * Created by js on 2018/5/24.
 */

public class LtAssatEntity extends BaseEntity {
    private int limit;

    private String sum_total;
    private String cny_total;

    private int offset;

    private List<Rows> rows;

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getLimit() {
        return this.limit;
    }

    public String getSum_total() {
        return sum_total;
    }

    public void setSum_total(String sum_total) {
        this.sum_total = sum_total;
    }

    public String getCny_total() {
        return cny_total;
    }

    public void setCny_total(String cny_total) {
        this.cny_total = cny_total;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getOffset() {
        return this.offset;
    }

    public void setRows(List<Rows> rows) {
        this.rows = rows;
    }

    public List<Rows> getRows() {
        return this.rows;
    }

    public static class Rows extends BaseEntity {
        private String id;
        private String borrow_sell;
        private String borrow_buy;

        public String getBorrow_sell() {
            return borrow_sell;
        }

        public void setBorrow_sell(String borrow_sell) {
            this.borrow_sell = borrow_sell;
        }

        public String getBorrow_buy() {
            return borrow_buy;
        }

        public void setBorrow_buy(String borrow_buy) {
            this.borrow_buy = borrow_buy;
        }

        private String buy_coin_freeze;

        private String price;

        private String market;

        private String risk_ratio;

        private String sell_coin_freeze;

        private String buy_coin_debit;

        private String sell_coin;

        private String user_id;

        private String buy_coin;

        private String sell_coin_debit;

        private String percent;

        public String getPercent() {
            return percent;
        }

        public void setPercent(String percent) {
            this.percent = percent;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getId() {
            return this.id;
        }

        public void setBuy_coin_freeze(String buy_coin_freeze) {
            this.buy_coin_freeze = buy_coin_freeze;
        }

        public String getBuy_coin_freeze() {
            return this.buy_coin_freeze;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getPrice() {
            return this.price;
        }

        public void setMarket(String market) {
            this.market = market;
        }

        public String getMarket() {
            return this.market;
        }

        public void setRisk_ratio(String risk_ratio) {
            this.risk_ratio = risk_ratio;
        }

        public String getRisk_ratio() {
            return this.risk_ratio;
        }

        public void setSell_coin_freeze(String sell_coin_freeze) {
            this.sell_coin_freeze = sell_coin_freeze;
        }

        public String getSell_coin_freeze() {
            return this.sell_coin_freeze;
        }

        public void setBuy_coin_debit(String buy_coin_debit) {
            this.buy_coin_debit = buy_coin_debit;
        }

        public String getBuy_coin_debit() {
            return this.buy_coin_debit;
        }

        public void setSell_coin(String sell_coin) {
            this.sell_coin = sell_coin;
        }

        public String getSell_coin() {
            return this.sell_coin;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getUser_id() {
            return this.user_id;
        }

        public void setBuy_coin(String buy_coin) {
            this.buy_coin = buy_coin;
        }

        public String getBuy_coin() {
            return this.buy_coin;
        }

        public void setSell_coin_debit(String sell_coin_debit) {
            this.sell_coin_debit = sell_coin_debit;
        }

        public String getSell_coin_debit() {
            return this.sell_coin_debit;
        }
    }

}
