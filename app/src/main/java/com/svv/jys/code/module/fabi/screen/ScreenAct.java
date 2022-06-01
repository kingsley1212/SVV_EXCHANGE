package com.svv.jys.code.module.fabi.screen;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.mvp.MvpActivity;
import com.svv.jys.code.common.entity.AdvSettingEntity;
import com.svv.jys.code.common.view.selfview.MyGridView;
import com.svv.jys.code.module.fabi.screen.adapter.CountryAdapter;
import com.svv.jys.code.module.fabi.screen.adapter.PayAdapter;
import com.svv.jys.code.module.fabi.screen.model.ScreenModel;
import com.svv.jys.code.module.fabi.screen.presenter.ScreenPresenter;
import com.svv.jys.code.module.fabi.screen.view.ScreenView;
import com.svv.jys.code.module.net.req.GET_OTC_ADV_REQ;

/**
 * Created by js on 2018/5/17.
 */

public class ScreenAct extends MvpActivity<ScreenView, ScreenModel, ScreenPresenter> implements
        ScreenView {
    private MyGridView country_gridview, pay_gridview;
    private CountryAdapter countryAdapter;
    private PayAdapter payAdapter;
    private View ll_close;
    private TextView pay_all_tv,country_all_tv;
    public static int REQUEST_SCREEN = 101;
    public static int SUCCESS_RESULT = 1;
    public static int FAIL_RESULT = 2;
    private EditText jine_et;

    public static void startByReqAndData(Context context, GET_OTC_ADV_REQ req, AdvSettingEntity entity) {
        Intent intent = new Intent(context, ScreenAct.class);
        intent.putExtra("GET_OTC_ADV_REQ", req);
        intent.putExtra("AdvSettingEntity", entity);
        ((Activity) context).startActivityForResult(intent,REQUEST_SCREEN);
        ((Activity) context).overridePendingTransition(R.anim.anim_right_in, R.anim.anim_no);
    }


    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.anim_no, R.anim.anim_right_out);
    }

    @Override
    public Context getMContext() {
        return this;
    }

    @Override
    public ScreenPresenter initPresenter() {
        return new ScreenPresenter();
    }

    @Override
    public void baseInitialization() {
        styleControl = false;
    }


    @Override
    public int setR_Layout() {
        return R.layout.act_screen;
    }

    @Override
    public void viewInitialization() {
        pay_gridview = (MyGridView) $(R.id.pay_gridview);
        country_gridview = (MyGridView) $(R.id.country_gridview);
        ll_close = $(R.id.ll_close);
        ll_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        jine_et = (EditText) $(R.id.jine_et);
        pay_all_tv = (TextView) $(R.id.pay_all_tv);
        country_all_tv = (TextView) $(R.id.country_all_tv);
        $(R.id.reset_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pay_all_tv.performClick();
                country_all_tv.performClick();
                jine_et.setText("");
            }
        });
        $(R.id.screen_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.req.money = jine_et.getText().toString();
                Intent intent = new Intent();
                intent.putExtra("GET_OTC_ADV_REQ",presenter.req);
                setResult(SUCCESS_RESULT,intent);
                finish();
            }
        });
    }

    @Override
    public void doBusiness() {
        presenter.req = (GET_OTC_ADV_REQ) getIntent().getSerializableExtra("GET_OTC_ADV_REQ");
        presenter.entity = (AdvSettingEntity) getIntent().getSerializableExtra("AdvSettingEntity");
        countryAdapter = new CountryAdapter(getMContext(), presenter.entity.getCountry());
        country_gridview.setAdapter(countryAdapter);
        payAdapter = new PayAdapter(getMContext(), presenter.entity.getIncome().getRows());
        pay_gridview.setAdapter(payAdapter);
        countryAdapter.setOnItemClick(new CountryAdapter.MOnItemClickListener() {
            @Override
            public void onItemClick(int position, Object o) {
                presenter.req.country = (String) o;
                countryAdapter.setValue((String) o);
                country_all_tv.setSelected(false);
            }
        });
        payAdapter.setOnItemClick(new PayAdapter.MOnItemClickListener() {
            @Override
            public void onItemClick(int position, Object o) {
                presenter.req.pay = (String) o;
                payAdapter.setValue((String) o);
                pay_all_tv.setSelected(false);
            }
        });

        pay_all_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pay_all_tv.setSelected(true);
                presenter.req.pay = null;
                payAdapter.setValue("-1");
            }
        });

        country_all_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                country_all_tv.setSelected(true);
                presenter.req.country = null;
                countryAdapter.setValue("-1");
            }
        });

        if(TextUtils.isEmpty(presenter.req.country)){
            country_all_tv.setSelected(true);
        }else {
            countryAdapter.setValue(presenter.req.country);
        }

        if(TextUtils.isEmpty(presenter.req.pay)){
            pay_all_tv.setSelected(true);
        }else {
            payAdapter.setValue(presenter.req.pay);
        }

    }


}
