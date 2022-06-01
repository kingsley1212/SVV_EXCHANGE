package com.svv.jys.code.module.myself.myaddress.base.coin.model.impl;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.myself.myaddress.base.coin.model.CoinModel;
import com.svv.jys.code.module.net.api.API_Factory;
import com.svv.jys.code.module.net.req.POST_KONG_REQ;

import rx.Observable;

/**
 * Created by js on 2018/5/19.
 */

public class CoinModelimpl implements CoinModel {
    @Override
    public Observable<BaseResponse> rx_coinList(POST_KONG_REQ req) {
        return API_Factory.API_DoGetAllCoins(req);
    }
}
