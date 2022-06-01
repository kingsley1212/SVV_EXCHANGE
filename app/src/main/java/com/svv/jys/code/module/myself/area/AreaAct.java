package com.svv.jys.code.module.myself.area;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.MBaseAdapter;
import com.svv.jys.code.common.base.mvp.MvpActivity;
import com.svv.jys.code.common.entity.CountryEntity;
import com.svv.jys.code.module.myself.area.adapter.AreaAdapter;
import com.svv.jys.code.module.myself.area.model.AreaModel;
import com.svv.jys.code.module.myself.area.presenter.AreaPresenter;
import com.svv.jys.code.module.myself.area.view.AreaView;

import java.util.List;

/**
 * Created by js on 2018/9/8.
 */

public class AreaAct extends MvpActivity<AreaView,AreaModel,AreaPresenter> implements AreaView{
    private RecyclerView rv_area;
    @Override
    public Context getMContext() {
        return this;
    }

    @Override
    public AreaPresenter initPresenter() {
        return new AreaPresenter();
    }

    @Override
    public void baseInitialization() {

    }

    @Override
    public int setR_Layout() {
        return R.layout.act_area;
    }

    @Override
    public void viewInitialization() {
        setBackPress();
        setTitleTx(getString(R.string.myselffragment_select_the_area));
        rv_area=findViewById(R.id.rv_area);
        rv_area.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void doBusiness() {
        presenter.getCountries();
    }

    @Override
    public void setCountry(List<CountryEntity> list) {
        AreaAdapter adapter=new AreaAdapter(this,list);
        adapter.setOnItemClick(new MBaseAdapter.MOnItemClickListener() {
            @Override
            public void onItemClick(int position, Object o) {
                CountryEntity entity= (CountryEntity) o;
                Intent intent=new Intent();
                intent.putExtra("countriesEntity",entity);
                setResult(2, intent);
                finish();
            }
        });
        rv_area.setAdapter(adapter);
    }
}
