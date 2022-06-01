package com.svv.jys.code.module.fabi.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.mvp.MvpFragment;
import com.svv.jys.code.common.entity.AdvSettingEntity;
import com.svv.jys.code.common.entity.CountryEntity;
import com.svv.jys.code.common.entity.OtcAdvEntity;
import com.svv.jys.code.common.entity.OtcCoinEntity;
import com.svv.jys.code.common.entity.PopEntity;
import com.svv.jys.code.module.fabi.base.FabiFrag;
import com.svv.jys.code.module.fabi.fragment.adapter.BuyOrSellAdapter1;
import com.svv.jys.code.module.fabi.fragment.model.FabiBuyOrSellmodel;
import com.svv.jys.code.module.fabi.fragment.presenter.FabiBuyOrSellPersenter;
import com.svv.jys.code.module.fabi.fragment.view.FabiBuyOrSellView;
import com.svv.jys.code.module.net.req.GET_OTC_ADV_REQ;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.pedaily.yc.ycdialoglib.selectDialog.CustomSelectDialog;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by js on 2018/8/1.
 */

public class FabiBuyOrSellFrag extends MvpFragment<FabiBuyOrSellView, FabiBuyOrSellmodel, FabiBuyOrSellPersenter> implements  FabiBuyOrSellView, View.OnClickListener {

    private LinearLayout ll_otc_buy_sell,ll_otc_country,ll_otc_money,ll_otc_pay;
    private TextView tv_otc_country,tv_otc_pay;
    private XRecyclerView xrv_buy_sell;
    private RelativeLayout rl_adv_select;
    private FrameLayout frame;
    private int page=1;
    private TabLayout tab_market2;
    private PopupWindow popupWindow;
    private ImageView iv_otc_country,iv_otc_money,iv_otc_pay;
    private List<OtcAdvEntity.RowsBean> advlist=new ArrayList<>();
    private View no_data_ly;
    private BuyOrSellAdapter1 buyOrSellAdapter;
    private List<AdvSettingEntity.IncomeBean.RowsBean> list=new ArrayList<>();
    public GET_OTC_ADV_REQ req = new GET_OTC_ADV_REQ();
    //身份
    private String identity;
    @Override
    public Context getMContext() {
        return getActivity();
    }

    public static FabiBuyOrSellFrag newInstance(String trade_type) {
        Bundle args = new Bundle();
        args.putString("trade_type", trade_type);
        FabiBuyOrSellFrag fragment = new FabiBuyOrSellFrag();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public FabiBuyOrSellPersenter initPresenter() {
        return new FabiBuyOrSellPersenter();
    }

    @Override
    public void onWakeBussiness() {
//        presenter.getOtcAdv(page,trade_type,money,country,pay,coin);

    }
    List<String> otcCoinEntities;
    @Override
    public void setOtcCoin(List<OtcCoinEntity> list) {


    }

    @Override
    public void setOtcAdv(List<OtcAdvEntity.RowsBean> list) {
        if (page==1){
            refresh(list);
        }else {
            loading(list);
        }
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
            buyOrSellAdapter = new BuyOrSellAdapter1(getActivity(),advlist);

            xrv_buy_sell.setAdapter(buyOrSellAdapter);
            buyOrSellAdapter.notifyDataSetChanged();
            xrv_buy_sell.refreshComplete();//关闭刷新
        } else {
            xrv_buy_sell.setVisibility(View.GONE);
            no_data_ly.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setCountry(List<CountryEntity> list) {
        /*List<PopEntity> listCountry=new ArrayList<>();
        listCountry.add(new PopEntity(getString(R.string.OtcBuyOrSellFrag_all),"",true));
        if (list!=null&&list.size()!=0){
            for (int i=0;i<list.size();i++){
                CountryEntity entity=list.get(i);
                listCountry.add(new PopEntity(entity.getChinese(),entity.getId(),false));
            }
        }
        showCountry(listCountry);
        countryEntities=listCountry;*/
    }

    @Override
    public void setData(AdvSettingEntity entity) {
      /*  list = entity.getIncome().getRows();
        AdvSettingEntity.IncomeBean.RowsBean bean = new AdvSettingEntity.IncomeBean.RowsBean();
        bean.setName(getString(R.string.OtcBuyOrSellFrag_all_zffs));
        list.add(0,bean);*/
        otcCoinEntities=entity.getOtc_coin();
        if(entity.getUser() !=null) {
            identity = entity.getUser().getIs_shoper();
        }else {
            identity = null;
        }
        if(tab_market2.getTabCount()==0) {
            for (int i = 0; i < otcCoinEntities.size(); i++) {
                tab_market2.addTab(tab_market2.newTab().setText(otcCoinEntities.get(i).toUpperCase()));
            }
        }
        if(TextUtils.isEmpty(req.coin)) {
            req.coin = otcCoinEntities.get(0);
        }
        if(buyOrSellAdapter ==null){
            presenter.getOtcAdv(1);
        }else if( buyOrSellAdapter.getItemCount() == 0) {
            presenter.getOtcAdv(1);
        }
        ((FabiFrag)getParentFragment()).setNoticeNum(entity.getNotice());
    }

    public String returnIdentity(){
        return identity;
    }

    @Override
    public void baseInitialization() {
        req.trade_type=getArguments().getString("trade_type");
    }

    @Override
    public int setR_Layout() {
        return R.layout.frag_fabi_buy_sell;
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
      /*  ll_otc_country= (LinearLayout) $(R.id.ll_otc_country);
        ll_otc_money= (LinearLayout) $(R.id.ll_otc_money);
        ll_otc_pay= (LinearLayout) $(R.id.ll_otc_pay);
        ll_otc_country.setOnClickListener(this);
        ll_otc_money.setOnClickListener(this);
        ll_otc_pay.setOnClickListener(this);*/
        tv_otc_country= (TextView) $(R.id.tv_otc_country);
        tv_otc_pay= (TextView) $(R.id.tv_otc_pay);
        xrv_buy_sell= (XRecyclerView) $(R.id.xrv_buy_sell);
        xrv_buy_sell.setLayoutManager(new LinearLayoutManager(getActivity()));

        xrv_buy_sell.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page=1;
                presenter.getOtcAdv(page);
            }

            @Override
            public void onLoadMore() {
                page++;
                presenter.getOtcAdv(page);
            }
        });

        tab_market2.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                page=1;
                req.coin=otcCoinEntities.get(tab.getPosition());
                presenter.getOtcAdv(page);
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
        if(req.trade_type.equals("0"))
            onShow();
    }

    public void onShow(){
        presenter.getAdvSetting();
    }

    List<PopEntity> countryEntities;

   /* public void showCountry(final List<PopEntity> list){
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
    }*/

    @Override
    public void onClick(View view) {
      /*  switch (view.getId()){
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
                popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        iv_otc_money.setSelected(false);
                    }
                });
                showPop();
                break;
            case R.id.ll_otc_pay:
                iv_otc_pay.setSelected(true);
                showCustomDialog();
                break;
        }*/
    }

    public View initPop(int resId){
        popupWindow = new PopupWindow(getActivity());
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        View popview= LayoutInflater.from(getActivity()).inflate(resId, null);
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
       /* final List<String> names = new ArrayList<>();
        for (AdvSettingEntity.IncomeBean.RowsBean bean : list){
            names.add(bean.getName());
        }
        showDialog(new CustomSelectDialog.SelectDialogListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tv_otc_pay.setText(names.get(position));
                pay=list.get(position).getId();
                presenter.getOtcAdv(1,trade_type,money,country,pay,coin);
                iv_otc_pay.setSelected(false);
            }
        }, names);*/
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

    public AdvSettingEntity getSettingEntity(){
        return presenter.settingEntity;
    }


    public void setREQ(GET_OTC_ADV_REQ req) {
        this.req = req;
        presenter.getOtcAdv(1);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
