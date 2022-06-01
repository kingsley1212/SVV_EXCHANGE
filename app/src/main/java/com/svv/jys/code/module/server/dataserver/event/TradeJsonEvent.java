package com.svv.jys.code.module.server.dataserver.event;

import com.svv.jys.code.module.business.tradechat.base.mychat.MyDataParse;
import com.svv.jys.code.module.business.tradechat.base2.adapter.KLineEntity;

import java.util.List;

/**
 * Created by lzj on 2018/5/23.
 * K线图数据event
 */
public class TradeJsonEvent {

    public MyDataParse myDataParse;
    public List<KLineEntity> kLineEntities;

    public TradeJsonEvent( MyDataParse myDataParse) {
        this.myDataParse = myDataParse;
    }

    public TradeJsonEvent( List<KLineEntity> kLineEntities) {
        this.kLineEntities = kLineEntities;
    }

    public MyDataParse getMyDataParse() {
        return myDataParse;
    }

    public void setMyDataParse(MyDataParse myDataParse) {
        this.myDataParse = myDataParse;
    }

    public List<KLineEntity> getkLineEntities() {
        return kLineEntities;
    }

    public void setkLineEntities(List<KLineEntity> kLineEntities) {
        this.kLineEntities = kLineEntities;
    }
}
