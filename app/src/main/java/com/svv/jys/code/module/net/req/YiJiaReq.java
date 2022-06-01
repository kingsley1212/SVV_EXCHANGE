package com.svv.jys.code.module.net.req;

import com.svv.jys.code.common.base.net.BaseRequest;

import java.util.Map;

/**
 * Created by js on 2018/7/25.
 */

public class YiJiaReq extends BaseRequest {
    public String coin;
    public String code;
    public String num;

    @Override
    public Map<String, String> bulitReqMap() {
        Map<String,String> map=super.bulitReqMap();
        map.put("coin",coin);
        map.put("num",num);
        map.put("code",code);
        return map;
    }
}
