package com.svv.jys.code.module.myself.bank.base;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.mvp.MvpActivity;
import com.svv.jys.code.common.entity.BankInfoEntity;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.common.view.popup.PopupDialogView;
import com.svv.jys.code.module.myself.bank.aoe.AddorEditBankAct;
import com.svv.jys.code.module.myself.bank.base.adapter.BankManagerAdapter;
import com.svv.jys.code.module.myself.bank.base.model.IBankManagerModel;
import com.svv.jys.code.module.myself.bank.base.presenter.BankManagerPresenter;
import com.svv.jys.code.module.myself.bank.base.view.IBankManagerView;

import java.util.List;


/**
 * Created by js on 2018/7/11.
 */

public class BankManagerAct extends MvpActivity<IBankManagerView, IBankManagerModel, BankManagerPresenter> implements
        IBankManagerView {
    private RecyclerView rv_bank;
    private RelativeLayout ll_address;
    private View no_data;
    private TextView noDataText;
    private BankManagerAdapter adapter;

    @Override
    public Context getMContext() {
        return this;
    }

    @Override
    public BankManagerPresenter initPresenter() {
        return new BankManagerPresenter();
    }

    @Override
    public void baseInitialization() {
        isselectBank = getIntent().getBooleanExtra("isselectBank", false);
    }

    @Override
    public int setR_Layout() {
        return R.layout.act_bankmanager;
    }

    @Override
    public void viewInitialization() {
        setBackPress();
        setTitleTx(getString(R.string.bank_manager));
        ImageView iv_title = (ImageView) $(R.id.iv_title);
        iv_title.setImageResource(R.mipmap.ic_add_bank);

        rv_bank = findViewById(R.id.rv_bank);
        ll_address = findViewById(R.id.ll_address);
        no_data = $(R.id.no_data);
        noDataText = (TextView) $(R.id.noDataText);
        noDataText.setText(R.string.no_bank);
        rv_bank.setLayoutManager(new LinearLayoutManager(this));
        iv_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ToolUtils.isFastClick(view.getId())){
                    AddorEditBankAct.startActForAdd(BankManagerAct.this);
                }

            }
        });
    }

    @Override
    public void doBusiness() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.banklist();
    }

    private boolean isselectBank;

    @Override
    public void setbankInfo(List<BankInfoEntity> list) {
        if (list == null || list.size() == 0) {
            no_data.setVisibility(View.VISIBLE);
            rv_bank.setVisibility(View.GONE);
        } else {
            rv_bank.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.GONE);
            adapter = new BankManagerAdapter(this, list);
            rv_bank.setAdapter(adapter);
            adapter.setListener(new BankManagerAdapter.OnItemClickListener() {
                @Override
                public void onDelete(int position, final BankInfoEntity entity) {
                        PopupDialogView view = new PopupDialogView(getMContext(), new PopupDialogView.MClickLisnener() {
                            @Override
                            public void leftBtn() {

                            }

                            @Override
                            public void rightBtn() {
                                presenter.deleteBankMes(entity.getId());

                            }
                        });
                        view.showPop(ll_address, getString(R.string.prompt), getString(R.string.isno_remove_bank),
                                getString(R.string.dialog_cancel), getString(R.string.create_commit));
                }

                @Override
                public void onClick(int position, BankInfoEntity entity) {
                    if (isselectBank) {
                        Intent intent = new Intent();
                        intent.putExtra("bank", entity);
                        setResult(2, intent);
                        finish();
                    }
                }
            });
        }
    }

    @Override
    public void deleteBankMesSuccess() {
        presenter.banklist();
    }
}
