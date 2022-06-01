package com.svv.jys.code.module.net.req;

import com.svv.jys.code.common.base.net.BaseRequest;

import java.util.Map;

/**
 * Created by js on 2018/5/19.
 */

public class GET_TRADELIST_REQ extends BaseRequest{
    public String market;
    public String limit;
    @Override
    public Map<String, String> bulitReqMap() {
        Map<String,String> map=super.bulitReqMap();
        map.put("market",market);
        map.put("limit",limit);
        return map;
    }
}
