package com.svv.jys.code.module.myself.myaddress.base;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.mvp.MvpActivity;
import com.svv.jys.code.common.utils.T;
import com.svv.jys.code.module.myself.myaddress.base.model.IMyAddressModel;
import com.svv.jys.code.module.myself.myaddress.base.presenter.MyAddressPresenter;
import com.svv.jys.code.module.myself.myaddress.base.view.IMyAddressView;

/**
 * Created by Administrator on 2018/5/2 0002.
 */

public class MyAddressAct extends MvpActivity<IMyAddressView, IMyAddressModel, MyAddressPresenter> implements IMyAddressView {
    private TextView saveCommit;
    private EditText address_et, tag_et, remark_et;
    private String coin;
    private View tag_ll;

    @Override
    public Context getMContext() {
        return this;
    }

    @Override
    public MyAddressPresenter initPresenter() {
        return new MyAddressPresenter();
    }

    @Override
    public void baseInitialization() {
        coin = getIntent().getStringExtra("coin");
    }

    @Override
    public int setR_Layout() {
        return R.layout.act_myaddress;
    }

    @Override
    public void viewInitialization() {
        setBackPress();
        setTitleTx(String.format(getString(R.string.myaddressact_title), coin.toUpperCase()));
        address_et = findViewById(R.id.address_et);
        tag_et = findViewById(R.id.tag_et);
        remark_et = findViewById(R.id.remark_et);
        saveCommit = (TextView) $(R.id.saveCommit);
        tag_ll = $(R.id.tag_ll);
        switch (coin) {
            case "eos":
            case "xrp":
                tag_ll.setVisibility(View.VISIBLE);
                break;
        }
        saveCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check();
            }
        });
    }

    private void check() {
        String address = address_et.getText().toString();
        if (TextUtils.isEmpty(address)) {
            T.showShort(getMContext(), getString(R.string.address_tv1));
            return;
        }
        if (tag_ll.getVisibility() == View.VISIBLE) {
            String tag = tag_et.getText().toString();
            if (TextUtils.isEmpty(tag)) {
                T.showShort(getMContext(), getString(R.string.address_et_biaoqian));
                return;
            }
            address = address + "_" + tag;
        }

        String remark = remark_et.getText().toString();
        if (TextUtils.isEmpty(remark)) {
            T.showShort(getMContext(), getString(R.string.address_tv3));
            return;
        }

        presenter.add_address(coin,remark,address);


    }

    @Override
    public void doBusiness() {

    }


    @Override
    public void successAddAddress() {
        T.showShort(this, getString(R.string.address3));
        finish();
    }
}
