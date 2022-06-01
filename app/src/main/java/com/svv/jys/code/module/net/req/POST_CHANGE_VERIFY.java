package com.svv.jys.code.module.net.req;

import com.svv.jys.code.common.base.net.BaseRequest;

import java.util.Map;

public class POST_CHANGE_VERIFY extends BaseRequest {
    public String m;
    public String status;
    @Override
    public Map<String, String> bulitReqMap() {
        Map<String,String> map=super.bulitReqMap();
        map.put("m",m);
        map.put("status",status);
        return map;
    }
}
