package com.svv.jys.code.module.myself.myasset.jiaoyi.rengou;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.mvp.MvpActivity;
import com.svv.jys.code.common.entity.HomeAssatEntity;
import com.svv.jys.code.common.utils.T;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.myasset.jiaoyi.rengou.model.RenGouModel;
import com.svv.jys.code.module.myself.myasset.jiaoyi.rengou.presenter.RenGouPresenter;
import com.svv.jys.code.module.myself.myasset.jiaoyi.rengou.view.RenGouView;
import com.svv.jys.code.module.myself.myasset.jiaoyi.rengourecord.RenGouRecordAct;

/**
 * Created by js on 2018/10/19.
 */

public class RenGouAct extends MvpActivity<RenGouView,RenGouModel,RenGouPresenter> implements RenGouView, View.OnClickListener, TextWatcher {
    private String coin,type;
    private TextView tv_rengou_coin,right_tv,tv_rengou_sxf,tv_rengou_total,tv_rengou_commit,rengou_price,tv_rengou_wenzi,tv_rengou_price;
    private EditText et_rengou_num;
    private HomeAssatEntity.Rows bean;
    @Override
    public Context getMContext() {
        return this;
    }

    @Override
    public RenGouPresenter initPresenter() {
        return new RenGouPresenter();
    }

    @Override
    public void baseInitialization() {
        Intent intent=getIntent();
        coin = intent.getStringExtra("coin");
        type = intent.getStringExtra("type");
        bean = (HomeAssatEntity.Rows) intent.getSerializableExtra("assat");
    }

    @Override
    public int setR_Layout() {
        return R.layout.act_rengou;
    }

    @Override
    public void viewInitialization() {
        setBackPress();
        tv_rengou_price=findViewById(R.id.tv_rengou_price);
        tv_rengou_wenzi=findViewById(R.id.tv_rengou_wenzi);
        tv_rengou_sxf=findViewById(R.id.tv_rengou_sxf);
        tv_rengou_total=findViewById(R.id.tv_rengou_total);
        tv_rengou_commit=findViewById(R.id.tv_rengou_commit);
        rengou_price=findViewById(R.id.rengou_price);
        et_rengou_num=findViewById(R.id.et_rengou_num);
        right_tv=findViewById(R.id.right_tv);
        right_tv.setVisibility(View.VISIBLE);
        right_tv.setText(getString(R.string.jilu));
        right_tv.setOnClickListener(this);
        tv_rengou_commit.setOnClickListener(this);
        tv_rengou_coin=findViewById(R.id.tv_rengou_coin);
        tv_rengou_coin.setText(coin.toUpperCase());
        if (type.equals("1")){
            setTitleTx(coin.toUpperCase()+getString(R.string.rengou));
            rengou_price.setText(bean.getRg_price());
            tv_rengou_wenzi.setText(getString(R.string.please_rengou_num));
            tv_rengou_commit.setText(getString(R.string.tijiao_rengou));
            tv_rengou_price.setText(getString(R.string.rengou_price));
        }else {
            setTitleTx(coin.toUpperCase()+getString(R.string.peishou));
            rengou_price.setText(bean.getSell_price());
            tv_rengou_wenzi.setText(getString(R.string.please_peishou_price));
            tv_rengou_commit.setText(getString(R.string.tijiao_peishou));
            tv_rengou_price.setText(getString(R.string.peishou_price));
        }
        et_rengou_num.addTextChangedListener(this);
    }

    @Override
    public void doBusiness() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.right_tv:
                Bundle bundle=new Bundle();
                bundle.putString("type",type);
                bundle.putString("coin",coin);
                gotoActivity(RenGouRecordAct.class,false,bundle);
                break;
            case R.id.tv_rengou_commit:
                String num= et_rengou_num.getText().toString().trim();
                if (ToolUtils.isNull(num)){
                    if (type.equals("1")){
                        T.showShort(this,getString(R.string.please_rengou_num));
                    }else {
                        T.showShort(this,getString(R.string.please_peishou_price));
                    }
                   return;
                }
                presenter.rengou(type,coin,num);
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        String temp = s.toString();
        int posDot = temp.indexOf(".");
        if (posDot>0){
            if (temp.length() - posDot - 1 > 8) {
                s.delete(posDot + 9, s.length());
            }
        }
        String num= et_rengou_num.getText().toString().trim();
        if (ToolUtils.isNull(num)){
            tv_rengou_sxf.setText("0.00");
            tv_rengou_total.setText("0.00");
        }else {
            if (type.equals("1")){
                if(ToolUtils.String2Double(bean.getRg_price())==0.00){
                    tv_rengou_sxf.setText("0.00");
                    tv_rengou_total.setText("0.00");
                }else {
                    if (ToolUtils.String2Double(bean.getRg_sxf())==0.00){
                        tv_rengou_sxf.setText("0.00");
                        tv_rengou_total.setText(ToolUtils.multiplyWithScale(bean.getRg_price(),num,8));
                    }else {
                        String rg_sxf=ToolUtils.multiplyWithScale(ToolUtils.multiplyWithScale(ToolUtils.multiplyWithScale(bean.getRg_price(),num,8),bean.getRg_sxf(),8),"0.01",8);
                        tv_rengou_sxf.setText(rg_sxf);
                        tv_rengou_total.setText(ToolUtils.add(ToolUtils.multiplyWithScale(bean.getRg_price(),num,8),rg_sxf,8));
                    }
                }

            }else {
                if(ToolUtils.String2Double(bean.getSell_price())==0.00){
                    tv_rengou_sxf.setText("0.00");
                    tv_rengou_total.setText("0.00");
                }else {
                    if (ToolUtils.String2Double(bean.getPs_sxf())==0.00){
                        tv_rengou_sxf.setText("0.00");
                        tv_rengou_total.setText(ToolUtils.multiplyWithScale(bean.getSell_price(),num,8));
                    }else {
                        String ps_sxf=ToolUtils.multiplyWithScale(ToolUtils.multiplyWithScale(ToolUtils.multiplyWithScale(bean.getSell_price(),num,8),bean.getPs_sxf(),8),"0.01",8);
                        tv_rengou_sxf.setText(ps_sxf);
                        tv_rengou_total.setText(ToolUtils.add(ToolUtils.multiplyWithScale(bean.getSell_price(),num,8),ps_sxf,8));
                    }
                }

            }
        }


    }

    @Override
    public void rengouSuccese() {
        finish();
    }
}
