package com.svv.jys.code.module.myself.myasset.countdetail.model.impl;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.myself.myasset.countdetail.model.ICountDetailModel;
import com.svv.jys.code.module.net.api.API_Factory;
import com.svv.jys.code.module.net.req.GET_LT_RECORD_REQ;
import com.svv.jys.code.module.net.req.GET_MARKETINFO_REQ;

import rx.Observable;

/**
 * Created by Administrator on 2018/5/3 0003.
 */

public class CountDetailModelImpl implements ICountDetailModel {
    @Override
    public Observable<BaseResponse> rx_ltrecord(GET_LT_RECORD_REQ req) {
        return API_Factory.API_GetLtRecord(req);
    }

    @Override
    public Observable<BaseResponse> rx_ltUserCoinInfo(GET_MARKETINFO_REQ req) {
        return API_Factory.GetLtUserCoinInfo(req);
    }

    @Override
    public Observable<BaseResponse> rx_ltTransferrecord(GET_LT_RECORD_REQ req) {
        return API_Factory.API_GetLtTransferRecord(req);
    }

    @Override
    public Observable<BaseResponse> rx_ltBorrowrecord(GET_LT_RECORD_REQ req) {
        return API_Factory.API_GetLtBorrowRecord(req);
    }
}
