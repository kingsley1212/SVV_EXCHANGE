package com.svv.jys.code.module.myself.usercenter.base.verifymethod.presenter;

import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.common.utils.T;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.usercenter.base.verifymethod.model.VerifyMethodModel;
import com.svv.jys.code.module.myself.usercenter.base.verifymethod.model.impl.VerifyMethodModelImpl;
import com.svv.jys.code.module.myself.usercenter.base.verifymethod.view.VerifyMethodView;
import com.svv.jys.code.module.net.req.POST_CODE_REQ;
import com.svv.jys.code.module.net.req.VerifyMethodReq;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by js on 2018/9/7.
 */

public class VerifyMethodPresenter extends BasePresent<VerifyMethodView,VerifyMethodModel>{
    public VerifyMethodPresenter(){
        model=new VerifyMethodModelImpl();
    }

    public void setVerifyMethod(String key,String mobile_code,String email_code,String google_code,String type){
        VerifyMethodReq req=new VerifyMethodReq();
        req.key=key;
        req.type=type;
        req.email_code=email_code;
        req.google_code=google_code;
        req.mobile_code=mobile_code;
        model.rx_verifyMethod(req).doOnSubscribe(new Action0() {
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
                dismissLoading(view.getMContext());
                ToolUtils.doNetErroMsg(view.getMContext(), e, true);
            }

            @Override
            public void onNext(String s) {
                if (!ToolUtils.isNull(s)){
                    T_Quick(s);
                }
                view.setVerifySuccese();
            }
        });
    }


    public void getCode(String type){
        POST_CODE_REQ req = new POST_CODE_REQ();
        req.send = "2";
        req.type=type;
        model.docode(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {
//                showLoading(view.getMContext());
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
