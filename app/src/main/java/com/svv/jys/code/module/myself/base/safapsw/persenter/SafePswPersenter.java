package com.svv.jys.code.module.myself.base.safapsw.persenter;

import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.common.utils.T;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.base.safapsw.model.SafePswModel;
import com.svv.jys.code.module.myself.base.safapsw.model.impl.SafePswModelImpl;
import com.svv.jys.code.module.myself.base.safapsw.view.SafePswview;
import com.svv.jys.code.module.net.req.POST_CHANGEPSW_REQ;
import com.svv.jys.code.module.net.req.POST_CODE_REQ;
import com.svv.jys.code.module.net.req.POST_SAFEPSW_REQ;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by js on 2018/5/21.
 */

public class SafePswPersenter extends BasePresent<SafePswview, SafePswModel> {
    public SafePswPersenter() {
        model = new SafePswModelImpl();
    }

    public void getCode() {
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
                if (!ToolUtils.isNull(s)) {
                    T.showLong(view.getMContext(), s);
                }
                view.successCode();
            }
        });

    }

    public void setSafePsw(String code, String psw, String repsw, String nickname) {
        POST_SAFEPSW_REQ req = new POST_SAFEPSW_REQ();
        req.code = code;
        req.pwd = psw;
        req.repwd = repsw;
        req.nickname = nickname;
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
                view.setSafeSuccess();
            }
        });
    }

    public void changePsw(String code, String psw, String repsw, String old, String pswType) {
        POST_CHANGEPSW_REQ req = new POST_CHANGEPSW_REQ();
        req.action = pswType;
        req.code = code;
        req.oldpwd = old;
        req.pwd = psw;
        req.repwd = repsw;
        model.rx_changeSafePsw(req).doOnSubscribe(new Action0() {
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
                view.changeSafeSuccess();
            }
        });
    }
}
