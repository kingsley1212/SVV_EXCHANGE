package com.svv.jys.code.module.myself.welfare.model;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.net.req.WelfareReq;

import rx.Observable;

public interface WelfareModel {
    Observable<BaseResponse> rx_getTangGuo(WelfareReq req);
}
