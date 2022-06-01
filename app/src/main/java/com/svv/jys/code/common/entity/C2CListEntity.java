package com.svv.jys.code.common.entity;

import com.svv.jys.code.common.base.BaseEntity;

import java.util.List;

public class C2CListEntity extends BaseEntity {

    /**
     * rows : [{"id":"52","user_id":"1","bank_name":"工商银行工商银行地址","bank_no":"工商银行卡号","true_name":"工商银行姓名","coin":"btc","price":"2.00","num":"1.00","total":"2.00","end_time":"0","status":"0","type":"2","order_sn":"7375911133","add_time":"2019-03-29 14:41:24","admin_id":"0","status_name":"交易中"},{"id":"51","user_id":"1","bank_name":"工商银行工商银行地址","bank_no":"工商银行卡号","true_name":"工商银行姓名","coin":"btc","price":"2.00","num":"23.26","total":"46.51","end_time":"1553841069","status":"3","type":"2","order_sn":"8309767999","add_time":"2019-03-29 14:28:47","admin_id":"0","status_name":"已撤销"},{"id":"50","user_id":"1","bank_name":"admin","bank_no":"3443453214235","true_name":"admin","coin":"btc","price":"1.00","num":"1.00","total":"1.00","end_time":"1553840850","status":"3","type":"1","order_sn":"7548976905","add_time":"2019-03-29 14:27:26","admin_id":"1","status_name":"已撤销"},{"id":"49","user_id":"1","bank_name":"工商银行工商银行地址","bank_no":"工商银行卡号","true_name":"工商银行姓名","coin":"btc","price":"2.00","num":"21.00","total":"42.00","end_time":"1553840812","status":"3","type":"2","order_sn":"0756451974","add_time":"2019-03-29 14:12:11","admin_id":"0","status_name":"已撤销"},{"id":"37","user_id":"1","bank_name":"工商银行工商银行地址","bank_no":null,"true_name":"工商银行姓名","coin":"btc","price":"2.00","num":"100.00","total":"200.00","end_time":"1553831842","status":"2","type":"2","order_sn":"4577664719","add_time":"2019-03-29 09:48:54","admin_id":"1","status_name":"已拒绝"},{"id":"36","user_id":"1","bank_name":"admin","bank_no":"3443453214235","true_name":"admin","coin":"btc","price":"1.00","num":"11.00","total":"11.00","end_time":"1553831789","status":"2","type":"1","order_sn":"5873252905","add_time":"2019-03-28 17:39:30","admin_id":"1","status_name":"已拒绝"},{"id":"34","user_id":"1","bank_name":"admin","bank_no":"3443453214235","true_name":"admin","coin":"btc","price":"1.00","num":"100.00","total":"100.00","end_time":"1553765964","status":"2","type":"1","order_sn":"8916593194","add_time":"2019-03-26 08:55:24","admin_id":"1","status_name":"已拒绝"},{"id":"33","user_id":"1","bank_name":"admin","bank_no":"3443453214235","true_name":"admin","coin":"btc","price":"1.00","num":"10.00","total":"10.00","end_time":"1553561700","status":"2","type":"1","order_sn":"0753075237","add_time":"2019-03-25 18:01:57","admin_id":"1","status_name":"已拒绝"},{"id":"26","user_id":"1","bank_name":"农业银行东莞支行","bank_no":null,"true_name":"王强","coin":"usdt","price":"6.00","num":"100.00","total":"600.00","end_time":"1553561705","status":"2","type":"2","order_sn":"6558784929","add_time":"2019-01-24 11:36:07","admin_id":"1","status_name":"已拒绝"},{"id":"25","user_id":"1","bank_name":"农业银行东莞支行","bank_no":null,"true_name":"王强","coin":"usdt","price":"6.00","num":"100.00","total":"600.00","end_time":"1548300894","status":"1","type":"2","order_sn":"3417387387","add_time":"2019-01-24 11:33:29","admin_id":"1","status_name":"已完成"}]
     * total : 16
     */

    private String total;
    private List<RowsBean> rows;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
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
         * id : 52
         * user_id : 1
         * bank_name : 工商银行工商银行地址
         * bank_no : 工商银行卡号
         * true_name : 工商银行姓名
         * coin : btc
         * price : 2.00
         * num : 1.00
         * total : 2.00
         * end_time : 0
         * status : 0
         * type : 2
         * order_sn : 7375911133
         * add_time : 2019-03-29 14:41:24
         * admin_id : 0
         * status_name : 交易中
         */

        private String id;
        private String user_id;
        private String bank_name;
        private String bank_no;
        private String true_name;
        private String coin;
        private String price;
        private String num;
        private String total;
        private String end_time;
        private String status;
        private String type;
        private String order_sn;
        private String add_time;
        private String admin_id;
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

        public String getBank_name() {
            return bank_name;
        }

        public void setBank_name(String bank_name) {
            this.bank_name = bank_name;
        }

        public String getBank_no() {
            return bank_no;
        }

        public void setBank_no(String bank_no) {
            this.bank_no = bank_no;
        }

        public String getTrue_name() {
            return true_name;
        }

        public void setTrue_name(String true_name) {
            this.true_name = true_name;
        }

        public String getCoin() {
            return coin;
        }

        public void setCoin(String coin) {
            this.coin = coin;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getOrder_sn() {
            return order_sn;
        }

        public void setOrder_sn(String order_sn) {
            this.order_sn = order_sn;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getAdmin_id() {
            return admin_id;
        }

        public void setAdmin_id(String admin_id) {
            this.admin_id = admin_id;
        }

        public String getStatus_name() {
            return status_name;
        }

        public void setStatus_name(String status_name) {
            this.status_name = status_name;
        }
    }
}
