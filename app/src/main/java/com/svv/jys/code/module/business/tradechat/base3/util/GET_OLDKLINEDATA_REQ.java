package com.svv.jys.code.module.business.tradechat.base3.util;


import com.svv.jys.code.common.base.net.BaseRequest;

import java.util.Map;

/**
 * Created by js on 2018/10/19.
 */

public class GET_OLDKLINEDATA_REQ extends BaseRequest {
    public String symbol;//交易对
    public String resolution;//
    public String from;
    public String to;

    @Override
    public Map<String, String> bulitReqMap() {
        Map<String,String> map=super.bulitReqMap();
        map.put("symbol",symbol);
        map.put("resolution",resolution);
        map.put("from",from);
        map.put("to",to);
        return map;
    }
}
