package com.svv.jys.code.module.net.req;

import com.svv.jys.code.common.base.net.BaseRequest;

import java.util.Map;

/**
 * Created by js on 2018/6/16.
 */

public class DeletePayReq extends BaseRequest {
    public String id;

    @Override
    public Map<String, String> bulitReqMap() {
        Map<String,String> map= super.bulitReqMap();
        map.put("id",id);
        return map;
    }
}
