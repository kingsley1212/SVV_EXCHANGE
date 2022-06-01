package com.svv.jys.code.module.myself.bank.base.model;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.net.req.BANKADDRESS_DELETE_REQ;

import rx.Observable;


/**
 * Created by js on 2018/7/11.
 */

public interface IBankManagerModel {
    Observable<BaseResponse> rx_banklist();

    Observable<BaseResponse> deleteBankMes(BANKADDRESS_DELETE_REQ addressDeleteReq);
}
