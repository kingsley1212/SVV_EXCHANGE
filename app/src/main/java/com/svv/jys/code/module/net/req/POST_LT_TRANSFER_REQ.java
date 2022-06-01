package com.svv.jys.code.module.net.req;

import com.svv.jys.code.common.base.net.BaseRequest;

import java.util.Map;

/**
 * Created by js on 2018/5/29.
 */

public class POST_LT_TRANSFER_REQ extends BaseRequest{
    public String coin;
    public String market;
    public String from;
    public String num;
    public String password;
    @Override
    public Map<String, String> bulitReqMap() {
        Map<String,String> map=super.bulitReqMap();
        map.put("coin",coin);
        map.put("market",market);
        map.put("from",from);
        map.put("num",num);
        map.put("password",password);
        return map;
    }
}
