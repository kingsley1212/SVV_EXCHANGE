package com.svv.jys.code.module.myself.myasset.jiaoyi.suocang;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.pedaily.yc.ycdialoglib.selectDialog.CustomSelectDialog;
import com.svv.jys.R;
import com.svv.jys.code.common.base.mvp.MvpActivity;
import com.svv.jys.code.common.entity.CunFangSetEntity;
import com.svv.jys.code.common.entity.SuoCangLiXiEntity;
import com.svv.jys.code.common.utils.T;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.myasset.jiaoyi.suocang.model.SuoCangModel;
import com.svv.jys.code.module.myself.myasset.jiaoyi.suocang.presenter.SuoCangPresenter;
import com.svv.jys.code.module.myself.myasset.jiaoyi.suocang.view.SuoCangView;
import com.svv.jys.code.module.myself.myasset.jiaoyi.suocangrecord.SuoCangRecordAct;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by js on 2018/8/10.
 */

public class SuocangAct extends MvpActivity<SuoCangView,SuoCangModel,SuoCangPresenter> implements SuoCangView, View.OnClickListener {
    private TextView tv_cunfang_record,tv_cunfang_coin,tv_cunfang_time,tv_confir_cunfang;
    private TextView et_cunfang_num,tv_getlixi,tv_getEvery;
    private CunFangSetEntity.ListBean coin;
    private CunFangSetEntity cunFangSetEntity;
    private String time,rcoin;
    private List<String> RCoins;
    @Override
    public Context getMContext() {
        return this;
    }

    @Override
    public SuoCangPresenter initPresenter() {
        return new SuoCangPresenter();
    }

    @Override
    public void baseInitialization() {
        coin = (CunFangSetEntity.ListBean) getIntent().getSerializableExtra("listbean");
        cunFangSetEntity = (CunFangSetEntity) getIntent().getSerializableExtra("CunFangSetEntity");
    }

    @Override
    public int setR_Layout() {
        return R.layout.act_suocang;
    }

    @Override
    public void viewInitialization() {
        setBackPress();
        setTitleTx(getString(R.string.suocang));
        tv_cunfang_record=findViewById(R.id.tv_cunfang_record);
        tv_cunfang_coin=findViewById(R.id.tv_cunfang_coin);
        tv_cunfang_time=findViewById(R.id.tv_cunfang_time);
        tv_confir_cunfang=findViewById(R.id.tv_confir_cunfang);
        et_cunfang_num=findViewById(R.id.et_cunfang_num);
        tv_getlixi=findViewById(R.id.tv_getlixi);
        tv_getEvery=findViewById(R.id.tv_getEvery);
        tv_cunfang_record.setOnClickListener(this);
        tv_cunfang_coin.setOnClickListener(this);
        tv_cunfang_time.setOnClickListener(this);
        tv_confir_cunfang.setOnClickListener(this);
        tv_cunfang_coin.setText(coin.getCoin().toUpperCase());
        et_cunfang_num.setOnClickListener(this);
    }

    @Override
    public void doBusiness() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_cunfang_record:
                Bundle bundle=new Bundle();
                bundle.putString("coin",coin.getCoin());
                gotoActivity(SuoCangRecordAct.class,false,bundle);
                break;
            case R.id.tv_cunfang_time:
                showCustomDialog();
                break;
            case R.id.tv_confir_cunfang:
                if (ToolUtils.isNull(time)){
                    T.showLong(this,getString(R.string.suocang_zhouqi));
                    return;
                }
                if (TextUtils.isEmpty(rcoin)){
                    T.showLong(this,getString(R.string.suocang_tv1));
                    return;
                }
              presenter.cunfang(coin.getCoin(),rcoin,time);
                break;
            case R.id.et_cunfang_num:
                showRCoins();
                break;
        }
    }


    private void showCustomDialog() {
        final List<String> names = new ArrayList<>();
        if (cunFangSetEntity!=null&&cunFangSetEntity.getRevolution()!=null){
            for (int i=0;i<cunFangSetEntity.getRevolution().size();i++){
                names.add(cunFangSetEntity.getRevolution().get(i).getName());
            }
        }
        showDialog(new CustomSelectDialog.SelectDialogListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               tv_cunfang_time.setText(names.get(position));
                time=cunFangSetEntity.getRevolution().get(position).getValue();
                getLiXiToShow();
            }
        }, names);
    }

    public void showRCoins(){

        if (RCoins == null) {
            RCoins = new ArrayList<>();
            List<CunFangSetEntity.ListBean> list = cunFangSetEntity.getList();
            for (CunFangSetEntity.ListBean bean : list) {
                if (coin.getCoin().equals(bean.getCoin())){
                    RCoins.add(bean.getR_coin().toUpperCase());
                }
            }
        }
        showDialog(new CustomSelectDialog.SelectDialogListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                et_cunfang_num.setText(RCoins.get(position));
                rcoin = RCoins.get(position).toLowerCase();
                getLiXiToShow();
            }
        },RCoins);
    }

    private CustomSelectDialog showDialog(CustomSelectDialog.SelectDialogListener listener,
                                          List<String> names) {
        CustomSelectDialog dialog = new CustomSelectDialog(((Activity) getMContext()),
                R.style.transparentFrameWindowStyle, listener, names);
        dialog.setItemColor(R.color.colorAccent, R.color.colorPrimary);
        //判断activity是否finish
        if (!((Activity) getMContext()).isFinishing()) {
            dialog.show();
        }
        return dialog;
    }

    @Override
    public void suoCangSucese() {

        finish();
    }

    @Override
    public void showLiXi(SuoCangLiXiEntity entity) {
        tv_getEvery.setText(entity.getDay_money());
        tv_getlixi.setText(entity.getReturn_money());
    }

    public void getLiXiToShow(){
        if (TextUtils.isEmpty(rcoin)||TextUtils.isEmpty(time)){
            return;
        }
        presenter.getLiXi(coin.getCoin(),rcoin,time);
    }
}
