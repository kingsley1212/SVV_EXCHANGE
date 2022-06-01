package com.svv.jys.code.module.myself.myasset.jiaoyi.rengou.model.impl;


import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.myself.myasset.jiaoyi.rengou.model.RenGouModel;
import com.svv.jys.code.module.net.api.API_Factory;
import com.svv.jys.code.module.net.req.RenGouRea;

import rx.Observable;

/**
 * Created by js on 2018/10/19.
 */

public class RenGouModelImpl implements RenGouModel {
    @Override
    public Observable<BaseResponse> rx_rengou(RenGouRea req) {
        return API_Factory.rengou(req);
    }
}
