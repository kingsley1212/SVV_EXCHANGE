package com.svv.jys.code.module.myself.myasset.jiaoyi.chargemoney.model.impl;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.myself.myasset.jiaoyi.chargemoney.model.ChargeMoneyModel;
import com.svv.jys.code.module.net.api.API_Factory;
import com.svv.jys.code.module.net.req.GET_USER_COIN_INFO;

import rx.Observable;

/**
 * Created by js on 2018/5/26.
 */

public class ChargeMoneyModelimpl implements ChargeMoneyModel {
    @Override
    public Observable<BaseResponse> rx_setAddress(GET_USER_COIN_INFO req) {
        return API_Factory.API_SetAddress(req);
    }
}
