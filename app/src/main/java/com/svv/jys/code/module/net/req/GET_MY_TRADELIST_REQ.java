package com.svv.jys.code.module.net.req;

import com.svv.jys.code.common.base.net.BaseRequest;

import java.util.Map;

/**
 * Created by js on 2018/5/19.
 */
public class GET_MY_TRADELIST_REQ extends BaseRequest {

    public static final String TRADE_STATUS_ALL = "-1";
    public static final String TRADE_STATUS_NOT = "0";
    public static final String TRADE_STATUS_YET = "1";

    public String market;

    public String status;//状态 -1 全部， 0 未成交 ，1 已成交

    public int offset;

    public int limit;

    @Override
    public Map<String, String> bulitReqMap() {
        Map<String, String> map = super.bulitReqMap();
        map.put("market", market);
        map.put("status", status);
        map.put("offset", offset + "");
        map.put("limit", limit + "");
        return map;
    }
}
