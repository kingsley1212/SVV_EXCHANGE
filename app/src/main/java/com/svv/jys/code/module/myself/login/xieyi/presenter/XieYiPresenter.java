package com.svv.jys.code.module.myself.login.xieyi.presenter;

import com.alibaba.fastjson.JSONObject;
import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.common.entity.ArticleEntity;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.login.xieyi.model.XieYiModel;
import com.svv.jys.code.module.myself.login.xieyi.model.impl.XieYiModelImpl;
import com.svv.jys.code.module.myself.login.xieyi.view.XieYiView;
import com.svv.jys.code.module.net.req.GET_ARTICLE_REQ;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by js on 2018/7/2.
 */

public class XieYiPresenter extends BasePresent<XieYiView,XieYiModel>{
    public XieYiPresenter(){
        model=new XieYiModelImpl();
    }
    public void xieyi(){
        GET_ARTICLE_REQ req = new GET_ARTICLE_REQ();
        req.id = "1";
        model.rx_protocol(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {
                showLoading(view.getMContext());
            }
        }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io()).map(new Func1<BaseResponse, String>() {
            @Override
            public String call(BaseResponse baseResponse) {
                ArticleEntity entity= JSONObject.parseObject(baseResponse.datas,ArticleEntity.class);
                return entity.getContent();
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
               view.setXieyi(s);
            }
        });
    }
    }

