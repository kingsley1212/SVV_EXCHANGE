package com.svv.jys.code.module.myself.usercenter.base.pay.model.ipml;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.myself.usercenter.base.pay.model.PayMethedModel;
import com.svv.jys.code.module.net.api.API_Factory;
import com.svv.jys.code.module.net.req.BankStatusReq;
import com.svv.jys.code.module.net.req.DeletePayReq;
import com.svv.jys.code.module.net.req.PayMethodReq;

import rx.Observable;

/**
 * Created by js on 2018/6/16.
 */

public class PayMethedModelImpl implements PayMethedModel {
    @Override
    public Observable<BaseResponse> rx_paymethod(PayMethodReq req) {
        return API_Factory.paymethod(req);
    }

    @Override
    public Observable<BaseResponse> rx_deletepaymethod(DeletePayReq req) {
        return API_Factory.deletePayMethod(req);
    }

    @Override
    public Observable<BaseResponse> rx_bankStatus(BankStatusReq req) {
        return API_Factory.setBankStatus(req);
    }
}
