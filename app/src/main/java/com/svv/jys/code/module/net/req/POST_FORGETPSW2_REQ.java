package com.svv.jys.code.module.net.req;

import com.svv.jys.code.common.base.net.BaseRequest;

import java.util.Map;

/**
 * Created by js on 2018/5/21.
 */

public class POST_FORGETPSW2_REQ extends BaseRequest{
    public String mobile;
    public String code;
    public String pwd;
    public String re_pwd;
    public String idcard;
    @Override
    public Map<String, String> bulitReqMap() {
        Map<String,String> map=super.bulitReqMap();
        map.put("mobile",mobile);
        map.put("code",code);
        map.put("pwd",pwd);
        map.put("re_pwd",re_pwd);
        map.put("idcard",idcard);
        return map;
    }
}
