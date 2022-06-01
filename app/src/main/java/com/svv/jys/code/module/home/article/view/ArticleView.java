package com.svv.jys.code.module.home.article.view;

import com.svv.jys.code.common.base.mvp.BaseView;
import com.svv.jys.code.common.entity.ArticleEntity;

/**
 * Created by js on 2018/5/23.
 */

public interface ArticleView extends BaseView {
    void setArticle(ArticleEntity entity);
}
