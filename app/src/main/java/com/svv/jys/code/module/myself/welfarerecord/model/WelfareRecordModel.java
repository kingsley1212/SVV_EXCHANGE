package com.svv.jys.code.module.myself.welfarerecord.model;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.net.req.AdvInfoReq;
import com.svv.jys.code.module.net.req.WelfareReq;

import rx.Observable;

public interface WelfareRecordModel {
    Observable<BaseResponse> rx_getWelfareRecord(WelfareReq req);
    Observable<BaseResponse> rx_getWelfareReceive(AdvInfoReq req);
}
