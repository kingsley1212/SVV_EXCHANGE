package com.svv.jys.code.module.business.barbusiness.base.model.impl;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.business.barbusiness.base.model.IBarBusinessModel;
import com.svv.jys.code.module.net.api.API_Factory;
import com.svv.jys.code.module.net.req.CollectMarketReq;
import com.svv.jys.code.module.net.req.GET_MARKETINFO_REQ;
import com.svv.jys.code.module.net.req.GET_MARKET_LIST_REQ;
import com.svv.jys.code.module.net.req.GET_MY_TRADELIST_REQ;
import com.svv.jys.code.module.net.req.GET_USER_COIN_INFO;
import com.svv.jys.code.module.net.req.POST_BARBUSINESS_SUBMIT_MARKETPRICE_REQ;
import com.svv.jys.code.module.net.req.POST_BARBUSINESS_SUBMIT_REQ;
import com.svv.jys.code.module.net.req.POST_BARBUSINESS_TRADEREVOKE_REQ;
import com.svv.jys.code.module.net.req.POST_KONG_REQ;

import rx.Observable;

/**
 * Created by Administrator on 2018/4/28 0028.
 */

public class BarBusinessModelImpl implements IBarBusinessModel {

    @Override
    public Observable<BaseResponse> rx_getMarketTitle(POST_KONG_REQ req) {
        return API_Factory.API_DoGetMarketArea(req);
    }
    @Override
    public Observable<BaseResponse> rx_GetMarketList(GET_MARKET_LIST_REQ req) {
        return API_Factory.API_DoGetMarketList(req);
    }


    @Override
    public Observable<BaseResponse> rx_GetMyTradeList(GET_MY_TRADELIST_REQ req) {
        return API_Factory.API_DoGetMyTradeList(req);
    }

    @Override
    public Observable<BaseResponse> rx_barBusinessSubmitData(POST_BARBUSINESS_SUBMIT_REQ req) {
        return API_Factory.API_DoBarBusinessSubmit(req);
    }

    @Override
    public Observable<BaseResponse> rx_barBusinessSubmitDataByMarketPrice(POST_BARBUSINESS_SUBMIT_MARKETPRICE_REQ req) {
        return API_Factory.API_DoBarBusinessSubmitByMarketPrice(req);
    }

    @Override
    public Observable<BaseResponse> rx_barBusinessTradeRevoke(POST_BARBUSINESS_TRADEREVOKE_REQ req) {
        return  API_Factory.API_DoBarBusinessTradeRevoke(req);
    }

    @Override
    public Observable<BaseResponse> rx_ltUserCoinInfo(GET_MARKETINFO_REQ req) {
        return API_Factory.GetLtUserCoinInfo(req);
    }

    @Override
    public Observable<BaseResponse> rx_getUserCoinInfo(GET_USER_COIN_INFO req) {
        return API_Factory.GetUserCoinInfo(req);
    }
    @Override
    public Observable<BaseResponse> rx_collectMarket(CollectMarketReq req) {
        return API_Factory.collectMarket(req);
    }
}
