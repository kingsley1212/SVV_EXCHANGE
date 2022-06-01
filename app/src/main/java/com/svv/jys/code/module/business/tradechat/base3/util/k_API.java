package com.svv.jys.code.module.business.tradechat.base3.util;


import com.svv.jys.code.common.base.net.BaseRXNetApi;

import rx.Observable;

public class k_API {
    /**
     * 获取C2C交易区域
     */
    public static Observable<GetOldKLineDataResponse> GetOldKlineData(GET_OLDKLINEDATA_REQ req) {
        return BaseRXNetApi.rx_doExecuteQuick(K_URL.oldklinelistdata, BaseRXNetApi.RXExecuteType.POST, req, GetOldKLineDataResponse
                .class);
    }
}
