package com.svv.jys.code.module.home.article.model;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.net.req.GET_ARTICLE_REQ;

import rx.Observable;

/**
 * Created by js on 2018/5/23.
 */

public interface ArticleModel {
    Observable<BaseResponse> rx_getArticel(GET_ARTICLE_REQ req);
}
