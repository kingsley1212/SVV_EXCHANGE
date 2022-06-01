package com.svv.jys.code.module.myself.myasset.jiaoyi.mentionmoney.model.impl;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.myself.myasset.jiaoyi.mentionmoney.model.MentionMoneyMode;
import com.svv.jys.code.module.net.api.API_Factory;
import com.svv.jys.code.module.net.req.GET_COININFO_REQ;
import com.svv.jys.code.module.net.req.POST_CODE_REQ;
import com.svv.jys.code.module.net.req.POST_TRANSFEROUT_REQ;

import rx.Observable;

/**
 * Created by js on 2018/5/26.
 */

public class MentionMoneyModelImpl implements MentionMoneyMode {
    @Override
    public Observable<BaseResponse> rx_TransferOut(POST_TRANSFEROUT_REQ req) {
        return API_Factory.API_PostCoinTransferOut(req);
    }
    @Override
    public Observable<BaseResponse> rx_CoinInfo(GET_COININFO_REQ req) {
        return API_Factory.API_GetCoinInfo(req);
    }


    @Override
    public Observable<BaseResponse> docode(POST_CODE_REQ req) {
        return API_Factory.API_DoCode(req);
    }

}
