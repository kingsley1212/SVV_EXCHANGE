package com.svv.jys.code.module.myself.myasset.jiaoyi.suocang.model.impl;


import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.myself.myasset.jiaoyi.suocang.model.SuoCangModel;
import com.svv.jys.code.module.net.api.API_Factory;
import com.svv.jys.code.module.net.req.SuoCangReq;

import rx.Observable;

/**
 * Created by js on 2018/8/10.
 */

public class SuoCangModelImpl implements SuoCangModel {


    @Override
    public Observable<BaseResponse> rx_postSuocang(SuoCangReq req) {
        return API_Factory.suocang(req);
    }

    @Override
    public Observable<BaseResponse> rx_postLiXi(SuoCangReq req) {
        return API_Factory.getLiXi(req);
    }
}
