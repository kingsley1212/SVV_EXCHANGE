package com.svv.jys.code.module.myself.myaddress.base.coin;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.MBaseAdapter;
import com.svv.jys.code.common.base.mvp.MvpActivity;
import com.svv.jys.code.common.entity.CoinEntity;
import com.svv.jys.code.module.myself.myaddress.base.addresslist.AddressManagerAct;
import com.svv.jys.code.module.myself.myaddress.base.coin.adapter.CoinAdapter;
import com.svv.jys.code.module.myself.myaddress.base.coin.model.CoinModel;
import com.svv.jys.code.module.myself.myaddress.base.coin.persenter.CoinPersenter;
import com.svv.jys.code.module.myself.myaddress.base.coin.view.CoinView;

import java.util.List;

/**
 * Created by js on 2018/5/19.
 */

public class CoinAct extends MvpActivity<CoinView,CoinModel,CoinPersenter> implements CoinView{
    RecyclerView rv_coin;
    private CoinAdapter adapter;

    @Override
    public CoinPersenter initPresenter() {
        return new CoinPersenter();
    }

    @Override
    public void baseInitialization() {

    }

    @Override
    public int setR_Layout() {
        return R.layout.act_coin;
    }

    @Override
    public void viewInitialization() {
        setBackPress();
        setTitleTx(getString(R.string.myselffragment_item6));
        rv_coin=findViewById(R.id.rv_coin);
        rv_coin.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void doBusiness() {
        presenter.getCoin();
    }

    @Override
    public Context getMContext() {
        return this;
    }

    @Override
    public void setCoin(final List<CoinEntity> list) {
        if (list!=null&&list.size()!=0){
            adapter = new CoinAdapter(this,list);
            rv_coin.setAdapter(adapter);
            adapter.setOnItemClick(new MBaseAdapter.MOnItemClickListener() {
                @Override
                public void onItemClick(int position, Object o) {
                    Bundle bundle=new Bundle();
                    bundle.putString("coin",list.get(position).getName());
                    gotoActivity(AddressManagerAct.class,false,bundle);
                }
            });
        }

    }
}
