package com.svv.jys.code.module.myself.useridentify.base.presenter;

import com.alibaba.fastjson.JSONObject;
import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.common.base.net.BaseMulitRequest;
import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.common.entity.IndentityImgEntity;
import com.svv.jys.code.common.utils.BitmapUtil;
import com.svv.jys.code.common.utils.T;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.useridentify.base.model.IUserIdentifyModel;
import com.svv.jys.code.module.myself.useridentify.base.model.impl.UserIdentifyModelImpl;
import com.svv.jys.code.module.myself.useridentify.base.view.IUserIdentifyView;
import com.svv.jys.code.module.net.req.POST_CODE_REQ;
import com.svv.jys.code.module.net.req.POST_IDENTITY_IMG_REQ;
import com.svv.jys.code.module.net.req.POST_IDENTITY_REQ;
import com.svv.jys.code.module.net.req.POST_REMOVEFILE_REQ;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/5/2 0002.
 */

public class UserIdentifyPresenter extends BasePresent<IUserIdentifyView, IUserIdentifyModel> {
    public UserIdentifyPresenter() {
        model = new UserIdentifyModelImpl();
    }
    public void postIdentify(String truename,String idcard,String pic1,String pic2,String pic3,String surname,String city,String address){
        POST_IDENTITY_REQ req = new POST_IDENTITY_REQ();
        req.idcard=idcard;
        req.true_name=truename;
        req.pic1=pic1;
        req.pic2=pic2;
        req.pic3=pic3;
        req.surname=surname;
        req.address=address;
        req.city=city;
        model.rx_postIdentify(req).doOnSubscribe(new Action0() {
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
                view.identifySuccese();
            }
        });
    }
    public void getCode(){
        POST_CODE_REQ req = new POST_CODE_REQ();
        req.send = "3";
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
                if(!ToolUtils.isNull(s)){
                    T.showLong(view.getMContext(),s);
                }
            }
        });
    }

    private void commitUserImg(File imgFile, final int type) {
        POST_IDENTITY_IMG_REQ req = new POST_IDENTITY_IMG_REQ();
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
                view.successUpImg(type,entity);
            }
        });
    }

    public void compressAndcommitImg(File file, final int type) {
        List<File> list = new ArrayList<>();
        list.add(file);

        BitmapUtil.compressFiles(list, new BitmapUtil.CompressImageResponse() {
            @Override
            public void onSuccess(List<File> imgs) {
                dismissLoading(view.getMContext());
                File imgFile = imgs.get(0);
                commitUserImg(imgFile,type);
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

    public void removeFile(String path){
        POST_REMOVEFILE_REQ req=new POST_REMOVEFILE_REQ();
        req.path=path;
        model.rx_removeFile(req).doOnSubscribe(new Action0() {
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
                view.successRemove();
            }
        });
    }

}
