package com.svv.jys.code.module.net.req;

import com.svv.jys.code.common.base.net.BaseRequest;

import java.util.Map;

/**
 * Created by js on 2018/6/12.
 */

public class POST_SHENSU_REQ extends BaseRequest{
    public String id;
    public String type;
    public String memo;
    @Override
    public Map<String, String> bulitReqMap() {
        Map<String,String> map=super.bulitReqMap();
        map.put("id",id);
        map.put("type",type);
        map.put("memo",memo);
        return map;
    }
}
