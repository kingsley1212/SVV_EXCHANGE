package com.svv.jys.code.module.myself.usercenter.securityverification.presenter;

import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.usercenter.securityverification.model.SecurityVerificationModel;
import com.svv.jys.code.module.myself.usercenter.securityverification.model.impl.SecurityVerificationModelImpl;
import com.svv.jys.code.module.myself.usercenter.securityverification.view.SecurityVerificationView;
import com.svv.jys.code.module.net.req.ChangePswTypeReq;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class SecurityVerificationPresenter extends BasePresent<SecurityVerificationView,SecurityVerificationModel> {
    public SecurityVerificationPresenter(){
        model=new SecurityVerificationModelImpl();
    }
    public void changepwdtype(String type,String psw){
        ChangePswTypeReq req=new ChangePswTypeReq();
        req.pwd=psw;
        req.type=type;
        model.changepwdtype(req)
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        showLoading(view.getMContext());
                    }
                }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io()).map(new Func1<BaseResponse, String>() {
            @Override
            public  String call(BaseResponse baseResponse) {

                return baseResponse.msg;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                dismissLoading(view.getMContext());
                ToolUtils.doNetErroMsg(view.getMContext(), e, true,true);
            }

            @Override
            public void onNext( String s) {
                dismissLoading(view.getMContext());
                view.succese();
            }
        });
    }

}
