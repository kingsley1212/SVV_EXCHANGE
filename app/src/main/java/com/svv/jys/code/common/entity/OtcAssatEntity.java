package com.svv.jys.code.common.entity;

import com.svv.jys.code.common.base.BaseEntity;

import java.util.List;

/**
 * Created by js on 2018/6/7.
 */

public class OtcAssatEntity extends BaseEntity {

    /**
     * limit : 10
     * total : 3
     * offset : 0
     */

    private int limit;
    private String sum_total;
    private String cny_total;
    private int offset;
    private List<RowsBean> rows;

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getSum_total() {
        return sum_total;
    }

    public void setSum_total(String sum_total) {
        this.sum_total = sum_total;
    }

    public String getCny_total() {
        return cny_total;
    }

    public void setCny_total(String cny_total) {
        this.cny_total = cny_total;
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

    public static class RowsBean extends  BaseEntity{
        public String getCny_price() {
            return cny_price;
        }

        public void setCny_price(String cny_price) {
            this.cny_price = cny_price;
        }

        /**
         * is_area : 0
         * out_fee : 0.00
         * logo : http://192.168.1.127/digital_huobi/public/static/upload/coin_logo/201805/1ff12a2d9b2ed05cd87c019931b10400.PNG
         * confirmations : 6
         * out_min : 0.00000000
         * status : 1
         * out_max : 0.00000000
         * in_fee : 0.00
         * is_in : 0
         * type : 0
         * chinese : 比特币
         * info :
         * buy_price : 1.00
         * course :
         * id : 2
         * is_plat : 0
         * is_out : 0
         * sell_max : 0.00
         * freeze : 0.00000000
         * able : 991.77723972
         * description : &lt;p&gt;&amp;nbsp; &amp;nbsp; &amp;nbsp;111&lt;/p&gt;
         * name : btc
         * add_time : 1521099151
         * english : BTC
         */
        private String cny_price;
        private String is_area;
        private String out_fee;
        private String logo;
        private String confirmations;
        private String out_min;
        private String status;
        private String out_max;
        private String in_fee;
        private String is_in;
        private String type;
        private String chinese;
        private String info;
        private String buy_price;
        private String course;
        private String id;
        private String is_plat;
        private String is_out;
        private String sell_max;
        private String freeze;
        private String able;
        private String description;
        private String name;
        private String add_time;
        private String english;

        public String getIs_area() {
            return is_area;
        }

        public void setIs_area(String is_area) {
            this.is_area = is_area;
        }

        public String getOut_fee() {
            return out_fee;
        }

        public void setOut_fee(String out_fee) {
            this.out_fee = out_fee;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getConfirmations() {
            return confirmations;
        }

        public void setConfirmations(String confirmations) {
            this.confirmations = confirmations;
        }

        public String getOut_min() {
            return out_min;
        }

        public void setOut_min(String out_min) {
            this.out_min = out_min;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getOut_max() {
            return out_max;
        }

        public void setOut_max(String out_max) {
            this.out_max = out_max;
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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getChinese() {
            return chinese;
        }

        public void setChinese(String chinese) {
            this.chinese = chinese;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public String getBuy_price() {
            return buy_price;
        }

        public void setBuy_price(String buy_price) {
            this.buy_price = buy_price;
        }

        public String getCourse() {
            return course;
        }

        public void setCourse(String course) {
            this.course = course;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIs_plat() {
            return is_plat;
        }

        public void setIs_plat(String is_plat) {
            this.is_plat = is_plat;
        }

        public String getIs_out() {
            return is_out;
        }

        public void setIs_out(String is_out) {
            this.is_out = is_out;
        }

        public String getSell_max() {
            return sell_max;
        }

        public void setSell_max(String sell_max) {
            this.sell_max = sell_max;
        }

        public String getFreeze() {
            return freeze;
        }

        public void setFreeze(String freeze) {
            this.freeze = freeze;
        }

        public String getAble() {
            return able;
        }

        public void setAble(String able) {
            this.able = able;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getEnglish() {
            return english;
        }

        public void setEnglish(String english) {
            this.english = english;
        }
    }
}
