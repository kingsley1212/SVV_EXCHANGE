package com.svv.jys.code.module.myself.usercenter.securityverification.model;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.net.req.ChangePswTypeReq;

import rx.Observable;

public interface SecurityVerificationModel {
    Observable<BaseResponse> changepwdtype(ChangePswTypeReq req);
}
