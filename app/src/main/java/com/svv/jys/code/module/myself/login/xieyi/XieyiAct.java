package com.svv.jys.code.module.myself.login.xieyi;

import android.content.Context;
import android.text.Html;
import android.webkit.WebView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.mvp.MvpActivity;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.login.xieyi.model.XieYiModel;
import com.svv.jys.code.module.myself.login.xieyi.presenter.XieYiPresenter;
import com.svv.jys.code.module.myself.login.xieyi.view.XieYiView;

/**
 * Created by js on 2018/7/2.
 */

public class XieyiAct extends MvpActivity<XieYiView,XieYiModel,XieYiPresenter> implements XieYiView{
    private WebView webview_xieyi;
    @Override
    public Context getMContext() {
        return this;
    }

    @Override
    public XieYiPresenter initPresenter() {
        return new XieYiPresenter();
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
        setTitleTx(getString(R.string.XieyiAct_title));
        webview_xieyi=findViewById(R.id.webview_xieyi);
    }

    @Override
    public void doBusiness() {
        presenter.xieyi();
    }

    @Override
    public void setXieyi(String s) {
        if (!ToolUtils.isNull(s)){
            //  加载、并显示HTML代码
            webview_xieyi.loadDataWithBaseURL(null, ""+s+"", "text/html" , "utf-8", null);
        }else {
            webview_xieyi.loadDataWithBaseURL(null, Html.fromHtml(getString(R.string.nodata_no_sj))+"", "text/html" , "utf-8", null);
        }
    }
}
