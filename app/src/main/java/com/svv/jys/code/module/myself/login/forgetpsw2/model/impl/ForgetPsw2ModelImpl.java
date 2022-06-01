package com.svv.jys.code.module.myself.login.forgetpsw2.model.impl;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.myself.login.forgetpsw2.model.ForgetPsw2Model;
import com.svv.jys.code.module.net.api.API_Factory;
import com.svv.jys.code.module.net.req.POST_CODE_REQ;
import com.svv.jys.code.module.net.req.POST_FORGETPSW2_REQ;

import rx.Observable;

/**
 * Created by js on 2018/5/21.
 */

public class ForgetPsw2ModelImpl implements ForgetPsw2Model{
    @Override
    public Observable<BaseResponse> doForgetPsw2(POST_FORGETPSW2_REQ req) {
        return API_Factory.API_PostForgerPsw2(req);
    }

    @Override
    public Observable<BaseResponse> docode(POST_CODE_REQ req) {
        return API_Factory.API_DoCode(req);
    }
}
