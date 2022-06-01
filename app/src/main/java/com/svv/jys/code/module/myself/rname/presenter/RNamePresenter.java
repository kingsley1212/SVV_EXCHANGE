package com.svv.jys.code.module.myself.rname.presenter;


import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.common.entity.VerifyEntity;
import com.svv.jys.code.common.utils.T;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.rname.model.IRNameModel;
import com.svv.jys.code.module.myself.rname.model.impl.RNameModelImpl;
import com.svv.jys.code.module.myself.rname.view.IRNamePwdView;
import com.svv.jys.code.module.net.req.POST_R_NAME;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


public class RNamePresenter extends BasePresent<IRNamePwdView, IRNameModel> {
    public VerifyEntity entity;
    public RNamePresenter() {
        this.model = new RNameModelImpl();
    }
    public void changeName(final String name){
        POST_R_NAME req = new POST_R_NAME();
        req.nickname = name;
        model.rx_setName(req).doOnSubscribe(new Action0() {
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
               view.successChange(name);
            }
        });
    }

}
