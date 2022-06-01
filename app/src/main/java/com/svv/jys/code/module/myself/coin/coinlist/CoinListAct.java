package com.svv.jys.code.module.myself.coin.coinlist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.mvp.MvpActivity;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.common.view.thirdview.sortlistview.CoinsComparator;
import com.svv.jys.code.module.myself.coin.coinlist.adapter.CoinListAdapter;
import com.svv.jys.code.module.myself.coin.coinlist.model.CoinListModel;
import com.svv.jys.code.module.myself.coin.coinlist.presenter.CoinListPresenter;
import com.svv.jys.code.module.myself.coin.coinlist.view.CoinListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by LB on 2018/3/2.
 */

public class CoinListAct extends MvpActivity<CoinListView, CoinListModel, CoinListPresenter> implements CoinListView {
    private ListView sortListView;
    private CoinListAdapter adapter;
    private EditText mClearEditText;
    private String type = "0";

    /**
     * 根据拼音来排列ListView里面的数据类
     */
    private CoinsComparator countriesComparator;

    public static void startByCharge(Context context){
        Intent intent = new Intent(context,CoinListAct.class);
        intent.putExtra("TYPE","0");
        ((Activity)context).startActivityForResult(intent,401);
    }

    public static void startByExtract(Context context){
        Intent intent = new Intent(context,CoinListAct.class);
        intent.putExtra("TYPE","1");
        ((Activity)context).startActivityForResult(intent,402);
    }



    @Override
    public Context getMContext() {
        return this;
    }

    @Override
    public CoinListPresenter initPresenter() {
        return new CoinListPresenter();
    }

    @Override
    public void baseInitialization() {
        type = getIntent().getStringExtra("TYPE");
    }

    @Override
    public int setR_Layout() {
        return R.layout.act_coinlist;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void viewInitialization() {
        setBackPress();
        countriesComparator = new CoinsComparator();
        sortListView = (ListView) $(R.id.country_lvcountry);

        mClearEditText = (EditText) findViewById(R.id.filter_edit);

        //根据输入框输入值的改变来过滤搜索
        mClearEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                List<String> filter = filter(s.toString());
                if(ToolUtils.isListNull(filter) && TextUtils.isEmpty( s.toString())){
                    setData(presenter.coins);
                }else {
                    setData(filter);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        $(R.id.cancel_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(0);
                finish();
            }
        });
    }

    @Override
    public void doBusiness() {
        presenter.getAreaCode();
    }

    public List<String> filter(String text){
        List<String> strings = new ArrayList<>();
        for (String s : presenter.coins){
            if(s.toUpperCase().contains(text.toUpperCase()))
            strings.add(s);
        }
        return strings;
    }

    @Override
    public void setData(List<String> list) {
        if (adapter == null) {
            // 根据a-z进行排序源数据
            Collections.sort(list, countriesComparator);
            adapter = new CoinListAdapter(this, list);
            sortListView.setAdapter(adapter);
        } else {
            adapter.updateListView(list);
        }
    }

    @Override
    public void getAddressError() {
        setResult(0);
        finish();
    }

    public void getAddressSuccess(String coin) {
        Intent intent = new Intent();
        intent.putExtra("coin",coin);
        setResult(501,intent);
        finish();
    }

    @Override
    public void getCoinFalse() {
        setResult(0);
        finish();
    }

    public void getCoinSuccess(String coin) {
        Intent intent = new Intent();
        intent.putExtra("coin",coin);
        setResult(502,intent);
        finish();
    }

    public void OnItemClick(int position) {
        switch (type){
            case "0":
                getAddressSuccess(presenter.coins.get(position));
                break;
            case "1":
                getCoinSuccess(presenter.coins.get(position));
        }
    }
}
