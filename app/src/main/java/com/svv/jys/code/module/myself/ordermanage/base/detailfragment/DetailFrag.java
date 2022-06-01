package com.svv.jys.code.module.myself.ordermanage.base.detailfragment;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.MBaseAdapter;
import com.svv.jys.code.common.base.mvp.MvpFragment;
import com.svv.jys.code.common.entity.EntrustDetailEntity;
import com.svv.jys.code.common.entity.MarketOrederEntity;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.ordermanage.base.detailfragment.adapter.EntrustDetailAdapter;
import com.svv.jys.code.module.myself.ordermanage.base.detailfragment.model.DetailModel;
import com.svv.jys.code.module.myself.ordermanage.base.detailfragment.presenter.DetailPresenter;
import com.svv.jys.code.module.myself.ordermanage.base.detailfragment.view.DetailView;
import com.svv.jys.code.module.myself.ordermanage.base.entrustfragment.adapter.PopOrderAdapter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.svv.jys.code.module.net.req.GET_ORDER_DETAIL_REQ;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by js on 2018/5/24.
 */

public class DetailFrag extends MvpFragment<DetailView, DetailModel, DetailPresenter> implements DetailView {
    private XRecyclerView xrv_entrust;
    private View no_data, ll_shaixuan;
    private TextView noDataText;
    private ImageView iv_entrust;

    private EntrustDetailAdapter adapter;
    private PopupWindow popupWindow;
    private boolean isShowMarket, isBuy, isSell;
    private String selectType;
    private String selectCoin;


    public static DetailFrag newInstance() {
        DetailFrag fragment = new DetailFrag();
        return fragment;
    }

    @Override
    public Context getMContext() {
        return getActivity();
    }

    @Override
    public DetailPresenter initPresenter() {
        return new DetailPresenter();
    }

    @Override
    public void onWakeBussiness() {

    }

    @Override
    public void baseInitialization() {

    }

    @Override
    public int setR_Layout() {
        return R.layout.act_entrust;
    }

    @Override
    public void viewInitialization() {
        xrv_entrust = (XRecyclerView) $(R.id.xrv_entrust);
        xrv_entrust.setLayoutManager(new LinearLayoutManager(getMContext()));
        no_data = $(R.id.no_data);
        ll_shaixuan = $(R.id.ll_shaixuan);
        iv_entrust = (ImageView) $(R.id.iv_entrust);
        noDataText = (TextView) $(R.id.noDataText);
        noDataText.setText(getString(R.string.DetailFrag_zw_jyjl));

        xrv_entrust.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                presenter.setDatas(new ArrayList<EntrustDetailEntity.RowsBean>());
                presenter.getEntrustDetail();
            }

            @Override
            public void onLoadMore() {
                presenter.getEntrustDetail();
            }
        });

        ll_shaixuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (lists2 == null) {
                    presenter.getMarket();
                } else {
                    showPop(lists2);
                }
            }
        });
    }

    @Override
    public void doBusiness() {
        presenter.setDatas(new ArrayList<EntrustDetailEntity.RowsBean>());
    }

    @Override
    public void loading(List<EntrustDetailEntity.RowsBean> list) {
        xrv_entrust.loadMoreComplete();
        //加载下一页
        if (list != null && list.size() != 0) {
            presenter.addDatas(list);
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

    @Override
    public void refresh(List<EntrustDetailEntity.RowsBean> list) {
        //刷新
        if (list != null && list.size() != 0) {
            xrv_entrust.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.GONE);
            presenter.setDatas(list);
            if (adapter == null) {
                adapter = new EntrustDetailAdapter(getMContext(), presenter.getDatas());
                xrv_entrust.setAdapter(adapter);
            } else {
                adapter.setList(presenter.getDatas());
                adapter.notifyDataSetChanged();
            }
            xrv_entrust.refreshComplete();//关闭刷新
        } else {
            xrv_entrust.setVisibility(View.GONE);
            no_data.setVisibility(View.VISIBLE);
        }
    }

    private List<MarketOrederEntity> lists2;

    @Override
    public void setMarket(List<MarketOrederEntity> list) {
        showPop(list);
        lists2 = list;
    }

    public void setRefershData(String market,String type){
        if (xrv_entrust == null)
            return;
        if (presenter.req == null){
            presenter.req = new GET_ORDER_DETAIL_REQ();
        }
        presenter.req.market = market;
        presenter.req.type = type;
        xrv_entrust.refresh();
    }

    public void showPop(final List<MarketOrederEntity> list) {
        iv_entrust.setSelected(true);
        popupWindow = new PopupWindow(getActivity());
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        final View popview = LayoutInflater.from(getActivity()).inflate(R.layout.pop_entrust_shaixuan, null);
        popupWindow.setContentView(popview);
        final RecyclerView recyclerView = popview.findViewById(R.id.rv_order_entrust);
        final TextView tv_market_select = popview.findViewById(R.id.tv_market_select);
        RelativeLayout rl_market = popview.findViewById(R.id.rl_market);
        final EditText et_coin = popview.findViewById(R.id.et_coin);
        final TextView tv_type_buy = popview.findViewById(R.id.tv_type_buy);
        final TextView tv_type_sell = popview.findViewById(R.id.tv_type_sell);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isSelect()) {
                tv_market_select.setText(list.get(i).getName().toUpperCase());
            }
        }
        if (isBuy) {
            tv_type_buy.setSelected(true);
            tv_type_buy.setTextColor(0xffffffff);
        } else {
            tv_type_buy.setSelected(false);
            tv_type_buy.setTextColor(0xff444444);
        }
        if (isSell) {
            tv_type_sell.setSelected(true);
            tv_type_sell.setTextColor(0xffffffff);
        } else {
            tv_type_sell.setSelected(false);
            tv_type_sell.setTextColor(0xff444444);
        }
        tv_type_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectType = "1";
                isBuy = true;
                isSell = false;
                tv_type_buy.setSelected(isBuy);
                tv_type_sell.setSelected(isSell);
                tv_type_buy.setTextColor(0xffffffff);
                tv_type_sell.setTextColor(0xff444444);
            }
        });
        tv_type_sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectType = "2";
                isBuy = false;
                isSell = true;
                tv_type_buy.setSelected(isBuy);
                tv_type_sell.setSelected(isSell);
                tv_type_sell.setTextColor(0xffffffff);
                tv_type_buy.setTextColor(0xff444444);
            }
        });
        if (isShowMarket) {
            recyclerView.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.GONE);
        }
        popview.findViewById(R.id.rl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });
        rl_market.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isShowMarket = !isShowMarket;
                if (isShowMarket) {
                    recyclerView.setVisibility(View.VISIBLE);
                } else {
                    recyclerView.setVisibility(View.GONE);
                }
            }
        });
        popview.findViewById(R.id.tv_chongzhi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.req.market = "";
                presenter.req.type = "";
                isBuy=false;
                isSell=false;
                isShowMarket=false;
                presenter.setDatas(new ArrayList<EntrustDetailEntity.RowsBean>());
                presenter.getEntrustDetail();
                popupWindow.dismiss();
            }
        });
        popview.findViewById(R.id.tv_queren).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String coin = et_coin.getText().toString().trim();
                if (!ToolUtils.isNull(coin)) {
                    presenter.req.market = coin.toLowerCase() + "_" + selectCoin;
                }
                presenter.req.type = selectType;
                presenter.setDatas(new ArrayList<EntrustDetailEntity.RowsBean>());
                presenter.getEntrustDetail();
                popupWindow.dismiss();
            }
        });
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        final PopOrderAdapter adapter = new PopOrderAdapter(getActivity(), list);
        adapter.setOnItemClick(new MBaseAdapter.MOnItemClickListener() {
            @Override
            public void onItemClick(int position, Object o) {
                tv_market_select.setText(list.get(position).getName().toUpperCase());
                recyclerView.setVisibility(View.GONE);
                selectCoin = list.get(position).getName();
                for (int i = 0; i < list.size(); i++) {
                    list.get(i).setSelect(false);
                }
                list.get(position).setSelect(true);
                adapter.notifyDataSetChanged();
            }
        });
        recyclerView.setAdapter(adapter);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x000000));
        popupWindow.setOutsideTouchable(false);
        popupWindow.setFocusable(true);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
//                frame.setBackgroundColor(Color.parseColor("#ffffff"));
                iv_entrust.setSelected(false);
            }
        });
        popupWindow.showAsDropDown(ll_shaixuan);
    }

}
