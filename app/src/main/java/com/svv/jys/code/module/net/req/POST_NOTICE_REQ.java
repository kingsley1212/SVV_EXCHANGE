package com.svv.jys.code.module.net.req;

import com.svv.jys.code.common.base.net.BaseRequest;

import java.util.Map;

public class POST_NOTICE_REQ extends BaseRequest {
    public String cid;
    public String offset;
    public String limit="15";

    @Override
    public Map<String, String> bulitReqMap() {
        Map<String, String> map = super.bulitReqMap();
        map.put("cid", cid);
        map.put("offset", offset);
        map.put("limit", limit);
        return map;
    }
}
