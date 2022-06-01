package com.svv.jys.code.module.myself.coinshow;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.MBaseAdapter;
import com.svv.jys.code.common.base.mvp.MvpActivity;
import com.svv.jys.code.common.entity.CoinShowEntity;
import com.svv.jys.code.module.myself.coinshow.adapter.CoinShowAdapter;
import com.svv.jys.code.module.myself.coinshow.model.ICoinShowModel;
import com.svv.jys.code.module.myself.coinshow.presenter.CoinShowPresenter;
import com.svv.jys.code.module.myself.coinshow.view.ICoinShowView;

import java.util.List;

/**
 * Created by Administrator on 2018/5/2 0002.
 */

public class CoinShowAct extends MvpActivity<ICoinShowView, ICoinShowModel, CoinShowPresenter> implements
        ICoinShowView {
    private RecyclerView coin_rv;
    private CoinShowAdapter adapter;
    private boolean fubu;
    private String code;

    @Override
    public Context getMContext() {
        return this;
    }

    @Override
    public CoinShowPresenter initPresenter() {
        return new CoinShowPresenter();
    }

    @Override
    public void baseInitialization() {
        fubu = getIntent().getBooleanExtra("fubu",false);
        code = getIntent().getStringExtra("code");
    }

    @Override
    public int setR_Layout() {
        return R.layout.act_coinshow;
    }

    @Override
    public void viewInitialization() {
        setBackPress();
        coin_rv = (RecyclerView) $(R.id.coin_rv);
        adapter = new CoinShowAdapter(getMContext(),fubu,code);
        coin_rv.setLayoutManager(new LinearLayoutManager(getMContext()));
        adapter.setOnItemClick(new MBaseAdapter.MOnItemClickListener() {
            @Override
            public void onItemClick(int position, Object o) {
                Intent intent=new Intent();
                intent.putExtra("code",((CoinShowEntity)o).getCurrency_code());
                setResult(200,intent);
                finish();
            }
        });
        coin_rv.setAdapter(adapter);

    }

    @Override
    public void doBusiness() {
        presenter.getCoinList();
    }




    @Override
    public void setData(List<CoinShowEntity> list) {
        adapter.setData(list);
    }
}
