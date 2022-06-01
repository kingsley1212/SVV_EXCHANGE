package com.svv.jys.code.module.myself.usercenter.pwd.presenter;


import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.common.entity.VerifyEntity;
import com.svv.jys.code.common.utils.T;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.usercenter.pwd.model.IChangePwdModel;
import com.svv.jys.code.module.myself.usercenter.pwd.model.impl.ChangePwdModelImpl;
import com.svv.jys.code.module.myself.usercenter.pwd.view.IChangePwdView;
import com.svv.jys.code.module.net.req.POST_SAFEPSW_REQ;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


public class ChangePwdPresenter extends BasePresent<IChangePwdView, IChangePwdModel> {
    public VerifyEntity entity;
    public ChangePwdPresenter() {
        this.model = new ChangePwdModelImpl();
    }
    public void changeVerify(String pwd,String repwd){
        POST_SAFEPSW_REQ req = new POST_SAFEPSW_REQ();
        req.pwd = pwd;
        req.repwd = repwd;
        model.rx_setSafePsw(req).doOnSubscribe(new Action0() {
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
