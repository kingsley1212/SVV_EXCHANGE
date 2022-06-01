package com.svv.jys.code.module.myself.myasset.jiaoyi.chargerecord;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.svv.jys.R;
import com.svv.jys.code.common.base.mvp.MvpActivity;
import com.svv.jys.code.common.entity.MentionRecordEntity;
import com.svv.jys.code.module.myself.myasset.jiaoyi.chargerecord.adapter.ChargeRecordAdapter;
import com.svv.jys.code.module.myself.myasset.jiaoyi.chargerecord.model.ChargeRecordModel;
import com.svv.jys.code.module.myself.myasset.jiaoyi.chargerecord.presenter.ChargeRecordPresenter;
import com.svv.jys.code.module.myself.myasset.jiaoyi.chargerecord.view.ChargeRecordView;

import java.util.ArrayList;
import java.util.List;

public class ChargeRecordAct extends MvpActivity<ChargeRecordView,ChargeRecordModel,ChargeRecordPresenter> implements ChargeRecordView {

    private String coin;
    private XRecyclerView xrv_mentionrecord;
    private View no_data_ly;
    private int page = 1;
    private List<MentionRecordEntity.RowsBean> rowsBeans = new ArrayList<>();
    private ChargeRecordAdapter adapter;
    @Override
    public ChargeRecordPresenter initPresenter() {
        return new ChargeRecordPresenter();
    }

    @Override
    public void baseInitialization() {
        coin = getIntent().getStringExtra("coin");
    }

    @Override
    public int setR_Layout() {
        return R.layout.act_mentionrecord;
    }

    @Override
    public void viewInitialization() {
        setBackPress();
        setTitleTx(getString(R.string.assat18));
        xrv_mentionrecord=findViewById(R.id.xrv_mentionrecord);
        no_data_ly = findViewById(R.id.no_data_ly);
        xrv_mentionrecord.setLayoutManager(new LinearLayoutManager(this));
        xrv_mentionrecord.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page=1;
                presenter.getMentionRecord(page,coin);
            }

            @Override
            public void onLoadMore() {
                page++;
                presenter.getMentionRecord(page,coin);
            }
        });
    }

    @Override
    public void doBusiness() {
        presenter.getMentionRecord(page,coin);
    }

    @Override
    public Context getMContext() {
        return this;
    }

    public void loading(List<MentionRecordEntity.RowsBean> list) {
        xrv_mentionrecord.loadMoreComplete();
        //加载下一页
        if (list != null && list.size() != 0) {
            rowsBeans.addAll(list);
            adapter.notifyDataSetChanged();
        } else {
            xrv_mentionrecord.setNoMore(true);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    xrv_mentionrecord.setNoMore(false);//已经全部加载完成
                }
            }, 1000);
        }
    }

    public void refresh(List<MentionRecordEntity.RowsBean> list) {
        //刷新
        if (list != null && list.size() != 0) {
            no_data_ly.setVisibility(View.GONE);
            xrv_mentionrecord.setVisibility(View.VISIBLE);
            rowsBeans.clear();
            rowsBeans.addAll(list);
            if(adapter == null) {
                adapter = new ChargeRecordAdapter(this, rowsBeans);

                xrv_mentionrecord.setAdapter(adapter);
            }else {
                adapter.setList(rowsBeans);
            }
            adapter.notifyDataSetChanged();
            xrv_mentionrecord.refreshComplete();//关闭刷新
        } else {
            xrv_mentionrecord.setVisibility(View.GONE);
            no_data_ly.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setMentionRecord(List<MentionRecordEntity.RowsBean> list) {
        if (page == 1) {
            refresh(list);
        } else {
            loading(list);
        }
    }


}
