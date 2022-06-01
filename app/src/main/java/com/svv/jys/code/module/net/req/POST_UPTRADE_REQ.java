package com.svv.jys.code.module.net.req;

import com.svv.jys.code.common.base.net.BaseRequest;

import java.util.Map;

/**
 * Created by js on 2018/5/19.
 */

public class POST_UPTRADE_REQ extends BaseRequest{
    public String market;
    public String type;
    public String price;
    public String num;
    public String pwd;
    @Override
    public Map<String, String> bulitReqMap() {
        Map<String,String> map=super.bulitReqMap();
        map.put("market",market);
        map.put("type",type);
        map.put("price",price);
        map.put("num",num);
        map.put("pwd",pwd);
        return map;
    }
}
