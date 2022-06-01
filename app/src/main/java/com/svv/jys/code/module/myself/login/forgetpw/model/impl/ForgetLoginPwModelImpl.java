package com.svv.jys.code.module.myself.login.forgetpw.model.impl;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.myself.login.forgetpw.model.IForgetLoginPwModel;
import com.svv.jys.code.module.net.api.API_Factory;
import com.svv.jys.code.module.net.req.POST_CODE_REQ;
import com.svv.jys.code.module.net.req.POST_FORGETPSW1_REQ;

import rx.Observable;

/**
 * Created by Administrator on 2017/11/14 0014.
 */

public class ForgetLoginPwModelImpl implements IForgetLoginPwModel {

    @Override
    public Observable<BaseResponse> doForgetPsw1(POST_FORGETPSW1_REQ req) {
        return API_Factory.API_PostForgerPsw1(req);
    }

    @Override
    public Observable<BaseResponse> docode(POST_CODE_REQ req) {
        return API_Factory.API_DoCode(req);
    }
}
