package com.svv.jys.code.module.myself.myasset.countdetail.transfer;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.mvp.MvpActivity;
import com.svv.jys.code.common.entity.LtUserCoinInfoentity;
import com.svv.jys.code.common.entity.UserCoinInfo;
import com.svv.jys.code.common.utils.T;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.myasset.countdetail.transfer.model.Transfermodel;
import com.svv.jys.code.module.myself.myasset.countdetail.transfer.presenter.TransferPreserter;
import com.svv.jys.code.module.myself.myasset.countdetail.transfer.view.TransferView;

/**
 * Created by js on 2018/5/25.
 */

public class TransferAct extends MvpActivity<TransferView,Transfermodel,TransferPreserter> implements TransferView, View.OnClickListener {
    private LtUserCoinInfoentity entity;
    private TextView tv_jiaoyi,tv_lt,tv_transfer_comfir,tv_all;
    private ImageView iv_huhuan;
    private EditText et_transfer_num,et_transfer_psw;
    private String[] s;
    private String coin;
    private String from="2";
    private String num,psa;
    private TabLayout tab_market4;
    @Override
    public Context getMContext() {
        return this;
    }

    @Override
    public TransferPreserter initPresenter() {
        return new TransferPreserter();
    }

    @Override
    public void baseInitialization() {
        entity = (LtUserCoinInfoentity) getIntent().getSerializableExtra("entity");

    }

    @Override
    public int setR_Layout() {
        return R.layout.act_transfer;
    }

    @Override
    public void viewInitialization() {
        setBackPress();
        setTitleTx(getString(R.string.assat4));
        tab_market4= (TabLayout) $(R.id.tab_market4);
        s=entity.getMarket().split("_");
        tv_all=findViewById(R.id.tv_all);
        tv_jiaoyi=findViewById(R.id.tv_jiaoyi);
        tv_lt=findViewById(R.id.tv_lt);
        tv_transfer_comfir=findViewById(R.id.tv_transfer_comfir);
        iv_huhuan=findViewById(R.id.iv_huhuan);
        et_transfer_num=findViewById(R.id.et_transfer_num);
        et_transfer_psw=findViewById(R.id.et_transfer_psw);
        tv_transfer_comfir.setOnClickListener(this);
        iv_huhuan.setOnClickListener(this);
        tv_all.setOnClickListener(this);
        tv_lt.setText(getString(R.string.myassetact_tab1));
        coin=s[0];
        for (int i=0;i<s.length;i++){
            tab_market4.addTab(tab_market4.newTab().setText(s[i].toUpperCase()));
        }
        tab_market4.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                et_transfer_num.setText("");
                coin=s[tab.getPosition()];
                if (isTransfer){
                    presenter.getLtuserinfo(entity.getMarket());
                }else {
                    presenter.getUserCoinInfo(coin);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void doBusiness() {
        presenter.getUserCoinInfo(coin);
    }


    @Override
    public void transferSuccess() {
        T.showShort(this,getString(R.string.assat5));
        finish();

    }
    UserCoinInfo userCoinInfo;
    @Override
    public void setUserCoinInfo(UserCoinInfo userCoinInfo) {
        et_transfer_num.setHint(getString(R.string.assat6)+userCoinInfo.getAble()+coin.toUpperCase());
        this.userCoinInfo=userCoinInfo;
    }
    LtUserCoinInfoentity ltUserCoinInfoentity;
    @Override
    public void getLtUserinfo(LtUserCoinInfoentity entity) {

        ltUserCoinInfoentity=entity;
        if (from.equals("2")){
            et_transfer_num.setText(getString(R.string.assat6)+userCoinInfo.getAble());
        }else {
            if (s[0].equals(coin)){
                et_transfer_num.setHint(getString(R.string.assat6)+entity.getBuy_coin()+coin.toUpperCase());
            }else {
                et_transfer_num.setHint(getString(R.string.assat6)+entity.getSell_coin()+coin.toUpperCase());
            }                }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_transfer_comfir:
                num=et_transfer_num.getText().toString().trim();
                psa=et_transfer_psw.getText().toString().trim();
                if (ToolUtils.isNull(num)){
                    T.showShort(this,getString(R.string.assat7));
                    return;
                }
                if (ToolUtils.isNull(psa)){
                    T.showShort(this,getString(R.string.safepsw6));
                    return;
                }
                presenter.postTransfer(coin,entity.getMarket(),from,num,psa);
                break;
            case R.id.iv_huhuan:
                et_transfer_num.setText("");
                isTransfer=!isTransfer;
                transferType(isTransfer);
                break;
            case R.id.tv_all:
                if (from.equals("2")){
                    et_transfer_num.setText(userCoinInfo.getAble());
                }else {
                    if (s[0].equals(coin)){
                        et_transfer_num.setText(ltUserCoinInfoentity.getBuy_coin());
                    }else {
                        et_transfer_num.setText(ltUserCoinInfoentity.getSell_coin());
                    }                }

                break;
        }
    }
    private boolean isTransfer;
    public void transferType(boolean isTransfer){
        if (isTransfer){
            from="1";
            tv_jiaoyi.setText(getString(R.string.myassetact_tab1));
            tv_lt.setText(getString(R.string.myassetact_tab2));
            presenter.getLtuserinfo(entity.getMarket());
        }else {
            from="2";
            tv_lt.setText(getString(R.string.myassetact_tab1));
            tv_jiaoyi.setText(getString(R.string.myassetact_tab2));
            presenter.getUserCoinInfo(coin);
        }
    }
}
