package com.svv.jys.code.common.entity;

import com.svv.jys.code.common.base.BaseEntity;

import java.util.List;

/**
 * Created by js on 2018/6/20.
 */

public class BusinessInfoEntity extends BaseEntity {

    /**
     * user : {"user_id":"1","collect_market":["usdt_btc","btc_eth"],"rank":"0","user_name":"13512345678","true_name":"红","surname":"李","nick_name":"梦幻帝国","mobile":"13512345678","reg_time":"2019-03-21","reg_ip":"192.168.1.128","status":"1","can_cash":"0","email":"193735456@qq.com","logo":"http://192.168.1.118/yaf_digital_v1/public/static/upload/user/201904/ddb1488c01dbc8acf9f4be012b25e58d.JPG","token":"b2f82319c2ab433d1e86b3ad101de5e5","is_identity":"1","area_code":"86","verify_mobile":"1","verify_email":"1","invite_code":"QhU590","security_degree":"3","verify_google":"0","country":"1","is_shoper":"1","is_google":"0","power_id":"0","idcard":"yLCzPU5WP8LvpgerP4koy7C5IjleKMjXk5CT/0bvOMQ=","pid":"0","invite_value":"0.00000000","pwd_type":"0","open_id":"0","pwd_time":"1554173122","ext_data":null,"robot_coin":"{\"btc\":\"1\",\"eth\":\"1\",\"usdt\":\"1\"}","id":"1"}
     * message : {"total":70,"three":4,"finish":4,"finish_rate":"5.71","appeal":0,"success":0,"fangxing":0}
     * sell : {"rows":[{"nick_name":"梦幻帝国","user_name":"13512345678","true_name":"红","logo":"upload/user/201904/ddb1488c01dbc8acf9f4be012b25e58d.JPG","status":"0","country":"1","currency":"CNY","price":"27.62","min_amount":"100.00","max_amount":"1000.00","message":"1111","payment":"{\"alipay\":\" \\u652f\\u4ed8\\u5b9d \",\"wechat\":\" \\u5fae\\u4fe1\\u652f\\u4ed8 \"}","num":"192.80483670","coin_name":"BTC","add_time":"1553494044","id":"2","type":"1","user_id":"1","payment_list":{"alipay":" 支付宝 ","wechat":" 微信支付 "},"coin_logo":"http://192.168.1.118/yaf_digital_v1/public/static/upload/coin_logo/201902/262b2bd127aa4e2a1bb8199205c9c3e0.PNG"}],"total":1}
     * buy : {"rows":[{"nick_name":"梦幻帝国","user_name":"13512345678","true_name":"红","logo":"upload/user/201904/ddb1488c01dbc8acf9f4be012b25e58d.JPG","status":"0","country":"1","currency":"CNY","price":"27.89","min_amount":"100.00","max_amount":"100.00","message":"111","payment":"{\"alipay\":\" \\u652f\\u4ed8\\u5b9d \"}","num":"46.41448548","coin_name":"BTC","add_time":"1554172764","id":"9","type":"0","user_id":"1","payment_list":{"alipay":" 支付宝 "},"coin_logo":"http://192.168.1.118/yaf_digital_v1/public/static/upload/coin_logo/201902/262b2bd127aa4e2a1bb8199205c9c3e0.PNG"},{"nick_name":"梦幻帝国","user_name":"13512345678","true_name":"红","logo":"upload/user/201904/ddb1488c01dbc8acf9f4be012b25e58d.JPG","status":"0","country":"1","currency":"CNY","price":"100.00","min_amount":"100.00","max_amount":"1000.00","message":"123124","payment":"{\"alipay\":\" \\u652f\\u4ed8\\u5b9d \"}","num":"100.00000000","coin_name":"BTC","add_time":"1554168322","id":"5","type":"0","user_id":"1","payment_list":{"alipay":" 支付宝 "},"coin_logo":"http://192.168.1.118/yaf_digital_v1/public/static/upload/coin_logo/201902/262b2bd127aa4e2a1bb8199205c9c3e0.PNG"},{"nick_name":"梦幻帝国","user_name":"13512345678","true_name":"红","logo":"upload/user/201904/ddb1488c01dbc8acf9f4be012b25e58d.JPG","status":"0","country":"2","currency":"CNY","price":"658.52","min_amount":"100.00","max_amount":"1000.00","message":"dfgdf","payment":"{\"alipay\":\"\\u652f\\u4ed8\\u5b9d\"}","num":"99.69628866","coin_name":"ETH","add_time":"1553496506","id":"3","type":"0","user_id":"1","payment_list":{"alipay":"支付宝"},"coin_logo":"http://192.168.1.118/yaf_digital_v1/public/static/upload/coin_logo/201902/7a6df4bc91b91f80dd15c6913e7e1efb.PNG"},{"nick_name":"梦幻帝国","user_name":"13512345678","true_name":"红","logo":"upload/user/201904/ddb1488c01dbc8acf9f4be012b25e58d.JPG","status":"0","country":"1","currency":"CNY","price":"7179.17","min_amount":"100.00","max_amount":"1000.00","message":"333","payment":"{\"alipay\":\" \\u652f\\u4ed8\\u5b9d \"}","num":"1.00000000","coin_name":"BTC","add_time":"1553226367","id":"1","type":"0","user_id":"1","payment_list":{"alipay":" 支付宝 "},"coin_logo":"http://192.168.1.118/yaf_digital_v1/public/static/upload/coin_logo/201902/262b2bd127aa4e2a1bb8199205c9c3e0.PNG"}],"total":4}
     */

    private UserBean user;
    private MessageBean message;
    private SellBean sell;
    private SellBean buy;

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public MessageBean getMessage() {
        return message;
    }

    public void setMessage(MessageBean message) {
        this.message = message;
    }

    public SellBean getSell() {
        return sell;
    }

    public void setSell(SellBean sell) {
        this.sell = sell;
    }

    public SellBean getBuy() {
        return buy;
    }

    public void setBuy(SellBean buy) {
        this.buy = buy;
    }

    public static class UserBean {
        /**
         * user_id : 1
         * collect_market : ["usdt_btc","btc_eth"]
         * rank : 0
         * user_name : 13512345678
         * true_name : 红
         * surname : 李
         * nick_name : 梦幻帝国
         * mobile : 13512345678
         * reg_time : 2019-03-21
         * reg_ip : 192.168.1.128
         * status : 1
         * can_cash : 0
         * email : 193735456@qq.com
         * logo : http://192.168.1.118/yaf_digital_v1/public/static/upload/user/201904/ddb1488c01dbc8acf9f4be012b25e58d.JPG
         * token : b2f82319c2ab433d1e86b3ad101de5e5
         * is_identity : 1
         * area_code : 86
         * verify_mobile : 1
         * verify_email : 1
         * invite_code : QhU590
         * security_degree : 3
         * verify_google : 0
         * country : 1
         * is_shoper : 1
         * is_google : 0
         * power_id : 0
         * idcard : yLCzPU5WP8LvpgerP4koy7C5IjleKMjXk5CT/0bvOMQ=
         * pid : 0
         * invite_value : 0.00000000
         * pwd_type : 0
         * open_id : 0
         * pwd_time : 1554173122
         * ext_data : null
         * robot_coin : {"btc":"1","eth":"1","usdt":"1"}
         * id : 1
         */

        private String user_id;
        private String rank;
        private String user_name;
        private String true_name;
        private String surname;
        private String nick_name;
        private String mobile;
        private String reg_time;
        private String reg_ip;
        private String status;
        private String can_cash;
        private String email;
        private String logo;
        private String token;
        private String is_identity;
        private String area_code;
        private String verify_mobile;
        private String verify_email;
        private String invite_code;
        private String security_degree;
        private String verify_google;
        private String country;
        private String is_shoper;
        private String is_google;
        private String power_id;
        private String idcard;
        private String pid;
        private String invite_value;
        private String pwd_type;
        private String open_id;
        private String pwd_time;
        private Object ext_data;
        private String robot_coin;
        private String id;
        private List<String> collect_market;

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getRank() {
            return rank;
        }

        public void setRank(String rank) {
            this.rank = rank;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
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

        public String getNick_name() {
            return nick_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getReg_time() {
            return reg_time;
        }

        public void setReg_time(String reg_time) {
            this.reg_time = reg_time;
        }

        public String getReg_ip() {
            return reg_ip;
        }

        public void setReg_ip(String reg_ip) {
            this.reg_ip = reg_ip;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCan_cash() {
            return can_cash;
        }

        public void setCan_cash(String can_cash) {
            this.can_cash = can_cash;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getIs_identity() {
            return is_identity;
        }

        public void setIs_identity(String is_identity) {
            this.is_identity = is_identity;
        }

        public String getArea_code() {
            return area_code;
        }

        public void setArea_code(String area_code) {
            this.area_code = area_code;
        }

        public String getVerify_mobile() {
            return verify_mobile;
        }

        public void setVerify_mobile(String verify_mobile) {
            this.verify_mobile = verify_mobile;
        }

        public String getVerify_email() {
            return verify_email;
        }

        public void setVerify_email(String verify_email) {
            this.verify_email = verify_email;
        }

        public String getInvite_code() {
            return invite_code;
        }

        public void setInvite_code(String invite_code) {
            this.invite_code = invite_code;
        }

        public String getSecurity_degree() {
            return security_degree;
        }

        public void setSecurity_degree(String security_degree) {
            this.security_degree = security_degree;
        }

        public String getVerify_google() {
            return verify_google;
        }

        public void setVerify_google(String verify_google) {
            this.verify_google = verify_google;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getIs_shoper() {
            return is_shoper;
        }

        public void setIs_shoper(String is_shoper) {
            this.is_shoper = is_shoper;
        }

        public String getIs_google() {
            return is_google;
        }

        public void setIs_google(String is_google) {
            this.is_google = is_google;
        }

        public String getPower_id() {
            return power_id;
        }

        public void setPower_id(String power_id) {
            this.power_id = power_id;
        }

        public String getIdcard() {
            return idcard;
        }

        public void setIdcard(String idcard) {
            this.idcard = idcard;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public String getInvite_value() {
            return invite_value;
        }

        public void setInvite_value(String invite_value) {
            this.invite_value = invite_value;
        }

        public String getPwd_type() {
            return pwd_type;
        }

        public void setPwd_type(String pwd_type) {
            this.pwd_type = pwd_type;
        }

        public String getOpen_id() {
            return open_id;
        }

        public void setOpen_id(String open_id) {
            this.open_id = open_id;
        }

        public String getPwd_time() {
            return pwd_time;
        }

        public void setPwd_time(String pwd_time) {
            this.pwd_time = pwd_time;
        }

        public Object getExt_data() {
            return ext_data;
        }

        public void setExt_data(Object ext_data) {
            this.ext_data = ext_data;
        }

        public String getRobot_coin() {
            return robot_coin;
        }

        public void setRobot_coin(String robot_coin) {
            this.robot_coin = robot_coin;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public List<String> getCollect_market() {
            return collect_market;
        }

        public void setCollect_market(List<String> collect_market) {
            this.collect_market = collect_market;
        }
    }

    public static class MessageBean {
        /**
         * total : 70
         * three : 4
         * finish : 4
         * finish_rate : 5.71
         * appeal : 0
         * success : 0
         * fangxing : 0
         */

        private String total;
        private String three;
        private String finish;
        private String finish_rate;
        private String appeal;
        private String success;
        private String fangxing;

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
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

        public String getSuccess() {
            return success;
        }

        public void setSuccess(String success) {
            this.success = success;
        }

        public String getFangxing() {
            return fangxing;
        }

        public void setFangxing(String fangxing) {
            this.fangxing = fangxing;
        }
    }



    public static class SellBean {
        /**
         * rows : [{"nick_name":"梦幻帝国","user_name":"13512345678","true_name":"红","logo":"upload/user/201904/ddb1488c01dbc8acf9f4be012b25e58d.JPG","status":"0","country":"1","currency":"CNY","price":"27.62","min_amount":"100.00","max_amount":"1000.00","message":"1111","payment":"{\"alipay\":\" \\u652f\\u4ed8\\u5b9d \",\"wechat\":\" \\u5fae\\u4fe1\\u652f\\u4ed8 \"}","num":"192.80483670","coin_name":"BTC","add_time":"1553494044","id":"2","type":"1","user_id":"1","payment_list":{"alipay":" 支付宝 ","wechat":" 微信支付 "},"coin_logo":"http://192.168.1.118/yaf_digital_v1/public/static/upload/coin_logo/201902/262b2bd127aa4e2a1bb8199205c9c3e0.PNG"}]
         * total : 1
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

    }

    public static class RowsBean {
        /**
         * nick_name : 梦幻帝国
         * user_name : 13512345678
         * true_name : 红
         * logo : upload/user/201904/ddb1488c01dbc8acf9f4be012b25e58d.JPG
         * status : 0
         * country : 1
         * currency : CNY
         * price : 27.62
         * min_amount : 100.00
         * max_amount : 1000.00
         * message : 1111
         * payment : {"alipay":" \u652f\u4ed8\u5b9d ","wechat":" \u5fae\u4fe1\u652f\u4ed8 "}
         * num : 192.80483670
         * coin_name : BTC
         * add_time : 1553494044
         * id : 2
         * type : 1
         * user_id : 1
         * payment_list : {"alipay":" 支付宝 ","wechat":" 微信支付 "}
         * coin_logo : http://192.168.1.118/yaf_digital_v1/public/static/upload/coin_logo/201902/262b2bd127aa4e2a1bb8199205c9c3e0.PNG
         */

        private String nick_name;
        private String user_name;
        private String true_name;
        private String logo;
        private String status;
        private String country;
        private String currency;
        private String price;
        private String min_amount;
        private String max_amount;
        private String message;
        private String payment;
        private String num;
        private String coin_name;
        private String add_time;
        private String id;
        private String type;
        private String user_id;
        private PaymentListBean payment_list;
        private String coin_logo;

        public String getNick_name() {
            return nick_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getTrue_name() {
            return true_name;
        }

        public void setTrue_name(String true_name) {
            this.true_name = true_name;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getMin_amount() {
            return min_amount;
        }

        public void setMin_amount(String min_amount) {
            this.min_amount = min_amount;
        }

        public String getMax_amount() {
            return max_amount;
        }

        public void setMax_amount(String max_amount) {
            this.max_amount = max_amount;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getPayment() {
            return payment;
        }

        public void setPayment(String payment) {
            this.payment = payment;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getCoin_name() {
            return coin_name;
        }

        public void setCoin_name(String coin_name) {
            this.coin_name = coin_name;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
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

        public PaymentListBean getPayment_list() {
            return payment_list;
        }

        public void setPayment_list(PaymentListBean payment_list) {
            this.payment_list = payment_list;
        }

        public String getCoin_logo() {
            return coin_logo;
        }

        public void setCoin_logo(String coin_logo) {
            this.coin_logo = coin_logo;
        }

        public static class PaymentListBean {
            /**
             * alipay :  支付宝
             * wechat :  微信支付
             */

            private String alipay;
            private String wechat;

            public String getAlipay() {
                return alipay;
            }

            public void setAlipay(String alipay) {
                this.alipay = alipay;
            }

            public String getWechat() {
                return wechat;
            }

            public void setWechat(String wechat) {
                this.wechat = wechat;
            }
        }
    }
}
