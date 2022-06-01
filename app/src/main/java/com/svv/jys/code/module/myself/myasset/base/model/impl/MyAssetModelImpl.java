package com.svv.jys.code.module.myself.myasset.base.model.impl;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.myself.myasset.base.model.IMyAssetModel;
import com.svv.jys.code.module.net.api.API_Factory;
import com.svv.jys.code.module.net.req.GET_BARBUSINESS_ASSET_REQ;
import com.svv.jys.code.module.net.req.GET_FBBUSINESS_ASSET_REQ;

import rx.Observable;

/**
 * Created by Administrator on 2018/5/2 0002.
 */

public class MyAssetModelImpl implements IMyAssetModel {
    @Override
    public Observable<BaseResponse> rx_getUserCoin() {
        return API_Factory.API_DoGetUserCoin();
    }

    @Override
    public Observable<BaseResponse> rx_getLtUserCoin(GET_BARBUSINESS_ASSET_REQ req) {
        return API_Factory.API_GetLtUserCoin(req);
    }

    @Override
    public Observable<BaseResponse> rx_getqtcUserCoin(GET_FBBUSINESS_ASSET_REQ req) {
        return API_Factory.API_GetQtcUserCoin(req);
    }

}
