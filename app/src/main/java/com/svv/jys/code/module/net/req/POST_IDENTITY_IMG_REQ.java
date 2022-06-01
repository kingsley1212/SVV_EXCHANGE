package com.svv.jys.code.module.net.req;

import com.svv.jys.code.common.base.net.BaseRequest;

import java.util.Map;

/**
 * Created by js on 2018/5/22.
 */

public class POST_IDENTITY_IMG_REQ extends BaseRequest{
    public String path="identity";
    @Override
    public Map<String, String> bulitReqMap() {
        Map<String ,String> map= super.bulitReqMap();
        map.put("path",path);
        map.put("json","3");
        return map;
    }
}
