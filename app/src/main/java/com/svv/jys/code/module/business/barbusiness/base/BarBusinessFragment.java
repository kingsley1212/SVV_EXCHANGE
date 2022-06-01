package com.svv.jys.code.module.business.barbusiness.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.MBaseAdapter;
import com.svv.jys.code.common.base.mvp.MvpFragment;
import com.svv.jys.code.common.constant.ACEConstant;
import com.svv.jys.code.common.db.nativecache.ACache;
import com.svv.jys.code.common.entity.CollectEntity;
import com.svv.jys.code.common.entity.EntrustEntity;
import com.svv.jys.code.common.entity.LtUserCoinInfoentity;
import com.svv.jys.code.common.entity.MarketListEntity;
import com.svv.jys.code.common.entity.MarketTitleEntity;
import com.svv.jys.code.common.entity.TradeListEntity;
import com.svv.jys.code.common.utils.T;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.common.view.popup.PopupBusinessSubmitView;
import com.svv.jys.code.common.view.popup.PopupDialogView;
import com.svv.jys.code.common.view.selfview.MProgressView;
import com.svv.jys.code.module.business.barbusiness.base.adapter.BarDelegateAdapter;
import com.svv.jys.code.module.business.barbusiness.base.model.IBarBusinessModel;
import com.svv.jys.code.module.business.barbusiness.base.presenter.BarBusinessPresenter;
import com.svv.jys.code.module.business.barbusiness.base.view.IBarBusinessView;
import com.svv.jys.code.module.business.coinbusiness.base.adapter.Pankou2Adapter;
import com.svv.jys.code.module.business.coinbusiness.base.adapter.PankouAdapter;
import com.svv.jys.code.module.business.coinbusiness.base.presenter.CoinBusinessPresenter;
import com.svv.jys.code.module.main.MainAct;
import com.svv.jys.code.module.market.slidemenu.SlideMenuAct;
import com.svv.jys.code.module.net.exception.NeedLoginException;
import com.svv.jys.code.module.net.req.GET_MY_TRADELIST_REQ;
import com.svv.jys.code.module.net.req.POST_BARBUSINESS_SUBMIT_REQ;
import com.svv.jys.code.module.net.req.POST_COINBUSINESS_SUBMIT_REQ;
import com.svv.jys.code.module.server.dataserver.event.MarketListEvent;
import com.svv.jys.code.module.server.dataserver.event.TradeListEvent;
import com.pedaily.yc.ycdialoglib.selectDialog.CustomSelectDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/4/27 0027.
 */

public class BarBusinessFragment extends MvpFragment<IBarBusinessView, IBarBusinessModel, BarBusinessPresenter>
        implements IBarBusinessView {


    private ImageView coinbusiness_return, coinbusiness_save, coinbusiness_market, iv_menu;
    private TextView coinBusinessBuy_text, coinBusinessSell_text, barbusiness_buyorsell, marketnp_tv, coin_util_1,
            tv_market_name, usesj_tx, stype_tv, showtradetype_tv, tv_my_price, min_amount_tv,
            add_amount_tv, fprice_tv, tv_price, tv_sell_price, tv_sell_coin, tv_risk_rote, tv_buy_price, tv_buy_coin,
            fivePointRightText, jye_tv, about_rmb_tv,coin_util_tx;
    private EditText tv_my_num;
    private LinearLayout ll_price;
    private View coinBusinessBuy_line, coinBusinessSell_line, ll_xj, showtradetype_ll;
    private RecyclerView rvCoinType3List, rvCoinType4List;
    private RecyclerView rvDelegate;
    private RelativeLayout jyetx_re;
    private PopupBusinessSubmitView businessSubmitView;

    private PankouAdapter pankou3Adapter;
    private Pankou2Adapter pankou4Adapter;
    private BarDelegateAdapter delegateAdapter;
    private RelativeLayout rl_weituo;
    private FrameLayout fl_weituo;
    private MProgressView pointProgress;
    @Override
    public Context getMContext() {
        return getContext();
    }

    @Override
    public BarBusinessPresenter initPresenter() {
        return new BarBusinessPresenter();
    }
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden){
            if (presenter.getSelect_ml()!=null){
                ((MainAct) getMContext()).mDataService.leaveDept(presenter.getSelect_ml().getName());
            }
        }
    }
    @Override
    public void onWakeBussiness() {
        buySellTabChange(presenter.nowBusinessType);
//        noLogin();
        try {
            ToolUtils.checkLogin(getMContext(),false);
            setCollectMarket(((CollectEntity) ACache.get(getActivity()).getAsObject(ACEConstant
                    .ACE_USERINFO_COLLECTMARKET)).getCollect_market());
        } catch (NeedLoginException e) {
            coinbusiness_save.setSelected(false);
            e.printStackTrace();
        }
//        presenter.getMyTradeList(true);
        MarketListEntity marketListEntity = (MarketListEntity) ACache.get(getMContext()).getAsObject
                (ACEConstant.ACE_MARKET_ENTITY);
        if (marketListEntity != null) {
            presenter.setSelect_ml(marketListEntity);
        } else {
            if (presenter.getSelect_ml()!=null){
                presenter.getLtuserinfo();
                ((MainAct) getMContext()).mDataService.subscribeDept(presenter.getSelect_ml().getName());
            }else {
                presenter.gettitle();//获取交易市场
            }
        }

    }


    public void noLogin(){
        try {
            ToolUtils.checkLogin(getMContext(),false);
            fl_weituo.setVisibility(View.VISIBLE);
            rl_weituo.setVisibility(View.VISIBLE);
        } catch (NeedLoginException e) {
            fl_weituo.setVisibility(View.GONE);
            rl_weituo.setVisibility(View.GONE);
            e.printStackTrace();
        }
    }

    @Override
    public void baseInitialization() {
        EventBus.getDefault().register(this);
        try {
            ToolUtils.checkLogin(getMContext(),false);
            presenter.collectEntity = (CollectEntity) ACache.get(getActivity()).getAsObject(ACEConstant
                    .ACE_USERINFO_COLLECTMARKET);
        } catch (NeedLoginException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int setR_Layout() {
        return R.layout.fragment_barbusiness;
    }

    @Override
    public void viewInitialization() {
        jyetx_re = (RelativeLayout) $(R.id.jyetx_re);
        fl_weituo= (FrameLayout) $(R.id.fl_weituo);
        rl_weituo= (RelativeLayout) $(R.id.rl_weituo);
        coin_util_tx = (TextView) $(R.id.coin_util_tx);
//        noLogin();
        ll_price = (LinearLayout) $(R.id.ll_price);
        tv_price = (TextView) $(R.id.tv_price);
        tv_sell_price = (TextView) $(R.id.tv_sell_price);
        tv_sell_coin = (TextView) $(R.id.tv_sell_coin);
        tv_risk_rote = (TextView) $(R.id.tv_risk_rote);
        tv_buy_price = (TextView) $(R.id.tv_buy_price);
        tv_buy_coin = (TextView) $(R.id.tv_buy_coin);

        tv_market_name = (TextView) $(R.id.tv_market_name);
        iv_menu = (ImageView) $(R.id.iv_menu);
        iv_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getMContext(), SlideMenuAct.class), SlideMenuAct.ACT_REQCODE);
                getActivity().overridePendingTransition(R.anim.anim_left_in, R.anim.anim_no);
            }
        });
        //右上角 返回 收藏 行情
        coinbusiness_return = (ImageView) $(R.id.coinbusiness_return);
        coinbusiness_save = (ImageView) $(R.id.coinbusiness_save);
        coinbusiness_market = (ImageView) $(R.id.coinbusiness_market);
        coinbusiness_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                coinbusiness_return.setSelected(!coinbusiness_return.isSelected());
            }
        });
        coinbusiness_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.collectMarket();
            }
        });
        coinbusiness_market.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                coinbusiness_market.setSelected(!coinbusiness_market.isSelected());
                if (presenter.select_ml == null) {
                    T.showShort(getMContext(), getString(R.string.data_not_yet));
                    return;
                }
//                TradeChat2Act.startAct(getMContext(), presenter.select_ml);
            }
        });

        coin_util_1 = (TextView) $(R.id.coin_util_1);
        about_rmb_tv = (TextView) $(R.id.about_rmb_tv);
        marketnp_tv = (TextView) $(R.id.marketnp_tv);

        //买入卖出
        barbusiness_buyorsell = (TextView) $(R.id.barbusiness_buyorsell);
        barbusiness_buyorsell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                        public void doSubmit(String price, String num, String pw,int type) {
                            switch (presenter.nowPriceType) {
                                case BarBusinessPresenter.P_LIMIT_PRICE:
                                    presenter.doPostCoinBusinessByLimit(price, num, pw);
                                    break;
                                case BarBusinessPresenter.P_MARKET_PRICE:
                                    presenter.doPostCoinBusinessByMarket(price, num, pw);
                                    break;
                            }
                        }
                    });
                } else {
                    businessSubmitView.dismiss();
                }
                String num = tv_my_num.getText().toString().trim();
                if (ToolUtils.isNull(num)) {
                    T.showShort(getMContext(), getString(R.string.business_inputnum));
                    return;
                }
                if (presenter.getSelect_ml()!=null){
                    businessSubmitView.showPop(view, presenter.nowBusinessType, presenter
                            .nowPriceType ==
                            CoinBusinessPresenter.P_LIMIT_PRICE ? tv_my_price.getText().toString().trim() :
                            getString(R.string.business_zuiyoujia2), num,presenter.getSelect_ml().sellName,presenter.getSelect_ml().buyName);
                }


//                businessSubmitView.showPop(view, presenter.nowBusinessType, price, num);
            }
        });

        //买入卖出
        coinBusinessBuy_text = (TextView) $(R.id.coinBusinessBuy_text);
        coinBusinessSell_text = (TextView) $(R.id.coinBusinessSell_text);
        coinBusinessBuy_line = $(R.id.coinBusinessBuy_line);
        coinBusinessSell_line = $(R.id.coinBusinessSell_line);
        coinBusinessBuy_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buySellTabChange(POST_BARBUSINESS_SUBMIT_REQ.P_BUY);

            }
        });
        coinBusinessSell_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buySellTabChange(POST_BARBUSINESS_SUBMIT_REQ.P_SELL);

            }
        });
        jye_tv = (TextView) $(R.id.jye_tv);

        //盘口 列表
        rvCoinType3List = (RecyclerView) $(R.id.rvCoinType3List);
        rvCoinType3List.setLayoutManager(new LinearLayoutManager(getMContext()));
        rvCoinType4List = (RecyclerView) $(R.id.rvCoinType4List);
        rvCoinType4List.setLayoutManager(new LinearLayoutManager(getMContext()));

        //当前委托
        rvDelegate = (RecyclerView) $(R.id.rvDelegate);
        rvDelegate.setLayoutManager(new LinearLayoutManager(getMContext()));

        showtradetype_tv = (TextView) $(R.id.showtradetype_tv);
        showtradetype_ll = $(R.id.showtradetype_ll);
        showtradetype_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                gotoActivity(CurrentEntrustAct.class);
                showTradeTypeDialog();
            }
        });

        //进度选择圈
        int[] locations = new int[2];
        pointProgress = (MProgressView) $(R.id.mProgressView);
        pointProgress.getLocationInWindow(locations);
        pointProgress.setOXY(locations[0], locations[1]);
        pointProgress.setMPositionListener(new MProgressView.MPositionListener() {
            @Override
            public void selectMultiple(float u_ratio) {
                try {
                    if (u_ratio == 0.00) {
                        tv_my_num.setText("");
                    } else {
                        if (ToolUtils.String2Double(fivePointRightText.getText().toString().trim()) == 0.00) {
                            tv_my_num.setText("");
                        } else {
                            tv_my_num.setText(ToolUtils.multiply(fivePointRightText.getText().toString().trim(),
                                    u_ratio + ""));
                        }

                    }
                } catch (Exception e) {

                }
            }
        });

        stype_tv = (TextView) $(R.id.stype_tv);
        usesj_tx = (TextView) $(R.id.usesj_tx);

        fprice_tv = (TextView) $(R.id.fprice_tv);
        fivePointRightText = (TextView) $(R.id.fivePointRightText);
        tv_my_num = (EditText) $(R.id.tv_my_num);
        tv_my_num.addTextChangedListener(new TextWatcher() {
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
                    jye_tv.setText(ToolUtils.doublePoint4(ToolUtils.String2Double(ToolUtils.multiply(tv_my_price
                            .getText().toString(), tv_my_num.getText().toString()))));
                } catch (Exception e) {
                    jye_tv.setText("0.0000");
                }
            }
        });

        tv_my_price = (TextView) $(R.id.tv_my_price);
        tv_my_price.addTextChangedListener(new TextWatcher() {
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
                presenter.price = editable.toString();
                presenter.setProgessMaxNum();
                presenter.setGFbPrice();
                try {
                    jye_tv.setText(ToolUtils.doublePoint4(ToolUtils.String2Double(ToolUtils.multiply(tv_my_price
                            .getText().toString(), tv_my_num.getText().toString()))));
                } catch (Exception e) {
                    jye_tv.setText("0.0000");
                }
            }
        });
        ll_xj = $(R.id.ll_xj);
        ll_xj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCustomDialog();
            }
        });

        min_amount_tv = (TextView) $(R.id.min_amount_tv);
        min_amount_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BigDecimal decimal = new BigDecimal(ToolUtils.String2Double(tv_my_price.getText().toString().trim()));
                if (ToolUtils.String2Float(ToolUtils.doublePoint4(decimal.doubleValue())) > 0.00000000) {
                    decimal = decimal.subtract(new BigDecimal(0.00000001));
                }
                tv_my_price.setText(ToolUtils.trimZero(ToolUtils.doublePoint8(decimal.doubleValue())));
                presenter.price = ToolUtils.doublePoint(decimal.doubleValue());
                presenter.setProgessMaxNum();
            }
        });
        add_amount_tv = (TextView) $(R.id.add_amount_tv);
        add_amount_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BigDecimal decimal = new BigDecimal(ToolUtils.String2Double(tv_my_price.getText().toString().trim()));
                decimal = decimal.add(new BigDecimal(0.00000001));
                tv_my_price.setText(ToolUtils.trimZero(ToolUtils.doublePoint8(decimal.doubleValue())));
                presenter.price = ToolUtils.doublePoint(decimal.doubleValue());
                presenter.setProgessMaxNum();
            }
        });

    }

    @Override
    public void doBusiness() {
//        coinBusinessBuy_text.performClick();
//        presenter.getDelegateData();
//        presenter.gettitle();
//        setTrade(null);
        coinBusinessBuy_text.performClick();//点击出初始化显示买入
        MarketListEntity marketListEntity = (MarketListEntity) ACache.get(getMContext()).getAsObject
                (ACEConstant.ACE_MARKET_ENTITY);
        if (marketListEntity != null) {
            presenter.setSelect_ml(marketListEntity);
        } else {
            presenter.gettitle();//获取交易市场
        }
        setTrade(null);//默认显示没有委托
    }


    /**
     * @param tabPosition 1--2
     */
    public void buySellTabChange(int tabPosition) {
        switch (tabPosition) {
            case 1:
                pointProgress.setReset();
                tv_my_num.setText("");
                coinBusinessBuy_text.setSelected(true);
                coinBusinessSell_text.setSelected(false);
                coinBusinessBuy_line.setVisibility(View.VISIBLE);
                coinBusinessSell_line.setVisibility(View.GONE);
                presenter.nowBusinessType = POST_COINBUSINESS_SUBMIT_REQ.P_BUY;
                marketnp_tv.setTextColor(MProgressView.MGREEN);
                barbusiness_buyorsell.setBackgroundColor(MProgressView.MGREEN);
                barbusiness_buyorsell.setText(getString(R.string.coinbusinessfragment_buy));

                try {
                    ToolUtils.checkLogin(getMContext(), false);
                } catch (NeedLoginException e) {
                    barbusiness_buyorsell.setText(getString(R.string.coinbusinessfragment_notlogin));
                }

                if (pointProgress != null) {
                    pointProgress.setMUSE_C(MProgressView.MGREEN);
                    pointProgress.postInvalidate();
                }
                if (presenter.nowPriceType == CoinBusinessPresenter.P_MARKET_PRICE) {
                    tv_my_price.setText("1");
                    coin_util_tx.setText(getString(R.string.jys_tx));
                    coin_util_1.setText(presenter.getSelect_ml().getSell_name().toUpperCase());
                }
                presenter.setProgessMaxNum();
                break;
            case 2:
                pointProgress.setReset();
                tv_my_num.setText("");
                presenter.nowBusinessType = POST_COINBUSINESS_SUBMIT_REQ.P_SELL;
                coinBusinessBuy_text.setSelected(false);
                coinBusinessSell_text.setSelected(true);
                coinBusinessBuy_line.setVisibility(View.GONE);
                coinBusinessSell_line.setVisibility(View.VISIBLE);

                marketnp_tv.setTextColor(MProgressView.MORANGE);
                barbusiness_buyorsell.setBackgroundColor(MProgressView.MORANGE);
                barbusiness_buyorsell.setText(getString(R.string.coinbusinessfragment_sell));
                presenter.setProgessMaxNum();
                try {
                    ToolUtils.checkLogin(getMContext(), false);
                } catch (NeedLoginException e) {
                    barbusiness_buyorsell.setText(getString(R.string.coinbusinessfragment_notlogin));
                }

                if (pointProgress != null) {
                    pointProgress.setMUSE_C(MProgressView.MORANGE);
                    pointProgress.postInvalidate();
                }
                if (presenter.nowPriceType == CoinBusinessPresenter.P_MARKET_PRICE) {
                    tv_my_price.setText("1");
                    coin_util_tx.setText(getString(R.string.number_tx));
                    coin_util_1.setText(presenter.getSelect_ml().getBuy_name().toUpperCase());
                }
                break;
            default:
                break;
        }
    }

    /**
     * 显示市场信息
     */
    @Override
    public void setMarket() {
        try {
            ((Activity) getMContext()).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tv_market_name.setText(presenter.getSelect_ml().getName().replace("_", "/").toUpperCase());
                    coin_util_1.setText(presenter.getSelect_ml().getBuy_name().toUpperCase());
                    marketnp_tv.setText(ToolUtils.doublePoint6(ToolUtils.String2Double(presenter.getSelect_ml()
                            .getNew_price())));
                    ToolUtils.setMarketUpDownTvColor(presenter.getSelect_ml(), marketnp_tv);
                    presenter.setGFbPrice();
                    fprice_tv.setText(getMContext().getString(R.string.fbprice_data, ToolUtils.doublePoint
                            (ToolUtils.String2Double(presenter.getSelect_ml().getCny_price())) + ""));
                }
            });
        } catch (Exception e) {

        }
    }

    @Override
    public void setMaxNum(String maxNum) {
        fivePointRightText.setText(ToolUtils.doublePoint4(ToolUtils.String2Double(maxNum)));
//        if(pointProgress!=null){
//            pointProgress.performScroll();
//        }
    }

    @Override
    public void setCollectMarket(List<String> list) {
        if (presenter.collectEntity == null) {
            coinbusiness_save.setSelected(false);
        } else {
            for (int i = 0; i < presenter.collectEntity.getCollect_market().size(); i++) {
                if (presenter.getSelect_ml().getName().equals(presenter.collectEntity.getCollect_market().get(i))) {
                    coinbusiness_save.setSelected(true);
                    return;
                }
            }
            coinbusiness_save.setSelected(false);
        }
    }

    @Override
    public void setTrade(TradeListEntity entity) {
        if (entity != null) {
            if (pankou3Adapter == null) {//出售深度数据
                pankou3Adapter = new PankouAdapter(entity.getSell(), getMContext());
                rvCoinType3List.setAdapter(pankou3Adapter);
                pankou3Adapter.setOnItemClick(new MBaseAdapter.MOnItemClickListener() {
                    @Override
                    public void onItemClick(int position, Object o) {
                        tv_my_price.setText(((TradeListEntity.Trade) o).getPrice());
//                        tv_my_num.setText(((TradeListEntity.Trade) o).getNum());
                    }
                });
            } else {
                pankou3Adapter.setList(entity.getSell());
                pankou3Adapter.notifyDataSetChanged();
            }

            if (pankou4Adapter == null) {//购买深度数据
                pankou4Adapter = new Pankou2Adapter(entity.getBuy(), getMContext());
                rvCoinType4List.setAdapter(pankou4Adapter);
                pankou4Adapter.setOnItemClick(new MBaseAdapter.MOnItemClickListener() {
                    @Override
                    public void onItemClick(int position, Object o) {
                        tv_my_price.setText(((TradeListEntity.Trade) o).getPrice());
//                        tv_my_num.setText(((TradeListEntity.Trade) o).getNum());
                    }
                });
            } else {
                pankou4Adapter.setList(entity.getBuy());
                pankou4Adapter.notifyDataSetChanged();
            }
        } else {
            pankou3Adapter = new PankouAdapter(new ArrayList<TradeListEntity.Trade>(), getMContext());
            rvCoinType3List.setAdapter(pankou3Adapter);
            pankou3Adapter.setOnItemClick(new MBaseAdapter.MOnItemClickListener() {
                @Override
                public void onItemClick(int position, Object o) {
                    tv_my_price.setText(((TradeListEntity.Trade) o).getPrice());
//                    tv_my_num.setText(((TradeListEntity.Trade) o).getNum());
                }
            });
            pankou4Adapter = new Pankou2Adapter(new ArrayList<TradeListEntity.Trade>(), getMContext());
            rvCoinType4List.setAdapter(pankou4Adapter);
            pankou4Adapter.setOnItemClick(new MBaseAdapter.MOnItemClickListener() {
                @Override
                public void onItemClick(int position, Object o) {
                    tv_my_price.setText(((TradeListEntity.Trade) o).getPrice());
//                    tv_my_num.setText(((TradeListEntity.Trade) o).getNum());
                }
            });
        }
    }


    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void doMarketListEvent(MarketListEvent marketListEvent) {
        if (marketListEvent != null && !isHidden) {
            presenter.doHandleMarket(marketListEvent.getMarketListEntities());
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void doTradeListEvent(TradeListEvent tradeListEvent) {
        if (tradeListEvent != null && !isHidden) {
            setTrade(tradeListEvent.tradeListEntity);
        }
    }

    @Override
    public void isNoData(boolean flag) {
        if (flag) {
            $(R.id.nsv).setVisibility(View.VISIBLE);
            rvDelegate.setVisibility(View.GONE);
        } else {
            $(R.id.nsv).setVisibility(View.GONE);
            rvDelegate.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void removeSuccese() {
        T.showShort(getMContext(), getString(R.string.barbusinessfragment_cx_success));
        presenter.getMyTradeList(true);

    }

    /**
     * 杠杆交易信息
     *
     * @param ltUserCoinInfoentity
     */
    @Override
    public void setLtUserinfo(LtUserCoinInfoentity ltUserCoinInfoentity) {

        if(ltUserCoinInfoentity!=null) {
            tv_price.setText(ltUserCoinInfoentity.getPrice());
            tv_sell_coin.setText(ltUserCoinInfoentity.getMarket().toUpperCase().split("_")[1]);
            tv_buy_coin.setText(ltUserCoinInfoentity.getMarket().toUpperCase().split("_")[0]);
            tv_sell_price.setText(String.format(getString(R.string.barbusinessfragment_coin1), ltUserCoinInfoentity
                    .getSell_coin()));
            tv_buy_price.setText(String.format(getString(R.string.barbusinessfragment_coin1), ltUserCoinInfoentity
                    .getBuy_coin()));
            tv_risk_rote.setText(String.format(getString(R.string.barbusinessfragment_risk_probability),
                    ltUserCoinInfoentity.getRisk_ratio()) + "%");
        }else{
            tv_price.setText(getString(R.string.data_defult));
            tv_sell_coin.setText( getString(R.string.data_defult));
            tv_buy_coin.setText( getString(R.string.data_defult));
            tv_sell_price.setText(String.format(getString(R.string.barbusinessfragment_coin1), getString(R.string.data_defult)));
            tv_buy_price.setText(String.format(getString(R.string.barbusinessfragment_coin1), getString(R.string.data_defult)));
            tv_risk_rote.setText(String.format(getString(R.string.barbusinessfragment_risk_probability),
                    getString(R.string.data_defult)));
        }
    }

    @Override
    public void postEntrusSuccese() {
//        T.showShort(getMContext(), "交易成功");
        presenter.getLtuserinfo();
        presenter.getMyTradeList(true);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 0) {
            return;
        } else if (resultCode == 1) {
            MarketListEntity entity = (MarketListEntity) data.getSerializableExtra("marketListEntities");
            tv_market_name.setText(entity.getName().replace("_", "/").toUpperCase());
        }
        switch (requestCode) {
            case SlideMenuAct.ACT_REQCODE:
                if (resultCode == SlideMenuAct.ACT_RES_OK) {
                    MarketTitleEntity select_mt = (MarketTitleEntity) data.getSerializableExtra("select_mt");
                    MarketListEntity select_ml = (MarketListEntity) data.getSerializableExtra("select_ml");
                    tv_market_name.setText(select_ml.getName().replace("_", "/").toUpperCase());
                    ((MainAct) getMContext()).mDataService.leaveDept(presenter.getSelect_ml().getName());
                    tv_my_price.setText(ToolUtils.trimZero(ToolUtils.doublePoint8(ToolUtils.String2Double(select_ml.getNew_price()))));
                    presenter.setSelect_mt(select_mt);
                    presenter.setSelect_ml(select_ml);
                    setTrade(null);
                    if (presenter.collectEntity == null) {
                        coinbusiness_save.setSelected(false);
                    } else {
                        if (presenter.collectEntity.getCollect_market()!=null){
                            for (int i = 0; i < presenter.collectEntity.getCollect_market().size(); i++) {
                                if (select_ml.getName().equals(presenter.collectEntity.getCollect_market().get(i))) {
                                    coinbusiness_save.setSelected(true);
                                }
                            }
                        }
                    }
                } else {

                }
                break;
            default:
                break;
        }
    }

    private void showTradeTypeDialog() {
        final List<String> names = new ArrayList<>();
        names.add(getString(R.string.business_trade_all));
        names.add(getString(R.string.business_trade_not));
        names.add(getString(R.string.business_trade_yet));
        ToolUtils.showDialog(getMContext(), new CustomSelectDialog.SelectDialogListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showtradetype_tv.setText(names.get(position));
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
                presenter.getMyTradeList(true);
            }
        }, names);
    }


    private void showCustomDialog() {
        final List<String> names = new ArrayList<>();
        names.add(getString(R.string.business_xiangjia));
        names.add(getString(R.string.business_shijia));
        ToolUtils.showDialog(getMContext(), new CustomSelectDialog.SelectDialogListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    usesj_tx.setVisibility(View.GONE);
                    ll_price.setVisibility(View.VISIBLE);
                    about_rmb_tv.setVisibility(View.VISIBLE);
                    stype_tv.setText(getString(R.string.business_xiangjia));
                    presenter.nowPriceType = CoinBusinessPresenter.P_LIMIT_PRICE;
                    if (presenter.getSelect_ml() != null) {
                        tv_my_price.setText(ToolUtils.trimZero(ToolUtils.SzzcFormat(presenter.getSelect_ml().getNew_price())));
                        if (presenter.nowBusinessType == POST_COINBUSINESS_SUBMIT_REQ.P_BUY) {
                            coin_util_tx.setText(getString(R.string.number_tx));
                            coin_util_1.setText(presenter.getSelect_ml().getSell_name().toUpperCase());
                        } else {
                            coin_util_tx.setText(getString(R.string.number_tx));
                            coin_util_1.setText(presenter.getSelect_ml().getBuy_name().toUpperCase());
                        }
                        jyetx_re.setVisibility(View.VISIBLE);
                    }
                } else {
                    usesj_tx.setVisibility(View.VISIBLE);
                    ll_price.setVisibility(View.GONE);
                    about_rmb_tv.setVisibility(View.GONE);
                    stype_tv.setText(getString(R.string.business_shijia));
                    presenter.nowPriceType = CoinBusinessPresenter.P_MARKET_PRICE;
                    if (presenter.getSelect_ml() != null) {
                        tv_my_price.setText("1");
                        if (presenter.nowBusinessType == POST_COINBUSINESS_SUBMIT_REQ.P_BUY) {
                            coin_util_tx.setText(getString(R.string.jys_tx));
                            coin_util_1.setText(presenter.getSelect_ml().getSell_name().toUpperCase());
                        } else {
                            coin_util_tx.setText(getString(R.string.number_tx));
                            coin_util_1.setText(presenter.getSelect_ml().getBuy_name().toUpperCase());
                        }
                    }
                    jyetx_re.setVisibility(View.GONE);
                }

            }
        }, names);
    }

    @Override
    public void setDelegateData(final List<EntrustEntity.RowsBean> list) {
        if (list.size() == 0) {
            isNoData(true);
        } else {
            isNoData(false);

            if (delegateAdapter == null) {
                delegateAdapter = new BarDelegateAdapter(list, getMContext());
                rvDelegate.setAdapter(delegateAdapter);
                delegateAdapter.setOnItemClick(new MBaseAdapter.MOnItemClickListener() {
                    @Override
                    public void onItemClick(int position, Object o) {
                        T.showShort(getMContext(), "" + position);
                    }
                });
                delegateAdapter.setDoTradeDuelInterface(new BarDelegateAdapter.DoTradeDuelInterface() {
                    @Override
                    public void doTradeRevoke(final String id) {
                        PopupDialogView view = new PopupDialogView(getMContext(), new PopupDialogView.MClickLisnener() {
                            @Override
                            public void leftBtn() {

                            }

                            @Override
                            public void rightBtn() {
                                presenter.doTradeRevoke(id);
                            }
                        });
                        view.showPop(rvDelegate, getString(R.string.barbusinessfragment_tip), getString(R.string
                                        .barbusinessfragment_you_cxwt), getString(R.string.myselffragment_left_text),
                                getString(R.string.myselffragment_right_text));

                    }
                });
            } else {
                delegateAdapter.setData(list);
                delegateAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void addDelegateData(List<EntrustEntity.RowsBean> list) {
//        if (delegateAdapter == null) {
//            delegateAdapter = new BarDelegateAdapter(list, getMContext());
//            rvDelegate.setAdapter(delegateAdapter);
//            delegateAdapter.setDoTradeDuelInterface(new BarDelegateAdapter.DoTradeDuelInterface() {
//                @Override
//                public void doTradeRevoke(String id,int position) {
//                    presenter.doTradeRevoke(id);
//                }
//            });
//        } else {
//            delegateAdapter.addData(list);
//        }
    }


    /***
     * 设置我买卖价
     * @param myPrice
     */
    @Override
    public void setMyPrice(final String myPrice) {
        try {
            ((Activity) getMContext()).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tv_my_price.setText(ToolUtils.trimZero(ToolUtils.doublePoint8(ToolUtils.String2Double(myPrice)) + ""));
                }
            });
        } catch (Exception e) {

        }
    }

    /**
     * 计算显示预算的CNY价格
     *
     * @param multiply
     */
    @Override
    public void setGFbPriceTx(String multiply) {
        try {
            about_rmb_tv.setText(multiply == null ? getString(R.string.data_defult) : getString(R.string.fbprice_data,
                    multiply));
        } catch (Exception e) {
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
