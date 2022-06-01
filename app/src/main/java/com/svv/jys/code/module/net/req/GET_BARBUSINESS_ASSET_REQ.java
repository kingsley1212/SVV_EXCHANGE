package com.svv.jys.code.module.net.req;

import com.svv.jys.code.common.base.net.BaseRequest;

import java.util.Map;

/**
 * Created by js on 2018/5/24.
 */

public class GET_BARBUSINESS_ASSET_REQ extends BaseRequest{
    public String limit;
    public String offset;
    public String market;
    public String zero;
    @Override
    public Map<String, String> bulitReqMap() {
        Map<String,String> map=super.bulitReqMap();
        map.put("limit",limit);
        map.put("offset",offset);
        map.put("market",market);
        map.put("zero",zero);
        return map;
    }
}
