package com.svv.jys.code.module.myself.myasset.otc;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.mvp.MvpActivity;
import com.svv.jys.code.common.entity.OtcCoinInfoEntity;
import com.svv.jys.code.common.entity.OtcRecordEntity;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.myasset.otc.adapter.OtcRecordAdapter;
import com.svv.jys.code.module.myself.myasset.otc.model.OtcDetailModel;
import com.svv.jys.code.module.myself.myasset.otc.otctrading.base.OtcTradeAct;
import com.svv.jys.code.module.myself.myasset.otc.otctransfer.OtcTransferAct;
import com.svv.jys.code.module.myself.myasset.otc.presenter.OtcDetailPersenter;
import com.svv.jys.code.module.myself.myasset.otc.view.OtcDetailView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by js on 2018/6/7.
 */

public class OtcDetailAct extends MvpActivity<OtcDetailView,OtcDetailModel,OtcDetailPersenter> implements OtcDetailView, View.OnClickListener {

    private String coin;
    private TextView tv_otc_name,tv_otc_able,tv_otc_freeze,tv_otc_zhehe,tv_otc_transfer,tv_otc_jiaoyi;
    private XRecyclerView xrv_trade_account;
    private View no_data_ly;
    private int page=1;
    List<OtcRecordEntity.RowsBean> lists=new ArrayList<>();
    private OtcRecordAdapter otcRecordAdapter;
    @Override
    public Context getMContext() {
        return this;
    }

    @Override
    public OtcDetailPersenter initPresenter() {
        return new OtcDetailPersenter();
    }

    @Override
    public void baseInitialization() {
        coin = getIntent().getStringExtra("coin");
    }

    @Override
    public int setR_Layout() {
        return R.layout.otc_detail;
    }

    @Override
    public void viewInitialization() {
        setBackPress();
        setTitleTx(getString(R.string.assat38));
        tv_otc_name=findViewById(R.id.tv_otc_name);
        tv_otc_able=findViewById(R.id.tv_otc_able);
        tv_otc_freeze=findViewById(R.id.tv_otc_freeze);
        tv_otc_zhehe=findViewById(R.id.tv_otc_zhehe);
        xrv_trade_account=findViewById(R.id.xrv_trade_account);
        no_data_ly=findViewById(R.id.no_data_ly);
        tv_otc_transfer=findViewById(R.id.tv_otc_transfer1);
        tv_otc_jiaoyi=findViewById(R.id.tv_otc_jiaoyi);
        tv_otc_transfer.setOnClickListener(this);
        tv_otc_jiaoyi.setOnClickListener(this);
        xrv_trade_account.setLayoutManager(new LinearLayoutManager(this));
        xrv_trade_account.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page=1;
                presenter.getOtcList(page,coin,true);
            }

            @Override
            public void onLoadMore() {
                page++;
                presenter.getOtcList(page,coin,true);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getOtcUserInfo(coin);
        presenter.getOtcList(page,coin,false);
    }

    @Override
    public void doBusiness() {

    }

    @Override
    public void setOtcInfo(OtcCoinInfoEntity entity) {
        tv_otc_name.setText(coin.toUpperCase());
        tv_otc_able.setText(entity.getAble());
        tv_otc_freeze.setText(entity.getFreeze());
        tv_otc_zhehe.setText(ToolUtils.multiplyWithScale(ToolUtils.add(entity.getAble(),entity.getFreeze()),entity.getCny_price(),2));
    }

    @Override
    public void setRecordList(OtcRecordEntity entity) {
        List<OtcRecordEntity.RowsBean> list= entity.getRows();
       if (page==1){
           refresh(list);
       }else {
           loading(list);
       }
       presenter.dismissLoading(this);
    }

    public void loading(List<OtcRecordEntity.RowsBean> list) {
        xrv_trade_account.loadMoreComplete();
        //加载下一页
        if (list != null && list.size() != 0) {
            lists.addAll(list);
            otcRecordAdapter.notifyDataSetChanged();
        } else {
            xrv_trade_account.setNoMore(true);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    xrv_trade_account.setNoMore(false);//已经全部加载完成
                }
            }, 1000);
        }
    }

    public void refresh(List<OtcRecordEntity.RowsBean> list) {
        //刷新
        if (list != null && list.size() != 0) {
            no_data_ly.setVisibility(View.GONE);
            xrv_trade_account.setVisibility(View.VISIBLE);
            lists.clear();
            lists.addAll(list);
            otcRecordAdapter = new OtcRecordAdapter(this,lists);
            xrv_trade_account.setAdapter(otcRecordAdapter);
            otcRecordAdapter.notifyDataSetChanged();
            xrv_trade_account.refreshComplete();//关闭刷新
        } else {
            xrv_trade_account.setVisibility(View.GONE);
            no_data_ly.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_otc_transfer1:
                if (ToolUtils.isFastClick(view.getId())){
                    gotoActivity(OtcTransferAct.class);
                }

                break;
            case R.id.tv_otc_jiaoyi:
                if (ToolUtils.isFastClick(view.getId())){
                    Bundle bundle=new Bundle();
                    bundle.putString("coin",coin);
                    bundle.putBoolean("isFirst",true);
                    gotoActivity(OtcTradeAct.class,false,bundle);
                }

                break;
        }
    }
}
