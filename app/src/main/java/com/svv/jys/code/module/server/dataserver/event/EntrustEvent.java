package com.svv.jys.code.module.server.dataserver.event;

import com.svv.jys.code.common.entity.EntrustEntity;

import java.util.List;

public class EntrustEvent {
    public EntrustEvent(List<EntrustEntity.RowsBean> list) {
        this.list = list;
    }

    public List<EntrustEntity.RowsBean> list;

    public List<EntrustEntity.RowsBean> getList() {
        return list;
    }

    public void setList(List<EntrustEntity.RowsBean> list) {
        this.list = list;
    }
}
