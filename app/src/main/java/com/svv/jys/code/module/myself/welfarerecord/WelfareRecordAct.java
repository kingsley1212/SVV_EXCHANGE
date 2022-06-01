package com.svv.jys.code.module.myself.welfarerecord;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.svv.jys.R;
import com.svv.jys.code.common.base.mvp.MvpActivity;
import com.svv.jys.code.common.entity.WelfareRecordEntity;
import com.svv.jys.code.common.view.popup.PopupDialog1View;
import com.svv.jys.code.module.myself.welfarerecord.adapter.WelfareRecordAdapter;
import com.svv.jys.code.module.myself.welfarerecord.model.WelfareRecordModel;
import com.svv.jys.code.module.myself.welfarerecord.presenter.WelfareRecordPresenter;
import com.svv.jys.code.module.myself.welfarerecord.view.WelfareRecordView;

import java.util.ArrayList;
import java.util.List;

public class WelfareRecordAct extends MvpActivity<WelfareRecordView,WelfareRecordModel,WelfareRecordPresenter> implements WelfareRecordView {
    private int page=1;
    XRecyclerView xrv_welfare_record;
    View no_data;
    private List<WelfareRecordEntity.RowsBean> lists=new ArrayList<>();
    private WelfareRecordAdapter adapter;

    @Override
    public WelfareRecordPresenter initPresenter() {
        return new WelfareRecordPresenter();
    }

    @Override
    public void baseInitialization() {

    }

    @Override
    public int setR_Layout() {
        return R.layout.act_welfarerecord;
    }

    @Override
    public void viewInitialization() {
        setBackPress();
        setTitleTx(getString(R.string.activation_record));
        xrv_welfare_record = (XRecyclerView) $(R.id.xrv_welfare_record);
        xrv_welfare_record.setLayoutManager(new LinearLayoutManager(getMContext()));
        no_data = $(R.id.no_data);
        xrv_welfare_record.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                presenter.getWelfareRecord(page);
            }

            @Override
            public void onLoadMore() {
                page++;
                presenter.getWelfareRecord(page);
            }
        });
    }

    @Override
    public void doBusiness() {
        presenter.getWelfareRecord(page);
    }

    @Override
    public Context getMContext() {
        return this;
    }

    public void loading(List<WelfareRecordEntity.RowsBean> list) {
        xrv_welfare_record.loadMoreComplete();
        //加载下一页
        if (list != null && list.size() != 0) {
            lists.addAll(list);
            adapter.notifyDataSetChanged();
        } else {
            xrv_welfare_record.setNoMore(true);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    xrv_welfare_record.setNoMore(false);//已经全部加载完成
                }
            }, 1000);
        }
    }

    public void refresh(List<WelfareRecordEntity.RowsBean> list) {
        //刷新
        if (list != null && list.size() != 0) {
            xrv_welfare_record.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);
            xrv_welfare_record.setVisibility(View.VISIBLE);
            lists.clear();
            lists.addAll(list);
            if (adapter == null) {
                adapter = new WelfareRecordAdapter(this, lists);
                xrv_welfare_record.setAdapter(adapter);
                adapter.setOnLingquListener(new WelfareRecordAdapter.OnLingquListener() {
                    @Override
                    public void onLingqu(int pos, final WelfareRecordEntity.RowsBean rowsBean) {
                        PopupDialog1View view = new PopupDialog1View(getMContext(), new PopupDialog1View.MClickLisnener() {
                            @Override
                            public void leftBtn() {

                            }

                            @Override
                            public void rightBtn() {
                                presenter.getwelfarereceive(rowsBean.getId());
                            }
                        });
                        view.showPop(xrv_welfare_record, getString(R.string.welfare_record_msg), getString(R.string.myselffragment_left_text), getString(R.string.myselffragment_right_text));
                    }
                });
            } else {
                adapter.notifyDataSetChanged();
            }
            xrv_welfare_record.refreshComplete();//关闭刷新
        } else {
            xrv_welfare_record.setVisibility(View.GONE);
            no_data.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void setWelfareRecord(List<WelfareRecordEntity.RowsBean> list) {
        if (page == 1) {
            refresh(list);
        } else {
            loading(list);
        }
    }

    @Override
    public void lingquSuccese() {
        presenter.getWelfareRecord(1);
    }
}
