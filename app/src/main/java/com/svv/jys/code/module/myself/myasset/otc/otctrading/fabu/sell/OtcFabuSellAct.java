package com.svv.jys.code.module.myself.myasset.otc.otctrading.fabu.sell;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;

import com.flyco.tablayout.SlidingTabLayout;
import com.svv.jys.R;
import com.svv.jys.code.common.base.mvp.MvpActivity;
import com.svv.jys.code.common.entity.AdvInfoEntity;
import com.svv.jys.code.common.entity.AdvListEntity;
import com.svv.jys.code.common.entity.AdvSettingEntity;
import com.svv.jys.code.common.utils.T;
import com.svv.jys.code.module.myself.myasset.otc.otctrading.fabu.fabufrag.FabuFrag;
import com.svv.jys.code.module.myself.myasset.otc.otctrading.fabu.sell.model.IOtcFabuSellModel;
import com.svv.jys.code.module.myself.myasset.otc.otctrading.fabu.sell.presenter.OtcFabuSellPresenter;
import com.svv.jys.code.module.myself.myasset.otc.otctrading.fabu.sell.view.IOtcFabuSellView;
import java.util.ArrayList;

/**
 * Created by lzj on 2018/7/24.
 */

public class OtcFabuSellAct extends MvpActivity<IOtcFabuSellView, IOtcFabuSellModel, OtcFabuSellPresenter> implements
        IOtcFabuSellView {
    private String type;
    private AdvListEntity.RowsBean rowsBean;
    private SlidingTabLayout tab_publish_adv;
    private ViewPager vp_publish_adv;
    private String[] titles;
    private ArrayList<Fragment> fragments;


    @Override
    public Context getMContext() {
        return this;
    }

    @Override
    public void baseInitialization() {
        type = getIntent().getStringExtra("type");
        rowsBean = (AdvListEntity.RowsBean) getIntent().getSerializableExtra("AdvListEntity");
    }

    @Override
    public int setR_Layout() {
        return R.layout.act_otcfabusell;
    }

    @Override
    public void viewInitialization() {
        setBackPress();
        setTitleTx(getResources().getString(R.string.menu_tv1));
        titles = new String[]{getString(R.string.otc_fabu_sell_buy), getString(R.string.otc_fabu_sell_sell)};
        tab_publish_adv = findViewById(R.id.tab_publish_adv);
        vp_publish_adv = findViewById(R.id.vp_publish_adv);
        fragments = new ArrayList<>();
        fragments.add(FabuFrag.newInstance("0", rowsBean == null ? "" : type.equals("0") ? rowsBean.getId() : ""));
        fragments.add(FabuFrag.newInstance("1", rowsBean == null ? "" : type.equals("1") ? rowsBean.getId() : ""));
        vp_publish_adv.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    tab_publish_adv.setIndicatorColor(Color.parseColor("#1ede89"));
                    tab_publish_adv.setTextSelectColor(Color.parseColor("#1ede89"));
                    tab_publish_adv.setTextUnselectColor(Color.parseColor("#8f9ba2"));
                } else if (position == 1) {
                    tab_publish_adv.setIndicatorColor(Color.parseColor("#ff676a"));
                    tab_publish_adv.setTextSelectColor(Color.parseColor("#ff676a"));
                    tab_publish_adv.setTextUnselectColor(Color.parseColor("#8f9ba2"));
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        tab_publish_adv.setViewPager(vp_publish_adv, titles, this, fragments);
        if (!TextUtils.isEmpty(type)&&type.equals("1")){
            vp_publish_adv.setCurrentItem(1);
        }
    }

    @Override
    public void doBusiness() {
        presenter.getAdvSetting();
    }

    @Override
    public OtcFabuSellPresenter initPresenter() {
        return new OtcFabuSellPresenter();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    AdvSettingEntity advSettingEntity;

    @Override
    public void setAdvSetting(AdvSettingEntity entity) {
        advSettingEntity = entity;
    }

    @Override
    public void setYijia(String s) {

    }

    @Override
    public void publishSuccese() {
        T.showLong(this, getString(R.string.otc_please_fb_success));
        finish();
    }

    @Override
    public void setAdvInfo(AdvInfoEntity advInfoEntity) {

    }

}
