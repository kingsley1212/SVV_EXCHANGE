package com.svv.jys.code.module.myself.myasset.jiaoyi.chargemoney;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.ClipboardManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.WriterException;
import com.svv.jys.R;
import com.svv.jys.code.common.base.mvp.MvpActivity;
import com.svv.jys.code.common.entity.CoinAddressEntity;
import com.svv.jys.code.common.utils.T;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.coin.coinlist.CoinListAct;
import com.svv.jys.code.module.myself.myasset.jiaoyi.chargemoney.model.ChargeMoneyModel;
import com.svv.jys.code.module.myself.myasset.jiaoyi.chargemoney.presenter.ChargeMoneyPresenter;
import com.svv.jys.code.module.myself.myasset.jiaoyi.chargemoney.view.ChargeMoneyView;
import com.svv.jys.code.module.myself.myasset.jiaoyi.chargerecord.ChargeRecordAct;
import com.svv.jys.code.module.zxing.encoding.EncodingHandler;

/**
 * Created by js on 2018/5/26.
 */

public class ChargeMoneyAct extends MvpActivity<ChargeMoneyView, ChargeMoneyModel, ChargeMoneyPresenter> implements
        ChargeMoneyView {
    private ImageView iv_charge_code;
    private TextView iv_charge_address, tv_charge_name, tv_copy, tips_text, coin_tv,tv_charge_tip,tv_copy_tip;
    private String coin;
    private View coins_ll,tip_ll;

    @Override
    public Context getMContext() {
        return this;
    }

    @Override
    public ChargeMoneyPresenter initPresenter() {
        return new ChargeMoneyPresenter();
    }

    @Override
    public void baseInitialization() {
        coin = getIntent().getStringExtra("coin");
    }

    public static void StartByCoin(Context context,String coin){
        Intent intent = new Intent(context,ChargeMoneyAct.class);
        intent.putExtra("coin",coin);
        context.startActivity(intent);
    }

    @Override
    public int setR_Layout() {
        return R.layout.act_charge;
    }

    @Override
    public void viewInitialization() {
        setBackPress();
        setTitleTx(getString(R.string.assat23));
        ImageView iv_title=findViewById(R.id.iv_title);
        iv_title.setImageResource(R.mipmap.ic_record);
        findViewById(R.id.rl_right).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ToolUtils.isFastClick(v.getId())){
                    Bundle bundle=new Bundle();
                    bundle.putString("coin",presenter.coinAddressEntity.getCoin());
                    gotoActivity(ChargeRecordAct.class,false,bundle);
                }

            }
        });
        iv_charge_code = findViewById(R.id.iv_charge_code);
        iv_charge_address = findViewById(R.id.iv_charge_address);
        tv_charge_name = findViewById(R.id.tv_charge_name);
        tv_copy = findViewById(R.id.tv_copy);
        tips_text = findViewById(R.id.tips_text);
        coin_tv = findViewById(R.id.coin_tv);
        coins_ll = findViewById(R.id.coins_ll);
        tip_ll = findViewById(R.id.tip_ll);
        tv_charge_tip = findViewById(R.id.tv_charge_tip);
        tv_copy_tip = findViewById(R.id.tv_copy_tip);
        tv_copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                // 将文本内容放到系统剪贴板里。
                cm.setText(iv_charge_address.getText());
                T.showShort(ChargeMoneyAct.this, getString(R.string.assat24));
            }
        });
        coins_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ToolUtils.isFastClick(view.getId())){
                    CoinListAct.startByCharge(getMContext());
                }
            }
        });
    }

    @Override
    public void doBusiness() {
        if(TextUtils.isEmpty(coin))
            CoinListAct.startByCharge(getMContext());
        else
            presenter.setAddress(coin);
    }

    public void getCode(CoinAddressEntity address) {
        try {
            String at = address.getAddress();
            if(at.contains("_")) {
                iv_charge_address.setText(at.split("_")[0]);
                tip_ll.setVisibility(View.VISIBLE);
                tv_charge_tip.setText(at.split("_")[1]);
                tv_copy_tip.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                        // 将文本内容放到系统剪贴板里。
                        cm.setText(tv_charge_tip.getText());
                        T.showShort(ChargeMoneyAct.this, getString(R.string.assat24));
                    }
                });
            }else {
                iv_charge_address.setText(at);
                tip_ll.setVisibility(View.GONE);
            }
            Bitmap mBitmap = EncodingHandler.createQRCode(address.getAddress(), 500);
            if (mBitmap != null) {
                iv_charge_code.setImageBitmap(mBitmap);
            }
            tips_text.setText(address.getTips());
            coin_tv.setText(address.getCoin().toUpperCase());

        } catch (WriterException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 401 && resultCode == 501) {
            String coin = data.getStringExtra("coin");
            presenter.setAddress(coin);
        }else {
            afterGetAddress();
        }
    }

    @Override
    public void afterGetAddress() {
       if(presenter.coinAddressEntity == null){
           finish();
       }else {
           getCode(presenter.coinAddressEntity);
       }
    }
}
