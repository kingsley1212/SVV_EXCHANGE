package com.svv.jys.code.module.net.req;

import android.text.TextUtils;

import com.svv.jys.code.common.base.net.BaseRequest;

import java.util.Map;

public class POST_CHECK_VERIFY extends BaseRequest {
    private String mobile;
    private String email;
    private String google;
    @Override
    public Map<String, String> bulitReqMap() {
        Map<String,String> map=super.bulitReqMap();
        map.put("mobile",mobile);
        map.put("email",email);
        map.put("google",google);
        return map;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        if(!TextUtils.isEmpty(mobile)) {
            this.mobile = mobile;
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if(!TextUtils.isEmpty(email)) {
            this.email = email;
        }
    }

    public String getGoogle() {
        return google;
    }

    public void setGoogle(String google) {
        if(!TextUtils.isEmpty(google)) {
            this.google = google;
        }
    }
}
