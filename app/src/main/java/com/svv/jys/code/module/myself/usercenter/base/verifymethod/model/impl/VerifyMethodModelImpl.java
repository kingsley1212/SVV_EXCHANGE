package com.svv.jys.code.module.myself.usercenter.base.verifymethod.model.impl;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.myself.usercenter.base.verifymethod.model.VerifyMethodModel;
import com.svv.jys.code.module.net.api.API_Factory;
import com.svv.jys.code.module.net.req.POST_CODE_REQ;
import com.svv.jys.code.module.net.req.VerifyMethodReq;

import rx.Observable;

/**
 * Created by js on 2018/9/7.
 */

public class VerifyMethodModelImpl implements VerifyMethodModel {
    @Override
    public Observable<BaseResponse> rx_verifyMethod(VerifyMethodReq req) {
        return API_Factory.setVerifyMethod(req);
    }
    @Override
    public Observable<BaseResponse> docode(POST_CODE_REQ req) {
        return API_Factory.API_DoCode(req);
    }
}
