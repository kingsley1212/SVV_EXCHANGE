package com.svv.jys.code.module.myself.myasset.otc.otctrading.fabu.sell.model;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.net.req.AdvInfoReq;
import com.svv.jys.code.module.net.req.AdvPulishReq;
import com.svv.jys.code.module.net.req.YiJiaReq;

import rx.Observable;

/**
 * Created by lzj on 2018/7/24.
 */

public interface IOtcFabuSellModel {
    Observable<BaseResponse> rx_GetAdvSetting();
    Observable<BaseResponse> rx_yijia(YiJiaReq req);
    Observable<BaseResponse> rx_pulish(AdvPulishReq req);
    Observable<BaseResponse> rx_advInfo(AdvInfoReq req);
}
