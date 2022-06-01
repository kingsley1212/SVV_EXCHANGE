package com.svv.jys.code.module.net.req;

import com.svv.jys.code.common.base.net.BaseRequest;

import java.util.Map;

public class POST_GETVERIFY extends BaseRequest {

    public String type;

    @Override
    public Map<String, String> bulitReqMap() {
        Map map = super.bulitReqMap();
        map.put("type", type);
        return map;
    }
}
