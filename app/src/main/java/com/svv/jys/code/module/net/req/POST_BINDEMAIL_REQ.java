package com.svv.jys.code.module.net.req;

import com.svv.jys.code.common.base.net.BaseRequest;

import java.util.Map;

/**
 * Created by js on 2018/5/30.
 */

public class POST_BINDEMAIL_REQ extends BaseRequest{
    public String email;
    public String code;
    public String secpwd;
    @Override
    public Map<String, String> bulitReqMap() {
        Map<String,String> map=super.bulitReqMap();
        map.put("email",email);
        map.put("code",code);
        map.put("secpwd",secpwd);
        return map;
    }
}
