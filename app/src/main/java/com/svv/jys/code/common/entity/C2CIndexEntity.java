package com.svv.jys.code.common.entity;

import com.svv.jys.code.common.base.BaseEntity;

import java.util.List;

public class C2CIndexEntity extends BaseEntity {

    /**
     * coin_list : ["btc"]
     * coin : {"id":"5","name":"btc","logo":"upload/coin_logo/201902/262b2bd127aa4e2a1bb8199205c9c3e0.PNG","type":"0","pending":"100.00","sell_max":"100.00","out_fee":"10.00000000","fee_address":"","out_min":"1.00000000","out_max":"9999998.00000000","is_out":"1","in_fee":"0.00","is_in":"1","is_outfee":"1","add_time":"1551255177","status":"1","course":"","is_plat":"0","info":"","confirmations":"0","is_area":"1","website":"https://www.baidu.com","white_paper":"https://www.baidu.com","block_link":"https://www.baidu.com","public":"2019-01-01","pay_fee":"","ext_data":"","usdt_price":"0.00000000","lang":["full_name","description","out_tips","in_tips","c2c_tips"],"full_name":"BTC","description":"","out_tips":"123","in_tips":"321","is_c2c":"1","buy_price":"1","sell_price":"2","min_buy":"1","min_sell":"1","c2c_link":"47","c2c_tips":"121"}
     * bank_list : [{"id":"10","user_id":"1","bank_name":"农业","bank_no":"13246546","bank_user":"35","bank_address":"1354354","type_state":"0","add_time":"1554173790","status":"1","nickname":"1123","currency_code":"","is_default":"1","user_name":"13512345678"},{"id":"5","user_id":"1","bank_name":"工商银行","bank_no":"工商银行卡号","bank_user":"工商银行姓名","bank_address":"工商银行地址","type_state":"0","add_time":"1553821310","status":"1","nickname":"工商银行","currency_code":"","is_default":"0","user_name":"13512345678"}]
     */

    private CoinBean coin;
    private List<CoinListBean> coin_list;
    private List<BankListBean> bank_list;


    public CoinBean getCoin() {
        return coin;
    }

    public void setCoin(CoinBean coin) {
        this.coin = coin;
    }

    public List<CoinListBean> getCoin_list() {
        return coin_list;
    }

    public void setCoin_list(List<CoinListBean> coin_list) {
        this.coin_list = coin_list;
    }

    public List<BankListBean> getBank_list() {
        return bank_list;
    }

    public void setBank_list(List<BankListBean> bank_list) {
        this.bank_list = bank_list;
    }

    public static class CoinListBean{

        /**
         * name : btc
         * ext_data :
         */

        private String name;
        private String ext_data;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getExt_data() {
            return ext_data;
        }

        public void setExt_data(String ext_data) {
            this.ext_data = ext_data;
        }
    }

    public static class CoinBean {
        /**
         * id : 5
         * name : btc
         * logo : upload/coin_logo/201902/262b2bd127aa4e2a1bb8199205c9c3e0.PNG
         * type : 0
         * pending : 100.00
         * sell_max : 100.00
         * out_fee : 10.00000000
         * fee_address :
         * out_min : 1.00000000
         * out_max : 9999998.00000000
         * is_out : 1
         * in_fee : 0.00
         * is_in : 1
         * is_outfee : 1
         * add_time : 1551255177
         * status : 1
         * course :
         * is_plat : 0
         * info :
         * confirmations : 0
         * is_area : 1
         * website : https://www.baidu.com
         * white_paper : https://www.baidu.com
         * block_link : https://www.baidu.com
         * public : 2019-01-01
         * pay_fee :
         * ext_data :
         * usdt_price : 0.00000000
         * lang : ["full_name","description","out_tips","in_tips","c2c_tips"]
         * full_name : BTC
         * description :
         * out_tips : 123
         * in_tips : 321
         * is_c2c : 1
         * buy_price : 1
         * sell_price : 2
         * min_buy : 1
         * min_sell : 1
         * c2c_link : 47
         * c2c_tips : 121
         */

        private String id;
        private String name;
        private String logo;
        private String type;
        private String pending;
        private String sell_max;
        private String out_fee;
        private String fee_address;
        private String out_min;
        private String out_max;
        private String is_out;
        private String in_fee;
        private String is_in;
        private String is_outfee;
        private String add_time;
        private String status;
        private String course;
        private String is_plat;
        private String info;
        private String confirmations;
        private String is_area;
        private String website;
        private String white_paper;
        private String block_link;
        private String pay_fee;
        private String ext_data;
        private String usdt_price;
        private String full_name;
        private String description;
        private String out_tips;
        private String in_tips;
        private String is_c2c;
        private String buy_price;
        private String sell_price;
        private String min_buy;
        private String min_sell;
        private String c2c_link;
        private String c2c_tips;
        private List<String> lang;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getPending() {
            return pending;
        }

        public void setPending(String pending) {
            this.pending = pending;
        }

        public String getSell_max() {
            return sell_max;
        }

        public void setSell_max(String sell_max) {
            this.sell_max = sell_max;
        }

        public String getOut_fee() {
            return out_fee;
        }

        public void setOut_fee(String out_fee) {
            this.out_fee = out_fee;
        }

        public String getFee_address() {
            return fee_address;
        }

        public void setFee_address(String fee_address) {
            this.fee_address = fee_address;
        }

        public String getOut_min() {
            return out_min;
        }

        public void setOut_min(String out_min) {
            this.out_min = out_min;
        }

        public String getOut_max() {
            return out_max;
        }

        public void setOut_max(String out_max) {
            this.out_max = out_max;
        }

        public String getIs_out() {
            return is_out;
        }

        public void setIs_out(String is_out) {
            this.is_out = is_out;
        }

        public String getIn_fee() {
            return in_fee;
        }

        public void setIn_fee(String in_fee) {
            this.in_fee = in_fee;
        }

        public String getIs_in() {
            return is_in;
        }

        public void setIs_in(String is_in) {
            this.is_in = is_in;
        }

        public String getIs_outfee() {
            return is_outfee;
        }

        public void setIs_outfee(String is_outfee) {
            this.is_outfee = is_outfee;
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

        public String getCourse() {
            return course;
        }

        public void setCourse(String course) {
            this.course = course;
        }

        public String getIs_plat() {
            return is_plat;
        }

        public void setIs_plat(String is_plat) {
            this.is_plat = is_plat;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public String getConfirmations() {
            return confirmations;
        }

        public void setConfirmations(String confirmations) {
            this.confirmations = confirmations;
        }

        public String getIs_area() {
            return is_area;
        }

        public void setIs_area(String is_area) {
            this.is_area = is_area;
        }

        public String getWebsite() {
            return website;
        }

        public void setWebsite(String website) {
            this.website = website;
        }

        public String getWhite_paper() {
            return white_paper;
        }

        public void setWhite_paper(String white_paper) {
            this.white_paper = white_paper;
        }

        public String getBlock_link() {
            return block_link;
        }

        public void setBlock_link(String block_link) {
            this.block_link = block_link;
        }

        public String getPay_fee() {
            return pay_fee;
        }

        public void setPay_fee(String pay_fee) {
            this.pay_fee = pay_fee;
        }

        public String getExt_data() {
            return ext_data;
        }

        public void setExt_data(String ext_data) {
            this.ext_data = ext_data;
        }

        public String getUsdt_price() {
            return usdt_price;
        }

        public void setUsdt_price(String usdt_price) {
            this.usdt_price = usdt_price;
        }

        public String getFull_name() {
            return full_name;
        }

        public void setFull_name(String full_name) {
            this.full_name = full_name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getOut_tips() {
            return out_tips;
        }

        public void setOut_tips(String out_tips) {
            this.out_tips = out_tips;
        }

        public String getIn_tips() {
            return in_tips;
        }

        public void setIn_tips(String in_tips) {
            this.in_tips = in_tips;
        }

        public String getIs_c2c() {
            return is_c2c;
        }

        public void setIs_c2c(String is_c2c) {
            this.is_c2c = is_c2c;
        }

        public String getBuy_price() {
            return buy_price;
        }

        public void setBuy_price(String buy_price) {
            this.buy_price = buy_price;
        }

        public String getSell_price() {
            return sell_price;
        }

        public void setSell_price(String sell_price) {
            this.sell_price = sell_price;
        }

        public String getMin_buy() {
            return min_buy;
        }

        public void setMin_buy(String min_buy) {
            this.min_buy = min_buy;
        }

        public String getMin_sell() {
            return min_sell;
        }

        public void setMin_sell(String min_sell) {
            this.min_sell = min_sell;
        }

        public String getC2c_link() {
            return c2c_link;
        }

        public void setC2c_link(String c2c_link) {
            this.c2c_link = c2c_link;
        }

        public String getC2c_tips() {
            return c2c_tips;
        }

        public void setC2c_tips(String c2c_tips) {
            this.c2c_tips = c2c_tips;
        }

        public List<String> getLang() {
            return lang;
        }

        public void setLang(List<String> lang) {
            this.lang = lang;
        }
    }

    public static class BankListBean {
        /**
         * id : 10
         * user_id : 1
         * bank_name : 农业
         * bank_no : 13246546
         * bank_user : 35
         * bank_address : 1354354
         * type_state : 0
         * add_time : 1554173790
         * status : 1
         * nickname : 1123
         * currency_code :
         * is_default : 1
         * user_name : 13512345678
         */

        private String id;
        private String user_id;
        private String bank_name;
        private String bank_no;
        private String bank_user;
        private String bank_address;
        private String type_state;
        private String add_time;
        private String status;
        private String nickname;
        private String currency_code;
        private String is_default;
        private String user_name;

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

        public String getBank_user() {
            return bank_user;
        }

        public void setBank_user(String bank_user) {
            this.bank_user = bank_user;
        }

        public String getBank_address() {
            return bank_address;
        }

        public void setBank_address(String bank_address) {
            this.bank_address = bank_address;
        }

        public String getType_state() {
            return type_state;
        }

        public void setType_state(String type_state) {
            this.type_state = type_state;
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

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getCurrency_code() {
            return currency_code;
        }

        public void setCurrency_code(String currency_code) {
            this.currency_code = currency_code;
        }

        public String getIs_default() {
            return is_default;
        }

        public void setIs_default(String is_default) {
            this.is_default = is_default;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }
    }
}
