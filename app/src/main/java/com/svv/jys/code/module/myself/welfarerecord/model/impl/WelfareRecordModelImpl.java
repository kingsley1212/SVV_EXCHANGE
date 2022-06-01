package com.svv.jys.code.module.myself.welfarerecord.model.impl;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.myself.welfarerecord.model.WelfareRecordModel;
import com.svv.jys.code.module.net.api.API_Factory;
import com.svv.jys.code.module.net.req.AdvInfoReq;
import com.svv.jys.code.module.net.req.WelfareReq;

import rx.Observable;

public class WelfareRecordModelImpl implements WelfareRecordModel {
    @Override
    public Observable<BaseResponse> rx_getWelfareRecord(WelfareReq req) {
        return API_Factory.Api_welfareRecord(req);
    }

    @Override
    public Observable<BaseResponse> rx_getWelfareReceive(AdvInfoReq req) {
        return API_Factory.Api_welfarereceive(req);
    }
}
