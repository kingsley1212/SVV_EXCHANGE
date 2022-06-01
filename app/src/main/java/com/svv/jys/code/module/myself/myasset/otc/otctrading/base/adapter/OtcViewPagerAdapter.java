package com.svv.jys.code.module.myself.myasset.otc.otctrading.base.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by js on 2018/6/8.
 */

public class OtcViewPagerAdapter extends FragmentPagerAdapter{
    List<Fragment> list;
    public OtcViewPagerAdapter(FragmentManager fm,List<Fragment> list) {
        super(fm);
        this.list=list;
    }

    public void setList(List<Fragment> list){
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
