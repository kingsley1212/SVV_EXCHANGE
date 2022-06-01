package com.svv.jys.code.module.net.req;

import com.svv.jys.code.common.base.net.BaseRequest;

import java.util.Map;

/**
 * Created by js on 2018/5/18.
 */

public class GET_FBBUSINESS_ASSET_REQ extends BaseRequest{
    public String limit;
    public String offset;
    public String coinname;
    public String zero;
    @Override
    public Map<String, String> bulitReqMap() {
        Map<String,String> map=super.bulitReqMap();
        map.put("limit",limit);
        map.put("offset",offset);
        map.put("coinname",coinname);
        map.put("zero",zero);
        return map;
    }
}
