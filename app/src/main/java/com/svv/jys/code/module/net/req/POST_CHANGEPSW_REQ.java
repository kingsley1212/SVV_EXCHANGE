package com.svv.jys.code.module.net.req;

import com.svv.jys.code.common.base.net.BaseRequest;

import java.util.Map;

/**
 * Created by js on 2018/5/30.
 */

public class POST_CHANGEPSW_REQ extends BaseRequest{
    public String action;
    public String oldpwd;
    public String pwd;
    public String repwd;
    public String code;
    @Override
    public Map<String, String> bulitReqMap() {
        Map<String,String> map=super.bulitReqMap();
        map.put("action",action);
        map.put("oldpwd",oldpwd);
        map.put("pwd",pwd);
        map.put("repwd",repwd);
        map.put("code",code);
        return map;
    }
}
