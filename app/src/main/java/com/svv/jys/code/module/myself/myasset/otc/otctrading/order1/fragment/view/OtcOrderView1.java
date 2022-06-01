package com.svv.jys.code.module.myself.myasset.otc.otctrading.order1.fragment.view;

import com.svv.jys.code.common.base.mvp.BaseView;
import com.svv.jys.code.common.entity.OtcOrderEntity;

/**
 * Created by js on 2018/9/4.
 */

public interface OtcOrderView1 extends BaseView {
    void setOtcOrder(OtcOrderEntity entity,boolean isLoadMore);

    String getRxSize();

}

