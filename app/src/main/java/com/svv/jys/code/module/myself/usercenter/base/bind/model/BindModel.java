package com.svv.jys.code.module.myself.usercenter.base.bind.model;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.net.req.POST_BINDEMAIL_REQ;
import com.svv.jys.code.module.net.req.POST_BINDPHONE_REQ;
import com.svv.jys.code.module.net.req.POST_CODE_REQ;
import com.svv.jys.code.module.net.req.POST_KONG_REQ;

import rx.Observable;

/**
 * Created by js on 2018/5/30.
 */

public interface BindModel {
    Observable<BaseResponse> rx_bindEmail(POST_BINDEMAIL_REQ req);
    Observable<BaseResponse> docode(POST_CODE_REQ req);
    Observable<BaseResponse> getCountries(POST_KONG_REQ req);
    Observable<BaseResponse> rx_bindPhone(POST_BINDPHONE_REQ req);
}
