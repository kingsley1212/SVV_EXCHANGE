package com.svv.jys.code.module.net.req;

import com.svv.jys.code.common.base.net.BaseRequest;

import java.util.Map;

/**
 * Created by js on 2018/9/7.
 */

public class VerifyMethodReq extends BaseRequest {
    public String key;
    public String mobile_code;
    public String email_code;
    public String google_code;
    public String type;
    @Override
    public Map<String, String> bulitReqMap() {
        Map<String,String> map=super.bulitReqMap();
        map.put("key",key);
        map.put("mobile_code",mobile_code);
        map.put("email_code",email_code);
        map.put("google_code",google_code);
        map.put("type",type);
        return map;
    }
}
