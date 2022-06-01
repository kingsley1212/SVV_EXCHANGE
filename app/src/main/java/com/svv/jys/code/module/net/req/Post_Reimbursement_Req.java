package com.svv.jys.code.module.net.req;

import com.svv.jys.code.common.base.net.BaseRequest;

import java.util.Map;

/**
 * Created by js on 2018/5/29.
 */

public class Post_Reimbursement_Req extends BaseRequest{
    public String id;
    public String num;
    public String password;
    @Override
    public Map<String, String> bulitReqMap() {
        Map<String,String> map=super.bulitReqMap();
        map.put("id",id);
        map.put("num",num);
        map.put("password",password);
        return map;
    }
}
