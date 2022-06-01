package com.svv.jys.code.module.net.req;

import com.svv.jys.code.common.base.net.BaseRequest;

import java.util.Map;

/**
 * Created by js on 2018/5/28.
 */

public class GET_MARKETINFO_REQ extends BaseRequest{
    public String market;
    @Override
    public Map<String, String> bulitReqMap() {
        Map<String,String> map=super.bulitReqMap();
        map.put("market",market);
        return map;
    }
}
