package com.svv.jys.code.module.net.req;

import com.svv.jys.code.common.base.net.BaseRequest;

import java.util.Map;

/**
 * Created by js on 2018/5/17.
 */

public class POST_CHATFILE_REQ extends BaseRequest {

    public String json;
    public String path;

    @Override
    public Map<String, String> bulitReqMap() {
        Map map = super.bulitReqMap();
        map.put("json","3");
        map.put("path","chat");
        return map;
    }
}
