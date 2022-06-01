package com.svv.jys.code.module.net.req;

import com.svv.jys.code.common.base.net.BaseRequest;

import java.util.Map;

/**
 * Created by js on 2018/5/19.
 */

public class POST_COINBUSINESS_SUBMIT_REQ extends BaseRequest {

    public static final int P_BUY = 1;
    public static final int P_SELL = 2;

    public String market;
    public String type;//委托类型：1买入，2卖出
    public String trade_type;//账户类型：0交易，1杠杆
    public String price;
    public String num;
    public String sec_pwd;

    @Override
    public Map<String, String> bulitReqMap() {
        Map<String, String> map = super.bulitReqMap();
        map.put("market", market);
        map.put("type", type);
        map.put("trade_type", trade_type);
        map.put("price", price);
        map.put("num", num);
        map.put("sec_pwd", sec_pwd);
        return map;
    }
}
