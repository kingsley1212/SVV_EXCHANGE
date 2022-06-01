package com.svv.jys.code.module.business.c2cbusiness.base.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.mvp.MvpFragment;
import com.svv.jys.code.common.entity.BankInfoEntity;
import com.svv.jys.code.common.entity.C2CCoinInfoEntity;
import com.svv.jys.code.common.utils.T;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.common.view.popup.PopupBusinessSubmitView;
import com.svv.jys.code.module.business.c2cbusiness.base.fragment.model.IC2CTradeModel;
import com.svv.jys.code.module.business.c2cbusiness.base.fragment.presenter.C2CTradePresenter;
import com.svv.jys.code.module.business.c2cbusiness.base.fragment.view.IC2CTradeView;
import com.svv.jys.code.module.myself.bank.base.BankManagerAct;


/**
 * Created by js on 2018/7/11.
 */

public class C2CTradeFragment extends MvpFragment<IC2CTradeView, IC2CTradeModel, C2CTradePresenter> implements
        IC2CTradeView, View
        .OnClickListener {

    private String name;
    private TextView tv_c2c_sell_name, tv_c2c_buy_name, tv_c2c_buy_price, tv_c2c_name, tv_c2c_may_buy_price, tv_buy,
            tv_shuoming, tv_c2c_sell_price, tv_sell, tv_c2c_name1, tv_c2c_may_sell_price;
    private EditText et_c2c_buy_nun, et_c2c_sell_nun;
    private String sell_num;
    private String sell_price, buy_price;
    private String type;

    public static C2CTradeFragment newInstance(String name) {
        Bundle args = new Bundle();
        args.putSerializable("name", name);
        C2CTradeFragment fragment = new C2CTradeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Context getMContext() {
        return getActivity();
    }

    @Override
    public C2CTradePresenter initPresenter() {
        return new C2CTradePresenter();
    }

    @Override
    public void onWakeBussiness() {
    }

    @Override
    public void baseInitialization() {
        name = getArguments().getString("name");
    }


    @Override
    public int setR_Layout() {
        return R.layout.fragment_c2ctrade;
    }

    @Override
    public void viewInitialization() {
        tv_shuoming = (TextView) $(R.id.tv_shuoming);
        tv_c2c_sell_name = (TextView) $(R.id.tv_c2c_sell_name);
        tv_c2c_buy_name = (TextView) $(R.id.tv_c2c_buy_name);
        tv_c2c_buy_price = (TextView) $(R.id.tv_c2c_buy_price);
        tv_c2c_name = (TextView) $(R.id.tv_c2c_name);
        tv_c2c_may_buy_price = (TextView) $(R.id.tv_c2c_may_buy_price);
        tv_buy = (TextView) $(R.id.tv_buy);
        tv_c2c_sell_price = (TextView) $(R.id.tv_c2c_sell_price);
        tv_sell = (TextView) $(R.id.tv_sell);
        tv_c2c_name1 = (TextView) $(R.id.tv_c2c_name1);
        tv_c2c_may_sell_price = (TextView) $(R.id.tv_c2c_may_sell_price);
        et_c2c_buy_nun = (EditText) $(R.id.et_c2c_buy_nun);
        et_c2c_sell_nun = (EditText) $(R.id.et_c2c_sell_nun);


        tv_buy.setOnClickListener(this);
        tv_sell.setOnClickListener(this);
        tv_c2c_sell_name.setText(name.toUpperCase());
        tv_c2c_buy_name.setText(name.toUpperCase());
        tv_c2c_name.setText(name.toUpperCase());
        tv_c2c_name1.setText(name.toUpperCase());
        et_c2c_buy_nun.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String buy_num = et_c2c_buy_nun.getText().toString().trim();
                if (!ToolUtils.isNull(buy_num)) {
                    tv_c2c_may_buy_price.setText(ToolUtils.multiplyWithScale(buy_num, buy_price, 2));
                } else {
                    tv_c2c_may_buy_price.setText("0.00");
                }

            }
        });
        et_c2c_sell_nun.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String sell_num = et_c2c_sell_nun.getText().toString().trim();
                if (!ToolUtils.isNull(sell_num)) {
                    tv_c2c_may_sell_price.setText(ToolUtils.multiplyWithScale(sell_num, sell_price, 2));
                } else {
                    tv_c2c_may_sell_price.setText("0.00");
                }

            }
        });

    }

    @Override
    public void doBusiness() {
        presenter.getc2cCoinInfo(name);
    }


    private PopupBusinessSubmitView businessSubmitView;

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_buy:
                type = "1";
                if (businessSubmitView == null) {
                    businessSubmitView = new PopupBusinessSubmitView(getMContext(), new PopupBusinessSubmitView
                            .SubmitLisnener() {
                        @Override
                        public void doCancle() {

                        }

                        @Override
                        public void doSubmit(String price, String num, String pw,int t) {
                            presenter.buyorsell(name, type, num, "", pw);
                        }
                    });
                } else {
                    businessSubmitView.dismiss();
                }
                String buy_num = et_c2c_buy_nun.getText().toString().trim();
                if (ToolUtils.isNull(buy_num)) {
                    T.showShort(getActivity(), getString(R.string.et_trade_num));
                    return;
                }
                businessSubmitView.showPop(view, 1, buy_price, buy_num,"","");
                break;
            case R.id.tv_sell:
                type = "2";
//                String sell_num = et_c2c_sell_nun.getText().toString().trim();
                sell_num = et_c2c_sell_nun.getText().toString().trim();
                if (ToolUtils.isNull(sell_num)) {
                    T.showShort(getActivity(), getString(R.string.et_trade_num));
                    return;
                } else {
                    Intent intent = new Intent(getActivity(), BankManagerAct.class);
                    intent.putExtra("isselectBank", true);
                    startActivityForResult(intent, 1);
                }

//                String sell_num=et_c2c_sell_nun.getText().toString().trim();
                break;
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 2) {
            final BankInfoEntity entity = (BankInfoEntity) data.getSerializableExtra("bank");
            if (businessSubmitView == null) {
                businessSubmitView = new PopupBusinessSubmitView(getMContext(), new PopupBusinessSubmitView
                        .SubmitLisnener() {
                    @Override
                    public void doCancle() {

                    }

                    @Override
                    public void doSubmit(String price, String num, String pw,int t) {
                        presenter.buyorsell(name, type, num, entity.getId(), pw);
                    }
                });
            } else {
                businessSubmitView.dismiss();
            }

            businessSubmitView.showPop(tv_shuoming, 2, sell_price, sell_num,"","");

        }

    }

    @Override
    public void setCoinInfo(C2CCoinInfoEntity entity) {
        sell_price = entity.getSell_price();
        buy_price = entity.getBuy_price();
        tv_c2c_buy_price.setText(entity.getBuy_price());
        tv_c2c_sell_price.setText(entity.getSell_price());
        tv_shuoming.setText(entity.getC2c_tips());
    }

    @Override
    public void buyorsellsuccese() {
        T.showShort(getActivity(), getString(R.string.trade_succese));
    }
}
