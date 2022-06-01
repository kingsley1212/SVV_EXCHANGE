package com.svv.jys.code.module.myself.businessorder2.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.svv.jys.R;
import com.svv.jys.code.common.base.MBaseAdapter;
import com.svv.jys.code.common.base.mvp.MvpFragment;
import com.svv.jys.code.common.entity.OtcOrderEntity;
import com.svv.jys.code.module.myself.businessorder2.fragment.adapter.BusinessOrderAdapter2;
import com.svv.jys.code.module.myself.businessorder2.fragment.model.BusinessOrderModel;
import com.svv.jys.code.module.myself.businessorder2.fragment.presenter.BusinessOrderPresenter;
import com.svv.jys.code.module.myself.businessorder2.fragment.view.BusinessOrderView;
import com.svv.jys.code.module.myself.myasset.otc.otctrading.orderdetail.OrderDetailAct;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by js on 2018/9/4.
 */

public class BusinessOrderFragment extends MvpFragment<BusinessOrderView, BusinessOrderModel, BusinessOrderPresenter>
        implements BusinessOrderView {
    private XRecyclerView xrv_otc_order;
    private View no_data_ly;
    private List<OtcOrderEntity.RowsBean> lists = new ArrayList<>();
    private BusinessOrderAdapter2 otcOrderAdapter;
    private int page = 1;
    String stutas;

    @Override
    public Context getMContext() {
        return getActivity();
    }

    public static BusinessOrderFragment newInstance(String stutas) {
        Bundle args = new Bundle();
        args.putString("stutas", stutas);
        BusinessOrderFragment fragment = new BusinessOrderFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public BusinessOrderPresenter initPresenter() {
        return new BusinessOrderPresenter();
    }

    @Override
    public void onWakeBussiness() {

    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getShopOrder(page, stutas);
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
                page = 1;
                presenter.getShopOrder(page, stutas);
            }

            @Override
            public void onLoadMore() {
                page++;
                presenter.getShopOrder(page, stutas);
            }
        });
    }

    @Override
    public void doBusiness() {

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
            otcOrderAdapter = new BusinessOrderAdapter2(getActivity(), lists);
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

    @Override
    public void setBusinessOrder(OtcOrderEntity order) {
        List<OtcOrderEntity.RowsBean> list = order.getRows();
        if (page == 1) {
            refresh(list);
        } else {
            loading(list);
        }
    }
}
