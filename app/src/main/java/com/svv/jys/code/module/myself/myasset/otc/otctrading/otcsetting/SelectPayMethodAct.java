package com.svv.jys.code.module.myself.myasset.otc.otctrading.otcsetting;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.MBaseAdapter;
import com.svv.jys.code.common.base.mvp.BaseAcitvity;
import com.svv.jys.code.common.entity.AdvSettingEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by js on 2018/7/25.
 */

public class SelectPayMethodAct extends BaseAcitvity {
    private RecyclerView rv_selectcoin;
    private List<AdvSettingEntity.IncomeBean.RowsBean> list;
    private TextView tv_ok;
    @Override
    public void baseInitialization() {
        AdvSettingEntity advSettingEntity= (AdvSettingEntity) getIntent().getSerializableExtra("advSettingEntity");
        list=advSettingEntity.getIncome().getRows();
    }

    @Override
    public int setR_Layout() {
        return R.layout.act_select_pay;
    }

    @Override
    public void viewInitialization() {
        setBackPress();
        tv_ok=findViewById(R.id.tv_ok);
        rv_selectcoin=findViewById(R.id.rv_selectcoin);
        rv_selectcoin.setLayoutManager(new LinearLayoutManager(this));
        final SelectPaymethodAdapter adapter=new SelectPaymethodAdapter(this,list);
        rv_selectcoin.setAdapter(adapter);
        adapter.setOnItemClick(new MBaseAdapter.MOnItemClickListener() {
            @Override
            public void onItemClick(int position, Object o) {
                list.get(position).setSelect(!list.get(position).isSelect());
                adapter.notifyDataSetChanged();
//                Intent intent=new Intent();
//                intent.putExtra("selectpay",list.get(position));
//                setResult(400,intent);
//                finish();
            }
        });
        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<AdvSettingEntity.IncomeBean.RowsBean> incomesBeans=new ArrayList<>();
                for (int i=0;i<list.size();i++){
                    if (list.get(i).isSelect()){
                        incomesBeans.add(list.get(i));
                    }
                }
                Intent intent=new Intent();
                intent.putExtra("selectpay", (Serializable) incomesBeans);
                setResult(400,intent);
                finish();
            }
        });
    }

    @Override
    public void doBusiness() {

    }

}
