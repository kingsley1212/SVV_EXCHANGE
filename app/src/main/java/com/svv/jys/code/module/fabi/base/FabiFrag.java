package com.svv.jys.code.module.fabi.base;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.svv.jys.R;
import com.svv.jys.code.common.base.mvp.MvpFragment;
import com.svv.jys.code.common.entity.AdvSettingEntity;
import com.svv.jys.code.common.entity.OtcAdvEntity;
import com.svv.jys.code.common.utils.ResourceUtils;
import com.svv.jys.code.common.utils.StatusBarUtils;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.fabi.base.model.FabiModel;
import com.svv.jys.code.module.fabi.base.presenter.FabiPresenter;
import com.svv.jys.code.module.fabi.base.view.FabiView;
import com.svv.jys.code.module.fabi.fragment.adapter.BuyOrSellAdapter1;
import com.svv.jys.code.module.fabi.screen.ScreenAct;
import com.svv.jys.code.module.myself.apply.ApplyAct;
import com.svv.jys.code.module.myself.myadv.MyAdvAct;
import com.svv.jys.code.module.myself.myasset.otc.otctrading.buyorsell.BuyOrSellAct;
import com.svv.jys.code.module.myself.myasset.otc.otctrading.fabu.sell.OtcFabuSellAct;
import com.svv.jys.code.module.myself.myasset.otc.otctrading.fragment.userinfo.UserInfoAct;
import com.svv.jys.code.module.myself.myasset.otc.otctrading.order1.OtcOrderAct1;
import com.svv.jys.code.module.myself.usercenter.base.pay.PayMethedAct;
import com.svv.jys.code.module.net.exception.NeedLoginException;
import com.svv.jys.code.module.net.req.GET_OTC_ADV_REQ;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by js on 2018/8/1.
 */

public class FabiFrag extends MvpFragment<FabiView, FabiModel, FabiPresenter> implements FabiView, View.OnClickListener {
    ViewPager vp_otc_trade;
    private RelativeLayout tl_fabi_more, rl_fabi_order;
    private PopupWindow window, window1;
    private TextView notice_tv, order_type_tv;
    private TextView buy_fabi_tv, sell_fabi_tv;
    private List<String> types;
    private TabLayout tab_coin;
    public GET_OTC_ADV_REQ req = new GET_OTC_ADV_REQ();
    private List<View> OrderViews = new ArrayList<>();
    private PagerAdapter pagerAdapter;
    private int p_position;

    @Override
    public Context getMContext() {
        return getActivity();
    }

    @Override
    public FabiPresenter initPresenter() {
        return new FabiPresenter();
    }

    @Override
    public void onWakeBussiness() {
        if (vp_otc_trade != null) {
            presenter.getAdvSetting();
        }
        StatusBarUtils.setImmersiveStatusBar(getActivity(), true);
    }

    @Override
    public void onResume() {
        super.onResume();
        StatusBarUtils.setImmersiveStatusBar(getActivity(), true);
    }
    @Override
    public void baseInitialization() {

    }

    @Override
    public int setR_Layout() {
        return R.layout.frag_fabi1;
    }


    @Override
    public void viewInitialization() {
        tab_coin = (TabLayout) $(R.id.tab_coin);
        req.trade_type = "0";
        types = new ArrayList<>();
        types.add(getString(R.string.fabi_buy));
        types.add(getString(R.string.fabi_sell));
        order_type_tv = (TextView) $(R.id.order_type_tv);
        buy_fabi_tv = (TextView) $(R.id.buy_fabi_tv);
        sell_fabi_tv = (TextView) $(R.id.sell_fabi_tv);
        vp_otc_trade = (ViewPager) $(R.id.vp_otc_trade);
        tl_fabi_more = (RelativeLayout) $(R.id.tl_fabi_more);
        rl_fabi_order = (RelativeLayout) $(R.id.rl_fabi_order);
        rl_fabi_order.setOnClickListener(this);
        sell_fabi_tv.setOnClickListener(this);
        buy_fabi_tv.setOnClickListener(this);

        notice_tv = (TextView) $(R.id.notice_tv);
        tab_coin.setTabMode(TabLayout.MODE_SCROLLABLE);
        tab_coin.setupWithViewPager(vp_otc_trade);
        $(R.id.order_type_ll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ToolUtils.showDialog(getMContext(), new CustomSelectDialog.SelectDialogListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                        req.trade_type = String.valueOf(position);
//                        if (p_position != 0) {
//                            vp_otc_trade.setCurrentItem(0);
//                        } else {
//                            setChildReq(req);
//                        }
//                        order_type_tv.setText(types.get(position));
//                    }
//                }, types);
                showBuyOrSellPop(v);
            }
        });
        vp_otc_trade.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int index) {
                req.coin = presenter.settingEntity.getOtc_coin().get(index);
                View view = OrderViews.get(index);
                XRecyclerView xrv = view.findViewById(R.id.xrv_buy_sell);
                p_position = index;
                xrv.refresh();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        vp_otc_trade.setCurrentItem(0);
        $(R.id.screen_rl).setOnClickListener(this);
//        selectBuyAndSellView("0");
    }

    @Override
    public void doBusiness() {
        presenter.getAdvSetting();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_otc_buy:
                vp_otc_trade.setCurrentItem(0);
                break;
            case R.id.buy_fabi_tv:
                //购买
                selectBuyAndSellView("0");
                break;
            case R.id.sell_fabi_tv:
                //出售
                selectBuyAndSellView("1");
                break;
            case R.id.tv_otc_sell:
                vp_otc_trade.setCurrentItem(1);
                break;
            case R.id.tl_fabi_more:
                if (presenter.settingEntity.getUser() == null) {
                    showPop(view, false);
                    return;
                }
                String identity = presenter.settingEntity.getUser().getIs_shoper();
                if (TextUtils.isEmpty(identity)) {
                    showPop(view, false);
                } else {
                    showPop(view, identity.equals("1"));
                }
                break;
            case R.id.tv_pop_chushou:
                window.dismiss();
                Bundle bundle1 = new Bundle();
                bundle1.putString("type", "1");
                gotoActivity(OtcFabuSellAct.class, false, bundle1);
                break;

            case R.id.rl_fabi_order:
                try {
                    ToolUtils.checkLogin(getMContext(), true);
                    gotoActivity(OtcOrderAct1.class);
                } catch (NeedLoginException e) {
                    e.printStackTrace();
                }

                break;
            case R.id.screen_rl:
                if (ToolUtils.isFastClick(view.getId())) {
                    if (presenter.settingEntity != null)
                        ScreenAct.startByReqAndData(getMContext(), req, presenter.settingEntity);
                }

                break;
        }
    }

    private void selectBuyAndSellView(String buyOrSellType) {
        sell_fabi_tv.setSelected(false);
        sell_fabi_tv.setTextColor(ContextCompat.getColor(getMContext(), R.color.c_8f9ba2));

        buy_fabi_tv.setTextColor(ContextCompat.getColor(getMContext(), R.color.c_8f9ba2));
        buy_fabi_tv.setSelected(false);
        switch (buyOrSellType) {
            //0是购买 1是出售
            case "0":
                buy_fabi_tv.setTextColor(ContextCompat.getColor(getMContext(), R.color.c_1ede89));
                req.trade_type = "0";
                setChildReq(req);
                break;
            case "1":
                sell_fabi_tv.setTextColor(ContextCompat.getColor(getMContext(), R.color.c_1ede89));
                req.trade_type = "1";
                setChildReq(req);
                break;
            default:
                break;
        }
    }


    @Override
    public void isPublish(String s) {
        if (s.equals("true")) {
            tl_fabi_more.setVisibility(View.VISIBLE);
        } else {
            tl_fabi_more.setVisibility(View.GONE);
        }
    }

    @Override
    public void setData(AdvSettingEntity entity) {
        List<String> otcCoinEntities = entity.getOtc_coin();
        tl_fabi_more.setOnClickListener(this);
        if (otcCoinEntities == null || otcCoinEntities.size() == 0) {
            return;
        }
        if (TextUtils.isEmpty(req.coin)) {
            req.coin = otcCoinEntities.get(0);
            p_position = 0;
        }
        setNoticeNum(entity.getNotice());
        if (OrderViews.size() == 0) {
            for (int i = 0; i < otcCoinEntities.size(); i++) {
                View view = getLayoutInflater().inflate(R.layout.view_fabi_item, null);
                final XRecyclerView xrv_buy_sell = (XRecyclerView) view.findViewById(R.id.xrv_buy_sell);
                xrv_buy_sell.setLayoutManager(new LinearLayoutManager(getActivity()));
                xrv_buy_sell.setLoadingListener(new XRecyclerView.LoadingListener() {
                    @Override
                    public void onRefresh() {
                        presenter.getOtcAdv(0, p_position);
                    }

                    @Override
                    public void onLoadMore() {
                        presenter.getOtcAdv(xrv_buy_sell.getAdapter().getItemCount(), p_position);
                    }
                });
                OrderViews.add(view);
                if (i == 0) {
                    xrv_buy_sell.refresh();
                }

            }
            pagerAdapter = new PagerAdapter() {

                @Override
                public int getCount() {
                    return OrderViews.size();
                }

                @Override
                public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                    return view == object;
                }

                @NonNull
                @Override
                public Object instantiateItem(@NonNull ViewGroup container, int position) {
                    View view = OrderViews.get(position);
                    container.addView(view);
                    return view;
                }

                @Override
                public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                    container.removeView((View) object);
                }

                @Override
                public CharSequence getPageTitle(int position) {
                    return presenter.settingEntity.getOtc_coin().get(position).toUpperCase();
                }
            };
            vp_otc_trade.setAdapter(pagerAdapter);
        }

    }

    @Override
    public void setOtcAdv(List<OtcAdvEntity.RowsBean> rows, int position, boolean isLoadMore) {
        View view = OrderViews.get(position);
        XRecyclerView xrv = view.findViewById(R.id.xrv_buy_sell);
        BuyOrSellAdapter1 adapter = (BuyOrSellAdapter1) xrv.getAdapter();
        if (adapter == null) {
            if (rows.size() == 0) {
                view.findViewById(R.id.no_data_ly).setVisibility(View.VISIBLE);
                xrv.setVisibility(View.GONE);
            } else {
                view.findViewById(R.id.no_data_ly).setVisibility(View.GONE);
                xrv.setVisibility(View.VISIBLE);
            }
            xrv.refreshComplete();//关闭刷新
            adapter = new BuyOrSellAdapter1(getMContext(), rows);
            xrv.setAdapter(adapter);
            adapter.setOnClickLinster(new BuyOrSellAdapter1.OnClickLinsters() {
                @Override
                public void toBuy(OtcAdvEntity.RowsBean rowsBean) {
                    try {
                        ToolUtils.checkLogin(getMContext(), true);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("id", rowsBean.getId());
                        bundle.putString("coin", rowsBean.getCoin_name());
                        bundle.putString("trade_type", req.trade_type);
                        gotoActivity(BuyOrSellAct.class, false, bundle);
                    } catch (NeedLoginException e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void toQureyUser(OtcAdvEntity.RowsBean rowsBean) {
                    try {
                        ToolUtils.checkLogin(getMContext(), true);
                        Bundle bundle = new Bundle();
                        bundle.putString("uid", rowsBean.getUser_id());
                        gotoActivity(UserInfoAct.class, false, bundle);
                    } catch (NeedLoginException e) {
                        e.printStackTrace();
                    }

                }
            });
            return;
        }
        if (isLoadMore) {
            xrv.loadMoreComplete();//关闭刷新
            adapter.addList(rows);
        } else {
            if (rows.size() == 0) {
                view.findViewById(R.id.no_data_ly).setVisibility(View.VISIBLE);
                xrv.setVisibility(View.GONE);
            } else {
                view.findViewById(R.id.no_data_ly).setVisibility(View.GONE);
                xrv.setVisibility(View.VISIBLE);
            }
            xrv.refreshComplete();//关闭刷新
            adapter.setList(rows);
        }

    }

    public void showPop(View view, boolean isShop) {
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.popup_fabi_menu, (RelativeLayout) $(R.id.tl_fabi_more), false);
        if (window == null) {
            window = new PopupWindow(contentView, ResourceUtils.dipToPX(getMContext(), 145), ViewGroup.LayoutParams.WRAP_CONTENT, true);
            // 设置PopupWindow的背景
            window.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00ffffff")));
            window.setOnDismissListener(new PopupWindow.OnDismissListener() {

                @Override
                public void onDismiss() {
                    backgroundAlpha(1f);
                }
            });
            // 设置PopupWindow是否能响应外部点击事件
            window.setOutsideTouchable(true);
            // 设置PopupWindow是否能响应点击事件
            window.setTouchable(true);
        } else {
            contentView = window.getContentView();
        }
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        backgroundAlpha(0.8f);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.showAsDropDown(view, -ResourceUtils.dipToPX(getMContext(), 108), 0);
        }
        LinearLayout tv_pop_fabu = contentView.findViewById(R.id.tv_pop_fabu);
        LinearLayout tv_pop_guanli = contentView.findViewById(R.id.tv_pop_guanli);
        LinearLayout tv_pop_shanj = contentView.findViewById(R.id.tv_pop_shanj);
        LinearLayout tv_pop_shouk = contentView.findViewById(R.id.tv_pop_shouk);
        View tv_order_m = contentView.findViewById(R.id.tv_order_m);
        try {
            ToolUtils.checkLogin(getMContext(), false);
            tv_order_m.setVisibility(View.VISIBLE);
            if (isShop) {

                tv_pop_fabu.setVisibility(View.VISIBLE);
                tv_pop_guanli.setVisibility(View.VISIBLE);
                tv_pop_shanj.setVisibility(View.GONE);

            } else {
                if (presenter.settingEntity.getOtc_setting().getBusiness().equals("1")) {
                    tv_pop_fabu.setVisibility(View.GONE);
                    tv_pop_guanli.setVisibility(View.GONE);
                } else {
                    tv_pop_fabu.setVisibility(View.VISIBLE);
                    tv_pop_guanli.setVisibility(View.VISIBLE);
                }
                tv_pop_shanj.setVisibility(View.VISIBLE);
            }
        } catch (NeedLoginException e) {
            e.printStackTrace();
            tv_pop_fabu.setVisibility(View.GONE);
            tv_pop_guanli.setVisibility(View.GONE);
            tv_pop_shanj.setVisibility(View.GONE);
            tv_order_m.setVisibility(View.GONE);
            return;
        }

        tv_pop_fabu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ToolUtils.isFastClick(view.getId())) {
                    window.dismiss();
                    Bundle bundle = new Bundle();
                    bundle.putString("type", "0");
                    gotoActivity(OtcFabuSellAct.class, false, bundle);
                }

            }
        });
        tv_pop_shouk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ToolUtils.isFastClick(view.getId())) {
                    window.dismiss();
                    gotoActivity(PayMethedAct.class);
                }
            }
        });
        tv_pop_guanli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ToolUtils.isFastClick(view.getId())) {
                    window.dismiss();
                    gotoActivity(MyAdvAct.class);
                }
            }
        });
        tv_pop_shanj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ToolUtils.isFastClick(view.getId())) {
                    window.dismiss();
                    gotoActivity(ApplyAct.class);
                }
            }
        });
        tv_order_m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ToolUtils.isFastClick(v.getId())) {
                    window.dismiss();
                    gotoActivity(OtcOrderAct1.class);
                }
            }
        });

    }

    public void showBuyOrSellPop(View view) {
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.popup_fabi_menu1, (RelativeLayout) $(R.id.tl_fabi_more), false);
        if (window1 == null) {
            window1 = new PopupWindow(contentView, ResourceUtils.dipToPX(getActivity(), 135), ViewGroup.LayoutParams.WRAP_CONTENT, true);
            // 设置PopupWindow的背景
            window1.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00ffffff")));
            window1.setOnDismissListener(new PopupWindow.OnDismissListener() {

                @Override
                public void onDismiss() {
                    backgroundAlpha(1f);
                }
            });
            // 设置PopupWindow是否能响应外部点击事件
            window1.setOutsideTouchable(true);
            // 设置PopupWindow是否能响应点击事件
            window1.setTouchable(true);
        } else {
            contentView = window1.getContentView();
        }
        TextView tv_fabi_buy = contentView.findViewById(R.id.tv_fabi_buy);
        TextView tv_fabi_sell = contentView.findViewById(R.id.tv_fabi_sell);
        tv_fabi_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                req.trade_type = "0";
                setChildReq(req);
                order_type_tv.setText(types.get(0));
                window1.dismiss();
            }
        });
        tv_fabi_sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                req.trade_type = "1";
                setChildReq(req);
                order_type_tv.setText(types.get(1));
                window1.dismiss();
            }
        });
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        backgroundAlpha(0.8f);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window1.showAsDropDown(view, ResourceUtils.dipToPX(getActivity(), 15), 0, Gravity.LEFT);
        }
    }

    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = bgAlpha; // 0.0-1.0
        getActivity().getWindow().setAttributes(lp);
    }


    public void setNoticeNum(String num) {
        if (Integer.valueOf(num) <= 0) {
            notice_tv.setVisibility(View.GONE);
        } else {
            notice_tv.setVisibility(View.VISIBLE);
            if (Integer.valueOf(num) >= 99)
                notice_tv.setText("99");
            else
                notice_tv.setText(num);
        }
    }

    public void setChildReq(GET_OTC_ADV_REQ req) {
        this.req = req;
        View view = OrderViews.get(p_position);
        XRecyclerView xrv = view.findViewById(R.id.xrv_buy_sell);
        xrv.refresh();
    }


}
