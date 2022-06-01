package com.svv.jys.code.module.home.article.model.impl;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.home.article.model.ArticleModel;
import com.svv.jys.code.module.net.api.API_Factory;
import com.svv.jys.code.module.net.req.GET_ARTICLE_REQ;

import rx.Observable;

/**
 * Created by js on 2018/5/23.
 */

public class ArticleModelImpl implements ArticleModel{
    @Override
    public Observable<BaseResponse> rx_getArticel(GET_ARTICLE_REQ req) {
        return API_Factory.API_GetArticleInfo(req);
    }
}
