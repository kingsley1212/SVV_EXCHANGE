package com.svv.jys.code.module.business.otcbusiness;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.mvp.MvpFragment;
import com.svv.jys.code.common.utils.T;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.common.view.popup.PopupNatSelectTypeView;
import com.svv.jys.code.module.business.otcbusiness.fragment.OtcBuyOrSellFragment;
import com.svv.jys.code.module.business.otcbusiness.model.IOtcBusinessModel;
import com.svv.jys.code.module.business.otcbusiness.presenter.OtcBusinessPresenter;
import com.svv.jys.code.module.business.otcbusiness.view.IOtcBusinessView;
import com.svv.jys.code.module.myself.myasset.otc.otctrading.base.adapter.OtcViewPagerAdapter;
import com.svv.jys.code.module.myself.myasset.otc.otctrading.fabu.sell.OtcFabuSellAct;
import com.svv.jys.code.module.myself.myasset.otc.otctrading.order.OtcOrderAct;
import com.svv.jys.code.module.net.exception.NeedLoginException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by js on 2018/8/1.
 */

public class OtcBussinessFragment extends MvpFragment<IOtcBusinessView,IOtcBusinessModel,OtcBusinessPresenter> implements IOtcBusinessView, View.OnClickListener {
    RelativeLayout rl_otc_back, rl_otc_order, add_otc_order;
    TextView tv_otc_buy, tv_otc_sell;
    ViewPager vp_otc_trade;
    PopupNatSelectTypeView natSelectTypeView;
    @Override
    public Context getMContext() {
        return getActivity();
    }

    @Override
    public OtcBusinessPresenter initPresenter() {
        return new OtcBusinessPresenter();
    }

    @Override
    public void onWakeBussiness() {
        try {
            ToolUtils.checkLogin(getActivity(),false);
            presenter.isPublish();
        } catch (NeedLoginException e) {
            add_otc_order.setVisibility(View.GONE);
            e.printStackTrace();
        }

    }

    @Override
    public void baseInitialization() {

    }

    @Override
    public int setR_Layout() {
        return R.layout.frag_fabi;
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            ToolUtils.checkLogin(getActivity(),false);
            presenter.isPublish();
        } catch (NeedLoginException e) {
            add_otc_order.setVisibility(View.GONE);
            e.printStackTrace();
        }
    }

    @Override
    public void viewInitialization() {
        rl_otc_order = (RelativeLayout) $(R.id.rl_otc_order);
//        rl_otc_back = (RelativeLayout) $(R.id.rl_otc_back);
        tv_otc_buy = (TextView) $(R.id.tv_otc_buy);
        tv_otc_sell = (TextView) $(R.id.tv_otc_sell);
        vp_otc_trade = (ViewPager) $(R.id.vp_otc_trade);
        add_otc_order = (RelativeLayout) $(R.id.add_otc_order1);
//        rl_otc_back.setOnClickListener(this);
        rl_otc_order.setOnClickListener(this);
        tv_otc_buy.setOnClickListener(this);
        tv_otc_sell.setOnClickListener(this);
        add_otc_order.setOnClickListener(this);
        List<Fragment> list = new ArrayList<>();
        list.add(OtcBuyOrSellFragment.newInstance("1"));
        list.add(OtcBuyOrSellFragment.newInstance("0"));
        tv_otc_buy.setSelected(true);


        vp_otc_trade.setAdapter(new OtcViewPagerAdapter(getChildFragmentManager(), list));
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
        natSelectTypeView = new PopupNatSelectTypeView(getActivity(), new PopupNatSelectTypeView.NatSelectType() {
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
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_otc_buy:
                vp_otc_trade.setCurrentItem(0);
                break;
            case R.id.tv_otc_sell:
                vp_otc_trade.setCurrentItem(1);
                break;
            case R.id.rl_otc_order:
                try {
                    ToolUtils.checkLogin(getActivity(),false);
                    gotoActivity(OtcOrderAct.class);
                } catch (NeedLoginException e) {
                    T.showLong(getActivity(),getString(R.string.please_login));
                    e.printStackTrace();
                }

                break;
            case R.id.add_otc_order1:
                natSelectTypeView.showPop(view);
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
}
