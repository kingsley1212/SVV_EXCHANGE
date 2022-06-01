package com.svv.jys.code.module.main;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.mvp.BaseFragment;
import com.svv.jys.code.common.base.mvp.MvpDataSocketActivity;
import com.svv.jys.code.common.constant.ACEConstant;
import com.svv.jys.code.common.db.nativecache.ACache;
import com.svv.jys.code.common.entity.MarketListEntity;
import com.svv.jys.code.common.utils.StatusBarUtils;
import com.svv.jys.code.common.utils.T;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.business.barbusiness.base.BarBusinessFragment;
import com.svv.jys.code.module.business.coinbusiness.base.CoinBusinessFragment;
import com.svv.jys.code.module.fabi.base.FabiFrag;
import com.svv.jys.code.module.fabi.screen.ScreenAct;
import com.svv.jys.code.module.home.base.HomeFragment;
import com.svv.jys.code.module.main.model.IMainModel;
import com.svv.jys.code.module.main.presenter.MainPresenter;
import com.svv.jys.code.module.main.view.IMainView;
import com.svv.jys.code.module.market.base.MarketBusinessFragment;
import com.svv.jys.code.module.myself.base.MySelfFragment;
import com.svv.jys.code.module.net.req.GET_OTC_ADV_REQ;

public class MainAct extends MvpDataSocketActivity<IMainView, IMainModel, MainPresenter> implements IMainView {

    private final long FINISH_WAIT = 2000;//退出app间隔时间
    private long timeMillis;//退出app当前间隔
    private final String SHOW_TAB1 = "SHOW_TAB1";
    private final String SHOW_TAB2 = "SHOW_TAB2";
    private final String SHOW_TAB3 = "SHOW_TAB3";
    private final String SHOW_TAB6 = "SHOW_TAB6";
    private final String SHOW_TAB4 = "SHOW_TAB4";
    private final String SHOW_TAB5 = "SHOW_TAB5";


    private View tab1_ly, tab2_ly, tab3_ly, tab4_ly, tab5_ly, tab6_ly;
    private ImageView tab1_img, tab2_img, tab3_img, tab4_img, tab5_img, tab6_img;
    private TextView tab1_text, tab2_text, tab3_text, tab4_text, tab5_text, tab6_text;
    private FrameLayout fragment_content;

    private BaseFragment tempFragment;
    private HomeFragment homeFragment;//首页
    private CoinBusinessFragment coinBusinessFragment;//币交易
    private BarBusinessFragment barBusinessFragment;//杠杆交易
    private MarketBusinessFragment marketBusinessFragment;//行情版面
    private MySelfFragment mySelfFragment;//我的版面
    //    private OtcBussinessFragment otcBussinessFragment;//法币交易
    private FabiFrag otcBussinessFragment;//法币交易
    private TextView tv_ceshi;//是否测试
    private MarketListEntity entity;


    private Boolean bol = false;
    private int requestCode, resultCode;
    private Intent data;

    public String getSHOW_TAB() {
        return SHOW_TAB4;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (mySelfFragment != null) {
            mySelfFragment.onActivityResult(requestCode, resultCode, data);
        }

        if (resultCode == 0) {
            return;
        }
        if (requestCode == ScreenAct.REQUEST_SCREEN) {
            if (resultCode == ScreenAct.SUCCESS_RESULT) {
                GET_OTC_ADV_REQ req = (GET_OTC_ADV_REQ) data.getSerializableExtra("GET_OTC_ADV_REQ");
                otcBussinessFragment.setChildReq(req);
            } else {
                return;
            }
        }

        if (requestCode == 500) {//k线图
            switch (resultCode) {
                case 501://买入
                    if (coinBusinessFragment != null && coinBusinessFragment.getUserVisibleHint()) {
//                        coinBusinessFragment.buySellTabChange(POST_BARBUSINESS_SUBMIT_REQ.P_BUY);
                        tab3_ly.performClick();
                        coinBusinessFragment.onActivityResult(requestCode, resultCode, data);
                    }/* else if (barBusinessFragment != null && barBusinessFragment.getUserVisibleHint()) {
                        barBusinessFragment.buySellTabChange(POST_BARBUSINESS_SUBMIT_REQ.P_BUY);
                        barBusinessFragment.onActivityResult(requestCode, resultCode, data);
                        tab3_ly.performClick();
                    } */ else {
                        entity = (MarketListEntity) data.getSerializableExtra("select_ml");
                        tab3_ly.performClick();
                    }
                    break;
                case 502://卖出
                    if (coinBusinessFragment != null && coinBusinessFragment.getUserVisibleHint()) {
//                        coinBusinessFragment.buySellTabChange(POST_BARBUSINESS_SUBMIT_REQ.P_SELL);
                        coinBusinessFragment.onActivityResult(requestCode, resultCode, data);
                        tab3_ly.performClick();
                    }/* else if (barBusinessFragment != null && barBusinessFragment.getUserVisibleHint()) {
                        barBusinessFragment.buySellTabChange(POST_BARBUSINESS_SUBMIT_REQ.P_SELL);
                        barBusinessFragment.onActivityResult(requestCode, resultCode, data);
                        tab3_ly.performClick();
                    }*/ else {
                        entity = (MarketListEntity) data.getSerializableExtra("select_ml");
                        tab3_ly.performClick();
//                        this.requestCode = requestCode;
//                        this.resultCode = resultCode;
//                        this.data = data;
                    }
                    break;

            }
        }
//        if(coinBusinessFragment!=null){
//            coinBusinessFragment.onActivityResult(requestCode, resultCode, data);
//        }
//        if(mySelfFragment!=null){
//            mySelfFragment.onActivityResult(requestCode, resultCode, data);
//        }
    }

    @Override
    public MainPresenter initPresenter() {
        return new MainPresenter();
    }

    @Override
    public void baseInitialization() {
        ACache.get(this).remove(ACEConstant.ACE_MARKET_ENTITY);
        if (!ToolUtils.isNull(ACache.get(this).getAsString("succese"))) {
            if (ACache.get(this).getAsString("succese").equals("1")) {
                ToolUtils.logout(this);
            }
        }

    }

    @Override
    public int setR_Layout() {
        return R.layout.act_main;
    }

    @Override
    public void viewInitialization() {
        StatusBarUtils.setImmersiveStatusBar(this, true);
        tv_ceshi = findViewById(R.id.tv_ceshi);
        //底部tab
        tab1_ly = $(R.id.tab1_ly);
        tab2_ly = $(R.id.tab2_ly);
        tab3_ly = $(R.id.tab3_ly);
        tab4_ly = $(R.id.tab4_ly);
        tab5_ly = $(R.id.tab5_ly);
        tab6_ly = $(R.id.tab6_ly);
        tab1_img = (ImageView) $(R.id.tab1_img);
        tab2_img = (ImageView) $(R.id.tab2_img);
        tab3_img = (ImageView) $(R.id.tab3_img);
        tab4_img = (ImageView) $(R.id.tab4_img);
        tab5_img = (ImageView) $(R.id.tab5_img);
        tab6_img = (ImageView) $(R.id.tab6_img);
        tab1_text = (TextView) $(R.id.tab1_text);
        tab2_text = (TextView) $(R.id.tab2_text);
        tab3_text = (TextView) $(R.id.tab3_text);
        tab4_text = (TextView) $(R.id.tab4_text);
        tab5_text = (TextView) $(R.id.tab5_text);
        tab6_text = (TextView) $(R.id.tab6_text);
        tab1_ly.setOnClickListener(new TabClicklistener(SHOW_TAB1));
        tab2_ly.setOnClickListener(new TabClicklistener(SHOW_TAB2));
        tab3_ly.setOnClickListener(new TabClicklistener(SHOW_TAB3));
        tab4_ly.setOnClickListener(new TabClicklistener(SHOW_TAB4));
        tab5_ly.setOnClickListener(new TabClicklistener(SHOW_TAB5));
        tab6_ly.setOnClickListener(new TabClicklistener(SHOW_TAB6));

        //
        fragment_content = (FrameLayout) $(R.id.fragment_content);

    }

    private String TAB1 = "TAB1";
    private String TAB2 = "TAB2";
    private String TAB3 = "TAB3";
    private String TAB4 = "TAB4";
    private String TAB5 = "TAB5";

    @Override
    public void doBusiness() {
        if (savedInstanceState != null) {
            homeFragment = (HomeFragment) getSupportFragmentManager().getFragment(savedInstanceState, TAB1);
            marketBusinessFragment = (MarketBusinessFragment) getSupportFragmentManager().getFragment(savedInstanceState, TAB2);
            coinBusinessFragment = (CoinBusinessFragment) getSupportFragmentManager().getFragment(savedInstanceState, TAB3);
            otcBussinessFragment = (FabiFrag) getSupportFragmentManager().getFragment(savedInstanceState, TAB4);
            mySelfFragment = (MySelfFragment) getSupportFragmentManager().getFragment(savedInstanceState, TAB5);
        }
        tab1_ly.performClick();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        try {
            int id = intent.getIntExtra("id", 1);
            if (id == 2) {
                showFragment(SHOW_TAB2);
                showTab(SHOW_TAB2);
            } else if (id == 3) {
                showFragment(SHOW_TAB3);
                showTab(SHOW_TAB3);
            }
        } catch (Exception e) {
        }

    }

//    /**
//     * 聊天服务链接成功
//     */
//    @Override
//    public void chatConttectSeccuss() {
//    }
//
//    /**
//     * 聊天服务链接失败
//     */
//    @Override
//    public void chatConttectFail() {
//
//    }

    /**
     * 聊天服务链接失败
     */
    @Override
    public void cSConttectSeccuss() {

    }

    /**
     * 聊天服务链接失败
     */
    @Override
    public void cSConttectFail() {

    }


    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - timeMillis >= FINISH_WAIT) {
            T.showShort(getMContext(), getString(R.string.again_finish));
            timeMillis = System.currentTimeMillis();
        } else {
//            super.onBackPressed();
            ACache.get(getMContext()).remove(ACEConstant.ACE_MARKET_ENTITY);
            System.exit(0);
        }
    }


    @Override
    public Context getMContext() {
        return this;
    }

    public void showFragment(String tabPosition) {
        if (tabPosition == null) {
            return;
        }
        switch (tabPosition) {
            case SHOW_TAB1:
                onFragmentChangeSelected(1);
                break;
            case SHOW_TAB2:
                onFragmentChangeSelected(2);
                break;
            case SHOW_TAB3:
                onFragmentChangeSelected(3);
                break;
            case SHOW_TAB4:
                onFragmentChangeSelected(4);
                break;
            case SHOW_TAB5:
                onFragmentChangeSelected(5);
                break;
            case SHOW_TAB6:
                onFragmentChangeSelected(6);
                break;
            default:
                break;
        }
    }

    private void onFragmentChangeSelected(int position) {
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        switch (position) {
            case 1:
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                }
                if (homeFragment.isAdded()) {
                    transaction.hide(tempFragment).show(homeFragment).commit();
                } else {
                    if (tempFragment == null) {
                        tempFragment = homeFragment;
                        transaction.add(R.id.fragment_content, homeFragment).commit();
                    } else {
                        transaction.hide(tempFragment).add(R.id.fragment_content, homeFragment).commit();
                    }
                    homeFragment.isHidden = false;
                }
                tempFragment = homeFragment;
                break;
            case 3:
                if (coinBusinessFragment == null) {
                    coinBusinessFragment = CoinBusinessFragment.newInstance(entity);
                }
                if (coinBusinessFragment.isAdded()) {
                    transaction.hide(tempFragment).show(coinBusinessFragment).commit();
                } else {
                    if (tempFragment == null) {
                        tempFragment = coinBusinessFragment;
                        transaction.add(R.id.fragment_content, coinBusinessFragment).commit();
                    } else {
                        transaction.hide(tempFragment).add(R.id.fragment_content, coinBusinessFragment).commit();
                    }
                    coinBusinessFragment.isHidden = false;
                }
                tempFragment = coinBusinessFragment;
//                if (!bol) {
//                    bol = true;
//                    new Handler().postDelayed(new Runnable() {
//                        public void run() {
//                            coinBusinessFragment.onActivityResult(requestCode, resultCode, data);
//                        }
//                    }, 800);
//
//                }
                break;
            case 6:
                if (barBusinessFragment == null) {
                    barBusinessFragment = new BarBusinessFragment();
                }
                if (barBusinessFragment.isAdded()) {
                    transaction.hide(tempFragment).show(barBusinessFragment).commit();
                } else {
                    if (tempFragment == null) {
                        tempFragment = barBusinessFragment;
                        transaction.add(R.id.fragment_content, barBusinessFragment).commit();
                    } else {
                        transaction.hide(tempFragment).add(R.id.fragment_content, barBusinessFragment).commit();
                    }
                    barBusinessFragment.isHidden = false;
                }
                tempFragment = barBusinessFragment;
                break;
            case 2:
                if (marketBusinessFragment == null) {
                    marketBusinessFragment = new MarketBusinessFragment();
                }
                if (marketBusinessFragment.isAdded()) {
                    transaction.hide(tempFragment).show(marketBusinessFragment).commit();
                } else {
                    if (tempFragment == null) {
                        tempFragment = marketBusinessFragment;
                        transaction.add(R.id.fragment_content, marketBusinessFragment).commit();
                    } else {
                        transaction.hide(tempFragment).add(R.id.fragment_content, marketBusinessFragment).commit();
                    }
                    marketBusinessFragment.isHidden = false;
                }
                tempFragment = marketBusinessFragment;
                break;
            case 5:
                if (mySelfFragment == null) {
                    mySelfFragment = new MySelfFragment();
                }
                if (mySelfFragment.isAdded()) {
                    transaction.hide(tempFragment).show(mySelfFragment).commit();
                } else {
                    if (tempFragment == null) {
                        tempFragment = mySelfFragment;
                        transaction.add(R.id.fragment_content, mySelfFragment).commit();
                    } else {
                        transaction.hide(tempFragment).add(R.id.fragment_content, mySelfFragment).commit();
                    }
                    mySelfFragment.isHidden = false;
                }
                tempFragment = mySelfFragment;
                break;
            case 4:
                if (otcBussinessFragment == null) {
                    otcBussinessFragment = new FabiFrag();
                }
                if (otcBussinessFragment.isAdded()) {
                    transaction.hide(tempFragment).show(otcBussinessFragment).commit();
                } else {
                    if (tempFragment == null) {
                        tempFragment = otcBussinessFragment;
                        transaction.add(R.id.fragment_content, otcBussinessFragment).commit();
                    } else {
                        transaction.hide(tempFragment).add(R.id.fragment_content, otcBussinessFragment).commit();
                    }
                    otcBussinessFragment.isHidden = false;
                }
                tempFragment = otcBussinessFragment;
                break;
        }
    }

    public void showTab(String tabPosition) {
        if (tabPosition == null) {
            return;
        }
        tab1_img.setSelected(false);
        tab2_img.setSelected(false);
        tab3_img.setSelected(false);
        tab4_img.setSelected(false);
        tab5_img.setSelected(false);
        tab6_img.setSelected(false);
        tab1_text.setSelected(false);
        tab2_text.setSelected(false);
        tab3_text.setSelected(false);
        tab4_text.setSelected(false);
        tab5_text.setSelected(false);
        tab6_text.setSelected(false);
        switch (tabPosition) {
            case SHOW_TAB1:
                tab1_img.setSelected(true);
                tab1_text.setSelected(true);
                break;
            case SHOW_TAB2:
                tab2_img.setSelected(true);
                tab2_text.setSelected(true);
                break;
            case SHOW_TAB3:
                tab3_img.setSelected(true);
                tab3_text.setSelected(true);
                break;
            case SHOW_TAB4:
                tab4_img.setSelected(true);
                tab4_text.setSelected(true);
                break;
            case SHOW_TAB5:
                tab5_img.setSelected(true);
                tab5_text.setSelected(true);
                break;
            case SHOW_TAB6:
                tab6_img.setSelected(true);
                tab6_text.setSelected(true);
                break;
            default:
                break;
        }
    }

    @Override
    public void isCeshi(String s) {
//        if (s.equals("true")) {
//            tv_ceshi.setVisibility(View.VISIBLE);
//        } else {
//            tv_ceshi.setVisibility(View.GONE);
//        }
    }


    private class TabClicklistener implements View.OnClickListener {
        /**
         * 选择第几个tab
         */
        private String tabPosition = "";

        public TabClicklistener(String tabPosition) {
            this.tabPosition = tabPosition;
        }

        @Override
        public void onClick(View v) {
            showFragment(tabPosition);
            showTab(tabPosition);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        backgroundAlpha(0.5f);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (TextUtils.isEmpty(ACache.get(MainAct.this).getAsString(ACEConstant.home_dialog))) {
            checkWindowAlerPer();
        }
        backgroundAlpha(1f);
//        presenter.isCeshi();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            FragmentManager fragmentManager = this.getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            if (homeFragment.isAdded()) {
                transaction.remove(homeFragment);
            }
            if (coinBusinessFragment.isAdded()) {
                transaction.remove(coinBusinessFragment);
            }
            if (otcBussinessFragment.isAdded()) {
                transaction.remove(otcBussinessFragment);
            }
            if (marketBusinessFragment.isAdded()) {
                transaction.remove(marketBusinessFragment);
            }
            if (mySelfFragment.isAdded()) {
                transaction.remove(mySelfFragment);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (homeFragment != null) {
            getSupportFragmentManager().putFragment(outState, TAB1, homeFragment);
        }
        if (marketBusinessFragment != null) {
            getSupportFragmentManager().putFragment(outState, TAB2, marketBusinessFragment);
        }
        if (coinBusinessFragment != null) {
            getSupportFragmentManager().putFragment(outState, TAB3, coinBusinessFragment);
        }
        if (otcBussinessFragment != null) {
            getSupportFragmentManager().putFragment(outState, TAB4, otcBussinessFragment);
        }
        if (mySelfFragment != null) {
            getSupportFragmentManager().putFragment(outState, TAB5, mySelfFragment);
        }
        super.onSaveInstanceState(outState);
    }

    /**
     * 设置背景透明度
     *
     * @param bgAlpha
     */
    private void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow()
                .getAttributes();
        lp.alpha = bgAlpha; // 0.0-1.0
        getWindow().setAttributes(lp);
    }

    /**
     * 检查是否获取了弹框权限
     */
    public void checkWindowAlerPer() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (!Settings.canDrawOverlays(getApplicationContext())) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                AlertDialog showWindowMessage = builder.setMessage(R.string.mainact_bounced_permissions)
                        .setPositiveButton(R.string.mainact_to_open, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                //启动Activity让用户授权
                                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                                startActivity(intent);
                                return;

                            }
                        }).setNegativeButton(R.string.no_tishi, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ACache.get(MainAct.this).put(ACEConstant.home_dialog, "1");
                            }
                        })
                        .create();
                showWindowMessage.show();
            }
        }
    }

}
