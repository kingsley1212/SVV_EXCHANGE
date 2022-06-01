package com.svv.jys.code.module.business.tradechat.base3.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.fujianlian.klinechart.KLineEntity;
import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.business.tradechat.base3.presenter.TradeChat3Presenter;
import com.svv.jys.code.module.net.u.NET_CODE;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lzj on 2018/5/28.
 */

public class GetOldKLineDataResponse extends BaseResponse {
    public List<KLineEntity> rows;

    public void fromJSON(String content) {
//        super.fromJSON(content);
        this.code = NET_CODE.C_200;
        this.datas = content;
        this.rows = new ArrayList<>();
        try {
            JSONObject json = JSONObject.parseObject(datas);
            JSONArray jsonArray = JSONArray.parseArray(json.getString("d"));
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONArray en = jsonArray.getJSONArray(i);
                try {
                    KLineEntity entity = new KLineEntity();
                    entity.Timestamp = en.getLongValue(0);
                    entity.Date = ToolUtils.timeStamp2String(en.getString(0), TradeChat3Presenter.klineFormat);
                    entity.Open = ToolUtils.String2Float(en.getString(1));
                    entity.High = ToolUtils.String2Float(en.getString(2));
                    entity.Low = ToolUtils.String2Float(en.getString(3));
                    entity.Close = ToolUtils.String2Float(en.getString(4));
                    entity.Volume = ToolUtils.String2Float(en.getString(5));
                    rows.add(entity);
                } catch (Exception e) {
                }
            }
        } catch (Exception e) {
            this.rows = new ArrayList<>();
        }
    }

}
