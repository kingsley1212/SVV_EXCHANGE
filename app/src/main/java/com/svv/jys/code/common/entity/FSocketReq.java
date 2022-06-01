package com.svv.jys.code.common.entity;

import com.alibaba.fastjson.JSONObject;
import com.svv.jys.code.common.base.BaseEntity;

/**
 * Created by lzj on 2018/5/22.
 */

public class FSocketReq extends BaseEntity {

    public String type;
    public String data;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String toJSONObject() {
        JSONObject json = new JSONObject();
        json.put("channel", type);
        json.put("event", data);
        return json.toJSONString();
    }
}
