package com.svv.jys.code.module.market.list;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.svv.jys.R;
import com.svv.jys.code.common.base.MBaseAdapter;
import com.svv.jys.code.common.base.mvp.MvpFragment;
import com.svv.jys.code.common.entity.MarketListEntity;
import com.svv.jys.code.common.utils.T;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.market.base.adapter.MarketAdapter;
import com.svv.jys.code.module.market.list.model.MarketListModel;
import com.svv.jys.code.module.market.list.persenter.MarketListPersenter;
import com.svv.jys.code.module.market.list.view.MarketListView;
import com.svv.jys.code.module.server.dataserver.event.MarketListEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by js on 2018/5/19.
 */

public class MarketListFrag extends MvpFragment<MarketListView, MarketListModel, MarketListPersenter> implements
        MarketListView {
    private boolean isVisable;
    private RecyclerView rvMarketList;
    private MarketAdapter marketAdapter;
    View nodata;
    private String price;

    public static MarketListFrag newInstance(ArrayList<MarketListEntity> list, String price) {
        Bundle args = new Bundle();
        args.putSerializable("list", list);
        args.putString("price", price);
        MarketListFrag fragment = new MarketListFrag();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Context getMContext() {
        return getActivity();
    }

    @Override
    public MarketListPersenter initPresenter() {
        return new MarketListPersenter();
    }

    @Override
    public void onWakeBussiness() {
//        presenter.getMarketList();
    }

    @Override
    public void baseInitialization() {

        EventBus.getDefault().register(this);
    }

    @Override
    public int setR_Layout() {
        return R.layout.frag_marketlist;
    }

    @Override
    public void viewInitialization() {
        rvMarketList = (RecyclerView) $(R.id.rvMarketList);
        nodata = $(R.id.nodata);
        rvMarketList.setLayoutManager(new LinearLayoutManager(getMContext()));
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            isVisable = true;
            //相当于Fragment的onResume
        } else {
            isVisable = false;
            //相当于Fragment的onPause
        }
    }

    @Override
    public void doBusiness() {
        price = getArguments().getString("price");
        setMarketList((List<MarketListEntity>) getArguments().getSerializable("list"));

    }

    public void setMarketList(List<MarketListEntity> list) {
        if (rvMarketList == null) {
            return;
        }
        if (list == null || list.size() == 0) {
            nodata.setVisibility(View.VISIBLE);
            rvMarketList.setVisibility(View.GONE);
        } else {
            nodata.setVisibility(View.GONE);
            rvMarketList.setVisibility(View.VISIBLE);
            if (marketAdapter == null) {
                marketAdapter = new MarketAdapter(getMContext(), list, price);
                rvMarketList.setAdapter(marketAdapter);
                marketAdapter.setOnItemClick(new MBaseAdapter.MOnItemClickListener() {
                    @Override
                    public void onItemClick(int position, Object o) {

                        if (o == null) {
                            T.showShort(getMContext(), getString(R.string.data_not_yet));
                            return;
                        }
//                        TradeChat2Act.startAct(getMContext(), (MarketListEntity) o);
                        ToolUtils.startKLineAct(getMContext(), (MarketListEntity) o);

                    }
                });
            } else {
                marketAdapter.setData(list);
            }
        }

    }

    public void setAdapterStatus(int type, boolean isAscending) {
        if (marketAdapter != null)
            marketAdapter.setSort(type, isAscending);
    }

    public List<MarketListEntity> getNowShowMarket() {
        if (marketAdapter != null) {
            return marketAdapter.getAllList();
        }
        return new ArrayList<MarketListEntity>();
    }


    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void doMarketListEvent(MarketListEvent marketListEvent) {
        if (marketListEvent != null && !isHidden() && isVisable) {
            presenter.doHandleMarketList(marketListEvent);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        marketAdapter = null;
        EventBus.getDefault().unregister(this);
    }
}
