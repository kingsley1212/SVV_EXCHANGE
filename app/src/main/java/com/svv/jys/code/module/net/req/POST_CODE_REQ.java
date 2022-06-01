package com.svv.jys.code.module.net.req;

import com.svv.jys.code.common.base.net.BaseRequest;

import java.util.Map;

/**
 * Created by js on 2018/5/12.
 */

public class POST_CODE_REQ extends BaseRequest {

    public String mobile;
    public String area;
    public String type;
    public String send;
    public String verify;

    @Override
    public Map<String, String> bulitReqMap() {
        Map map = super.bulitReqMap();
        map.put("mobile", mobile);
        map.put("area", area);
        map.put("type", type);
        map.put("send", send);
        map.put("verify", verify);
        map.put("module", "exchange");
        return map;
    }
}
