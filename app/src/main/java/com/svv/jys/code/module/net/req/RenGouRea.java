package com.svv.jys.code.module.net.req;


import com.svv.jys.code.common.base.net.BaseRequest;

import java.util.Map;

/**
 * Created by js on 2018/10/19.
 */

public class RenGouRea extends BaseRequest {
    public String type;
    public String coin;
    public String num;
    @Override
    public Map<String, String> bulitReqMap() {
        Map<String,String> map=super.bulitReqMap();
        map.put("coin",coin);
        map.put("type",type);
        map.put("num",num);
        return map;
    }
}
