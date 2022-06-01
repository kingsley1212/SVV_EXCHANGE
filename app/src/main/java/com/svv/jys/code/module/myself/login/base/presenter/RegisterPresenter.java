package com.svv.jys.code.module.myself.login.base.presenter;


import com.alibaba.fastjson.JSONObject;
import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.common.entity.CountryEntity;
import com.svv.jys.code.common.utils.T;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.login.base.model.IRegisterModel;
import com.svv.jys.code.module.myself.login.base.model.impl.RegisterModelImpl;
import com.svv.jys.code.module.myself.login.base.view.IRegisterView;
import com.svv.jys.code.module.net.req.POST_CODE_REQ;
import com.svv.jys.code.module.net.req.POST_KONG_REQ;
import com.svv.jys.code.module.net.req.POST_REGISTER_REQ;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


/**
 * Created by Administrator on 2017/10/21 0021.
 */

public class RegisterPresenter extends BasePresent<IRegisterView, IRegisterModel> {

    public static final String MOBILE_REGISTER_TYPE = "0";
    public static final String EMAIL_REGISTER_TYPE = "1";

    public RegisterPresenter() {
        this.model = new RegisterModelImpl();
    }

    public void doregister() {
        POST_REGISTER_REQ req = new POST_REGISTER_REQ();
        if (ToolUtils.isNull(view.getCodeTyoe())) {
            return;
        }
        switch (view.getCodeTyoe()) {
            case MOBILE_REGISTER_TYPE:
                req.username = view.getPhone();
                req.area = view.getArea();
                break;
            case EMAIL_REGISTER_TYPE:
                req.username = view.getEmail();
                break;
            default:
                break;
        }
        req.pid = view.getYaoQingMa();
        req.pwd = view.getPsw();
        req.re_pwd = view.getrePsw();
        req.type = view.getCodeTyoe();
        req.code = view.getcode();
        req.country = view.getCountry();
        model.doRegister(req).doOnSubscribe(new Action0() {
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
                view.registerSuccese();
            }
        });
    }

    public void docode() {
        POST_CODE_REQ req = new POST_CODE_REQ();
        req.type = view.getCodeTyoe();
        if (ToolUtils.isNull(req.type)) {
            return;
        }
        switch (req.type) {
            case MOBILE_REGISTER_TYPE:
                req.area = view.getArea();
                req.mobile = view.getPhone();
                break;
            case EMAIL_REGISTER_TYPE:
                req.mobile = view.getEmail();
                break;
            default:
                break;
        }
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

    public void getCountries() {
        POST_KONG_REQ req = new POST_KONG_REQ();
        model.getCountries(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {
                showLoading(view.getMContext());
            }
        }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io()).map(new Func1<BaseResponse, List<CountryEntity>>() {
            @Override
            public List<CountryEntity> call(BaseResponse baseResponse) {
                List<CountryEntity> list = JSONObject.parseArray(baseResponse.datas, CountryEntity.class);
                return list;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<List<CountryEntity>>() {
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
            public void onNext(List<CountryEntity> list) {
                view.setCountry(list);
            }
        });
    }

}




