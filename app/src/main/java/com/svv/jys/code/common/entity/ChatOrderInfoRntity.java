package com.svv.jys.code.common.entity;

import com.svv.jys.code.common.base.BaseEntity;

import java.util.List;

public class ChatOrderInfoRntity extends BaseEntity {

    /**
     * t : {"name":"王霸","logo":"http://192.168.1.118/yaf_digital_v1/public/static/upload/user/201904/491e2e0d86d1ac1498da60b7c66bde14.JPG","id":"6"}
     * u : {"name":"呦吼","logo":"http://192.168.1.118/yaf_digital_v1/public/static/upload/user/201905/353fd1c46ad6bfd4fc4350fb7a2b4484.JPG","id":"1"}
     * info : {"order_no":"969952","status_name_text":"收款","now_time":1557477856,"type_name":"出售","appeal_id":"0","num":"3.58551452","fee":"0.35855145","current_memo":{"pic":"","account":"123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123","realname":"李红"},"type":"1","current_pay_info":"支付宝","uid":"1","e_status_buy":"0","s_id":6,"current_pay_code":"alipay","coin_name":"BTC","true_name":"王霸","price":"27.89","pay_index":"0","adv_id":"25","payment":[{"pay_info":"支付宝","pay_code":"alipay","memo":{"pic":"","account":"123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123","realname":"李红"},"type":"1"},{"pay_info":"支付宝","pay_code":"alipay","memo":{"pic":"","account":"1111","realname":"阿萨德"},"type":"1"},{"pay_info":"支付宝","pay_code":"alipay","memo":{"account":"q","realname":"s"},"type":"1"},{"pay_info":"银行转账","pay_code":"bank","memo":{"bankaddress":"123","bankname":"123","account":"123","realname":"123"},"type":"0"},{"pay_info":"银行转账","pay_code":"bank","memo":{"bankaddress":"412","bankname":"出售","account":"6663254324145646513515385452135746543215","realname":"546"},"type":"0"},{"pay_info":"微信支付","pay_code":"wechat","memo":{"pic":"","account":"123","realname":"123"},"type":"1"},{"pay_info":"微信支付","pay_code":"wechat","memo":{"pic":"","account":"32","realname":"23"},"type":"1"},{"pay_info":"微信支付","pay_code":"wechat","memo":{"pic":"","account":"asd45as61454","realname":"123"},"type":"1"}],"currency":"CNY","id":"107","e_status_sell":"0","adv_user_name":"13512345678","a_status":"0","amount":"100.00","o_status":"0","status_name":"待收款","end_time":"1557452772","mobile":"13512345678","adv_user_id":"6","pay_time":"1557452772","user_id":"1","prompt":"30","add_time":"1557390506","status":"1"}
     */

    private TBean t;
    private UBean u;
    private InfoBean info;

    public TBean getT() {
        return t;
    }

    public void setT(TBean t) {
        this.t = t;
    }

    public UBean getU() {
        return u;
    }

    public void setU(UBean u) {
        this.u = u;
    }

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public static class TBean {
        /**
         * name : 王霸
         * logo : http://192.168.1.118/yaf_digital_v1/public/static/upload/user/201904/491e2e0d86d1ac1498da60b7c66bde14.JPG
         * id : 6
         */

        private String name;
        private String logo;
        private String id;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    public static class UBean {
        /**
         * name : 呦吼
         * logo : http://192.168.1.118/yaf_digital_v1/public/static/upload/user/201905/353fd1c46ad6bfd4fc4350fb7a2b4484.JPG
         * id : 1
         */

        private String name;
        private String logo;
        private String id;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    public static class InfoBean {
        /**
         * order_no : 969952
         * status_name_text : 收款
         * now_time : 1557477856
         * type_name : 出售
         * appeal_id : 0
         * num : 3.58551452
         * fee : 0.35855145
         * current_memo : {"pic":"","account":"123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123","realname":"李红"}
         * type : 1
         * current_pay_info : 支付宝
         * uid : 1
         * e_status_buy : 0
         * s_id : 6
         * current_pay_code : alipay
         * coin_name : BTC
         * true_name : 王霸
         * price : 27.89
         * pay_index : 0
         * adv_id : 25
         * payment : [{"pay_info":"支付宝","pay_code":"alipay","memo":{"pic":"","account":"123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123","realname":"李红"},"type":"1"},{"pay_info":"支付宝","pay_code":"alipay","memo":{"pic":"","account":"1111","realname":"阿萨德"},"type":"1"},{"pay_info":"支付宝","pay_code":"alipay","memo":{"account":"q","realname":"s"},"type":"1"},{"pay_info":"银行转账","pay_code":"bank","memo":{"bankaddress":"123","bankname":"123","account":"123","realname":"123"},"type":"0"},{"pay_info":"银行转账","pay_code":"bank","memo":{"bankaddress":"412","bankname":"出售","account":"6663254324145646513515385452135746543215","realname":"546"},"type":"0"},{"pay_info":"微信支付","pay_code":"wechat","memo":{"pic":"","account":"123","realname":"123"},"type":"1"},{"pay_info":"微信支付","pay_code":"wechat","memo":{"pic":"","account":"32","realname":"23"},"type":"1"},{"pay_info":"微信支付","pay_code":"wechat","memo":{"pic":"","account":"asd45as61454","realname":"123"},"type":"1"}]
         * currency : CNY
         * id : 107
         * e_status_sell : 0
         * adv_user_name : 13512345678
         * a_status : 0
         * amount : 100.00
         * o_status : 0
         * status_name : 待收款
         * end_time : 1557452772
         * mobile : 13512345678
         * adv_user_id : 6
         * pay_time : 1557452772
         * user_id : 1
         * prompt : 30
         * add_time : 1557390506
         * status : 1
         */

        private String order_no;
        private String status_name_text;
        private int now_time;
        private String type_name;
        private String appeal_id;
        private String num;
        private String fee;
        private CurrentMemoBean current_memo;
        private String type;
        private String current_pay_info;
        private String uid;
        private String e_status_buy;
        private int s_id;
        private String current_pay_code;
        private String coin_name;
        private String true_name;
        private String price;
        private String pay_index;
        private String adv_id;
        private String currency;
        private String id;
        private String e_status_sell;
        private String adv_user_name;
        private String a_status;
        private String amount;
        private String o_status;
        private String status_name;
        private String end_time;
        private String mobile;
        private String adv_user_id;
        private String pay_time;
        private String user_id;
        private String prompt;
        private String add_time;
        private String status;
        private List<PaymentBean> payment;

        public String getOrder_no() {
            return order_no;
        }

        public void setOrder_no(String order_no) {
            this.order_no = order_no;
        }

        public String getStatus_name_text() {
            return status_name_text;
        }

        public void setStatus_name_text(String status_name_text) {
            this.status_name_text = status_name_text;
        }

        public int getNow_time() {
            return now_time;
        }

        public void setNow_time(int now_time) {
            this.now_time = now_time;
        }

        public String getType_name() {
            return type_name;
        }

        public void setType_name(String type_name) {
            this.type_name = type_name;
        }

        public String getAppeal_id() {
            return appeal_id;
        }

        public void setAppeal_id(String appeal_id) {
            this.appeal_id = appeal_id;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getFee() {
            return fee;
        }

        public void setFee(String fee) {
            this.fee = fee;
        }

        public CurrentMemoBean getCurrent_memo() {
            return current_memo;
        }

        public void setCurrent_memo(CurrentMemoBean current_memo) {
            this.current_memo = current_memo;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getCurrent_pay_info() {
            return current_pay_info;
        }

        public void setCurrent_pay_info(String current_pay_info) {
            this.current_pay_info = current_pay_info;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getE_status_buy() {
            return e_status_buy;
        }

        public void setE_status_buy(String e_status_buy) {
            this.e_status_buy = e_status_buy;
        }

        public int getS_id() {
            return s_id;
        }

        public void setS_id(int s_id) {
            this.s_id = s_id;
        }

        public String getCurrent_pay_code() {
            return current_pay_code;
        }

        public void setCurrent_pay_code(String current_pay_code) {
            this.current_pay_code = current_pay_code;
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

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getPay_index() {
            return pay_index;
        }

        public void setPay_index(String pay_index) {
            this.pay_index = pay_index;
        }

        public String getAdv_id() {
            return adv_id;
        }

        public void setAdv_id(String adv_id) {
            this.adv_id = adv_id;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getE_status_sell() {
            return e_status_sell;
        }

        public void setE_status_sell(String e_status_sell) {
            this.e_status_sell = e_status_sell;
        }

        public String getAdv_user_name() {
            return adv_user_name;
        }

        public void setAdv_user_name(String adv_user_name) {
            this.adv_user_name = adv_user_name;
        }

        public String getA_status() {
            return a_status;
        }

        public void setA_status(String a_status) {
            this.a_status = a_status;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getO_status() {
            return o_status;
        }

        public void setO_status(String o_status) {
            this.o_status = o_status;
        }

        public String getStatus_name() {
            return status_name;
        }

        public void setStatus_name(String status_name) {
            this.status_name = status_name;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getAdv_user_id() {
            return adv_user_id;
        }

        public void setAdv_user_id(String adv_user_id) {
            this.adv_user_id = adv_user_id;
        }

        public String getPay_time() {
            return pay_time;
        }

        public void setPay_time(String pay_time) {
            this.pay_time = pay_time;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getPrompt() {
            return prompt;
        }

        public void setPrompt(String prompt) {
            this.prompt = prompt;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public List<PaymentBean> getPayment() {
            return payment;
        }

        public void setPayment(List<PaymentBean> payment) {
            this.payment = payment;
        }

        public static class CurrentMemoBean {
            /**
             * pic :
             * account : 123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123
             * realname : 李红
             */

            private String pic;
            private String account;
            private String realname;

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

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

        public static class PaymentBean {
            /**
             * pay_info : 支付宝
             * pay_code : alipay
             * memo : {"pic":"","account":"123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123","realname":"李红"}
             * type : 1
             */

            private String pay_info;
            private String pay_code;
            private MemoBean memo;
            private String type;

            public String getPay_info() {
                return pay_info;
            }

            public void setPay_info(String pay_info) {
                this.pay_info = pay_info;
            }

            public String getPay_code() {
                return pay_code;
            }

            public void setPay_code(String pay_code) {
                this.pay_code = pay_code;
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

            public static class MemoBean {
                /**
                 * pic :
                 * account : 123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123
                 * realname : 李红
                 */

                private String pic;
                private String account;
                private String realname;

                public String getPic() {
                    return pic;
                }

                public void setPic(String pic) {
                    this.pic = pic;
                }

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
    }
}
