package com.svv.jys.code.common.entity;

import com.svv.jys.code.common.base.BaseEntity;

/**
 * Created by js on 2018/6/19.
 */

public class AddPayWxEntity extends BaseEntity{
    public String realname;
    public String account;
    public String pic;
    public AddPayWxEntity(String realname, String account,String pic) {
        this.realname = realname;
        this.account = account;
        this.pic=pic;
    }


}
