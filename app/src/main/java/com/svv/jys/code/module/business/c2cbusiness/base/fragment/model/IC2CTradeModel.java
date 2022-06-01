package com.svv.jys.code.module.business.c2cbusiness.base.fragment.model;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.net.req.C2CBuyOrSellReq;
import com.svv.jys.code.module.net.req.C2CCoinInfoReq;

import rx.Observable;

/**
 * Created by js on 2018/7/11.
 */

public interface IC2CTradeModel {
    Observable<BaseResponse> rx_c2cCoinInfo(C2CCoinInfoReq req);
    Observable<BaseResponse> rx_c2cbuyorsell(C2CBuyOrSellReq req);
}
