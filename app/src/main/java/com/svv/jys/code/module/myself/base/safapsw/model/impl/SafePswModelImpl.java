package com.svv.jys.code.module.myself.base.safapsw.model.impl;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.myself.base.safapsw.model.SafePswModel;
import com.svv.jys.code.module.net.api.API_Factory;
import com.svv.jys.code.module.net.req.POST_CHANGEPSW_REQ;
import com.svv.jys.code.module.net.req.POST_CODE_REQ;
import com.svv.jys.code.module.net.req.POST_SAFEPSW_REQ;

import rx.Observable;

/**
 * Created by js on 2018/5/21.
 */

public class SafePswModelImpl implements SafePswModel {
    @Override
    public Observable<BaseResponse> docode(POST_CODE_REQ req) {
        return API_Factory.API_DoCode(req);
    }

    @Override
    public Observable<BaseResponse> rx_setSafePsw(POST_SAFEPSW_REQ req) {
        return API_Factory.API_PostSafePsw(req);
    }

    @Override
    public Observable<BaseResponse> rx_changeSafePsw(POST_CHANGEPSW_REQ req) {
        return API_Factory.API_changePassword(req);
    }
}
