package com.svv.jys.code.common.entity;

import com.svv.jys.code.common.base.BaseEntity;

import java.util.List;

/**
 * Created by Administrator on 2018/5/14 0014.
 */

public class FUserInfoEntity extends BaseEntity {

    /**
     * country : 1
     * security_degree : 2
     * robot_coin : {"eos":"1","btc":"1","eth":"1","usdt":"1"}
     * open_id : 0
     * collect_market : ["eos_usdt","btc_usdt","eth_usdt"]
     * user_name : 13512345678
     * invite_value : 0.02146000
     * pid : 0
     * is_shoper : 1
     * pwd_time : 1557736121
     * is_google : 0
     * true_name : 红
     * surname : 李
     * reg_time : 1553179765
     * verify_google : 0
     * rank : 0
     * logo : upload/user/201905/cfd89ce4bc5822e8656adae7244e021d.JPG
     * id : 1
     * verify_email : 0
     * email : 123@qq.com
     * pwd_type : 1
     * area_code : 86
     * mobile : 13512345678
     * is_identity : 1
     * can_cash : 0
     * token : f9ba0cec84d125071f82a2ebe01de347
     * user_id : 1
     * nick_name : 梦幻帝国
     * verify_mobile : 1
     * idcard : yLCzPU5WP8LvpgerP4koy7C5IjleKMjXk5CT/0bvOMQ=
     * power_id : 0
     * reg_ip : 192.168.1.128
     * invite_code : QhU590
     * status : 1
     */

    private String country;
    private String security_degree;
    private boolean check_pwd;
    private String robot_coin;
    private String open_id;
    private String user_name;
    private String invite_value;
    private String pid;
    private String is_shoper;
    private String pwd_time;
    private String is_google;
    private String true_name;
    private String surname;
    private String reg_time;
    private String verify_google;
    private String rank;
    private String logo;
    private String id;
    private String verify_email;
    private String email;
    private String pwd_type;
    private String area_code;
    private String mobile;
    private String is_identity;
    private String can_cash;
    private String token;
    private String user_id;
    private String nick_name;
    private String verify_mobile;
    private String idcard;
    private String power_id;
    private String reg_ip;
    private String invite_code;
    private String status;
    private List<String> collect_market;

    public boolean isCheck_pwd() {
        return check_pwd;
    }

    public void setCheck_pwd(boolean check_pwd) {
        this.check_pwd = check_pwd;
    }

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

    public String getRobot_coin() {
        return robot_coin;
    }

    public void setRobot_coin(String robot_coin) {
        this.robot_coin = robot_coin;
    }

    public String getOpen_id() {
        return open_id;
    }

    public void setOpen_id(String open_id) {
        this.open_id = open_id;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getCollect_market() {
        return collect_market;
    }

    public void setCollect_market(List<String> collect_market) {
        this.collect_market = collect_market;
    }
}
