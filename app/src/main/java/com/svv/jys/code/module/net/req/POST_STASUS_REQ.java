package com.svv.jys.code.module.net.req;

import com.svv.jys.code.common.base.net.BaseRequest;

import java.util.Map;

/**
 * Created by js on 2018/6/12.
 */

public class POST_STASUS_REQ extends BaseRequest {
    public String id;
    public String state;
    @Override
    public Map<String, String> bulitReqMap() {
        Map<String,String> map=super.bulitReqMap();
        map.put("id",id);
        map.put("state",state);
        return map;
    }
}
