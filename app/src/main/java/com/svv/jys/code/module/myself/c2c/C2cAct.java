package com.svv.jys.code.module.myself.c2c;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
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
import com.svv.jys.code.common.entity.BankInfoEntity;
import com.svv.jys.code.common.entity.C2CIndexEntity;
import com.svv.jys.code.common.entity.C2CListEntity;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.common.view.popup.PopupNewSelectorView;
import com.svv.jys.code.module.home.article.ArticleAct;
import com.svv.jys.code.module.myself.bank.base.BankManagerAct;
import com.svv.jys.code.module.myself.c2c.adapter.C2cAdapter;
import com.svv.jys.code.module.myself.c2c.model.C2cModel;
import com.svv.jys.code.module.myself.c2c.presenter.C2cPresenter;
import com.svv.jys.code.module.myself.c2c.view.C2cView;

import java.util.List;

/**
 * Created by js on 2018/5/26.
 */

public class C2cAct extends MvpActivity<C2cView, C2cModel, C2cPresenter>
        implements C2cView {
    private C2cAdapter adapter;
    private XRecyclerView transfer_rv;
    private View no_data, bank_ll, tab_view_1, tab_view_2, coin_ll, sell_in_rl, buy_out_rl;
    private String type = "1", sell_price, buy_price,bank_id,coin;
    private TextView tab1_tv, tab2_tv, buy_btn, coin_tv, buy_num_tv, price_tv,bank_tv,buy_price_tv;
    private EditText buy_num_et,safe_et;
    private NestedScrollView c2c_scroll;
    private boolean loadMoreAble = true;
    private PopupNewSelectorView popup;


    @Override
    public Context getMContext() {
        return this;
    }

    @Override
    public C2cPresenter initPresenter() {
        return new C2cPresenter();
    }

    @Override
    public void baseInitialization() {

    }

    @Override
    public int setR_Layout() {
        return R.layout.act_c2c;
    }

    @Override
    public void viewInitialization() {
        setBackPress();
        setTitleTx(getString(R.string.MyselfFragment_ctowc));
        adapter = new C2cAdapter(getMContext());
        transfer_rv = (XRecyclerView) $(R.id.transfer_rv);
        transfer_rv.setLayoutManager(new LinearLayoutManager(getMContext()));
        transfer_rv.setAdapter(adapter);
        no_data = $(R.id.no_data);
        tab1_tv = (TextView) $(R.id.tab1_tv);
        tab2_tv = (TextView) $(R.id.tab2_tv);
        tab_view_1 = $(R.id.tab_view_1);
        tab_view_2 = $(R.id.tab_view_2);
        buy_btn = (TextView) $(R.id.buy_btn);
        price_tv = (TextView) $(R.id.price_tv);
        coin_ll = $(R.id.coin_ll);
        coin_tv = (TextView) $(R.id.coin_tv);
        buy_num_tv = (TextView) $(R.id.buy_num_tv);
        sell_in_rl = $(R.id.sell_in_rl);
        buy_out_rl = $(R.id.buy_out_rl);
        bank_ll = $(R.id.bank_ll);
        c2c_scroll = (NestedScrollView) $(R.id.c2c_scroll);
        bank_tv = (TextView) $(R.id.bank_tv);
        buy_num_et = (EditText) $(R.id.buy_num_et);
        safe_et  = (EditText) $(R.id.safe_et);
        buy_price_tv = (TextView) $(R.id.buy_price_tv);
        buy_btn.setEnabled(false);
        $(R.id.right_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ToolUtils.isFastClick(view.getId())){
                    Intent intent = new Intent(getMContext(), BankManagerAct.class);
                    intent.putExtra("isselectBank", false);
                    startActivity(intent);
                }

            }
        });
        transfer_rv.setNestedScrollingEnabled(false);
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
        bank_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ToolUtils.isFastClick(view.getId())){
                    Intent intent = new Intent(getMContext(), BankManagerAct.class);
                    intent.putExtra("isselectBank", true);
                    startActivityForResult(intent, 101);
                }

            }
        });
        buy_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.c2cUp(coin,type,buy_num_et.getText().toString(),bank_id,safe_et.getText().toString());
            }
        });
        $(R.id.sm_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ToolUtils.isFastClick(view.getId())){
                    Bundle bundle=new Bundle();
                    bundle.putString("id","47");
                    gotoActivity(ArticleAct.class,false,bundle);
                }

            }
        });
        safe_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                    if (TextUtils.isEmpty(buy_num_et.getText().toString())||TextUtils.isEmpty(safe_et.getText().toString())){
                        buy_btn.setBackgroundResource(R.drawable.shape_c2c_no_bg);
                        buy_btn.setEnabled(false);
                    }else {
                        if (type.equals("1")){
                            buy_btn.setBackgroundResource(R.drawable.shape_fabi_buy_sell_bg);
                        }else {
                            buy_btn.setBackgroundResource(R.drawable.shape_fabi_buy_bg);
                        }
                        buy_btn.setEnabled(true);

                    }

            }
        });
        buy_num_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String temp = editable.toString();
                int posDot = temp.indexOf(".");
                if (posDot > 0) {
                    if (temp.length() - posDot - 1 > 2) {
                        editable.delete(posDot + 3, editable.length());
                    }
                }
                if (TextUtils.isEmpty(buy_num_et.getText().toString())||TextUtils.isEmpty(safe_et.getText().toString())){
                    buy_btn.setBackgroundResource(R.drawable.shape_c2c_no_bg);
                    buy_btn.setEnabled(false);
                }else {
                    if (type.equals("1")){
                        buy_btn.setBackgroundResource(R.drawable.shape_fabi_buy_sell_bg);
                    }else {
                        buy_btn.setBackgroundResource(R.drawable.shape_fabi_buy_bg);
                    }
                    buy_btn.setEnabled(true);
                }
            }
        });
    }

    @Override
    public void doBusiness() {
        presenter.getIndexData("");
        presenter.setListData(false);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    public void chageTab() {
        switch (type) {
            case "1":
                bank_ll.setVisibility(View.GONE);
                tab1_tv.setTextColor(getResources().getColor(R.color.c_1ede89));
                tab2_tv.setTextColor(getResources().getColor(R.color.c_9fb8cf));
                tab_view_2.setVisibility(View.GONE);
                tab_view_1.setVisibility(View.VISIBLE);
                buy_btn.setText(getString(R.string.tradechat_buy));
                if (TextUtils.isEmpty(buy_num_et.getText().toString())||TextUtils.isEmpty(safe_et.getText().toString())){
                    buy_btn.setBackgroundResource(R.drawable.shape_c2c_no_bg);

                }else {
                    buy_btn.setBackgroundResource(R.drawable.shape_fabi_buy_sell_bg);
                }

//                buy_btn.setSelected(false);
                price_tv.setText(buy_price);
                buy_num_tv.setText(String.format(getString(R.string.c2c_tv2), coin_tv.getText().toString()));
                buy_price_tv.setText(getString(R.string.c2c_tv1));
                break;
            case "2":
                bank_ll.setVisibility(View.VISIBLE);
                tab1_tv.setTextColor(getResources().getColor(R.color.c_9fb8cf));
                tab2_tv.setTextColor(getResources().getColor(R.color.c_ff5755));
                tab_view_1.setVisibility(View.GONE);
                tab_view_2.setVisibility(View.VISIBLE);
                buy_btn.setText(getString(R.string.tradechat_sell));
                if (TextUtils.isEmpty(buy_num_et.getText().toString())||TextUtils.isEmpty(safe_et.getText().toString())){
                    buy_btn.setBackgroundResource(R.drawable.shape_c2c_no_bg);
                }else {
                    buy_btn.setBackgroundResource(R.drawable.shape_fabi_buy_bg);

                }
//                buy_btn.setSelected(true);
                buy_num_tv.setText(String.format(getString(R.string.c2c_tv6), coin_tv.getText().toString()));
                buy_price_tv.setText(getString(R.string.c2c_tv7));
                price_tv.setText(sell_price);
                break;
        }
    }


    public void loading(List<C2CListEntity.RowsBean> rows) {
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

    public void refresh(List<C2CListEntity.RowsBean> rows) {
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
    public void buyorSellSuccese() {
        buy_num_et.setText("");
        safe_et.setText("");
    }

    @Override
    public void setIndexData(C2CIndexEntity entity) {
        coin_tv.setText(entity.getCoin().getName().toUpperCase());
        coin = entity.getCoin().getName();
        coin_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (popup == null) {
                    popup = new PopupNewSelectorView(getMContext(), presenter.coins);
                    popup.setValue(coin);
                    popup.setOnClickItem(new PopupNewSelectorView.OnClickItem() {
                        @Override
                        public void OnSelect(int position, String value) {
                            if (!coin_tv.getText().toString().toUpperCase().equals(presenter.coins.get(position).toUpperCase())) {
                                presenter.getIndexData(presenter.coins.get(position));
                            }
                        }
                    });
                }
                popup.showPop(coin_ll);
            }
        });

        sell_price = entity.getCoin().getSell_price();
        buy_price = entity.getCoin().getBuy_price();
        chageTab();
        buy_num_tv.setText(String.format(getString(R.string.c2c_tv2), entity.getCoin().getName().toUpperCase()));
        sell_in_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!type.equals("1")) {
                    type = "1";
                    chageTab();
                }
            }
        });
        buy_out_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!type.equals("2")) {
                    type = "2";
                    chageTab();
                }
            }
        });
    }

    @Override
    public String getRvSize() {
        return String.valueOf(adapter.getItemCount());
    }

    public void doCancel(String id) {
        presenter.doCx(id);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101 && resultCode == 2) {
            BankInfoEntity entity = (BankInfoEntity) data.getSerializableExtra("bank");
            bank_tv.setText(entity.getBank_name());
            bank_id = entity.getId();
        }
    }
}
