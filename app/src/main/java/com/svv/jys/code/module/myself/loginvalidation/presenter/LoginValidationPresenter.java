package com.svv.jys.code.module.myself.loginvalidation.presenter;



import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.common.utils.T;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.loginvalidation.model.LoginValidationModel;
import com.svv.jys.code.module.myself.loginvalidation.model.impl.LoginValidationModelImpl;
import com.svv.jys.code.module.myself.loginvalidation.view.LoginValidationView;
import com.svv.jys.code.module.net.req.POST_CODE_REQ;
import com.svv.jys.code.module.net.req.VerifyReq;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by js on 2018/9/14.
 */

public class LoginValidationPresenter extends BasePresent<LoginValidationView,LoginValidationModel> {
    public LoginValidationPresenter(){
        model=new LoginValidationModelImpl();
    }
    public void verify(String code){
        VerifyReq req=new VerifyReq();
        req.code=code;
        model.rx_yanz(req).doOnSubscribe(new Action0() {
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
                view.verifySuccese();
            }
        });
    }
    public void getCode(){
        POST_CODE_REQ req = new POST_CODE_REQ();
        req.send = "2";
        model.docode(req).doOnSubscribe(new Action0() {
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
                if (!ToolUtils.isNull(s)){
                    T.showShort(view.getMContext(),s);
                }
                view.successCode();
            }
        });

    }
}
