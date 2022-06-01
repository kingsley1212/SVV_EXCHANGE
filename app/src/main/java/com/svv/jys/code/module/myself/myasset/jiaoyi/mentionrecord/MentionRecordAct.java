package com.svv.jys.code.module.myself.myasset.jiaoyi.mentionrecord;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.svv.jys.R;
import com.svv.jys.code.common.base.mvp.MvpActivity;
import com.svv.jys.code.common.entity.MentionRecordEntity;
import com.svv.jys.code.common.view.popup.PopupDialogView;
import com.svv.jys.code.module.myself.myasset.jiaoyi.mentionrecord.adapter.MentionRecordAdapter;
import com.svv.jys.code.module.myself.myasset.jiaoyi.mentionrecord.model.MentionRecordModel;
import com.svv.jys.code.module.myself.myasset.jiaoyi.mentionrecord.presenter.MentionRecordPresenter;
import com.svv.jys.code.module.myself.myasset.jiaoyi.mentionrecord.view.MentionRecordView;

import java.util.ArrayList;
import java.util.List;

public class MentionRecordAct extends MvpActivity<MentionRecordView,MentionRecordModel,MentionRecordPresenter> implements MentionRecordView {

    private String coin;
    private XRecyclerView xrv_mentionrecord;
    private View no_data_ly;
    private int page = 1;
    private List<MentionRecordEntity.RowsBean> rowsBeans = new ArrayList<>();
    private MentionRecordAdapter adapter;
    @Override
    public MentionRecordPresenter initPresenter() {
        return new MentionRecordPresenter();
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
        setTitleTx(getString(R.string.assat19));
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
                adapter = new MentionRecordAdapter(this, rowsBeans);

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

    public void deleteItem(final int position, final String id){
        PopupDialogView view = new PopupDialogView(getMContext(), new PopupDialogView.MClickLisnener() {
            @Override
            public void leftBtn() {

            }

            @Override
            public void rightBtn() {
                presenter.delete(position,id);
            }
        });
        view.showPop(xrv_mentionrecord, getString(R.string.address6), getString(R.string.metionrecord_tv1), getString(R.string.myselffragment_left_text), getString(R.string.myselffragment_right_text));

    }

    @Override
    public void deleteSuccess(int position) {
        rowsBeans.get(position).setStatus("3");
        rowsBeans.get(position).setStatus_name(getString(R.string.entrust_chexiao));
        adapter.notifyDataSetChanged();
    }
}
