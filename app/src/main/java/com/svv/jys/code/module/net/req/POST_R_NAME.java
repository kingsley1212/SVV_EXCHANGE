package com.svv.jys.code.module.net.req;

import com.svv.jys.code.common.base.net.BaseRequest;

import java.util.Map;

public class POST_R_NAME  extends BaseRequest {
    public String nickname;

    @Override
    public Map<String, String> bulitReqMap() {
        Map<String,String> map=super.bulitReqMap();
        map.put("nickname",nickname);
        return map;
    }
}

