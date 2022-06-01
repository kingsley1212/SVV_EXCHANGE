package com.svv.jys.code.module.business.barbusiness.base.model;

import com.svv.jys.code.common.base.net.BaseResponse;
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

public interface IBarBusinessModel {


    Observable<BaseResponse> rx_getMarketTitle(POST_KONG_REQ req);

    Observable<BaseResponse> rx_GetMarketList(GET_MARKET_LIST_REQ req);

    Observable<BaseResponse> rx_GetMyTradeList(GET_MY_TRADELIST_REQ req);
    /**
     * 根据限价提交委托
     * @param req
     * @return
     */
    Observable<BaseResponse> rx_barBusinessSubmitData(POST_BARBUSINESS_SUBMIT_REQ req);

    /**
     * 根据市价提交委托
     * @param req
     * @return
     */
    Observable<BaseResponse> rx_barBusinessSubmitDataByMarketPrice(POST_BARBUSINESS_SUBMIT_MARKETPRICE_REQ req);

    /**
     * 撤销委托
     * @param req
     * @return
     */
    Observable<BaseResponse> rx_barBusinessTradeRevoke(POST_BARBUSINESS_TRADEREVOKE_REQ req);

    /**
     * 用户杠杆资产信息
     * @param req
     * @return
     */
    Observable<BaseResponse> rx_ltUserCoinInfo(GET_MARKETINFO_REQ req);


    Observable<BaseResponse> rx_getUserCoinInfo(GET_USER_COIN_INFO req);

    Observable<BaseResponse> rx_collectMarket(CollectMarketReq req);
}
