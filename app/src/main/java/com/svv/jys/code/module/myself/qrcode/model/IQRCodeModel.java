package com.svv.jys.code.module.myself.qrcode.model;

import com.svv.jys.code.common.base.net.BaseResponse;

import rx.Observable;

/**
 * Created by Administrator on 2018/5/3 0003.
 */

public interface IQRCodeModel {
    Observable<BaseResponse> rx_InviteImages();
}
