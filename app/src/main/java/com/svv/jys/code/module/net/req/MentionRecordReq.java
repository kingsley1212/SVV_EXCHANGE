package com.svv.jys.code.module.net.req;

import com.svv.jys.code.common.base.net.BaseRequest;

import java.util.Map;

public class MentionRecordReq extends BaseRequest {
    public String limit;
    public String offset;
    public String coin;
    @Override
    public Map<String, String> bulitReqMap() {
        Map<String,String> map=super.bulitReqMap();
        map.put("limit",limit);
        map.put("offset",offset);
        map.put("coin",coin);
        return map;
    }
}
