package com.svv.jys.code.module.myself.ordermanage.base.model.impl;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.myself.ordermanage.base.model.IOrderManagerModel;
import com.svv.jys.code.module.net.api.API_Factory;

import rx.Observable;

/**
 * Created by Administrator on 2018/5/2 0002.
 */

public class OrderManagerModelImpl implements IOrderManagerModel {
    @Override
    public Observable<BaseResponse> rx_getExchange() {
        return API_Factory.API_GetExchangeArea();
    }
}
