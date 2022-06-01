package com.svv.jys.code.module.net.res;

import com.alibaba.fastjson.JSONObject;
import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.common.entity.CoinMyTradeEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lzj on 2018/5/28.
 */

public class GetMyTradeListResponse extends BaseResponse {
    public String total;
    public int offset;
    public int limit;
    public List<CoinMyTradeEntity> rows;

    public void fromJSON(String content) {
        super.fromJSON(content);
        try {
            JSONObject json = JSONObject.parseObject(datas);
            this.offset = json.getIntValue("offset");
            this.limit = json.getIntValue("limit");
            this.total = json.getString("total");
            this.rows = (List<CoinMyTradeEntity>) CoinMyTradeEntity.fromJSONListAuto(json.getString("rows"),
                    CoinMyTradeEntity.class);
        } catch (Exception e) {
            this.offset = 0;
            this.limit = 0;
            this.total = "0";
            this.rows = new ArrayList<>();
        }
    }

}
