package com.svv.jys.code.common.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by js on 2018/5/29.
 */

public class LtBorrowRecord {
    /**
     * limit : 10
     * total : 4
     * offset : 0
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

    public static class RowsBean implements Serializable{
        /**
         * issue_time : 0
         * status : 0
         * accrual_deal : 0.00000000
         * coin : eth
         * accrual : 0.00000000
         * id : 11
         * num : 0.00100000
         * rate : 0.001000
         * market : eth_btc
         * deal : 0.00000000
         * end_time : 0
         * issue_num : 0
         * user_id : 1
         * add_time : 1527489173
         */

        private String issue_time;
        private String status;
        private String accrual_deal;
        private String coin;
        private String accrual;
        private String id;
        private String num;
        private String rate;
        private String market;
        private String deal;
        private String end_time;
        private String issue_num;
        private String user_id;
        private String add_time;

        public String getIssue_time() {
            return issue_time;
        }

        public void setIssue_time(String issue_time) {
            this.issue_time = issue_time;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getAccrual_deal() {
            return accrual_deal;
        }

        public void setAccrual_deal(String accrual_deal) {
            this.accrual_deal = accrual_deal;
        }

        public String getCoin() {
            return coin;
        }

        public void setCoin(String coin) {
            this.coin = coin;
        }

        public String getAccrual() {
            return accrual;
        }

        public void setAccrual(String accrual) {
            this.accrual = accrual;
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

        public String getRate() {
            return rate;
        }

        public void setRate(String rate) {
            this.rate = rate;
        }

        public String getMarket() {
            return market;
        }

        public void setMarket(String market) {
            this.market = market;
        }

        public String getDeal() {
            return deal;
        }

        public void setDeal(String deal) {
            this.deal = deal;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public String getIssue_num() {
            return issue_num;
        }

        public void setIssue_num(String issue_num) {
            this.issue_num = issue_num;
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
    }
}
