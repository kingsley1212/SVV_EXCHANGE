package com.svv.jys.code.module.myself.myasset.transfer.model;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.net.req.PayMethodReq;

import rx.Observable;

/**
 * Created by js on 2018/6/7.
 */

public interface TransferRecordModel {
    Observable<BaseResponse> rx_getTransferRecord(PayMethodReq req);
}
