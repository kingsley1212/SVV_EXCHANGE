package com.svv.jys.code.module.net.req;

import com.svv.jys.code.common.base.net.BaseRequest;

import java.util.Map;

/**
 * Created by js on 2018/7/26.
 */

public class AdvPulishReq extends BaseRequest {
    public String id;
    public String type;
    public String country;
    public String currency;
    public String premium;
    public String minamount;
    public String maxamount;
    public String prompt;
    public String num;
    public String message;
    public String payment;
    public String coin;
    public String price;
    public String valuetion;

    @Override
    public Map<String, String> bulitReqMap() {
        Map<String,String> map=super.bulitReqMap();
        map.put("id",id);
        map.put("type",type);
        map.put("country",country);
        map.put("currency",currency);
        map.put("premium",premium);
        map.put("minamount",minamount);
        map.put("maxamount",maxamount);
        map.put("prompt",prompt);
        map.put("num",num);
        map.put("message",message);
        map.put("payment",payment);
        map.put("coin",coin);
        map.put("price",price);
        map.put("valuetion",valuetion);
        return map;
    }
}
