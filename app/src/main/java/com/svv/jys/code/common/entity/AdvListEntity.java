package com.svv.jys.code.common.entity;

import com.svv.jys.code.common.base.BaseEntity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by js on 2018/7/27.
 */

public class AdvListEntity extends BaseEntity {
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

    private String total;
    private int offset;
    private int limit;
    private List<RowsBean> rows;
    private List<String> coin_name;

    public List<String> getCoin_name() {
        return coin_name;
    }

    public void setCoin_name(List<String> coin_name) {
        this.coin_name = coin_name;
    }

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean implements Serializable {
        public String getType_name() {
            return type_name;
        }

        public void setType_name(String type_name) {
            this.type_name = type_name;
        }

        /**
         * prompt : 30
         * order_msg : {"fangxing":0,"total":"24","finish_rate":"70.83","three":"0","finish":"17","appeal":0,"success":0}
         * payment : {"bank":"\u94f6\u884c\u8f6c\u8d26","alipay":"\u652f\u4ed8\u5b9d"}
         * premium : 1.00
         * r_name : 王
         * payment_list : {"bank":"银行转账","alipay":"支付宝"，"wx":"支付宝"}
         * min_amount : 100.00
         * min_price : 0.00
         * true_name : 王强
         * add_time : 1532670704
         * price : 108197.26
         * country : 中国台湾
         * status : 0
         * id : 9
         * max_amount : 1000.00
         * num : 10000.00000000
         * currency : KRW
         * coin_name : ADE
         * message : 222
         * type : 1
         * user_id : 1
         */
        private String type_name;
        private String prompt;
        private OrderMsgBean order_msg;
        private String payment;
        private String premium;
        private String r_name;
        private String payment_list;
        private String min_amount;
        private String min_price;
        private String true_name;
        private String add_time;
        private String price;
        private String country;
        private String status;
        private String id;
        private String max_amount;
        private String num;
        private String currency;
        private String coin_name;
        private String message;
        private String type;
        private String user_id;

        public String getPrompt() {
            return prompt;
        }

        public void setPrompt(String prompt) {
            this.prompt = prompt;
        }

        public OrderMsgBean getOrder_msg() {
            return order_msg;
        }

        public void setOrder_msg(OrderMsgBean order_msg) {
            this.order_msg = order_msg;
        }

        public String getPayment() {
            return payment;
        }

        public void setPayment(String payment) {
            this.payment = payment;
        }

        public String getPremium() {
            return premium;
        }

        public void setPremium(String premium) {
            this.premium = premium;
        }

        public String getR_name() {
            return r_name;
        }

        public void setR_name(String r_name) {
            this.r_name = r_name;
        }

        public String getPayment_list() {
            return payment_list;
        }

        public void setPayment_list(String payment_list) {
            this.payment_list = payment_list;
        }

        public String getMin_amount() {
            return min_amount;
        }

        public void setMin_amount(String min_amount) {
            this.min_amount = min_amount;
        }

        public String getMin_price() {
            return min_price;
        }

        public void setMin_price(String min_price) {
            this.min_price = min_price;
        }

        public String getTrue_name() {
            return true_name;
        }

        public void setTrue_name(String true_name) {
            this.true_name = true_name;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMax_amount() {
            return max_amount;
        }

        public void setMax_amount(String max_amount) {
            this.max_amount = max_amount;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public String getCoin_name() {
            return coin_name;
        }

        public void setCoin_name(String coin_name) {
            this.coin_name = coin_name;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
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

        public static class OrderMsgBean implements Serializable{
            /**
             * fangxing : 0
             * total : 24
             * finish_rate : 70.83
             * three : 0
             * finish : 17
             * appeal : 0
             * success : 0
             */

            private int fangxing;
            private String total;
            private String finish_rate;
            private String three;
            private String finish;
            private int appeal;
            private int success;

            public int getFangxing() {
                return fangxing;
            }

            public void setFangxing(int fangxing) {
                this.fangxing = fangxing;
            }

            public String getTotal() {
                return total;
            }

            public void setTotal(String total) {
                this.total = total;
            }

            public String getFinish_rate() {
                return finish_rate;
            }

            public void setFinish_rate(String finish_rate) {
                this.finish_rate = finish_rate;
            }

            public String getThree() {
                return three;
            }

            public void setThree(String three) {
                this.three = three;
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

            public int getSuccess() {
                return success;
            }

            public void setSuccess(int success) {
                this.success = success;
            }
        }


    }
}
