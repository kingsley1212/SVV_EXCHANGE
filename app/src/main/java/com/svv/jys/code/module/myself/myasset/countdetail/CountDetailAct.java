package com.svv.jys.code.module.myself.myasset.countdetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.mvp.MvpActivity;
import com.svv.jys.code.common.entity.LtBorrowRecord;
import com.svv.jys.code.common.entity.LtRecordEntity;
import com.svv.jys.code.common.entity.LtTransferRecord;
import com.svv.jys.code.common.entity.LtUserCoinInfoentity;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.common.view.selfview.ProgressPercentView;
import com.svv.jys.code.module.main.MainAct;
import com.svv.jys.code.module.myself.myasset.countdetail.adapter.LtBorrowRecordAdapter;
import com.svv.jys.code.module.myself.myasset.countdetail.adapter.LtRecordAdapter;
import com.svv.jys.code.module.myself.myasset.countdetail.adapter.LtTransferRecordAdapter;
import com.svv.jys.code.module.myself.myasset.countdetail.borrow.BorrowAct;
import com.svv.jys.code.module.myself.myasset.countdetail.model.ICountDetailModel;
import com.svv.jys.code.module.myself.myasset.countdetail.presenter.CountDetailPresenter;
import com.svv.jys.code.module.myself.myasset.countdetail.reimbursement.ReimbursementAct;
import com.svv.jys.code.module.myself.myasset.countdetail.transfer.TransferAct;
import com.svv.jys.code.module.myself.myasset.countdetail.view.ICountDetailView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/3 0003.
 */

public class CountDetailAct extends MvpActivity<ICountDetailView, ICountDetailModel, CountDetailPresenter> implements ICountDetailView, View.OnClickListener {
    private LtUserCoinInfoentity entity;
    private ProgressPercentView itemPercent;
    private XRecyclerView rvAppealNote;
    private String market;
    private TextView tv_type1,tv_type2,tv_able1,tv_able2,tv_freeze1,tv_freeze2,tv_borrow1,tv_borrow2,tv_risk_ratio,tv_transfer,tv_borrowing,tv_count_name;
    private TabLayout tab_lt;
    private LinearLayout ll_to_lt_trade;
    private View appealNoData_ly;
    private int selectRecord=0;
    private int page=1;
    private List<LtRecordEntity.RowsBean> list1=new ArrayList<>();
    private List<LtTransferRecord.RowsBean> list2=new ArrayList<>();
    private List<LtBorrowRecord.RowsBean> list3=new ArrayList<>();
    private LtRecordAdapter ltRecordAdapter;
    private LtTransferRecordAdapter ltTransferRecordAdapter;
    private LtBorrowRecordAdapter ltBorrowRecordAdapter;
    private ProgressBar pregress;
    @Override
    public Context getMContext() {
        return this;
    }

    @Override
    public CountDetailPresenter initPresenter() {
        return new CountDetailPresenter();
    }

    @Override
    public void baseInitialization() {
        market = getIntent().getStringExtra("market");
    }

    @Override
    public int setR_Layout() {
        return R.layout.act_lt_count;
    }

    @Override
    public void viewInitialization() {
        setBackPress();
        setTitleTx(getString(R.string.myassetact_tab1));
        pregress=findViewById(R.id.pregress);
        ll_to_lt_trade=findViewById(R.id.ll_to_lt_trade);
        appealNoData_ly=findViewById(R.id.appealNoData_ly);
        itemPercent = (ProgressPercentView) $(R.id.itemPercent);
        tab_lt=findViewById(R.id.tab_lt);
        tv_transfer=findViewById(R.id.tv_transfer);
        tv_borrowing=findViewById(R.id.tv_borrowing);
        tv_borrowing.setOnClickListener(this);
        tv_transfer.setOnClickListener(this);
        ll_to_lt_trade.setOnClickListener(this);
        tv_type1=findViewById(R.id.tv_type1);
        tv_type2=findViewById(R.id.tv_type2);
        tv_able1=findViewById(R.id.tv_able1);
        tv_able2=findViewById(R.id.tv_able2);
        tv_freeze1=findViewById(R.id.tv_freeze1);
        tv_freeze2=findViewById(R.id.tv_freeze2);
        tv_borrow1=findViewById(R.id.tv_borrow1);
        tv_borrow2=findViewById(R.id.tv_borrow2);
        tv_risk_ratio=findViewById(R.id.tv_risk_ratio);
        tv_count_name=findViewById(R.id.tv_count_name);
        rvAppealNote = (XRecyclerView) $(R.id.rvAppealNote);
        rvAppealNote.setLayoutManager(new LinearLayoutManager(getMContext()));
        List<String> list=new ArrayList<>();
        list.add(getString(R.string.assat1));
        list.add(getString(R.string.assat2));
        list.add(getString(R.string.assat3));
        findViewById(R.id.no_data_ly);
        for (int i=0;i<list.size();i++){
            tab_lt.addTab(tab_lt.newTab().setText(list.get(i)));
        }
        tab_lt.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                page=1;
                switch (tab.getPosition()){
                    case 0:
                        selectRecord=0;
                        presenter.getLtRecord(page,market);
                        break;
                    case 1:
                        selectRecord=1;
                        presenter.getLtTransferRecord(page,market);
                        break;
                    case 2:
                        selectRecord=2;
                        presenter.getBorrowRecord(page,market);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        rvAppealNote.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page=1;
                if (selectRecord==0){
                    presenter.getLtRecord(page,market);
                }else if (selectRecord==1){
                    presenter.getLtTransferRecord(page,market);
                }else {
                    presenter.getBorrowRecord(page,market);
                }

            }

            @Override
            public void onLoadMore() {
                page++;
                if (selectRecord==0){
                    presenter.getLtRecord(page,market);
                }else if (selectRecord==1){
                    presenter.getLtTransferRecord(page,market);
                }else {
                    presenter.getBorrowRecord(page,market);
                }
            }
        });

    }

    @Override
    public void doBusiness() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getLtuserinfo(market);
        if (selectRecord==0){
            presenter.getLtRecord(page,market);
        }else if (selectRecord==1){
            presenter.getLtTransferRecord(page,market);
        }else {
            presenter.getBorrowRecord(page,market);
        }
    }

    @Override
    public void onClick(View view) {
        Bundle bundle=new Bundle();
        bundle.putSerializable("entity",entity);
        switch (view.getId()){
            case R.id.tv_borrowing:
                if (ToolUtils.isFastClick(view.getId())){
                    gotoActivity(BorrowAct.class,false,bundle);
                }

                break;
            case R.id.tv_transfer:
                if (ToolUtils.isFastClick(view.getId())){
                    gotoActivity(TransferAct.class,false,bundle);
                }

                break;
            case R.id.ll_to_lt_trade:
                Intent intent=new Intent(this,MainAct.class);
                intent.putExtra("id",3);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void setLtRecord(LtRecordEntity entity) {
//        LtRecordAdapter adapter=new LtRecordAdapter(this,entity.getRows());
//        rvAppealNote.setAdapter(adapter);
        if (page==1){
            refresh(entity.getRows());
        }else {
            loading(entity.getRows());
        }
    }

    public void loading(List<LtRecordEntity.RowsBean> list) {
        rvAppealNote.loadMoreComplete();
        //加载下一页
        if (list != null && list.size() != 0) {
            list1.addAll(list);
            ltRecordAdapter.notifyDataSetChanged();
        } else {
            rvAppealNote.setNoMore(true);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    rvAppealNote.setNoMore(false);//已经全部加载完成
                }
            }, 1000);
        }
    }

    public void refresh(List<LtRecordEntity.RowsBean> list) {
        //刷新
        if (list != null && list.size() != 0) {
            appealNoData_ly.setVisibility(View.GONE);
            rvAppealNote.setVisibility(View.VISIBLE);
            list1.clear();
            list1.addAll(list);
            ltRecordAdapter = new LtRecordAdapter(this,list1);
            rvAppealNote.setAdapter(ltRecordAdapter);
            ltRecordAdapter.notifyDataSetChanged();
            rvAppealNote.refreshComplete();//关闭刷新
        } else {
            rvAppealNote.setVisibility(View.GONE);
            appealNoData_ly.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void getLtUserinfo(LtUserCoinInfoentity entity) {
        this.entity=entity;
        tv_type1.setText(entity.getMarket().toUpperCase().split("_")[0]);
        tv_type2.setText(entity.getMarket().toUpperCase().split("_")[1]);
        tv_able1.setText(entity.getBuy_coin());
        tv_able2.setText(entity.getSell_coin());
        tv_freeze1.setText(entity.getBuy_coin_freeze());
        tv_freeze2.setText(entity.getSell_coin_freeze());
        tv_borrow1.setText(entity.getBuy_coin_debit());
        tv_borrow2.setText(entity.getSell_coin_debit());
        tv_count_name.setText(entity.getMarket().toUpperCase().replace("_","/"));
        tv_risk_ratio.setText(String.format(getString(R.string.countdetailact_item_percent_xx),entity.getRisk_ratio())+"%");
        pregress.setProgress(Integer.parseInt(ToolUtils.multiplyWithScale(entity.getPercent(),"100",0)));
    }

    @Override
    public void setLtTransferRecord(LtTransferRecord entity) {
//        LtTransferRecordAdapter adapter=new LtTransferRecordAdapter(this,entity.getRows());
//        rvAppealNote.setAdapter(adapter);
        if (page==1){
            refresh1(entity.getRows());
        }else {
            loading1(entity.getRows());
        }
    }

    public void loading1(List<LtTransferRecord.RowsBean> list) {
        rvAppealNote.loadMoreComplete();
        //加载下一页
        if (list != null && list.size() != 0) {
            list2.addAll(list);
            ltTransferRecordAdapter.notifyDataSetChanged();
        } else {
            rvAppealNote.setNoMore(true);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    rvAppealNote.setNoMore(false);//已经全部加载完成
                }
            }, 1000);
        }
    }

    public void refresh1(List<LtTransferRecord.RowsBean> list) {
        //刷新
        if (list != null && list.size() != 0) {
            appealNoData_ly.setVisibility(View.GONE);
            rvAppealNote.setVisibility(View.VISIBLE);
            list2.clear();
            list2.addAll(list);
            ltTransferRecordAdapter = new LtTransferRecordAdapter(this,list2);
            rvAppealNote.setAdapter(ltTransferRecordAdapter);
            ltTransferRecordAdapter.notifyDataSetChanged();
            rvAppealNote.refreshComplete();//关闭刷新
        } else {
            rvAppealNote.setVisibility(View.GONE);
            appealNoData_ly.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setborrowRecord(final LtBorrowRecord entity) {
//        LtBorrowRecordAdapter adapter=new LtBorrowRecordAdapter(this,entity.getRows());
//        adapter.setReimbursement(new LtBorrowRecordAdapter.ReimbursementImpl() {
//            @Override
//            public void toReimbursement(int position) {
//                Bundle bundle=new Bundle();
//                bundle.putString("id",entity.getRows().get(position).getId());
//                gotoActivity(ReimbursementAct.class,false,bundle);
//            }
//        });
//        rvAppealNote.setAdapter(adapter);
        if (page==1){
            refresh2(entity.getRows());
        }else {
            loading2(entity.getRows());
        }
    }
    public void loading2(List<LtBorrowRecord.RowsBean> list) {
        rvAppealNote.loadMoreComplete();
        //加载下一页
        if (list != null && list.size() != 0) {
            list3.addAll(list);
            ltBorrowRecordAdapter.notifyDataSetChanged();
        } else {
            rvAppealNote.setNoMore(true);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    rvAppealNote.setNoMore(false);//已经全部加载完成
                }
            }, 1000);
        }
    }

    public void refresh2(final List<LtBorrowRecord.RowsBean> list) {
        //刷新
        if (list != null && list.size() != 0) {
            appealNoData_ly.setVisibility(View.GONE);
            rvAppealNote.setVisibility(View.VISIBLE);
            list3.clear();
            list3.addAll(list);
            ltBorrowRecordAdapter = new LtBorrowRecordAdapter(this,list3);
            rvAppealNote.setAdapter(ltBorrowRecordAdapter);
            ltBorrowRecordAdapter.notifyDataSetChanged();
            rvAppealNote.refreshComplete();//关闭刷新
            ltBorrowRecordAdapter.setReimbursement(new LtBorrowRecordAdapter.ReimbursementImpl() {
                @Override
                public void toReimbursement(int position) {
                    Bundle bundle=new Bundle();
                    bundle.putSerializable("LtBorrowRecord",list3.get(position));
                    gotoActivity(ReimbursementAct.class,false,bundle);
                }
            });
        } else {
            rvAppealNote.setVisibility(View.GONE);
            appealNoData_ly.setVisibility(View.VISIBLE);
        }
    }

}
