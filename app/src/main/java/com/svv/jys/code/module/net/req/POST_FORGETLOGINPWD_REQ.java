package com.svv.jys.code.module.net.req;


import com.svv.jys.code.common.base.net.BaseRequest;

import java.util.Map;

/**
 * Created by LB on 2017/12/14.
 */

public class POST_FORGETLOGINPWD_REQ extends BaseRequest {
    public String category;
    public String account;
    public String code;
    public String password;
    public String password_sec;
    public String type;

    @Override
    public Map<String, String> bulitReqMap() {
        Map map = super.bulitReqMap();
        map.put("account", account);
        map.put("code", code);
        map.put("password", password);
        map.put("password_sec", password_sec);
        map.put("type", type);
        map.put("category", category);
        return map;
    }

}
