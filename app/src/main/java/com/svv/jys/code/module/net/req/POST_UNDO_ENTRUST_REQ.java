package com.svv.jys.code.module.net.req;

import com.svv.jys.code.common.base.net.BaseRequest;

import java.util.Map;

/**
 * Created by js on 2018/5/25.
 */

public class POST_UNDO_ENTRUST_REQ extends BaseRequest{
    public String id;

    @Override
    public Map<String, String> bulitReqMap() {
        Map<String,String> map=super.bulitReqMap();
        map.put("id",id);
        return map;
    }
}
