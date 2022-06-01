package com.svv.jys.code.module.myself.base.safapsw;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.mvp.MvpActivity;
import com.svv.jys.code.common.constant.ACEConstant;
import com.svv.jys.code.common.db.nativecache.ACache;
import com.svv.jys.code.common.utils.CountDownMsgUtils;
import com.svv.jys.code.common.utils.T;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.base.safapsw.model.SafePswModel;
import com.svv.jys.code.module.myself.base.safapsw.persenter.SafePswPersenter;
import com.svv.jys.code.module.myself.base.safapsw.view.SafePswview;

/**
 * Created by js on 2018/5/21.
 */
public class SafePswAct extends MvpActivity<SafePswview, SafePswModel, SafePswPersenter> implements SafePswview,
        TextWatcher {
    private EditText et_safe_psw, et_safe_repsw, et_safe_code, et_old_safe_psw, et_safe_psw_nickname;
    private TextView tv_send_code, commit_safepsw;
    private CountDownMsgUtils countDownMsgUtils;
    private String code, psw, repsw, old;
    private String changeType = "set";
    private boolean loginpsw;
    public String pswType;
    private String nickname;
    private RelativeLayout rl_verify_code;

    @Override
    public Context getMContext() {
        return this;
    }

    @Override
    public SafePswPersenter initPresenter() {
        return new SafePswPersenter();
    }

    @Override
    public void baseInitialization() {
        loginpsw = getIntent().getBooleanExtra("loginpsw", false);
    }

    @Override
    public int setR_Layout() {
        return R.layout.act_safe_psw;
    }

    @Override
    public void viewInitialization() {
        setBackPress();
        rl_verify_code = findViewById(R.id.rl_verify_code);
        et_old_safe_psw = findViewById(R.id.et_old_safe_psw);
        et_safe_psw_nickname = findViewById(R.id.et_safe_psw_nickname);
        et_safe_psw = findViewById(R.id.et_safe_psw);
        et_safe_repsw = findViewById(R.id.et_safe_repsw);
        et_safe_code = findViewById(R.id.et_safe_code);
        ToolUtils.setEditTextInhibitInputSpace(et_old_safe_psw);
        ToolUtils.setEditTextInhibitInputSpace(et_safe_psw);
        ToolUtils.setEditTextInhibitInputSpace(et_safe_repsw);
        et_safe_code.addTextChangedListener(this);
        et_safe_repsw.addTextChangedListener(this);
        et_safe_psw.addTextChangedListener(this);
        et_safe_psw_nickname.addTextChangedListener(this);
        tv_send_code = findViewById(R.id.tv_send_code);
        commit_safepsw = findViewById(R.id.commit_safepsw);
        commit_safepsw.setSelected(true);
        if (ToolUtils.isNull(ACache.get(this).getAsString(ACEConstant.ACE_WERIFY_KEY))) {
            rl_verify_code.setVisibility(View.GONE);
        } else {
            rl_verify_code.setVisibility(View.VISIBLE);
            if (ACache.get(this).getAsString(ACEConstant.ACE_WERIFY_KEY).equals("google")) {
                et_safe_code.setHint(R.string.google9);
                tv_send_code.setVisibility(View.GONE);
            } else if (ACache.get(this).getAsString(ACEConstant.ACE_WERIFY_KEY).equals("email")) {
                et_safe_code.setHint(R.string.assat30);
                tv_send_code.setVisibility(View.VISIBLE);
            } else if (ACache.get(this).getAsString(ACEConstant.ACE_WERIFY_KEY).equals("mobile")) {
                et_safe_code.setHint(R.string.assat29);
                tv_send_code.setVisibility(View.VISIBLE);
            }
        }
        if (loginpsw) {
            setTitleTx(getString(R.string.safepsw1));
            pswType = "pwd";
            et_old_safe_psw.setHint(getString(R.string.safepsw2));
            et_safe_psw_nickname.setVisibility(View.GONE);
            countDownMsgUtils = new CountDownMsgUtils(ACEConstant.ACE_LOGIN_DJS, this, 60, tv_send_code);
        } else {
            if (ToolUtils.isNull(ACache.get(getMContext()).getAsString(ACEConstant.ACE_USERINFO_SECPSW))) {
                setTitleTx(getString(R.string.safepsw3));
                et_old_safe_psw.setVisibility(View.GONE);
                changeType = "set";
                et_safe_psw_nickname.setVisibility(View.VISIBLE);
                countDownMsgUtils = new CountDownMsgUtils(ACEConstant.ACE_SET_DJS, this, 60, tv_send_code);
            } else {
                et_safe_psw_nickname.setVisibility(View.GONE);
                setTitleTx(getString(R.string.safepsw4));
                et_old_safe_psw.setHint(getString(R.string.safepsw5));
                et_old_safe_psw.setVisibility(View.VISIBLE);
                changeType = "change";
                pswType = "sec_pwd";
                countDownMsgUtils = new CountDownMsgUtils(ACEConstant.ACE_CHANGE_DJS, this, 60, tv_send_code);
            }
        }


        tv_send_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                presenter.getCode();
            }
        });
        commit_safepsw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                code = et_safe_code.getText().toString().trim();
                psw = et_safe_psw.getText().toString().trim();
                repsw = et_safe_repsw.getText().toString().trim();
                old = et_old_safe_psw.getText().toString().trim();
                nickname = et_safe_psw_nickname.getText().toString().trim();
                if (loginpsw) {
                    if (ToolUtils.isNull(old)) {
                        T.showShort(SafePswAct.this, getString(R.string.safepsw2));
                        return;
                    }
                    if (ToolUtils.isNull(psw)) {
                        T.showShort(SafePswAct.this, getString(R.string.safepsw12));
                        return;
                    }
                    if (ToolUtils.isNull(repsw)) {
                        T.showShort(SafePswAct.this, getString(R.string.safepsw13));
                        return;
                    }
                    if (rl_verify_code.getVisibility() == View.VISIBLE && ToolUtils.isNull(code)) {
                        T.showShort(SafePswAct.this, getString(R.string.safepsw8));
                        return;
                    }
                    presenter.changePsw(code, psw, repsw, old, pswType);
                } else {

                    if (changeType.equals("set")) {
                        nickname = et_safe_psw_nickname.getText().toString().trim();
                        if (ToolUtils.isNull(nickname)) {
                            T.showShort(SafePswAct.this, getString(R.string.safepsw14));
                            return;
                        }
                        if (ToolUtils.isNull(psw)) {
                            T.showShort(SafePswAct.this, getString(R.string.safepsw15));
                            return;
                        }
                        if (ToolUtils.isNull(repsw)) {
                            T.showShort(SafePswAct.this, getString(R.string.safepsw7));
                            return;
                        }
//                        if (ToolUtils.isNull(code)) {
//                            T.showShort(SafePswAct.this, getString(R.string.safepsw8));
//                            return;
//                        }
                        presenter.setSafePsw(code, psw, repsw, nickname);
                    } else {
                        if (ToolUtils.isNull(old)) {
                            T.showShort(SafePswAct.this, getString(R.string.safepsw5));
                            return;
                        }

                        if (ToolUtils.isNull(psw)) {
                            T.showShort(SafePswAct.this, getString(R.string.safepsw6));
                            return;
                        }
                        if (ToolUtils.isNull(repsw)) {
                            T.showShort(SafePswAct.this, getString(R.string.safepsw7));
                            return;
                        }
//                        if (ToolUtils.isNull(code)) {
//                            T.showShort(SafePswAct.this, getString(R.string.safepsw8));
//                            return;
//                        }
                        presenter.changePsw(code, psw, repsw, old, pswType);
                    }
                }


            }
        });
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

//        if (ToolUtils.isNull(code)||ToolUtils.isNull(psw)||ToolUtils.isNull(repsw)){
//            commit_safepsw.setSelected(false);
//        }else {
//            if (changeType.equals("set")){
//                commit_safepsw.setSelected(true);
//            }else {
//                if (ToolUtils.isNull(old)){
//                    commit_safepsw.setSelected(false);
//                }else {
//                    commit_safepsw.setSelected(true);
//                }
//
//            }
//
//        }
    }

    @Override
    public void successCode() {
        countDownMsgUtils.requestSuccess();
    }

    @Override
    public void setSafeSuccess() {
        T.showShort(this, getString(R.string.safepsw9));
        ACache.get(this).put(ACEConstant.ACE_USERINFO_SECPSW, psw);
        finish();
    }

    @Override
    public void changeSafeSuccess() {
        if (loginpsw) {
            T.showShort(this, getString(R.string.safepsw10));
        } else {
            T.showShort(this, getString(R.string.safepsw11));
        }
        finish();
    }
}
