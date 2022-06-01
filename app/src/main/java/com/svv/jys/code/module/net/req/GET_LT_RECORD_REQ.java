package com.svv.jys.code.module.net.req;

import com.svv.jys.code.common.base.net.BaseRequest;

import java.util.Map;

/**
 * Created by js on 2018/5/26.
 */

public class GET_LT_RECORD_REQ extends BaseRequest{
    public String offset;
    public String limit;
    public String market;
    @Override
    public Map<String, String> bulitReqMap() {
        Map<String,String> map=super.bulitReqMap();
        map.put("offset",offset);
        map.put("limit",limit);
        map.put("market",market);
        return map;
    }
}
