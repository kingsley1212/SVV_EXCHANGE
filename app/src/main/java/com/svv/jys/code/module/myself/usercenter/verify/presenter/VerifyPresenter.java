package com.svv.jys.code.module.myself.usercenter.verify.presenter;


import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.common.entity.VerifyEntity;
import com.svv.jys.code.common.utils.T;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.usercenter.verify.model.IVerifyModel;
import com.svv.jys.code.module.myself.usercenter.verify.model.impl.VerifyModelImpl;
import com.svv.jys.code.module.myself.usercenter.verify.view.IVerifyView;
import com.svv.jys.code.module.net.req.POST_CHANGE_VERIFY;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


public class VerifyPresenter extends BasePresent<IVerifyView, IVerifyModel> {
    public VerifyEntity entity;
    public VerifyPresenter() {
        this.model = new VerifyModelImpl();
    }
    public void changeVerify(String m,String status){
        POST_CHANGE_VERIFY req = new POST_CHANGE_VERIFY();
        req.m = m;
        req.status = status;
        model.rx_changeVerify(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {
                showLoading(view.getMContext());
            }
        }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io()).map(new Func1<BaseResponse, String>() {
            @Override
            public String call(BaseResponse baseResponse) {
                return baseResponse.msg;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<String>() {
            @Override

            public void onCompleted() {
                dismissLoading(view.getMContext());
            }

            @Override
            public void onError(Throwable e) {
                ToolUtils.doNetErroMsg(view.getMContext(), e, true, true);
                dismissLoading(view.getMContext());
            }

            @Override
            public void onNext(String s) {
               T.showShort(view.getMContext(),s);
               view.successChange();
            }
        });
    }

}
