package com.svv.jys.code.module.net.req;

import com.svv.jys.code.common.base.net.BaseRequest;

import java.util.Map;

/**
 * Created by js on 2018/5/25.
 */

public class GET_ORDER_DETAIL_REQ extends BaseRequest{
    public String offset;
    public String limit;
    public String market;
    public String search_field;
    public String search_value;
    public String s_time;
    public String e_time;
    public String area;
    public String coin;
    public String type;
    @Override
    public Map<String, String> bulitReqMap() {
        Map<String,String> map=super.bulitReqMap();
        map.put("offset",offset);
        map.put("limit",limit);
        map.put("market",market);
        map.put("search_field",search_field);
        map.put("search_value",search_value);
        map.put("s_time",s_time);
        map.put("e_time",e_time);
        map.put("area",area);
        map.put("coin",coin);
        map.put("type",type);
        return map;
    }
}
