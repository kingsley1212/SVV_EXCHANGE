package com.svv.jys.code.module.server.dataserver.event;

import com.svv.jys.code.common.entity.TransactionEntity;

import java.util.List;

/**
 * Created by lzj on 2018/5/23.
 * 深度数据event
 */
public class TransactionListEvent {

    public List<TransactionEntity> list;

    public TransactionListEvent(List<TransactionEntity> list) {
        this.list = list;
    }

    public List<TransactionEntity> getTradeListEntity() {
        return list;
    }

    public void setTradeListEntity(List<TransactionEntity> list) {
        this.list = list;
    }
}
