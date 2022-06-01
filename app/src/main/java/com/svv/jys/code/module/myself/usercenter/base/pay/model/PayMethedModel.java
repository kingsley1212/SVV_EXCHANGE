package com.svv.jys.code.module.myself.usercenter.base.pay.model;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.net.req.BankStatusReq;
import com.svv.jys.code.module.net.req.DeletePayReq;
import com.svv.jys.code.module.net.req.PayMethodReq;

import rx.Observable;

/**
 * Created by js on 2018/6/16.
 */

public interface PayMethedModel {
    Observable<BaseResponse> rx_paymethod(PayMethodReq req);
    Observable<BaseResponse> rx_deletepaymethod(DeletePayReq req);
    Observable<BaseResponse> rx_bankStatus(BankStatusReq req);
}
