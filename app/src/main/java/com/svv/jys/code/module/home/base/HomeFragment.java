package com.svv.jys.code.module.home.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.sunfusheng.marqueeview.MarqueeView;
import com.svv.jys.R;
import com.svv.jys.code.common.base.mvp.MvpFragment;
import com.svv.jys.code.common.entity.FUserInfoEntity;
import com.svv.jys.code.common.entity.IndexEntity;
import com.svv.jys.code.common.entity.MarketListEntity;
import com.svv.jys.code.common.utils.StatusBarUtils;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.common.view.thirdview.banner.Banner;
import com.svv.jys.code.common.view.thirdview.banner.BannerConfig;
import com.svv.jys.code.common.view.thirdview.banner.Transformer;
import com.svv.jys.code.module.home.article.ArticleAct;
import com.svv.jys.code.module.home.base.adapter.HP_MarketListAdapter;
import com.svv.jys.code.module.home.base.adapter.MarketAdapter;
import com.svv.jys.code.module.home.base.adapter.MyBannerViewHolder;
import com.svv.jys.code.module.home.base.model.IHomeModel;
import com.svv.jys.code.module.home.base.presenter.HomePresenter;
import com.svv.jys.code.module.home.base.view.IHomeView;
import com.svv.jys.code.module.home.message_center.MessageCenterAct;
import com.svv.jys.code.module.main.MainAct;
import com.svv.jys.code.module.myself.qrcode.QRCodeAct;
import com.svv.jys.code.module.net.exception.NeedLoginException;
import com.svv.jys.code.module.server.dataserver.event.MarketListEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/4/27 0027.
 */

public class HomeFragment extends MvpFragment<IHomeView, IHomeModel, HomePresenter> implements IHomeView {

    private RecyclerView market_rv;
    private Banner banner;
    private MarqueeView marqueeView;

    private MarketAdapter marketAdapter;

    private boolean marketDataFlag = true;
    private Unbinder unbinder;
    private ViewPager market_vp;
    private int positions = 0;
    private HP_MarketListAdapter marketListAdapter;
    private List<MarketListEntity> marketListEntities = new ArrayList<>();

    @Override
    public HomePresenter initPresenter() {
        return new HomePresenter();
    }

    @Override
    public void onWakeBussiness() {
        presenter.getIndexData();
        StatusBarUtils.setImmersiveStatusBar(getActivity(), false);
    }

    @Override
    public void onResume() {
        super.onResume();
        StatusBarUtils.setImmersiveStatusBar(getActivity(), false);
    }

    //唤醒
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        isHidden = hidden;
        if (presenter == null) {
            return;
        }
        if (banner != null) {
            if (!isHidden) {
                //开始轮播
                banner.startAutoPlay();
            } else {
                banner.stopAutoPlay();
            }
        }
        if (!hidden && wakeListener) {
            onWakeBussiness();
        }
    }


    @Override
    public void setMarketVp(final List<List<MarketListEntity>> marketListEntities) {
        market_vp.post(new Runnable() {
            @Override
            public void run() {
                marketListAdapter = new HP_MarketListAdapter(getMContext());

                marketListAdapter.setList(marketListEntities);
                market_vp.setAdapter(marketListAdapter);
                marketListAdapter.notifyDataSetChanged();
                marketListAdapter.setOnItemClick(new HP_MarketListAdapter.OnItemClick() {
                    @Override
                    public void onItemClick(MarketListEntity entity) {
//                        TradeChat2Act.startAct(getMContext(), entity);
                        ToolUtils.startKLineAct(getMContext(), entity);

                    }
                });
                market_vp.setCurrentItem(positions);
            }
        });


    }

    @Override
    public void baseInitialization() {
        EventBus.getDefault().register(this);
    }

    @Override
    public int setR_Layout() {
        return R.layout.fragment_home;
    }

    @Override
    public void viewInitialization() {
        unbinder = ButterKnife.bind(this, mainView);

        banner = (Banner) $(R.id.banner);
        marqueeView = (MarqueeView) $(R.id.marqueeView);


        market_rv = (RecyclerView) $(R.id.market_rv);
        market_rv.setLayoutManager(new LinearLayoutManager(getMContext()));
        marketAdapter = new MarketAdapter(getMContext());
        market_rv.setAdapter(marketAdapter);
        $(R.id.fabi_ll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainAct) getActivity()).showFragment("SHOW_TAB4");
                ((MainAct) getActivity()).showTab("SHOW_TAB4");
            }
        });
        market_vp = (ViewPager) $(R.id.market_vp);
        market_vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                positions = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @OnClick({R.id.msgImg, R.id.help_ll, R.id.qrCode_ll})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.msgImg:
                if (ToolUtils.isFastClick(view.getId())) {
                    MessageCenterAct.startByType("0", getMContext());
                }

                break;
            case R.id.help_ll:
                if (ToolUtils.isFastClick(view.getId())) {
                    MessageCenterAct.startByType("1", getMContext());
                }
                break;
            case R.id.qrCode_ll:
                if (ToolUtils.isFastClick(view.getId())) {
                    try {
                        ToolUtils.checkLogin(getMContext(), true);
                        FUserInfoEntity fUserInfoEntity = ToolUtils.getUserInfo(getMContext());
                        if (fUserInfoEntity != null) {
                            Intent intent = new Intent(getMContext(), QRCodeAct.class);
                            intent.putExtra("user", fUserInfoEntity);
                            startActivity(intent);
                        }
                    } catch (NeedLoginException e) {
                    }
                }
                break;
            default:
                break;
        }
    }


    @Override
    public void doBusiness() {
        presenter.getIndexData();
    }


    @Override
    public Context getMContext() {
        return getActivity();
    }

    @Override
    public void setBanner(final List<IndexEntity.BannerBean> banners) {
        banner.setAutoPlay(true)
                .setPages(banners, new MyBannerViewHolder())
                .setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
                .setBannerAnimation(Transformer.Scale)
                .start();
        banner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.d("TAG", "onPageScrolled: ");
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void setmarqueeView(final List<IndexEntity.NoticeBean> list) {
        List<String> list1 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            list1.add(list.get(i).getTitle());
        }
        if (marqueeView.getNotices().size() != 0) {
            marqueeView.setNotices(list1);
        } else {
            marqueeView.startWithList(list1);
        }
        marqueeView.setOnItemClickListener(new MarqueeView.OnItemClickListener() {
            @Override
            public void onItemClick(int position, TextView textView) {
                if (ToolUtils.isFastClick(marqueeView.getId())) {
                    Bundle bundle = new Bundle();
                    bundle.putString("id", list.get(position).getId());
                    gotoActivity(ArticleAct.class, false, bundle);
                }
            }
        });
    }


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            marketDataFlag = true;
        }
    };


    public void setMarketList(final List<MarketListEntity> list) {
        market_rv.post(new Runnable() {
            @Override
            public void run() {
                if (marketListEntities.size() == 0) {
                    marketListEntities.clear();
                    marketListEntities.addAll(list);
                }
                marketAdapter.setData(list);
                marketAdapter.notifyDataSetChanged();
                handler.sendMessageAtTime(Message.obtain(), 10000);
            }
        });

    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void doMarketListEvent(MarketListEvent marketListEvent) {
        if (!marketDataFlag) {
            return;
        }
        marketDataFlag = false;
        if (marketListEvent != null) {
            IndexEntity entity = new IndexEntity();
            entity.setMarket(marketListEvent.getMarketListEntities());
            List<MarketListEntity> list = new ArrayList<>();
            for (int i = 0; i < marketListEntities.size(); i++) {
                MarketListEntity entity1 = marketListEntities.get(i);
                for (int j = 0; j < marketListEvent.getMarketListEntities().size(); j++) {
                    MarketListEntity entity2 = marketListEvent.getMarketListEntities().get(j);
                    if (entity1.getName().equals(entity2.getName())) {
                        list.add(entity2);
                        break;
                    }
                }
            }
            setMarketList(list);
        }
        if (marketListEvent != null) {
            marketListEvent.setMarketList();
//                setMarketList(marketListEvent.getMarketListEntities());
            setMarketVp(marketListEvent.getMarketList());
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
