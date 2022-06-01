package com.svv.jys.code.module.myself.myasset.otc.otctrading.base;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.mvp.MvpActivity;
import com.svv.jys.code.common.utils.ResourceUtils;
import com.svv.jys.code.common.view.popup.PopupNatSelectTypeView;
import com.svv.jys.code.module.myself.myasset.otc.otctrading.base.adapter.OtcViewPagerAdapter;
import com.svv.jys.code.module.myself.myasset.otc.otctrading.base.model.OtcTradeModel;
import com.svv.jys.code.module.myself.myasset.otc.otctrading.base.presenter.OtcTradePresenter;
import com.svv.jys.code.module.myself.myasset.otc.otctrading.base.view.OtcTradeView;
import com.svv.jys.code.module.myself.myasset.otc.otctrading.fabu.sell.OtcFabuSellAct;
import com.svv.jys.code.module.myself.myasset.otc.otctrading.fragment.OtcBuyOrSellFrag;
import com.svv.jys.code.module.myself.myasset.otc.otctrading.order1.OtcOrderAct1;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by js on 2018/6/8.
 */

public class OtcTradeAct extends MvpActivity<OtcTradeView, OtcTradeModel, OtcTradePresenter> implements OtcTradeView,
        View.OnClickListener {
    RelativeLayout rl_otc_back, rl_otc_order, add_otc_order;
    TextView tv_otc_buy, tv_otc_sell;
    ViewPager vp_otc_trade;
    PopupNatSelectTypeView natSelectTypeView;

    private String coin;
    private PopupWindow window;

    @Override
    public Context getMContext() {
        return this;
    }

    @Override
    public OtcTradePresenter initPresenter() {
        return new OtcTradePresenter();
    }

    @Override
    public void baseInitialization() {
        coin = getIntent().getStringExtra("coin");
    }

    @Override
    public int setR_Layout() {
        return R.layout.act_otc_trade;
    }

    @Override
    public void viewInitialization() {
        rl_otc_order = findViewById(R.id.rl_otc_order);
        rl_otc_back = findViewById(R.id.rl_otc_back);
        tv_otc_buy = findViewById(R.id.tv_otc_buy);
        tv_otc_sell = findViewById(R.id.tv_otc_sell);
        vp_otc_trade = findViewById(R.id.vp_otc_trade);
        add_otc_order = findViewById(R.id.add_otc_order);
        rl_otc_back.setOnClickListener(this);
        rl_otc_order.setOnClickListener(this);
        tv_otc_buy.setOnClickListener(this);
        tv_otc_sell.setOnClickListener(this);
        add_otc_order.setOnClickListener(this);
        List<Fragment> list = new ArrayList<>();
        list.add(OtcBuyOrSellFrag.newInstance("1", coin));
        list.add(OtcBuyOrSellFrag.newInstance("0", coin));
        tv_otc_buy.setSelected(true);
        vp_otc_trade.setAdapter(new OtcViewPagerAdapter(getSupportFragmentManager(), list));
        vp_otc_trade.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    tv_otc_buy.setSelected(true);
                    tv_otc_sell.setSelected(false);
                } else {
                    tv_otc_buy.setSelected(false);
                    tv_otc_sell.setSelected(true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        natSelectTypeView = new PopupNatSelectTypeView(this, new PopupNatSelectTypeView.NatSelectType() {
            @Override
            public void toBuy() {
                Bundle bundle=new Bundle();
                bundle.putString("type","0");
                gotoActivity(OtcFabuSellAct.class,false,bundle);
            }

            @Override
            public void toSell() {
                Bundle bundle=new Bundle();
                bundle.putString("type","1");
                gotoActivity(OtcFabuSellAct.class,false,bundle);
            }
        });


    }

    @Override
    public void doBusiness() {
        presenter.isPublish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_otc_back:
                finish();
                break;
            case R.id.tv_otc_buy:
                vp_otc_trade.setCurrentItem(0);
                break;
            case R.id.tv_otc_sell:
                vp_otc_trade.setCurrentItem(1);
                break;
            case R.id.rl_otc_order:
                gotoActivity(OtcOrderAct1.class);
                break;
            case R.id.add_otc_order:
                showPop(view);
//                natSelectTypeView.showPop(view);
                break;
            case R.id.tv_pop_fabu:
                window.dismiss();
                Bundle bundle=new Bundle();
                bundle.putString("type","0");
                gotoActivity(OtcFabuSellAct.class,false,bundle);
                break;
            case R.id.tv_pop_chushou:
                window.dismiss();
                Bundle bundle1=new Bundle();
                bundle1.putString("type","1");
                gotoActivity(OtcFabuSellAct.class,false,bundle1);
                break;
        }
    }

    @Override
    public void isPublish(String s) {
        if (s.equals("true")){
            add_otc_order.setVisibility(View.VISIBLE);
        }else {
            add_otc_order.setVisibility(View.GONE);
        }
    }

    public void showPop(View view){
        View contentView= LayoutInflater.from(this).inflate(R.layout.pop_fabi_more, null, false);
        TextView tv_pop_buy=contentView.findViewById(R.id.tv_pop_fabu);
        tv_pop_buy.setOnClickListener(this);
        TextView tv_pop_sell=contentView.findViewById(R.id.tv_pop_chushou);
        tv_pop_sell.setOnClickListener(this);
        TextView tv_pop_moren=contentView.findViewById(R.id.tv_pop_guanli);
        tv_pop_moren.setOnClickListener(this);
        window = new PopupWindow(contentView, ResourceUtils.dipToPX(this, 150), ResourceUtils.dipToPX(this, 105), true);
        // 设置PopupWindow的背景
        window.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#132737")));
        // 设置PopupWindow是否能响应外部点击事件
        window.setOutsideTouchable(true);
        // 设置PopupWindow是否能响应点击事件
        window.setTouchable(true);
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        window.showAsDropDown(view,-ResourceUtils.dipToPX(this, 110), 0);
    }
}
