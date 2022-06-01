package com.svv.jys.code.module.net.req;

import com.svv.jys.code.common.base.net.BaseRequest;

import java.util.Map;

/**
 * Created by lzj on 2018/6/2.
 */

public class POST_COINBUSINESS_TRADEREVOKE_REQ extends BaseRequest {
    public String id;

    @Override
    public Map<String, String> bulitReqMap() {
        Map map = super.bulitReqMap();
        map.put("id", id);
        return map;
    }

}
