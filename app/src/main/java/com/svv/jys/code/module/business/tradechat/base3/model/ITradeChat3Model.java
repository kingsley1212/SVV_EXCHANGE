package com.svv.jys.code.module.business.tradechat.base3.model;


import com.svv.jys.code.module.business.tradechat.base3.util.GET_OLDKLINEDATA_REQ;
import com.svv.jys.code.module.business.tradechat.base3.util.GetOldKLineDataResponse;

import rx.Observable;

/**
 * Created by lzj on 2018/6/26.
 */

public interface ITradeChat3Model {
    Observable<GetOldKLineDataResponse> rx_getoldklinedata(GET_OLDKLINEDATA_REQ req);
}
