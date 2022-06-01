package com.svv.jys.code.common.entity;

import com.svv.jys.code.common.base.BaseEntity;

/**
 * Created by js on 2018/5/21.
 */

public class CountryEntity extends BaseEntity{
    public CountryEntity(String name, String code, String chinese, String id) {
        this.name = name;
        this.code = code;
        this.chinese = chinese;
        this.id = id;
    }

    public CountryEntity() {
    }

    private String name;

    private String code;

    private String chinese;

    private String id;

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    private String first_name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setCode(String code){
        this.code = code;
    }
    public String getCode(){
        return this.code;
    }
    public void setChinese(String chinese){
        this.chinese = chinese;
    }
    public String getChinese(){
        return this.chinese;
    }
}
