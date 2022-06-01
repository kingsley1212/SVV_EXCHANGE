package com.svv.jys.code.common.entity;

import com.svv.jys.code.common.base.BaseEntity;

import java.util.List;

public class AdvInfoEntity extends BaseEntity {

    /**
     * income : {"rows":[{"code":"alipay","name":"支付宝","id":"1","type":"1"},{"code":"bank","name":"银行转账","id":"2","type":"0"},{"code":"wechat","name":"微信支付","id":"5","type":"1"}]}
     * country : {"1":{"chinese":"中国","code":"86","name":"China","id":"1","type":"1","status":"1"},"2":{"chinese":"加拿大","code":"1","name":"Canada","id":"2","type":"0","status":"1"},"3":{"chinese":"美国","code":"1","name":"United States","id":"3","type":"0","status":"1"},"246":{"chinese":"乌兹别克斯坦","code":"998","name":"Uzbekistan","id":"246","type":"0","status":"1"},"247":{"chinese":"中国香港","code":"852","name":"Hong Kong","id":"247","type":"1","status":"1"},"6":{"chinese":"安提瓜和巴布达","code":"1268","name":"Antigua and Barbuda","id":"6","type":"0","status":"1"},"248":{"chinese":"中国澳门","code":"853","name":"Macao","id":"248","type":"1","status":"1"},"249":{"chinese":"中国台湾","code":"886","name":"Taiwan","id":"249","type":"1","status":"1"}}
     * r_country : {"chinese":"中国","code":"86","name":"China","id":"1","type":"1","status":"1"}
     * currency : {"KRW":{"country":"韩国","is_open":"1","first_char":"K","id":"75","name_cn_ft":"韓元","currency_code":"KRW","name_en":"South Korean Won","name_cn_jt":"韩元"},"CNY":{"country":"中国","is_open":"1","first_char":"C","id":"32","name_cn_ft":"人民幣","currency_code":"CNY","name_en":"Chinese Yuan","name_cn_jt":"人民币"}}
     * pay_name : 支付宝
     * payment : {"alipay":"\u652f\u4ed8\u5b9d"}
     * r_currency : {"country":"中国","is_open":"1","first_char":"C","id":"32","name_cn_ft":"人民幣","currency_code":"CNY","name_en":"Chinese Yuan","name_cn_jt":"人民币"}
     * pay_times : {"45":"45分钟","60":"60分钟","30":"30分钟"}
     * otc_coin : {"btc":{"pending":"0.00","block_link":"https://www.baidu.com","in_fee":"0.00","type":"0","sell_max":"0.00","is_outfee":"1","public":"2019-01-01","is_area":"0","ext_data":"{\"lang\":[\"full_name\",\"description\",\"out_tips\",\"in_tips\"],\"full_name\":{\"zh_cn\":\"BTC\",\"zh_tw\":\"BTC\"},\"description\":{\"zh_cn\":\"\\u6bd4\\u8f83\\u9002\\u5408\\u6267\\u884c\\u547d\\u4ee4\\u7684\\u573a\\u666f\\n\\u7136\\u540e\\u5728\\u5177\\u4f53\\u547d\\u4ee4\\u7c7b\\u4e2d\\u8bbe\\u5b9a\\u547d\\u4ee4\\u63a5\\u6536\\u8005\\n\\u53ef\\u4ee5\\u6d88\\u9664\\u547d\\u4ee4\\u53d1\\u9001\\u8005\\u548c\\u547d\\u4ee4\\u63a5\\u53d7\\u8005\\u4e4b\\u95f4\\u7684\\u8026\\u5408\\n\\u5e76\\u4e14\\u53ef\\u4ee5\\u65b9\\u4fbf\\u7684\\u6269\\u5c55\\u65b0\\u547d\\u4ee4\",\"zh_tw\":\"\"},\"out_tips\":{\"zh_cn\":\"123\",\"zh_tw\":\"\"},\"in_tips\":{\"zh_cn\":\"321\",\"zh_tw\":\"\"}}","is_out":"1","logo":"upload/coin_logo/201902/262b2bd127aa4e2a1bb8199205c9c3e0.PNG","course":"","id":"5","out_fee":"10.00000000","info":"","website":"https://www.baidu.com","is_in":"1","out_min":"1.00000000","usdt_price":"0.00000000","sort":"255","confirmations":"0","name":"btc","out_max":"9999998.00000000","pay_fee":"","add_time":"1551255177","is_plat":"0","white_paper":"https://www.baidu.com","status":"1"},"eth":{"pending":"0.00","block_link":"https://www.baidu.com","in_fee":"0.00","type":"0","sell_max":"0.00","is_outfee":"0","public":"2019-02-01","is_area":"0","ext_data":"{\"lang\":[\"full_name\",\"description\",\"out_tips\",\"in_tips\"],\"full_name\":{\"zh_cn\":\"ETH\",\"zh_tw\":\"ETH\"},\"description\":{\"zh_cn\":\"123123\",\"zh_tw\":\"\"},\"out_tips\":{\"zh_cn\":\"\",\"zh_tw\":\"\"},\"in_tips\":{\"zh_cn\":\"\",\"zh_tw\":\"\"}}","is_out":"0","logo":"upload/coin_logo/201902/7a6df4bc91b91f80dd15c6913e7e1efb.PNG","course":"","id":"6","out_fee":"0.00000000","info":"","website":"https://www.baidu.com","is_in":"0","out_min":"1.00000000","usdt_price":"0.00000000","sort":"255","confirmations":"0","name":"eth","out_max":"1000000.00000000","pay_fee":"","add_time":"1551255532","is_plat":"0","white_paper":"https://www.baidu.com","status":"1"},"usdt":{"pending":"0.00","block_link":"","in_fee":"0.00","type":"0","sell_max":"0.00","is_outfee":"0","public":"","is_area":"0","ext_data":"{\"lang\":[\"full_name\",\"description\",\"out_tips\",\"in_tips\"],\"full_name\":{\"zh_cn\":\"USDT\"},\"description\":{\"zh_cn\":\"\"},\"out_tips\":{\"zh_cn\":\"\"},\"in_tips\":{\"zh_cn\":\"\"}}","is_out":"0","logo":"upload/coin_logo/201904/24a6fb1f73e138e9428041a356256094.PNG","course":"","id":"9","out_fee":"1.00000000","info":"","website":"","is_in":"0","out_min":"0.10000000","usdt_price":"1.00000000","sort":"255","confirmations":"0","name":"usdt","out_max":"10000.00000000","pay_fee":"0.00001","add_time":"1555740896","is_plat":"0","white_paper":"","status":"1"}}
     * info : {"country":"1","min_amount":"20.00","num":"0.44984255","type":"0","message":"1","valuetion":"0","premium":"0.00","coin_name":"BTC","min_price":"0.00","user_id":"1","price":"66.69","max_amount":"30.00","currency":"CNY","payment":"{\"alipay\":\"\\u652f\\u4ed8\\u5b9d\"}","id":"22","prompt":"30","add_time":"1554116266","status":"0"}
     * coin : btc
     * pay_time : 30分钟
     */

    private IncomeBean income;
    private RCountryBean r_country;
    private String pay_name;
    private String payment;
    private RCurrencyBean r_currency;
    private InfoBean info;
    private String coin;
    private String pay_time;

    public IncomeBean getIncome() {
        return income;
    }

    public void setIncome(IncomeBean income) {
        this.income = income;
    }

    public RCountryBean getR_country() {
        return r_country;
    }

    public void setR_country(RCountryBean r_country) {
        this.r_country = r_country;
    }

    public String getPay_name() {
        return pay_name;
    }

    public void setPay_name(String pay_name) {
        this.pay_name = pay_name;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public RCurrencyBean getR_currency() {
        return r_currency;
    }

    public void setR_currency(RCurrencyBean r_currency) {
        this.r_currency = r_currency;
    }

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }

    public String getPay_time() {
        return pay_time;
    }

    public void setPay_time(String pay_time) {
        this.pay_time = pay_time;
    }

    public static class IncomeBean {
        private List<RowsBean> rows;

        public List<RowsBean> getRows() {
            return rows;
        }

        public void setRows(List<RowsBean> rows) {
            this.rows = rows;
        }

        public static class RowsBean {
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



    public static class RCountryBean {
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


    public static class RCurrencyBean {
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

    public static class InfoBean {
        /**
         * country : 1
         * min_amount : 20.00
         * num : 0.44984255
         * type : 0
         * message : 1
         * valuetion : 0
         * premium : 0.00
         * coin_name : BTC
         * min_price : 0.00
         * user_id : 1
         * price : 66.69
         * max_amount : 30.00
         * currency : CNY
         * payment : {"alipay":"\u652f\u4ed8\u5b9d"}
         * id : 22
         * prompt : 30
         * add_time : 1554116266
         * status : 0
         */

        private String country;
        private String min_amount;
        private String num;
        private String type;
        private String message;
        private String valuetion;
        private String premium;
        private String coin_name;
        private String min_price;
        private String user_id;
        private String price;
        private String max_amount;
        private String currency;
        private String payment;
        private String id;
        private String prompt;
        private String add_time;
        private String status;

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getMin_amount() {
            return min_amount;
        }

        public void setMin_amount(String min_amount) {
            this.min_amount = min_amount;
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

        public String getMin_price() {
            return min_price;
        }

        public void setMin_price(String min_price) {
            this.min_price = min_price;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getMax_amount() {
            return max_amount;
        }

        public void setMax_amount(String max_amount) {
            this.max_amount = max_amount;
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
    }
}
