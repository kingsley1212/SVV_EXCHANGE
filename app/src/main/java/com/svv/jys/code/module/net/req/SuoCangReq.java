package com.svv.jys.code.module.net.req;


import com.svv.jys.code.common.base.net.BaseRequest;

import java.util.Map;

/**
 * Created by js on 2018/8/10.
 */

public class SuoCangReq extends BaseRequest {
    public String coin;
    public String revolution;
    public String num;
    public String rcoin;

    @Override
    public Map<String, String> bulitReqMap() {
        Map<String,String> map=super.bulitReqMap();
        map.put("coin",coin);
        map.put("revolution",revolution);
        map.put("num",num);
        map.put("rcoin",rcoin);
        return map;
    }
}
