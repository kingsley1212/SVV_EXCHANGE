package com.svv.jys.code.module.net.req;

import com.svv.jys.code.common.base.net.BaseRequest;

import java.util.Map;

/**
 * Created by js on 2018/7/27.
 */

public class AdvListReq extends BaseRequest {
    public String limit;
    public String offset;
    public String coin;
    public String status;
    @Override
    public Map<String, String> bulitReqMap() {
        Map<String,String> map=super.bulitReqMap();
        map.put("limit",limit);
        map.put("offset",offset);
        map.put("coin",coin);
        map.put("status",status);
        return map;
    }
}
