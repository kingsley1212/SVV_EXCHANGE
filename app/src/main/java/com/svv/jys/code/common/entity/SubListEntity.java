package com.svv.jys.code.common.entity;

import java.util.List;

public class SubListEntity {


    /**
     * total : 1
     * rows : [{"num":"100.00000000","fee":"0.00000000","type":"2","total":"0.00000000","sell_coin":"usdt","user_id":"8","rate":"0","id":"1","sell_coin_num":"0.00000000","add_time":"1563440883","coin":"eth","status":"1"}]
     */

    private int total;
    private List<RowsBean> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean {
        /**
         * num : 100.00000000
         * fee : 0.00000000
         * type : 2
         * total : 0.00000000
         * sell_coin : usdt
         * user_id : 8
         * rate : 0
         * id : 1
         * sell_coin_num : 0.00000000
         * add_time : 1563440883
         * coin : eth
         * status : 1
         */

        private String num;
        private String fee;
        private String type;
        private String total;
        private String sell_coin;
        private String user_id;
        private String rate;
        private String id;
        private String sell_coin_num;
        private String add_time;
        private String coin;
        private String status;

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
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

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
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

        public String getRate() {
            return rate;
        }

        public void setRate(String rate) {
            this.rate = rate;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSell_coin_num() {
            return sell_coin_num;
        }

        public void setSell_coin_num(String sell_coin_num) {
            this.sell_coin_num = sell_coin_num;
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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
