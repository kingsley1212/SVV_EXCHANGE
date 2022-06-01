package com.svv.jys.code.common.entity;

import com.svv.jys.code.common.base.BaseEntity;

import java.io.Serializable;

public class NewIdentifyEntity extends BaseEntity implements Serializable {

    /**
     * country : 1
     * able : true
     * area_code : 86
     * country_info : {"chinese":"中国","code":"86","name":"China","id":"1","type":"1","status":"1"}
     * identity_info : {"reason":"132","address":"","pic_3":"http://192.168.1.118/yaf_digital_v1/public/static/http://192.168.1.118/yaf_digital_v1/public/static/upload/identity/201903/e7cc857bd7a39edca07964621890d8ce.JPG","city":"","pic_2":"http://192.168.1.118/yaf_digital_v1/public/static/http://192.168.1.118/yaf_digital_v1/public/static/upload/identity/201903/3ce34405d4986c9ba556b5405bf2762a.JPG","pic_1":"http://192.168.1.118/yaf_digital_v1/public/static/http://192.168.1.118/yaf_digital_v1/public/static/upload/identity/201903/6368e3f68c9fe8e64c150a7143147441.JPG","type":"0","user_id":"4","true_name":"杨","surname":"杨","idcard":"360732199910060419","id":"3","add_time":"1553827785","status":"2","check_time":"1553828013"}
     * tips : 您未能通过实名认证，原因是：132。
     * key : mobile
     */

    private String country;
    private String able;
    private String area_code;
    private CountryInfoBean country_info;
    private IdentityInfoBean identity_info;
    private String tips;
    private String key;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAble() {
        return able;
    }

    public void setAble(String able) {
        this.able = able;
    }

    public String getArea_code() {
        return area_code;
    }

    public void setArea_code(String area_code) {
        this.area_code = area_code;
    }

    public CountryInfoBean getCountry_info() {
        return country_info;
    }

    public void setCountry_info(CountryInfoBean country_info) {
        this.country_info = country_info;
    }

    public IdentityInfoBean getIdentity_info() {
        return identity_info;
    }

    public void setIdentity_info(IdentityInfoBean identity_info) {
        this.identity_info = identity_info;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public static class CountryInfoBean implements Serializable{
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

    public static class IdentityInfoBean implements Serializable{
        /**
         * reason : 132
         * address :
         * pic_3 : http://192.168.1.118/yaf_digital_v1/public/static/http://192.168.1.118/yaf_digital_v1/public/static/upload/identity/201903/e7cc857bd7a39edca07964621890d8ce.JPG
         * city :
         * pic_2 : http://192.168.1.118/yaf_digital_v1/public/static/http://192.168.1.118/yaf_digital_v1/public/static/upload/identity/201903/3ce34405d4986c9ba556b5405bf2762a.JPG
         * pic_1 : http://192.168.1.118/yaf_digital_v1/public/static/http://192.168.1.118/yaf_digital_v1/public/static/upload/identity/201903/6368e3f68c9fe8e64c150a7143147441.JPG
         * type : 0
         * user_id : 4
         * true_name : 杨
         * surname : 杨
         * idcard : 360732199910060419
         * id : 3
         * add_time : 1553827785
         * status : 2
         * check_time : 1553828013
         */

        private String reason;
        private String address;
        private String pic_3;
        private String city;
        private String pic_2;
        private String pic_1;
        private String pic1;
        private String pic2;

        public String getPic1() {
            return pic1;
        }

        public void setPic1(String pic1) {
            this.pic1 = pic1;
        }

        public String getPic2() {
            return pic2;
        }

        public void setPic2(String pic2) {
            this.pic2 = pic2;
        }

        public String getPic3() {
            return pic3;
        }

        public void setPic3(String pic3) {
            this.pic3 = pic3;
        }

        private String pic3;
        private String type;
        private String user_id;
        private String true_name;
        private String surname;
        private String idcard;
        private String id;
        private String add_time;
        private String status;
        private String check_time;

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPic_3() {
            return pic_3;
        }

        public void setPic_3(String pic_3) {
            this.pic_3 = pic_3;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getPic_2() {
            return pic_2;
        }

        public void setPic_2(String pic_2) {
            this.pic_2 = pic_2;
        }

        public String getPic_1() {
            return pic_1;
        }

        public void setPic_1(String pic_1) {
            this.pic_1 = pic_1;
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

        public String getIdcard() {
            return idcard;
        }

        public void setIdcard(String idcard) {
            this.idcard = idcard;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public String getCheck_time() {
            return check_time;
        }

        public void setCheck_time(String check_time) {
            this.check_time = check_time;
        }
    }
}
