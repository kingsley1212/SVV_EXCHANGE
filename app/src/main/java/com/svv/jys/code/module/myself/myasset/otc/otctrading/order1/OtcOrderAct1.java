package com.svv.jys.code.module.myself.myasset.otc.otctrading.order1;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.svv.jys.R;
import com.svv.jys.code.common.base.mvp.BaseAcitvity;
import com.svv.jys.code.module.myself.myasset.otc.otctrading.order1.fragment.OtcOrderFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by js on 2018/9/4.
 */
public class OtcOrderAct1 extends BaseAcitvity {
    private TabLayout tab_order_manager;
    private ViewPager vp_order_manager;
    private List<Fragment> fragments ;
    private List<String> titles=new ArrayList<>();

    @Override
    public void baseInitialization() {

    }

    @Override
    public int setR_Layout() {
        return R.layout.act_otc_order1;
    }

    @Override
    public void viewInitialization() {
        setBackPress();
        setTitleTx(getString(R.string.myselffragment_item2));
        tab_order_manager=findViewById(R.id.tab_order_manager);
        vp_order_manager=findViewById(R.id.vp_order_manager);
        titles.add(getString(R.string.jinxingz));
        titles.add(getString(R.string.end));
        fragments=new ArrayList<>();
        fragments.add(OtcOrderFragment.newInstance("0"));
        fragments.add(OtcOrderFragment.newInstance("1"));
        vp_order_manager.setAdapter(new MyAdapter(getSupportFragmentManager(),titles));
        tab_order_manager.setupWithViewPager(vp_order_manager);
    }

    @Override
    public void doBusiness() {

    }

    public class MyAdapter extends FragmentPagerAdapter {
        private List<String> list;

        public MyAdapter(FragmentManager fm, List<String> list) {
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
            return list.get(position);
        }
    }

}
