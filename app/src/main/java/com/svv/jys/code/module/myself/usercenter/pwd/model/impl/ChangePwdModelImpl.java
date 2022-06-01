package com.svv.jys.code.module.myself.usercenter.pwd.model.impl;


import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.myself.usercenter.pwd.model.IChangePwdModel;
import com.svv.jys.code.module.net.api.API_Factory;
import com.svv.jys.code.module.net.req.POST_SAFEPSW_REQ;

import rx.Observable;


public class ChangePwdModelImpl implements IChangePwdModel {

    @Override
    public Observable<BaseResponse> rx_setSafePsw(POST_SAFEPSW_REQ req) {
        return API_Factory.API_PostSafePsw(req);
    }
}
