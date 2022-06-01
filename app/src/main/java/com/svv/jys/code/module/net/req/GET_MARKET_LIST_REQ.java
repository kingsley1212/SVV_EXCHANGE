package com.svv.jys.code.module.net.req;

import com.svv.jys.code.common.base.net.BaseRequest;

import java.util.Map;

/**
 * Created by js on 2018/5/18.
 */

public class GET_MARKET_LIST_REQ extends BaseRequest {
    public String area;
    public String market;
    @Override
    public Map<String, String> bulitReqMap() {
        Map<String,String> map=super.bulitReqMap();
        map.put("area",area);
        map.put("market",market);
        return map;
    }
}
