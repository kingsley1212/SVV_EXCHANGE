package com.svv.jys.code.module.myself.businessorder.view;

import com.svv.jys.code.common.base.mvp.BaseView;
import com.svv.jys.code.common.entity.OtcOrderEntity;

/**
 * Created by js on 2018/6/12.
 */

public interface BusinessOrderView extends BaseView {
    void setBusinessOrder(OtcOrderEntity order);
}
