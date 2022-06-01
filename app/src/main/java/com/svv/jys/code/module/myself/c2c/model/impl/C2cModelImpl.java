package com.svv.jys.code.module.myself.c2c.model.impl;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.myself.c2c.model.C2cModel;
import com.svv.jys.code.module.net.api.API_Factory;
import com.svv.jys.code.module.net.req.C2CBuyOrSellReq;
import com.svv.jys.code.module.net.req.DO_C2CCANCEL_REQ;
import com.svv.jys.code.module.net.req.GET_TRADEACCOUNT_RECORD_REQ;
import com.svv.jys.code.module.net.req.GetC2CRecordListReq;

import rx.Observable;

/**
 * Created by js on 2018/5/26.
 */

public class C2cModelImpl implements C2cModel {


    @Override
    public Observable<BaseResponse> rx_getIndexCoinInfo(GET_TRADEACCOUNT_RECORD_REQ req) {
        return API_Factory.API_GetC2cIndexInfo(req);
    }

    @Override
    public Observable<BaseResponse> rx_getC2CList(GetC2CRecordListReq req) {
        return API_Factory.API_getC2CRecordList(req);
    }

    @Override
    public Observable<BaseResponse> doCancel(DO_C2CCANCEL_REQ doCancelReq) {
        return API_Factory.API_doC2CCancel(doCancelReq);
    }

    @Override
    public Observable<BaseResponse> rx_c2cbuyorsell(C2CBuyOrSellReq req) {
        return API_Factory.c2cBuyOrSell(req);
    }
}
