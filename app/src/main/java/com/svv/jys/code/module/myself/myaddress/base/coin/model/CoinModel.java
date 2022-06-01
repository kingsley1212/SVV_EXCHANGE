package com.svv.jys.code.module.myself.myaddress.base.coin.model;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.net.req.POST_KONG_REQ;

import rx.Observable;

/**
 * Created by js on 2018/5/19.
 */

public interface CoinModel {
    Observable<BaseResponse> rx_coinList(POST_KONG_REQ req);
}
