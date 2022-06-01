package com.svv.jys.code.module.myself.login.forgetpsw2.persenter;

import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.common.utils.T;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.login.forgetpsw2.model.ForgetPsw2Model;
import com.svv.jys.code.module.myself.login.forgetpsw2.model.impl.ForgetPsw2ModelImpl;
import com.svv.jys.code.module.myself.login.forgetpsw2.view.ForgetPsw2View;
import com.svv.jys.code.module.net.req.POST_CODE_REQ;
import com.svv.jys.code.module.net.req.POST_FORGETPSW2_REQ;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by js on 2018/5/21.
 */

public class ForgetPsw2Persenter extends BasePresent<ForgetPsw2View, ForgetPsw2Model> {
    public ForgetPsw2Persenter() {
        model = new ForgetPsw2ModelImpl();
    }

    public void postForgetPsw2(String username, String code, String psw, String repsw,String idcard) {
        POST_FORGETPSW2_REQ req = new POST_FORGETPSW2_REQ();
        req.code = code;
        req.pwd = psw;
        req.re_pwd = repsw;
        req.mobile = username;
        req.idcard = idcard;
        model.doForgetPsw2(req).doOnSubscribe(new Action0() {
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
            public void onNext(String success) {
                view.succese();
            }
        });

    }

    public void docode(String phone) {
        POST_CODE_REQ req = new POST_CODE_REQ();
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
                view.getCodeSuccess();
            }
        });
    }
}
