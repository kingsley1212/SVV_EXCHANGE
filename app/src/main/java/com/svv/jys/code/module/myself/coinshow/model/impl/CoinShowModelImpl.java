package com.svv.jys.code.module.myself.coinshow.model.impl;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.myself.coinshow.model.ICoinShowModel;
import com.svv.jys.code.module.net.api.API_Factory;

import rx.Observable;

/**
 * Created by Administrator on 2018/5/2 0002.
 */

public class CoinShowModelImpl implements ICoinShowModel {
    @Override
    public Observable<BaseResponse> rx_getCoin() {
        return API_Factory.API_GetCurrencyInfo();
    }
}
