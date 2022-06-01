package com.svv.jys.code.module.myself.myasset.otc.otctrading.otcsetting;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.MBaseAdapter;
import com.svv.jys.code.common.base.mvp.BaseAcitvity;
import com.svv.jys.code.common.entity.AdvSettingEntity;

import java.util.List;

/**
 * Created by js on 2018/7/25.
 */

public class SelectAddressAct extends BaseAcitvity {
    private RecyclerView rv_selectcoin;
    private List<AdvSettingEntity.CountryBean> list;
    @Override
    public void baseInitialization() {
        AdvSettingEntity advSettingEntity= (AdvSettingEntity) getIntent().getSerializableExtra("advSettingEntity");
        list=advSettingEntity.getCountry();
    }

    @Override
    public int setR_Layout() {
        return R.layout.act_selectcoin;
    }

    @Override
    public void viewInitialization() {
        setBackPress();
        rv_selectcoin=findViewById(R.id.rv_selectcoin);
        rv_selectcoin.setLayoutManager(new LinearLayoutManager(this));
        SelectAddressAdapter adapter=new SelectAddressAdapter(this,list);
        rv_selectcoin.setAdapter(adapter);
        adapter.setOnItemClick(new MBaseAdapter.MOnItemClickListener() {
            @Override
            public void onItemClick(int position, Object o) {
                Intent intent=new Intent();
                intent.putExtra("selectaddress",list.get(position));
                setResult(200,intent);
                finish();
            }
        });
    }

    @Override
    public void doBusiness() {

    }

}
