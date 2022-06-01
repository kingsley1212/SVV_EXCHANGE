package com.svv.jys.code.module.myself.usercenter.verify.model;


import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.net.req.POST_CHANGE_VERIFY;

import rx.Observable;

public interface IVerifyModel {
    Observable<BaseResponse> rx_changeVerify(POST_CHANGE_VERIFY req);
}
