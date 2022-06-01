package com.svv.jys.code.module.myself.myasset.otc.view;

import com.svv.jys.code.common.base.mvp.BaseView;
import com.svv.jys.code.common.entity.OtcCoinInfoEntity;
import com.svv.jys.code.common.entity.OtcRecordEntity;

/**
 * Created by js on 2018/6/7.
 */

public interface OtcDetailView extends BaseView {
    void setOtcInfo(OtcCoinInfoEntity entity);
    void setRecordList(OtcRecordEntity entity);
}
