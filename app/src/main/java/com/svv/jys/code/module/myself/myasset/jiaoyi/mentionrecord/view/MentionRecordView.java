package com.svv.jys.code.module.myself.myasset.jiaoyi.mentionrecord.view;

import com.svv.jys.code.common.base.mvp.BaseView;
import com.svv.jys.code.common.entity.MentionRecordEntity;

import java.util.List;

public interface MentionRecordView extends BaseView {
    void setMentionRecord(List<MentionRecordEntity.RowsBean> list);

    void deleteSuccess(int position);
}
