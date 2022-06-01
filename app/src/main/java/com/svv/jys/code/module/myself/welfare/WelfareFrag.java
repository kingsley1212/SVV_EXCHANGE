package com.svv.jys.code.module.myself.welfare;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.svv.jys.R;
import com.svv.jys.code.common.base.mvp.MvpFragment;
import com.svv.jys.code.common.entity.WelfareEntity;
import com.svv.jys.code.module.myself.welfare.adapter.WelfareAdapter;
import com.svv.jys.code.module.myself.welfare.model.WelfareModel;
import com.svv.jys.code.module.myself.welfare.presenter.WelfarePresenter;
import com.svv.jys.code.module.myself.welfare.view.WelfareView;

import java.util.ArrayList;
import java.util.List;


public class WelfareFrag extends MvpFragment<WelfareView,WelfareModel,WelfarePresenter> implements  WelfareView {

    private String stutas;
    XRecyclerView xrv_entrust;
    View no_data;
    private int page = 1;
    private WelfareAdapter adapter;
    private List<WelfareEntity.RowsBean> lists=new ArrayList<>();

    @Override
    public WelfarePresenter initPresenter() {
        return new WelfarePresenter();
    }

    public static WelfareFrag newInstance(String stutas) {
        Bundle args = new Bundle();
        args.putString("stutas", stutas);
        WelfareFrag fragment = new WelfareFrag();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onWakeBussiness() {

    }

    @Override
    public void baseInitialization() {
        stutas = getArguments().getString("stutas");
    }

    @Override
    public int setR_Layout() {
        return R.layout.frag_welfare;
    }

    @Override
    public void viewInitialization() {
        xrv_entrust = (XRecyclerView) $(R.id.xrv_entrust);
        xrv_entrust.setLayoutManager(new LinearLayoutManager(getMContext()));
        no_data = $(R.id.no_data);
        xrv_entrust.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                presenter.getWelfare(stutas,page);
            }

            @Override
            public void onLoadMore() {
                page++;
                presenter.getWelfare(stutas,page);
            }
        });
    }

    public void loading(List<WelfareEntity.RowsBean> list) {
        xrv_entrust.loadMoreComplete();
        //加载下一页
        if (list != null && list.size() != 0) {
            lists.addAll(list);
            adapter.notifyDataSetChanged();
        } else {
            xrv_entrust.setNoMore(true);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    xrv_entrust.setNoMore(false);//已经全部加载完成
                }
            }, 1000);
        }
    }

    public void refresh(List<WelfareEntity.RowsBean> list) {
        //刷新
        if (list != null && list.size() != 0) {
            xrv_entrust.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);
            xrv_entrust.setVisibility(View.VISIBLE);
            lists.clear();
            lists.addAll(list);
            if (adapter == null) {
                adapter = new WelfareAdapter(getMContext(), lists);
                xrv_entrust.setAdapter(adapter);
            } else {
                adapter.setList(lists);
            }
            adapter.notifyDataSetChanged();
            xrv_entrust.refreshComplete();//关闭刷新
        } else {
            xrv_entrust.setVisibility(View.GONE);
            no_data.setVisibility(View.VISIBLE);
        }

    }


    @Override
    public void doBusiness() {
        presenter.getWelfare(stutas,page);
    }

    @Override
    public Context getMContext() {
        return getActivity();
    }

    @Override
    public void setWelfare(List<WelfareEntity.RowsBean> list) {
        if (page == 1) {
            refresh(list);
        } else {
            loading(list);
        }
    }
}
