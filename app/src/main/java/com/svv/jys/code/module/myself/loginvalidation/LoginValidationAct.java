package com.svv.jys.code.module.myself.loginvalidation;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.mvp.MvpActivity;
import com.svv.jys.code.common.constant.ACEConstant;
import com.svv.jys.code.common.db.nativecache.ACache;
import com.svv.jys.code.common.entity.FToken;
import com.svv.jys.code.common.utils.CountDownMsgUtils;
import com.svv.jys.code.common.utils.T;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.loginvalidation.model.LoginValidationModel;
import com.svv.jys.code.module.myself.loginvalidation.presenter.LoginValidationPresenter;
import com.svv.jys.code.module.myself.loginvalidation.view.LoginValidationView;


/**
 * Created by js on 2018/9/14.
 */

public class LoginValidationAct extends MvpActivity<LoginValidationView,LoginValidationModel,LoginValidationPresenter> implements LoginValidationView, View.OnClickListener {
    private EditText et_code;
    private TextView getcode,tv_confir;
    private CountDownMsgUtils loginVerifyUtils;
    private FToken fToken;
    String verify_key;
    @Override
    public Context getMContext() {
        return this;
    }

    @Override
    public LoginValidationPresenter initPresenter() {
        return new LoginValidationPresenter();
    }
    @Override
    public void baseInitialization() {
        fToken= (FToken) getIntent().getSerializableExtra("verify_key");
        verify_key=fToken.getVerify_key();
        ACache.get(this).put("succese","1");
    }

    @Override
    public int setR_Layout() {
        return R.layout.act_login_validation;
    }

    @Override
    public void viewInitialization() {
        et_code=findViewById(R.id.code);
        getcode=findViewById(R.id.getcode);
        tv_confir=findViewById(R.id.tv_confir);
        getcode.setOnClickListener(this);
        tv_confir.setOnClickListener(this);
        if (verify_key.equals("google")){
            et_code.setHint(R.string.google9);
            getcode.setVisibility(View.GONE);
        }else if (verify_key.equals("email")){
            et_code.setHint(R.string.assat30);
        }else if (verify_key.equals("mobile")){
            et_code.setHint(R.string.assat29);
        }
        loginVerifyUtils = new CountDownMsgUtils(ACEConstant.ACE_LOGIN_VERIFY_DJS, this, 60, getcode);
    }

    @Override
    public void doBusiness() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.getcode:
                presenter.getCode();
                break;
            case R.id.tv_confir:
                String code=et_code.getText().toString().trim();
                if (ToolUtils.isNull(code)){
                    T.showShort(this, getString(R.string.BindActivity_input_code));
                    return;
                }
                presenter.verify(code);
                break;
        }
    }

    @Override
    public void successCode() {
        loginVerifyUtils.requestSuccess();
    }

    @Override
    public void verifySuccese() {
        ACache.get(this).put("verifycode","1");
        ACache.get(this).put("succese","2");
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ToolUtils.logout(this);
    }
}
