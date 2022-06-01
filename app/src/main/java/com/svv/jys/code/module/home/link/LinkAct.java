package com.svv.jys.code.module.home.link;

import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.svv.jys.R;
import com.svv.jys.code.common.base.mvp.BaseAcitvity;

/**
 * Created by js on 2018/7/3.
 */

public class LinkAct extends BaseAcitvity{
    public WebView webview_link;
    private String url;

    @Override
    public void baseInitialization() {
        url = getIntent().getStringExtra("url");
    }

    @Override
    public int setR_Layout() {
        return R.layout.act_link;
    }

    @Override
    public void viewInitialization() {
        setBackPress();
        webview_link=findViewById(R.id.webview_link);
        init();
    }

    @Override
    public void doBusiness() {

    }

    private void init(){

        //WebView加载web资源
        webview_link.loadUrl(url);
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        webview_link.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });
    }

}
