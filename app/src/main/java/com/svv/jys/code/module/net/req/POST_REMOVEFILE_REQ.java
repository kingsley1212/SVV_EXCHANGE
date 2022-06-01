package com.svv.jys.code.module.net.req;

import com.svv.jys.code.common.base.net.BaseRequest;

import java.util.Map;

/**
 * Created by js on 2018/5/23.
 */

public class POST_REMOVEFILE_REQ extends BaseRequest{
    public String path;
    @Override
    public Map<String, String> bulitReqMap() {
        Map<String ,String> map=super.bulitReqMap();
        map.put("path",path);
        return map;
    }
}
