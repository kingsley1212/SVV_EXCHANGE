package com.svv.jys.code.module.myself.myasset.jiaoyi.suocangrecord;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.svv.jys.R;
import com.svv.jys.code.common.base.mvp.MvpActivity;
import com.svv.jys.code.common.entity.CunFangRecordEntity;
import com.svv.jys.code.module.myself.myasset.jiaoyi.suocangrecord.adapter.SuoFangRecordAdapter;
import com.svv.jys.code.module.myself.myasset.jiaoyi.suocangrecord.model.SuoCangRecordModel;
import com.svv.jys.code.module.myself.myasset.jiaoyi.suocangrecord.presenter.SuoCangRecordPresenter;
import com.svv.jys.code.module.myself.myasset.jiaoyi.suocangrecord.view.SuoCangRecordView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by js on 2018/8/11.
 */

public class SuoCangRecordAct extends MvpActivity<SuoCangRecordView,SuoCangRecordModel,SuoCangRecordPresenter> implements SuoCangRecordView{
    private String coin;
    private View no_data_ly;
    private int page = 1;
    private XRecyclerView xrv_suocang_record;
    private List<CunFangRecordEntity.RowsBean> lists = new ArrayList<>();
    SuoFangRecordAdapter adapter;

    @Override
    public Context getMContext() {
        return this;
    }

    @Override
    public SuoCangRecordPresenter initPresenter() {
        return new SuoCangRecordPresenter();
    }

    @Override
    public void baseInitialization() {
        coin = getIntent().getStringExtra("coin");
    }

    @Override
    public int setR_Layout() {
        return R.layout.act_suocangrecord;
    }

    @Override
    public void viewInitialization() {
        setBackPress();
        setTitleTx(getString(R.string.suocang_record));
        no_data_ly=findViewById(R.id.no_data_ly);
        xrv_suocang_record=findViewById(R.id.xrv_suocang_record);
        xrv_suocang_record.setLayoutManager(new LinearLayoutManager(this));
        xrv_suocang_record.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page=1;
                presenter.suocangRecord(page,coin,true);
            }

            @Override
            public void onLoadMore() {
                page++;
                presenter.suocangRecord(page,coin,true);
            }
        });
    }

    @Override
    public void doBusiness() {
        presenter.suocangRecord(page,coin,false);
    }

    @Override
    public void suocangRecord(List<CunFangRecordEntity.RowsBean> list) {
        if (page==1){
            refresh(list);
        }else {
            loading(list);
        }
    }

    public void loading(List<CunFangRecordEntity.RowsBean> list) {
        xrv_suocang_record.loadMoreComplete();
        //加载下一页
        if (list != null && list.size() != 0) {
            lists.addAll(list);
            adapter.notifyDataSetChanged();
        } else {
            xrv_suocang_record.setNoMore(true);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    xrv_suocang_record.setNoMore(false);//已经全部加载完成
                }
            }, 1000);
        }
    }

    public void refresh(List<CunFangRecordEntity.RowsBean> list) {
        //刷新
        if (list != null && list.size() != 0) {
            no_data_ly.setVisibility(View.GONE);
            xrv_suocang_record.setVisibility(View.VISIBLE);
            lists.clear();
            lists.addAll(list);
            if (adapter == null) {
                adapter = new SuoFangRecordAdapter(this, lists);
                xrv_suocang_record.setAdapter(adapter);

            } else {
                adapter.setList(list);
            }
            xrv_suocang_record.refreshComplete();//关闭刷新
        } else {
            xrv_suocang_record.setVisibility(View.GONE);
            no_data_ly.setVisibility(View.VISIBLE);
        }

    }
}
