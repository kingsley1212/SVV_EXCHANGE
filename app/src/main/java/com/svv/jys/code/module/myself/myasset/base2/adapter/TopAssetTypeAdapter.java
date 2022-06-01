package com.svv.jys.code.module.myself.myasset.base2.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;

/**
 * Created by lzj on 2018/6/8.
 */

public class TopAssetTypeAdapter extends PagerAdapter {
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return false;
    }


//    public List<TopAssetTypeBean> beans;
//
//
//    @Override
//    public int getCount() {
//        return beans == null ? 0 : beans.size();
//    }
//
//    @Override
//    public boolean isViewFromObject(View view, Object object) {
//        return view == object;
//    }
//
//    @Override
//    public Object instantiateItem(ViewGroup container, int position) {
//        //重点就在这儿了，不再是把布局写死，而是用接口提供的布局
//        // 也不在这里绑定数据，数据绑定交给Api调用者。
//        View view = getView(position,null,container);
//        container.addView(view);
//        return view;
//    }

}
