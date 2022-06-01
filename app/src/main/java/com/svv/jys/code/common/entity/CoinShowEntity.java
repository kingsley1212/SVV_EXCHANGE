package com.svv.jys.code.common.entity;

import com.svv.jys.code.common.base.BaseEntity;

public class CoinShowEntity extends BaseEntity {

    /**
     * id : 32
     * currency_code : CNY
     * name_en : Chinese Yuan
     * name_cn_jt : 人民币
     * name_cn_ft : 人民幣
     * first_char : C
     * is_open : 1
     * country : 中国
     */

    private String id;
    private String currency_code;
    private String name_en;
    private String name_cn_jt;
    private String name_cn_ft;
    private String first_char;
    private String is_open;
    private String country;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getName_cn_ft() {
        return name_cn_ft;
    }

    public void setName_cn_ft(String name_cn_ft) {
        this.name_cn_ft = name_cn_ft;
    }

    public String getFirst_char() {
        return first_char;
    }

    public void setFirst_char(String first_char) {
        this.first_char = first_char;
    }

    public String getIs_open() {
        return is_open;
    }

    public void setIs_open(String is_open) {
        this.is_open = is_open;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
