package com.svv.jys.code.module.business.c2cbusiness.base.model;

import com.svv.jys.code.common.base.net.BaseResponse;
import rx.Observable;

/**
 * Created by js on 2018/7/11.
 */

public interface IC2CTradeModel {
    Observable<BaseResponse> rx_c2ctrademarkdet();
}
