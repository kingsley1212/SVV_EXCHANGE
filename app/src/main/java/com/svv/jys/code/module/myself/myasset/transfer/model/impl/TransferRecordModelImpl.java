package com.svv.jys.code.module.myself.myasset.transfer.model.impl;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.myself.myasset.transfer.model.TransferRecordModel;
import com.svv.jys.code.module.net.api.API_Factory;
import com.svv.jys.code.module.net.req.PayMethodReq;

import rx.Observable;

/**
 * Created by js on 2018/6/7.
 */

public class TransferRecordModelImpl implements TransferRecordModel {

    @Override
    public Observable<BaseResponse> rx_getTransferRecord(PayMethodReq req) {
        return API_Factory.API_TransferRecord(req);
    }
}
