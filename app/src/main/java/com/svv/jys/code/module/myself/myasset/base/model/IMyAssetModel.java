package com.svv.jys.code.module.myself.myasset.base.model;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.net.req.GET_BARBUSINESS_ASSET_REQ;
import com.svv.jys.code.module.net.req.GET_FBBUSINESS_ASSET_REQ;

import rx.Observable;

/**
 * Created by Administrator on 2018/5/2 0002.
 */

public interface IMyAssetModel {
    Observable<BaseResponse> rx_getUserCoin();

    Observable<BaseResponse> rx_getLtUserCoin(GET_BARBUSINESS_ASSET_REQ req);

    Observable<BaseResponse> rx_getqtcUserCoin(GET_FBBUSINESS_ASSET_REQ req);
}
