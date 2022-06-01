package com.svv.jys.code.module.myself.login.forgetpw;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.mvp.MvpActivity;
import com.svv.jys.code.common.utils.T;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.login.forgetpsw2.ForgetPsw2Act;
import com.svv.jys.code.module.myself.login.forgetpw.model.IForgetLoginPwModel;
import com.svv.jys.code.module.myself.login.forgetpw.presenter.ForgetLoginPwPresenter;
import com.svv.jys.code.module.myself.login.forgetpw.view.IForgetLoginPwView;

/**
 * 找回登录密码
 * Created by Administrator on 2017/11/14 0014.
 */

public class ForgetLoginPwAct extends MvpActivity<IForgetLoginPwView, IForgetLoginPwModel, ForgetLoginPwPresenter> implements IForgetLoginPwView {

    private EditText right_mobile_et,et_idcard;
    private TextView rightCommit;
    private String username;
    private String idcard;

    @Override
    public Context getMContext() {
        return this;
    }

    @Override
    public ForgetLoginPwPresenter initPresenter() {
        return new ForgetLoginPwPresenter();
    }

    @Override
    public void baseInitialization() {

    }

    public static void startWithType(String type,Context context){
        Intent intent = new Intent(context,ForgetLoginPwAct.class);
        //类型，0：短信，1：邮件
        intent.putExtra("type",type);
        context.startActivity(intent);
    }

    @Override
    public int setR_Layout() {
        return R.layout.act_forgetloginpw;
    }

    @Override
    public void viewInitialization() {
        setBackPress();
        setTitleTx(getString(R.string.forgetLoginPw_title));
        right_mobile_et = (EditText) $(R.id.right_mobile_et);
        et_idcard = (EditText) $(R.id.et_idcard);
        rightCommit = (TextView) $(R.id.rightCommit);
        rightCommit.setSelected(true);
        rightCommit.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View view) {
                username = right_mobile_et.getText().toString().trim();
                if (ToolUtils.isNull(username)){
                    T.showShort(ForgetLoginPwAct.this,getString(R.string.forgetpsw7));
                    return;
                }
                presenter.docode(username);
            }
        });
    }

    @Override
    public void doBusiness() {

    }

    @Override
    public void succese() {
        Bundle bundle=new Bundle();
        bundle.putString("username",username);
        gotoActivity(ForgetPsw2Act.class,true,bundle);
    }
}
