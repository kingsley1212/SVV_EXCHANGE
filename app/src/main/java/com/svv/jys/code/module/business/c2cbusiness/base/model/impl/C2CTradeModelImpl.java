package com.svv.jys.code.module.business.c2cbusiness.base.model.impl;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.business.c2cbusiness.base.model.IC2CTradeModel;
import com.svv.jys.code.module.net.api.API_Factory;

import rx.Observable;


/**
 * Created by js on 2018/7/11.
 */
public class C2CTradeModelImpl implements IC2CTradeModel {
    @Override
    public Observable<BaseResponse> rx_c2ctrademarkdet() {
        return API_Factory.c2ctrademarket();
    }
}
