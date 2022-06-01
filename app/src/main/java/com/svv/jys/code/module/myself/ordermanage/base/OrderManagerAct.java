package com.svv.jys.code.module.myself.ordermanage.base;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.mvp.MvpActivity;
import com.svv.jys.code.common.view.popup.PopupFilterView;
import com.svv.jys.code.module.myself.ordermanage.base.detailfragment.DetailFrag;
import com.svv.jys.code.module.myself.ordermanage.base.entrustfragment.EntrustFrag;
import com.svv.jys.code.module.myself.ordermanage.base.model.IOrderManagerModel;
import com.svv.jys.code.module.myself.ordermanage.base.presenter.OrderManagerPresenter;
import com.svv.jys.code.module.myself.ordermanage.base.view.IOrderManagerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/2 0002.
 */

public class OrderManagerAct extends MvpActivity<IOrderManagerView, IOrderManagerModel, OrderManagerPresenter>
        implements IOrderManagerView {
    private ViewPager vp_order;
    private List<Fragment> fragments;
    private LinearLayout titles_ll;
    private int n_size = 0;
    private PopupFilterView view;
    private String market,type;

    @Override
    public Context getMContext() {
        return this;
    }

    @Override
    public OrderManagerPresenter initPresenter() {
        return new OrderManagerPresenter();
    }

    @Override
    public void baseInitialization() {

    }

    @Override
    public int setR_Layout() {
        return R.layout.act_ordermanager;
    }

    @Override
    public void viewInitialization() {
        setBackPress();
        view = new PopupFilterView(getMContext());
        view.setOnClickItem(new PopupFilterView.OnResultDismiss() {
            @Override
            public void OnSelect(String m, String t) {
                market = m;
                type = t;
                if(fragments.get(n_size) instanceof  EntrustFrag){
                    ((EntrustFrag) fragments.get(n_size)).setRefershData(market,type);
                }
                else if(fragments.get(n_size) instanceof DetailFrag){
                    ((DetailFrag) fragments.get(n_size)).setRefershData(market,type);
                }


            }
        });
        titles_ll = findViewById(R.id.titles_ll);
        vp_order = findViewById(R.id.vp_order);
        fragments = new ArrayList<>();
        fragments.add(EntrustFrag.newInstance("0"));
        fragments.add(EntrustFrag.newInstance("-1"));
        fragments.add(DetailFrag.newInstance());
        vp_order.setAdapter(new MyAdapter(getSupportFragmentManager()));
        for (int i = 0;i < titles_ll.getChildCount(); i++ ){
            final int finalI = i;
            titles_ll.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    vp_order.setCurrentItem(finalI);

                }
            });
        }
        vp_order.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                changeTab(position);
                if(fragments.get(n_size) instanceof  EntrustFrag){
                    ((EntrustFrag) fragments.get(n_size)).setRefershData(market,type);
                }
                else if(fragments.get(n_size) instanceof DetailFrag){
                    ((DetailFrag) fragments.get(n_size)).setRefershData(market,type);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void changeTab(int position){
        if(position == n_size){
            return;
        }
        TextView bt = (TextView) titles_ll.getChildAt(n_size);
        bt.setTextColor(Color.parseColor("#8f9ba2"));
        bt.setTextSize(14);
        n_size = position;
        TextView at = (TextView) titles_ll.getChildAt(position);
        at.setTextColor(Color.parseColor("#1a456d"));
        at.setTextSize(18);
    }

    @Override
    public void doBusiness() {
        presenter.getExchange();
    }

    @Override
    public void setData(List<String> list) {
        view.setGridData(list);
        $(R.id.filter_iv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.showPop($(R.id.titlebar_ly));
            }
        });

    }

    public class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return 3;
        }

    }
}
