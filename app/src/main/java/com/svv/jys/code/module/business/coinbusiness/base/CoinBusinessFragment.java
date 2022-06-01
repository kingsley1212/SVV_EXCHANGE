package com.svv.jys.code.module.business.coinbusiness.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pedaily.yc.ycdialoglib.selectDialog.CustomSelectDialog;
import com.svv.jys.R;
import com.svv.jys.code.common.base.mvp.MvpFragment;
import com.svv.jys.code.common.constant.ACEConstant;
import com.svv.jys.code.common.db.nativecache.ACache;
import com.svv.jys.code.common.entity.CoinBusinessEntity;
import com.svv.jys.code.common.entity.EntrustEntity;
import com.svv.jys.code.common.entity.FUserInfoEntity;
import com.svv.jys.code.common.entity.HomeAssatEntity;
import com.svv.jys.code.common.entity.MarketListEntity;
import com.svv.jys.code.common.entity.TradeListEntity;
import com.svv.jys.code.common.utils.StatusBarUtils;
import com.svv.jys.code.common.utils.T;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.common.view.popup.PopupBusinessSubmitView;
import com.svv.jys.code.common.view.popup.PopupDialogView;
import com.svv.jys.code.module.business.coinbusiness.base.adapter.CoinDelegateAdapter;
import com.svv.jys.code.module.business.coinbusiness.base.adapter.Pankou2Adapter;
import com.svv.jys.code.module.business.coinbusiness.base.adapter.PankouAdapter;
import com.svv.jys.code.module.business.coinbusiness.base.model.ICoinBusinessModel;
import com.svv.jys.code.module.business.coinbusiness.base.presenter.CoinBusinessPresenter;
import com.svv.jys.code.module.business.coinbusiness.base.view.ICoinBusinessView;
import com.svv.jys.code.module.main.MainAct;
import com.svv.jys.code.module.market.slidemenu.SlideMenuAct;
import com.svv.jys.code.module.myself.ordermanage.base.OrderManagerAct;
import com.svv.jys.code.module.net.exception.NeedLoginException;
import com.svv.jys.code.module.net.req.POST_COINBUSINESS_SUBMIT_REQ;
import com.svv.jys.code.module.server.dataserver.event.MarketListEvent;
import com.svv.jys.code.module.server.dataserver.event.TradeListEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import static com.svv.jys.code.module.business.coinbusiness.base.presenter.CoinBusinessPresenter.P_LIMIT_PRICE;
import static com.svv.jys.code.module.business.coinbusiness.base.presenter.CoinBusinessPresenter.P_MARKET_PRICE;

/**
 * Created by Administrator on 2018/4/27 0027.
 * V 1.1.1  Update by Lzj  on 2018/8/22 0930.
 */
public class CoinBusinessFragment extends MvpFragment<ICoinBusinessView, ICoinBusinessModel, CoinBusinessPresenter>
        implements ICoinBusinessView {
    private View no_data_ly;
    private LinearLayout ll_coin;
    private ImageView coinbusiness_save, coinbusiness_market;
    private TextView marketnp_tv,
            tv_market_name, fprice_tv;
    private RecyclerView rvCoinType1List, rvCoinType2List;
    private PankouAdapter pankouAdapter;//盘口数据适配器 上
    private Pankou2Adapter pankou2Adapter;//盘口数据适配器 下
    private CoinDelegateAdapter coinDelegateAdapter;//展示委托数据adapter
    private RecyclerView rvCoinDelegate;//展示委托数据
    private View xj_ll, buy_add, showtradetype_ll, buy_cut, sj_ll, sj_view, xj_view,
            buy_price_ll, sell_price_ll, buy_sj_tv, sell_sj_tv, sell_add, sell_cut,jiaoyi_ll;
    private TextView xj_tv, buy_coin_num, sell_coin_num, sj_tv, buy_jye_tv, sell_jye_tv, bibi_coin_name_tv;
    private EditText buy_price_et, buy_num_et, sell_price_et, sell_num_et;
    private TextView sell_rmb_tv, buy_rmb_tv, tv_market_num;
    private TextView buy_btn, sell_btn, Transaction_amount_tv;
    /**
     * 买入卖出按钮
     */
    private TextView select_buy_type_tv, select_sell_type_tv;
    private TextView type_sell_buy_tv;
    private TextView number_buy_sell_tv;
    private PopupBusinessSubmitView businessSubmitView;
    private CoinBusinessEntity businessEntity;
    private Boolean isDestory = false;
    private NestedScrollView nsl;
    public Handler.Callback tradeHandler = new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    if (!isHidden()) {
                        isDestory = true;
                    } else {
                        isDestory = false;
                    }
                    if (isDestory) {
                        if (presenter.user_id != null) {
                            ((MainAct) getMContext()).mDataService.subscribeEntrust(presenter.user_id, presenter.area);
                        } else {
                            setEntrustData(null);
                        }
                    }
                    handler.sendEmptyMessageDelayed(1, 5000);//这里想几秒刷新一次就写几秒
                    break;
            }
            return false;
        }
    };
    private Handler handler = new Handler(tradeHandler);
    private MarketListEntity entity;
    private FUserInfoEntity fUserInfoEntity;

    public CoinBusinessFragment() {

    }

    public static CoinBusinessFragment newInstance(MarketListEntity entity) {
        Bundle args = new Bundle();
        args.putSerializable("entity", entity);
        CoinBusinessFragment fragment = new CoinBusinessFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Context getMContext() {
        return getContext();
    }


    @Override
    public CoinBusinessPresenter initPresenter() {
        return new CoinBusinessPresenter();
    }

//    @Override
//    public void onHiddenChanged(boolean hidden) {
//        super.onHiddenChanged(hidden);
//        if (hidden) {
//
//        }
//    }

    @Override
    public void onWakeBussiness() {
        fUserInfoEntity = ToolUtils.getUserInfo(getMContext());
        try {
            ToolUtils.checkLogin(getMContext(), false);
          /*  setCollectMarket(((CollectEntity) ACache.get(getActivity()).getAsObject(ACEConstant
                    .ACE_USERINFO_COLLECTMARKET)).getCollect_market());*/
            if (presenter.isSellView()) {
                buy_btn.setText(getString(R.string.tradechat_sell));
            } else {
                buy_btn.setText(getString(R.string.tradechat_buy));
            }

        } catch (NeedLoginException e) {
            coinbusiness_save.setSelected(false);
            buy_btn.setText(getString(R.string.coinbusinessfragment_notlogin));
            sell_btn.setText(getString(R.string.coinbusinessfragment_notlogin));
            e.printStackTrace();
        }
        if (!TextUtils.isEmpty(presenter.area)) {
            if (!TextUtils.isEmpty(ACache.get(getMContext()).getAsString("coin_aera"))) {
                ((MainAct) getMContext()).mDataService.leaveDept(ACache.get(getMContext()).getAsString("coin_aera"));
            }
            ((MainAct) getMContext()).mDataService.subscribeDept(presenter.area);
        }
        presenter.user_id = ACache.get(getMContext()).getAsString(ACEConstant.ACE_USERINFO_USERID);//用户ID
        presenter.getCoinBusinessAssetData();
        if (presenter.isSellView()) {

            getPrice(presenter.sell_price, buy_rmb_tv);
        } else {
            getPrice(presenter.buy_price, buy_rmb_tv);
        }
        if (presenter.entity != null && presenter.entity.getMarket_info() != null) {
            getPrice(presenter.entity.getMarket_info().getNew_price(), fprice_tv);
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
        EventBus.getDefault().register(this);
        fUserInfoEntity = ToolUtils.getUserInfo(getMContext());
        entity = (MarketListEntity) getArguments().getSerializable("entity");
        try {
            ToolUtils.checkLogin(getMContext(), false);
        } catch (NeedLoginException e) {
            e.printStackTrace();
        }

    }


    @Override
    public int setR_Layout() {
        return R.layout.fragment_coinbusiness;
    }


    @Override
    public void viewInitialization() {
        ll_coin = (LinearLayout) $(R.id.ll_coin);
        nsl = (NestedScrollView) $(R.id.nsl);
        no_data_ly = $(R.id.no_data_ly);
        presenter.user_id = ACache.get(getMContext()).getAsString(ACEConstant.ACE_USERINFO_USERID);//用户ID
        sell_add = $(R.id.sell_add);
        sell_cut = $(R.id.sell_cut);
        jiaoyi_ll = $(R.id.jiaoyi_ll);
        select_buy_type_tv = (TextView) $(R.id.select_buy_type_tv);
        Transaction_amount_tv = (TextView) $(R.id.Transaction_amount_tv);
        select_sell_type_tv = (TextView) $(R.id.select_sell_type_tv);
        tv_market_num = (TextView) $(R.id.tv_market_num);
        bibi_coin_name_tv = (TextView) $(R.id.bibi_coin_name_tv);
        tv_market_name = (TextView) $(R.id.tv_market_name);

        number_buy_sell_tv = (TextView) $(R.id.number_buy_sell_tv);
        type_sell_buy_tv = (TextView) $(R.id.type_sell_buy_tv);
        $(R.id.select_market_ll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ToolUtils.isFastClick(view.getId())) {
                    startActivityForResult(new Intent(getActivity(), SlideMenuAct.class), SlideMenuAct.ACT_REQCODE);
                    getActivity().overridePendingTransition(R.anim.anim_left_in, R.anim.anim_no);
                }

            }
        });
        coinDelegateAdapter = new CoinDelegateAdapter(getMContext());

        coinDelegateAdapter.setDoTradeDuelInterface(new CoinDelegateAdapter.DoTradeDuelInterface() {
            @Override
            public void doTradeRevoke(final String id, final int position) {
                PopupDialogView view = new PopupDialogView(getMContext(), new PopupDialogView.MClickLisnener() {
                    @Override
                    public void leftBtn() {

                    }

                    @Override
                    public void rightBtn() {
                        presenter.doTradeRevoke(id);
                    }
                });
                view.showPop(rvCoinDelegate, getString(R.string.EntrustFrag_tip), getString(R.string
                        .EntrustFrag_are_you_sure), getString(R.string.myselffragment_left_text), getString(R
                        .string.myselffragment_right_text));

            }
        });
        //右上角  收藏 行情
        coinbusiness_save = (ImageView) $(R.id.coinbusiness_save);
        coinbusiness_market = (ImageView) $(R.id.coinbusiness_market);

        coinbusiness_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ToolUtils.isFastClick(v.getId())) {
                    try {
                        ToolUtils.checkLogin(getMContext(), true);
                        presenter.collectMarket();
                    } catch (NeedLoginException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        coinbusiness_market.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ToolUtils.isFastClick(v.getId())) {
//                    TradeChat2Act.startAct(getMContext(), presenter.entity.getMarket_info());
                    ToolUtils.startKLineAct(getMContext(), presenter.entity.getMarket_info());
                }

            }
        });

      /*  //买入卖出
        coinbusiness_buyorsell = (TextView) $(R.id.coinbusiness_buyorsell);
       */

        marketnp_tv = (TextView) $(R.id.marketnp_tv);
        //盘口列表数据
        rvCoinType1List = (RecyclerView) $(R.id.rvCoinType1List);//卖
        rvCoinType2List = (RecyclerView) $(R.id.rvCoinType2List);//买
        //这个是自带的倒序排序，从下面开始往上的列表
        LinearLayoutManager layout = new LinearLayoutManager(getActivity());
        layout.setStackFromEnd(true);//列表再底部开始展示，反转后由上面开始展示
        layout.setReverseLayout(true);//列表翻转
        rvCoinType1List.setLayoutManager(layout);
//        rvCoinType1List.setLayoutManager(new LinearLayoutManager(getMContext()));

        rvCoinType2List.setLayoutManager(new LinearLayoutManager(getMContext()));

        //当前委托
        rvCoinDelegate = (RecyclerView) $(R.id.rvCoinDelegate);
        rvCoinDelegate.setLayoutManager(new LinearLayoutManager(getMContext()));
        rvCoinDelegate.setNestedScrollingEnabled(false);
        rvCoinDelegate.setAdapter(coinDelegateAdapter);


        fprice_tv = (TextView) $(R.id.fprice_tv);
        showtradetype_ll = $(R.id.showtradetype_ll);
        showtradetype_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ToolUtils.isFastClick(view.getId())) {
                    gotoActivity(OrderManagerAct.class);
                }

            }
        });


        xj_ll = $(R.id.xj_ll);
        sj_view = $(R.id.sj_view);
        sj_ll = $(R.id.sj_ll);
        xj_view = $(R.id.xj_view);
        xj_ll = $(R.id.xj_ll);
        sj_tv = (TextView) $(R.id.sj_tv);
        xj_tv = (TextView) $(R.id.xj_tv);
        buy_price_et = (EditText) $(R.id.buy_price_et);
        buy_coin_num = (TextView) $(R.id.buy_coin_num);
        sell_coin_num = (TextView) $(R.id.sell_coin_num);
        buy_jye_tv = (TextView) $(R.id.buy_jye_tv);
        buy_rmb_tv = (TextView) $(R.id.buy_rmb_tv);
        sell_rmb_tv = (TextView) $(R.id.sell_rmb_tv);
        sell_price_et = (EditText) $(R.id.sell_price_et);
        sell_jye_tv = (TextView) $(R.id.sell_jye_tv);
        sell_num_et = (EditText) $(R.id.sell_num_et);
        buy_num_et = (EditText) $(R.id.buy_num_et);
        buy_add = $(R.id.buy_add);
        buy_cut = $(R.id.buy_cut);
        buy_btn = (TextView) $(R.id.buy_btn);
        sell_btn = (TextView) $(R.id.sell_btn);
        buy_btn.setSelected(false);
        sell_btn.setSelected(true);
        buy_price_ll = $(R.id.buy_price_ll);
        sell_price_ll = $(R.id.sell_price_ll);
        buy_sj_tv = $(R.id.buy_sj_tv);
        sell_sj_tv = $(R.id.sell_sj_tv);
        ChangeExchangeWays(0);
        xj_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                showCustomDialog();
                ChangeExchangeWays(0);
            }
        });
        sj_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeExchangeWays(1);
            }
        });
        buy_price_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String temp = s.toString();
                int posDot = temp.indexOf(".");
                if (posDot > 0) {
                    if (temp.length() - posDot - 1 > 8) {
                        s.delete(posDot + 9, s.length());
                    }
                }
//                if (presenter.isSellView()) {
//                    presenter.sell_price = s.toString();
//                } else {
                presenter.buy_price = s.toString();
//                }
                getPrice(presenter.buy_price, buy_rmb_tv);
//                setUserData(false);
                try {
                    buy_jye_tv.setText(ToolUtils.SzzcFormat(ToolUtils.multiply(buy_price_et
                            .getText().toString(), buy_num_et.getText().toString())));
                } catch (Exception e) {
                    buy_jye_tv.setText("0");
                }
            }
        });

        sell_price_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String temp = s.toString();
                int posDot = temp.indexOf(".");
                if (posDot > 0) {
                    if (temp.length() - posDot - 1 > 8) {
                        s.delete(posDot + 9, s.length());
                    }
                }
                presenter.sell_price = s.toString();
                getPrice(presenter.sell_price, sell_rmb_tv);
//                setUserData(false);
                try {
                    sell_jye_tv.setText(ToolUtils.SzzcFormat(ToolUtils.multiply(sell_price_et
                            .getText().toString(), sell_num_et.getText().toString())));
                } catch (Exception e) {
                    sell_jye_tv.setText("0");
                }
            }
        });
        buy_num_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String temp = editable.toString();
                int posDot = temp.indexOf(".");
                if (posDot > 0) {
                    if (temp.length() - posDot - 1 > 8) {
                        editable.delete(posDot + 9, editable.length());
                    }
                }
                try {
                    if (presenter.nowPriceType == CoinBusinessPresenter.P_LIMIT_PRICE) {
                        buy_jye_tv.setText(ToolUtils.SzzcFormat(ToolUtils.multiply(buy_price_et
                                .getText().toString(), buy_num_et.getText().toString())));
                    } else {
                        buy_jye_tv.setText(buy_num_et.getText().toString());
                    }

                } catch (Exception e) {
                    buy_jye_tv.setText("0");
                }
            }
        });
        sell_num_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String temp = editable.toString();
                int posDot = temp.indexOf(".");
                if (posDot > 0) {
                    if (temp.length() - posDot - 1 > 8) {
                        editable.delete(posDot + 9, editable.length());
                    }
                }
                try {
                    if (presenter.nowPriceType == CoinBusinessPresenter.P_LIMIT_PRICE) {
                        sell_jye_tv.setText(ToolUtils.SzzcFormat(ToolUtils.multiply(sell_price_et
                                .getText().toString(), sell_num_et.getText().toString())));
                    } else {
                        sell_jye_tv.setText(sell_num_et.getText().toString());
                    }
                } catch (Exception e) {
                    sell_jye_tv.setText("0");
                }
            }
        });
        buy_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = buy_price_et.getText().toString();
                String result;
                if (TextUtils.isEmpty(value)) {
                    result = "1";
                } else {
                    result = ToolUtils.plusOneAtLast(value, value.length() - 1);
                }
                buy_price_et.setText(result);
                buy_price_et.setSelection(buy_price_et.getText().toString().length());
            }
        });
        buy_cut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = buy_price_et.getText().toString();
                String result;
                if (TextUtils.isEmpty(value)) {
                    result = "0";
                } else {
                    result = ToolUtils.subtractOneAtLast(value, value.length() - 1);
                }

                buy_price_et.setText(String.valueOf(Double.parseDouble(result)));
                buy_price_et.setSelection(buy_price_et.getText().toString().length());
            }
        });

        sell_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = sell_price_et.getText().toString();
                String result;
                if (TextUtils.isEmpty(value)) {
                    result = "1";
                } else {
                    result = ToolUtils.plusOneAtLast(value, value.length() - 1);
                }
                sell_price_et.setText(result);
                sell_price_et.setSelection(sell_price_et.getText().toString().length());
            }
        });
        sell_cut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = sell_price_et.getText().toString();
                String result;
                if (TextUtils.isEmpty(value)) {
                    result = "0";
                } else {
                    result = ToolUtils.subtractOneAtLast(value, value.length() - 1);
                }

                sell_price_et.setText(String.valueOf(Double.parseDouble(result)));
                sell_price_et.setSelection(sell_price_et.getText().toString().length());
            }
        });
        buy_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ToolUtils.isFastClick(v.getId())) {
                    try {
                        ToolUtils.checkLogin(getMContext(), true);
                    } catch (NeedLoginException e) {
                        return;
                    }

                    if (businessSubmitView == null) {
                        businessSubmitView = new PopupBusinessSubmitView(getMContext(), new PopupBusinessSubmitView
                                .SubmitLisnener() {
                            @Override
                            public void doCancle() {

                            }

                            @Override
                            public void doSubmit(String price, String num, String pw, int type) {
                                presenter.Submit(num, price, pw, type, true);
                            }
                        });
                    }


                    String num = buy_num_et.getText().toString().trim();
                    if (ToolUtils.isNull(num)) {
                        T.showShort(getMContext(), getString(R.string.business_inputnum));
                        return;
                    }
                    int type = POST_COINBUSINESS_SUBMIT_REQ.P_BUY;
                    if (presenter.isSellView()) {
                        type = POST_COINBUSINESS_SUBMIT_REQ.P_SELL;
                    } else {
                        type = POST_COINBUSINESS_SUBMIT_REQ.P_BUY;
                    }
                    if (fUserInfoEntity != null) {
                        if (fUserInfoEntity.getPwd_type().equals("0")) {
                            switch (presenter.nowPriceType) {
                                case P_LIMIT_PRICE:
                                    businessSubmitView.showPop(v, type, buy_price_et.getText().toString(), num,
                                            presenter.entity.getMarket_info().getBuy_name(), presenter.entity.getMarket_info().getSell_name());
                                    break;
                                case P_MARKET_PRICE:
                                    businessSubmitView.showPop(v, type, getString(R.string.business_zuiyoujia2), num);
                                    break;
                            }
                        } else if (fUserInfoEntity.getPwd_type().equals("1") && !fUserInfoEntity.isCheck_pwd()) {
                            switch (presenter.nowPriceType) {
                                case P_LIMIT_PRICE:
                                    businessSubmitView.showPop(v, type, buy_price_et.getText().toString(), num,
                                            presenter.entity.getMarket_info().getBuy_name(), presenter.entity.getMarket_info().getSell_name());
                                    break;
                                case P_MARKET_PRICE:
                                    businessSubmitView.showPop(v, type, getString(R.string.business_zuiyoujia2), num);
                                    break;
                            }
                        } else {
                            presenter.Submit(num, buy_price_et.getText().toString(), "", type, false);
                        }

                    }
                }


            }

        });

        sell_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ToolUtils.isFastClick(v.getId())) {
                    try {
                        ToolUtils.checkLogin(getMContext(), true);
                    } catch (NeedLoginException e) {
                        return;
                    }

                    if (businessSubmitView == null) {
                        businessSubmitView = new PopupBusinessSubmitView(getMContext(), new PopupBusinessSubmitView
                                .SubmitLisnener() {
                            @Override
                            public void doCancle() {

                            }

                            @Override
                            public void doSubmit(String price, String num, String pw, int type) {
                                presenter.Submit(num, price, pw, type, true);
                            }
                        });
                    }


                    String num = sell_num_et.getText().toString().trim();
                    if (ToolUtils.isNull(num)) {
                        T.showShort(getMContext(), getString(R.string.business_inputnum));
                        return;
                    }
                    if (fUserInfoEntity.getPwd_type().equals("0")) {
                        switch (presenter.nowPriceType) {
                            case P_LIMIT_PRICE:
                                businessSubmitView.showPop(v, POST_COINBUSINESS_SUBMIT_REQ.P_SELL, buy_price_et.getText().toString(), num,
                                        presenter.entity.getMarket_info().getBuy_name(), presenter.entity.getMarket_info().getSell_name());
                                break;
                            case P_MARKET_PRICE:
                                businessSubmitView.showPop(v, POST_COINBUSINESS_SUBMIT_REQ.P_SELL, buy_price_et.getText().toString(), num);
                                break;
                        }
                    } else if (fUserInfoEntity.getPwd_type().equals("1") && !fUserInfoEntity.isCheck_pwd()) {
                        switch (presenter.nowPriceType) {
                            case P_LIMIT_PRICE:
                                businessSubmitView.showPop(v, POST_COINBUSINESS_SUBMIT_REQ.P_SELL, buy_price_et.getText().toString(), num,
                                        presenter.entity.getMarket_info().getBuy_name(), presenter.entity.getMarket_info().getSell_name());
                                break;
                            case P_MARKET_PRICE:
                                businessSubmitView.showPop(v, POST_COINBUSINESS_SUBMIT_REQ.P_SELL, buy_price_et.getText().toString(), num);
                                break;
                        }
                    } else {
                        presenter.Submit(num, buy_price_et.getText().toString(), "", POST_COINBUSINESS_SUBMIT_REQ.P_SELL, false);
                    }
                }


            }

        });
        // 买入
        select_buy_type_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BuyOrSellPageInformation("0");
            }
        });
        // 卖出
        select_sell_type_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BuyOrSellPageInformation("1");
            }
        });
        //默认买入
        BuyOrSellPageInformation("0");
    }

    /**
     * 页面信息
     *
     * @param sellOrBuyType
     */
    private void BuyOrSellPageInformation(String sellOrBuyType) {
        select_buy_type_tv.setSelected(false);
        select_buy_type_tv.setTextColor(ContextCompat.getColor(getMContext(), R.color.c_9fb8cf));
        select_sell_type_tv.setSelected(false);
        select_sell_type_tv.setTextColor(ContextCompat.getColor(getMContext(), R.color.c_9fb8cf));
        presenter.sellOrBuyType = sellOrBuyType;

        switch (sellOrBuyType) {
            case "0":
                //买入
                select_buy_type_tv.setSelected(true);
                select_buy_type_tv.setTextColor(ContextCompat.getColor(getMContext(), R.color.c_ffffff));
                buy_btn.setSelected(false);
                type_sell_buy_tv.setText(getString(R.string.tradechat_buy_jia));
                number_buy_sell_tv.setText(getString(R.string.tradechat_buy_number));
                buy_btn.setText(getString(R.string.tradechat_buy));
                break;
            case "1":
                //卖出
                select_sell_type_tv.setSelected(true);
                select_sell_type_tv.setTextColor(ContextCompat.getColor(getMContext(), R.color.c_ffffff));
                buy_btn.setSelected(true);
                type_sell_buy_tv.setText(getString(R.string.tradechat_sell_jia));
                number_buy_sell_tv.setText(getString(R.string.tradechat_sell_number));
                buy_btn.setText(getString(R.string.tradechat_sell));
                break;
            default:
                break;
        }
        try {
            ToolUtils.checkLogin(getMContext(),false);
        } catch (NeedLoginException e) {
            buy_btn.setText(getString(R.string.coinbusinessfragment_notlogin));
            e.printStackTrace();
        }
        setUserData(true);
    }


    @Override
    public void doBusiness() {

        setTrade(null);//默认显示没有委托

        //定时器触发
        handler.sendEmptyMessage(1);

        if (entity != null)
            presenter.getMarketData(null, entity.getName());
        else
            presenter.getMarketData(null, null);

    }


 /*   @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void doMarketListEvent(MarketListEvent marketListEvent) {
        if (marketListEvent != null && !isHidden) {
            presenter.doHandleMarket(marketListEvent.getMarketListEntities());
        }
    }*/


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void doTradeListEvent(TradeListEvent tradeListEvent) {
        if (tradeListEvent != null && !isHidden) {
            setTrade(tradeListEvent.tradeListEntity);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getEntrust(EntrustEntity event) {
        if (event != null && !isHidden) {
            sell_coin_num.setText(String.format("%s %s", event.getUser_coin().get(presenter.entity.getMarket_info().getBuy_name()), presenter.entity.getMarket_info().getBuy_name().toUpperCase()));
            //判断是买还是卖
            if (presenter.isSellView()) {
                buy_coin_num.setText(String.format("%s %s", event.getUser_coin().get(presenter.entity.getMarket_info().getBuy_name()), presenter.entity.getMarket_info().getBuy_name().toUpperCase()));
                bibi_coin_name_tv.setText(presenter.entity.getMarket_info().getBuy_name().toUpperCase());
            } else {
                buy_coin_num.setText(String.format("%s %s", event.getUser_coin().get(presenter.entity.getMarket_info().getSell_name()), presenter.entity.getMarket_info().getSell_name().toUpperCase()));
                bibi_coin_name_tv.setText(presenter.entity.getMarket_info().getSell_name().toUpperCase());
                //限价
                if (presenter.nowPriceType == P_LIMIT_PRICE) {
                    bibi_coin_name_tv.setText(presenter.entity.getMarket_info().getBuy_name().toUpperCase());
                } else {
                    bibi_coin_name_tv.setText(presenter.entity.getMarket_info().getSell_name().toUpperCase());
                }
            }
            setEntrustData(event.getRows());
        }
    }

    public void setEntrustData(List<EntrustEntity.RowsBean> list) {
        if (!ToolUtils.isListNull(list)) {
            no_data_ly.setVisibility(View.GONE);
            rvCoinDelegate.setVisibility(View.VISIBLE);
            coinDelegateAdapter.setData(list);
            coinDelegateAdapter.notifyDataSetChanged();
        } else {
            no_data_ly.setVisibility(View.VISIBLE);
            rvCoinDelegate.setVisibility(View.GONE);
        }
    }


    @Override
    public void setMarket() {
        marketnp_tv.setText(ToolUtils.doublePoint8(ToolUtils.String2Double(presenter.getSelect_ml().getNew_price())));
        fprice_tv.setText(getMContext().getString(R.string.fbprice_data, ToolUtils.CnyFormat(presenter.getSelect_ml().getCny_price()) + ToolUtils.getCurrency(getMContext())));
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void doMarketListEvent(MarketListEvent marketListEvent) {
        if (marketListEvent != null && !isHidden) {
            presenter.doHandleMarket(marketListEvent.getMarketListEntities());
        }
    }

    /**
     * 显示盘口信息
     *
     * @param entity
     */
    @Override
    public void setTrade(final TradeListEntity entity) {
        if (entity != null) {
            if (pankouAdapter == null) {//出售深度数据
                pankouAdapter = new PankouAdapter(entity.getSell(), getMContext());
                rvCoinType1List.setAdapter(pankouAdapter);
                pankouAdapter.setOnItemClick(new PankouAdapter.MOnItemClickListener() {
                    @Override
                    public void onItemClick(String num, String price) {
                        buy_num_et.setText(num);
                        buy_price_et.setText(price);

                    }
                });
            } else {
                pankouAdapter.setList(entity.getSell());
                pankouAdapter.notifyDataSetChanged();
            }

            if (pankou2Adapter == null) {//购买深度数据
                pankou2Adapter = new Pankou2Adapter(entity.getBuy(), getMContext());
                rvCoinType2List.setAdapter(pankou2Adapter);
                pankou2Adapter.setOnItemClick(new Pankou2Adapter.MOnItemClickListener() {
                    @Override
                    public void onItemClick(String num, String price) {
//                        sell_num_et.setText(num);
//                        sell_price_et.setText(price);
                        buy_num_et.setText(num);
                        buy_price_et.setText(price);
                    }
                });
            } else {
                pankou2Adapter.setList(entity.getBuy());
                pankou2Adapter.notifyDataSetChanged();
            }
        } else {
            pankouAdapter = new PankouAdapter(new ArrayList<TradeListEntity.Trade>(), getMContext());
            rvCoinType1List.setAdapter(pankouAdapter);
            pankouAdapter.setOnItemClick(new PankouAdapter.MOnItemClickListener() {
                @Override
                public void onItemClick(String num, String price) {
                    buy_num_et.setText(num);
                    buy_price_et.setText(price);
                }
            });

            pankou2Adapter = new Pankou2Adapter(new ArrayList<TradeListEntity.Trade>(), getMContext());
            rvCoinType2List.setAdapter(pankou2Adapter);
            pankou2Adapter.setOnItemClick(new Pankou2Adapter.MOnItemClickListener() {
                @Override
                public void onItemClick(String num, String price) {
//                    sell_num_et.setText(num);
//                    sell_price_et.setText(price);
                    buy_num_et.setText(num);
                    buy_price_et.setText(price);
                }
            });
        }
    }

    @Override
    public void removeSuccese() {

    }

    @Override
    public void setCollectMarket(List<String> list) {

    }


    @Override
    public void setBBData(CoinBusinessEntity entity) {
        businessEntity = entity;
        tv_market_name.setText(entity.getMarket_info().getName().toUpperCase().replace("_", "/"));
//        coin_util_1.setText(entity.getMarket_info().getBuy_name().toUpperCase());
        ((MainAct) getMContext()).mDataService.leaveDept(presenter.area);
        ((MainAct) getMContext()).mDataService.subscribeDept(presenter.entity.getMarket_info().getName());
        presenter.area = entity.getMarket_info().getName();
        ACache.get(getMContext()).put("coin_aera", presenter.area);
        marketnp_tv.setText(ToolUtils.doublePoint8(ToolUtils.String2Double(entity.getMarket_info().getNew_price())));
        getPrice(entity.getMarket_info().getNew_price(), fprice_tv);
        buy_price_et.setText(ToolUtils.trimZero(entity.getMarket_info().getNew_price()));
        sell_price_et.setText(ToolUtils.trimZero(entity.getMarket_info().getNew_price()));
        if (presenter.assatEntity == null) {
            presenter.getCoinBusinessAssetData();
        }
        getPrice(presenter.buy_price, buy_rmb_tv);
        getPrice(presenter.sell_price, sell_rmb_tv);
        if (entity.getUser() != null && entity.getUser().getCollect_market() != null) {
            setCollectState(entity.getUser().getCollect_market());
        }


    }

    public void setCollectState(List<String> collects) {
        for (String collect : collects) {
            if (presenter.entity.getMarket_info().getName().equals(collect)) {
                coinbusiness_save.setSelected(true);
                return;
            }
        }
        coinbusiness_save.setSelected(false);
    }

    public void setUserData(boolean exchangeSellNum) {
        if (presenter.assatEntity != null) {
            jiaoyi_ll.setVisibility(View.VISIBLE);
            buy_num_et.setHint(R.string.num);
            HomeAssatEntity.Rows sellEntity = getAssetEntity(presenter.entity.getMarket_info().getSell_name());
            HomeAssatEntity.Rows buyEntity = getAssetEntity(presenter.entity.getMarket_info().getBuy_name());
//            String sellNum = ToolUtils.division(sellEntity.getAble(), buy_price_et.getText().toString(), 8);
            if (presenter.isSellView()) {
                buy_coin_num.setText(String.format("%s %s", buyEntity.getAble(), buyEntity.getName().toUpperCase()));
                bibi_coin_name_tv.setText(buyEntity.getName().toUpperCase());
            } else {
                buy_coin_num.setText(String.format("%s %s", sellEntity.getAble(), sellEntity.getName().toUpperCase()));
//                bibi_coin_name_tv.setText(sellEntity.getName().toUpperCase());
                if (presenter.nowPriceType == P_LIMIT_PRICE) {
                    bibi_coin_name_tv.setText(presenter.entity.getMarket_info().getBuy_name().toUpperCase());
                } else {
                    //隐藏下面的交易额
                    bibi_coin_name_tv.setText(presenter.entity.getMarket_info().getSell_name().toUpperCase());
                    jiaoyi_ll.setVisibility(View.INVISIBLE);
                    buy_num_et.setHint(R.string.jys_tx);
                }
            }

            sell_coin_num.setText(String.format("%s %s", buyEntity.getAble(), buyEntity.getName().toUpperCase()));
        } else {
            resetCoinNum();
        }
    }

    @Override
    public void resetCoinNum() {
        jiaoyi_ll.setVisibility(View.VISIBLE);
        buy_num_et.setHint(R.string.num);
        if (presenter.isSellView()) {
            buy_coin_num.setText("0" + (businessEntity == null ? "" : businessEntity.getMarket_info().getBuy_name().toUpperCase()));
            bibi_coin_name_tv.setText(businessEntity == null ? "" : businessEntity.getMarket_info().getBuy_name().toUpperCase());
        } else {
            buy_coin_num.setText("0" + (businessEntity == null ? "" : businessEntity.getMarket_info().getSell_name().toUpperCase()));
            if (presenter.nowPriceType == P_LIMIT_PRICE) {
                bibi_coin_name_tv.setText(businessEntity == null ? "" : businessEntity.getMarket_info().getBuy_name().toUpperCase());
            } else {
                bibi_coin_name_tv.setText(businessEntity == null ? "" : businessEntity.getMarket_info().getSell_name().toUpperCase());
                jiaoyi_ll.setVisibility(View.INVISIBLE);
                buy_num_et.setHint(R.string.jys_tx);
//                buy_num_et.setHint(R.string.num);
            }
//            bibi_coin_name_tv.setText(businessEntity == null ? "" : businessEntity.getMarket_info().getSell_name().toUpperCase());
        }


        presenter.assatEntity = null;
    }

    @Override
    public void submitSuccess(int type, boolean isCheck) {
        if (isCheck) {
            fUserInfoEntity.setCheck_pwd(true);
            ToolUtils.saveUserInfo(getMContext(), fUserInfoEntity);
        }
        switch (type) {
            case POST_COINBUSINESS_SUBMIT_REQ.P_SELL:
                sell_num_et.setText("0");
                sell_price_et.setText(ToolUtils.trimZero(presenter.entity.getMarket_info().getNew_price()));
                break;
            case POST_COINBUSINESS_SUBMIT_REQ.P_BUY:
                buy_num_et.setText("0");
                buy_price_et.setText(ToolUtils.trimZero(presenter.entity.getMarket_info().getNew_price()));
                break;
        }
    }

    public HomeAssatEntity.Rows getAssetEntity(String name) {
        for (HomeAssatEntity.Rows row : presenter.assatEntity.getRows()) {
            if (name.equals(row.getName())) {
                return row;
            }
        }
        return null;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        isDestory = true;
        handler.removeCallbacksAndMessages(null);
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        nsl.scrollTo(0, 0);
        if (resultCode == 0) {
            return;
        } else if (resultCode == 1) {
            MarketListEntity entity = (MarketListEntity) data.getSerializableExtra("marketListEntities");
            tv_market_name.setText(entity.getName().toUpperCase());
        }

        if (requestCode == 500) {
            MarketListEntity select_ml = (MarketListEntity) data.getSerializableExtra("select_ml");
            presenter.getMarketData(null, select_ml.getName());
        }
        switch (requestCode) {
            case SlideMenuAct.ACT_REQCODE:
                if (resultCode == SlideMenuAct.ACT_RES_OK) {
                    MarketListEntity entity = (MarketListEntity) data.getSerializableExtra("entity");
                    presenter.getMarketData(null, entity.getName());
                }
                break;
            default:
                break;
        }
    }


    /**
     * 显示订单状态选择栏
     */
    private void showTradeTypeDialog() {
        final List<String> names = new ArrayList<>();
        names.add(getString(R.string.business_trade_all));
        names.add(getString(R.string.business_trade_not));
        names.add(getString(R.string.business_trade_yet));
        ToolUtils.showDialog(getMContext(), new CustomSelectDialog.SelectDialogListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /*showtradetype_tv.setText(names.get(position));
                switch (position) {
                    case 0:
                        presenter.setMyTradeStatus(GET_MY_TRADELIST_REQ.TRADE_STATUS_ALL);
                        break;
                    case 1:
                        presenter.setMyTradeStatus(GET_MY_TRADELIST_REQ.TRADE_STATUS_NOT);
                        break;
                    case 2:
                        presenter.setMyTradeStatus(GET_MY_TRADELIST_REQ.TRADE_STATUS_YET);
                        break;
                }
                presenter.getMyTradeList(true);*/
            }
        }, names);
    }

    /**
     * 选择交易方式
     */
    private void ChangeExchangeWays(int position) {

        if (position == 0) {
            tv_market_num.setText(R.string.jys_tx);
            Transaction_amount_tv.setText(R.string.jys_tx);
            if (presenter.nowPriceType != P_LIMIT_PRICE) {
                presenter.nowPriceType = CoinBusinessPresenter.P_LIMIT_PRICE;
                buy_price_ll.setVisibility(View.VISIBLE);
                sell_price_ll.setVisibility(View.VISIBLE);
                buy_sj_tv.setVisibility(View.GONE);
                sell_sj_tv.setVisibility(View.GONE);
                xj_tv.setTextColor(getResources().getColor(R.color.main_little_title));
                xj_view.setVisibility(View.VISIBLE);
                sj_tv.setTextColor(getResources().getColor(R.color.thin_tx));
                sj_view.setVisibility(View.GONE);
                setUserData(false);
                try {
                    buy_jye_tv.setText(ToolUtils.SzzcFormat(ToolUtils.multiply(buy_price_et
                            .getText().toString(), buy_num_et.getText().toString())));
                } catch (Exception e) {
                    buy_jye_tv.setText("0");
                }
                try {
                    sell_jye_tv.setText(ToolUtils.SzzcFormat(ToolUtils.multiply(sell_price_et
                            .getText().toString(), sell_num_et.getText().toString())));
                } catch (Exception e) {
                    sell_jye_tv.setText("0");
                }
            }
        } else {
            if (presenter.nowPriceType != P_MARKET_PRICE) {
                tv_market_num.setText(R.string.num);
                Transaction_amount_tv.setText(R.string.num);
                buy_jye_tv.setText(TextUtils.isEmpty(buy_num_et.getText().toString()) ? "0" : buy_num_et.getText().toString());
                sell_jye_tv.setText(TextUtils.isEmpty(sell_num_et.getText().toString()) ? "0" : sell_num_et.getText().toString());
                presenter.nowPriceType = CoinBusinessPresenter.P_MARKET_PRICE;

//                xj_tv.setText(getString(R.string.business_shijia));//限价
                setUserData(false);
                sj_tv.setTextColor(getResources().getColor(R.color.main_little_title));
                sj_view.setVisibility(View.VISIBLE);
                xj_tv.setTextColor(getResources().getColor(R.color.thin_tx));
                xj_view.setVisibility(View.GONE);
                buy_price_ll.setVisibility(View.GONE);
                sell_price_ll.setVisibility(View.GONE);
                buy_sj_tv.setVisibility(View.VISIBLE);
                sell_sj_tv.setVisibility(View.VISIBLE);
            }
        }
        try {
            ToolUtils.checkLogin(getMContext(), false);
            if (presenter.isSellView()) {
                buy_btn.setText(getString(R.string.tradechat_sell));
            } else {
                buy_btn.setText(getString(R.string.tradechat_buy));
            }
        } catch (NeedLoginException e) {
            coinbusiness_save.setSelected(false);
            buy_btn.setText(getString(R.string.coinbusinessfragment_notlogin));
            sell_btn.setText(getString(R.string.coinbusinessfragment_notlogin));
            e.printStackTrace();
        }

    }

    public void getPrice(String price, TextView view) {
        String s = presenter.setGFbPrice(price);
        view.setText(s);
    }

    private void showCustomDialog() {
        final List<String> names = new ArrayList<>();
        names.add(getString(R.string.business_xiangjia));
        names.add(getString(R.string.business_shijia));
        showDialog(new CustomSelectDialog.SelectDialogListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ChangeExchangeWays(position);
            }
        }, names);
    }

    private CustomSelectDialog showDialog(CustomSelectDialog.SelectDialogListener listener,
                                          List<String> names) {
        CustomSelectDialog dialog = new CustomSelectDialog(((Activity) getMContext()),
                R.style.transparentFrameWindowStyle, listener, names);
        dialog.setItemColor(R.color.colorAccent, R.color.colorPrimary);
        //判断activity是否finish
        if (!((Activity) getMContext()).isFinishing()) {
            dialog.show();
        }
        return dialog;
    }
}

