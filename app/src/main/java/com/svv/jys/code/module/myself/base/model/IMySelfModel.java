package com.svv.jys.code.module.myself.base.model;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.net.req.POST_UPDATA_IMG_REQ;

import rx.Observable;

/**
 * Created by Administrator on 2018/4/27 0027.
 */

public interface IMySelfModel {

    Observable<BaseResponse> rx_getUserInfo();
    Observable<BaseResponse> rx_updataImg(POST_UPDATA_IMG_REQ req);
    Observable<BaseResponse> rx_loginout();
    Observable<BaseResponse> rx_IdentityInfo();
}
