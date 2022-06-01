package com.svv.jys.code.module.net.req;

import com.svv.jys.code.common.base.net.BaseRequest;

import java.util.Map;

/**
 * Created by js on 2018/6/16.
 */

public class PayMethodReq extends BaseRequest{
    public String offset="0";
    public String limit="100";

    @Override
    public Map<String, String> bulitReqMap() {
        Map<String,String> map=super.bulitReqMap();
        map.put("offset",offset);
        map.put("limit",limit);
        return map;
    }
}
