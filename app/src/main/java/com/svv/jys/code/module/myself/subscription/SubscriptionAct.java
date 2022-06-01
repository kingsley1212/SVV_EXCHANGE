package com.svv.jys.code.module.myself.subscription;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.svv.jys.R;
import com.svv.jys.code.common.base.mvp.MvpActivity;
import com.svv.jys.code.common.entity.SubEntity;
import com.svv.jys.code.common.entity.SubListEntity;
import com.svv.jys.code.common.entity.SubscriptionEntity;
import com.svv.jys.code.common.utils.T;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.subscription.adapter.SubAdapter;
import com.svv.jys.code.module.myself.subscription.model.SubscriptionModel;
import com.svv.jys.code.module.myself.subscription.presenter.SubscriptionPresenter;
import com.svv.jys.code.module.myself.subscription.view.SubscriptionView;

import java.util.ArrayList;
import java.util.List;

public class SubscriptionAct extends MvpActivity<SubscriptionView, SubscriptionModel, SubscriptionPresenter> implements SubscriptionView {
    private String type;
    private TextView coin_tv, tv_type, price_tv, buy_price_tv, buy_num_tv, tv_zhehe,zhehe_tv, buy_btn,tv_memo,tv_memo_unit;
    private EditText buy_num_et, safe_et;
    private String coin;
    private List<String> coins = new ArrayList<>();
    private XRecyclerView transfer_rv;
    private View no_data;
    private SubscriptionEntity.CoinListBean.ExtDataBean extDataBean;
    private NestedScrollView c2c_scroll;
    private boolean loadMoreAble = true;
    private SubAdapter adapter;
    private SubEntity subEntity;
    @Override
    public SubscriptionPresenter initPresenter() {
        return new SubscriptionPresenter();
    }

    @Override
    public void baseInitialization() {
        coin = getIntent().getStringExtra("coin");
        type=getIntent().getStringExtra("type");
    }

    @Override
    public int setR_Layout() {
        return R.layout.act_subscription;
    }

    @Override
    public void viewInitialization() {
        setBackPress();
        tv_memo=findViewById(R.id.tv_memo);
        tv_memo_unit=findViewById(R.id.tv_memo_unit);
        adapter = new SubAdapter(this);
        coin_tv = findViewById(R.id.coin_tv);
        tv_type = findViewById(R.id.tv_type);
        c2c_scroll = (NestedScrollView) $(R.id.c2c_scroll);
        price_tv = findViewById(R.id.price_tv);
        buy_price_tv = findViewById(R.id.buy_price_tv);
        buy_num_tv = findViewById(R.id.buy_num_tv);
        tv_zhehe = findViewById(R.id.tv_zhehe);
        buy_btn = findViewById(R.id.buy_btn);
        zhehe_tv = findViewById(R.id.zhehe_tv);
        buy_num_et = findViewById(R.id.buy_num_et);
        safe_et = findViewById(R.id.safe_et);
        transfer_rv = findViewById(R.id.transfer_rv);
        no_data = findViewById(R.id.no_data);
        transfer_rv.setLayoutManager(new LinearLayoutManager(this));
        transfer_rv.setNestedScrollingEnabled(false);
        transfer_rv.setAdapter(adapter);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            c2c_scroll.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                    if (i1 == (c2c_scroll.getChildAt(0).getMeasuredHeight() - view.getMeasuredHeight())) {
                        if (loadMoreAble)
                            //滑动到底部
                            presenter.setListData(true);
                    }
                }
            });
        }
        if (type.equals("1")){
            setTitleTx(getString(R.string.subscription));
            buy_price_tv.setText(getResources().getString(R.string.subscription_price));
            buy_num_tv.setText(getResources().getString(R.string.subscription_num));
        }else {
            setTitleTx(getString(R.string.placement));
            buy_price_tv.setText(getResources().getString(R.string.placement_price));
            buy_num_tv.setText(getResources().getString(R.string.placement_num));
        }
        tv_memo_unit.setText(coin.toUpperCase());
        coin_tv.setText(coin.toUpperCase());
    }


    public void loading(List<SubListEntity.RowsBean> rows) {
        transfer_rv.loadMoreComplete();
        //加载下一页
        if (rows != null && rows.size() != 0) {
            adapter.addList(rows);
            adapter.notifyDataSetChanged();
        } else {
            transfer_rv.setNoMore(true);
            loadMoreAble = false;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    transfer_rv.setNoMore(false);//已经全部加载完成
                }
            }, 1000);
        }
    }

    public void refresh(List<SubListEntity.RowsBean> rows) {
        //刷新
        if (rows != null && rows.size() != 0) {
            no_data.setVisibility(View.GONE);
            transfer_rv.setVisibility(View.VISIBLE);
            adapter.setList(rows);
            adapter.notifyDataSetChanged();
            transfer_rv.refreshComplete();//关闭刷新
        } else {
            transfer_rv.setVisibility(View.GONE);
            no_data.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void succeseSub() {
//        if (type.equals("1")) {
//            buy_num_et.setHint(getResources().getString(R.string.BuyOrSellAct_xiane)+subEntity.getSub_min_limit()+"-"+subEntity.getSub_max_limit());
//        }else {
//            buy_num_et.setHint(getResources().getString(R.string.BuyOrSellAct_xiane)+subEntity.getPla_min_limit()+"-"+subEntity.getPla_max_limit());
//        }
        buy_num_et.setText("");
        safe_et.setText("");
    }

    @Override
    public void subInfo(final SubEntity entity) {
        subEntity=entity;
        if (type.equals("1")){
            tv_memo.setText(getResources().getString(R.string.usdt_cash,entity.getConvert_coin())+entity.getConvert_price().toUpperCase());
            buy_num_et.setHint(getResources().getString(R.string.BuyOrSellAct_xiane)+entity.getConvert_min_limit()+"-"+entity.getConvert_max_limit());
            price_tv.setText(entity.getConvert_rate()+entity.getConvert_coin().toUpperCase());
            zhehe_tv.setText(getResources().getString(R.string.zhehe_usdt,entity.getConvert_coin().toUpperCase()));
        }else {
//            tv_memo.setText(getResources().getString(R.string.usdt_cash)+entity.getPla_price());
//            buy_num_et.setHint(getResources().getString(R.string.BuyOrSellAct_xiane)+entity.getPla_min_limit()+"-"+entity.getPla_max_limit());
//            price_tv.setText(entity.getPla_rate()+"USDT");
        }
        buy_num_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s.toString())) {
                    if (type.equals("1")) {
                        tv_zhehe.setText(ToolUtils.division(s.toString(), entity.getConvert_price(), 8));
                    } else {
//                        tv_zhehe.setText(ToolUtils.division(s.toString(), entity.getPla_price(), 8));
                    }
                } else {
                    tv_zhehe.setText("");
                }
            }
        });
        buy_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ToolUtils.isFastClick(v.getId())){
                    String num = buy_num_et.getText().toString().trim();
                    if (TextUtils.isEmpty(num)) {
                        if (type.equals("1")) {
                            T.showShort(getMContext(), getResources().getString(R.string.subscription_et));
                        } else {
                            T.showShort(getMContext(), getResources().getString(R.string.placement_et));
                        }
                        return;
                    }
                    String psw = safe_et.getText().toString().trim();
                    if (TextUtils.isEmpty(psw)) {
                        T.showShort(getMContext(), getResources().getString(R.string.popupBusiness_pwnull));
                        return;
                    }
                    presenter.subscription(coin, type, num, psw);
                }

            }
        });
    }

    @Override
    public void doBusiness() {
        presenter.getcoindata(coin);
//        presenter.getSubInfo();
        presenter.setListData(false);
    }

    @Override
    public Context getMContext() {
        return this;
    }

    @Override
    public void setSubscriptionInfo(final SubscriptionEntity entity) {
        coin = entity.getCoin_list().get(0).getName();
        tv_memo_unit.setText(coin.toUpperCase());
        extDataBean = entity.getCoin_list().get(0).getExt_data();
        if (!TextUtils.isEmpty(entity.getCoin_list().get(0).getExt_data().getIs_sub())) {
            tv_type.setText(getResources().getString(R.string.subscription));
            type = "1";
            tv_memo.setText(getResources().getString(R.string.usdt_cash)+entity.getCoin_list().get(0).getExt_data().getSub_price());
            buy_num_et.setHint(getResources().getString(R.string.BuyOrSellAct_xiane)+extDataBean.getSub_min_limit()+"-"+extDataBean.getSub_max_limit());
        } else {
            tv_type.setText(getResources().getString(R.string.placement));
            type = "2";
            tv_memo.setText(getResources().getString(R.string.usdt_cash)+entity.getCoin_list().get(0).getExt_data().getPla_price());
            buy_num_et.setHint(getResources().getString(R.string.BuyOrSellAct_xiane)+extDataBean.getPla_min_limit()+"-"+extDataBean.getPla_max_limit());
        }

        for (int i = 0; i < entity.getCoin_list().size(); i++) {
            coins.add(entity.getCoin_list().get(i).getName());
        }
        price_tv.setText(entity.getCoin_list().get(0).getExt_data().getSub_price());
        coin_tv.setText(entity.getCoin_list().get(0).getName().toUpperCase());
//        coin_ll.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (popup == null) {
//                    popup = new PopupNewSelectorView(getMContext(), coins);
//                    popup.setValue(coin);
//                    popup.setOnClickItem(new PopupNewSelectorView.OnClickItem() {
//                        @Override
//                        public void OnSelect(int position, String value) {
//                            coin = coins.get(position);
//                            coin_tv.setText(coin.toUpperCase());
//                            extDataBean = entity.getCoin_list().get(position).getExt_data();
//                            tv_memo_unit.setText(coin.toUpperCase());
//                            if (type.equals("1")) {
//                                if (TextUtils.isEmpty(extDataBean.getIs_sub())){
//                                    type="2";
//                                    tv_type.setText(getResources().getString(R.string.placement));
//                                    buy_price_tv.setText(getResources().getString(R.string.placement_price));
//                                    buy_num_tv.setText(getResources().getString(R.string.placement_num));
//                                    buy_num_et.setHint(getResources().getString(R.string.BuyOrSellAct_xiane)+extDataBean.getPla_min_limit()+"-"+extDataBean.getPla_max_limit());
////                                    buy_num_et.setHint(getResources().getString(R.string.placement_et));
//                                    price_tv.setText(entity.getCoin_list().get(position).getExt_data().getPla_price());
//                                    tv_memo.setText(getResources().getString(R.string.usdt_cash)+entity.getCoin_list().get(0).getExt_data().getPla_price());
//                                    if (!TextUtils.isEmpty(buy_num_et.getText().toString())) {
//                                        tv_zhehe.setText(ToolUtils.division(buy_num_et.getText().toString(), extDataBean.getPla_price(), 8));
//                                    }
//                                }else {
//                                    price_tv.setText(entity.getCoin_list().get(position).getExt_data().getSub_price());
//                                    if (!TextUtils.isEmpty(buy_num_et.getText().toString())) {
//                                        tv_zhehe.setText(ToolUtils.division(buy_num_et.getText().toString(), extDataBean.getSub_price(), 8));
//                                    }
//                                }
//
//                            } else {
//                                if (TextUtils.isEmpty(extDataBean.getIs_pla())){
//                                    type="1";
//                                    tv_type.setText(getResources().getString(R.string.subscription));
//                                    buy_price_tv.setText(getResources().getString(R.string.subscription_price));
//                                    buy_num_tv.setText(getResources().getString(R.string.subscription_num));
//                                    buy_num_et.setHint(getResources().getString(R.string.BuyOrSellAct_xiane)+extDataBean.getSub_min_limit()+"-"+extDataBean.getSub_max_limit());
////                                    buy_num_et.setHint(getResources().getString(R.string.subscription_et));
//                                    price_tv.setText(entity.getCoin_list().get(position).getExt_data().getSub_price());
//                                    tv_memo.setText(getResources().getString(R.string.usdt_cash)+entity.getCoin_list().get(0).getExt_data().getSub_price());
//                                    if (!TextUtils.isEmpty(buy_num_et.getText().toString())) {
//
//                                        tv_zhehe.setText(ToolUtils.division(buy_num_et.getText().toString(), extDataBean.getSub_price(), 8));
//                                    }
//                                }else {
//                                    price_tv.setText(entity.getCoin_list().get(position).getExt_data().getPla_price());
//                                    if (!TextUtils.isEmpty(buy_num_et.getText().toString())) {
//
//                                        tv_zhehe.setText(ToolUtils.division(buy_num_et.getText().toString(), extDataBean.getPla_price(), 8));
//                                    }
//                                }
//
//                            }
////                            if (!coin_tv.getText().toString().toUpperCase().equals(coins.get(position).toUpperCase())) {
////
////                            }
//                        }
//                    });
//                }
//                popup.showPop(coin_ll);
//            }
//        });
//        ll_type.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                List<String> types = new ArrayList<>();
//                if (!TextUtils.isEmpty(extDataBean.getIs_sub())) {
//                    types.add(getResources().getString(R.string.subscription));
//                }
//                if (!TextUtils.isEmpty(extDataBean.getIs_pla())) {
//                    types.add(getResources().getString(R.string.placement));
//                }
//
//                typePopup = new PopupNewSelectorView(getMContext(), types);
//                typePopup.setDa(true);
//                typePopup.setOnClickItem(new PopupNewSelectorView.OnClickItem() {
//                    @Override
//                    public void OnSelect(int position, String value) {
//                        type = (position + 1) + "";
//                        tv_type.setText(value);
//                        if (position == 0) {
//                            price_tv.setText(extDataBean.getSub_price());
//                            buy_price_tv.setText(getResources().getString(R.string.subscription_price));
//                            buy_num_tv.setText(getResources().getString(R.string.subscription_num));
//                            buy_num_et.setHint(getResources().getString(R.string.BuyOrSellAct_xiane)+extDataBean.getSub_min_limit()+"-"+extDataBean.getSub_max_limit());
////                            buy_num_et.setHint(getResources().getString(R.string.subscription_et));
//                            tv_memo.setText(getResources().getString(R.string.usdt_cash)+entity.getCoin_list().get(0).getExt_data().getSub_price());
//                            if (!TextUtils.isEmpty(buy_num_et.getText().toString().trim())) {
//                                tv_zhehe.setText(ToolUtils.division(buy_num_et.getText().toString(), extDataBean.getSub_price(), 8));
//                            }
//                        } else {
//                            price_tv.setText(extDataBean.getPla_price());
//                            buy_price_tv.setText(getResources().getString(R.string.placement_price));
//                            buy_num_tv.setText(getResources().getString(R.string.placement_num));
////                            buy_num_et.setHint(getResources().getString(R.string.placement_et));
//                            buy_num_et.setHint(getResources().getString(R.string.BuyOrSellAct_xiane)+extDataBean.getPla_min_limit()+"-"+extDataBean.getPla_max_limit());
//                            tv_memo.setText(getResources().getString(R.string.usdt_cash)+entity.getCoin_list().get(0).getExt_data().getPla_price());
//                            if (!TextUtils.isEmpty(buy_num_et.getText().toString().trim())) {
//                                tv_zhehe.setText(ToolUtils.division(buy_num_et.getText().toString(), extDataBean.getPla_price(), 8));
//                            }
//                        }
//                    }
//                });
//                typePopup.showPop(ll_type);
//            }
//        });
        buy_num_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s.toString())) {
                    if (type.equals("1")) {
                        tv_zhehe.setText(ToolUtils.division(s.toString(), extDataBean.getSub_price(), 8));
                    } else {
                        tv_zhehe.setText(ToolUtils.division(s.toString(), extDataBean.getPla_price(), 8));
                    }
                } else {
                    tv_zhehe.setText("");
                }
            }
        });
        buy_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ToolUtils.isFastClick(v.getId())){
                    String num = buy_num_et.getText().toString().trim();
                    if (TextUtils.isEmpty(num)) {
                        if (type.equals("1")) {
                            T.showShort(getMContext(), getResources().getString(R.string.subscription_et));
                        } else {
                            T.showShort(getMContext(), getResources().getString(R.string.placement_et));
                        }
                        return;
                    }
                    String psw = safe_et.getText().toString().trim();
                    if (TextUtils.isEmpty(psw)) {
                        T.showShort(getMContext(), getResources().getString(R.string.popupBusiness_pwnull));
                        return;
                    }
                    presenter.subscription(coin, type, num, psw);
                }

            }
        });
    }

    @Override
    public String getRvSize() {
        return String.valueOf(adapter.getItemCount());
    }
}
