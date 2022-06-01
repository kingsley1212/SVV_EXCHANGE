package com.svv.jys.code.module.net.req;


import com.svv.jys.code.common.base.net.BaseRequest;

import java.util.Map;

/**
 * Created by lzj on 2017/11/10.
 */

public class POST_REGISTER_REQ extends BaseRequest {

    /**
     * 会员账号
     */
    public String pwd;
    public String re_pwd;
    public String username;
    public String pid;
    /**
     * 手机0、邮箱1
     */
    public String type;
    public String code;
    public String area;
    public String country;



    @Override
    public Map<String, String> bulitReqMap() {
        Map map = super.bulitReqMap();
        map.put("pwd", pwd);
        map.put("re_pwd", re_pwd);
        map.put("username", username);
        map.put("pid", pid);
        map.put("type", type);
        map.put("code", code);
        map.put("area", area);
        map.put("country", country);

        return map;
    }
}
