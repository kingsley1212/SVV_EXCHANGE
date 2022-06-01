package com.svv.jys.code.common.entity;


import com.svv.jys.code.common.base.BaseEntity;

/**
 * Created by lzj on 2017/11/10.
 */

public class FCoinType extends BaseEntity {

    public String title;
    public String shortname;
    public String fullname;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
}
