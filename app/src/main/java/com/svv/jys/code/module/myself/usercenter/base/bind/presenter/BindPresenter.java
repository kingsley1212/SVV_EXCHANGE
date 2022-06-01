package com.svv.jys.code.module.myself.usercenter.base.bind.presenter;

import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.common.utils.T;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.usercenter.base.bind.model.BindModel;
import com.svv.jys.code.module.myself.usercenter.base.bind.model.impl.BindModelImpl;
import com.svv.jys.code.module.myself.usercenter.base.bind.view.BindView;
import com.svv.jys.code.module.net.req.POST_BINDEMAIL_REQ;
import com.svv.jys.code.module.net.req.POST_BINDPHONE_REQ;
import com.svv.jys.code.module.net.req.POST_CODE_REQ;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by js on 2018/5/30.
 */

public class BindPresenter extends BasePresent<BindView, BindModel> {
    public BindPresenter() {
        model = new BindModelImpl();
    }

    public void getCode(String type, String name, String area) {
        POST_CODE_REQ req = new POST_CODE_REQ();
        req.mobile = name;
        req.type = type;
        req.area = area;
        req.send = "0";
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

    public void bindEmail(String email, String code) {
        POST_BINDEMAIL_REQ req = new POST_BINDEMAIL_REQ();
        req.code = code;
        req.email = email;
        model.rx_bindEmail(req).doOnSubscribe(new Action0() {
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
                view.successBind();
            }
        });
    }


    public void bindPhone(String area, String phone, String code) {
        POST_BINDPHONE_REQ req = new POST_BINDPHONE_REQ();
        req.area = area;
        req.code = code;
        req.mobile = phone;
        model.rx_bindPhone(req).doOnSubscribe(new Action0() {
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
                view.successBind();
            }
        });
    }
}
