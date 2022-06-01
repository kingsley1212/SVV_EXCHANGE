package com.svv.jys.code.module.myself.usercenter.base.pay;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.mvp.MvpActivity;
import com.svv.jys.code.common.entity.PayMethodEntity;
import com.svv.jys.code.common.utils.T;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.common.view.popup.PopupDialogView;
import com.svv.jys.code.module.myself.usercenter.base.pay.adapter.PayAdapter;
import com.svv.jys.code.module.myself.usercenter.base.pay.addpay.AddPayAct;
import com.svv.jys.code.module.myself.usercenter.base.pay.model.PayMethedModel;
import com.svv.jys.code.module.myself.usercenter.base.pay.presenter.PayMethedPresenter;
import com.svv.jys.code.module.myself.usercenter.base.pay.view.PayMethedView;

import java.util.List;

/**
 * Created by js on 2018/6/16.
 */

public class PayMethedAct extends MvpActivity<PayMethedView,PayMethedModel,PayMethedPresenter> implements PayMethedView{
    RecyclerView rv_pay;
    private PayAdapter adapter;
    private View no_data;
    private RelativeLayout rl_add_bank;
    @Override
    public Context getMContext() {
        return this;
    }

    @Override
    public PayMethedPresenter initPresenter() {
        return new PayMethedPresenter();
    }

    @Override
    public void baseInitialization() {

    }

    @Override
    public int setR_Layout() {
        return R.layout.act_paymethed;
    }

    @Override
    public void viewInitialization() {
        setBackPress();
        setTitleTx(getString(R.string.otc_buy_sell_zhifu));
        rl_add_bank=findViewById(R.id.rl_add_bank);
        rl_add_bank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ToolUtils.isFastClick(view.getId())){
                    Bundle bundle=new Bundle();
                    bundle.putBoolean("isAdd",true);
                    gotoActivity(AddPayAct.class,false,bundle);
                }

            }
        });
        rv_pay=findViewById(R.id.rv_pay);
        no_data = findViewById(R.id.no_data_pay);
        rv_pay.setLayoutManager(new LinearLayoutManager(this));
        TextView noDataText=findViewById(R.id.noDataText);
        noDataText.setText(R.string.PayMethedAct_no_pay);
    }

    @Override
    public void doBusiness() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getPayMethod();
    }

    @Override
    public void setPayMethod(PayMethodEntity payMethod) {
        final List<PayMethodEntity.RowsBean> bean=payMethod.getRows();
        if (bean!=null&&bean.size()!=0){
            no_data.setVisibility(View.GONE);
            rv_pay.setVisibility(View.VISIBLE);
            if (adapter==null){
                adapter = new PayAdapter(this,bean);
                adapter.setPayManenger(new PayAdapter.PayManenger() {
                    @Override
                    public void Updatepay(int position,PayMethodEntity.RowsBean rowsBean) {
                        Bundle bundle=new Bundle();
                        bundle.putBoolean("isAdd",false);
                        bundle.putSerializable("bean",rowsBean);
                        gotoActivity(AddPayAct.class,false,bundle);
                    }

                    @Override
                    public void delete(final int position, View view, final PayMethodEntity.RowsBean rowsBean) {
                        PopupDialogView popupDialogView = new PopupDialogView(getMContext(), new PopupDialogView.MClickLisnener() {
                            @Override
                            public void leftBtn() {

                            }

                            @Override
                            public void rightBtn() {
                                presenter.deletePay(rowsBean.getId());
                            }
                        });
                        popupDialogView.showPop(view, getString(R.string.PayMethedAct_delete),getString(R.string.PayMethedAct_yes_detele), getString(R.string.PayMethedAct_qux),getResources().getString(R.string.myselffragment_right_text));
                    }

                    @Override
                    public void setStatus(int position, PayMethodEntity.RowsBean rowsBean) {
                        setBean=rowsBean;
                        presenter.setStatus(rowsBean.getId(),rowsBean.getStatus());
                    }
                });
                rv_pay.setAdapter(adapter);
            }else {
               adapter.setdata(bean);
            }

        }else {
            no_data.setVisibility(View.VISIBLE);
            rv_pay.setVisibility(View.GONE);
        }

    }

    PayMethodEntity.RowsBean setBean;

    @Override
    public void deleteSuccese() {
        T.showLong(this,getString(R.string.PayMethedAct_delete_success));
        presenter.getPayMethod();
    }

    @Override
    public void setStatusSuccese() {
       setBean.setStatus(setBean.getStatus().equals("1")?"0":"1");
       adapter.notifyDataSetChanged();
    }
}
