package com.svv.jys.code.module.myself.coin.coinlist.model.impl;


import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.myself.coin.coinlist.model.CoinListModel;
import com.svv.jys.code.module.net.api.API_Factory;
import com.svv.jys.code.module.net.req.GET_COININFO_REQ;
import com.svv.jys.code.module.net.req.GET_USER_COIN_INFO;

import rx.Observable;

/**
 * Created by LB on 2018/3/2.
 */

public class CoinListModelImpl implements CoinListModel {

    @Override
    public Observable<BaseResponse> rx_setAddress(GET_USER_COIN_INFO req) {
        return API_Factory.API_SetAddress(req);
    }

    @Override
    public Observable<BaseResponse> rx_getCoinList() {
        return API_Factory.API_GetCoinList();
    }

    @Override
    public Observable<BaseResponse> rx_CoinInfo(GET_COININFO_REQ req) {
        return API_Factory.API_GetCoinInfo(req);
    }
}
