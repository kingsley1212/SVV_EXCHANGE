package com.svv.jys.code.module.myself.login.sjxy.presenter;

import com.alibaba.fastjson.JSONObject;
import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.common.entity.ArticleEntity;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.login.sjxy.model.SjxyModel;
import com.svv.jys.code.module.myself.login.sjxy.model.impl.SjxyModelImpl;
import com.svv.jys.code.module.myself.login.sjxy.view.SjxyView;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by js on 2018/7/2.
 */

public class SjxyPresenter extends BasePresent<SjxyView,SjxyModel>{
    public SjxyPresenter(){
        model=new SjxyModelImpl();
    }
    public void xieyi(){
        model.rx_protocol().doOnSubscribe(new Action0() {
            @Override
            public void call() {
                showLoading(view.getMContext());
            }
        }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io()).map(new Func1<BaseResponse, ArticleEntity>() {
            @Override
            public ArticleEntity call(BaseResponse baseResponse) {
                ArticleEntity entity= JSONObject.parseObject(baseResponse.datas,ArticleEntity.class);
                return entity;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<ArticleEntity>() {
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
            public void onNext(ArticleEntity entity) {
               view.setXieyi(entity);
            }
        });
    }
    }

