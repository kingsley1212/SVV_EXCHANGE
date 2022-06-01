package com.svv.jys.code.module.myself.welfare;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.mvp.BaseAcitvity;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.welfarerecord.WelfareRecordAct;

import java.util.ArrayList;
import java.util.List;

public class WelfareAct extends BaseAcitvity {
    private int n_size = 0;
    private LinearLayout titles_ll;
    private List<Fragment> fragments;
    private ViewPager vp_order;
    @Override
    public void baseInitialization() {

    }

    @Override
    public int setR_Layout() {
        return R.layout.act_welfare;
    }

    @Override
    public void viewInitialization() {
        setBackPress();
        setTitleTx(getString(R.string.myselffragment_candy_activities));
        vp_order = findViewById(R.id.vp_order);
        fragments = new ArrayList<>();
        fragments.add(WelfareFrag.newInstance("-1"));
        fragments.add(WelfareFrag.newInstance("1"));
        fragments.add(WelfareFrag.newInstance("2"));
        fragments.add(WelfareFrag.newInstance("3"));
        titles_ll = findViewById(R.id.titles_ll);
        TextView right_tv=findViewById(R.id.right_tv);
        right_tv.setVisibility(View.VISIBLE);
        right_tv.setText(getString(R.string.activation_record));
        right_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ToolUtils.isFastClick(v.getId())){
                    gotoActivity(WelfareRecordAct.class);
                }

            }
        });
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
            return 4;
        }

    }
}
