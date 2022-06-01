package com.svv.jys.code.module.myself.myasset.countdetail.borrow.model.impl;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.myself.myasset.countdetail.borrow.model.BorrowModel;
import com.svv.jys.code.module.net.api.API_Factory;
import com.svv.jys.code.module.net.req.GET_MARKETINFO_REQ;
import com.svv.jys.code.module.net.req.POST_LT_DEBIT_REQ;

import rx.Observable;

/**
 * Created by js on 2018/5/25.
 */

public class BorrowModelImpl implements BorrowModel {
    @Override
    public Observable<BaseResponse> rx_getMarketinfo(GET_MARKETINFO_REQ req) {
        return API_Factory.API_GetMarketInfo(req);
    }

    @Override
    public Observable<BaseResponse> rx_postToDebit(POST_LT_DEBIT_REQ req) {
        return API_Factory.postLtDebitApply(req);
    }
}
