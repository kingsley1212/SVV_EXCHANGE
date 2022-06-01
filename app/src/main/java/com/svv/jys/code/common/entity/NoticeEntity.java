package com.svv.jys.code.common.entity;

import com.svv.jys.code.common.base.BaseEntity;

/**
 * Created by js on 2018/5/17.
 */

public class NoticeEntity extends BaseEntity{
    private String add_time;

    private String title;

    private String id;

    public void setAdd_time(String add_time){
        this.add_time = add_time;
    }
    public String getAdd_time(){
        return this.add_time;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return this.title;
    }
    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return this.id;
    }
}
