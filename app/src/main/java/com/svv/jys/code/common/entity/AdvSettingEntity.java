package com.svv.jys.code.common.entity;

import com.svv.jys.code.common.base.BaseEntity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by js on 2018/7/25.
 */

public class AdvSettingEntity extends BaseEntity implements Serializable{


    /**
     * income : {"rows":[{"code":"alipay","name":"支付宝","id":"1","type":"1"},{"code":"bank","name":"银行转账","id":"2","type":"0"},{"code":"wechat","name":"微信支付","id":"5","type":"1"}]}
     * country : [{"chinese":"中国","code":"86","name":"China","id":"1","type":"1","status":"1"},{"chinese":"加拿大","code":"1","name":"Canada","id":"2","type":"0","status":"1"},{"chinese":"美国","code":"1","name":"United States","id":"3","type":"0","status":"1"},{"chinese":"安提瓜和巴布达","code":"1268","name":"Antigua and Barbuda","id":"6","type":"0","status":"1"},{"chinese":"中国香港","code":"852","name":"Hong Kong","id":"247","type":"1","status":"1"},{"chinese":"中国澳门","code":"853","name":"Macao","id":"248","type":"1","status":"1"},{"chinese":"中国台湾","code":"886","name":"Taiwan","id":"249","type":"1","status":"1"}]
     * price : 27.61
     * r_country : {"chinese":"中国","code":"86","name":"China","id":"1","type":"1","status":"1"}
     * otc_setting : {"adv_cancel":"100","business":"1","deal_c_idcard":"on","min_amount":"100","max_amount":"1000","ad_time":"90","deal_fee":"10","open_ad":"1","adv_limit":"100","ad_num":"10","ad_c_idcard":"on","pay_time":"30|45"}
     * currency : [{"country":"中国","is_open":"1","first_char":"C","id":"32","name_cn_ft":"人民幣","currency_code":"CNY","name_en":"Chinese Yuan","name_cn_jt":"人民币"},{"country":"英国","is_open":"1","first_char":"G","id":"48","name_cn_ft":"英鎊","currency_code":"GBP","name_en":"British Pound","name_cn_jt":"英镑"},{"country":"香港","is_open":"1","first_char":"H","id":"56","name_cn_ft":"港幣","currency_code":"HKD","name_en":"Hong Kong Dollar","name_cn_jt":"港币"},{"country":"台湾","is_open":"1","first_char":"T","id":"140","name_cn_ft":"新台幣","currency_code":"TWD","name_en":"New Taiwan Dollar","name_cn_jt":"新台币"},{"country":"美国","is_open":"1","first_char":"U","id":"144","name_cn_ft":"美元","currency_code":"USD","name_en":"United States Dollar","name_cn_jt":"美元"}]
     * r_currency : {"country":"中国","is_open":"1","first_char":"C","id":"32","name_cn_ft":"人民幣","currency_code":"CNY","name_en":"Chinese Yuan","name_cn_jt":"人民币"}
     * pay_times : [{"name":"30分钟","value":30},{"name":"45分钟","value":45}]
     * otc_coin : ["btc","eth"]
     * user : []
     * coin : btc
     * notice : 0
     */

    private IncomeBean income;
    private String price;
    private RCountryBean r_country;
    private OtcSettingBean otc_setting;
    private RCurrencyBean r_currency;
    private String coin;
    private String notice;
    private List<CountryBean> country;
    private List<CurrencyBean> currency;
    private List<PayTimesBean> pay_times;
    private List<String> otc_coin;
    private UserBean user;

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public static class UserBean implements Serializable{
        private String is_shoper;

        public String getIs_shoper() {
            return is_shoper;
        }

        public void setIs_shoper(String is_shoper) {
            this.is_shoper = is_shoper;
        }
    }

    public IncomeBean getIncome() {
        return income;
    }

    public void setIncome(IncomeBean income) {
        this.income = income;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public RCountryBean getR_country() {
        return r_country;
    }

    public void setR_country(RCountryBean r_country) {
        this.r_country = r_country;
    }

    public OtcSettingBean getOtc_setting() {
        return otc_setting;
    }

    public void setOtc_setting(OtcSettingBean otc_setting) {
        this.otc_setting = otc_setting;
    }

    public RCurrencyBean getR_currency() {
        return r_currency;
    }

    public void setR_currency(RCurrencyBean r_currency) {
        this.r_currency = r_currency;
    }

    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public List<CountryBean> getCountry() {
        return country;
    }

    public void setCountry(List<CountryBean> country) {
        this.country = country;
    }

    public List<CurrencyBean> getCurrency() {
        return currency;
    }

    public void setCurrency(List<CurrencyBean> currency) {
        this.currency = currency;
    }

    public List<PayTimesBean> getPay_times() {
        return pay_times;
    }

    public void setPay_times(List<PayTimesBean> pay_times) {
        this.pay_times = pay_times;
    }

    public List<String> getOtc_coin() {
        return otc_coin;
    }

    public void setOtc_coin(List<String> otc_coin) {
        this.otc_coin = otc_coin;
    }

    public static class IncomeBean implements Serializable{
        private List<RowsBean> rows;

        public List<RowsBean> getRows() {
            return rows;
        }

        public void setRows(List<RowsBean> rows) {
            this.rows = rows;
        }

        public static class RowsBean implements  Serializable{
            /**
             * code : alipay
             * name : 支付宝
             * id : 1
             * type : 1
             */

            private String code;
            private String name;
            private String id;
            private String type;
            private boolean select;

            public boolean isSelect() {
                return select;
            }

            public void setSelect(boolean select) {
                this.select = select;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
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
        }
    }

    public static class RCountryBean implements Serializable{
        /**
         * chinese : 中国
         * code : 86
         * name : China
         * id : 1
         * type : 1
         * status : 1
         */

        private String chinese;
        private String code;
        private String name;
        private String id;
        private String type;
        private String status;

        public String getChinese() {
            return chinese;
        }

        public void setChinese(String chinese) {
            this.chinese = chinese;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }

    public static class OtcSettingBean implements Serializable{
        /**
         * adv_cancel : 100
         * business : 1
         * deal_c_idcard : on
         * min_amount : 100
         * max_amount : 1000
         * ad_time : 90
         * deal_fee : 10
         * open_ad : 1
         * adv_limit : 100
         * ad_num : 10
         * ad_c_idcard : on
         * pay_time : 30|45
         */

        private String adv_cancel;
        private String business;
        private String deal_c_idcard;
        private String min_amount;
        private String max_amount;
        private String ad_time;
        private String deal_fee;
        private String open_ad;
        private String adv_limit;
        private String ad_num;
        private String ad_c_idcard;
        private String pay_time;

        public String getAdv_cancel() {
            return adv_cancel;
        }

        public void setAdv_cancel(String adv_cancel) {
            this.adv_cancel = adv_cancel;
        }

        public String getBusiness() {
            return business;
        }

        public void setBusiness(String business) {
            this.business = business;
        }

        public String getDeal_c_idcard() {
            return deal_c_idcard;
        }

        public void setDeal_c_idcard(String deal_c_idcard) {
            this.deal_c_idcard = deal_c_idcard;
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

        public String getAd_time() {
            return ad_time;
        }

        public void setAd_time(String ad_time) {
            this.ad_time = ad_time;
        }

        public String getDeal_fee() {
            return deal_fee;
        }

        public void setDeal_fee(String deal_fee) {
            this.deal_fee = deal_fee;
        }

        public String getOpen_ad() {
            return open_ad;
        }

        public void setOpen_ad(String open_ad) {
            this.open_ad = open_ad;
        }

        public String getAdv_limit() {
            return adv_limit;
        }

        public void setAdv_limit(String adv_limit) {
            this.adv_limit = adv_limit;
        }

        public String getAd_num() {
            return ad_num;
        }

        public void setAd_num(String ad_num) {
            this.ad_num = ad_num;
        }

        public String getAd_c_idcard() {
            return ad_c_idcard;
        }

        public void setAd_c_idcard(String ad_c_idcard) {
            this.ad_c_idcard = ad_c_idcard;
        }

        public String getPay_time() {
            return pay_time;
        }

        public void setPay_time(String pay_time) {
            this.pay_time = pay_time;
        }
    }

    public static class RCurrencyBean implements Serializable{
        /**
         * country : 中国
         * is_open : 1
         * first_char : C
         * id : 32
         * name_cn_ft : 人民幣
         * currency_code : CNY
         * name_en : Chinese Yuan
         * name_cn_jt : 人民币
         */

        private String country;
        private String is_open;
        private String first_char;
        private String id;
        private String name_cn_ft;
        private String currency_code;
        private String name_en;
        private String name_cn_jt;

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getIs_open() {
            return is_open;
        }

        public void setIs_open(String is_open) {
            this.is_open = is_open;
        }

        public String getFirst_char() {
            return first_char;
        }

        public void setFirst_char(String first_char) {
            this.first_char = first_char;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName_cn_ft() {
            return name_cn_ft;
        }

        public void setName_cn_ft(String name_cn_ft) {
            this.name_cn_ft = name_cn_ft;
        }

        public String getCurrency_code() {
            return currency_code;
        }

        public void setCurrency_code(String currency_code) {
            this.currency_code = currency_code;
        }

        public String getName_en() {
            return name_en;
        }

        public void setName_en(String name_en) {
            this.name_en = name_en;
        }

        public String getName_cn_jt() {
            return name_cn_jt;
        }

        public void setName_cn_jt(String name_cn_jt) {
            this.name_cn_jt = name_cn_jt;
        }
    }

    public static class CountryBean implements Serializable {
        /**
         * chinese : 中国
         * code : 86
         * name : China
         * id : 1
         * type : 1
         * status : 1
         */

        private String chinese;
        private String code;
        private String name;
        private String id;
        private String type;
        private String status;

        public String getChinese() {
            return chinese;
        }

        public void setChinese(String chinese) {
            this.chinese = chinese;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }

    public static class CurrencyBean implements Serializable{
        /**
         * country : 中国
         * is_open : 1
         * first_char : C
         * id : 32
         * name_cn_ft : 人民幣
         * currency_code : CNY
         * name_en : Chinese Yuan
         * name_cn_jt : 人民币
         */

        private String country;
        private String is_open;
        private String first_char;
        private String id;
        private String name_cn_ft;
        private String currency_code;
        private String name_en;
        private String name_cn_jt;

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getIs_open() {
            return is_open;
        }

        public void setIs_open(String is_open) {
            this.is_open = is_open;
        }

        public String getFirst_char() {
            return first_char;
        }

        public void setFirst_char(String first_char) {
            this.first_char = first_char;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName_cn_ft() {
            return name_cn_ft;
        }

        public void setName_cn_ft(String name_cn_ft) {
            this.name_cn_ft = name_cn_ft;
        }

        public String getCurrency_code() {
            return currency_code;
        }

        public void setCurrency_code(String currency_code) {
            this.currency_code = currency_code;
        }

        public String getName_en() {
            return name_en;
        }

        public void setName_en(String name_en) {
            this.name_en = name_en;
        }

        public String getName_cn_jt() {
            return name_cn_jt;
        }

        public void setName_cn_jt(String name_cn_jt) {
            this.name_cn_jt = name_cn_jt;
        }
    }

    public static class PayTimesBean implements Serializable{
        /**
         * name : 30分钟
         * value : 30
         */

        private String name;
        private String value;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
