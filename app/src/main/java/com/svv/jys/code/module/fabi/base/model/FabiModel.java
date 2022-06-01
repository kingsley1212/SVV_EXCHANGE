package com.svv.jys.code.module.fabi.base.model;


import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.net.req.GET_OTC_ADV_REQ;
import com.svv.jys.code.module.net.req.POST_KONG_REQ;

import rx.Observable;

/**
 * Created by js on 2018/8/1.
 */

public interface FabiModel {
    Observable<BaseResponse> rx_isPublish();
    Observable<BaseResponse> rx_otcCoin();
    Observable<BaseResponse> rx_otcADv(GET_OTC_ADV_REQ req);
    Observable<BaseResponse> getCountries(POST_KONG_REQ req);
    Observable<BaseResponse> rx_GetAdvSetting();
}
