package com.svv.jys.code.module.net.req;

import com.svv.jys.code.common.base.net.BaseRequest;

import java.util.Map;


/**
 * Created by js on 2018/7/11.
 */

public class C2CBuyOrSellReq extends BaseRequest {
    public String coin;
    public String type;//委托类型：1买入，2卖出
    public String bank;
    public String num;
    public String sec_pwd;

    @Override
    public Map<String, String> bulitReqMap() {
        Map<String, String> map = super.bulitReqMap();
        map.put("coin", coin);
        map.put("type", type);
        map.put("bank", bank);
        map.put("num", num);
        map.put("sec_pwd", sec_pwd);
        return map;
    }
}
