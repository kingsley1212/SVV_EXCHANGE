package com.svv.jys.code.module.myself.usercenter.base.pay.addbank.model.impl;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.myself.usercenter.base.pay.addbank.model.AddBankModel;
import com.svv.jys.code.module.net.api.API_Factory;
import com.svv.jys.code.module.net.req.AddPayReq;

import rx.Observable;

public class AddBankModelImpl implements AddBankModel {
    @Override
    public Observable<BaseResponse> rx_addpay(AddPayReq req) {
        return API_Factory.addOrEditPayMethod(req);
    }
}
