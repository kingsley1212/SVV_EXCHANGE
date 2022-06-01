package com.svv.jys.code.module.myself.businessorder2.fragment.view;

import com.svv.jys.code.common.base.mvp.BaseView;
import com.svv.jys.code.common.entity.OtcOrderEntity;

/**
 * Created by js on 2018/9/4.
 */

public interface BusinessOrderView extends BaseView {
    void setBusinessOrder(OtcOrderEntity order);
}
