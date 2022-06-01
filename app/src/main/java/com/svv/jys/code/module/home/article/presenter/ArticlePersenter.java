package com.svv.jys.code.module.home.article.presenter;

import com.alibaba.fastjson.JSONObject;
import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.common.entity.ArticleEntity;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.home.article.model.ArticleModel;
import com.svv.jys.code.module.home.article.model.impl.ArticleModelImpl;
import com.svv.jys.code.module.home.article.view.ArticleView;
import com.svv.jys.code.module.net.req.GET_ARTICLE_REQ;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by js on 2018/5/23.
 */

public class ArticlePersenter extends BasePresent<ArticleView,ArticleModel>{
    public ArticlePersenter(){
        model= new ArticleModelImpl();
    }
    public void getArticle(String id){
        GET_ARTICLE_REQ req=new GET_ARTICLE_REQ();
        req.id=id;
        model.rx_getArticel(req)
                .doOnSubscribe(new Action0() {
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
                dismissLoading(view.getMContext());
                ToolUtils.doNetErroMsg(view.getMContext(), e, true);
            }

            @Override
            public void onNext(ArticleEntity entity) {
                view.setArticle(entity);
            }
        });
    }
}
