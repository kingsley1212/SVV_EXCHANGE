package com.svv.jys.code.module.myself.myadv;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.svv.jys.R;
import com.svv.jys.code.common.base.mvp.MvpActivity;
import com.svv.jys.code.common.entity.AdvListEntity;
import com.svv.jys.code.common.utils.T;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.common.view.popup.PopupDialogView;
import com.svv.jys.code.common.view.popup.PopupNewSelectorView;
import com.svv.jys.code.module.myself.myadv.adapter.MyAdvAdapter;
import com.svv.jys.code.module.myself.myadv.model.MyAdvModel;
import com.svv.jys.code.module.myself.myadv.presenter.MyAdvPresenter;
import com.svv.jys.code.module.myself.myadv.view.MyAdvView;
import com.svv.jys.code.module.myself.myasset.otc.otctrading.fabu.sell.OtcFabuSellAct;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by js on 2018/7/27.
 */

public class MyAdvAct extends MvpActivity<MyAdvView,MyAdvModel,MyAdvPresenter> implements MyAdvView{
    private XRecyclerView xrv_adv;
    private MyAdvAdapter advAdapter;
    private View no_data_ly;
    private int page = 1;
    private List<AdvListEntity.RowsBean> list1 = new ArrayList<>();
    private TabLayout tab_market2;
    private PopupNewSelectorView popup;

    @Override
    public Context getMContext() {
        return this;
    }

    @Override
    public MyAdvPresenter initPresenter() {
        return new MyAdvPresenter();
    }

    @Override
    public void baseInitialization() {

    }

    @Override
    public int setR_Layout() {
        return R.layout.act_adv;
    }

    @Override
    public void viewInitialization() {
        setBackPress();
        setTitleTx(getString(R.string.menu_tv2));
        xrv_adv=findViewById(R.id.xrv_adv);
        no_data_ly = findViewById(R.id.no_data_ly);
        xrv_adv.setLayoutManager(new LinearLayoutManager(this));
        tab_market2 = findViewById(R.id.tab_market2);
        xrv_adv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page=1;
                presenter.getAdvList(page,String.valueOf(tab_market2.getSelectedTabPosition()));
            }

            @Override
            public void onLoadMore() {
                page++;
                presenter.getAdvList(page,String.valueOf(tab_market2.getSelectedTabPosition()));
            }
        });

        tab_market2.addTab(tab_market2.newTab().setText(getString(R.string.jinxingz)));
        tab_market2.addTab(tab_market2.newTab().setText(getString(R.string.sold_out)));
        tab_market2.setTabMode(TabLayout.MODE_SCROLLABLE);
        tab_market2.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                xrv_adv.refresh();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void doBusiness() {
        presenter.getAdvList(page,String.valueOf(tab_market2.getSelectedTabPosition()));
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    public void loading(List<AdvListEntity.RowsBean> list) {
        xrv_adv.loadMoreComplete();
        //加载下一页
        if (list != null && list.size() != 0) {
            list1.addAll(list);
            advAdapter.notifyDataSetChanged();
        } else {
            xrv_adv.setNoMore(true);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    xrv_adv.setNoMore(false);//已经全部加载完成
                }
            }, 1000);
        }
    }

    public void refresh(List<AdvListEntity.RowsBean> list) {
        //刷新
        if (list != null && list.size() != 0) {
            no_data_ly.setVisibility(View.GONE);
            xrv_adv.setVisibility(View.VISIBLE);
            list1.clear();
            list1.addAll(list);
            if(advAdapter == null) {
                advAdapter = new MyAdvAdapter(this, list1);
                advAdapter.setOnAdvCaoZuo(new MyAdvAdapter.OnAdvCaoZuo() {
                    @Override
                    public void setBianji(int position, AdvListEntity.RowsBean rowsBean) {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("AdvListEntity", rowsBean);
                        bundle.putString("type", rowsBean.getType());
                        gotoActivity(OtcFabuSellAct.class, false, bundle);
                    }

                    @Override
                    public void setShangjia(final int position, final AdvListEntity.RowsBean rowsBean) {
                        PopupDialogView view = new PopupDialogView(getMContext(), new PopupDialogView.MClickLisnener() {
                            @Override
                            public void leftBtn() {

                            }

                            @Override
                            public void rightBtn() {
                                presenter.setStatus(rowsBean.getId(), rowsBean.getStatus().equals("0") ? "1" : "0");
                                positions = position;
                            }
                        });
                        view.showPop(no_data_ly, getString(R.string.otc_adv_ts), rowsBean.getStatus().equals("0") ? getString(R.string.otc_adv_xiajia) : getString(R.string.otc_adv_shangjia), getString(R.string
                                .myselffragment_left_text), getString(R.string.myselffragment_right_text));
                    }
                });
                xrv_adv.setAdapter(advAdapter);
            }else {
                advAdapter.setList(list1);
            }
            advAdapter.notifyDataSetChanged();
            xrv_adv.refreshComplete();//关闭刷新
        } else {
            xrv_adv.setVisibility(View.GONE);
            no_data_ly.setVisibility(View.VISIBLE);
        }
    }


    int positions=0;
    @Override
    public void setAdvList(final AdvListEntity entity) {
        if (page == 1) {
            refresh(entity.getRows());
        } else {
            loading(entity.getRows());
        }
        $(R.id.title).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ToolUtils.isFastClick(view.getId())) {
                    if (popup == null) {
                        popup = new PopupNewSelectorView(getMContext(), entity.getCoin_name());
                        popup.setValue(presenter.coin.toUpperCase());
                        popup.setOnClickItem(new PopupNewSelectorView.OnClickItem() {
                            @Override
                            public void OnSelect(int position, String value) {
                                presenter.coin = entity.getCoin_name().get(position);
                                presenter.getAdvList(1, String.valueOf(tab_market2.getSelectedTabPosition()));
                            }
                        });
                    }
                    popup.showPop(xrv_adv);
                }
            }
        });
        if(TextUtils.isEmpty(presenter.coin)){
            presenter.coin = entity.getCoin_name().get(0);
        }
        setTitleTx(getString(R.string.menu_tv2)+presenter.coin.toUpperCase());
    }

    @Override
    public void setStatusSuccese() {
        T.showLong(this,list1.get(positions).getStatus().equals("0")?getString(R.string.otc_adv_xiajia_succese):getString(R.string.otc_adv_shangjia_succese));
        list1.remove(positions);
        advAdapter.notifyDataSetChanged();
    }
}
