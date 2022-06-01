package com.svv.jys.code.module.myself.usercenter.base.verifymethod.model;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.net.req.POST_CODE_REQ;
import com.svv.jys.code.module.net.req.VerifyMethodReq;

import rx.Observable;

/**
 * Created by js on 2018/9/7.
 */

public interface VerifyMethodModel {
    Observable<BaseResponse> rx_verifyMethod(VerifyMethodReq req);
    Observable<BaseResponse> docode(POST_CODE_REQ req);
}
