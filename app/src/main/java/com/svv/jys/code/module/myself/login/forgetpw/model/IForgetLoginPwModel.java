package com.svv.jys.code.module.myself.login.forgetpw.model;


import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.net.req.POST_CODE_REQ;
import com.svv.jys.code.module.net.req.POST_FORGETPSW1_REQ;

import rx.Observable;

/**
 * Created by Administrator on 2017/11/14 0014.
 */

public interface IForgetLoginPwModel {
    Observable<BaseResponse> docode(POST_CODE_REQ req);
    Observable<BaseResponse> doForgetPsw1(POST_FORGETPSW1_REQ req);
}
