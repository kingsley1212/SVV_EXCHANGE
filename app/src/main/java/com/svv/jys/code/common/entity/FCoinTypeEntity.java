package com.svv.jys.code.common.entity;

import com.svv.jys.code.common.base.BaseEntity;

/**
 * 账户详情的几类数据
 * Created by Administrator on 2018/5/3 0003.
 */

public class FCoinTypeEntity extends BaseEntity {
    public String typeName;
    public String coin1Count;
    public String coin2Count;
    public boolean isTitle;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getCoin1Count() {
        return coin1Count;
    }

    public void setCoin1Count(String coin1Count) {
        this.coin1Count = coin1Count;
    }

    public String getCoin2Count() {
        return coin2Count;
    }

    public void setCoin2Count(String coin2Count) {
        this.coin2Count = coin2Count;
    }
}
