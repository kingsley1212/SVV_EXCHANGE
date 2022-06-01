package com.svv.jys.code.common.entity;

import com.svv.jys.code.common.base.BaseEntity;

public class AdvEntity extends BaseEntity {


    /**
     * country : 1
     * num : 22.00000000
     * type : 0
     * title : 出售
     * r_name : sbX4ux
     * premium : 21.00
     * coin_name : BTC
     * price : 444.07
     * currency : AED
     * payment : {"alipay":" \u652f\u4ed8\u5b9d ","bank":" \u94f6\u884c\u8f6c\u8d26 "}
     * id : 116
     * min_amount : 22.00
     * payment_list : {"bank":" 银行转账 ","alipay":" 支付宝 "}
     * is_identity : 1
     * message : 1
     * valuetion : 0
     * coin_logo : http://ds6yjy.hzarjc.com/public/static/upload/coin_logo/201902/262b2bd127aa4e2a1bb8199205c9c3e0.PNG
     * min_price : 0.00
     * login_user : {"country":"1","check_pwd":true,"security_degree":"2","robot_coin":"{\"eos\":\"1\",\"btc\":\"1\",\"eth\":\"1\",\"usdt\":\"1\"}","open_id":"0","collect_market":["eth_usdt"],"user_name":"13688885555","invite_value":"0.00000000","pid":"0","is_shoper":"1","pwd_time":"1556070064","is_google":"0","true_name":"","surname":"","reg_time":"1554188061","verify_google":"0","rank":"0","logo":"","id":"31","verify_email":"1","email":"321654@qq.com","pwd_type":"0","area_code":"86","mobile":"13688885555","is_identity":"1","can_cash":"0","token":"0a9b03a86f904269d4c775be137fb52b","user_id":"31","nick_name":"QKPX0x","verify_mobile":"0","power_id":"0","reg_ip":"113.78.66.145","invite_code":"4F4TZv","status":"1"}
     * user_id : 6
     * max_amount : 33.00
     * prompt : 10
     * add_time : 1559546354
     * user : {"country":"1","security_degree":"4","open_id":"0","collect_market":"","user_name":"13686147211","invite_value":"0.00000000","pid":"0","is_shoper":"1","security_key":"pMM3w2luteUBbo+98NpKPgicuaImXKy9urkmNe7a9xNKUYWNLw4V8B9e8vv65GVo","pwd_time":"1554253174","is_google":"0","true_name":"","surname":"","reg_time":"2019-03-29","verify_google":"0","sec_salt":"jMwWar","rank":"0","logo":"","id":6,"verify_email":"1","email":"t+UK5u5oqsZ8pj4Lq/9C4EXoEcLmkRuNN0q5BxifP/g=","salt":"mCptUa","pwd_type":"0","area_code":"86","mobile":"uZz+YcGF/GdAzKx2CWSZSg==","sec_pwd":"318a713a3e8d79041dfaf3031128eb1c","payment_list":{"bank":{"code":"bank","name":"银行转账","id":"2","type":"0"},"alipay":{"code":"alipay","name":"支付宝","id":"1","type":"1"},"wechat":{"code":"wechat","name":"微信支付","id":"5","type":"1"}},"is_identity":"1","can_cash":"1","token":"dfc6dcdd4a0721dbfab158f1bbe7d4d4","user_id":"6","nick_name":"sbX4ux","verify_mobile":"1","power_id":"0","reg_ip":"113.78.64.173","invite_code":"wsxZkk","pwd":"791873f81ba0aba0b4eca7b0404378b4","status":"1"}
     * max_num : 0.07431260
     * order_msg : {"total":61,"fangxing":0,"success":0,"finish":20,"finish_rate":"32.79","appeal":0,"three":0}
     * status : 0
     */

    private String country;
    private String num;
    private String type;
    private String title;
    private String r_name;
    private String premium;
    private String coin_name;
    private String price;
    private String currency;
    private String payment;
    private String id;
    private String min_amount;
    private String payment_list;
    private String is_identity;
    private String message;
    private String valuetion;
    private String coin_logo;
    private String min_price;
    private FUserInfoEntity login_user;
    private String user_id;
    private String max_amount;
    private String prompt;
    private String add_time;
    private UserBean user;
    private String max_num;
    private OrderMsgBean order_msg;
    private String status;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getR_name() {
        return r_name;
    }

    public void setR_name(String r_name) {
        this.r_name = r_name;
    }

    public String getPremium() {
        return premium;
    }

    public void setPremium(String premium) {
        this.premium = premium;
    }

    public String getCoin_name() {
        return coin_name;
    }

    public void setCoin_name(String coin_name) {
        this.coin_name = coin_name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMin_amount() {
        return min_amount;
    }

    public void setMin_amount(String min_amount) {
        this.min_amount = min_amount;
    }

    public String getPayment_list() {
        return payment_list;
    }

    public void setPayment_list(String payment_list) {
        this.payment_list = payment_list;
    }

    public String getIs_identity() {
        return is_identity;
    }

    public void setIs_identity(String is_identity) {
        this.is_identity = is_identity;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getValuetion() {
        return valuetion;
    }

    public void setValuetion(String valuetion) {
        this.valuetion = valuetion;
    }

    public String getCoin_logo() {
        return coin_logo;
    }

    public void setCoin_logo(String coin_logo) {
        this.coin_logo = coin_logo;
    }

    public String getMin_price() {
        return min_price;
    }

    public void setMin_price(String min_price) {
        this.min_price = min_price;
    }

    public FUserInfoEntity getLogin_user() {
        return login_user;
    }

    public void setLogin_user(FUserInfoEntity login_user) {
        this.login_user = login_user;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getMax_amount() {
        return max_amount;
    }

    public void setMax_amount(String max_amount) {
        this.max_amount = max_amount;
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

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public String getMax_num() {
        return max_num;
    }

    public void setMax_num(String max_num) {
        this.max_num = max_num;
    }

    public OrderMsgBean getOrder_msg() {
        return order_msg;
    }

    public void setOrder_msg(OrderMsgBean order_msg) {
        this.order_msg = order_msg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    public static class UserBean {
        /**
         * country : 1
         * security_degree : 4
         * open_id : 0
         * collect_market :
         * user_name : 13686147211
         * invite_value : 0.00000000
         * pid : 0
         * is_shoper : 1
         * security_key : pMM3w2luteUBbo+98NpKPgicuaImXKy9urkmNe7a9xNKUYWNLw4V8B9e8vv65GVo
         * pwd_time : 1554253174
         * is_google : 0
         * true_name :
         * surname :
         * reg_time : 2019-03-29
         * verify_google : 0
         * sec_salt : jMwWar
         * rank : 0
         * logo :
         * id : 6
         * verify_email : 1
         * email : t+UK5u5oqsZ8pj4Lq/9C4EXoEcLmkRuNN0q5BxifP/g=
         * salt : mCptUa
         * pwd_type : 0
         * area_code : 86
         * mobile : uZz+YcGF/GdAzKx2CWSZSg==
         * sec_pwd : 318a713a3e8d79041dfaf3031128eb1c
         * payment_list : {"bank":{"code":"bank","name":"银行转账","id":"2","type":"0"},"alipay":{"code":"alipay","name":"支付宝","id":"1","type":"1"},"wechat":{"code":"wechat","name":"微信支付","id":"5","type":"1"}}
         * is_identity : 1
         * can_cash : 1
         * token : dfc6dcdd4a0721dbfab158f1bbe7d4d4
         * user_id : 6
         * nick_name : sbX4ux
         * verify_mobile : 1
         * power_id : 0
         * reg_ip : 113.78.64.173
         * invite_code : wsxZkk
         * pwd : 791873f81ba0aba0b4eca7b0404378b4
         * status : 1
         */

        private String country;
        private String security_degree;
        private String open_id;
        private String collect_market;
        private String user_name;
        private String invite_value;
        private String pid;
        private String is_shoper;
        private String security_key;
        private String pwd_time;
        private String is_google;
        private String true_name;
        private String surname;
        private String reg_time;
        private String verify_google;
        private String sec_salt;
        private String rank;
        private String logo;
        private int id;
        private String verify_email;
        private String email;
        private String salt;
        private String pwd_type;
        private String area_code;
        private String mobile;
        private String sec_pwd;
        private String payment_list;
        private String is_identity;
        private String can_cash;
        private String token;
        private String user_id;
        private String nick_name;
        private String verify_mobile;
        private String power_id;
        private String reg_ip;
        private String invite_code;
        private String pwd;
        private String status;

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getSecurity_degree() {
            return security_degree;
        }

        public void setSecurity_degree(String security_degree) {
            this.security_degree = security_degree;
        }

        public String getOpen_id() {
            return open_id;
        }

        public void setOpen_id(String open_id) {
            this.open_id = open_id;
        }

        public String getCollect_market() {
            return collect_market;
        }

        public void setCollect_market(String collect_market) {
            this.collect_market = collect_market;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getInvite_value() {
            return invite_value;
        }

        public void setInvite_value(String invite_value) {
            this.invite_value = invite_value;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public String getIs_shoper() {
            return is_shoper;
        }

        public void setIs_shoper(String is_shoper) {
            this.is_shoper = is_shoper;
        }

        public String getSecurity_key() {
            return security_key;
        }

        public void setSecurity_key(String security_key) {
            this.security_key = security_key;
        }

        public String getPwd_time() {
            return pwd_time;
        }

        public void setPwd_time(String pwd_time) {
            this.pwd_time = pwd_time;
        }

        public String getIs_google() {
            return is_google;
        }

        public void setIs_google(String is_google) {
            this.is_google = is_google;
        }

        public String getTrue_name() {
            return true_name;
        }

        public void setTrue_name(String true_name) {
            this.true_name = true_name;
        }

        public String getSurname() {
            return surname;
        }

        public void setSurname(String surname) {
            this.surname = surname;
        }

        public String getReg_time() {
            return reg_time;
        }

        public void setReg_time(String reg_time) {
            this.reg_time = reg_time;
        }

        public String getVerify_google() {
            return verify_google;
        }

        public void setVerify_google(String verify_google) {
            this.verify_google = verify_google;
        }

        public String getSec_salt() {
            return sec_salt;
        }

        public void setSec_salt(String sec_salt) {
            this.sec_salt = sec_salt;
        }

        public String getRank() {
            return rank;
        }

        public void setRank(String rank) {
            this.rank = rank;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getVerify_email() {
            return verify_email;
        }

        public void setVerify_email(String verify_email) {
            this.verify_email = verify_email;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getSalt() {
            return salt;
        }

        public void setSalt(String salt) {
            this.salt = salt;
        }

        public String getPwd_type() {
            return pwd_type;
        }

        public void setPwd_type(String pwd_type) {
            this.pwd_type = pwd_type;
        }

        public String getArea_code() {
            return area_code;
        }

        public void setArea_code(String area_code) {
            this.area_code = area_code;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getSec_pwd() {
            return sec_pwd;
        }

        public void setSec_pwd(String sec_pwd) {
            this.sec_pwd = sec_pwd;
        }

        public String getPayment_list() {
            return payment_list;
        }

        public void setPayment_list(String payment_list) {
            this.payment_list = payment_list;
        }

        public String getIs_identity() {
            return is_identity;
        }

        public void setIs_identity(String is_identity) {
            this.is_identity = is_identity;
        }

        public String getCan_cash() {
            return can_cash;
        }

        public void setCan_cash(String can_cash) {
            this.can_cash = can_cash;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getNick_name() {
            return nick_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
        }

        public String getVerify_mobile() {
            return verify_mobile;
        }

        public void setVerify_mobile(String verify_mobile) {
            this.verify_mobile = verify_mobile;
        }

        public String getPower_id() {
            return power_id;
        }

        public void setPower_id(String power_id) {
            this.power_id = power_id;
        }

        public String getReg_ip() {
            return reg_ip;
        }

        public void setReg_ip(String reg_ip) {
            this.reg_ip = reg_ip;
        }

        public String getInvite_code() {
            return invite_code;
        }

        public void setInvite_code(String invite_code) {
            this.invite_code = invite_code;
        }

        public String getPwd() {
            return pwd;
        }

        public void setPwd(String pwd) {
            this.pwd = pwd;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

    }

    public static class OrderMsgBean {
        /**
         * total : 61
         * fangxing : 0
         * success : 0
         * finish : 20
         * finish_rate : 32.79
         * appeal : 0
         * three : 0
         */

        private String total;
        private String fangxing;
        private String success;
        private String finish;
        private String finish_rate;
        private String appeal;
        private String three;

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public String getFangxing() {
            return fangxing;
        }

        public void setFangxing(String fangxing) {
            this.fangxing = fangxing;
        }

        public String getSuccess() {
            return success;
        }

        public void setSuccess(String success) {
            this.success = success;
        }

        public String getFinish() {
            return finish;
        }

        public void setFinish(String finish) {
            this.finish = finish;
        }

        public String getFinish_rate() {
            return finish_rate;
        }

        public void setFinish_rate(String finish_rate) {
            this.finish_rate = finish_rate;
        }

        public String getAppeal() {
            return appeal;
        }

        public void setAppeal(String appeal) {
            this.appeal = appeal;
        }

        public String getThree() {
            return three;
        }

        public void setThree(String three) {
            this.three = three;
        }
    }
}
