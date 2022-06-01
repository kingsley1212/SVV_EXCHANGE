package com.svv.jys.code.module.myself.usercenter.pwd.model;


import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.net.req.POST_SAFEPSW_REQ;

import rx.Observable;

public interface IChangePwdModel {
    Observable<BaseResponse> rx_setSafePsw(POST_SAFEPSW_REQ req);
}
