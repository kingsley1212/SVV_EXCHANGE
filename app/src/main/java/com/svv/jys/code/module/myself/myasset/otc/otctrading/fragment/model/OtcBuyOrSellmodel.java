package com.svv.jys.code.module.myself.myasset.otc.otctrading.fragment.model;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.net.req.GET_OTC_ADV_REQ;
import com.svv.jys.code.module.net.req.POST_KONG_REQ;

import rx.Observable;

/**
 * Created by js on 2018/6/8.
 */

public interface OtcBuyOrSellmodel {
    Observable<BaseResponse> rx_otcCoin();
    Observable<BaseResponse> rx_otcADv(GET_OTC_ADV_REQ req);
    Observable<BaseResponse> getCountries(POST_KONG_REQ req);
}
