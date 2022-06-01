package com.svv.jys.code.module.myself.loginvalidation.model;



import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.net.req.POST_CODE_REQ;
import com.svv.jys.code.module.net.req.VerifyReq;

import rx.Observable;

/**
 * Created by js on 2018/9/14.
 */

public interface LoginValidationModel {
    Observable<BaseResponse> rx_yanz(VerifyReq req);
    Observable<BaseResponse> docode(POST_CODE_REQ req);
}
