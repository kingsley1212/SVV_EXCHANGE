package com.svv.jys.code.module.net.req;

import com.svv.jys.code.common.base.net.BaseRequest;

import java.util.Map;

/**
 * Created by js on 2018/6/11.
 */

public class GET_OTC_ORDER_REQ extends BaseRequest{
    public String offset;
    public String limit;
    public String type;
    public String status;
    @Override
    public Map<String, String> bulitReqMap() {
        Map<String,String> map=super.bulitReqMap();
        map.put("offset",offset);
        map.put("limit",limit);
        map.put("type",type);
        map.put("status",status);
        return map;
    }
}
