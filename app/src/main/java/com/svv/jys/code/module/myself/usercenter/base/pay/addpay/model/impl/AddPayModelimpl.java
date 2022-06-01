package com.svv.jys.code.module.myself.usercenter.base.pay.addpay.model.impl;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.myself.usercenter.base.pay.addpay.model.AddPayModel;
import com.svv.jys.code.module.net.api.API_Factory;
import com.svv.jys.code.module.net.req.AddPayReq;
import com.svv.jys.code.module.net.req.POST_VERIFY_PAYMENT;

import rx.Observable;

/**
 * Created by js on 2018/6/16.
 */

public class AddPayModelimpl implements AddPayModel {
    @Override
    public Observable<BaseResponse> rx_addpay(AddPayReq req) {
        return API_Factory.addOrEditPayMethod(req);
    }

    @Override
    public Observable<BaseResponse> rx_payMethod() {
        return API_Factory.payMethod();
    }

    @Override
    public Observable<BaseResponse> rx_VerifyPayMethod(POST_VERIFY_PAYMENT req) {
        return API_Factory.API_VerifyPayment(req);
    }
}
