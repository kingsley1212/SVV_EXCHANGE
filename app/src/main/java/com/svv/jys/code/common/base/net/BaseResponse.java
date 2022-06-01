package com.svv.jys.code.common.base.net;

import android.text.TextUtils;

import com.alibaba.fastjson.JSONObject;
import com.svv.jys.code.common.constant.ACEConstant;
import com.svv.jys.code.common.db.nativecache.ACache;
import com.svv.jys.code.module.app.MAppliaction;
import com.svv.jys.code.module.net.u.NET_CODE;


/**
 * Created by lzj on 2017/6/3.
 */

public class BaseResponse implements java.io.Serializable {

    public int code;

    public String datas;
    public String msg;
    public String extend;
    public void toJSON() {

    }

    public void fromJSON(String content) {

        try {
            JSONObject json = JSONObject.parseObject(content);
            this.code = json.getIntValue("code");
            this.datas = json.getString("data");
            this.msg = json.getString("msg");
            this.extend=json.getString("extend");
            if(!TextUtils.isEmpty(this.extend)&&this.extend.contains("c_price")) {
                String price = JSONObject.parseObject(extend).getString("c_price");
                String socket = JSONObject.parseObject(extend).getString("socket");
                String socket_chat = JSONObject.parseObject(extend).getString("chat");
                String help_url = JSONObject.parseObject(extend).getString("help");
                ACache.get(MAppliaction.getApp()).put(ACEConstant.PRICE, price);
                ACache.get(MAppliaction.getApp()).put(ACEConstant.SOCKET_NET, socket);
                ACache.get(MAppliaction.getApp()).put(ACEConstant.SOCKET_Chat, socket_chat);
                ACache.get(MAppliaction.getApp()).put(ACEConstant.HELP_URL, help_url);
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.code = NET_CODE.C_400;// 解析异常
            this.datas = "";// 解析异常
            this.msg = "服务器繁忙，请稍后重试";
        }
    }

}
