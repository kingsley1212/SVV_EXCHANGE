package com.svv.jys.code.module.net.req;

import com.svv.jys.code.common.base.net.BaseRequest;

import java.util.Map;

/**
 * Created by js on 2018/5/25.
 */

public class GET_ENTRUST_REQ extends BaseRequest{
    public String market;
    public String status;
    public String offset;
    public String limit;
    public String type;
    @Override
    public Map<String, String> bulitReqMap() {
        Map<String,String> map=super.bulitReqMap();
        map.put("market",market);
        map.put("status",status);
        map.put("offset",offset);
        map.put("limit",limit);
        map.put("type",type);
        return map;
    }
}
