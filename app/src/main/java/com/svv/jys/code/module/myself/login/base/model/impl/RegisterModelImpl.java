package com.svv.jys.code.module.myself.login.base.model.impl;


import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.myself.login.base.model.IRegisterModel;
import com.svv.jys.code.module.net.api.API_Factory;
import com.svv.jys.code.module.net.req.POST_CODE_REQ;
import com.svv.jys.code.module.net.req.POST_KONG_REQ;
import com.svv.jys.code.module.net.req.POST_REGISTER_REQ;

import rx.Observable;

/**
 * Created by Administrator on 2017/10/21 0021.
 */

public class RegisterModelImpl implements IRegisterModel {

    @Override
    public rx.Observable<BaseResponse> doRegister(POST_REGISTER_REQ req) {
        return API_Factory.API_DoRegister(req);
    }

    @Override
    public Observable<BaseResponse> docode(POST_CODE_REQ req) {
        return API_Factory.API_DoCode(req);
    }

    @Override
    public Observable<BaseResponse> getCountries(POST_KONG_REQ req) {
        return API_Factory.API_GetCountries(req);
    }
}
