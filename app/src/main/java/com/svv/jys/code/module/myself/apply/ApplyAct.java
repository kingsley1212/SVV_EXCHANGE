package com.svv.jys.code.module.myself.apply;


import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.mvp.MvpActivity;
import com.svv.jys.code.common.entity.ApplyEntity;
import com.svv.jys.code.common.utils.T;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.apply.model.IApplyModel;
import com.svv.jys.code.module.myself.apply.presenter.ApplyPresenter;
import com.svv.jys.code.module.myself.apply.view.IApplyView;
import com.svv.jys.code.module.myself.login.sjxy.SjxyAct;

/**
 * Created by lzj on 2018/10/9.
 */

public class ApplyAct extends MvpActivity<IApplyView, IApplyModel, ApplyPresenter> implements IApplyView {

    @Override
    public Context getMContext() {
        return this;
    }

    @Override
    public ApplyPresenter initPresenter() {
        return new ApplyPresenter();
    }

    private WebView web_content;
    private LinearLayout web_content_ll;
    private CheckBox check;
    private TextView info_title;


    @Override
    public void baseInitialization() {

    }

    @Override
    public int setR_Layout() {
        return R.layout.act_apply;
    }

    @Override
    public void viewInitialization() {
        setBackPress();
        web_content_ll = (LinearLayout) $(R.id.web_content_ll);
        setTitleTx(getString(R.string.menu_tv3));
        info_title = (TextView) $(R.id.info_title);
        check = (CheckBox) $(R.id.check);
        $(R.id.xy_ll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check.setChecked(!check.isChecked());
            }
        });
        $(R.id.xy_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ToolUtils.isFastClick(view.getId())){
                    gotoActivity(SjxyAct.class);
                }

            }
        });
    }

    @Override
    public void doBusiness() {
        presenter.getApplyInfo();

    }

    public void setData(ApplyEntity entity) {
        info_title.setText(entity.getProtocol().getTitle());
        web_content = new WebView(getMContext());
        if (!TextUtils.isEmpty(entity.getProtocol().getContent())) {
            //  加载、并显示HTML代码
            web_content.loadDataWithBaseURL(null,""+entity.getProtocol().getContent()+"", "text/html; charset=UTF-8", "utf-8", null);
        } else {
            web_content.loadDataWithBaseURL(null, "<font size='28px' color='#ffffff'>暂无数据" + "</font>", "text/html", "utf-8", null);
        }
        web_content.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                web_content_ll.setVisibility(View.VISIBLE);
            }
        });
        web_content_ll.addView(web_content,0);
        $(R.id.apply_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                apply();
            }
        });
    }

    @Override
    public void applySuccess() {
        finish();
    }

    public void apply(){
        if(check.isChecked()){
            presenter.doApply();
        }else {
            T.showShort(getMContext(),getString(R.string.register_protocol_prompt));
        }
    }

}