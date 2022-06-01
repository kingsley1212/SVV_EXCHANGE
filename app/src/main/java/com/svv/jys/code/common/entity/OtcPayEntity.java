package com.svv.jys.code.common.entity;

import com.svv.jys.code.common.base.BaseEntity;

import java.util.List;

/**
 * Created by js on 2018/8/29.
 */

public class OtcPayEntity extends BaseEntity {
    /**
     * total : 3
     * rows : [{"id":"1","name":"支付宝","code":"alipay","type":"1"},{"id":"2","name":"银行转账","code":"bank","type":"0"},{"id":"5","name":"微信支付","code":"wechat","type":"1"}]
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

    public static class RowsBean extends BaseEntity{
        /**
         * id : 1
         * name : 支付宝
         * code : alipay
         * type : 1
         */

        private String id;
        private String name;
        private String code;
        private String type;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
