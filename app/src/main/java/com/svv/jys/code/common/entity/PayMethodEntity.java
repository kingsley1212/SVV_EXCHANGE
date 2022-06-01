package com.svv.jys.code.common.entity;

import com.svv.jys.code.common.base.BaseEntity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by js on 2018/6/16.
 */

public class PayMethodEntity extends BaseEntity {
    /**
     * rows : [{"pay_info":"微信支付","status":"1","memo":{"account":"111","realname":"张三"},"id":"6","type":"1","user_id":"2","pay_code":"wechat"},{"pay_info":"银行转账","status":"1","memo":{"bankaddress":"东莞支行","account":"123456","bankname":"农业银行","realname":""},"id":"5","type":"0","user_id":"2","pay_code":"bank"},{"pay_info":"现金存款","status":"1","memo":{"account":"123456","realname":"王强"},"id":"4","type":"2","user_id":"1","pay_code":"cash"},{"pay_info":"支付宝","status":"1","memo":{"account":"666555","realname":"王强"},"id":"3","type":"1","user_id":"1","pay_code":"alipay"},{"pay_info":"微信支付","status":"1","memo":{"account":"123456","realname":"王强"},"id":"2","type":"1","user_id":"1","pay_code":"wechat"},{"pay_info":"银行转账","status":"1","memo":{"bankaddress":"46546","account":"456","bankname":"李红","realname":"王强"},"id":"1","type":"0","user_id":"1","pay_code":"bank"}]
     * total : 6
     * offset : 0
     * limit : 100
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

    public static class RowsBean implements Serializable{
        /**
         * pay_info : 微信支付
         * status : 1
         * memo : {"account":"111","realname":"张三"}
         * id : 6
         * type : 1
         * user_id : 2
         * pay_code : wechat
         */

        private String pay_info;
        private String status;
        private MemoBean memo;
        private String id;
        private String type;
        private String user_id;
        private String pay_code;

        public String getPay_info() {
            return pay_info;
        }

        public void setPay_info(String pay_info) {
            this.pay_info = pay_info;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public MemoBean getMemo() {
            return memo;
        }

        public void setMemo(MemoBean memo) {
            this.memo = memo;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getPay_code() {
            return pay_code;
        }

        public void setPay_code(String pay_code) {
            this.pay_code = pay_code;
        }

    }
    public static class MemoBean implements Serializable{
        public String getBankaddress() {
            return bankaddress;
        }

        public void setBankaddress(String bankaddress) {
            this.bankaddress = bankaddress;
        }

        public String getBankname() {
            return bankname;
        }

        public void setBankname(String bankname) {
            this.bankname = bankname;
        }

        /**
         * account : 111
         * realname : 张三
         */
        private String bankaddress;
        private String bankname;
        private String account;
        private String realname;

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getRealname() {
            return realname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
        }
    }

}
