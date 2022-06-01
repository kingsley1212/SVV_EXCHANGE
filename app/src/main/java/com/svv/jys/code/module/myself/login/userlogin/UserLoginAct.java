package com.svv.jys.code.module.myself.login.userlogin;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pedaily.yc.ycdialoglib.selectDialog.CustomSelectDialog;
import com.svv.jys.R;
import com.svv.jys.code.common.base.mvp.MvpChatSocketActivity;
import com.svv.jys.code.common.constant.ACEConstant;
import com.svv.jys.code.common.db.nativecache.ACache;
import com.svv.jys.code.common.utils.StatusBarUtils;
import com.svv.jys.code.common.utils.T;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.login.base.RegisterAct;
import com.svv.jys.code.module.myself.login.forgetpsw2.ForgetPsw2Act;
import com.svv.jys.code.module.myself.login.userlogin.model.IUserLoginModel;
import com.svv.jys.code.module.myself.login.userlogin.presenter.UserLoginPresenter;
import com.svv.jys.code.module.myself.login.userlogin.view.IUserLoginView;

import java.util.List;

/**
 * Created by Administrator on 2018/5/12 0012.
 */

public class UserLoginAct extends MvpChatSocketActivity<IUserLoginView, IUserLoginModel, UserLoginPresenter> implements IUserLoginView, TextWatcher {
    private TextView forgetPw, toRegister, leftBtnCommit;
    private EditText mobile_et, pwd_et;
    private ImageView eye_iv;
    private LinearLayout eye_ll;
    private View pwd_line, number_line;

    @Override
    public Context getMContext() {
        return this;
    }

    @Override
    public UserLoginPresenter initPresenter() {
        return new UserLoginPresenter();
    }

    @Override
    public void baseInitialization() {

    }

    @Override
    public int setR_Layout() {
        return R.layout.act_userlogin;
    }

    @Override
    public void viewInitialization() {
        setBackPress();
        setTitleTx(getString(R.string.userlogin_title));
        StatusBarUtils.setImmersiveStatusBar(this, true);
        forgetPw = (TextView) $(R.id.forgetPw);
        eye_iv = (ImageView) $(R.id.eye_iv);
        toRegister = (TextView) $(R.id.toRegister);
        eye_ll = (LinearLayout) $(R.id.eye_ll);
        pwd_line = (View) $(R.id.pwd_line);
        number_line = (View) $(R.id.number_line);
        forgetPw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoActivity(ForgetPsw2Act.class);
            }
        });
        toRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoActivity(RegisterAct.class);
            }
        });
        mobile_et = (EditText) $(R.id.mobile_et);
        pwd_et = (EditText) $(R.id.pwd_et);
        mobile_et.addTextChangedListener(this);
        pwd_et.addTextChangedListener(this);
        ToolUtils.setEditTextInhibitInputSpace(pwd_et);
        leftBtnCommit = (TextView) $(R.id.leftBtnCommit);
        leftBtnCommit.setSelected(true);
        leftBtnCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ToolUtils.isNull(mobile_et.getText().toString().trim()) || ToolUtils.isNull(pwd_et.getText().toString().trim())) {
                    T.showShort(getMContext(), getResources().getString(R.string.login_prompt));
                    return;
                }
                presenter.doLogin(mobile_et.getText().toString().trim(), pwd_et.getText().toString().trim());
            }
        });

        eye_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eye_iv.setSelected(!eye_iv.isSelected());
                if (eye_iv.isSelected()) {
                    pwd_et.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    pwd_et.setSelection(pwd_et.getText().toString().length());
                } else {
                    pwd_et.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    pwd_et.setSelection(pwd_et.getText().toString().length());
                }
            }
        });


        mobile_et.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    number_line.setBackgroundColor(ContextCompat.
                            getColor(UserLoginAct.this, R.color.c_1a456d));
                    pwd_line.setBackgroundColor(ContextCompat.
                            getColor(UserLoginAct.this, R.color.c_c3c3c3));
                }
            }
        });
        pwd_et.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    number_line.setBackgroundColor(ContextCompat.
                            getColor(UserLoginAct.this, R.color.c_c3c3c3));
                    pwd_line.setBackgroundColor(ContextCompat.
                            getColor(UserLoginAct.this, R.color.c_1a456d));
                }
            }
        });
    }

    @Override
    public void doBusiness() {
        mobile_et.setText(ACache.get(getMContext()).getAsString(ACEConstant.ACE_USERNAME));
        mobile_et.setSelection(mobile_et.getText().length());
    }


    @Override
    public void loginSuccese() {
        if (mChatService != null) {
            mChatService.subscribeChat();
        }
      /*  ACache.get(getMContext()).put(ACEConstant.ACE_USERNAME,mobile_et.getText().toString().trim());
            Bundle bundle=new Bundle();
            bundle.putSerializable("verify_key",s);
            gotoActivity(LoginValidationAct.class,false,bundle);*/


        finish();
    }

//    @Override
//    public void cSConttectSeccuss() {
//
//    }
//
//    @Override
//    public void cSConttectFail() {
//
//    }
//
//    @Override
//    public void chatConttectSeccuss() {
//
//    }
//
//    @Override
//    public void chatConttectFail() {
//
//    }

    private CustomSelectDialog showDialog(CustomSelectDialog.SelectDialogListener listener,
                                          List<String> names) {
        CustomSelectDialog dialog = new CustomSelectDialog(((Activity) getMContext()),
                R.style.transparentFrameWindowStyle, listener, names);
        dialog.setItemColor(R.color.colorAccent, R.color.colorPrimary);
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
            }
        });
        //判断activity是否finish
        if (!((Activity) getMContext()).isFinishing()) {
            dialog.show();
        }
        return dialog;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
//        if (!TextUtils.isEmpty(mobile_et.getText().toString()) && !TextUtils.isEmpty(pwd_et.getText().toString())) {
//            leftBtnCommit.setSelected(true);
//        } else {
//            leftBtnCommit.setSelected(false);
//        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void cSConttectSeccuss() {

    }

    @Override
    public void cSConttectFail() {

    }
}
