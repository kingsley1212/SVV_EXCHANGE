package com.svv.jys.code.common.entity;

import com.svv.jys.code.common.base.BaseEntity;

/**
 * Created by js on 2018/5/21.
 */

public class AddressEntity extends BaseEntity{
    private String id;

    private String address_img;

    private String address;

    private String coin;

    private String name;

    private String user_id;

    private String add_time;

    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return this.id;
    }
    public void setAddress_img(String address_img){
        this.address_img = address_img;
    }
    public String getAddress_img(){
        return this.address_img;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public String getAddress(){
        return this.address;
    }
    public void setCoin(String coin){
        this.coin = coin;
    }
    public String getCoin(){
        return this.coin;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setUser_id(String user_id){
        this.user_id = user_id;
    }
    public String getUser_id(){
        return this.user_id;
    }
    public void setAdd_time(String add_time){
        this.add_time = add_time;
    }
    public String getAdd_time(){
        return this.add_time;
    }
}
