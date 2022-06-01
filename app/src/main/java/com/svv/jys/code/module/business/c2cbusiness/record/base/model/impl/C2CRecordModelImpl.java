package com.svv.jys.code.module.business.c2cbusiness.record.base.model.impl;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.business.c2cbusiness.record.base.model.IC2CRecordModel;
import com.svv.jys.code.module.net.api.API_Factory;
import com.svv.jys.code.module.net.req.DO_C2CCANCEL_REQ;
import com.svv.jys.code.module.net.req.GetC2CRecordListReq;

import rx.Observable;

/**
 * Created by 74099 on 2018/7/11.
 */

public class C2CRecordModelImpl implements IC2CRecordModel {
    @Override
    public Observable<BaseResponse> getC2CRecordList(GetC2CRecordListReq recordListReq) {
        return API_Factory.API_getC2CRecordList(recordListReq);
    }

    @Override
    public Observable<BaseResponse> doCancel(DO_C2CCANCEL_REQ doCancelReq) {
        return API_Factory.API_doC2CCancel(doCancelReq);
    }
}
