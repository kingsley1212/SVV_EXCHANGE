package com.svv.jys.code.module.myself.myasset.countdetail.borrow;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.mvp.MvpActivity;
import com.svv.jys.code.common.entity.LtUserCoinInfoentity;
import com.svv.jys.code.common.entity.MarketInfoEntity;
import com.svv.jys.code.common.utils.T;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.myasset.countdetail.borrow.model.BorrowModel;
import com.svv.jys.code.module.myself.myasset.countdetail.borrow.presenter.BorrowPresenter;
import com.svv.jys.code.module.myself.myasset.countdetail.borrow.view.BorrowView;

/**
 * Created by js on 2018/5/25.
 */

public class BorrowAct extends MvpActivity<BorrowView,BorrowModel,BorrowPresenter> implements BorrowView, View.OnClickListener {
    private LtUserCoinInfoentity entity;
    private TextView tv_borrow_confir,tv_borrow_rote,tv_borrow_max,tv_borrow_already;
    private EditText et_borrow_max;
    private String[] s;
    private String coin;
    private TabLayout tab_market5;
    @Override
    public Context getMContext() {
        return this;
    }

    @Override
    public BorrowPresenter initPresenter() {
        return new BorrowPresenter();
    }

    @Override
    public void baseInitialization() {
        entity = (LtUserCoinInfoentity) getIntent().getSerializableExtra("entity");
    }

    @Override
    public int setR_Layout() {
        return R.layout.act_borrow;
    }

    @Override
    public void viewInitialization() {
        setBackPress();
        setTitleTx(getString(R.string.countdetailact_btn1));
        s = entity.getMarket().split("_");
        tab_market5=findViewById(R.id.tab_market5);
        tv_borrow_confir=findViewById(R.id.tv_borrow_confir);
        tv_borrow_rote=findViewById(R.id.tv_borrow_rote);
        tv_borrow_max=findViewById(R.id.tv_borrow_max);
        tv_borrow_already=findViewById(R.id.tv_borrow_already);
        et_borrow_max=findViewById(R.id.et_borrow_max);
        tv_borrow_confir.setOnClickListener(this);
        selectMarket(0);
        coin=s[0];
        for (int i=0;i<s.length;i++){
            tab_market5.addTab(tab_market5.newTab().setText(s[i].toUpperCase()));
        }
        tv_borrow_confir.setSelected(true);
        tab_market5.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                coin=s[tab.getPosition()];
                selectMarket(tab.getPosition());
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
        presenter.getMarketinfo(entity.getMarket());
    }

    @Override
    public void getMarketinfo(MarketInfoEntity marketInfoEntity) {
        tv_borrow_rote.setText(marketInfoEntity.getDebit_rate()+"%");
    }

    @Override
    public void debitSuccess() {
        T.showShort(this,getString(R.string.assat11));
        finish();
    }

    public void selectMarket(int type){

        if (type==0){
            tv_borrow_already.setText(entity.getBuy_coin_debit()+s[0].toUpperCase());
            tv_borrow_max.setText(entity.getBorrow_buy()+s[0].toUpperCase());
        }else {
            tv_borrow_already.setText(entity.getSell_coin_debit()+s[1].toUpperCase());
            tv_borrow_max.setText(entity.getBorrow_sell()+s[1].toUpperCase());
        }
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_borrow_confir:
                String num=et_borrow_max.getText().toString().trim();
                if (ToolUtils.isNull(num)){
                    T.showShort(this,getString(R.string.assat12));
                    return;
                }
                presenter.postToDebit(entity.getMarket(),coin,num);
                break;
        }
    }
}
