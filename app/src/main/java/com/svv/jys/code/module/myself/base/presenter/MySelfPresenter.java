package com.svv.jys.code.module.myself.base.presenter;

import com.alibaba.fastjson.JSON;
import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.common.base.net.BaseMulitRequest;
import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.common.entity.FUserInfoEntity;
import com.svv.jys.code.common.entity.NewIdentifyEntity;
import com.svv.jys.code.common.utils.BitmapUtil;
import com.svv.jys.code.common.utils.T;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.base.model.IMySelfModel;
import com.svv.jys.code.module.myself.base.model.impl.MySelfModelImpl;
import com.svv.jys.code.module.myself.base.view.IMySelfView;
import com.svv.jys.code.module.net.req.POST_UPDATA_IMG_REQ;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/4/28 0028.
 */

public class MySelfPresenter extends BasePresent<IMySelfView, IMySelfModel> {
    public MySelfPresenter() {
        model = new MySelfModelImpl();
    }

    /**
     * 获取用户信息
     *
     * @param showLoading
     */
    public void getUserInfoData(final boolean showLoading) {
        model.rx_getUserInfo()
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        if (showLoading) {
//                            showLoading(view.getMContext());
                        }
                    }
                }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io()).map(new Func1<BaseResponse, FUserInfoEntity>() {
            @Override
            public FUserInfoEntity call(BaseResponse baseResponse) {
                FUserInfoEntity entity = new FUserInfoEntity();
                entity.fromJSONAuto(baseResponse.datas);
                return entity;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<FUserInfoEntity>() {
            @Override
            public void onCompleted() {
                view.getUserInfoComplete();
            }

            @Override
            public void onError(Throwable e) {
                dismissLoading(view.getMContext());
                ToolUtils.doNetErroMsg(view.getMContext(), e, false);
                view.getUserInfoComplete();
            }

            @Override
            public void onNext(FUserInfoEntity fUserInfoEntity) {
                dismissLoading(view.getMContext());
                ToolUtils.saveUserInfo(view.getMContext(), fUserInfoEntity);
                view.setUsetData(fUserInfoEntity);
            }
        });
    }

    public void compressAndcommitImg(File file) {
        List<File> list = new ArrayList<>();
        list.add(file);

        BitmapUtil.compressFiles(list, new BitmapUtil.CompressImageResponse() {
            @Override
            public void onSuccess(List<File> imgs) {
                dismissLoading(view.getMContext());
                File imgFile = imgs.get(0);
                commitUserImg(imgFile);
            }

            @Override
            public void onDo() {
//                showLoading(view.getMContext());
            }

            @Override
            public void onFail() {
                dismissLoading(view.getMContext());
            }

            @Override
            public void onFinish() {
                dismissLoading(view.getMContext());
            }
        });
    }

    private void commitUserImg(File imgFile) {
        POST_UPDATA_IMG_REQ req = new POST_UPDATA_IMG_REQ();
        req.baseMulitRequests = new ArrayList<>();
        req.baseMulitRequests.add(new BaseMulitRequest("logo", imgFile, "image/jpg"));
        model.rx_updataImg(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {
                showLoading(view.getMContext());
            }
        }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io()).map(new Func1<BaseResponse, BaseResponse>() {
            @Override
            public BaseResponse call(BaseResponse baseResponse) {
                return baseResponse;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<BaseResponse>() {
            @Override
            public void onCompleted() {
                view.getUserInfoComplete();
            }

            @Override
            public void onError(Throwable e) {
                dismissLoading(view.getMContext());
                ToolUtils.doNetErroMsg(view.getMContext(), e, true);
                view.getUserInfoComplete();
            }

            @Override
            public void onNext(BaseResponse s) {
                dismissLoading(view.getMContext());
                T.showShort(view.getMContext(), s.msg);
                view.loadUserImg(s.datas);
            }
        });
    }

    public void Logout() {
        model.rx_loginout()
                .doOnSubscribe(new Action0() {
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
                view.getUserInfoComplete();
            }

            @Override
            public void onError(Throwable e) {
                dismissLoading(view.getMContext());
                ToolUtils.doNetErroMsg(view.getMContext(), e, true);
                view.getUserInfoComplete();
            }

            @Override
            public void onNext(String s) {
                dismissLoading(view.getMContext());
                view.successLoginout();
            }
        });
    }

    public void getIdentityInfo() {
        model.rx_IdentityInfo()
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        showLoading(view.getMContext());
                    }
                }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io()).map(new Func1<BaseResponse, NewIdentifyEntity>() {
            @Override
            public NewIdentifyEntity call(BaseResponse baseResponse) {

                return JSON.parseObject(baseResponse.datas, NewIdentifyEntity.class);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<NewIdentifyEntity>() {
            @Override
            public void onCompleted() {
                view.getUserInfoComplete();
            }

            @Override
            public void onError(Throwable e) {
                dismissLoading(view.getMContext());
                ToolUtils.doNetErroMsg(view.getMContext(), e, true);
                view.getUserInfoComplete();
            }

            @Override
            public void onNext(NewIdentifyEntity newIdentifyEntity) {
                dismissLoading(view.getMContext());
                view.getidentifyInfo(newIdentifyEntity);
            }
        });
    }
}
