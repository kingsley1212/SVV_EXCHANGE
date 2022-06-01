package com.svv.jys.code.module.business.c2cbusiness.base;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.mvp.MvpActivity;
import com.svv.jys.code.common.entity.C2CMarketEntity;
import com.svv.jys.code.module.business.c2cbusiness.base.fragment.C2CTradeFragment;
import com.svv.jys.code.module.business.c2cbusiness.base.model.IC2CTradeModel;
import com.svv.jys.code.module.business.c2cbusiness.base.presenter.C2CTradePresenter;
import com.svv.jys.code.module.business.c2cbusiness.base.view.IC2CTradeView;
import com.svv.jys.code.module.business.c2cbusiness.record.base.C2CRecordAct;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by js on 2018/7/11.
 */

public class C2CTradeAct extends MvpActivity<IC2CTradeView, IC2CTradeModel, C2CTradePresenter> implements IC2CTradeView,
        View.OnClickListener {
    private ViewPager vp_c2c;
    private TabLayout tab_c2c;
    private List<Fragment> fragments;
    private TextView tv_c2c_record;
    private String mCoin = "";
    private List<C2CMarketEntity> mCMarketEntities;

    @Override
    public Context getMContext() {
        return this;
    }

    @Override
    public C2CTradePresenter initPresenter() {
        return new C2CTradePresenter();
    }

    @Override
    public void baseInitialization() {

    }

    @Override
    public int setR_Layout() {
        return R.layout.act_c2cbussiness;
    }

    @Override
    public void viewInitialization() {
        setBackPress();
        setTitleTx("C2C");
        vp_c2c = (ViewPager) $(R.id.vp_c2c);
        tab_c2c = (TabLayout) $(R.id.tab_c2c);
        fragments = new ArrayList<>();
        tv_c2c_record = findViewById(R.id.tv_c2c_record);
        tv_c2c_record.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void doBusiness() {
        presenter.getc2cmarket();
    }

    @Override
    public void setC2CMarket(List<C2CMarketEntity> list) {
        mCMarketEntities = list;
        if (list != null && list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                fragments.add(C2CTradeFragment.newInstance(list.get(i).getName()));
            }
            vp_c2c.setAdapter(new MyAdapter(getSupportFragmentManager(), list));
            tab_c2c.setupWithViewPager(vp_c2c);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_c2c_record:
                Intent intent = new Intent(this, C2CRecordAct.class);
                intent.putExtra("coin", mCoin);
                startActivity(intent);
                break;
            default:
                break;
        }
    }


    public class MyAdapter extends FragmentPagerAdapter {

        private List<C2CMarketEntity> list;

        public MyAdapter(FragmentManager fm, List<C2CMarketEntity> list) {
            super(fm);
            this.list = list;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return list.get(position).getName().toUpperCase();
        }
    }
}
