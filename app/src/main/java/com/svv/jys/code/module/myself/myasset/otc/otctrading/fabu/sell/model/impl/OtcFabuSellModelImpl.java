package com.svv.jys.code.module.myself.myasset.otc.otctrading.fabu.sell.model.impl;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.myself.myasset.otc.otctrading.fabu.sell.model.IOtcFabuSellModel;
import com.svv.jys.code.module.net.api.API_Factory;
import com.svv.jys.code.module.net.req.AdvInfoReq;
import com.svv.jys.code.module.net.req.AdvPulishReq;
import com.svv.jys.code.module.net.req.YiJiaReq;

import rx.Observable;

/**
 * Created by lzj on 2018/7/24.
 */

public class OtcFabuSellModelImpl implements IOtcFabuSellModel {
    @Override
    public Observable<BaseResponse> rx_GetAdvSetting() {
        return API_Factory.GetAdvSetting();
    }

    @Override
    public Observable<BaseResponse> rx_yijia(YiJiaReq req) {
        return API_Factory.GetYijia(req);
    }

    @Override
    public Observable<BaseResponse> rx_pulish(AdvPulishReq req) {
        return API_Factory.pulish(req);
    }

    @Override
    public Observable<BaseResponse> rx_advInfo(AdvInfoReq req) {
        return API_Factory.Api_advInfo(req);
    }
}
