package com.svv.jys.code.module.myself.usercenter.base.pay.addbank.model;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.net.req.AddPayReq;

import rx.Observable;

public interface AddBankModel {
    Observable<BaseResponse> rx_addpay(AddPayReq req);
}
