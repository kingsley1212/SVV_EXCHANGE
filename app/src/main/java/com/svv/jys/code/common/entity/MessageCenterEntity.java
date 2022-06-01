package com.svv.jys.code.common.entity;

import com.svv.jys.code.common.base.BaseEntity;

public class MessageCenterEntity extends BaseEntity {

    private String id;
    private String title;
    private String sec_title;
    private String add_time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSec_title() {
        return sec_title;
    }

    public void setSec_title(String sec_title) {
        this.sec_title = sec_title;
    }

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }
}
