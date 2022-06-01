package com.svv.jys.code.module.net.req;

import com.svv.jys.code.common.base.net.BaseRequest;

import java.util.Map;

/**
 * Created by js on 2018/5/21.
 */

public class POST_SAFEPSW_REQ extends BaseRequest {
    public String pwd;
    public String repwd;
    public String code;
    public String nickname;

    @Override
    public Map<String, String> bulitReqMap() {
        Map<String,String> map=super.bulitReqMap();
        map.put("pwd",pwd);
        map.put("repwd",repwd);
        map.put("code",code);
        map.put("nickname",nickname);
        return map;
    }
}
