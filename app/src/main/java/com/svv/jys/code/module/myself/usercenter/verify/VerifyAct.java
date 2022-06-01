package com.svv.jys.code.module.myself.usercenter.verify;


import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.mvp.MvpActivity;
import com.svv.jys.code.common.entity.VerifyEntity;
import com.svv.jys.code.common.view.popup.PopupSafeVerifyView;
import com.svv.jys.code.module.myself.usercenter.verify.model.IVerifyModel;
import com.svv.jys.code.module.myself.usercenter.verify.presenter.VerifyPresenter;
import com.svv.jys.code.module.myself.usercenter.verify.view.IVerifyView;

/**
 * Created by lzj on 2018/10/9.
 */

public class VerifyAct extends MvpActivity<IVerifyView, IVerifyModel, VerifyPresenter> implements IVerifyView {
    // m:手机 e:邮箱 g:谷歌
    private String type;
    private boolean status;
    private String value;

    private TextView account_name, account_tv, verify_tv;
    private CheckBox status_iv;
    private PopupSafeVerifyView verifyView;

    public static void startByType(Context context, String type, VerifyEntity entity) {
        Intent intent = new Intent(context, VerifyAct.class);
        intent.putExtra("TYPE", type);
        intent.putExtra("VERIFY", entity);
        context.startActivity(intent);
    }


    @Override
    public VerifyPresenter initPresenter() {
        return new VerifyPresenter();
    }


    @Override
    public void baseInitialization() {
        type = getIntent().getStringExtra("TYPE");
        VerifyEntity entity = (VerifyEntity) getIntent().getSerializableExtra("VERIFY");
        switch (type) {
            case "m":
                status = entity.getVerify().getMobile().equals("2");
                value = entity.getInfo().getMobile();
                break;

            case "e":
                status = entity.getVerify().getEmail().equals("2");
                value = entity.getInfo().getEmail();
                break;

            case "g":
                status = entity.getVerify().getGoogle().equals("2");
                value = entity.getInfo().getGoogle();
                break;
        }
    }

    @Override
    public int setR_Layout() {
        return R.layout.act_verify;
    }

    @Override
    public void viewInitialization() {
        setBackPress();
        account_name = (TextView) $(R.id.account_name);
        account_tv = (TextView) $(R.id.account_tv);
        account_tv.setText(value);
        status_iv = (CheckBox) $(R.id.status_iv);
        status_iv.setChecked(status);
        verify_tv = (TextView) $(R.id.verify_tv);
        switch (type) {
            case "m":
                account_name.setText(getString(R.string.verify_tv1));
                setTitleTx(getString(R.string.phone_verify));
                verify_tv.setText(getString(R.string.phone_verify));
                break;
            case "e":
                account_name.setText(getString(R.string.verify_tv2));
                setTitleTx(getString(R.string.email_verify));
                verify_tv.setText(getString(R.string.email_verify));
                break;
            case "g":
                account_name.setText(getString(R.string.verify_tv3));
                setTitleTx(getString(R.string.google_verify));
                verify_tv.setText(getString(R.string.verify_tv3));
                break;
        }
        status_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                status_iv.setChecked(!status_iv.isChecked());
                if (verifyView == null) {
                    verifyView = new PopupSafeVerifyView(getMContext(), new PopupSafeVerifyView.checkVerifyListener() {
                        @Override
                        public void checkSuccess() {
                            String va = status ? "0" : "1";
                            presenter.changeVerify(type, va);
                        }
                    });
                }

                String value = "0";
                if (status) {
                    value = "1";
                } else {
                    switch (type) {
                        case "m":
                            value = "2";
                            break;
                        case "e":
                            value = "3";
                            break;
                        case "g":
                            value = "4";
                            break;
                    }
                }

                verifyView.showPop(view, value);
            }
        });
    }

    @Override
    public void doBusiness() {
    /* presenter.entity = (VerifyEntity) getIntent().getSerializableExtra("ENTITY");
        switch (type){
            case "0":
                account_name.setText(getString(R.string.verify_tv1));
                account_tv.setText(presenter.entity.getInfo().getMobile());
                break;
            case "1":
                account_name.setText(getString(R.string.verify_tv2));
                account_tv.setText(presenter.entity.getInfo().getEmail());
                break;
        }*/
    }


    @Override
    public Context getMContext() {
        return this;
    }

    @Override
    public void successChange() {
        status_iv.setChecked(!status_iv.isChecked());
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}