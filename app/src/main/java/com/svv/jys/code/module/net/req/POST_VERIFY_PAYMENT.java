package com.svv.jys.code.module.net.req;

import com.svv.jys.code.common.base.net.BaseRequest;

import java.util.Map;

public class POST_VERIFY_PAYMENT  extends BaseRequest {
    public String code;
    public String type;
    @Override
    public Map<String, String> bulitReqMap() {
        Map<String,String> map=super.bulitReqMap();
        map.put("code",code);
        map.put("type",type);
        return map;
    }
}
