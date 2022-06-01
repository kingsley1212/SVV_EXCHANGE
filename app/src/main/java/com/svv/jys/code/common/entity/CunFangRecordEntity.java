package com.svv.jys.code.common.entity;


import com.svv.jys.code.common.base.BaseEntity;

import java.util.List;

/**
 * Created by js on 2018/8/10.
 */

public class CunFangRecordEntity extends BaseEntity {

    /**
     * rows : [{"coin":"btc","add_time":"1533897606","end_time":"1536422400","deal_num":"0.06000000","status":"0","revolution":"1","id":"2","num":"12.00000000","user_name":"13688885555","gain_num":"0.12000000","user_id":"1"},{"coin":"btc","add_time":"1532743960","end_time":"1","deal_num":"0.01000000","status":"1","revolution":"1","id":"1","num":"1.00000000","user_name":"13688885555","gain_num":"0.01000000","user_id":"1"}]
     * total : 2
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
         * coin : btc
         * add_time : 1533897606
         * end_time : 1536422400
         * deal_num : 0.06000000
         * status : 0
         * revolution : 1
         * id : 2
         * num : 12.00000000
         * user_name : 13688885555
         * gain_num : 0.12000000
         * user_id : 1
         */

        private String coin;
        private String add_time;
        private String end_time;
        private String deal_num;
        private String status;
        private String revolution;
        private String id;
        private String num;
        private String user_name;
        private String gain_num;
        private String user_id;
        private String r_coin;

        public String getR_coin() {
            return r_coin;
        }

        public void setR_coin(String r_coin) {
            this.r_coin = r_coin;
        }

        public String getStatus_name() {
            return status_name;
        }

        public void setStatus_name(String status_name) {
            this.status_name = status_name;
        }

        private String status_name;

        public String getCoin() {
            return coin;
        }

        public void setCoin(String coin) {
            this.coin = coin;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public String getDeal_num() {
            return deal_num;
        }

        public void setDeal_num(String deal_num) {
            this.deal_num = deal_num;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getRevolution() {
            return revolution;
        }

        public void setRevolution(String revolution) {
            this.revolution = revolution;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getGain_num() {
            return gain_num;
        }

        public void setGain_num(String gain_num) {
            this.gain_num = gain_num;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }
    }
}
