package com.svv.jys.code.module.myself.myasset.jiaoyi.suocangrecord.view;


import com.svv.jys.code.common.base.mvp.BaseView;
import com.svv.jys.code.common.entity.CunFangRecordEntity;

import java.util.List;

/**
 * Created by js on 2018/8/11.
 */

public interface SuoCangRecordView extends BaseView {
    void suocangRecord(List<CunFangRecordEntity.RowsBean> list);
}
