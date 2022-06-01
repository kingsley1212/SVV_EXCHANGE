package com.svv.jys.code.module.server.dataserver.event;

import com.svv.jys.code.common.entity.TradeListEntity;

/**
 * Created by lzj on 2018/5/23.
 * 深度数据event
 */
public class TradeListEvent {

    public TradeListEntity tradeListEntity;

    public TradeListEvent(TradeListEntity tradeListEntity) {
        this.tradeListEntity = tradeListEntity;
    }

    public TradeListEntity getTradeListEntity() {
        return tradeListEntity;
    }

    public void setTradeListEntity(TradeListEntity tradeListEntity) {
        this.tradeListEntity = tradeListEntity;
    }
}
