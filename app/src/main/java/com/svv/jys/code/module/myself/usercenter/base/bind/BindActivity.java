package com.svv.jys.code.module.myself.usercenter.base.bind;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.mvp.MvpActivity;
import com.svv.jys.code.common.constant.ACEConstant;
import com.svv.jys.code.common.db.nativecache.ACache;
import com.svv.jys.code.common.entity.CountryEntity;
import com.svv.jys.code.common.utils.CountDownMsgUtils;
import com.svv.jys.code.common.utils.T;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.country.CountryAct;
import com.svv.jys.code.module.myself.usercenter.base.bind.model.BindModel;
import com.svv.jys.code.module.myself.usercenter.base.bind.presenter.BindPresenter;
import com.svv.jys.code.module.myself.usercenter.base.bind.view.BindView;

/**
 * Created by js on 2018/5/30.
 */

public class BindActivity extends MvpActivity<BindView, BindModel, BindPresenter> implements BindView, View.OnClickListener {
    private EditText et_bind_name, et_bind_code, email_et;
    private TextView tv_send_code, tv_bind_comfir, tv_bind_quhao_code;
    private CountDownMsgUtils countDownMsgUtils;
    private boolean isBindEmail;
    private String type, area;
    private View rl_phone, rl_email;

    @Override
    public Context getMContext() {
        return this;
    }

    @Override
    public BindPresenter initPresenter() {
        return new BindPresenter();
    }

    @Override
    public void baseInitialization() {
        isBindEmail = getIntent().getBooleanExtra("isBindEmail", false);
    }

    @Override
    public int setR_Layout() {
        return R.layout.act_bind;
    }

    @Override
    public void viewInitialization() {
        setBackPress();

        tv_bind_quhao_code = findViewById(R.id.tv_bind_quhao_code);

        et_bind_name = findViewById(R.id.et_bind_name);
        rl_email = findViewById(R.id.rl_email);
        rl_phone = findViewById(R.id.rl_phone);
        email_et = findViewById(R.id.email_et);
        et_bind_code = findViewById(R.id.et_bind_code);
        tv_send_code = findViewById(R.id.tv_send_code);
        tv_bind_comfir = findViewById(R.id.tv_bind_comfir);
        tv_bind_quhao_code.setOnClickListener(this);
        tv_send_code.setOnClickListener(this);
        tv_bind_comfir.setOnClickListener(this);

        if (isBindEmail) {
            countDownMsgUtils = new CountDownMsgUtils(ACEConstant.ACE_BIND_EMAIL_DJS, this, 60, tv_send_code);
            rl_email.setVisibility(View.VISIBLE);
            rl_phone.setVisibility(View.GONE);
            setTitleTx(getString(R.string.BindActivity_email_bind));
            type = "1";
        } else {
            countDownMsgUtils = new CountDownMsgUtils(ACEConstant.ACE_BIND_PHONE_DJS, this, 60, tv_send_code);
            setTitleTx(getString(R.string.bind_phone));
            type = "0";
        }
        ToolUtils.setButtone(tv_bind_comfir, new EditText[]{et_bind_code, et_bind_name, email_et});
    }

    @Override
    public void doBusiness() {

    }

    @Override
    public void onClick(View view) {
        String name;
        String code;
        switch (view.getId()) {
            case R.id.tv_send_code:

                if (isBindEmail) {
                    name = email_et.getText().toString().trim();
                    if (ToolUtils.isNull(name)) {
                        T.showShort(this, getString(R.string.register_email_prompt));
                        return;
                    }
                } else {
                    if (ToolUtils.isNull(area)) {
                        T.showShort(this, getString(R.string.BindActivity_please_quhao));
                        return;
                    }
                    name = et_bind_name.getText().toString().trim();
                    if (ToolUtils.isNull(name)) {
                        T.showShort(this, getString(R.string.register_phone_prompt));
                        return;
                    }
                }
                presenter.getCode(type, name, area);
                break;
            case R.id.tv_bind_comfir:
                if (isBindEmail) {
                    name = email_et.getText().toString().trim();
                    if (ToolUtils.isNull(name)) {
                        T.showShort(this, getString(R.string.register_email_prompt));
                        return;
                    }
                } else {
                    if (ToolUtils.isNull(area)) {
                        T.showShort(this, getString(R.string.BindActivity_please_quhao));
                        return;
                    }
                    name = et_bind_name.getText().toString().trim();
                    if (ToolUtils.isNull(name)) {
                        T.showShort(this, getString(R.string.register_phone_prompt));
                        return;
                    }
                }
                code = et_bind_code.getText().toString().trim();
                if (ToolUtils.isNull(code)) {
                    T.showShort(this, getString(R.string.BindActivity_input_code));
                    return;
                }
                if (isBindEmail) {
                    presenter.bindEmail(name,code);
                } else {
                    presenter.bindPhone(area, name, code);
                }

                break;
            case R.id.tv_bind_quhao_code:
                if (ToolUtils.isFastClick(view.getId())){
                    startActivityForResult(new Intent(getMContext(),CountryAct.class),100);
                }

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode==ACEConstant.REQUEST_OK){
            CountryEntity entity = (CountryEntity) data.getSerializableExtra("countriesEntity");
            area = entity.getCode();
            tv_bind_quhao_code.setText("+" + entity.getCode());
        }
    }

    @Override
    public void successCode() {
        countDownMsgUtils.requestSuccess();
    }

    @Override
    public void successBind() {
        if (isBindEmail) {
            ACache.get(getMContext()).put(ACEConstant.ACE_USERINFO_EMAIL, email_et.getText().toString().trim());
        } else {
            ACache.get(getMContext()).put("mobile", et_bind_name.getText().toString().trim());
        }
        finish();
    }

}
