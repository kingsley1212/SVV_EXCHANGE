package com.svv.jys.code.common.entity;

import com.svv.jys.code.common.base.BaseEntity;

import java.util.List;

/**
 * Created by js on 2018/5/28.
 */

public class MentionRecordEntity extends BaseEntity{

    /**
     * total : 1
     * rows : [{"address":"rewrsfas","status_name":"撤销","num":"10.00000000","fee":"1.00000000","end_time":"1553227180","type":"0","total":"9.00000000","user_id":"1","id":"1","add_time":"2019-03-22 11:59:27","hash":"","coin":"btc","status":"3"}]
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
         * address : rewrsfas
         * status_name : 撤销
         * num : 10.00000000
         * fee : 1.00000000
         * end_time : 1553227180
         * type : 0
         * total : 9.00000000
         * user_id : 1
         * id : 1
         * add_time : 2019-03-22 11:59:27
         * hash :
         * coin : btc
         * status : 3
         */

        private String address;
        private String status_name;
        private String num;
        private String fee;
        private String end_time;
        private String type;
        private String total;
        private String user_id;
        private String id;
        private String add_time;
        private String hash;
        private String coin;
        private String status;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getStatus_name() {
            return status_name;
        }

        public void setStatus_name(String status_name) {
            this.status_name = status_name;
        }

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

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
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

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getHash() {
            return hash;
        }

        public void setHash(String hash) {
            this.hash = hash;
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
