package com.svv.jys.code.module.myself.myasset.countdetail.reimbursement;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.mvp.MvpActivity;
import com.svv.jys.code.common.entity.LtBorrowRecord;
import com.svv.jys.code.common.utils.T;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.myasset.countdetail.reimbursement.model.ReimbursementModel;
import com.svv.jys.code.module.myself.myasset.countdetail.reimbursement.presenter.ReimbursementPresenter;
import com.svv.jys.code.module.myself.myasset.countdetail.reimbursement.view.ReimbursementView;

/**
 * Created by js on 2018/5/29.
 */

public class ReimbursementAct extends MvpActivity<ReimbursementView,ReimbursementModel,ReimbursementPresenter> implements ReimbursementView{
    private LtBorrowRecord.RowsBean entity;
    private EditText et_reimburment_num,et_reimburment_psw;
    private TextView tv_reimburment_confir,tv_total_borrow,tv_borrow_lixi,tv_yihuan,tv_borrow_shengyu;
    private String reimburment_num,reimburment_psw;
    @Override
    public Context getMContext() {
        return this;
    }

    @Override
    public ReimbursementPresenter initPresenter() {
        return new ReimbursementPresenter();
    }

    @Override
    public void baseInitialization() {
        entity= (LtBorrowRecord.RowsBean) getIntent().getSerializableExtra("LtBorrowRecord");
    }

    @Override
    public int setR_Layout() {
        return R.layout.act_reimbursement;
    }

    @Override
    public void viewInitialization() {
        setBackPress();
        setTitleTx(getString(R.string.assat8));
        tv_total_borrow=findViewById(R.id.tv_total_borrow);
        tv_borrow_lixi=findViewById(R.id.tv_borrow_lixi);
        tv_yihuan=findViewById(R.id.tv_yihuan);
        tv_borrow_shengyu=findViewById(R.id.tv_borrow_shengyu);
        tv_reimburment_confir=findViewById(R.id.tv_reimburment_confir);
        tv_reimburment_confir.setSelected(true);
        et_reimburment_num=findViewById(R.id.et_reimburment_num);
        et_reimburment_psw=findViewById(R.id.et_reimburment_psw);
        tv_reimburment_confir.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                reimburment_num = et_reimburment_num.getText().toString().trim();
                reimburment_psw=et_reimburment_psw.getText().toString().trim();
                if (ToolUtils.isNull(reimburment_num)){
                    T.showShort(ReimbursementAct.this,getString(R.string.assat9));
                    return;
                }
                if (ToolUtils.isNull(reimburment_psw)){
                    T.showShort(ReimbursementAct.this,getString(R.string.safepsw6));
                    return;
                }

                presenter.postReimburment(entity.getId(),reimburment_num,reimburment_psw);
            }
        });
        tv_total_borrow.setText(getString(R.string.ReimbursementAct_gj)+entity.getNum());
        tv_borrow_lixi.setText(getString(R.string.assat15)+"："+entity.getAccrual());
        String yihuan="0.00000000";
        if (ToolUtils.String2Double(entity.getAccrual_deal())==0.00&&ToolUtils.String2Double(entity.getDeal())==0.00){
            yihuan="0.00000000";
        }else if (ToolUtils.String2Double(entity.getAccrual_deal())==0.00&&ToolUtils.String2Double(entity.getDeal())!=0.00){
            yihuan=entity.getAccrual_deal();
        }else if (ToolUtils.String2Double(entity.getAccrual_deal())!=0.00&&ToolUtils.String2Double(entity.getDeal())==0.00){
            yihuan=entity.getDeal();
        }else if (ToolUtils.String2Double(entity.getAccrual_deal())!=0.00&&ToolUtils.String2Double(entity.getDeal())!=0.00){
            yihuan=ToolUtils.add(entity.getAccrual_deal(),entity.getDeal());
        }
        tv_yihuan.setText("已还："+yihuan);
        String shengyu="";
        if (ToolUtils.String2Double(entity.getAccrual())!=0.00){
            if (ToolUtils.String2Double(yihuan)==0.00){
                shengyu=ToolUtils.add(entity.getAccrual(),entity.getNum());
            }else {
                shengyu=ToolUtils.subtraction(ToolUtils.add(entity.getAccrual(),entity.getNum()),yihuan);
            }
        }else {
            if (ToolUtils.String2Double(yihuan)==0.00){
                shengyu=entity.getNum();
            }else {
                shengyu=ToolUtils.subtraction(entity.getNum(),yihuan);
            }
        }
        tv_borrow_shengyu.setText(getString(R.string.ReimbursementAct_hk_sy)+shengyu);
    }

    @Override
    public void doBusiness() {

    }

    @Override
    public void reimburmentSuccese() {
        T.showShort(this,getString(R.string.assat10));
        finish();
    }
}
