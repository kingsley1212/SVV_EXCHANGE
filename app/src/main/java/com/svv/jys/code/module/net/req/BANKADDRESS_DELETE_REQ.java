package com.svv.jys.code.module.net.req;



import com.svv.jys.code.common.base.net.BaseRequest;

import java.util.Map;


/**
 * Created by js on 2018/5/21.
 */

public class BANKADDRESS_DELETE_REQ extends BaseRequest {
    public String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public Map<String, String> bulitReqMap() {
        Map<String,String> map= super.bulitReqMap();
        map.put("id",id);
        return map;
    }
}
