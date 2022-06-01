package com.svv.jys.code.module.business.tradechat.transaction;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.mvp.MvpFragment;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.business.tradechat.transaction.adapter.TransactionAdapter;
import com.svv.jys.code.module.business.tradechat.transaction.model.TransactionModel;
import com.svv.jys.code.module.business.tradechat.transaction.persenter.TransactionPresenter;
import com.svv.jys.code.module.business.tradechat.transaction.view.TransactionView;
import com.svv.jys.code.module.server.dataserver.event.TransactionListEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by js on 2018/5/19.
 */

public class TransactionFragment extends MvpFragment<TransactionView, TransactionModel, TransactionPresenter> implements TransactionView {
    private String area;
    private TransactionAdapter adapter;
    private RecyclerView transaction_rv;

    public static TransactionFragment newInstance(String area) {
        Bundle args = new Bundle();
        TransactionFragment fragment = new TransactionFragment();
        args.putString("AREA",area);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Context getMContext() {
        return getActivity();
    }

    @Override
    public TransactionPresenter initPresenter() {
        return new TransactionPresenter();
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
        return R.layout.frag_transaction;
    }

    @Override
    public void viewInitialization() {
        transaction_rv = (RecyclerView) $(R.id.transaction_rv);
        transaction_rv.setLayoutManager(new LinearLayoutManager(getMContext()));
        adapter  = new TransactionAdapter(getMContext());
        transaction_rv.setAdapter(adapter);
//        ((TradeChat2Act) getMContext()).mDataService.leaveLog(area);
//        ((TradeChat2Act) getMContext()).mDataService.subscribeLog(area);
        ToolUtils.getKLineActType(getMContext()).mDataService.leaveLog(area);
        ToolUtils.getKLineActType(getMContext()).mDataService.subscribeLog(area);
    }




    @Override
    public void doBusiness() {


    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void doTradeListEvent(TransactionListEvent event) {
        if (event != null) {
            setList(event);
        }
    }

    private void setList(TransactionListEvent event) {
        adapter.setList(event.getTradeListEntity());
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
//        ((TradeChat2Act) getMContext()).mDataService.leaveLog(area);
        ToolUtils.getKLineActType(getMContext()).mDataService.leaveLog(area);
        EventBus.getDefault().unregister(this);
    }
}
