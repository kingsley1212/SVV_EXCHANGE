package com.svv.jys.code.module.myself.usercenter.base.pay.addzfborwx.presenter;

import com.alibaba.fastjson.JSONObject;
import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.common.base.net.BaseMulitRequest;
import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.common.entity.IndentityImgEntity;
import com.svv.jys.code.common.utils.BitmapUtil;
import com.svv.jys.code.common.utils.T;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.usercenter.base.pay.addzfborwx.model.AddZfbOrWxModel;
import com.svv.jys.code.module.myself.usercenter.base.pay.addzfborwx.model.impl.AddZfbOrWxModelImpl;
import com.svv.jys.code.module.myself.usercenter.base.pay.addzfborwx.view.AddZfbOrWxView;
import com.svv.jys.code.module.net.req.AddPayReq;
import com.svv.jys.code.module.net.req.POST_IDENTITY_IMG_REQ;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class AddZfbOrWxPresenter extends BasePresent<AddZfbOrWxView,AddZfbOrWxModel> {
    public AddZfbOrWxPresenter(){
        model=new AddZfbOrWxModelImpl();
    }

    public void addPay(String code, final String memo, String password){
        AddPayReq req=new AddPayReq();
        req.code=code;
        req.memo=memo;
        req.password=password;
        model.rx_addpay(req).doOnSubscribe(new Action0() {
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
            public void onNext(String msg) {
                T.showShort(view.getMContext(),msg);
                view.addSuccese();
            }
        });

    }

    private void commitUserImg(File imgFile) {
        POST_IDENTITY_IMG_REQ req = new POST_IDENTITY_IMG_REQ();
        req.path="otc";
        req.baseMulitRequests = new ArrayList<>();
        req.baseMulitRequests.add(new BaseMulitRequest("file", imgFile, "image/jpg"));
        model.rx_upImg(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {
                showLoading(view.getMContext());
            }
        }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io()).map(new Func1<BaseResponse, IndentityImgEntity>() {
            @Override
            public IndentityImgEntity call(BaseResponse baseResponse) {
                IndentityImgEntity entity=JSONObject.parseObject(baseResponse.datas,IndentityImgEntity.class);
                return entity;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<IndentityImgEntity>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                dismissLoading(view.getMContext());
                ToolUtils.doNetErroMsg(view.getMContext(), e, true);
            }

            @Override
            public void onNext(IndentityImgEntity entity) {
                dismissLoading(view.getMContext());
                view.succese(entity);
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
}
