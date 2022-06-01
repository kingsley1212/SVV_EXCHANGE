package com.svv.jys.code.module.myself.myasset.otc.otctransfer;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.mvp.MvpActivity;
import com.svv.jys.code.common.entity.OtcCoinEntity;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.common.view.popup.PopupNewSelectorView;
import com.svv.jys.code.module.myself.myasset.otc.otctransfer.model.OtcTransferModel;
import com.svv.jys.code.module.myself.myasset.otc.otctransfer.presenter.OtcTransferPresenter;
import com.svv.jys.code.module.myself.myasset.otc.otctransfer.view.OtcTransferView;
import com.svv.jys.code.module.myself.myasset.transfer.TransferRecordAct;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by js on 2018/6/7.
 */

public class OtcTransferAct extends MvpActivity<OtcTransferView, OtcTransferModel, OtcTransferPresenter> implements OtcTransferView {

    private TextView coin_tv, coin_num_tv, abel_tv, account_tv1, account_tv2, tv_all, confirm_tv;
    private List<String> strings;
    private EditText transfer_et, et_psw;
    private PopupNewSelectorView popup;

    private String OtcTrans_CoinName;

    @Override
    public Context getMContext() {
        return this;
    }

    @Override
    public OtcTransferPresenter initPresenter() {
        return new OtcTransferPresenter();
    }

    @Override
    public void baseInitialization() {
        OtcTrans_CoinName = getIntent().getStringExtra("OtcTrans_CoinName");
    }

    @Override
    public int setR_Layout() {
        return R.layout.act_new_transfer;
    }

    @Override
    public void viewInitialization() {
        setBackPress();
        setTitleTx(getString(R.string.assat4));
        et_psw = findViewById(R.id.et_psw);
        coin_tv = (TextView) $(R.id.coin_tv);
        coin_num_tv = (TextView) $(R.id.coin_num_tv);
        abel_tv = (TextView) $(R.id.abel_tv);
        account_tv1 = (TextView) $(R.id.account_tv1);
        account_tv2 = (TextView) $(R.id.account_tv2);
        tv_all = (TextView) $(R.id.tv_all);
        confirm_tv = (TextView) $(R.id.confirm_tv);
        transfer_et = (EditText) $(R.id.transfer_et);
        $(R.id.record_iv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ToolUtils.isFastClick(v.getId())) {
                    gotoActivity(TransferRecordAct.class);
                }

            }
        });
        ToolUtils.setButtone(confirm_tv, new EditText[]{transfer_et, et_psw});
    }

    @Override
    public void doBusiness() {
        presenter.getOtcCoin();
    }


    @Override
    public void setOtcCoin(final List<OtcCoinEntity> list) {
        OtcCoinEntity goEntity = null;
        strings = new ArrayList<>();
        for (OtcCoinEntity entity : list) {
            strings.add(entity.getName().toUpperCase());
            if (presenter.entity != null && coin_tv.getText().toString().equals(entity.getName().toUpperCase())) {
                presenter.entity = entity;
            }
            if (!ToolUtils.isNull(OtcTrans_CoinName) && OtcTrans_CoinName.equalsIgnoreCase(entity.getName())) {
                goEntity = entity;
            }
        }
        if (presenter.entity == null) {
            presenter.entity = list.get(0);
        }
        if (!ToolUtils.isNull(OtcTrans_CoinName) && goEntity != null) {
            presenter.entity = goEntity;
        }

        $(R.id.choose_rl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (popup == null) {
                    popup = new PopupNewSelectorView(getMContext(), strings);
                    popup.setValue(presenter.entity.getName().toUpperCase());
                }
                popup.setOnClickItem(new PopupNewSelectorView.OnClickItem() {
                    @Override
                    public void OnSelect(int position, String value) {
                        if (!list.get(position).equals(presenter.entity)) {
                            presenter.entity = list.get(position);
                            setViewData();
                        }
                    }
                });
                popup.showPop(view);

            }
        });
        setViewData();

        $(R.id.change_ll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (presenter.type.equals("0")) {
                    presenter.type = "1";
                    presenter.getOtcCoin();
                } else {
                    presenter.type = "0";
                    presenter.getOtcCoin();
                }
            }
        });
        if (presenter.type.equals("0")) {
            account_tv1.setText(getString(R.string.myassetact_tv1));
            account_tv2.setText(getString(R.string.myassetact_tab3));
        } else {
            account_tv1.setText(getString(R.string.myassetact_tab3));
            account_tv2.setText(getString(R.string.myassetact_tv1));
        }
        tv_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                transfer_et.setText(presenter.entity.getAble());
                transfer_et.setSelection(presenter.entity.getAble().length());
            }
        });
        $(R.id.confirm_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.postOtcTransfer(transfer_et.getText().toString(), "");
            }
        });
    }

    public void setViewData() {
        coin_tv.setText(presenter.entity.getName().toUpperCase());
        coin_num_tv.setText(presenter.entity.getName().toUpperCase());
        abel_tv.setText(String.format(getString(R.string.myassetact_tv3), presenter.entity.getAble(), presenter.entity.getName().toUpperCase()));
        transfer_et.setText("");
    }

    @Override
    public void transferSuccese() {
        finish();
    }

    @Override
    protected void onDestroy() {
        confirm_tv.setTag(null);
        super.onDestroy();

    }
}
