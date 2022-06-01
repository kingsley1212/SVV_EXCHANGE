package com.svv.jys.code.module.net.req;

import com.svv.jys.code.common.base.net.BaseRequest;

import java.util.Map;


/**
 * Created by js on 2018/7/11.
 */

public class ADD_OR_EDIT_BANKINFO_REQ extends BaseRequest {

//    nickname	字符串	必须			备注昵称
//    bankuser	字符串	必须			开户姓名
//    bankname	字符串	必须			开户银行
//    bankno	字符串	必须			银行卡号
//    bankaddress	字符串	必须			银行地址
//    password
    public String id;
    public String nickname;
    public String bankuser;
    public String bankname;
    public String bankno;
    public String bankaddress;
    public String password;

    @Override
    public Map<String, String> bulitReqMap() {
        Map<String, String> map = super.bulitReqMap();
        map.put("id", id);
        map.put("nickname", nickname);
        map.put("bankuser", bankuser);
        map.put("bankname", bankname);
        map.put("bankno", bankno);
        map.put("bankaddress", bankaddress);
        map.put("password", password);
        return map;
    }

}
