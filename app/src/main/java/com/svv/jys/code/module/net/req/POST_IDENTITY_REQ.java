package com.svv.jys.code.module.net.req;

import com.svv.jys.code.common.base.net.BaseRequest;

import java.util.Map;

/**
 * Created by js on 2018/5/19.
 */

public class POST_IDENTITY_REQ extends BaseRequest{
    public String true_name;
    public String idcard;
    public String pic1;
    public String pic2;
    public String pic3;
    public String surname;
    public String city;
    public String address;
    public String code;
    @Override
    public Map<String, String> bulitReqMap() {
        Map<String,String> map=super.bulitReqMap();
        map.put("true_name",true_name);
        map.put("idcard",idcard);
        map.put("pic1",pic1);
        map.put("pic2",pic2);
        map.put("pic3",pic3);
        map.put("surname",surname);
        map.put("city",city);
        map.put("address",address);
        map.put("code",code);
        return map;
    }
}
