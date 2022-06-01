package com.svv.jys.code.module.business.coinbusiness.base.model;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.net.req.CollectMarketReq;
import com.svv.jys.code.module.net.req.GET_MARKET_LIST_REQ;
import com.svv.jys.code.module.net.req.POST_COINBUSINESS_TRADEREVOKE_REQ;
import com.svv.jys.code.module.net.req.POST_COINBUSINESS_SUBMIT_REQ;

import rx.Observable;

/**
 * Created by Administrator on 2018/4/27 0027.
 * V 1.1.1  Update by Lzj  on 2018/8/22 0930.
 */
public interface ICoinBusinessModel {



    /**
     * 撤销委托
     *
     * @param req
     * @return
     */
    Observable<BaseResponse> rx_coinBusinessTradeRevoke(POST_COINBUSINESS_TRADEREVOKE_REQ req);


    Observable<BaseResponse> rx_submit(POST_COINBUSINESS_SUBMIT_REQ req);

    /**
     * 获取收藏币种
     * @param req
     * @return
     */
    Observable<BaseResponse> rx_collectMarket(CollectMarketReq req);

    /**
     * 获取数据
     */
    Observable<BaseResponse> rx_getMarket(GET_MARKET_LIST_REQ req);

    //货币交易资产数据
    Observable<BaseResponse> rx_getCoinBusinessAsset();


}
