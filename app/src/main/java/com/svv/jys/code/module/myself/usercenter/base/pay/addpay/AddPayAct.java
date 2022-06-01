package com.svv.jys.code.module.myself.usercenter.base.pay.addpay;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.MBaseAdapter;
import com.svv.jys.code.common.base.mvp.MvpActivity;
import com.svv.jys.code.common.entity.OtcPayEntity;
import com.svv.jys.code.module.myself.usercenter.base.pay.addbank.AddBankAct;
import com.svv.jys.code.module.myself.usercenter.base.pay.addpay.adapter.AddpayAdapter;
import com.svv.jys.code.module.myself.usercenter.base.pay.addpay.model.AddPayModel;
import com.svv.jys.code.module.myself.usercenter.base.pay.addpay.presenter.AddPayPresenter;
import com.svv.jys.code.module.myself.usercenter.base.pay.addpay.view.AddPayView;
import com.svv.jys.code.module.myself.usercenter.base.pay.addzfborwx.AddZfbOrWxAct;
import com.pedaily.yc.ycdialoglib.selectDialog.CustomSelectDialog;

import java.util.List;

/**
 * Created by js on 2018/6/16.
 */

public class AddPayAct extends MvpActivity<AddPayView, AddPayModel, AddPayPresenter> implements AddPayView{

    private RecyclerView rv_payment;

    @Override
    public Context getMContext() {
        return this;
    }

    @Override
    public AddPayPresenter initPresenter() {
        return new AddPayPresenter();
    }

    @Override
    public void baseInitialization() {

    }

    @Override
    public int setR_Layout() {
        return R.layout.act_addpay;
    }

    @Override
    public void viewInitialization() {
        setBackPress();
        rv_payment=findViewById(R.id.rv_payment);
        rv_payment.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void doBusiness() {
        presenter.payMethod();
    }

//    @Override
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.rl_addpay_method:
//                if (isAdd) {
//                    showCustomDialog();
//                } else {
//                    code = bean.getPay_code();
//                    tv_addpay_method_name.setText(bean.getPay_info());
//                }
//                break;
//            case R.id.tv_addpay_comfir:
//                String username = et_addpay_username.getText().toString().trim();
//                String bankname = et_addpay_bank_name.getText().toString().trim();
//                String bankUsername = et_addpay_bank_username.getText().toString().trim();
//                String zhanghao = et_addpay_zhanghao.getText().toString().trim();
//                String pas = et_addpay_psw.getText().toString().trim();
//                if (ToolUtils.isNull(username)) {
//                    T.showShort(this, getString(R.string.AddPayAct_input_username));
//                    return;
//                }
//
//                if (code.equals("bank")) {
//                    if (ToolUtils.isNull(bankname)) {
//                        T.showShort(this, getString(R.string.AddPayAct_please_input_bank));
//                        return;
//                    }
//                    if (ToolUtils.isNull(bankUsername)) {
//                        T.showShort(this, getString(R.string.AddPayAct_input_kh_bank));
//                        return;
//                    }
//                }
//                if (ToolUtils.isNull(zhanghao)) {
//                    T.showShort(this, getString(R.string.AddPayAct_input_zh));
//                    return;
//                }
//                if (ToolUtils.isNull(pas)) {
//                    T.showShort(this, getString(R.string.AddPayAct_input_psw));
//                    return;
//                }
//
//                String memo;
//
//                if (code.equals("bank")) {
////                    List<AddPayReqEntity> list = new ArrayList<>();
//                    AddPayReqEntity entity = new AddPayReqEntity(bankUsername, zhanghao, bankname, username);
//                    memo = JSON.toJSONString(entity);
////                    list.add(entity);
////                    memo = JSONArray.toJSONString(list);
//                } else {
////                    List<AddPayWxEntity> list = new ArrayList<>();
//                    AddPayWxEntity addPayWxEntity = new AddPayWxEntity(username, zhanghao);
////                    list.add(addPayWxEntity);
////                    memo = JSONArray.toJSONString(list);
//                    memo = JSON.toJSONString(addPayWxEntity);
//                }
//                presenter.addPay(id, code, memo, pas);
//                break;
//        }
//    }

//    private void showCustomDialog() {
//        final List<String> names = new ArrayList<>();
//        for (int i = 0; i < list.size(); i++) {
//            names.add(list.get(i).getName());
//        }
//
//        showDialog(new CustomSelectDialog.SelectDialogListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                code = list.get(position).getCode();
//                tv_addpay_method_name.setText(list.get(position).getName());
//                if (code.equals("bank")) {
//                    ll_addpay_kaihu_bank.setVisibility(View.VISIBLE);
//                    ll_addpay_kaihu_zhihang.setVisibility(View.VISIBLE);
//
//                    tv_addpay_zhanghao.setText(R.string.AddPayAct_bank_number);
//                    et_addpay_zhanghao.setHint(R.string.AddPayAct_input_bank_hao);
//                } else {
//                    ll_addpay_kaihu_bank.setVisibility(View.GONE);
//                    ll_addpay_kaihu_zhihang.setVisibility(View.GONE);
//                    tv_addpay_zhanghao.setText(list.get(position).getName() + getString(R.string.OrderDetailAct_zhagnhao));
//                    et_addpay_zhanghao.setHint(getString(R.string.AddPayAct_please_input) + list.get(position).getName() + getString(R.string.AddPayAct_zhagnhao));
//                }
//            }
//        }, names);
//    }

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
    public void addSuccese() {
        finish();
    }


    @Override
    public void setPaymMethod(OtcPayEntity entity) {
        AddpayAdapter adapter=new AddpayAdapter(this,entity.getRows());
        adapter.setOnItemClick(new MBaseAdapter.MOnItemClickListener() {
            @Override
            public void onItemClick(int position, Object o) {
                OtcPayEntity.RowsBean rowsBean= (OtcPayEntity.RowsBean) o;
                presenter.verify(rowsBean.getCode());
            }
        });
        rv_payment.setAdapter(adapter);
    }

    @Override
    public void verifySuccess(OtcPayEntity.RowsBean bean) {
        Bundle bundle=new Bundle();
        bundle.putSerializable("entity",bean);
        if (bean.getCode().equals("bank")){
            gotoActivity(AddBankAct.class,false,bundle);
        }else {
            gotoActivity(AddZfbOrWxAct.class,false,bundle);
        }
    }

}
