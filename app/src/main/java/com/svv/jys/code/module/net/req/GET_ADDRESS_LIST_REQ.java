package com.svv.jys.code.module.net.req;

import com.svv.jys.code.common.base.net.BaseRequest;

import java.util.Map;

/**
 * Created by js on 2018/5/19.
 */

public class GET_ADDRESS_LIST_REQ extends BaseRequest{
    public String coin;
    public String offset;
    public String limit;
    @Override
    public Map<String, String> bulitReqMap() {
        Map<String,String> map=super.bulitReqMap();
        map.put("coin",coin);
        map.put("offset",offset);
        map.put("limit",limit);
        return map;
    }
}
