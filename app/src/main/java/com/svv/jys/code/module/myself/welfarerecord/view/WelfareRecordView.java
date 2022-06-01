package com.svv.jys.code.module.myself.welfarerecord.view;

import com.svv.jys.code.common.base.mvp.BaseView;
import com.svv.jys.code.common.entity.WelfareRecordEntity;

import java.util.List;

public interface WelfareRecordView extends BaseView {
    void setWelfareRecord(List<WelfareRecordEntity.RowsBean> list);
    void lingquSuccese();
}
