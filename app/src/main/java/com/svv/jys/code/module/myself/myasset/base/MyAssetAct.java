package com.svv.jys.code.module.myself.myasset.base;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.MBaseAdapter;
import com.svv.jys.code.common.base.mvp.MvpActivity;
import com.svv.jys.code.common.entity.HomeAssatEntity;
import com.svv.jys.code.common.entity.LtAssatEntity;
import com.svv.jys.code.common.entity.OtcAssatEntity;
import com.svv.jys.code.module.myself.myasset.base.adapter.MyAssetCount1Adapter;
import com.svv.jys.code.module.myself.myasset.base.adapter.MyAssetCount2Adapter;
import com.svv.jys.code.module.myself.myasset.base.adapter.MyAssetCount3Adapter;
import com.svv.jys.code.module.myself.myasset.base.model.IMyAssetModel;
import com.svv.jys.code.module.myself.myasset.base.presenter.MyAssetPresenter;
import com.svv.jys.code.module.myself.myasset.base.view.IMyAssetView;
import com.svv.jys.code.module.myself.myasset.countdetail.CountDetailAct;
import com.svv.jys.code.module.myself.myasset.jiaoyi.TradingAccountAct;
import com.svv.jys.code.module.myself.myasset.otc.OtcDetailAct;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/2 0002.
 */

public class MyAssetAct extends MvpActivity<IMyAssetView, IMyAssetModel, MyAssetPresenter> implements IMyAssetView {
    private TextView myassetTab1, myassetTab2,myassetTab3;
    private View myassetTab1_line, myassetTab2_line,no_data_ly,myassetTab3_line;
    private int assatType=0;
    private XRecyclerView rvListCount1, rvListCount2,rvListCount3;
    private MyAssetCount1Adapter count1Adapter;
    private MyAssetCount2Adapter count2Adapter;
    private MyAssetCount3Adapter count3Adapter;
    private int currentPage = 1;
    private String coinname;
    private EditText et_search;
    private List<HomeAssatEntity.Rows> lists=new ArrayList<>();
    private List<LtAssatEntity.Rows> lists1=new ArrayList<>();
    private List<OtcAssatEntity.RowsBean> lists2=new ArrayList<>();
    private boolean show1,show2,show3;
    @Override
    public MyAssetPresenter initPresenter() {
        return new MyAssetPresenter();
    }

    @Override
    public void baseInitialization() {

    }

    @Override
    public int setR_Layout() {
        return R.layout.act_myasset;
    }

    @Override
    public void viewInitialization() {
        setBackPress();
        setTitleTx(getString(R.string.myassetact_title));
        no_data_ly=$(R.id.no_data_ly);
        et_search = (EditText) $(R.id.et_search);
        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                coinname=et_search.getText().toString().trim();
                    if (assatType==0){
                        show1=true;
                        presenter.getAssatList(1,coinname,true);
                    }else if(assatType==1){
                        show2=true;
                        presenter.getLtUserCoin(1,coinname,true);
                    }else {
                        show3=true;
                        presenter.getqtcAssatList(1,coinname,true);
                    }



            }
        });
        //顶部tab栏
        myassetTab1 = (TextView) $(R.id.myassetTab1);
        myassetTab2 = (TextView) $(R.id.myassetTab2);
        myassetTab3 = (TextView) $(R.id.myassetTab3);
        myassetTab1_line = $(R.id.myassetTab1_line);
        myassetTab2_line = $(R.id.myassetTab2_line);
        myassetTab3_line = $(R.id.myassetTab3_line);
        rvListCount1 = (XRecyclerView) $(R.id.rvListCount1);
        rvListCount2 = (XRecyclerView) $(R.id.rvListCount2);
        rvListCount3 = (XRecyclerView) $(R.id.rvListCount3);
        rvListCount1.setLayoutManager(new LinearLayoutManager(getMContext()));
        rvListCount2.setLayoutManager(new LinearLayoutManager(getMContext()));
        rvListCount3.setLayoutManager(new LinearLayoutManager(getMContext()));
        changeTabDisplay(1);
        myassetTab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!myassetTab1.isSelected()){
                    changeTabDisplay(1);
                    currentPage=1;
                    assatType=0;
                    show2=false;
                    presenter.getAssatList(currentPage,coinname,false);
                    rvListCount2.setVisibility(View.GONE);
                    rvListCount3.setVisibility(View.GONE);
                }

            }
        });
        rvListCount1.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                currentPage = 1;
                presenter.getAssatList(currentPage,coinname,true);
            }

            @Override
            public void onLoadMore() {
                currentPage++;
                presenter.getAssatList(currentPage,coinname,true);
            }
        });
        myassetTab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!myassetTab2.isSelected()){
                    changeTabDisplay(2);
                    currentPage=1;
                    assatType=1;
                    show1=false;
                    rvListCount1.setVisibility(View.GONE);
                    rvListCount3.setVisibility(View.GONE);
                    presenter.getLtUserCoin(currentPage,coinname,false);
                }

            }
        });
        myassetTab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!myassetTab3.isSelected()){
                    changeTabDisplay(3);
                    currentPage=1;
                    assatType=2;
                    show3=false;
                    rvListCount1.setVisibility(View.GONE);
                    rvListCount2.setVisibility(View.GONE);
                    presenter.getqtcAssatList(currentPage,coinname,false);
                }

            }
        });
        rvListCount2.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                currentPage = 1;
                presenter.getLtUserCoin(currentPage,coinname,true);
            }

            @Override
            public void onLoadMore() {
                currentPage++;
                presenter.getLtUserCoin(currentPage,coinname,true);
            }
        });
        rvListCount3.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                currentPage = 1;
                presenter.getqtcAssatList(currentPage,coinname,true);
            }

            @Override
            public void onLoadMore() {
                currentPage++;
                presenter.getqtcAssatList(currentPage,coinname,true);
            }
        });
    }

    public void loading(List<HomeAssatEntity.Rows> list) {
        rvListCount1.loadMoreComplete();
        //加载下一页
        if (list != null && list.size() != 0) {
            lists.addAll(list);
            count1Adapter.notifyDataSetChanged();
        } else {
            rvListCount1.setNoMore(true);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    rvListCount1.setNoMore(false);//已经全部加载完成
                }
            }, 1000);
        }
    }

    public void refresh(List<HomeAssatEntity.Rows> list) {
        //刷新
        if (list != null && list.size() != 0) {
            no_data_ly.setVisibility(View.GONE);
            if (show1){
                rvListCount1.setVisibility(View.VISIBLE);
            }
            lists.clear();
            lists.addAll(list);
            if (count1Adapter == null) {
                count1Adapter = new MyAssetCount1Adapter(getMContext(), lists);
                rvListCount1.setAdapter(count1Adapter);

            } else {
                count1Adapter.notifyDataSetChanged();
            }
            count1Adapter.setOnItemClick(new MBaseAdapter.MOnItemClickListener() {
                @Override
                public void onItemClick(int position, Object o) {
                    Bundle bundle=new Bundle();
                    bundle.putString("coin",lists.get(position).getName());
                    gotoActivity(TradingAccountAct.class,false,bundle);
                }
            });
            rvListCount1.refreshComplete();//关闭刷新
        } else {
            rvListCount1.setVisibility(View.GONE);
            no_data_ly.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public Context getMContext() {
        return this;
    }

    /**
     * @param position 1--3
     */
    private void changeTabDisplay(int position) {
        myassetTab1.setSelected(false);
        myassetTab2.setSelected(false);
        myassetTab3.setSelected(false);
        myassetTab1_line.setSelected(false);
        myassetTab2_line.setSelected(false);
        myassetTab3_line.setSelected(false);
        rvListCount1.setVisibility(View.GONE);
        rvListCount2.setVisibility(View.GONE);
        rvListCount3.setVisibility(View.GONE);
        switch (position) {
            case 1:
                myassetTab1.setSelected(true);
                myassetTab1_line.setSelected(true);
                rvListCount1.setVisibility(View.VISIBLE);
                break;
            case 2:
                myassetTab2.setSelected(true);
                myassetTab2_line.setSelected(true);
                rvListCount2.setVisibility(View.VISIBLE);
                break;
            case 3:
                myassetTab3.setSelected(true);
                myassetTab3_line.setSelected(true);
                rvListCount3.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
    }

    @Override
    public void doBusiness() {
        presenter.getAssatList(currentPage,coinname,false);
    }

    @Override
    public void setAssatTransaction(HomeAssatEntity entity) {
        List<HomeAssatEntity.Rows> list=entity.getRows();
        if (currentPage == 1) {
            refresh(list);
        } else {
            loading(list);
        }

    }

    public void loading1(List<LtAssatEntity.Rows> list) {
        rvListCount2.loadMoreComplete();
        //加载下一页
        if (list != null && list.size() != 0) {
            lists1.addAll(list);
            count2Adapter.notifyDataSetChanged();
        } else {
            rvListCount2.setNoMore(true);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    rvListCount2.setNoMore(false);//已经全部加载完成
                }
            }, 1000);
        }
    }

    public void refresh1(List<LtAssatEntity.Rows> list) {
        //刷新
        if (list != null && list.size() != 0) {
            no_data_ly.setVisibility(View.GONE);
            if (show2){
                rvListCount2.setVisibility(View.VISIBLE);
            }
            lists1.clear();
            lists1.addAll(list);
            if (count2Adapter == null) {
                count2Adapter = new MyAssetCount2Adapter(getMContext(), lists1);
                rvListCount2.setAdapter(count2Adapter);

            } else {
                count2Adapter.notifyDataSetChanged();
            }
            count2Adapter.setOnItemClick(new MBaseAdapter.MOnItemClickListener() {
                @Override
                public void onItemClick(int position, Object o) {
                    Bundle bundle=new Bundle();
                    bundle.putString("market",lists1.get(position).getMarket());
                    gotoActivity(CountDetailAct.class,false,bundle);
                }
            });
            rvListCount2.refreshComplete();//关闭刷新
        } else {
            rvListCount2.setVisibility(View.GONE);
            no_data_ly.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setLtAssat(LtAssatEntity entity) {
        List<LtAssatEntity.Rows> list=entity.getRows();
        if (currentPage == 1) {
            refresh1(list);
        } else {
            loading1(list);
        }

    }

    public void loading2(List<OtcAssatEntity.RowsBean> list) {
        rvListCount3.loadMoreComplete();
        //加载下一页
        if (list != null && list.size() != 0) {
            lists2.addAll(list);
            count3Adapter.notifyDataSetChanged();
        } else {
            rvListCount3.setNoMore(true);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    rvListCount3.setNoMore(false);//已经全部加载完成
                }
            }, 1000);
        }
    }

    public void refresh2(List<OtcAssatEntity.RowsBean> list) {
        //刷新
        if (list != null && list.size() != 0) {
            no_data_ly.setVisibility(View.GONE);
            if (show3){
                rvListCount3.setVisibility(View.VISIBLE);
            }
            lists2.clear();
            lists2.addAll(list);
            if (count3Adapter == null) {
                count3Adapter = new MyAssetCount3Adapter(getMContext(), lists2);
                rvListCount3.setAdapter(count3Adapter);

            } else {
                count3Adapter.notifyDataSetChanged();
            }
            count3Adapter.setOnItemClick(new MBaseAdapter.MOnItemClickListener() {
                @Override
                public void onItemClick(int position, Object o) {
                    Bundle bundle=new Bundle();
                    bundle.putString("coin",lists2.get(position).getName());
                    gotoActivity(OtcDetailAct.class,false,bundle);
                }
            });
            rvListCount2.refreshComplete();//关闭刷新
        } else {
            rvListCount3.setVisibility(View.GONE);
            no_data_ly.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setOtcAssat(OtcAssatEntity otcAssat) {
        List<OtcAssatEntity.RowsBean> list=otcAssat.getRows();
        if (currentPage == 1) {
            refresh2(list);
        } else {
            loading2(list);
        }
    }

}
