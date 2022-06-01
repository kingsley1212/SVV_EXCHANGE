package com.svv.jys.code.module.myself.login.forgetpsw2.model;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.net.req.POST_CODE_REQ;
import com.svv.jys.code.module.net.req.POST_FORGETPSW2_REQ;

import rx.Observable;

/**
 * Created by js on 2018/5/21.
 */

public interface ForgetPsw2Model {
    Observable<BaseResponse> doForgetPsw2(POST_FORGETPSW2_REQ req);
    Observable<BaseResponse> docode(POST_CODE_REQ req);
}
