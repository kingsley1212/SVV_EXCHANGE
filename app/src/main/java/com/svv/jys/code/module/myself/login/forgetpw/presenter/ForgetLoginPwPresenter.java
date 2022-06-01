package com.svv.jys.code.module.myself.login.forgetpw.presenter;


import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.common.utils.T;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.login.forgetpw.ForgetLoginPwAct;
import com.svv.jys.code.module.myself.login.forgetpw.model.IForgetLoginPwModel;
import com.svv.jys.code.module.myself.login.forgetpw.model.impl.ForgetLoginPwModelImpl;
import com.svv.jys.code.module.myself.login.forgetpw.view.IForgetLoginPwView;
import com.svv.jys.code.module.net.req.POST_CODE_REQ;
import com.svv.jys.code.module.net.req.POST_FORGETPSW1_REQ;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/11/14 0014.
 */

public class ForgetLoginPwPresenter extends BasePresent<IForgetLoginPwView, IForgetLoginPwModel> {


    public ForgetLoginPwPresenter() {
        model = new ForgetLoginPwModelImpl();
    }

    public void postForgetpsw(String username, String idcard) {
        POST_FORGETPSW1_REQ req = new POST_FORGETPSW1_REQ();
        req.username = username;
        req.idcard = idcard;
        model.doForgetPsw1(req).doOnSubscribe(new Action0() {
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
            public void onNext(String success) {
                if (!ToolUtils.isNull(success)) {
                    T.showLong(view.getMContext(), success);
                }
                view.succese();
            }
        });

    }

    public void docode(String phone) {
        POST_CODE_REQ req = new POST_CODE_REQ();
        req.type = ((ForgetLoginPwAct)view).getIntent().getStringExtra("type");
        if (ToolUtils.isNull(req.type)) {
            return;
        }
        req.mobile = phone;
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
                if (!ToolUtils.isNull(s)) {
                    T.showLong(view.getMContext(), s);
                }
                view.succese();
            }
        });
    }


}
