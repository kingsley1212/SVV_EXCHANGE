package com.svv.jys.code.module.myself.ordermanage.base.entrustfragment;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.MBaseAdapter;
import com.svv.jys.code.common.base.mvp.MvpFragment;
import com.svv.jys.code.common.entity.EntrustEntity;
import com.svv.jys.code.common.entity.MarketOrederEntity;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.common.view.popup.PopupDialogView;
import com.svv.jys.code.module.myself.ordermanage.base.entrustfragment.adapter.EntrustAdapter;
import com.svv.jys.code.module.myself.ordermanage.base.entrustfragment.adapter.PopOrderAdapter;
import com.svv.jys.code.module.myself.ordermanage.base.entrustfragment.model.EntrustModel;
import com.svv.jys.code.module.myself.ordermanage.base.entrustfragment.presenter.EntrustPresenter;
import com.svv.jys.code.module.myself.ordermanage.base.entrustfragment.view.EntrustView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by js on 2018/5/24.
 */

public class EntrustFrag extends MvpFragment<EntrustView, EntrustModel, EntrustPresenter> implements EntrustView,
        View.OnClickListener {
    String stutas;
    XRecyclerView xrv_entrust;
    List<EntrustEntity.RowsBean> lists = new ArrayList<>();
    View no_data;
    TextView noDataText;
    private LinearLayout ll_shaixuan;
    private ImageView iv_entrust;
    int page = 1;
    private String type;
    private String market;
    private EntrustAdapter adapter;
    private PopupWindow popupWindow;
    private RelativeLayout frame;
    private boolean isShowMarket, isBuy, isSell;
    private String selectType;

    @Override
    public Context getMContext() {
        return getActivity();
    }

    public static EntrustFrag newInstance(String stutas) {
        Bundle args = new Bundle();
        args.putString("stutas", stutas);
        EntrustFrag fragment = new EntrustFrag();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public EntrustPresenter initPresenter() {
        return new EntrustPresenter();
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
        return R.layout.act_entrust;
    }

    @Override
    public void viewInitialization() {
        iv_entrust = (ImageView) $(R.id.iv_entrust);
        frame = (RelativeLayout) $(R.id.frame1);
        ll_shaixuan = (LinearLayout) $(R.id.ll_shaixuan);
        ll_shaixuan.setOnClickListener(this);
        xrv_entrust = (XRecyclerView) $(R.id.xrv_entrust);
        xrv_entrust.setLayoutManager(new LinearLayoutManager(getMContext()));
        no_data = $(R.id.no_data);
        noDataText = (TextView) $(R.id.noDataText);
        noDataText.setText(getString(R.string.EntrustFrag_zw_jyjl));
        xrv_entrust.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                presenter.getEntrust(page, stutas, market, type);
            }

            @Override
            public void onLoadMore() {
                page++;
                presenter.getEntrust(page, stutas, market, type);
            }
        });

    }

    public void setRefershData(String market,String type){
        this.market = market;
        this.type = type;
        xrv_entrust.refresh();
    }

    @Override
    public void doBusiness() {
        presenter.getEntrust(page, stutas, market, type);
    }

    public void loading(List<EntrustEntity.RowsBean> list) {
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

    public void refresh(List<EntrustEntity.RowsBean> list) {
        //刷新
        if (list != null && list.size() != 0) {
            xrv_entrust.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);
            xrv_entrust.setVisibility(View.VISIBLE);
            lists.clear();
            lists.addAll(list);
            if (adapter == null) {
                adapter = new EntrustAdapter(getMContext(), lists, stutas);
                adapter.setEntrustManager(new EntrustAdapter.EntrustManager() {
                    @Override
                    public void undoEntrust(final int position, View v) {
                        PopupDialogView view = new PopupDialogView(getMContext(), new PopupDialogView.MClickLisnener() {
                            @Override
                            public void leftBtn() {

                            }

                            @Override
                            public void rightBtn() {
                                presenter.undoEntrust(lists.get(position).getId());
                                positions = position;
                            }
                        });
                        view.showPop(v, getString(R.string.EntrustFrag_tip), getString(R.string
                                .EntrustFrag_are_you_sure), getString(R.string.myselffragment_left_text), getString(R
                                .string.myselffragment_right_text));
                    }
                });
                xrv_entrust.setAdapter(adapter);

            } else {
                adapter.notifyDataSetChanged();
            }
            xrv_entrust.refreshComplete();//关闭刷新
        } else {
            xrv_entrust.setVisibility(View.GONE);
            no_data.setVisibility(View.VISIBLE);
        }

    }


    @Override
    public void setEntrustList(final EntrustEntity entrustList) {
        if (page == 1) {
            refresh(entrustList.getRows());
        } else {
            loading(entrustList.getRows());
        }

        presenter.dismissLoading(getMContext());
    }

    private int positions;

    @Override
    public void undoSuccess() {
        lists.remove(positions);
        adapter.notifyDataSetChanged();
    }

    List<MarketOrederEntity> lists2;

    @Override
    public void setMarket(List<MarketOrederEntity> list) {
        showPop(list);
        lists2 = list;
    }

    String selectCoin;

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
                market = "";
                type = "";
                isBuy=false;
                isSell=false;
                isShowMarket=false;
                presenter.getEntrust(page, stutas, market, type);
                popupWindow.dismiss();
            }
        });
        popview.findViewById(R.id.tv_queren).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String coin = et_coin.getText().toString().trim();
                if (!ToolUtils.isNull(coin)) {
                    market = coin.toLowerCase() + "_" + selectCoin;
                }
                type = selectType;
                page = 1;
                presenter.getEntrust(page, stutas, market, type);
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


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_shaixuan:
                if (lists2 == null) {
                    presenter.getMarket();
                } else {
                    showPop(lists2);
                }
                break;
        }
    }
}
