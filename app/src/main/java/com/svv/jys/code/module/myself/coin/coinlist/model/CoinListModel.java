package com.svv.jys.code.module.myself.coin.coinlist.model;


import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.net.req.GET_COININFO_REQ;
import com.svv.jys.code.module.net.req.GET_USER_COIN_INFO;

import rx.Observable;

/**
 * Created by LB on 2018/3/2.
 */

public interface CoinListModel {
    Observable<BaseResponse> rx_setAddress(GET_USER_COIN_INFO req);
    Observable<BaseResponse> rx_getCoinList();
    Observable<BaseResponse> rx_CoinInfo(GET_COININFO_REQ req);
}
