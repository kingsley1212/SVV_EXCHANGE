package com.svv.jys.code.module.market.base;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.mvp.MvpFragment;
import com.svv.jys.code.common.entity.MarketListEntity;
import com.svv.jys.code.common.entity.MarketTitleEntity;
import com.svv.jys.code.common.utils.StatusBarUtils;
import com.svv.jys.code.common.utils.T;
import com.svv.jys.code.module.market.base.model.IMarketBusinessModel;
import com.svv.jys.code.module.market.base.presenter.MarketBusinessPresenter;
import com.svv.jys.code.module.market.base.view.IMarketBusinessView;
import com.svv.jys.code.module.market.list.MarketListFrag;
import com.svv.jys.code.module.market.marketintroduce.MarketIntroduceAct;
import com.svv.jys.code.module.server.dataserver.event.MarketListEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/4/27 0027.
 */

public class MarketBusinessFragment extends MvpFragment<IMarketBusinessView, IMarketBusinessModel,
        MarketBusinessPresenter> implements IMarketBusinessView {
    private TextView toMainAreaIntroduce;
    private TextView marketTab1, marketTab2;
    private View marketTab1_line, marketTab2_line;
    private ViewPager market_vp;
    private RecyclerView rvMarketList;
    private TabLayout tab_market;
    private List<Fragment> fragments = new ArrayList<>();
    private int fposition;
    private int lastTimePosition;
    private boolean isRefresh = false;
    private View coin_ll, price_ll, up_down_ll;
    private ImageView up_down_iv, price_iv, coin_iv;
    private int sort_status = -1;
    List<ArrayList<MarketListEntity>> list;

    @Override
    public MarketBusinessPresenter initPresenter() {
        return new MarketBusinessPresenter();
    }

    @Override
    public void onWakeBussiness() {
        isRefresh = false;
        presenter.getMarketTitle();
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
    }

    @Override
    public int setR_Layout() {
        return R.layout.fragment_market;
    }

    @Override
    public void viewInitialization() {
        toMainAreaIntroduce = (TextView) $(R.id.toMainAreaIntroduce);
        toMainAreaIntroduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoActivity(MarketIntroduceAct.class);
            }
        });
        up_down_ll = $(R.id.up_down_ll);
        coin_ll = $(R.id.coin_ll);
        price_ll = $(R.id.price_ll);
        coin_iv = (ImageView) $(R.id.coin_iv);
        price_iv = (ImageView) $(R.id.price_iv);
        up_down_iv = (ImageView) $(R.id.up_down_iv);
        market_vp = (ViewPager) $(R.id.market_vp);
        tab_market = (TabLayout) $(R.id.tab_market);
        //第一层tab栏
        marketTab1 = (TextView) $(R.id.marketTab1);
        marketTab2 = (TextView) $(R.id.marketTab2);
        marketTab1_line = $(R.id.marketTab1_line);
        marketTab2_line = $(R.id.marketTab2_line);
        marketTab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstLevelChangeTab(1);
            }
        });
        marketTab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstLevelChangeTab(2);
            }
        });
        rvMarketList = (RecyclerView) $(R.id.rvMarketList);
        rvMarketList.setLayoutManager(new LinearLayoutManager(getMContext()));
        up_down_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sort_status != 2) {
                    reset();
                    up_down_iv.setImageResource(R.drawable.btn_sort);
                }
                sort_status = 2;
                up_down_iv.setSelected(!up_down_iv.isSelected());
                ((MarketListFrag) fragments.get(fposition)).setAdapterStatus(sort_status, up_down_iv.isSelected());
            }
        });
        up_down_ll.setClickable(false);
        coin_ll.setClickable(false);
        price_ll.setClickable(false);
        coin_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sort_status != 0) {
                    reset();
                    coin_iv.setImageResource(R.drawable.btn_sort);
                }
                sort_status = 0;
                coin_iv.setSelected(!coin_iv.isSelected());
                ((MarketListFrag) fragments.get(fposition)).setAdapterStatus(sort_status, coin_iv.isSelected());
            }
        });
        price_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sort_status != 1) {
                    reset();
                    price_iv.setImageResource(R.drawable.btn_sort);
                }
                sort_status = 1;
                price_iv.setSelected(!price_iv.isSelected());
                ((MarketListFrag) fragments.get(fposition)).setAdapterStatus(sort_status, price_iv.isSelected());
            }
        });
    }

    public void reset() {
        switch (sort_status) {
            case 0:
                coin_iv.setImageResource(R.drawable.ic_sort);
                coin_iv.setSelected(true);
                break;
            case 1:
                price_iv.setImageResource(R.drawable.ic_sort);
                price_iv.setSelected(true);
                break;
            case 2:
                up_down_iv.setImageResource(R.drawable.ic_sort);
                up_down_iv.setSelected(true);
                break;
        }
    }

    @Override
    public void doBusiness() {
        isRefresh = false;
        presenter.getMarketTitle();
        marketTab1.performClick();

    }


    /**
     * @param tab 1--2
     */
    private void firstLevelChangeTab(int tab) {
        switch (tab) {
            case 1:
                marketTab1.setSelected(true);
                marketTab2.setSelected(false);
                marketTab1_line.setSelected(true);
                marketTab2_line.setSelected(false);
                break;
            case 2:
                marketTab1.setSelected(false);
                marketTab2.setSelected(true);
                marketTab1_line.setSelected(false);
                marketTab2_line.setSelected(true);
                break;
            default:
                break;
        }
    }

    List<MarketListEntity> entities;

    @Override
    public Context getMContext() {
        return getContext();
    }

    @Override
    public void setMarketTitle(MarketTitleEntity entity) {

        fragments.clear();
        list = entity.getMarket();
        list.add(0, entity.getCollect());
        for (int i = 0; i < list.size(); i++) {
            fragments.add(MarketListFrag.newInstance(list.get(i), entity.getCoin()));
        }
        fposition = 0;
        entities = list.get(0);
        MyAdapter myAdapter = new MyAdapter(getChildFragmentManager(), list);
        market_vp.setAdapter(myAdapter);
        market_vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                entities = list.get(position);
                fposition = position;
                lastTimePosition = position;
                switch (sort_status) {
                    case 0:
                        ((MarketListFrag) fragments.get(fposition)).setAdapterStatus(sort_status, coin_iv.isSelected());
                        break;
                    case 1:
                        ((MarketListFrag) fragments.get(fposition)).setAdapterStatus(sort_status, price_iv.isSelected());
                        break;
                    case 2:
                        ((MarketListFrag) fragments.get(fposition)).setAdapterStatus(sort_status, up_down_iv.isSelected());
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        tab_market.setTabMode(TabLayout.MODE_SCROLLABLE);
        tab_market.setupWithViewPager(market_vp);
        up_down_ll.setClickable(true);
        coin_ll.setClickable(true);
        price_ll.setClickable(true);
//        }else {
//            List<ArrayList<MarketListEntity>> list = entity.getMarket();
//            list.add(0, entity.getCollect());
//            for (int i = 0; i < list.size(); i++) {
//                ((MarketListFrag)fragments.get(i)).setMarketList(list.get(i));
//            }
//            entities = list.get(fposition);
//        }
        try{
            //防止数据变化，导致角标越界闪退
            tab_market.getTabAt(lastTimePosition).select();
        }catch (Exception e){
            T.showShort(getMContext(),"请退出APP重新进入");
        }
        isRefresh = true;
    }

    @Override
    public void setMarketList(List<MarketListEntity> list) {

    }

    @Override
    public List<MarketListEntity> getNowShowMarket() {
        if (entities != null) {
            return entities;
        }
        return new ArrayList<MarketListEntity>();
    }

    @Override
    public void setMarket(List<MarketListEntity> list) {
        ((MyAdapter) market_vp.getAdapter()).setMarketList(list);
    }


    public class MyAdapter extends FragmentStatePagerAdapter {

        private List<ArrayList<MarketListEntity>> list;

        public MyAdapter(FragmentManager fm, List<ArrayList<MarketListEntity>> list) {
            super(fm);
            this.list = list;

        }

        public void setList(List<ArrayList<MarketListEntity>> list) {
            this.list = list;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if (position == 0) {
                return getString(R.string.SlideMenuAct_zixuan);
            } else
                return presenter.marketEntity.getCoin_name()
                        .get(position - 1).toUpperCase();
        }

        public void setMarketList(List<MarketListEntity> list) {
            ((MarketListFrag) fragments.get(fposition)).setMarketList(list);
        }


    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void doMarketListEvent(MarketListEvent marketListEvent) {
        if (marketListEvent != null && isRefresh) {
            presenter.doHandleMarketList(marketListEvent);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
