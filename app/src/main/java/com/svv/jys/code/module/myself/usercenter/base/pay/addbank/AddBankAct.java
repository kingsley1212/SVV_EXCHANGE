package com.svv.jys.code.module.myself.usercenter.base.pay.addbank;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.svv.jys.R;
import com.svv.jys.code.common.base.mvp.MvpActivity;
import com.svv.jys.code.common.entity.AddPayReqEntity;
import com.svv.jys.code.common.entity.OtcPayEntity;
import com.svv.jys.code.common.utils.T;
import com.svv.jys.code.common.view.popup.PswPopupView;
import com.svv.jys.code.module.myself.usercenter.base.pay.addbank.model.AddBankModel;
import com.svv.jys.code.module.myself.usercenter.base.pay.addbank.presenter.AddBankPresenter;
import com.svv.jys.code.module.myself.usercenter.base.pay.addbank.view.AddBankView;

public class AddBankAct extends MvpActivity<AddBankView,AddBankModel,AddBankPresenter> implements AddBankView {
    private OtcPayEntity.RowsBean entity;
    private EditText et_pay_name,et_bank_zhanghao,et_bank_bank,et_bank_address;
    private TextView tv_add;
    @Override
    public AddBankPresenter initPresenter() {
        return new AddBankPresenter();
    }

    @Override
    public void baseInitialization() {
        entity = (OtcPayEntity.RowsBean) getIntent().getSerializableExtra("entity");
    }

    @Override
    public int setR_Layout() {
        return R.layout.act_add_bank;
    }

    @Override
    public void viewInitialization() {
        setBackPress();
        et_pay_name=findViewById(R.id.et_pay_name);
        et_bank_zhanghao=findViewById(R.id.et_bank_zhanghao);
        et_bank_bank=findViewById(R.id.et_bank_bank);
        et_bank_address=findViewById(R.id.et_bank_address);
        tv_add=findViewById(R.id.tv_add);
        tv_add.setOnClickListener(new View.OnClickListener() {

            private String bank_address;
            private String bank;
            private String zhanghao;
            private String name;

            @Override
            public void onClick(View v) {
                name = et_pay_name.getText().toString().trim();
                zhanghao = et_bank_zhanghao.getText().toString().trim();
                bank = et_bank_bank.getText().toString().trim();
                bank_address = et_bank_address.getText().toString().trim();
                if (TextUtils.isEmpty(name)){
                    T.showShort(AddBankAct.this,getResources().getString(R.string.add_pay_name));
                    return;
                }
                if (TextUtils.isEmpty(zhanghao)){
                    T.showShort(AddBankAct.this,getResources().getString(R.string.AddPayAct_input_bank_hao));
                    return;
                }
                if (TextUtils.isEmpty(bank)){
                    T.showShort(AddBankAct.this,getResources().getString(R.string.addpay_kai_et_bank));
                    return;
                }
                PswPopupView pswPopupView=new PswPopupView(AddBankAct.this, new PswPopupView.SubmitLisnener() {
                    @Override
                    public void doSubmit(String pw) {
                        AddPayReqEntity addPayReqEntity = new AddPayReqEntity(bank_address, zhanghao, bank, name);
                        String memo = JSON.toJSONString(addPayReqEntity);
                        presenter.addPay( entity.getCode(), memo, pw);
                    }
                });
                pswPopupView.showPop(v);
            }
        });
    }

    @Override
    public void doBusiness() {

    }

    @Override
    public Context getMContext() {
        return this;
    }

    @Override
    public void addSuccese() {
        finish();
    }
}
