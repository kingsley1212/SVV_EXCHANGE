package com.svv.jys.code.module.net.req;

import com.svv.jys.code.common.base.net.BaseRequest;

import java.util.Map;

public class ChangePswTypeReq extends BaseRequest {
    public String type;
    public String pwd;
    @Override
    public Map<String, String> bulitReqMap() {
        Map<String,String> map=super.bulitReqMap();
        map.put("type",type);
        map.put("pwd",pwd);
        return map;
    }
}
