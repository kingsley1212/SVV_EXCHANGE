package com.svv.jys.code.module.myself.apply.presenter;


import com.alibaba.fastjson.JSON;
import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.common.entity.ApplyEntity;
import com.svv.jys.code.common.utils.T;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.apply.model.IApplyModel;
import com.svv.jys.code.module.myself.apply.model.impl.ApplyModelImpl;
import com.svv.jys.code.module.myself.apply.view.IApplyView;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


public class ApplyPresenter extends BasePresent<IApplyView, IApplyModel> {
    public ApplyPresenter() {
        this.model = new ApplyModelImpl();
    }

    public void getApplyInfo(){
        model.rx_apply_info().doOnSubscribe(new Action0() {
            @Override
            public void call() {
                showLoading(view.getMContext());
            }
        }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io()).map(new Func1<BaseResponse, ApplyEntity>() {
            @Override
            public ApplyEntity call(BaseResponse baseResponse) {
                return JSON.parseObject(baseResponse.datas,ApplyEntity.class);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber< ApplyEntity>() {
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
            public void onNext( ApplyEntity entity) {
                view.setData(entity);
            }
        });
    }

    public void doApply() {
        model.rx_apply().doOnSubscribe(new Action0() {
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
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber< String>() {
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
            public void onNext( String s) {
                T.showShort(view.getMContext(),s);
                view.applySuccess();
            }
        });
    }
}
