package com.svv.jys.code.module.business.c2cbusiness.record.base;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.svv.jys.R;
import com.svv.jys.code.common.base.mvp.MvpActivity;
import com.svv.jys.code.common.entity.C2cRecordEntity;
import com.svv.jys.code.common.utils.DialogUtil;
import com.svv.jys.code.common.view.dialog.LookC2CDialogFragment;
import com.svv.jys.code.common.view.popup.PopupDialogView;
import com.svv.jys.code.module.business.c2cbusiness.record.base.adapter.C2CRecordAdapter;
import com.svv.jys.code.module.business.c2cbusiness.record.base.model.IC2CRecordModel;
import com.svv.jys.code.module.business.c2cbusiness.record.base.presenter.C2CRecordPresenter;
import com.svv.jys.code.module.business.c2cbusiness.record.base.view.IC2CRecordView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by 74099 on 2018/7/11.
 */

public class C2CRecordAct extends MvpActivity<IC2CRecordView, IC2CRecordModel, C2CRecordPresenter> implements
        IC2CRecordView {
    private XRecyclerView c2c_recycle;
    private C2CRecordAdapter mC2CRecordAdapter;
    private int page = 1;
    private String coin = "";
    private List<C2cRecordEntity> lists;

    @Override
    public Context getMContext() {
        return this;
    }

    @Override
    public C2CRecordPresenter initPresenter() {
        return new C2CRecordPresenter();
    }

    @Override
    public void baseInitialization() {
        coin = getIntent().getStringExtra("coin");
    }

    @Override
    public int setR_Layout() {
        return R.layout.act_c2crecord;
    }

    @Override
    public void viewInitialization() {
        setBackPress();
        setTitleTx(getString(R.string.c2c_trade_record));
        lists = new ArrayList<>();
        c2c_recycle = findViewById(R.id.c2c_recycle);
        c2c_recycle.setLayoutManager(new LinearLayoutManager(this));
        c2c_recycle.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                presenter.getC2CRecordList(page);
            }

            @Override
            public void onLoadMore() {
                page++;
                presenter.getC2CRecordList(page);
            }
        });
    }

    @Override
    public void doBusiness() {

    }

    public void refresh(List<C2cRecordEntity> list) {
        //刷新
        if (list != null && list.size() != 0) {
//            xrv_pl.setVisibility(View.VISIBLE);
//            include_nodata.setVisibility(View.GONE);
            lists.clear();
            lists.addAll(list);
            if (mC2CRecordAdapter == null) {
                mC2CRecordAdapter = new C2CRecordAdapter(lists, this);
                c2c_recycle.setAdapter(mC2CRecordAdapter);
                mC2CRecordAdapter.setOnRecordClick(new C2CRecordAdapter.OnRecordClick() {
                    @Override
                    public void doCancel(final String id, View v) {
                        PopupDialogView view = new PopupDialogView(getMContext(), new PopupDialogView.MClickLisnener() {
                            @Override
                            public void leftBtn() {

                            }

                            @Override
                            public void rightBtn() {
                                presenter.doCancel(id);

                            }
                        });
                        view.showPop(v, getString(R.string.prompt), getString(R.string.isno_undo_order), getString(R
                                .string.dialog_cancel), getString(R.string.dialog_commit));
                    }

                    @Override
                    public void doLook(C2cRecordEntity c2cRecordEntity) {
                        DialogUtil.showLookDialog(C2CRecordAct.this, getString(R.string.payment_info),
                                c2cRecordEntity, new LookC2CDialogFragment.OnTipsListener() {
                                    @Override
                                    public void onCancel() {

                                    }
                                });
                    }
                });
            } else {
                mC2CRecordAdapter.notifyDataSetChanged();
            }
            c2c_recycle.refreshComplete();//关闭刷新
        } else {
//            xrv_pl.setVisibility(View.GONE);
//            include_nodata.setVisibility(View.VISIBLE);
        }
    }

    public void loading(List<C2cRecordEntity> list) {
        c2c_recycle.loadMoreComplete();
        //加载下一页
        if (list != null && list.size() != 0) {
            lists.addAll(list);
            mC2CRecordAdapter.notifyDataSetChanged();
        } else {
            c2c_recycle.setNoMore(true);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    c2c_recycle.setNoMore(false);//已经全部加载完成
                }
            }, 1000);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getC2CRecordList(page);
    }

    @Override
    public void getC2CRecordListSuccess(List<C2cRecordEntity> s) {
        if (page == 1) {
            refresh(s);
        } else {
            loading(s);
        }
    }

    /**
     * 撤单成功
     */
    @Override
    public void doCancelSuccess() {
        presenter.getC2CRecordList(1);
    }
}
