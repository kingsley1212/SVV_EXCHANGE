package com.svv.jys.code.module.business.coinbusiness.base.model.impl;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.business.coinbusiness.base.model.ICoinBusinessModel;
import com.svv.jys.code.module.net.api.API_Factory;
import com.svv.jys.code.module.net.req.CollectMarketReq;
import com.svv.jys.code.module.net.req.GET_MARKET_LIST_REQ;
import com.svv.jys.code.module.net.req.POST_COINBUSINESS_TRADEREVOKE_REQ;
import com.svv.jys.code.module.net.req.POST_COINBUSINESS_SUBMIT_REQ;

import rx.Observable;

/**
 * Created by Administrator on 2018/4/27 0027.
 */

public class CoinBusinessModelImpl implements ICoinBusinessModel {


    @Override
    public Observable<BaseResponse> rx_coinBusinessTradeRevoke(POST_COINBUSINESS_TRADEREVOKE_REQ req) {
        return API_Factory.API_DoCoinBusinessTradeRevoke(req);
    }


    @Override
    public Observable<BaseResponse> rx_collectMarket(CollectMarketReq req) {
        return API_Factory.collectMarket(req);
    }

    @Override
    public Observable<BaseResponse> rx_getMarket(GET_MARKET_LIST_REQ req) {
        return API_Factory.API_GetBBData(req);
    }

    @Override
    public Observable<BaseResponse> rx_getCoinBusinessAsset() {
        return API_Factory.API_DoGetUserCoin();
    }

    @Override
    public Observable<BaseResponse> rx_submit(POST_COINBUSINESS_SUBMIT_REQ req) {
        return API_Factory.API_DoCoinBusinessSubmit(req);
    }
}
