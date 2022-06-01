package com.svv.jys.code.common.entity;


import com.svv.jys.code.common.base.BaseEntity;

/**
 * Created by lzj on 2017/11/15.
 */

public class FToken extends BaseEntity {

    /**
     * uid : 1
     * key : 17ff5dd3b5205ce7366f0e4646a393f6
     * token : 4vg3vsal088jpv5kngat986b51
     */
    public String getVerify_key() {         return verify_key;     }      public void setVerify_key(String verify_key) {         this.verify_key = verify_key;     }      private String verify_key;
    private String uid;
    private String key;
    public String token;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
