package com.svv.jys.code.module.myself.usercenter.base.pay.addpay.view;

import com.svv.jys.code.common.base.mvp.BaseView;
import com.svv.jys.code.common.entity.OtcPayEntity;

/**
 * Created by js on 2018/6/16.
 */

public interface AddPayView extends BaseView {
    void addSuccese();
    void setPaymMethod(OtcPayEntity entity);

    void verifySuccess(OtcPayEntity.RowsBean entity);

}
