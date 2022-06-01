package com.svv.jys.code.common.entity;

import com.svv.jys.code.common.base.BaseEntity;

import java.util.List;

/**
 * Created by js on 2018/5/25.
 */

public class EntrustDetailEntity extends BaseEntity{
    /**
     * limit : 10
     * total : 1
     * offset : 0
     * rows : [{"peer_id":"1","memo":"","status":"1","buy_id":"1","round":"0","fee_sell":"0.00000000","type":"2","mum":"0.01000000","peer_name":"13688885555","buy_price":"0.00000000","id":"1","user_name":"13688885555","sell_id":"2","num":"0.01000000","price":"1.00000000","market":"eth_btc","end_time":"0","buy_fee":"0","user_id":"1","add_time":"1527213168","fee_buy":"0.00000000"}]
     */

    private int limit;
    private String total;
    private int offset;
    private List<RowsBean> rows;

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean {
        /**
         * peer_id : 1
         * memo :
         * status : 1
         * buy_id : 1
         * round : 0
         * fee_sell : 0.00000000
         * type : 2
         * mum : 0.01000000
         * peer_name : 13688885555
         * buy_price : 0.00000000
         * id : 1
         * user_name : 13688885555
         * sell_id : 2
         * num : 0.01000000
         * price : 1.00000000
         * market : eth_btc
         * end_time : 0
         * buy_fee : 0
         * user_id : 1
         * add_time : 1527213168
         * fee_buy : 0.00000000
         */

        private String peer_id;
        private String memo;
        private String status;
        private String buy_id;
        private String round;
        private String fee_sell;
        private String type;
        private String mum;
        private String peer_name;
        private String buy_price;
        private String id;
        private String user_name;
        private String sell_id;
        private String num;
        private String price;
        private String market;
        private String end_time;
        private String buy_fee;
        private String user_id;
        private String add_time;
        private String fee_buy;

        public String getOther_name() {
            return other_name;
        }

        public void setOther_name(String other_name) {
            this.other_name = other_name;
        }

        private String other_name
;
        public String getType_name() {
            return type_name;
        }

        public void setType_name(String type_name) {
            this.type_name = type_name;
        }

        private String type_name;

        public String getPeer_id() {
            return peer_id;
        }

        public void setPeer_id(String peer_id) {
            this.peer_id = peer_id;
        }

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getBuy_id() {
            return buy_id;
        }

        public void setBuy_id(String buy_id) {
            this.buy_id = buy_id;
        }

        public String getRound() {
            return round;
        }

        public void setRound(String round) {
            this.round = round;
        }

        public String getFee_sell() {
            return fee_sell;
        }

        public void setFee_sell(String fee_sell) {
            this.fee_sell = fee_sell;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getMum() {
            return mum;
        }

        public void setMum(String mum) {
            this.mum = mum;
        }

        public String getPeer_name() {
            return peer_name;
        }

        public void setPeer_name(String peer_name) {
            this.peer_name = peer_name;
        }

        public String getBuy_price() {
            return buy_price;
        }

        public void setBuy_price(String buy_price) {
            this.buy_price = buy_price;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getSell_id() {
            return sell_id;
        }

        public void setSell_id(String sell_id) {
            this.sell_id = sell_id;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
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

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public String getBuy_fee() {
            return buy_fee;
        }

        public void setBuy_fee(String buy_fee) {
            this.buy_fee = buy_fee;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getFee_buy() {
            return fee_buy;
        }

        public void setFee_buy(String fee_buy) {
            this.fee_buy = fee_buy;
        }
    }
}
