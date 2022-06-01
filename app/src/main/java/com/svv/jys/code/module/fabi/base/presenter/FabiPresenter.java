package com.svv.jys.code.module.fabi.base.presenter;


import com.alibaba.fastjson.JSONObject;
import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.common.entity.AdvSettingEntity;
import com.svv.jys.code.common.entity.OtcAdvEntity;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.fabi.base.FabiFrag;
import com.svv.jys.code.module.fabi.base.model.FabiModel;
import com.svv.jys.code.module.fabi.base.model.impl.FabiModelImpl;
import com.svv.jys.code.module.fabi.base.view.FabiView;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by js on 2018/8/1.
 */

public class FabiPresenter extends BasePresent<FabiView,FabiModel> {
    public AdvSettingEntity settingEntity;
    public FabiPresenter(){
        model=new FabiModelImpl();
    }
    public void isPublish(){
        model.rx_isPublish().doOnSubscribe(new Action0() {
            @Override
            public void call() {

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
                ToolUtils.doNetErroMsg(view.getMContext(), e, false);
            }

            @Override
            public void onNext(String s) {
                view.isPublish(s);
            }
        });
    }

    public void getAdvSetting(){
        model.rx_GetAdvSetting().doOnSubscribe(new Action0() {
            @Override
            public void call() {
                showLoading(view.getMContext());
            }
        }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io()).map(new Func1<BaseResponse, AdvSettingEntity>() {
            @Override
            public AdvSettingEntity call(BaseResponse baseResponse) {
                AdvSettingEntity entity= JSONObject.parseObject(baseResponse.datas,AdvSettingEntity.class);
                return entity;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<AdvSettingEntity>() {
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
            public void onNext(AdvSettingEntity entity) {
                settingEntity = entity;
                view.setData(entity);
            }
        });
    }

    public void getOtcAdv(final int offset, final int position){
        ((FabiFrag)view).req.limit="10";
        ((FabiFrag)view).req.offset = String.valueOf(offset);
        model.rx_otcADv(((FabiFrag)view).req).doOnSubscribe(new Action0() {
            @Override
            public void call() {

            }
        }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io()).map(new Func1<BaseResponse, OtcAdvEntity>() {
            @Override
            public OtcAdvEntity call(BaseResponse baseResponse) {
                OtcAdvEntity otcAdvEntity=JSONObject.parseObject(baseResponse.datas,OtcAdvEntity.class);
                return otcAdvEntity;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<OtcAdvEntity>() {
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
            public void onNext(OtcAdvEntity entity) {
                boolean isLoadMore = offset>0;
                view.setOtcAdv(entity.getRows(),position,isLoadMore);
            }
        });
    }

}
