package com.svv.jys.code.module.business.tradechat.deep;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.mvp.MvpFragment;
import com.svv.jys.code.common.db.nativecache.ACache;
import com.svv.jys.code.common.entity.TradeListEntity;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.business.tradechat.deep.adapter.Pankou2Adapter;
import com.svv.jys.code.module.business.tradechat.deep.adapter.PankouAdapter;
import com.svv.jys.code.module.business.tradechat.deep.model.DeepModel;
import com.svv.jys.code.module.business.tradechat.deep.persenter.DeepPresenter;
import com.svv.jys.code.module.business.tradechat.deep.view.DeepView;
import com.svv.jys.code.module.server.dataserver.event.TradeListEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by js on 2018/5/19.
 */

public class DeepFragment extends MvpFragment<DeepView, DeepModel, DeepPresenter> implements DeepView {
    private String area;
    private RecyclerView buy_rv,sell_rv;
    private PankouAdapter adapter;
    private Pankou2Adapter adapter2;
    private TextView tv_buy_coin,tv_price,tv_sell_coin;
    public static DeepFragment newInstance(String area) {
        Bundle args = new Bundle();
        DeepFragment fragment = new DeepFragment();
        args.putString("AREA",area);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Context getMContext() {
        return getActivity();
    }

    @Override
    public DeepPresenter initPresenter() {
        return new DeepPresenter();
    }

    @Override
    public void onWakeBussiness() {
    }

    @Override
    public void baseInitialization() {
        EventBus.getDefault().register(this);
        area = getArguments().getString("AREA");
    }

    @Override
    public int setR_Layout() {
        return R.layout.frag_deep;
    }

    @Override
    public void viewInitialization() {
        tv_buy_coin= (TextView) $(R.id.tv_buy_coin);
        tv_price= (TextView) $(R.id.tv_price);
        tv_sell_coin= (TextView) $(R.id.tv_sell_coin);
        buy_rv = (RecyclerView) $(R.id.buy_rv);
        sell_rv = (RecyclerView) $(R.id.sell_rv);
        buy_rv.setLayoutManager(new LinearLayoutManager(getMContext()));
        sell_rv.setLayoutManager(new LinearLayoutManager(getMContext()));
        buy_rv.setNestedScrollingEnabled(false);
        sell_rv.setNestedScrollingEnabled(false);
        adapter = new PankouAdapter(getMContext());
        adapter2 = new Pankou2Adapter(getMContext());
        buy_rv.setAdapter(adapter2);
        sell_rv.setAdapter(adapter);
        tv_buy_coin.setText(String.format(getString(R.string.chat_num),area.split("_")[0].toUpperCase()));
        tv_sell_coin.setText(String.format(getString(R.string.chat_num),area.split("_")[0].toUpperCase()));
        tv_price.setText(String.format(getString(R.string.chat_price),area.split("_")[1].toUpperCase()));
//        if (!TextUtils.isEmpty(ACache.get(getMContext()).getAsString("coin_aera"))){
//            ((TradeChat2Act) getMContext()).mDataService.leaveDept(ACache.get(getMContext()).getAsString("coin_aera"));
//        }
//        ((TradeChat2Act) getMContext()).mDataService.subscribeDept(area);
        if (!TextUtils.isEmpty(ACache.get(getMContext()).getAsString("coin_aera"))) {
            ToolUtils.getKLineActType(getMContext()).mDataService.leaveDept(ACache.get(getMContext()).getAsString("coin_aera"));
        }
        ToolUtils.getKLineActType(getMContext()).mDataService.subscribeDept(area);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void doTradeListEvent(TradeListEvent tradeListEvent) {
        if (tradeListEvent != null) {
            setTrade(tradeListEvent.tradeListEntity);
        }
    }

    private void setTrade(TradeListEntity tradeListEntity) {
        if (tradeListEntity != null) {
            adapter2.setList(tradeListEntity.getBuy());
            adapter2.notifyDataSetChanged();
            adapter.setList(tradeListEntity.getSell());
            adapter.notifyDataSetChanged();
        }
    }


    @Override
    public void doBusiness() {


    }



    @Override
    public void onDestroy() {
        super.onDestroy();
//        ((TradeChat2Act) getMContext()).mDataService.leaveDept(area);
        ToolUtils.getKLineActType(getMContext()).mDataService.leaveDept(area);
        EventBus.getDefault().unregister(this);
    }
}
