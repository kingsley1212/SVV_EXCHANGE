package com.svv.jys.code.module.business.tradechat.base3.model.impl;


import com.svv.jys.code.module.business.tradechat.base3.model.ITradeChat3Model;
import com.svv.jys.code.module.business.tradechat.base3.util.GET_OLDKLINEDATA_REQ;
import com.svv.jys.code.module.business.tradechat.base3.util.GetOldKLineDataResponse;
import com.svv.jys.code.module.business.tradechat.base3.util.k_API;

import rx.Observable;

/**
 * Created by lzj on 2018/6/26.
 */

public class TradeChat3ModelImpl implements ITradeChat3Model {
    @Override
    public Observable<GetOldKLineDataResponse> rx_getoldklinedata(GET_OLDKLINEDATA_REQ req) {
        return k_API.GetOldKlineData(req);
    }
}
