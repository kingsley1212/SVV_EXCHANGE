package com.svv.jys.code.common.entity;

import com.svv.jys.code.common.base.BaseEntity;

import java.util.List;

public class SubEntity extends BaseEntity{


    /**
     * in_tips : {"zh_tw":"","zh_cn":""}
     * pending : 0.00
     * block_link :
     * description : {"zh_tw":"","zh_cn":""}
     * in_fee : 0.00
     * type : 7
     * convert_min_limit : 10
     * sell_max : 0.00
     * ext_release : {"num":["0.000004","0.000000054"],"coin":["usdt","eth"]}
     * is_outfee : 0
     * public :
     * hold_number : 100
     * is_area : 0
     * ext_data : {"lang":["full_name","description","out_tips","in_tips","c2c_tips"],"full_name":{"zh_cn":"EOS","zh_tw":"EOS"},"description":{"zh_cn":"","zh_tw":""},"out_tips":{"zh_cn":"","zh_tw":""},"in_tips":{"zh_cn":"","zh_tw":""},"is_convert":"1","convert_coin":"usdt","convert_price":"10","convert_min_limit":"10","convert_max_limit":"100","is_hold":"1","hold_number":"100","ext_release":{"coin":["usdt","eth"],"num":["0.000004","0.000000054"]},"c2c_tips":{"zh_cn":"","zh_tw":""}}
     * convert_max_limit : 100
     * is_out : 1
     * convert_price : 10
     * logo :
     * course :
     * convert_rate : 0.10000000
     * id : 8
     * lang : ["full_name","description","out_tips","in_tips","c2c_tips"]
     * out_fee : 0.01000000
     * info :
     * website :
     * is_hold : 1
     * is_in : 1
     * out_min : 0.01000000
     * usdt_price : 0.00000000
     * sort : 255
     * confirmations : 0
     * c2c_tips : {"zh_tw":"","zh_cn":""}
     * full_name : {"zh_tw":"EOS","zh_cn":"EOS"}
     * convert_coin : usdt
     * out_tips : {"zh_tw":"","zh_cn":""}
     * name : eos
     * out_max : 100000.00000000
     * pay_fee :
     * is_convert : 1
     * add_time : 1555653266
     * is_plat : 0
     * white_paper :
     * status : 1
     */

    private InTipsBean in_tips;
    private String pending;
    private String block_link;
    private DescriptionBean description;
    private String in_fee;
    private String type;
    private String convert_min_limit;
    private String sell_max;
    private ExtReleaseBean ext_release;
    private String is_outfee;
    private String publicX;
    private String hold_number;
    private String is_area;
    private String ext_data;
    private String convert_max_limit;
    private String is_out;
    private String convert_price;
    private String logo;
    private String course;
    private String convert_rate;
    private String id;
    private String out_fee;
    private String info;
    private String website;
    private String is_hold;
    private String is_in;
    private String out_min;
    private String usdt_price;
    private String sort;
    private String confirmations;
    private C2cTipsBean c2c_tips;
    private FullNameBean full_name;
    private String convert_coin;
    private OutTipsBean out_tips;
    private String name;
    private String out_max;
    private String pay_fee;
    private String is_convert;
    private String add_time;
    private String is_plat;
    private String white_paper;
    private String status;
    private List<String> lang;

    public InTipsBean getIn_tips() {
        return in_tips;
    }

    public void setIn_tips(InTipsBean in_tips) {
        this.in_tips = in_tips;
    }

    public String getPending() {
        return pending;
    }

    public void setPending(String pending) {
        this.pending = pending;
    }

    public String getBlock_link() {
        return block_link;
    }

    public void setBlock_link(String block_link) {
        this.block_link = block_link;
    }

    public DescriptionBean getDescription() {
        return description;
    }

    public void setDescription(DescriptionBean description) {
        this.description = description;
    }

    public String getIn_fee() {
        return in_fee;
    }

    public void setIn_fee(String in_fee) {
        this.in_fee = in_fee;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getConvert_min_limit() {
        return convert_min_limit;
    }

    public void setConvert_min_limit(String convert_min_limit) {
        this.convert_min_limit = convert_min_limit;
    }

    public String getSell_max() {
        return sell_max;
    }

    public void setSell_max(String sell_max) {
        this.sell_max = sell_max;
    }

    public ExtReleaseBean getExt_release() {
        return ext_release;
    }

    public void setExt_release(ExtReleaseBean ext_release) {
        this.ext_release = ext_release;
    }

    public String getIs_outfee() {
        return is_outfee;
    }

    public void setIs_outfee(String is_outfee) {
        this.is_outfee = is_outfee;
    }

    public String getPublicX() {
        return publicX;
    }

    public void setPublicX(String publicX) {
        this.publicX = publicX;
    }

    public String getHold_number() {
        return hold_number;
    }

    public void setHold_number(String hold_number) {
        this.hold_number = hold_number;
    }

    public String getIs_area() {
        return is_area;
    }

    public void setIs_area(String is_area) {
        this.is_area = is_area;
    }

    public String getExt_data() {
        return ext_data;
    }

    public void setExt_data(String ext_data) {
        this.ext_data = ext_data;
    }

    public String getConvert_max_limit() {
        return convert_max_limit;
    }

    public void setConvert_max_limit(String convert_max_limit) {
        this.convert_max_limit = convert_max_limit;
    }

    public String getIs_out() {
        return is_out;
    }

    public void setIs_out(String is_out) {
        this.is_out = is_out;
    }

    public String getConvert_price() {
        return convert_price;
    }

    public void setConvert_price(String convert_price) {
        this.convert_price = convert_price;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getConvert_rate() {
        return convert_rate;
    }

    public void setConvert_rate(String convert_rate) {
        this.convert_rate = convert_rate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOut_fee() {
        return out_fee;
    }

    public void setOut_fee(String out_fee) {
        this.out_fee = out_fee;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getIs_hold() {
        return is_hold;
    }

    public void setIs_hold(String is_hold) {
        this.is_hold = is_hold;
    }

    public String getIs_in() {
        return is_in;
    }

    public void setIs_in(String is_in) {
        this.is_in = is_in;
    }

    public String getOut_min() {
        return out_min;
    }

    public void setOut_min(String out_min) {
        this.out_min = out_min;
    }

    public String getUsdt_price() {
        return usdt_price;
    }

    public void setUsdt_price(String usdt_price) {
        this.usdt_price = usdt_price;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getConfirmations() {
        return confirmations;
    }

    public void setConfirmations(String confirmations) {
        this.confirmations = confirmations;
    }

    public C2cTipsBean getC2c_tips() {
        return c2c_tips;
    }

    public void setC2c_tips(C2cTipsBean c2c_tips) {
        this.c2c_tips = c2c_tips;
    }

    public FullNameBean getFull_name() {
        return full_name;
    }

    public void setFull_name(FullNameBean full_name) {
        this.full_name = full_name;
    }

    public String getConvert_coin() {
        return convert_coin;
    }

    public void setConvert_coin(String convert_coin) {
        this.convert_coin = convert_coin;
    }

    public OutTipsBean getOut_tips() {
        return out_tips;
    }

    public void setOut_tips(OutTipsBean out_tips) {
        this.out_tips = out_tips;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOut_max() {
        return out_max;
    }

    public void setOut_max(String out_max) {
        this.out_max = out_max;
    }

    public String getPay_fee() {
        return pay_fee;
    }

    public void setPay_fee(String pay_fee) {
        this.pay_fee = pay_fee;
    }

    public String getIs_convert() {
        return is_convert;
    }

    public void setIs_convert(String is_convert) {
        this.is_convert = is_convert;
    }

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }

    public String getIs_plat() {
        return is_plat;
    }

    public void setIs_plat(String is_plat) {
        this.is_plat = is_plat;
    }

    public String getWhite_paper() {
        return white_paper;
    }

    public void setWhite_paper(String white_paper) {
        this.white_paper = white_paper;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getLang() {
        return lang;
    }

    public void setLang(List<String> lang) {
        this.lang = lang;
    }

    public static class InTipsBean extends BaseEntity {
        /**
         * zh_tw :
         * zh_cn :
         */

        private String zh_tw;
        private String zh_cn;

        public String getZh_tw() {
            return zh_tw;
        }

        public void setZh_tw(String zh_tw) {
            this.zh_tw = zh_tw;
        }

        public String getZh_cn() {
            return zh_cn;
        }

        public void setZh_cn(String zh_cn) {
            this.zh_cn = zh_cn;
        }
    }

    public static class DescriptionBean extends BaseEntity {
        /**
         * zh_tw :
         * zh_cn :
         */

        private String zh_tw;
        private String zh_cn;

        public String getZh_tw() {
            return zh_tw;
        }

        public void setZh_tw(String zh_tw) {
            this.zh_tw = zh_tw;
        }

        public String getZh_cn() {
            return zh_cn;
        }

        public void setZh_cn(String zh_cn) {
            this.zh_cn = zh_cn;
        }
    }

    public static class ExtReleaseBean extends BaseEntity{
        private List<String> num;
        private List<String> coin;

        public List<String> getNum() {
            return num;
        }

        public void setNum(List<String> num) {
            this.num = num;
        }

        public List<String> getCoin() {
            return coin;
        }

        public void setCoin(List<String> coin) {
            this.coin = coin;
        }
    }

    public static class C2cTipsBean extends BaseEntity {
        /**
         * zh_tw :
         * zh_cn :
         */

        private String zh_tw;
        private String zh_cn;

        public String getZh_tw() {
            return zh_tw;
        }

        public void setZh_tw(String zh_tw) {
            this.zh_tw = zh_tw;
        }

        public String getZh_cn() {
            return zh_cn;
        }

        public void setZh_cn(String zh_cn) {
            this.zh_cn = zh_cn;
        }
    }

    public static class FullNameBean extends BaseEntity{
        /**
         * zh_tw : EOS
         * zh_cn : EOS
         */

        private String zh_tw;
        private String zh_cn;

        public String getZh_tw() {
            return zh_tw;
        }

        public void setZh_tw(String zh_tw) {
            this.zh_tw = zh_tw;
        }

        public String getZh_cn() {
            return zh_cn;
        }

        public void setZh_cn(String zh_cn) {
            this.zh_cn = zh_cn;
        }
    }

    public static class OutTipsBean extends BaseEntity{
        /**
         * zh_tw :
         * zh_cn :
         */

        private String zh_tw;
        private String zh_cn;

        public String getZh_tw() {
            return zh_tw;
        }

        public void setZh_tw(String zh_tw) {
            this.zh_tw = zh_tw;
        }

        public String getZh_cn() {
            return zh_cn;
        }

        public void setZh_cn(String zh_cn) {
            this.zh_cn = zh_cn;
        }
    }
}
