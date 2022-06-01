package com.svv.jys.code.module.myself.usercenter.base.presenter;

import com.alibaba.fastjson.JSON;
import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.common.entity.NewIdentifyEntity;
import com.svv.jys.code.common.entity.VerifyEntity;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.usercenter.base.model.IUserCenterModel;
import com.svv.jys.code.module.myself.usercenter.base.model.impl.UserCenterModelModelImpl;
import com.svv.jys.code.module.myself.usercenter.base.view.IUserCenterView;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/5/2 0002.
 */

public class UserCenterPresenter extends BasePresent<IUserCenterView, IUserCenterModel> {
    public VerifyEntity verifyEntity;
    public UserCenterPresenter() {
        model = new UserCenterModelModelImpl();
    }

    public void getVerify(){
            model.rx_getVerify().doOnSubscribe(new Action0() {
                @Override
                public void call() {
                }
            }).subscribeOn(AndroidSchedulers.mainThread())
                    .observeOn(Schedulers.io()).map(new Func1<BaseResponse, VerifyEntity>() {
                @Override
                public VerifyEntity call(BaseResponse baseResponse) {
                    return JSON.parseObject(baseResponse.datas,VerifyEntity.class);
                }
            }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<VerifyEntity>() {
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
                public void onNext(VerifyEntity entity) {
                    verifyEntity = entity;
                   view.setVerifyData(entity);
                }
            });
    }

    public void getIdentityInfo(){
        model.rx_IdentityInfo()
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        showLoading(view.getMContext());
                    }
                }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io()).map(new Func1<BaseResponse, NewIdentifyEntity>() {
            @Override
            public  NewIdentifyEntity call(BaseResponse baseResponse) {

                return JSON.parseObject(baseResponse.datas,NewIdentifyEntity.class);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<NewIdentifyEntity>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                dismissLoading(view.getMContext());
                ToolUtils.doNetErroMsg(view.getMContext(), e, true);
            }

            @Override
            public void onNext( NewIdentifyEntity newIdentifyEntity) {
                dismissLoading(view.getMContext());
                view.getidentifyInfo(newIdentifyEntity);
            }
        });
    }

}
