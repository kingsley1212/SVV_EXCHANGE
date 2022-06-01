package com.svv.jys.code.module.myself.login.sjxy;

import android.content.Context;
import android.text.Html;
import android.webkit.WebView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.mvp.MvpActivity;
import com.svv.jys.code.common.entity.ArticleEntity;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.login.sjxy.model.SjxyModel;
import com.svv.jys.code.module.myself.login.sjxy.presenter.SjxyPresenter;
import com.svv.jys.code.module.myself.login.sjxy.view.SjxyView;

/**
 * Created by js on 2018/7/2.
 */

public class SjxyAct extends MvpActivity<SjxyView,SjxyModel,SjxyPresenter> implements SjxyView {
    private WebView webview_xieyi;
    @Override
    public Context getMContext() {
        return this;
    }

    @Override
    public SjxyPresenter initPresenter() {
        return new SjxyPresenter();
    }

    @Override
    public void baseInitialization() {

    }

    @Override
    public int setR_Layout() {
        return R.layout.act_xieyi;
    }

    @Override
    public void viewInitialization() {
        setBackPress();

        webview_xieyi=findViewById(R.id.webview_xieyi);
    }

    @Override
    public void doBusiness() {
        presenter.xieyi();
    }

    @Override
    public void setXieyi(ArticleEntity entity) {
        setTitleTx(entity.getTitle());
        if (!ToolUtils.isNull(entity.getContent())){
            //  加载、并显示HTML代码
            webview_xieyi.loadDataWithBaseURL(null, "<html><head><style>img{ max-width:100%;width:100%;max-height:100%;height:100%;}</style></head><body>"+entity.getContent()+"</body></html>", "text/html" , "utf-8", null);
        }else {
            webview_xieyi.loadDataWithBaseURL(null, Html.fromHtml(getString(R.string.nodata_no_sj))+"", "text/html" , "utf-8", null);
        }
    }
}
