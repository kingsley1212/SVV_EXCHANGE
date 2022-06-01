package com.svv.jys.code.module.net.req;

import com.svv.jys.code.common.base.net.BaseRequest;

import java.util.Map;

/**
 * Created by js on 2018/6/20.
 */

public class BusinessInfoReq extends BaseRequest{
    public String user_id;
    public String type;
    public String offset;
    public String limit;
    @Override
    public Map<String, String> bulitReqMap() {
        Map<String,String> map=super.bulitReqMap();
        map.put("user_id",user_id);
        map.put("type",type);
        map.put("offset",offset);
        map.put("limit",limit);
        return map;
    }
}
