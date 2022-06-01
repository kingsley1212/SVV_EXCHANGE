package com.svv.jys.code.module.myself.businessorder;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.svv.jys.R;
import com.svv.jys.code.common.base.MBaseAdapter;
import com.svv.jys.code.common.base.mvp.MvpActivity;
import com.svv.jys.code.common.entity.OrderDetailEntity;
import com.svv.jys.code.common.entity.OtcOrderEntity;
import com.svv.jys.code.module.myself.businessorder.adapter.BusinessOrderAdapter;
import com.svv.jys.code.module.myself.businessorder.model.BusinessOrderModel;
import com.svv.jys.code.module.myself.businessorder.presenter.BusinessOrderPresenter;
import com.svv.jys.code.module.myself.businessorder.view.BusinessOrderView;
import com.svv.jys.code.module.myself.myasset.otc.otctrading.orderdetail.OrderDetailAct;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by js on 2018/6/12.
 */

public class BusinessOrderAct extends MvpActivity<BusinessOrderView, BusinessOrderModel, BusinessOrderPresenter>
        implements BusinessOrderView {

    private XRecyclerView xrv_otc_order;
    private View no_data_ly;
    private List<OtcOrderEntity.RowsBean> lists = new ArrayList<>();
    private BusinessOrderAdapter otcOrderAdapter;
    private int page = 1;

    @Override
    public Context getMContext() {
        return this;
    }

    @Override
    public BusinessOrderPresenter initPresenter() {
        return new BusinessOrderPresenter();
    }

    @Override
    public void baseInitialization() {

    }

    @Override
    public int setR_Layout() {
        return R.layout.act_otc_order;
    }

    @Override
    public void viewInitialization() {
        setBackPress();
        setTitleTx(getString(R.string.mysele_business));
        no_data_ly = $(R.id.no_data_ly);
        xrv_otc_order = findViewById(R.id.xrv_otc_order);
        xrv_otc_order.setLayoutManager(new LinearLayoutManager(this));
        xrv_otc_order.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                presenter.getShopOrder(page, true);
            }

            @Override
            public void onLoadMore() {
                page++;
                presenter.getShopOrder(page, true);
            }
        });
    }

    @Override
    public void doBusiness() {
        presenter.getShopOrder(page, false);
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
            otcOrderAdapter = new BusinessOrderAdapter(this, lists);
            xrv_otc_order.setAdapter(otcOrderAdapter);
            otcOrderAdapter.setOnItemClick(new MBaseAdapter.MOnItemClickListener() {
                @Override
                public void onItemClick(int position, Object o) {
                    postion = position;
//                    Intent intent=new Intent(BusinessOrderAct.this,OrderDetailAct.class);
//                    intent.putExtra("id",lists.get(position).getId());
//                    startActivityForResult(intent,200);
                    OrderDetailAct.startOtcOrderDetailForResult(BusinessOrderAct.this, lists.get(position).getId());
                }
            });
            otcOrderAdapter.notifyDataSetChanged();
            xrv_otc_order.refreshComplete();//关闭刷新
        } else {
            xrv_otc_order.setVisibility(View.GONE);
            no_data_ly.setVisibility(View.VISIBLE);
        }
    }

    int postion;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 100) {
            OrderDetailEntity orderDetailEntity = (OrderDetailEntity) data.getSerializableExtra("OrderDetailEntity");
            lists.get(postion).setStatus_name(orderDetailEntity.getStatus_name());
            otcOrderAdapter.notifyDataSetChanged();
        }

    }
}
