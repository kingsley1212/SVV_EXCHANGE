package com.svv.jys.code.module.myself.bank.aoe;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.mvp.MvpActivity;
import com.svv.jys.code.common.entity.BankInfoEntity;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.bank.aoe.model.IAddorEditBankModel;
import com.svv.jys.code.module.myself.bank.aoe.presenter.AddorEditBankPresenter;
import com.svv.jys.code.module.myself.bank.aoe.view.IAddorEditBankView;


/**
 * Created by lzj on 2018/7/11.
 */

public class AddorEditBankAct extends MvpActivity<IAddorEditBankView, IAddorEditBankModel, AddorEditBankPresenter>
        implements IAddorEditBankView {
    public static final int ADD_TYPE = 1;
    public static final int EDIT_TYPE = 2;

    public static void startActForAdd(Context context) {
        Intent intent = new Intent(context, AddorEditBankAct.class);
        intent.putExtra("type", ADD_TYPE);
        context.startActivity(intent);
    }

    public static void startActForEdit(Context context, BankInfoEntity bankInfoEntity) {
        Intent intent = new Intent(context, AddorEditBankAct.class);
        intent.putExtra("type", ADD_TYPE);
        intent.putExtra("bankInfoEntity", bankInfoEntity);
        context.startActivity(intent);
    }

    private EditText et_nickname, et_bankname, et_khh, et_bankno, et_khxm;
    private TextView commit_tv;

    @Override
    public Context getMContext() {
        return this;
    }

    @Override
    public AddorEditBankPresenter initPresenter() {
        return new AddorEditBankPresenter();
    }

    @Override
    public void baseInitialization() {

    }

    @Override
    public int setR_Layout() {
        return R.layout.act_addoreditbank;
    }

    @Override
    public void viewInitialization() {
        setTitleTx(getString(R.string.add_bank));
        setBackPress();
        et_nickname = (EditText) $(R.id.et_nickname);
        et_bankname = (EditText) $(R.id.et_bankname);
        et_khh = (EditText) $(R.id.et_khh);
        et_bankno = (EditText) $(R.id.et_bankno);
        et_khxm = (EditText) $(R.id.et_khxm);
        commit_tv = (TextView) $(R.id.commit_tv);
        commit_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.addBank();
            }
        });
        ToolUtils.setButtone(commit_tv,new EditText[]{et_nickname,et_bankname,et_khh,et_bankno,et_khxm});
    }

    @Override
    public void doBusiness() {

    }

    @Override
    public String getNickName() {
        return et_nickname.getText().toString().trim();
    }

    @Override
    public String getBankUser() {
        return et_khxm.getText().toString().trim();
    }

    @Override
    public String getBankNo() {
        return et_bankno.getText().toString().trim();
    }

    @Override
    public String getBankAddress() {
        return et_khh.getText().toString().trim();
    }



    @Override
    public String getBankName() {
        return et_bankname.getText().toString().trim();
    }

    @Override
    protected void onDestroy() {
        commit_tv.setTag(null);
        super.onDestroy();

    }
}
