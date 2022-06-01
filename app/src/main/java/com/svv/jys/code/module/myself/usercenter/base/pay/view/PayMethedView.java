package com.svv.jys.code.module.myself.usercenter.base.pay.view;

import com.svv.jys.code.common.base.mvp.BaseView;
import com.svv.jys.code.common.entity.PayMethodEntity;

/**
 * Created by js on 2018/6/16.
 */

public interface PayMethedView extends BaseView {
    void setPayMethod(PayMethodEntity payMethod);
    void deleteSuccese();
    void setStatusSuccese();
}
