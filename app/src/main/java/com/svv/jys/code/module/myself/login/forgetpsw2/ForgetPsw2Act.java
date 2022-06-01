package com.svv.jys.code.module.myself.login.forgetpsw2;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.mvp.MvpActivity;
import com.svv.jys.code.common.constant.ACEConstant;
import com.svv.jys.code.common.utils.CountDownMsgUtils;
import com.svv.jys.code.common.utils.T;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.login.forgetpsw2.model.ForgetPsw2Model;
import com.svv.jys.code.module.myself.login.forgetpsw2.persenter.ForgetPsw2Persenter;
import com.svv.jys.code.module.myself.login.forgetpsw2.view.ForgetPsw2View;

/**
 * Created by js on 2018/5/21.
 */

public class ForgetPsw2Act extends MvpActivity<ForgetPsw2View,ForgetPsw2Model,ForgetPsw2Persenter> implements ForgetPsw2View, TextWatcher {
    private EditText right_checkCode_et,right_pwd_et,right_repwd_et,mobile_et,identify_et;
    private TextView Commit,getCheckCode;
    private String username;
    private String code1;
    private String psw1;
    private String psw2;
    private CountDownMsgUtils countDownMsgUtils;
    private View pwd_eye_iv,re_eye_iv;

    @Override
    public Context getMContext() {
        return this;
    }

    @Override
    public ForgetPsw2Persenter initPresenter() {
        return new ForgetPsw2Persenter();
    }

    @Override
    public void baseInitialization() {

    }

    @Override
    public int setR_Layout() {
        return R.layout.act_forgetpsw2;
    }

    @Override
    public void viewInitialization() {
        setBackPress();
        setTitleTx(getString(R.string.forgetLoginPw_title));
        pwd_eye_iv = $(R.id.pwd_eye_iv);
        re_eye_iv = $(R.id.re_eye_iv);
        mobile_et = (EditText) $(R.id.mobile_et);
        getCheckCode = (TextView) $(R.id.getCheckCode);
        right_checkCode_et = (EditText) $(R.id.right_checkCode_et);
        right_pwd_et = (EditText) $(R.id.right_pwd_et);
        right_repwd_et = (EditText) $(R.id.right_repwd_et);
        identify_et = (EditText) $(R.id.identify_et);
        Commit = (TextView) $(R.id.Commit);
        countDownMsgUtils = new CountDownMsgUtils(ACEConstant.ACE_DAOJISHI, this, 60, getCheckCode);
        getCheckCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = mobile_et.getText().toString().trim();
                if (ToolUtils.isNull(username)){
                    T.showShort(ForgetPsw2Act.this,getString(R.string.userlogin_input_username));
                    return;
                }
                presenter.docode(username);
            }
        });
        Commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = mobile_et.getText().toString().trim();
                if (ToolUtils.isNull(username)){
                    T.showShort(ForgetPsw2Act.this,getString(R.string.userlogin_input_username));
                    return;
                }
                code1 = right_checkCode_et.getText().toString().trim();
                if (ToolUtils.isNull(code1)){
                    T.showShort(ForgetPsw2Act.this,getString(R.string.forgetpsw1));
                    return;
                }
                psw1 = right_pwd_et.getText().toString().trim();
                if (ToolUtils.isNull(psw1)){
                    T.showShort(ForgetPsw2Act.this,getString(R.string.forgetpsw2));
                    return;
                }
                psw2 = right_repwd_et.getText().toString().trim();
                if (ToolUtils.isNull(psw2)){
                    T.showShort(ForgetPsw2Act.this,getString(R.string.forgetpsw3));
                    return;
                }
                presenter.postForgetPsw2(username,code1,psw1,psw2,identify_et.getText().toString());

            }
        });
        pwd_eye_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pwd_eye_iv.setSelected(!pwd_eye_iv.isSelected());
                if (pwd_eye_iv.isSelected()) {
                    right_pwd_et.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    right_pwd_et.setSelection(right_pwd_et.getText().toString().length());
                } else {
                    right_pwd_et.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    right_pwd_et.setSelection(right_pwd_et.getText().toString().length());
                }
            }
        });
        re_eye_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                re_eye_iv.setSelected(!re_eye_iv.isSelected());
                if (re_eye_iv.isSelected()) {
                    right_repwd_et.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    right_repwd_et.setSelection(right_repwd_et.getText().toString().length());
                } else {
                    right_repwd_et.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    right_repwd_et.setSelection(right_repwd_et.getText().toString().length());
                }
            }
        });
        ToolUtils.setButtone(Commit,new EditText[]{mobile_et,right_checkCode_et,right_pwd_et,right_repwd_et});
    }

    @Override
    public void doBusiness() {

    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    @Override
    public void succese() {
        T.showShort(this,getString(R.string.forgetpsw6));
        finish();
    }

    @Override
    protected void onDestroy() {
        Commit.setTag(null);
        super.onDestroy();
    }

    @Override
    public void getCodeSuccess() {
        countDownMsgUtils.requestSuccess();
    }
}
