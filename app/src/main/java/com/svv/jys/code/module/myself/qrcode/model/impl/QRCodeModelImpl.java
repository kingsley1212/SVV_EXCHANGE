package com.svv.jys.code.module.myself.qrcode.model.impl;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.myself.qrcode.model.IQRCodeModel;
import com.svv.jys.code.module.net.api.API_Factory;

import rx.Observable;

/**
 * Created by Administrator on 2018/5/3 0003.
 */

public class QRCodeModelImpl implements IQRCodeModel {
    @Override
    public Observable<BaseResponse> rx_InviteImages() {
        return API_Factory.API_GetInviteImages();
    }
}
