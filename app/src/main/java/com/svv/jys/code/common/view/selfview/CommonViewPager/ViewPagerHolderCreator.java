package com.svv.jys.code.common.view.selfview.CommonViewPager;

/**
 * Created by lzj on 2018/6/8.
 */
public interface ViewPagerHolderCreator<VH extends ViewPagerHolder> {
    /**
     * 创建ViewHolder
     * @return
     */
    public VH createViewHolder();
}