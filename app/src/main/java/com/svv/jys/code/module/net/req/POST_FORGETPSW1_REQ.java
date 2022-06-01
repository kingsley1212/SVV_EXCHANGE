package com.svv.jys.code.module.net.req;

import com.svv.jys.code.common.base.net.BaseRequest;

import java.util.Map;

/**
 * Created by js on 2018/5/21.
 */

public class POST_FORGETPSW1_REQ extends BaseRequest{
    public String username;
    public String idcard;

    @Override
    public Map<String, String> bulitReqMap() {
        Map<String,String> map=super.bulitReqMap();
        map.put("username",username);
        map.put("idcard",idcard);
        return map;
    }
}
