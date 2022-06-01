package com.svv.jys.code.module.net.req;

import com.svv.jys.code.common.base.net.BaseRequest;

import java.util.Map;

/**
 * Created by js on 2018/5/28.
 */

public class GET_USER_COIN_INFO extends BaseRequest{
    public String coin;
    @Override
    public Map<String, String> bulitReqMap() {
        Map<String,String> map=super.bulitReqMap();
        map.put("coin",coin);
        return map;
    }
}
