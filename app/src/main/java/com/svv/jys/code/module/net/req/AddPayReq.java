package com.svv.jys.code.module.net.req;

import com.svv.jys.code.common.base.net.BaseRequest;

import java.util.Map;

/**
 * Created by js on 2018/6/16.
 */

public class AddPayReq extends BaseRequest{
    public String id;
    public String code;
    public String memo;
    public String password;

    @Override
    public Map<String, String> bulitReqMap() {
        Map<String,String> map=super.bulitReqMap();
        map.put("id",id);
        map.put("code",code);
        map.put("memo",memo);
        map.put("password",password);
        return map;
    }
}
