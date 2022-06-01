package com.svv.jys.code.module.myself.usercenter.securityverification.model.impl;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.myself.usercenter.securityverification.model.SecurityVerificationModel;
import com.svv.jys.code.module.net.api.API_Factory;
import com.svv.jys.code.module.net.req.ChangePswTypeReq;

import rx.Observable;

public class SecurityVerificationModelImpl implements SecurityVerificationModel {
    @Override
    public Observable<BaseResponse> changepwdtype(ChangePswTypeReq req) {
        return API_Factory.changepwdtype(req);
    }
}
