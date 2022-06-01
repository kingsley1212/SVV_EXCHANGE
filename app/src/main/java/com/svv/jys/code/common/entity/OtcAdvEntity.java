package com.svv.jys.code.common.entity;

import com.svv.jys.code.common.base.BaseEntity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by js on 2018/6/8.
 */

public class OtcAdvEntity extends BaseEntity {
    /**
     * limit : 10
     * total : 1
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
         * payment : {"bank":"\u94f6\u884c\u8f6c\u8d26","wechat":"\u5fae\u4fe1\u652f\u4ed8","alipay":"\u652f\u4ed8\u5b9d","cash":"\u73b0\u91d1\u5b58\u6b3e"}
         * status : 0
         * premium : 90.00
         * payment_list : {"alipay":"支付宝","cash":"现金存款","bank":"银行转账","wechat":"微信支付"}
         * min_amount : 100.00
         * order_msg : {"finish_rate":"100.00","total":"2","finish":"2","appeal":0,"three":"2","fangxing":0,"success":0}
         * type : 1
         * r_name : 王
         * currency : CNY
         * country : 中国
         * message : 333
         * id : 1
         * num : 100.00000000
         * min_price : 0.00
         * price : 12.39
         * coin_name : BTC
         * true_name : 王强
         * user_id : 1
         * add_time : 1527148219
         * prompt : 30
         * max_amount : 1000.00
         */



        private String payment;
        private String status;
        private String premium;
        private PaymentListBean payment_list;
        private String min_amount;
        private OrderMsgBean order_msg;
        private String type;
        private String r_name;
        private String currency;
        private String country;
        private String message;
        private String id;
        private String num;
        private String min_price;
        private String price;
        private String coin_name;
        private String true_name;
        private String user_id;
        private String add_time;
        private String prompt;
        private String max_amount;
        private String logo;

        public String getNick_name() {
            return nick_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
        }

        private String nick_name;

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getPayment() {
            return payment;
        }

        public void setPayment(String payment) {
            this.payment = payment;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getPremium() {
            return premium;
        }

        public void setPremium(String premium) {
            this.premium = premium;
        }

        public PaymentListBean getPayment_list() {
            return payment_list;
        }

        public void setPayment_list(PaymentListBean payment_list) {
            this.payment_list = payment_list;
        }

        public String getMin_amount() {
            return min_amount;
        }

        public void setMin_amount(String min_amount) {
            this.min_amount = min_amount;
        }

        public OrderMsgBean getOrder_msg() {
            return order_msg;
        }

        public void setOrder_msg(OrderMsgBean order_msg) {
            this.order_msg = order_msg;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getR_name() {
            return r_name;
        }

        public void setR_name(String r_name) {
            this.r_name = r_name;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
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

        public String getMin_price() {
            return min_price;
        }

        public void setMin_price(String min_price) {
            this.min_price = min_price;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getCoin_name() {
            return coin_name;
        }

        public void setCoin_name(String coin_name) {
            this.coin_name = coin_name;
        }

        public String getTrue_name() {
            return true_name;
        }

        public void setTrue_name(String true_name) {
            this.true_name = true_name;
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

        public String getPrompt() {
            return prompt;
        }

        public void setPrompt(String prompt) {
            this.prompt = prompt;
        }

        public String getMax_amount() {
            return max_amount;
        }

        public void setMax_amount(String max_amount) {
            this.max_amount = max_amount;
        }

        public static class PaymentListBean implements Serializable{
            /**
             * alipay : 支付宝
             * cash : 现金存款
             * bank : 银行转账
             * wechat : 微信支付
             */

            private String alipay;
            private String cash;
            private String bank;
            private String wechat;

            public String getAlipay() {
                return alipay;
            }

            public void setAlipay(String alipay) {
                this.alipay = alipay;
            }

            public String getCash() {
                return cash;
            }

            public void setCash(String cash) {
                this.cash = cash;
            }

            public String getBank() {
                return bank;
            }

            public void setBank(String bank) {
                this.bank = bank;
            }

            public String getWechat() {
                return wechat;
            }

            public void setWechat(String wechat) {
                this.wechat = wechat;
            }
        }

        public static class OrderMsgBean implements Serializable{
            /**
             * finish_rate : 100.00
             * total : 2
             * finish : 2
             * appeal : 0
             * three : 2
             * fangxing : 0
             * success : 0
             */

            private String finish_rate;
            private String total;
            private String finish;
            private int appeal;
            private String three;
            private int fangxing;
            private int success;

            public String getFinish_rate() {
                return finish_rate;
            }

            public void setFinish_rate(String finish_rate) {
                this.finish_rate = finish_rate;
            }

            public String getTotal() {
                return total;
            }

            public void setTotal(String total) {
                this.total = total;
            }

            public String getFinish() {
                return finish;
            }

            public void setFinish(String finish) {
                this.finish = finish;
            }

            public int getAppeal() {
                return appeal;
            }

            public void setAppeal(int appeal) {
                this.appeal = appeal;
            }

            public String getThree() {
                return three;
            }

            public void setThree(String three) {
                this.three = three;
            }

            public int getFangxing() {
                return fangxing;
            }

            public void setFangxing(int fangxing) {
                this.fangxing = fangxing;
            }

            public int getSuccess() {
                return success;
            }

            public void setSuccess(int success) {
                this.success = success;
            }
        }
    }
}
