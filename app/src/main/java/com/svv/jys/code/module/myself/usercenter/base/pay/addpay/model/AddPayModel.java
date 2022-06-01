package com.svv.jys.code.module.myself.usercenter.base.pay.addpay.model;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.net.req.AddPayReq;
import com.svv.jys.code.module.net.req.POST_VERIFY_PAYMENT;

import rx.Observable;

/**
 * Created by js on 2018/6/16.
 */

public interface AddPayModel {
    Observable<BaseResponse> rx_addpay(AddPayReq req);
    Observable<BaseResponse> rx_payMethod();
    Observable<BaseResponse> rx_VerifyPayMethod(POST_VERIFY_PAYMENT req);
}
