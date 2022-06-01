package com.svv.jys.code.module.myself.myasset.otc.otctrading.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.MBaseAdapter;
import com.svv.jys.code.common.base.mvp.MvpFragment;
import com.svv.jys.code.common.entity.CountryEntity;
import com.svv.jys.code.common.entity.OtcAdvEntity;
import com.svv.jys.code.common.entity.OtcCoinEntity;
import com.svv.jys.code.common.entity.PopEntity;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.fabi.fragment.adapter.BuyOrSellAdapter1;
import com.svv.jys.code.module.myself.myasset.otc.otctrading.fragment.adapter.PopAdapter;
import com.svv.jys.code.module.myself.myasset.otc.otctrading.fragment.model.OtcBuyOrSellmodel;
import com.svv.jys.code.module.myself.myasset.otc.otctrading.fragment.presenter.OtcBuyOrSellPersenter;
import com.svv.jys.code.module.myself.myasset.otc.otctrading.fragment.view.OtcBuyOrSellView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.pedaily.yc.ycdialoglib.selectDialog.CustomSelectDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by js on 2018/6/8.
 */

public class OtcBuyOrSellFrag extends MvpFragment<OtcBuyOrSellView,OtcBuyOrSellmodel,OtcBuyOrSellPersenter> implements OtcBuyOrSellView, View.OnClickListener {
    private LinearLayout ll_otc_buy_sell,ll_otc_country,ll_otc_money,ll_otc_pay;
    private TextView tv_otc_country,tv_otc_pay;
    private XRecyclerView xrv_buy_sell;
    private RelativeLayout rl_adv_select;
    private String trade_type;
    private FrameLayout frame;
    private int page=1;
    private TabLayout tab_market2;
    private String money,pay,country,coin,coinname;
    private PopupWindow popupWindow;
    private ImageView iv_otc_country,iv_otc_money,iv_otc_pay;
    private List<OtcAdvEntity.RowsBean> advlist=new ArrayList<>();
    private View no_data_ly;
    private BuyOrSellAdapter1 buyOrSellAdapter;
    boolean isFirst;
    @Override
    public Context getMContext() {
        return getActivity();
    }
    public static OtcBuyOrSellFrag newInstance(String trade_type,String coinname) {
        Bundle args = new Bundle();
        args.putString("trade_type", trade_type);
        args.putString("coinname", coinname);
        OtcBuyOrSellFrag fragment = new OtcBuyOrSellFrag();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public OtcBuyOrSellPersenter initPresenter() {
        return new OtcBuyOrSellPersenter();
    }

    @Override
    public void onWakeBussiness() {

    }

    @Override
    public void baseInitialization() {
        trade_type=getArguments().getString("trade_type");
        coinname=getArguments().getString("coinname");
        isFirst=getActivity().getIntent().getBooleanExtra("isFirst",false);
    }

    @Override
    public int setR_Layout() {
        return R.layout.frag_otc_buy_sell;
    }

    @Override
    public void viewInitialization() {
        tab_market2= (TabLayout) $(R.id.tab_market2);
        no_data_ly=$(R.id.no_data_ly);
        iv_otc_country= (ImageView) $(R.id.iv_otc_country);
        iv_otc_money= (ImageView) $(R.id.iv_otc_money);
        iv_otc_pay= (ImageView) $(R.id.iv_otc_pay);
        frame= (FrameLayout) $(R.id.frame);
        rl_adv_select= (RelativeLayout) $(R.id.rl_adv_select);
        ll_otc_buy_sell= (LinearLayout) $(R.id.ll_otc_buy_sell);
        ll_otc_country= (LinearLayout) $(R.id.ll_otc_country);
        ll_otc_money= (LinearLayout) $(R.id.ll_otc_money);
        ll_otc_pay= (LinearLayout) $(R.id.ll_otc_pay);
        ll_otc_country.setOnClickListener(this);
        ll_otc_money.setOnClickListener(this);
        ll_otc_pay.setOnClickListener(this);
        tv_otc_country= (TextView) $(R.id.tv_otc_country);
        tv_otc_pay= (TextView) $(R.id.tv_otc_pay);
        xrv_buy_sell= (XRecyclerView) $(R.id.xrv_buy_sell);
        xrv_buy_sell.setLayoutManager(new LinearLayoutManager(getActivity()));
        list.add(new PopEntity(getString(R.string.OtcBuyOrSellFrag_all_zffs),"",true));
        list.add(new PopEntity(getString(R.string.OtcBuyOrSellFrag_bank_zhuanz),"bank",false));
        list.add(new PopEntity(getString(R.string.OtcBuyOrSellFrag_zfb),"alipay",false));
        list.add(new PopEntity(getString(R.string.OtcBuyOrSellFrag_vchact),"wechat",false));
        xrv_buy_sell.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page=1;
                presenter.getOtcAdv(page,trade_type,money,country,pay,coin);
            }

            @Override
            public void onLoadMore() {
                page++;
                presenter.getOtcAdv(page,trade_type,money,country,pay,coin);
            }
        });

                tab_market2.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        if (!isFirst){
                            page=1;
                            coin=otcCoinEntities.get(tab.getPosition()).getName();
                            presenter.getOtcAdv(page,trade_type,money,country,pay,otcCoinEntities.get(tab.getPosition()).getName());
                        }

                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {

                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {

                    }
                });
            }



    public void loading(List<OtcAdvEntity.RowsBean> list) {
        xrv_buy_sell.loadMoreComplete();
        //加载下一页
        if (list != null && list.size() != 0) {
            advlist.addAll(list);
            buyOrSellAdapter.notifyDataSetChanged();
        } else {
            xrv_buy_sell.setNoMore(true);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    xrv_buy_sell.setNoMore(false);//已经全部加载完成
                }
            }, 1000);
        }
    }

    public void refresh(List<OtcAdvEntity.RowsBean> list) {
        //刷新
        if (list != null && list.size() != 0) {
            no_data_ly.setVisibility(View.GONE);
            xrv_buy_sell.setVisibility(View.VISIBLE);
            advlist.clear();
            advlist.addAll(list);
         /*   buyOrSellAdapter = new BuyOrSellAdapter1(getActivity(),advlist,trade_type);
            buyOrSellAdapter.setOnClickLinster(new BuyOrSellAdapter1.OnClickLinsters() {
                @Override
                public void toBuy(int position) {
                    Bundle bundle=new Bundle();
                    bundle.putSerializable("id",advlist.get(position).getId());
                    bundle.putString("trade_type",trade_type);
                    gotoActivity(BuyOrSellAct.class,false,bundle);
                }

                @Override
                public void toQureyUser(int position) {
                    Bundle bundle=new Bundle();
                    bundle.putString("uid",advlist.get(position).getUser_id());
                    gotoActivity(UserInfoAct.class,false,bundle);
                }
            });*/
            xrv_buy_sell.setAdapter(buyOrSellAdapter);
            buyOrSellAdapter.notifyDataSetChanged();
            xrv_buy_sell.refreshComplete();//关闭刷新
        } else {
            xrv_buy_sell.setVisibility(View.GONE);
            no_data_ly.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void doBusiness() {
        presenter.getOtcCoin();

    }
    List<OtcCoinEntity> otcCoinEntities;
    @Override
    public void setOtcCoin(final List<OtcCoinEntity> list) {
        otcCoinEntities=list;
        for (int i=0;i<list.size();i++){
            tab_market2.addTab(tab_market2.newTab().setText(list.get(i).getEnglish()));
            if (ToolUtils.isNull(coinname)){
                presenter.getOtcAdv(1,trade_type,money,country,pay,list.get(0).getName());
            }else {
                if (list.get(i).getName().equals(coinname)){
                    tab_market2.getTabAt(i).select();
                    presenter.getOtcAdv(1,trade_type,money,country,pay,coinname);
                    isFirst=false;
                }
            }
        }

    }
    List<PopEntity> countryEntities;
    @Override
    public void setOtcAdv(List<OtcAdvEntity.RowsBean> list) {
        if (page==1){
            refresh(list);
        }else {
            loading(list);
        }
    }

    @Override
    public void setCountry(List<CountryEntity> list) {
        List<PopEntity> listCountry=new ArrayList<>();
        listCountry.add(new PopEntity(getString(R.string.OtcBuyOrSellFrag_all),"",true));
        if (list!=null&&list.size()!=0){
            for (int i=0;i<list.size();i++){
                CountryEntity entity=list.get(i);
                listCountry.add(new PopEntity(entity.getChinese(),entity.getId(),false));
            }
        }
        showCountry(listCountry);
        countryEntities=listCountry;
    }
    public void showCountry(final List<PopEntity> list){
        View pop_pay=initPop(R.layout.pop_pay);
        RecyclerView rv_pay=pop_pay.findViewById(R.id.rv_pay);
        rv_pay.setLayoutManager(new LinearLayoutManager(getActivity()));
        final PopAdapter adapter=new PopAdapter(list,getActivity());
        adapter.setOnItemClick(new MBaseAdapter.MOnItemClickListener() {
            @Override
            public void onItemClick(int position, Object o) {
                for (int i=0;i<list.size();i++){
                    list.get(i).setSelect(false);
                }
                list.get(position).setSelect(true);
                adapter.notifyDataSetChanged();
                tv_otc_country.setText(list.get(position).getName());
                country=list.get(position).getId();
                presenter.getOtcAdv(1,trade_type,money,country,pay,coin);
                popupWindow.dismiss();
            }
        });
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                frame.setBackgroundColor(Color.parseColor("#ffffff"));
                iv_otc_country.setSelected(false);
            }
        });
        rv_pay.setAdapter(adapter);
        showPop();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ll_otc_country:
                iv_otc_country.setSelected(true);
                if (countryEntities==null){
                    presenter.getCountries();
                }else {
                    showCountry(countryEntities);
                }
                break;
            case R.id.ll_otc_money:
                iv_otc_money.setSelected(true);
                final View pop_money=initPop(R.layout.popup_money);
                final EditText et_pop_money=pop_money.findViewById(R.id.et_pop_money);
                TextView tv_pop_comfir=pop_money.findViewById(R.id.tv_pop_comfir);
                pop_money.findViewById(R.id.rl_pop2).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        popupWindow.dismiss();
                        iv_otc_money.setSelected(false);
                    }
                });
                tv_pop_comfir.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        money=et_pop_money.getText().toString().trim();
                        presenter.getOtcAdv(1,trade_type,money,country,pay,coin);
                        popupWindow.dismiss();
                    }
                });
                if (!ToolUtils.isNull(money)){
                    et_pop_money.setText(money);
                }
                showPop();
                break;
            case R.id.ll_otc_pay:
                iv_otc_pay.setSelected(true);
                showCustomDialog();
                break;
        }
    }
    final List<PopEntity> list=new ArrayList<>();
    public View initPop(int resId){
        popupWindow = new PopupWindow(getActivity());
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        View popview=LayoutInflater.from(getActivity()).inflate(resId, null);
        popupWindow.setContentView(popview);

        return popview;
    }
    public void showPop(){
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x000000));
        popupWindow.setOutsideTouchable(false);
        popupWindow.setFocusable(true);
        popupWindow.showAsDropDown(rl_adv_select);
    }


    private void showCustomDialog() {
        final List<String> names = new ArrayList<>();
        names.add(getString(R.string.OtcBuyOrSellFrag_all_zffs));
        names.add(getString(R.string.OtcBuyOrSellFrag_bank_zhuanz));
        names.add(getString(R.string.OtcBuyOrSellFrag_zfb));
        names.add(getString(R.string.OtcBuyOrSellFrag_vchact));

        showDialog(new CustomSelectDialog.SelectDialogListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tv_otc_pay.setText(names.get(position));
                pay=list.get(position).getId();
                presenter.getOtcAdv(1,trade_type,money,country,pay,coin);
                iv_otc_pay.setSelected(false);
            }
        }, names);
    }

    private CustomSelectDialog showDialog(CustomSelectDialog.SelectDialogListener listener,
                                          List<String> names) {
        CustomSelectDialog dialog = new CustomSelectDialog(((Activity) getMContext()),
                R.style.transparentFrameWindowStyle, listener, names);
        dialog.setItemColor(R.color.colorAccent, R.color.colorPrimary);
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                iv_otc_pay.setSelected(false);
            }
        });
        //判断activity是否finish
        if (!((Activity) getMContext()).isFinishing()) {
            dialog.show();
        }
        return dialog;
    }

}
