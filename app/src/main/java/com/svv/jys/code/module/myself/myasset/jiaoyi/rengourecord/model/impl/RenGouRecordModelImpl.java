package com.svv.jys.code.module.myself.myasset.jiaoyi.rengourecord.model.impl;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.myself.myasset.jiaoyi.rengourecord.model.RenGouRecordModel;
import com.svv.jys.code.module.net.api.API_Factory;
import com.svv.jys.code.module.net.req.RenGouChexiaoReq;
import com.svv.jys.code.module.net.req.RenGouRecordReq;

import rx.Observable;

/**
 * Created by js on 2018/10/19.
 */

public class RenGouRecordModelImpl implements RenGouRecordModel {
    @Override
    public Observable<BaseResponse> rx_rengourecord(RenGouRecordReq req) {
        return API_Factory.rengourecord(req);
    }

    @Override
    public Observable<BaseResponse> rx_chexiao(RenGouChexiaoReq req) {
        return API_Factory.rengourechexiao(req);
    }
}
