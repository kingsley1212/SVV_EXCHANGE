package com.svv.jys.code.module.myself.usercenter.verify.model.impl;


import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.myself.usercenter.verify.model.IVerifyModel;
import com.svv.jys.code.module.net.api.API_Factory;
import com.svv.jys.code.module.net.req.POST_CHANGE_VERIFY;

import rx.Observable;


public class VerifyModelImpl implements IVerifyModel {

    @Override
    public Observable<BaseResponse> rx_changeVerify(POST_CHANGE_VERIFY req) {
        return API_Factory.API_CHANGEVERIFY(req);
    }
}
