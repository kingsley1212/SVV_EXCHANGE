package com.svv.jys.code.module.business.c2cbusiness.record.base.model;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.net.req.DO_C2CCANCEL_REQ;
import com.svv.jys.code.module.net.req.GetC2CRecordListReq;

import rx.Observable;

/**
 * Created by 74099 on 2018/7/11.
 */

public interface IC2CRecordModel {
    Observable<BaseResponse> getC2CRecordList(GetC2CRecordListReq recordListReq);

    Observable<BaseResponse> doCancel(DO_C2CCANCEL_REQ doCancelReq);
}
