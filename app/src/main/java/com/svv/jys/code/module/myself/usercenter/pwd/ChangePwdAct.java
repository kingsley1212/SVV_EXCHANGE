package com.svv.jys.code.module.myself.usercenter.pwd;


import android.content.Context;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.mvp.MvpActivity;
import com.svv.jys.code.common.utils.T;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.common.view.popup.PopupSafeVerifyView;
import com.svv.jys.code.module.myself.usercenter.pwd.model.IChangePwdModel;
import com.svv.jys.code.module.myself.usercenter.pwd.presenter.ChangePwdPresenter;
import com.svv.jys.code.module.myself.usercenter.pwd.view.IChangePwdView;

/**
 * Created by lzj on 2018/10/9.
 */

public class ChangePwdAct extends MvpActivity<IChangePwdView, IChangePwdModel, ChangePwdPresenter> implements IChangePwdView {
    private EditText new_pwd_et, sure_pwd_et;
    private View rl_new_eyes, rl_sure_eyes, commit_tv;
    private ImageView iv_new_eyes, iv_sure_eyes;

    @Override
    public ChangePwdPresenter initPresenter() {
        return new ChangePwdPresenter();
    }


    @Override
    public void baseInitialization() {

    }

    @Override
    public int setR_Layout() {
        return R.layout.act_change_pwd;
    }

    @Override
    public void viewInitialization() {
        setBackPress();
        setTitleTx(getString(R.string.UserCenterAct_change_psw));
        new_pwd_et = (EditText) $(R.id.new_pwd_et);
        sure_pwd_et = (EditText) $(R.id.sure_pwd_et);
        rl_new_eyes = $(R.id.rl_new_eyes);
        rl_sure_eyes = $(R.id.rl_sure_eyes);
        commit_tv = $(R.id.commit_tv);
        iv_new_eyes = (ImageView) $(R.id.iv_new_eyes);
        iv_sure_eyes = (ImageView) $(R.id.iv_sure_eyes);
        rl_new_eyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_new_eyes.setSelected(!iv_new_eyes.isSelected());
                if (iv_new_eyes.isSelected()) {
                    new_pwd_et.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    new_pwd_et.setSelection(new_pwd_et.getText().toString().length());
                } else {
                    new_pwd_et.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    new_pwd_et.setSelection(new_pwd_et.getText().toString().length());
                }
            }
        });
        rl_sure_eyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_sure_eyes.setSelected(!iv_sure_eyes.isSelected());
                if (iv_sure_eyes.isSelected()) {
                    sure_pwd_et.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    sure_pwd_et.setSelection(sure_pwd_et.getText().toString().length());
                } else {
                    sure_pwd_et.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    sure_pwd_et.setSelection(sure_pwd_et.getText().toString().length());
                }
            }
        });
        commit_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String new_pwd = new_pwd_et.getText().toString();
                final String sure_pwd = sure_pwd_et.getText().toString();
                if (TextUtils.isEmpty(new_pwd)) {
                    T.showShort(getMContext(), getString(R.string.login_inputpassword));
                    return;
                }
                if (TextUtils.isEmpty(sure_pwd)) {
                    T.showShort(getMContext(), getString(R.string.login_inputrepassword));
                    return;
                }
                if (new_pwd.length()<6||sure_pwd.length()<6){
                    T.showShort(getMContext(), getString(R.string.psw_6));
                    return;
                }
                new PopupSafeVerifyView(getMContext(), new PopupSafeVerifyView.checkVerifyListener() {
                    @Override
                    public void checkSuccess() {
                        presenter.changeVerify(new_pwd,sure_pwd);
                    }
                }).showPop(view,"1");

            }
        });
        ToolUtils.setButtone(commit_tv,new EditText[]{new_pwd_et,sure_pwd_et});
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
    public void successChange() {
        finish();
    }
}