package com.svv.jys.code.common.entity;

import com.svv.jys.code.common.base.BaseEntity;

/**
 * Created by js on 2018/6/16.
 */

public class AddPayReqEntity extends BaseEntity{
    public String bankaddress;
    public String bankname;
    public String realname;
    public String account;

    public AddPayReqEntity(String bankaddress, String account, String bankname, String realname) {
        this.bankaddress = bankaddress;
        this.account = account;
        this.bankname = bankname;
        this.realname = realname;
    }
}
