package com.svv.jys.code.module.myself.myasset.jiaoyi.chargerecord.view;

import com.svv.jys.code.common.base.mvp.BaseView;
import com.svv.jys.code.common.entity.MentionRecordEntity;

import java.util.List;

public interface ChargeRecordView extends BaseView {
    void setMentionRecord(List<MentionRecordEntity.RowsBean> list);

}
