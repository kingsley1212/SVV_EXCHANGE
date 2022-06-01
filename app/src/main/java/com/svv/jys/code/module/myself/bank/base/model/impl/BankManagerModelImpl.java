package com.svv.jys.code.module.myself.bank.base.model.impl;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.myself.bank.base.model.IBankManagerModel;
import com.svv.jys.code.module.net.api.API_Factory;
import com.svv.jys.code.module.net.req.BANKADDRESS_DELETE_REQ;

import rx.Observable;

/**
 * Created by js on 2018/7/11.
 */

public class BankManagerModelImpl implements IBankManagerModel {
    @Override
    public Observable<BaseResponse> rx_banklist() {
        return API_Factory.banklist();
    }

    @Override
    public Observable<BaseResponse> deleteBankMes(BANKADDRESS_DELETE_REQ addressDeleteReq) {
        return API_Factory.API_deleteBankMes(addressDeleteReq);
    }
}
