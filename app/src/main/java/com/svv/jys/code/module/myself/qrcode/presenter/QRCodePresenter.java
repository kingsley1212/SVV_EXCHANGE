package com.svv.jys.code.module.myself.qrcode.presenter;

import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.qrcode.model.IQRCodeModel;
import com.svv.jys.code.module.myself.qrcode.model.impl.QRCodeModelImpl;
import com.svv.jys.code.module.myself.qrcode.view.IQRCodeView;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/5/3 0003.
 */

public class QRCodePresenter extends BasePresent<IQRCodeView, IQRCodeModel> {
    public QRCodePresenter() {
        model = new QRCodeModelImpl();
    }
    public void getInviteImages(){
        model.rx_InviteImages()
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        showLoading(view.getMContext());
                    }
                }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io()).map(new Func1<BaseResponse, String>() {
            @Override
            public String call(BaseResponse baseResponse) {
                return baseResponse.datas;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                dismissLoading(view.getMContext());
            }

            @Override
            public void onError(Throwable e) {
                dismissLoading(view.getMContext());
                ToolUtils.doNetErroMsg(view.getMContext(), e, true);
            }

            @Override
            public void onNext(String s) {
                view.setInviteImages(s);
            }
        });
    }
}
