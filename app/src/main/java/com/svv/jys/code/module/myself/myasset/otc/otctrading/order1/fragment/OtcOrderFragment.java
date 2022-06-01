package com.svv.jys.code.module.myself.myasset.otc.otctrading.order1.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.svv.jys.R;
import com.svv.jys.code.common.base.MBaseAdapter;
import com.svv.jys.code.common.base.mvp.MvpFragment;
import com.svv.jys.code.common.entity.OtcOrderEntity;
import com.svv.jys.code.module.myself.myasset.otc.otctrading.order1.fragment.adapter.OtcOrderAdapter1;
import com.svv.jys.code.module.myself.myasset.otc.otctrading.order1.fragment.model.OtcOrderModel1;
import com.svv.jys.code.module.myself.myasset.otc.otctrading.order1.fragment.presenter.OtcOrderPreserter1;
import com.svv.jys.code.module.myself.myasset.otc.otctrading.order1.fragment.view.OtcOrderView1;
import com.svv.jys.code.module.myself.myasset.otc.otctrading.orderdetail.OrderDetailAct;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by js on 2018/9/4.
 */

public class OtcOrderFragment extends MvpFragment<OtcOrderView1, OtcOrderModel1, OtcOrderPreserter1> implements
        OtcOrderView1 {
    private XRecyclerView xrv_otc_order;
    private View no_data_ly;
    private List<OtcOrderEntity.RowsBean> lists = new ArrayList<>();
    private OtcOrderAdapter1 otcOrderAdapter;
    String stutas;

    @Override
    public Context getMContext() {
        return getActivity();
    }

    public static OtcOrderFragment newInstance(String stutas) {
        Bundle args = new Bundle();
        args.putString("stutas", stutas);
        OtcOrderFragment fragment = new OtcOrderFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void setOtcOrder(OtcOrderEntity entity,boolean isLoadMore) {
        List<OtcOrderEntity.RowsBean> list = entity.getRows();
        if (!isLoadMore) {
            refresh(list);
        } else {
            loading(list);
        }
    }

    @Override
    public String getRxSize() {
        return String.valueOf(otcOrderAdapter.getItemCount());
    }

    @Override
    public OtcOrderPreserter1 initPresenter() {
        return new OtcOrderPreserter1();
    }

    @Override
    public void onWakeBussiness() {

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void baseInitialization() {
        stutas = getArguments().getString("stutas");
    }

    @Override
    public int setR_Layout() {
        return R.layout.frag_otc_order;
    }

    @Override
    public void viewInitialization() {
        no_data_ly = $(R.id.no_data_ly);
        xrv_otc_order = (XRecyclerView) $(R.id.xrv_otc_order);
        xrv_otc_order.setLayoutManager(new LinearLayoutManager(getActivity()));
        xrv_otc_order.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                presenter.getOtcOrder(false, stutas);
            }

            @Override
            public void onLoadMore() {
                presenter.getOtcOrder(true, stutas);
            }
        });
    }

    @Override
    public void doBusiness() {
        presenter.getOtcOrder(false, stutas);
    }

    public void loading(List<OtcOrderEntity.RowsBean> list) {
        xrv_otc_order.loadMoreComplete();
        //加载下一页
        if (list != null && list.size() != 0) {
            lists.addAll(list);
            otcOrderAdapter.notifyDataSetChanged();
        } else {
            xrv_otc_order.setNoMore(true);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    xrv_otc_order.setNoMore(false);//已经全部加载完成
                }
            }, 1000);
        }
    }

    public void refresh(final List<OtcOrderEntity.RowsBean> list) {
        //刷新
        if (list != null && list.size() != 0) {
            no_data_ly.setVisibility(View.GONE);
            xrv_otc_order.setVisibility(View.VISIBLE);
            lists.clear();
            lists.addAll(list);
            otcOrderAdapter = new OtcOrderAdapter1(getActivity(), lists);
            xrv_otc_order.setAdapter(otcOrderAdapter);
            otcOrderAdapter.setOnItemClick(new MBaseAdapter.MOnItemClickListener() {
                @Override
                public void onItemClick(int position, Object o) {
//                    Intent intent=new Intent(getActivity(),OrderDetailAct.class);
//                    intent.putExtra("id",lists.get(position).getId());
//                    startActivityForResult(intent,200);
                    OrderDetailAct.startOtcOrderDetailForResult(getActivity(), lists.get(position).getId());
                }
            });
            otcOrderAdapter.notifyDataSetChanged();
            xrv_otc_order.refreshComplete();//关闭刷新
        } else {
            xrv_otc_order.setVisibility(View.GONE);
            no_data_ly.setVisibility(View.VISIBLE);
        }
    }
}
