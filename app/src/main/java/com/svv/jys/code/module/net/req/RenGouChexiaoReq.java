package com.svv.jys.code.module.net.req;



import com.svv.jys.code.common.base.net.BaseRequest;

import java.util.Map;

/**
 * Created by js on 2018/10/19.
 */

public class RenGouChexiaoReq extends BaseRequest {
    public String id;

    @Override
    public Map<String, String> bulitReqMap() {
        Map<String,String> map=super.bulitReqMap();
        map.put("id",id);
        return map;
    }
}
