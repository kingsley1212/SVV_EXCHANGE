package com.svv.jys.code.module.business.tradechat.base3;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.fujianlian.klinechart.KLineChartAdapter;
import com.github.fujianlian.klinechart.KLineChartView;
import com.github.fujianlian.klinechart.KLineEntity;
import com.github.fujianlian.klinechart.formatter.DateFormatter;
import com.svv.jys.R;
import com.svv.jys.code.common.base.mvp.MvpDataSocketActivity;
import com.svv.jys.code.common.constant.ACEConstant;
import com.svv.jys.code.common.db.nativecache.ACache;
import com.svv.jys.code.common.entity.MarketListEntity;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.common.view.selfview.MyViewPager;
import com.svv.jys.code.module.business.tradechat.base3.model.ITradeChat3Model;
import com.svv.jys.code.module.business.tradechat.base3.presenter.TradeChat3Presenter;
import com.svv.jys.code.module.business.tradechat.base3.view.ITradeChat3View;
import com.svv.jys.code.module.business.tradechat.deep.DeepFragment;
import com.svv.jys.code.module.business.tradechat.introduction.IntroductionFragment;
import com.svv.jys.code.module.business.tradechat.transaction.TransactionFragment;
import com.svv.jys.code.module.server.dataserver.event.MarketListEvent;
import com.svv.jys.code.module.server.dataserver.event.TradeJsonEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lzj on 2018/5/30.
 */

public class TradeChat3Act extends MvpDataSocketActivity<ITradeChat3View, ITradeChat3Model, TradeChat3Presenter>
        implements
        ITradeChat3View {

    private TextView toBuy, toSell;
    private TextView fenText, onem_tv, fivem_tv, fifteenm_tv, oneh_tv, oned_tv, onew_tv;
    private TextView[] textViews;

    private boolean klineReady = false;

    public static void startAct(Context mContext, MarketListEntity entity) {
        Intent intent = new Intent(mContext, TradeChat3Act.class);
        intent.putExtra("MarketListEntity", entity);
        ((Activity) mContext).startActivityForResult(intent, 500);
    }

    private TextView np_tv, highp_tv, lowp_tv, tradeamount_tv, cny_tv, cnyp_tv;
    private LinearLayout content_ll;
    private TabLayout tab_layout;
    private List<Fragment> fragments = new ArrayList<>();
    private MyViewPager view_pager;

    private KLineChartView kLineChartView;
    private KLineChartAdapter adapter = new KLineChartAdapter();

    @Override
    public Context getMContext() {
        return this;
    }

    @Override
    public TradeChat3Presenter initPresenter() {
        return new TradeChat3Presenter();
    }

    @Override
    public void baseInitialization() {
        presenter.getDataFromIntent(getIntent());
        EventBus.getDefault().register(this);
    }

    @Override
    public void cSConttectSeccuss() {
        if (presenter.select_ml == null) {
            return;
        }
        try {
            mDataService.subscribeKLineTrade(presenter.select_ml.getName(), "1");
        } catch (Exception e) {

        }
        fragments.add(DeepFragment.newInstance(presenter.select_ml.getName()));
        fragments.add(TransactionFragment.newInstance(presenter.select_ml.getName()));
        fragments.add(IntroductionFragment.newInstance(presenter.select_ml.getName()));
        MyAdapter myAdapter = new MyAdapter(getSupportFragmentManager());
        view_pager.setAdapter(myAdapter);
        tab_layout.setupWithViewPager(view_pager);
    }

    @Override
    public void cSConttectFail() {

    }

    @Override
    public int setR_Layout() {
        return R.layout.act_tradechat3;
    }

    @Override
    public void viewInitialization() {
        setTitleTx(presenter.symbol_title);
        setBackPress();

        view_pager = (MyViewPager) $(R.id.view_pager);
        content_ll = (LinearLayout) $(R.id.content_ll);
        toBuy = (TextView) $(R.id.toBuy);
        toSell = (TextView) $(R.id.toSell);
        np_tv = (TextView) $(R.id.np_tv);
        highp_tv = (TextView) $(R.id.highp_tv);
        lowp_tv = (TextView) $(R.id.lowp_tv);
        tradeamount_tv = (TextView) $(R.id.tradeamount_tv);
        cny_tv = (TextView) $(R.id.cny_tv);
        cnyp_tv = (TextView) $(R.id.cnyp_tv);
        tab_layout = (TabLayout) $(R.id.tab_layout);
        kLineChartView = (KLineChartView) $(R.id.kLineChartView);
        fenText = findViewById(R.id.fenText);
        onem_tv = findViewById(R.id.onem_tv);
        fivem_tv = findViewById(R.id.fivem_tv);
        fifteenm_tv = findViewById(R.id.fifteenm_tv);
        oneh_tv = findViewById(R.id.oneh_tv);
        oned_tv = findViewById(R.id.oned_tv);
        onew_tv = findViewById(R.id.onew_tv);
        textViews = new TextView[]{fenText, onem_tv, fivem_tv, fifteenm_tv, oneh_tv, oned_tv,onew_tv};

        fenText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeTimeTypeColor(fenText);
                kLineChartView.hideSelectData();
                if (!"1".equals(presenter.resolution)) {
                    adapter.clearData();
                    presenter.get1MinLineData();
                }
                kLineChartView.setMainDrawLine(true);
            }
        });
        onem_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeTimeTypeColor(onem_tv);
                kLineChartView.hideSelectData();
                if (!"1".equals(presenter.resolution)) {
                    adapter.clearData();
                    presenter.get1MinLineData();
                }
                kLineChartView.setMainDrawLine(false);
            }
        });
        fivem_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeTimeTypeColor(fivem_tv);
                kLineChartView.hideSelectData();
                kLineChartView.setMainDrawLine(false);
                adapter.clearData();
                presenter.get5MinLineData();
            }
        });
        fifteenm_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeTimeTypeColor(fifteenm_tv);
                kLineChartView.hideSelectData();
                kLineChartView.setMainDrawLine(false);
                adapter.clearData();
                presenter.get15MinLineData();
            }
        });
        oneh_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeTimeTypeColor(oneh_tv);
                kLineChartView.hideSelectData();
                kLineChartView.setMainDrawLine(false);
                adapter.clearData();
                presenter.get1HLineData();
            }
        });
        oned_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeTimeTypeColor(oned_tv);
                kLineChartView.hideSelectData();
                kLineChartView.setMainDrawLine(false);
                adapter.clearData();
                presenter.get1DLineData();
            }
        });
        onew_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeTimeTypeColor(onew_tv);
                kLineChartView.hideSelectData();
                kLineChartView.setMainDrawLine(false);
                adapter.clearData();
                presenter.get1WeekLineData();
            }
        });

        toBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("select_ml", presenter.select_ml);
                setResult(501, intent);
                finish();
            }
        });
        toSell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("select_ml", presenter.select_ml);
                setResult(502, intent);
                finish();
            }
        });


        kLineChartView.setDateTimeFormatter(new DateFormatter());
        kLineChartView.setGridRows(4);
        kLineChartView.setGridColumns(4);
        kLineChartView.setAdapter(adapter);
        kLineChartView.setOverScrollRange(150);
        kLineChartView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                ViewGroup viewGroup = (ViewGroup) view.getParent();
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_MOVE:
                        viewGroup.requestDisallowInterceptTouchEvent(true);
                        return false;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        viewGroup.requestDisallowInterceptTouchEvent(false);
                        return false;
                }
                return false;
            }
        });
    }


    public void changeTimeTypeColor(TextView aimView) {
        for (TextView tv : textViews) {
            tv.setTextColor(getResources().getColor(R.color.c_000000));
        }
        aimView.setTextColor(getResources().getColor(R.color.c_fd504e));
    }

    @Override
    public void doBusiness() {
        setMarket();
        onem_tv.performClick();
//        presenter.get1MinLineData();
//        presenter.getOldKLineData(presenter.select_ml.getName(), 1, 500 * 60);
//        Observable.just("").observeOn(Schedulers.io()).map(new Func1<String, List<KLineEntity>>() {
//            @Override
//            public List<KLineEntity> call(String data) {
//                try {
//                    List<KLineEntity> datas = DataRequest.getALL(TradeChat3Act.this).subList(0, 500);
//                    DataHelper.calculate(datas);
//                    return datas;
//                } catch (Exception e) {
//                    return new ArrayList<KLineEntity>();
//                }
//            }
//        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<List<KLineEntity>>() {
//            @Override
//            public void call(List<KLineEntity> datas) {
//                adapter.addFooterData(datas);
//                adapter.notifyDataSetChanged();
//                kLineChartView.startAnimation();
//                kLineChartView.refreshEnd();
//            }
//        });
    }


    @Override
    public void klineshowloading() {
        kLineChartView.justShowLoading();
    }

    @Override
    public void setKlineData(List<KLineEntity> datas) {
        adapter.addFooterData(datas);
        adapter.notifyDataSetChanged();
        kLineChartView.startAnimation();
        kLineChartView.refreshEnd();
        klineReady = true;
    }

    @Override
    public void klineDatanotify(String status, KLineEntity kLineEntity) {
        switch (status) {
            case "0":
                adapter.changeLastItem(kLineEntity);
                break;
            case "1":
                adapter.addHeaderDataSinal(kLineEntity);
                break;
        }
        adapter.notifyDataSetChanged();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void doTradeJsonEvent(TradeJsonEvent tradeJsonEvent) {
        if (tradeJsonEvent != null) {
//            presenter.setmData(tradeJsonEvent.kLineEntities);
        }
    }


    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void doMarketListEvent(MarketListEvent marketListEvent) {
        if (marketListEvent != null) {
            presenter.doHandleMarket(marketListEvent.getMarketListEntities());
            if (klineReady) {
                presenter.doHandleKline(marketListEvent.resData);
            }
        }
    }


    @Override
    public void setMarket() {
        if (presenter.select_ml != null) {
            ToolUtils.setMarketUpDownTvColor(presenter.select_ml.getChange(), np_tv);
            ToolUtils.setMarketUpDownTvColor(presenter.select_ml.getChange(), cnyp_tv);

            np_tv.setText(ToolUtils.SzzcFormat(presenter.select_ml.getNew_price()));
            highp_tv.setText(ToolUtils.SzzcFormat(presenter.select_ml.getMax_price()));
            lowp_tv.setText(ToolUtils.SzzcFormat(presenter.select_ml.getMin_price()));
            tradeamount_tv.setText(ToolUtils.SzzcFormat(presenter.select_ml.getVolume_day()));

            cny_tv.setText(String.format(getString(R.string.fbprice_data), ToolUtils.CnyFormat(presenter.select_ml.getCny_price()) + ToolUtils.getCurrency(getMContext())));

            double c = ToolUtils.String2Double(presenter.select_ml.getChange());
            if (c >= 0) {
                cnyp_tv.setText("+ " + ToolUtils.doublePoint(c) + "%");
            } else {
                cnyp_tv.setText(ToolUtils.doublePoint(c) + "%");
            }
        }
    }


    /**
     * 计算大概的法币价格
     */
    public String GFbPrice(MarketListEntity entity) {
        String s = ToolUtils.multiplyWithScale(ACache.get(getMContext()).getAsString(ACEConstant.PRICE), entity.getNew_price(), 2);
        return s + ToolUtils.getCurrency(getMContext());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        try {
            mDataService.leaveKLineTrade(presenter.select_ml.getName(), "1");
        } catch (Exception e) {
        }
    }

    @Override
    public void onBackPressed() {
        //判断当前屏幕方向
        if (getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            //切换竖屏
            this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

    }

    public class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getString(R.string.tradechat_deep);
                case 1:
                    return getString(R.string.tradechat_transaction);
                case 2:
                    return getString(R.string.tradechat_introduction);
            }
            return "";
        }


    }
}
