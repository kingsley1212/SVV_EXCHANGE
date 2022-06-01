package com.svv.jys.code.module.net.req;


import com.svv.jys.code.common.base.net.BaseRequest;

import java.util.Map;

/**
 * 会员登录
 * Created by lzj on 2017/11/11.
 */

public class POST_LOGIN_REQ extends BaseRequest {

    public String username;
    public String password;
    public String verify;

    @Override
    public Map<String, String> bulitReqMap() {
        Map map = super.bulitReqMap();
        map.put("username", username);
        map.put("password", password);
        map.put("verify",verify);
        return map;
    }
}
