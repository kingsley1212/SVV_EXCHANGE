package com.svv.jys.code.module.myself.rname;


import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.svv.jys.R;
import com.svv.jys.code.common.base.mvp.MvpActivity;
import com.svv.jys.code.common.utils.T;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.rname.model.IRNameModel;
import com.svv.jys.code.module.myself.rname.presenter.RNamePresenter;
import com.svv.jys.code.module.myself.rname.view.IRNamePwdView;

/**
 * Created by lzj on 2018/10/9.
 */

public class RNameAct extends MvpActivity<IRNamePwdView, IRNameModel, RNamePresenter> implements IRNamePwdView {
    private EditText rname_tv;
    private String name;
    private View commit_tv;

    @Override
    public RNamePresenter initPresenter() {
        return new RNamePresenter();
    }


    @Override
    public void baseInitialization() {
       name = getIntent().getStringExtra("name");
    }

    @Override
    public int setR_Layout() {
        return R.layout.act_r_name;
    }

    @Override
    public void viewInitialization() {
        setBackPress();
        setTitleTx(getString(R.string.r_name_tv3));

        rname_tv = (EditText) $(R.id.rname_tv);
        commit_tv = $(R.id.commit_tv);
        commit_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name = rname_tv.getText().toString();
                if (TextUtils.isEmpty(name)) {
                    T.showShort(getMContext(), getString(R.string.r_name_tv2));
                    return;
                }
                presenter.changeName(name);
            }
        });
        ToolUtils.setButtone(commit_tv,new EditText[]{rname_tv});
        rname_tv.setText(name);
    }

    @Override
    public void doBusiness() {

    }


    @Override
    public Context getMContext() {
        return this;
    }

    @Override
    protected void onDestroy() {
        commit_tv.setTag(null);
        super.onDestroy();

    }

    @Override
    public void successChange(String name) {
        Intent intent = new Intent();
        intent.putExtra("name",name);
        setResult(1,intent);
        finish();
    }
}