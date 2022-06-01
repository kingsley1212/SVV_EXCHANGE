package com.svv.jys.code.module.net.req;

import com.svv.jys.code.common.base.net.BaseRequest;

import java.util.Map;

/**
 * Created by js on 2018/5/19.
 */

public class POST_ADD_ADDRESS_REQ extends BaseRequest{
    public String  coin;
    public String  name;
    public String  address;
    @Override
    public Map<String, String> bulitReqMap() {
        Map<String,String> map=super.bulitReqMap();
        map.put("coin",coin);
        map.put("name",name);
        map.put("address",address);
        return map;
    }
}
