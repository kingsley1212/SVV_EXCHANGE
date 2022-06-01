package com.svv.jys.code.common.entity;

import com.svv.jys.code.common.base.BaseEntity;

import java.util.List;

/**
 * Created by js on 2018/5/29.
 */

public class LtRecordEntity extends BaseEntity{

    /**
     * limit : 10
     * total : 32
     * offset : 0
     * rows :
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
         * b_money : 0.00000000
         * id : 35
         * operation : 6
         * num : 0.00100000
         * market : eth_btc
         * memo : 借贷数量：0.001
         * coin : eth
         * user_id : 1
         * add_time : 1527489173
         * type : 2
         */

        private String b_money;
        private String id;
        private String operation;
        private String num;
        private String market;
        private String memo;
        private String coin;
        private String user_id;
        private String add_time;
        private String type;

        public String getB_money() {
            return b_money;
        }

        public void setB_money(String b_money) {
            this.b_money = b_money;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getOperation() {
            return operation;
        }

        public void setOperation(String operation) {
            this.operation = operation;
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

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
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
