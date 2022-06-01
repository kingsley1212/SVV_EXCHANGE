package com.svv.jys.code.module.myself.rname.model;


import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.net.req.POST_R_NAME;

import rx.Observable;

public interface IRNameModel {
    Observable<BaseResponse>  rx_setName(POST_R_NAME req);
}
