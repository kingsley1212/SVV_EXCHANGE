package com.svv.jys.code.module.myself.myasset.jiaoyi.mentionmoney.model;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.net.req.GET_COININFO_REQ;
import com.svv.jys.code.module.net.req.POST_CODE_REQ;
import com.svv.jys.code.module.net.req.POST_TRANSFEROUT_REQ;

import rx.Observable;

/**
 * Created by js on 2018/5/26.
 */

public interface MentionMoneyMode {
    Observable<BaseResponse> rx_TransferOut(POST_TRANSFEROUT_REQ req);
    Observable<BaseResponse> rx_CoinInfo(GET_COININFO_REQ req);
    Observable<BaseResponse> docode(POST_CODE_REQ req);
}
