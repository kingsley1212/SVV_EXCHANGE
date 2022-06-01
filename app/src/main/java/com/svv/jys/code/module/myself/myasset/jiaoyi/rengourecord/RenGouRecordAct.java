package com.svv.jys.code.module.myself.myasset.jiaoyi.rengourecord;


import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.svv.jys.R;
import com.svv.jys.code.common.base.MBaseAdapter;
import com.svv.jys.code.common.base.mvp.MvpActivity;
import com.svv.jys.code.common.entity.RenGouRecordEntity;
import com.svv.jys.code.module.myself.myasset.jiaoyi.rengourecord.adapter.RenGouRecordAdapter;
import com.svv.jys.code.module.myself.myasset.jiaoyi.rengourecord.model.RenGouRecordModel;
import com.svv.jys.code.module.myself.myasset.jiaoyi.rengourecord.presenter.RenGouRecordPresenter;
import com.svv.jys.code.module.myself.myasset.jiaoyi.rengourecord.view.RenGouRecordView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by js on 2018/10/19.
 */

public class RenGouRecordAct extends MvpActivity<RenGouRecordView,RenGouRecordModel,RenGouRecordPresenter> implements RenGouRecordView{
    private XRecyclerView xrv_rengou;
    private View no_data_ly;
    private int page = 1;
    private String type,coin;
    private List<RenGouRecordEntity.RowsBean> lists = new ArrayList<>();
    private RenGouRecordAdapter adapter;
    @Override
    public Context getMContext() {
        return this;
    }

    @Override
    public RenGouRecordPresenter initPresenter() {
        return new RenGouRecordPresenter();
    }

    @Override
    public void baseInitialization() {
        Intent intent=getIntent();
        type =intent .getStringExtra("type");
        coin =intent .getStringExtra("coin");
    }

    @Override
    public int setR_Layout() {
        return R.layout.act_rengou_record;
    }

    @Override
    public void viewInitialization() {
        setBackPress();

        no_data_ly = $(R.id.no_data_ly);
        xrv_rengou=findViewById(R.id.xrv_rengou);
        xrv_rengou.setLayoutManager(new LinearLayoutManager(this));
        xrv_rengou.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                presenter.getRengouRecord(page,type,coin,true);
            }

            @Override
            public void onLoadMore() {
                page++;
                presenter.getRengouRecord(page,type,coin,true);
            }
        });
        if (type.equals("1")){
            setTitleTx(getString(R.string.rengou_jilu));
        }else {
            setTitleTx(getString(R.string.peishou_jilu));
        }
    }

    @Override
    public void doBusiness() {
        presenter.getRengouRecord(page,type,coin,false);
    }

    @Override
    public void setRenGouRecord(RenGouRecordEntity entity) {
        List<RenGouRecordEntity.RowsBean> list = entity.getRows();
        if (page == 1) {
            refresh(list);
        } else {
            loading(list);
        }
    }

    @Override
    public void chexiaoSuccese() {
        rowsBean.setStatus("3");
        rowsBean.setStatus_name(getString(R.string.yichexiao));
        adapter.notifyDataSetChanged();
    }

    public void loading(List<RenGouRecordEntity.RowsBean> list) {
        xrv_rengou.loadMoreComplete();
        //加载下一页
        if (list != null && list.size() != 0) {
            lists.addAll(list);
            adapter.notifyDataSetChanged();
        } else {
            xrv_rengou.setNoMore(true);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    xrv_rengou.setNoMore(false);//已经全部加载完成
                }
            }, 1000);
        }
    }

    RenGouRecordEntity.RowsBean rowsBean;

    public void refresh(final List<RenGouRecordEntity.RowsBean> list) {
        //刷新
        if (list != null && list.size() != 0) {
            no_data_ly.setVisibility(View.GONE);
            xrv_rengou.setVisibility(View.VISIBLE);
            lists.clear();
            lists.addAll(list);
            adapter = new RenGouRecordAdapter(this, lists,type);
            xrv_rengou.setAdapter(adapter);
            adapter.setOnItemClick(new MBaseAdapter.MOnItemClickListener() {
                @Override
                public void onItemClick(int position, Object o) {
                    rowsBean=(RenGouRecordEntity.RowsBean)o;
                    presenter.chexiao(rowsBean.getId());
                }
            });
            adapter.notifyDataSetChanged();
            xrv_rengou.refreshComplete();//关闭刷新
        } else {
            xrv_rengou.setVisibility(View.GONE);
            no_data_ly.setVisibility(View.VISIBLE);
        }
    }
}
