package com.svv.jys.code.module.home.article;

import android.content.Context;
import android.view.View;
import android.webkit.WebView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.mvp.MvpActivity;
import com.svv.jys.code.common.entity.ArticleEntity;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.home.article.model.ArticleModel;
import com.svv.jys.code.module.home.article.presenter.ArticlePersenter;
import com.svv.jys.code.module.home.article.view.ArticleView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by js on 2018/5/23.
 */

public class ArticleAct extends MvpActivity<ArticleView, ArticleModel, ArticlePersenter> implements ArticleView {
    String id;
    @BindView(R.id.webview)
    WebView webView;
    @BindView(R.id.nodata_ly)
    View nodata_ly;
    @Override
    public Context getMContext() {
        return this;
    }

    @Override
    public ArticlePersenter initPresenter() {
        return new ArticlePersenter();
    }

    @Override
    public void baseInitialization() {
        id = getIntent().getStringExtra("id");
    }

    @Override
    public int setR_Layout() {
        return R.layout.act_article;
    }

    @Override
    public void viewInitialization() {
        ButterKnife.bind(this);
        setBackPress();
        webView = findViewById(R.id.webview);

    }

    @Override
    public void doBusiness() {
        presenter.getArticle(id);
    }

    @Override
    public void setArticle(ArticleEntity entity) {
        setTitleTx(getResources().getString(R.string.article_title));
        webView.setHorizontalScrollBarEnabled(false);//水平不显示
        webView.setVerticalScrollBarEnabled(false);//垂直不显示
        if (entity!=null&&!ToolUtils.isNull(entity.getContent())) {
            nodata_ly.setVisibility(View.GONE);
            webView.setVisibility(View.VISIBLE);
            //  加载、并显示HTML代码
            webView.loadDataWithBaseURL(null, "<!DOCTYPE HTML><html><head><style>img{ max-width:100%;width:100%;max-height:100%;height:100%;}</style></head><body>"+entity.getContent()+"</body></html>", "text/html", "utf-8", null);
        } else {
            webView.setVisibility(View.GONE);
            nodata_ly.setVisibility(View.VISIBLE);
        }
    }
}
