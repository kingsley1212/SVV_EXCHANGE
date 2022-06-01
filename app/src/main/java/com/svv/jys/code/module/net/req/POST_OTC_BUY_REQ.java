package com.svv.jys.code.module.net.req;

import com.svv.jys.code.common.base.net.BaseRequest;

import java.util.Map;

/**
 * Created by js on 2018/6/11.
 */

public class POST_OTC_BUY_REQ extends BaseRequest{
    public String adid;
    public String type;
    public String currency;
    public String sec_pwd;
    public String num;
    @Override
    public Map<String, String> bulitReqMap() {
        Map<String,String> map=super.bulitReqMap();
        map.put("adid",adid);
        map.put("type",type);
        map.put("currency",currency);
        map.put("sec_pwd",sec_pwd);
        map.put("num",num);
        return map;
    }
}
