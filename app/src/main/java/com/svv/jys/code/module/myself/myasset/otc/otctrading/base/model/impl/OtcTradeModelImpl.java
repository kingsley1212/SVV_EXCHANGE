package com.svv.jys.code.module.myself.myasset.otc.otctrading.base.model.impl;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.myself.myasset.otc.otctrading.base.model.OtcTradeModel;
import com.svv.jys.code.module.net.api.API_Factory;

import rx.Observable;

/**
 * Created by js on 2018/6/8.
 */

public class OtcTradeModelImpl implements OtcTradeModel {
    @Override
    public Observable<BaseResponse> rx_isPublish() {
        return API_Factory.isPublish();
    }
}
