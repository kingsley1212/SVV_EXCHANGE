package com.svv.jys.code.common.entity;


import com.svv.jys.code.common.base.BaseEntity;

import java.util.List;

/**
 * Created by js on 2018/10/19.
 */

public class RenGouRecordEntity extends BaseEntity {

    /**
     * total : 8
     * rows : [{"id":"10","user_id":"1","user_name":"13688885555","status":"审核中","type":"1","num":"2.00000000","total":"4.00000000","real_num":"0.00000000","frezze_num":"0.00000000","fee":"0.04000000","fee_rate":"0.01000000","price":"2.00000000","add_time":"2018-10-19 17:28:46","check_time":"0","settle_time":"0","coin":"eth"},{"id":"8","user_id":"1","user_name":"13688885555","status":"已完成","type":"1","num":"4.00000000","total":"8.00000000","real_num":"2.00000000","frezze_num":"0.00000000","fee":"0.08000000","fee_rate":"0.01000000","price":"2.00000000","add_time":"2018-10-19 16:47:02","check_time":"1539938850","settle_time":"0","coin":"eth"},{"id":"7","user_id":"1","user_name":"13688885555","status":"已完成","type":"1","num":"2.00000000","total":"4.00000000","real_num":"2.00000000","frezze_num":"0.00000000","fee":"0.04000000","fee_rate":"0.01000000","price":"2.00000000","add_time":"2018-10-19 16:45:58","check_time":"1539938801","settle_time":"0","coin":"eth"},{"id":"6","user_id":"1","user_name":"13688885555","status":"已完成","type":"1","num":"1.00000000","total":"2.00000000","real_num":"1.00000000","frezze_num":"0.00000000","fee":"0.02000000","fee_rate":"0.01000000","price":"2.00000000","add_time":"2018-10-19 16:41:49","check_time":"1539938565","settle_time":"1539938702","coin":"eth"},{"id":"5","user_id":"1","user_name":"13688885555","status":"已完成","type":"1","num":"1.00000000","total":"2.00000000","real_num":"1.00000000","frezze_num":"0.00000000","fee":"0.02000000","fee_rate":"0.01000000","price":"2.00000000","add_time":"2018-10-19 16:40:51","check_time":"1539938472","settle_time":"0","coin":"eth"},{"id":"4","user_id":"1","user_name":"13688885555","status":"已完成","type":"1","num":"10.00000000","total":"20.00000000","real_num":"10.00000000","frezze_num":"0.00000000","fee":"0.20000000","fee_rate":"0.01000000","price":"2.00000000","add_time":"2018-10-19 16:38:29","check_time":"1539938403","settle_time":"0","coin":"eth"},{"id":"3","user_id":"1","user_name":"13688885555","status":"已完成","type":"1","num":"20.00000000","total":"40.00000000","real_num":"10.00000000","frezze_num":"0.00000000","fee":"0.40000000","fee_rate":"0.01000000","price":"2.00000000","add_time":"2018-10-19 16:25:54","check_time":"1539938109","settle_time":"0","coin":"eth"},{"id":"2","user_id":"1","user_name":"13688885555","status":"已撤销","type":"1","num":"1.00000000","total":"2.00000000","real_num":"0.00000000","frezze_num":"0.00000000","fee":"0.02000000","fee_rate":"0.01000000","price":"2.00000000","add_time":"2018-10-19 16:20:45","check_time":"1539937527","settle_time":"0","coin":"eth"}]
     * offset : 0
     * limit : 10
     */

    private String total;
    private int offset;
    private int limit;
    private List<RowsBean> rows;

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

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean {
        /**
         * id : 10
         * user_id : 1
         * user_name : 13688885555
         * status : 审核中
         * type : 1
         * num : 2.00000000
         * total : 4.00000000
         * real_num : 0.00000000
         * frezze_num : 0.00000000
         * fee : 0.04000000
         * fee_rate : 0.01000000
         * price : 2.00000000
         * add_time : 2018-10-19 17:28:46
         * check_time : 0
         * settle_time : 0
         * coin : eth
         */

        private String id;
        private String user_id;
        private String user_name;
        private String status;
        private String type;
        private String num;
        private String total;
        private String real_num;
        private String frezze_num;
        private String fee;
        private String fee_rate;
        private String price;
        private String add_time;
        private String check_time;
        private String settle_time;
        private String coin;

        public String getStatus_name() {
            return status_name;
        }

        public void setStatus_name(String status_name) {
            this.status_name = status_name;
        }

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

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public String getReal_num() {
            return real_num;
        }

        public void setReal_num(String real_num) {
            this.real_num = real_num;
        }

        public String getFrezze_num() {
            return frezze_num;
        }

        public void setFrezze_num(String frezze_num) {
            this.frezze_num = frezze_num;
        }

        public String getFee() {
            return fee;
        }

        public void setFee(String fee) {
            this.fee = fee;
        }

        public String getFee_rate() {
            return fee_rate;
        }

        public void setFee_rate(String fee_rate) {
            this.fee_rate = fee_rate;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getCheck_time() {
            return check_time;
        }

        public void setCheck_time(String check_time) {
            this.check_time = check_time;
        }

        public String getSettle_time() {
            return settle_time;
        }

        public void setSettle_time(String settle_time) {
            this.settle_time = settle_time;
        }

        public String getCoin() {
            return coin;
        }

        public void setCoin(String coin) {
            this.coin = coin;
        }
    }
}
