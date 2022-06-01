package com.svv.jys.code.module.myself.usercenter.base.pay.addzfborwx.model.impl;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.myself.usercenter.base.pay.addzfborwx.model.AddZfbOrWxModel;
import com.svv.jys.code.module.net.api.API_Factory;
import com.svv.jys.code.module.net.req.AddPayReq;
import com.svv.jys.code.module.net.req.POST_IDENTITY_IMG_REQ;

import rx.Observable;

public class AddZfbOrWxModelImpl implements AddZfbOrWxModel {
    @Override
    public Observable<BaseResponse> rx_upImg(POST_IDENTITY_IMG_REQ req) {
        return API_Factory.Api_payCode(req);
    }

    @Override
    public Observable<BaseResponse> rx_addpay(AddPayReq req) {
        return API_Factory.addOrEditPayMethod(req);
    }
}
