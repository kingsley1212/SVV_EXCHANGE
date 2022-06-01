package com.svv.jys.code.module.myself.myasset.otc.otctrading.fabu.fabufrag;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.mvp.MvpFragment;
import com.svv.jys.code.common.constant.ACEConstant;
import com.svv.jys.code.common.entity.AdvInfoEntity;
import com.svv.jys.code.common.entity.AdvSettingEntity;
import com.svv.jys.code.common.entity.CountryEntity;
import com.svv.jys.code.common.utils.T;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.common.view.popup.PopupNewSelectorView;
import com.svv.jys.code.common.view.popup.PopupPaymentView;
import com.svv.jys.code.module.myself.coinshow.CoinShowAct;
import com.svv.jys.code.module.myself.country.CountryAct;
import com.svv.jys.code.module.myself.myasset.otc.otctrading.fabu.sell.model.IOtcFabuSellModel;
import com.svv.jys.code.module.myself.myasset.otc.otctrading.fabu.sell.presenter.OtcFabuSellPresenter;
import com.svv.jys.code.module.myself.myasset.otc.otctrading.fabu.sell.view.IOtcFabuSellView;
import com.pedaily.yc.ycdialoglib.selectDialog.CustomSelectDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class FabuFrag extends MvpFragment<IOtcFabuSellView, IOtcFabuSellModel, OtcFabuSellPresenter> implements IOtcFabuSellView {

    @BindView(R.id.tv_coin_name)
    TextView tvCoinName;
    @BindView(R.id.rl_select_coin)
    RelativeLayout rlSelectCoin;
    @BindView(R.id.tv_country)
    TextView tvCountry;
    @BindView(R.id.rl_select_country)
    RelativeLayout rlSelectCountry;
    @BindView(R.id.tv_coin_unit)
    TextView tvCoinUnit;
    @BindView(R.id.rl_select_huobi)
    RelativeLayout rlSelectHuobi;
    @BindView(R.id.tv_payment)
    TextView tvPayment;
    @BindView(R.id.tv_payment_name)
    TextView tvPaymentName;
    @BindView(R.id.rl_payment)
    RelativeLayout rlPayment;
    @BindView(R.id.tv_jijia)
    TextView tvJijia;
    @BindView(R.id.tv_jijia_fangshi)
    TextView tvJijiaFangshi;
    @BindView(R.id.rl_select_jijia)
    RelativeLayout rlSelectJijia;
    @BindView(R.id.et_num)
    EditText etNum;
    @BindView(R.id.yijia_et)
    EditText yijiaEt;
    @BindView(R.id.yj_ll)
    LinearLayout yjLl;
    @BindView(R.id.low_price_et)
    EditText lowPriceEt;
    @BindView(R.id.et_total_money)
    EditText etTotalMoney;
    @BindView(R.id.tv_pay_qixian)
    TextView tvPayQixian;
    @BindView(R.id.tv_pay_time)
    TextView tvPayTime;
    @BindView(R.id.rl_select_time)
    RelativeLayout rlSelectTime;
    @BindView(R.id.et_beizhu)
    EditText etBeizhu;
    @BindView(R.id.tv_fabu)
    TextView tvFabu;
    @BindView(R.id.view_line)
    View view_line;
    @BindView(R.id.tv_price)
    TextView tv_price;
    @BindView(R.id.tv_price_unit)
    TextView tv_price_unit;
    @BindView(R.id.tv_total_unit)
    TextView tv_total_unit;
    @BindView(R.id.ll_yijia_price)
    LinearLayout ll_yijia_price;
    @BindView(R.id.ll_gu_price)
    LinearLayout ll_gu_price;
    @BindView(R.id.et_price)
    EditText et_price;
    @BindView(R.id.tv_gu_price_unit)
    TextView tv_gu_price_unit;
    Unbinder unbinder;
    private String type;
    private String time;
    private String coin;
    private String price_type = "0";
    private String country;
    private String code;
    private String id;
    private String payMethod;
    private AdvSettingEntity advSettingEntity;
    private PopupPaymentView paymentView;
    private PopupNewSelectorView popup1;
    private PopupNewSelectorView popup2;
    private PopupNewSelectorView popup3;


    @Override
    public OtcFabuSellPresenter initPresenter() {
        return new OtcFabuSellPresenter();
    }

    public static FabuFrag newInstance(String type, String id) {
        Bundle args = new Bundle();
        args.putString("type", type);
        args.putString("id", id);
        FabuFrag fragment = new FabuFrag();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onWakeBussiness() {

    }

    @Override
    public void baseInitialization() {

    }

    @Override
    public int setR_Layout() {
        return R.layout.frag_fabu;
    }

    @Override
    public void viewInitialization() {
        unbinder = ButterKnife.bind(this, mainView);
        type = getArguments().getString("type");
        id = getArguments().getString("id");
        if (!TextUtils.isEmpty(id)) {
            presenter.getAdvInfo(id);
        }
        if (type.equals("0")) {
            etNum.setHint(getString(R.string.otc_fabu_purchase_quantity));
            tvFabu.setBackgroundResource(R.drawable.btn_adv_buy);
        } else {
            tvFabu.setBackgroundResource(R.drawable.btn_adv_sell);
            etNum.setHint(getString(R.string.otc_fabu_sell_quantity));
        }

    }

    @Override
    public void doBusiness() {
        presenter.getAdvSetting();
    }

    @Override
    public void setAdvSetting(AdvSettingEntity entity) {
        advSettingEntity = entity;
        coin = entity.getCoin();
        tvCoinName.setText(coin.toUpperCase());
        country = entity.getR_country().getId();
        tvCountry.setText(entity.getR_country().getChinese());
        code = entity.getR_currency().getCurrency_code();
        tvCoinUnit.setText(entity.getR_currency().getCurrency_code());
        tv_price.setText(entity.getPrice());
        tv_price_unit.setText(entity.getR_currency().getCurrency_code());
        tv_total_unit.setText(entity.getR_currency().getCurrency_code());
        tv_gu_price_unit.setText(entity.getR_currency().getCurrency_code());
        yijiaEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s.toString())) {
                    presenter.getyijia(coin, s.toString(), code);
                }

            }
        });
    }

    private void showTimeCustomDialog() {
        final List<String> names = new ArrayList<>();
        for (AdvSettingEntity.PayTimesBean bean : advSettingEntity.getPay_times()) {
            names.add(bean.getName());
        }
        if (popup1 == null) {
            popup1 = new PopupNewSelectorView(getMContext(), names);
            popup1.setOnClickItem(new PopupNewSelectorView.OnClickItem() {
                @Override
                public void OnSelect(int position, String value) {
                    time = advSettingEntity.getPay_times().get(position).getValue();
                    tvPayTime.setText(value);
                }
            });
        }
        popup1.showPop(tvPayTime);

    }

    private void showCoinCustomDialog() {
        final List<String> names = new ArrayList<>();
        for (String bean : advSettingEntity.getOtc_coin()) {
            names.add(bean.toUpperCase());
        }
        if (popup2 == null) {
            popup2 = new PopupNewSelectorView(getMContext(), names);
            popup2.setValue(coin.toUpperCase());
            popup2.setOnClickItem(new PopupNewSelectorView.OnClickItem() {
                @Override
                public void OnSelect(int position, String value) {
                    coin = advSettingEntity.getOtc_coin().get(position);
                    tvCoinName.setText(advSettingEntity.getOtc_coin().get(position).toUpperCase());
                    String num = yijiaEt.getText().toString().trim();
                    if (!TextUtils.isEmpty(num)) {
                        presenter.getyijia(coin, num, code);
                    }
                }
            });
        }
        popup2.showPop(tvCoinName);

    }

    private void showjijiaCustomDialog() {
        final List<String> names = new ArrayList<>();
        names.add(getString(R.string.otc_fs_act_tv1));
        names.add(getString(R.string.otc_fs_act_tv2));

        if (popup3 == null) {
            popup3 = new PopupNewSelectorView(getMContext(), names);
            popup3.setValue(tvJijiaFangshi.getText().toString());
            popup3.setOnClickItem(new PopupNewSelectorView.OnClickItem() {
                @Override
                public void OnSelect(int position, String value) {
                    price_type = position + "";
                    guJiaOrYijia(price_type);
                    tvJijiaFangshi.setText(names.get(position));
                }
            });
        }
        popup3.showPop(tvJijiaFangshi);
    }

    public void guJiaOrYijia(String price_type) {
        if (price_type.equals("0")) {
            yjLl.setVisibility(View.VISIBLE);
            view_line.setVisibility(View.VISIBLE);
            ll_yijia_price.setVisibility(View.VISIBLE);
            ll_gu_price.setVisibility(View.GONE);
        } else if (price_type.equals("1")) {
            yjLl.setVisibility(View.GONE);
            view_line.setVisibility(View.GONE);
            ll_yijia_price.setVisibility(View.GONE);
            ll_gu_price.setVisibility(View.VISIBLE);
        }
    }


    private CustomSelectDialog showDialog(CustomSelectDialog.SelectDialogListener listener,
                                          List<String> names) {
        CustomSelectDialog dialog = new CustomSelectDialog(((Activity) getMContext()),
                R.style.transparentFrameWindowStyle, listener, names);
        dialog.setItemColor(R.color.colorAccent, R.color.colorPrimary);
        //判断activity是否finish
        if (!((Activity) getMContext()).isFinishing()) {
            dialog.show();
        }
        return dialog;
    }

    @Override
    public void setYijia(String s) {
        tv_price.setText(s);
    }

    @Override
    public void publishSuccese() {
        getActivity().finish();
    }

    @Override
    public void setAdvInfo(AdvInfoEntity advInfoEntity) {
        tvCountry.setText(advInfoEntity.getR_country().getChinese());
        country = advInfoEntity.getInfo().getCountry();
        payMethod = advInfoEntity.getPayment();
        tvPaymentName.setText(advInfoEntity.getPay_name());
        coin = advInfoEntity.getCoin();
        tvCoinName.setText(coin.toUpperCase());
        if (advInfoEntity.getInfo().getValuetion().equals("0")) {
            tvJijiaFangshi.setText(getString(R.string.otc_fs_act_tv1));
            price_type = "0";
        } else {
            tvJijiaFangshi.setText(getString(R.string.otc_fs_act_tv2));
            price_type = "1";
        }
        time = advInfoEntity.getInfo().getPrompt();
        guJiaOrYijia(price_type);
        etNum.setText(advInfoEntity.getInfo().getNum());
        yijiaEt.setText(advInfoEntity.getInfo().getPremium());
        tv_price.setText(advInfoEntity.getInfo().getPrice());
        lowPriceEt.setText(advInfoEntity.getInfo().getMin_amount());
        etTotalMoney.setText(advInfoEntity.getInfo().getMax_amount());
        tvPayTime.setText(advInfoEntity.getPay_time());
        etBeizhu.setText(advInfoEntity.getInfo().getMessage());
    }

    @Override
    public Context getMContext() {
        return getActivity();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.rl_select_coin, R.id.rl_select_country, R.id.rl_select_huobi, R.id.rl_payment, R.id.rl_select_jijia, R.id.rl_select_time, R.id.tv_fabu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_select_coin:
                if (advSettingEntity != null) {
                    showCoinCustomDialog();
                }
                break;
            case R.id.rl_select_country:
                if (ToolUtils.isFastClick(view.getId())){
                    startActivityForResult(new Intent(getMContext(), CountryAct.class), 200);
                }

                break;
            case R.id.rl_select_huobi:
                if (ToolUtils.isFastClick(view.getId())){
                    Intent intent=new Intent(getActivity(), CoinShowAct.class);
                    intent.putExtra("fubu",true);
                    intent.putExtra("code",code);
                    startActivityForResult(intent, 100);
                }

                break;
            case R.id.rl_payment:
                if (advSettingEntity != null) {
                    if (paymentView == null) {
                        paymentView = new PopupPaymentView(getActivity(), advSettingEntity.getIncome().getRows());
                    }
                    paymentView.setOnClickItem(new PopupPaymentView.OnClickItem() {
                        @Override
                        public void OnSelect(String payname, String payment) {
                            if (TextUtils.isEmpty(payname)) {
                                tvPaymentName.setText(getResources().getString(R.string.otc_please_zf_fangs));
                            } else {
                                tvPaymentName.setText(payname);
                            }
                            payMethod = payment;
                        }
                    });
                    paymentView.showPop(view);
                }

                break;
            case R.id.rl_select_jijia:
                showjijiaCustomDialog();
                break;
            case R.id.rl_select_time:
                if (advSettingEntity != null) {
                    showTimeCustomDialog();
                }
                break;
            case R.id.tv_fabu:
                String yijia = yijiaEt.getText().toString().trim();
                String low = lowPriceEt.getText().toString().trim();
                String high = etTotalMoney.getText().toString().trim();
                String message = etBeizhu.getText().toString().trim();
                String num = etNum.getText().toString().trim();
                String price = et_price.getText().toString().trim();
                if (ToolUtils.isNull(coin)) {
                    T.showLong(getActivity(), getString(R.string.otc_please_bz));
                    return;
                }
                if (ToolUtils.isNull(country)) {
                    T.showLong(getActivity(), getString(R.string.otc_please_address));
                    return;
                }
                if (ToolUtils.isNull(code)) {
                    T.showLong(getActivity(), getString(R.string.otc_please_select_b));
                    return;
                }
                if (ToolUtils.isNull(num)) {
                    T.showLong(getActivity(), getString(R.string.otc_please_input_number));
                    return;
                }

                if (ToolUtils.isNull(low)) {
                    T.showLong(getActivity(), getString(R.string.otc_please_zuixiao));
                    return;
                }
                if (ToolUtils.isNull(high)) {
                    T.showLong(getActivity(), getString(R.string.otc_please_zuigao));
                    return;
                }
                if (ToolUtils.isNull(payMethod)) {
                    T.showLong(getActivity(), getString(R.string.otc_please_zf_fangs));
                    return;
                }

                if (ToolUtils.isNull(message)) {
                    T.showLong(getActivity(), getString(R.string.otc_please_input_ly));
                    return;
                }
                if (price_type.equals("0")) {
                    if (ToolUtils.isNull(yijia)) {
                        T.showLong(getActivity(), getString(R.string.otc_please_yj));
                        return;
                    }
                    presenter.publish(id, type, country, code, yijia, low, high, time, num, message, payMethod, coin);
                } else {
                    if (ToolUtils.isNull(price)) {
                        T.showLong(getActivity(), getString(R.string.otc_please_jg));
                        return;
                    }
                    presenter.publish2(id, type, country, code, price, low, high, time, num, message, payMethod, coin);
                }
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 200 && resultCode == ACEConstant.REQUEST_OK) {
            country = ((CountryEntity) data.getSerializableExtra("countriesEntity")).getId();
            tvCountry.setText( ((CountryEntity) data.getSerializableExtra("countriesEntity")).getChinese());
        }
        if (resultCode == 200) {
            code = data.getStringExtra("code");
            String num = yijiaEt.getText().toString().trim();
            tv_price_unit.setText(code);
            tv_total_unit.setText(code);
            tv_gu_price_unit.setText(code);
            tvCoinUnit.setText(code);
            if (!TextUtils.isEmpty(num)) {
                presenter.getyijia(coin, num, code);
            }
        }
    }
}
