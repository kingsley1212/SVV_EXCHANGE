package com.svv.jys.code.module.net.req;

import com.svv.jys.code.common.base.net.BaseRequest;

import java.util.Map;

/**
 * Created by js on 2018/5/26.
 */

public class POST_TRANSFEROUT_REQ extends BaseRequest{
    public String coin;
    public String address;
    public String num;
    public String pwd;
    @Override
    public Map<String, String> bulitReqMap() {
        Map<String,String> map=super.bulitReqMap();
        map.put("coin",coin);
        map.put("address",address);
        map.put("num",num);
        map.put("pwd",pwd);
        return map;
    }
}
