package com.svv.jys.code.common.entity;

import com.svv.jys.code.common.base.BaseEntity;

import java.util.List;

/**
 * Created by js on 2018/6/15.
 */

public class CollectEntity extends BaseEntity{
    public List<String> getCollect_market() {
        return collect_market;
    }

    public void setCollect_market(List<String> collect_market) {
        this.collect_market = collect_market;
    }

    private   List<String> collect_market;
}
