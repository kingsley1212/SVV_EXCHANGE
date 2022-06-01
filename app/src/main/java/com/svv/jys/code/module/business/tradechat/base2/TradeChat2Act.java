package com.svv.jys.code.module.business.tradechat.base2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.mvp.MvpDataSocketActivity;
import com.svv.jys.code.common.constant.ACEConstant;
import com.svv.jys.code.common.db.nativecache.ACache;
import com.svv.jys.code.common.entity.MarketListEntity;
import com.svv.jys.code.common.utils.ResourceUtils;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.common.utils.WebViewUtil;
import com.svv.jys.code.common.view.selfview.MyViewPager;
import com.svv.jys.code.module.business.tradechat.base2.model.ITradeChat2Model;
import com.svv.jys.code.module.business.tradechat.base2.presenter.TradeChat2Presenter;
import com.svv.jys.code.module.business.tradechat.base2.view.ITradeChat2View;
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

public class TradeChat2Act extends MvpDataSocketActivity<ITradeChat2View, ITradeChat2Model, TradeChat2Presenter>
        implements
        ITradeChat2View {
    private TextView toBuy, toSell;

    public static void startAct(Context mContext, MarketListEntity entity) {
        Intent intent = new Intent(mContext, TradeChat2Act.class);
        intent.putExtra("MarketListEntity", entity);
        ((Activity) mContext).startActivityForResult(intent, 500);
    }

    private TextView  np_tv, highp_tv, lowp_tv, tradeamount_tv, cny_tv, cnyp_tv;
    private WebView webview_chart;
    private LinearLayout content_ll;
    private TabLayout tab_layout;
    private List<Fragment> fragments = new ArrayList<>();
    private MyViewPager view_pager;


    @Override
    public Context getMContext() {
        return this;
    }

    @Override
    public TradeChat2Presenter initPresenter() {
        return new TradeChat2Presenter();
    }

    @Override
    public void baseInitialization() {
        presenter.getDataFromIntent(getIntent());
        EventBus.getDefault().register(this);

    }

    @Override
    public void cSConttectSeccuss() {
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
        return R.layout.act_tradechat2;
    }

    @Override
    public void viewInitialization() {
        setBackPress();
        view_pager = (MyViewPager) $(R.id.view_pager);
        setTitleTx(presenter.select_ml.getName().replace("_", "/").toUpperCase());
        content_ll = (LinearLayout) $(R.id.content_ll);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ResourceUtils.dip2px2(getMContext(),410));
        webview_chart = new WebView(this.getApplicationContext());
        webview_chart.setLayoutParams(params);
        webview_chart.setBackgroundColor(0);


        webview_chart.setOnTouchListener(new View.OnTouchListener() {
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

        content_ll.addView(webview_chart,1);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
//        WebViewUtil.getInstance().initWebView(webview_chart, this, NET_URL.getInstance().getUrlPerfix() +
//                "app/exchange/exchange_tradejson/view/market/" + presenter.select_ml.getName());
        String lang="";
        switch (ACache.get(this).getAsString(ACEConstant.CURRENTLANGUAGE_ID)) {
            case ACEConstant.LANGUAGE_SIMPLIFIED_CHINESE:
                lang="zh_cn";
                break;
            case ACEConstant.LANGUAGE_TRADITIONAL_CHINESE:
                lang="zh_tw";
                break;
            case ACEConstant.LANGUAGE_ENGLISH:
                lang="en";
                break;
        }
        WebViewUtil.getInstance().initLocalWebView(webview_chart, this, presenter.select_ml.getName(),lang);

        toBuy = (TextView) $(R.id.toBuy);
        toSell = (TextView) $(R.id.toSell);

        np_tv = (TextView) $(R.id.np_tv);
        highp_tv = (TextView) $(R.id.highp_tv);
        lowp_tv = (TextView) $(R.id.lowp_tv);
        tradeamount_tv = (TextView) $(R.id.tradeamount_tv);
        cny_tv = (TextView) $(R.id.cny_tv);
        cnyp_tv = (TextView) $(R.id.cnyp_tv);
        tab_layout = (TabLayout) $(R.id.tab_layout);
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

    }


    @Override
    public void doBusiness() {
        setMarket();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void doTradeJsonEvent(TradeJsonEvent tradeJsonEvent) {
        if (tradeJsonEvent != null) {
                presenter.setmData(tradeJsonEvent.kLineEntities);

        }
    }


    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void doMarketListEvent(MarketListEvent marketListEvent) {
        if (marketListEvent != null) {
            presenter.doHandleMarket(marketListEvent.getMarketListEntities());
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

            cny_tv.setText(String.format(getString(R.string.fbprice_data),ToolUtils.CnyFormat(presenter.select_ml.getCny_price())+ToolUtils.getCurrency(getMContext())));

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
        String s = ToolUtils.multiplyWithScale(ACache.get(getMContext()).getAsString(ACEConstant.PRICE),entity.getNew_price(), 2);
        return s + ToolUtils.getCurrency(getMContext());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
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
          switch (position){
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
