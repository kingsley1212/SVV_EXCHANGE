package com.svv.jys.code.module.myself.myasset.transfer;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.svv.jys.R;
import com.svv.jys.code.common.base.mvp.MvpActivity;
import com.svv.jys.code.common.entity.OtcTransferEntity;
import com.svv.jys.code.module.myself.myasset.transfer.adapter.TransferRecordAdapter;
import com.svv.jys.code.module.myself.myasset.transfer.model.TransferRecordModel;
import com.svv.jys.code.module.myself.myasset.transfer.presenter.TransferRecordPresenter;
import com.svv.jys.code.module.myself.myasset.transfer.view.TransferRecordView;

import java.util.List;

import static android.view.View.GONE;

/**
 * Created by js on 2018/6/7.
 */

public class TransferRecordAct extends MvpActivity<TransferRecordView, TransferRecordModel, TransferRecordPresenter> implements TransferRecordView {
    private XRecyclerView transfer_record_rv;
    private TransferRecordAdapter adapter;
    private View nodata_ly;
    @Override
    public Context getMContext() {
        return this;
    }

    @Override
    public TransferRecordPresenter initPresenter() {
        return new TransferRecordPresenter();
    }

    @Override
    public void baseInitialization() {

    }

    @Override
    public int setR_Layout() {
        return R.layout.act_transfer_record;
    }

    @Override
    public void viewInitialization() {
        setBackPress();
        setTitleTx(getString(R.string.assat2));
        nodata_ly = $(R.id.nodata_ly);
        transfer_record_rv = (XRecyclerView) $(R.id.transfer_record_rv);
         adapter = new  TransferRecordAdapter(getMContext());
        transfer_record_rv.setLayoutManager(new LinearLayoutManager(getMContext()));
        transfer_record_rv.setAdapter(adapter);
        transfer_record_rv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                presenter.getOtcList(0);
            }

            @Override
            public void onLoadMore() {
                presenter.getOtcList(adapter.getItemCount());
            }
        });
    }

    @Override
    public void doBusiness() {
        presenter.getOtcList(0);
    }

    @Override
    public void loadMoreFinish(List<OtcTransferEntity> list) {
       adapter.addEntities(list);
       adapter.notifyDataSetChanged();
       if(list.size()==0){
           transfer_record_rv.setNoMore(true);
       }
       transfer_record_rv.loadMoreComplete();
    }

    @Override
    public void refreshFinish(List<OtcTransferEntity> list) {
        adapter.setEntities(list);
        adapter.notifyDataSetChanged();
        if(list.size()==0){
            nodata_ly.setVisibility(View.VISIBLE);
            transfer_record_rv.setVisibility(GONE);
        }else {
            nodata_ly.setVisibility(View.GONE);
            transfer_record_rv.setVisibility(View.VISIBLE);
        }
        transfer_record_rv.refreshComplete();
    }
}
