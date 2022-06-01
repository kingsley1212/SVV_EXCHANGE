package com.svv.jys.code.module.myself.myasset.countdetail.transfer.model.impl;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.myself.myasset.countdetail.transfer.model.Transfermodel;
import com.svv.jys.code.module.net.api.API_Factory;
import com.svv.jys.code.module.net.req.GET_MARKETINFO_REQ;
import com.svv.jys.code.module.net.req.GET_USER_COIN_INFO;
import com.svv.jys.code.module.net.req.POST_LT_TRANSFER_REQ;

import rx.Observable;

/**
 * Created by js on 2018/5/25.
 */

public class TransferModelImpl implements Transfermodel {
    @Override
    public Observable<BaseResponse> rx_postLttransfer(POST_LT_TRANSFER_REQ req) {
        return API_Factory.API_PostTransfer(req);
    }
    @Override
    public Observable<BaseResponse> rx_getUserCoinInfo(GET_USER_COIN_INFO req) {
        return API_Factory.GetUserCoinInfo(req);
    }
    @Override
    public Observable<BaseResponse> rx_ltUserCoinInfo(GET_MARKETINFO_REQ req) {
        return API_Factory.GetLtUserCoinInfo(req);
    }
}
