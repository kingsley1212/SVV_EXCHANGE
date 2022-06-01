package com.svv.jys.code.module.net.req;

import com.svv.jys.code.common.base.net.BaseRequest;

import java.util.Map;

/**
 * Created by js on 2018/6/8.
 */

public class GET_OTC_ADV_REQ extends BaseRequest{
    public String offset;
    public String limit;
    public String coin;
    public String money;
    public String country;
    public String pay;
    public String trade_type;

    @Override
    public Map<String, String> bulitReqMap() {
        Map<String,String> map= super.bulitReqMap();
        map.put("offset",offset);
        map.put("limit",limit);
        map.put("coin",coin);
        map.put("money",money);
        map.put("country",country);
        map.put("pay",pay);
        map.put("trade_type",trade_type);
        return map;
    }
}
