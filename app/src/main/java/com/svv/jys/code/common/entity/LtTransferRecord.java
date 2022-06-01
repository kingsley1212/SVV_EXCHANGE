package com.svv.jys.code.common.entity;

import com.svv.jys.code.common.base.BaseEntity;

import java.util.List;

/**
 * Created by js on 2018/5/29.
 */

public class LtTransferRecord extends BaseEntity{

    /**
     * limit : 10
     * total : 17
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

    public static class RowsBean {
        /**
         * total : 0.50800000
         * fee : 0.00000000
         * id : 18
         * num : 0.50800000
         * market : eth_btc
         * hash :
         * end_time : 1527314810
         * status : 1
         * coin : eth
         * user_id : 1
         * add_time : 1527314810
         * type : 转出
         */

        private String total;
        private String fee;
        private String id;
        private String num;
        private String market;
        private String hash;
        private String end_time;
        private String status;
        private String coin;
        private String user_id;
        private String add_time;
        private String type;

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public String getFee() {
            return fee;
        }

        public void setFee(String fee) {
            this.fee = fee;
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

        public String getMarket() {
            return market;
        }

        public void setMarket(String market) {
            this.market = market;
        }

        public String getHash() {
            return hash;
        }

        public void setHash(String hash) {
            this.hash = hash;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCoin() {
            return coin;
        }

        public void setCoin(String coin) {
            this.coin = coin;
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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
