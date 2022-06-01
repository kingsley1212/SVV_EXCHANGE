package com.svv.jys.code.module.myself.useridentify.base.model.impl;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.myself.useridentify.base.model.IUserIdentifyModel;
import com.svv.jys.code.module.net.api.API_Factory;
import com.svv.jys.code.module.net.req.POST_CODE_REQ;
import com.svv.jys.code.module.net.req.POST_IDENTITY_IMG_REQ;
import com.svv.jys.code.module.net.req.POST_IDENTITY_REQ;
import com.svv.jys.code.module.net.req.POST_REMOVEFILE_REQ;

import rx.Observable;

/**
 * Created by Administrator on 2018/5/2 0002.
 */

public class UserIdentifyModelImpl implements IUserIdentifyModel {
    @Override
    public Observable<BaseResponse> rx_postIdentify(POST_IDENTITY_REQ req) {
        return API_Factory.API_DoIdentity(req);
    }
    @Override
    public Observable<BaseResponse> docode(POST_CODE_REQ req) {
        return API_Factory.API_DoCode(req);
    }

    @Override
    public Observable<BaseResponse> rx_upImg(POST_IDENTITY_IMG_REQ req) {
        return API_Factory.API_UploadImage(req);
    }

    @Override
    public Observable<BaseResponse> rx_removeFile(POST_REMOVEFILE_REQ req) {
        return API_Factory.API_RemoveFile(req);
    }
}
