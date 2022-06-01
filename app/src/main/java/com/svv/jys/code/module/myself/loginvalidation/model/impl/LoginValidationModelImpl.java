package com.svv.jys.code.module.myself.loginvalidation.model.impl;



import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.myself.loginvalidation.model.LoginValidationModel;
import com.svv.jys.code.module.net.api.API_Factory;
import com.svv.jys.code.module.net.req.POST_CODE_REQ;
import com.svv.jys.code.module.net.req.VerifyReq;

import rx.Observable;

/**
 * Created by js on 2018/9/14.
 */

public class LoginValidationModelImpl implements LoginValidationModel {
    @Override
    public Observable<BaseResponse> rx_yanz(VerifyReq req) {
        return API_Factory.verify(req);
    }

    @Override
    public Observable<BaseResponse> docode(POST_CODE_REQ req) {
        return API_Factory.API_DoCode(req);
    }
}
