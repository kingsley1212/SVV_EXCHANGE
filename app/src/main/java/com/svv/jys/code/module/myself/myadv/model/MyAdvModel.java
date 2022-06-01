package com.svv.jys.code.module.myself.myadv.model;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.net.req.AdvListReq;
import com.svv.jys.code.module.net.req.SetStatusReq;

import rx.Observable;

/**
 * Created by js on 2018/7/27.
 */

public interface MyAdvModel {
    Observable<BaseResponse> rx_advList(AdvListReq req);
    Observable<BaseResponse> rx_setStatus(SetStatusReq req);
}
