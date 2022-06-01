package com.svv.jys.code.module.myself.myasset.otc.otctrading.order.view;

import com.svv.jys.code.common.base.mvp.BaseView;
import com.svv.jys.code.common.entity.OtcOrderEntity;

/**
 * Created by js on 2018/6/11.
 */

public interface OtcOrderView extends BaseView {
    void setOtcOrder(OtcOrderEntity entity);
}
