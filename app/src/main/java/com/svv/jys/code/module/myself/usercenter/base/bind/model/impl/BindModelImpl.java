package com.svv.jys.code.module.myself.usercenter.base.bind.model.impl;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.myself.usercenter.base.bind.model.BindModel;
import com.svv.jys.code.module.net.api.API_Factory;
import com.svv.jys.code.module.net.req.POST_BINDEMAIL_REQ;
import com.svv.jys.code.module.net.req.POST_BINDPHONE_REQ;
import com.svv.jys.code.module.net.req.POST_CODE_REQ;
import com.svv.jys.code.module.net.req.POST_KONG_REQ;

import rx.Observable;

/**
 * Created by js on 2018/5/30.
 */

public class BindModelImpl implements BindModel {
    @Override
    public Observable<BaseResponse> rx_bindEmail(POST_BINDEMAIL_REQ req) {
        return API_Factory.API_bindEmail(req);
    }
    @Override
    public Observable<BaseResponse> docode(POST_CODE_REQ req) {
        return API_Factory.API_DoCode(req);
    }
    @Override
    public Observable<BaseResponse> getCountries(POST_KONG_REQ req) {
        return API_Factory.API_GetCountries(req);
    }

    @Override
    public Observable<BaseResponse> rx_bindPhone(POST_BINDPHONE_REQ req) {
        return API_Factory.API_bindPhone(req);
    }
}
