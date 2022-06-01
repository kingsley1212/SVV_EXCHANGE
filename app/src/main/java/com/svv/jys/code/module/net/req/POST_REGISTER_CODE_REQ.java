package com.svv.jys.code.module.net.req;


import com.svv.jys.code.common.base.net.BaseRequest;

import java.util.Map;

/**
 * Created by LB on 2017/12/12.
 */

public class POST_REGISTER_CODE_REQ extends BaseRequest {

    public String type;
    public String addr;
    public String category;
    public String area_num;


    @Override
    public Map<String, String> bulitReqMap() {
        Map map = super.bulitReqMap();
        map.put("type", type);
        map.put("addr", addr);
        map.put("category",category);
        map.put("area_num",area_num);
        return map;
    }
}
