package com.svv.jys.code.module.net.req;

import com.svv.jys.code.common.base.net.BaseRequest;

import java.util.Map;

/**
 * Created by js on 2018/5/28.
 */

public class POST_LT_DEBIT_REQ extends BaseRequest{
    public String market;
    public String coin;
    public String num;
    @Override
    public Map<String, String> bulitReqMap() {
        Map<String,String> map=super.bulitReqMap();
        map.put("market",market);
        map.put("coin",coin);
        map.put("num",num);
        return map;
    }
}
