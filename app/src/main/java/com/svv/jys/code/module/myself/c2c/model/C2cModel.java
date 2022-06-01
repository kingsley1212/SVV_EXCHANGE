package com.svv.jys.code.module.myself.c2c.model;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.net.req.C2CBuyOrSellReq;
import com.svv.jys.code.module.net.req.DO_C2CCANCEL_REQ;
import com.svv.jys.code.module.net.req.GET_TRADEACCOUNT_RECORD_REQ;
import com.svv.jys.code.module.net.req.GetC2CRecordListReq;

import rx.Observable;

/**
 * Created by js on 2018/5/26.
 */

public interface C2cModel {
    Observable<BaseResponse> rx_getIndexCoinInfo(GET_TRADEACCOUNT_RECORD_REQ req);
    Observable<BaseResponse> rx_getC2CList(GetC2CRecordListReq req);
    Observable<BaseResponse> doCancel(DO_C2CCANCEL_REQ doCancelReq);
    Observable<BaseResponse> rx_c2cbuyorsell(C2CBuyOrSellReq req);
}
