package com.svv.jys.code.common.entity;

import com.svv.jys.code.common.base.BaseEntity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by js on 2018/6/11.
 */

public class OtcOrderEntity extends BaseEntity{

    /**
     * total : 4
     * rows : [{"id":"5","user_id":"2","adv_user_id":"1","adv_id":"3","order_no":"CS20180611704495","price":"8.60000000","num":"11.86046511","amount":"102.00000000","payment":[{"pay_code":"wechat","pay_info":"微信支付","memo":{"realname":"张三","account":"111"},"type":"1"}],"coin_name":"BTC","prompt":"30","status":"待付款","type":"出售","add_time":"1528697407","end_time":"0","fee":"0.05930232","e_status_buy":"0","e_status_sell":"0","currency":"HKD","o_status":"0","appeal_id":"0","a_status":"0","pay_time":"0","adv_name":"王强"},{"id":"4","user_id":"2","adv_user_id":"1","adv_id":"3","order_no":"CS20180611626409","price":"8.60000000","num":"11.74418604","amount":"101.00000000","payment":[{"pay_code":"wechat","pay_info":"微信支付","memo":{"realname":"张三","account":"111"},"type":"1"}],"coin_name":"BTC","prompt":"30","status":"待收款","type":"出售","add_time":"1528697380","end_time":"1528701431","fee":"0.05872093","e_status_buy":"0","e_status_sell":"0","currency":"HKD","o_status":"1","appeal_id":"1","a_status":"0","pay_time":"1528701419","adv_name":"王强"},{"id":"3","user_id":"2","adv_user_id":"1","adv_id":"1","order_no":"GM20180611746682","price":"12.39000000","num":"8.15173527","amount":"101.00000000","payment":[{"pay_code":"bank","pay_info":"银行转账","memo":{"realname":"王强","bankname":"李红","bankaddress":"46546","account":"456"},"type":"0"},{"pay_code":"wechat","pay_info":"微信支付","memo":{"realname":"王强","account":"123456"},"type":"1"}],"coin_name":"BTC","prompt":"30","status":"交易完成","type":"购买","add_time":"1528696221","end_time":"1528697774","fee":"0.04075867","e_status_buy":"0","e_status_sell":"0","currency":"CNY","o_status":"0","appeal_id":"0","a_status":"0","pay_time":"1528697703","adv_name":"王强"},{"id":"1","user_id":"2","adv_user_id":"1","adv_id":"1","order_no":"GM20180524427436","price":"12.39000000","num":"8.07102502","amount":"100.00000000","payment":[{"pay_code":"bank","pay_info":"银行转账","memo":{"realname":"王强","bankname":"李红","bankaddress":"46546","account":"456"},"type":"0"}],"coin_name":"BTC","prompt":"30","status":"交易完成","type":"购买","add_time":"1527162823","end_time":"1527162862","fee":"0.04035512","e_status_buy":"0","e_status_sell":"0","currency":"CNY","o_status":"0","appeal_id":"0","a_status":"0","pay_time":"1527162839","adv_name":"王强"}]
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

    public static class RowsBean implements Serializable{
        public String getType_name() {
            return type_name;
        }

        public void setType_name(String type_name) {
            this.type_name = type_name;
        }

        public String getStatus_name() {
            return status_name;
        }

        public void setStatus_name(String status_name) {
            this.status_name = status_name;
        }

        public String getTrue_name() {
            return true_name;
        }

        public void setTrue_name(String true_name) {
            this.true_name = true_name;
        }

        public String getIs_shoper() {
            return is_shoper;
        }

        public void setIs_shoper(String is_shoper) {
            this.is_shoper = is_shoper;
        }

        /**
         * id : 5
         * user_id : 2
         * adv_user_id : 1
         * adv_id : 3
         * order_no : CS20180611704495
         * price : 8.60000000
         * num : 11.86046511
         * amount : 102.00000000
         * payment : [{"pay_code":"wechat","pay_info":"微信支付","memo":{"realname":"张三","account":"111"},"type":"1"}]
         * coin_name : BTC
         * prompt : 30
         * status : 待付款
         * type : 出售
         * add_time : 1528697407
         * end_time : 0
         * fee : 0.05930232
         * e_status_buy : 0
         * e_status_sell : 0
         * currency : HKD
         * o_status : 0
         * appeal_id : 0
         * a_status : 0
         * pay_time : 0
         * adv_name : 王强
         */
        private String true_name;
        private String is_shoper;
        private String type_name;
        private String status_name;
        private String id;
        private String user_id;
        private String adv_user_id;
        private String adv_id;
        private String order_no;
        private String price;
        private String num;
        private String amount;
        private String coin_name;
        private String prompt;
        private String status;
        private String type;
        private String add_time;
        private String end_time;
        private String fee;
        private String e_status_buy;
        private String e_status_sell;
        private String currency;
        private String o_status;
        private String appeal_id;
        private String a_status;
        private String pay_time;
        private String adv_name;
        private List<PaymentBean> payment;
        private String adv_logo;

        public String getAdv_logo() {
            return adv_logo;
        }

        public void setAdv_logo(String adv_logo) {
            this.adv_logo = adv_logo;
        }

        public String getR_name() {
            return r_name;
        }

        public void setR_name(String r_name) {
            this.r_name = r_name;
        }

        private String r_name;
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

        public String getAdv_user_id() {
            return adv_user_id;
        }

        public void setAdv_user_id(String adv_user_id) {
            this.adv_user_id = adv_user_id;
        }

        public String getAdv_id() {
            return adv_id;
        }

        public void setAdv_id(String adv_id) {
            this.adv_id = adv_id;
        }

        public String getOrder_no() {
            return order_no;
        }

        public void setOrder_no(String order_no) {
            this.order_no = order_no;
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

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getCoin_name() {
            return coin_name;
        }

        public void setCoin_name(String coin_name) {
            this.coin_name = coin_name;
        }

        public String getPrompt() {
            return prompt;
        }

        public void setPrompt(String prompt) {
            this.prompt = prompt;
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

        public String getFee() {
            return fee;
        }

        public void setFee(String fee) {
            this.fee = fee;
        }

        public String getE_status_buy() {
            return e_status_buy;
        }

        public void setE_status_buy(String e_status_buy) {
            this.e_status_buy = e_status_buy;
        }

        public String getE_status_sell() {
            return e_status_sell;
        }

        public void setE_status_sell(String e_status_sell) {
            this.e_status_sell = e_status_sell;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public String getO_status() {
            return o_status;
        }

        public void setO_status(String o_status) {
            this.o_status = o_status;
        }

        public String getAppeal_id() {
            return appeal_id;
        }

        public void setAppeal_id(String appeal_id) {
            this.appeal_id = appeal_id;
        }

        public String getA_status() {
            return a_status;
        }

        public void setA_status(String a_status) {
            this.a_status = a_status;
        }

        public String getPay_time() {
            return pay_time;
        }

        public void setPay_time(String pay_time) {
            this.pay_time = pay_time;
        }

        public String getAdv_name() {
            return adv_name;
        }

        public void setAdv_name(String adv_name) {
            this.adv_name = adv_name;
        }

        public List<PaymentBean> getPayment() {
            return payment;
        }

        public void setPayment(List<PaymentBean> payment) {
            this.payment = payment;
        }

        public static class PaymentBean implements Serializable{
            /**
             * pay_code : wechat
             * pay_info : 微信支付
             * memo : {"realname":"张三","account":"111"}
             * type : 1
             */

            private String pay_code;
            private String pay_info;
            private MemoBean memo;
            private String type;

            public String getPay_code() {
                return pay_code;
            }

            public void setPay_code(String pay_code) {
                this.pay_code = pay_code;
            }

            public String getPay_info() {
                return pay_info;
            }

            public void setPay_info(String pay_info) {
                this.pay_info = pay_info;
            }

            public MemoBean getMemo() {
                return memo;
            }

            public void setMemo(MemoBean memo) {
                this.memo = memo;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public static class MemoBean implements Serializable{
                /**
                 * realname : 张三
                 * account : 111
                 */

                private String realname;
                private String account;
                private String bankaddress;

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

                private String bankname;
                public String getRealname() {
                    return realname;
                }

                public void setRealname(String realname) {
                    this.realname = realname;
                }

                public String getAccount() {
                    return account;
                }

                public void setAccount(String account) {
                    this.account = account;
                }
            }
        }
    }
}
