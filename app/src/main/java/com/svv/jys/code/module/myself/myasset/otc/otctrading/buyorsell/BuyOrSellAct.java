package com.svv.jys.code.module.myself.myasset.otc.otctrading.buyorsell;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.svv.jys.R;
import com.svv.jys.code.common.base.mvp.MvpActivity;
import com.svv.jys.code.common.entity.AdvEntity;
import com.svv.jys.code.common.utils.GlideUtils;
import com.svv.jys.code.common.utils.T;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.common.view.popup.PswPopupView;
import com.svv.jys.code.module.myself.myasset.otc.otctrading.buyorsell.model.BuyOrSellModel;
import com.svv.jys.code.module.myself.myasset.otc.otctrading.buyorsell.presenter.BuyOrSellPresenter;
import com.svv.jys.code.module.myself.myasset.otc.otctrading.buyorsell.view.BuyOrSellView;
import com.svv.jys.code.module.myself.myasset.otc.otctrading.orderdetail.OrderDetailAct;

/**
 * Created by js on 2018/6/9.
 */

public class BuyOrSellAct extends MvpActivity<BuyOrSellView, BuyOrSellModel, BuyOrSellPresenter> implements
        BuyOrSellView, View.OnClickListener {
    private AdvEntity advEntity;
    private String id;
    private String trade_type;
    private TextView name_tv, transaction_num, success_tv, finish_rate_tv, tv_price_cny, tv_limit, tv_num, tv_pay_time, tv_unit, tv_max_unit, tv_max_num, tv_all_buy_sell;
    private View is_realName_tv, iv_otc_bank, iv_otc_zfb, iv_otc_wx;
    private ImageView iv_userImg, vip_logo, iv_email_renz, iv_phone_renz, iv_card_renz, iv_qiehuan,iv_coin_log;
    private TextView buy_tv;
    private int valuePosition = 0;
    private String coin;
    private LinearLayout ll_bank, ll_zfb, ll_wx;
    private EditText et_buy_sell_num;
    private LinearLayout ll_payment;
    @Override
    public Context getMContext() {
        return this;
    }

    @Override
    public BuyOrSellPresenter initPresenter() {
        return new BuyOrSellPresenter();
    }

    @Override
    public void baseInitialization() {
        showNewsMessageBool = false;
        id = getIntent().getStringExtra("id");
        trade_type = getIntent().getStringExtra("trade_type");
        coin = getIntent().getStringExtra("coin");
    }

    @Override
    public int setR_Layout() {
        return R.layout.act_buy_sell;
    }

    @Override
    public void viewInitialization() {
        setBackPress();
        ll_payment=findViewById(R.id.ll_payment);
        iv_coin_log = findViewById(R.id.iv_coin_log);
        iv_qiehuan = findViewById(R.id.iv_qiehuan);
        iv_email_renz = findViewById(R.id.iv_email_renz);
        iv_phone_renz = findViewById(R.id.iv_phone_renz);
        iv_card_renz = findViewById(R.id.iv_card_renz);
        et_buy_sell_num = findViewById(R.id.et_buy_sell_num);
        vip_logo = findViewById(R.id.vip_logo);
        ll_bank = findViewById(R.id.ll_bank);
        ll_zfb = findViewById(R.id.ll_zfb);
        ll_wx = findViewById(R.id.ll_wx);
        tv_all_buy_sell = (TextView) $(R.id.tv_all_buy_sell);
        tv_max_num = (TextView) $(R.id.tv_max_num);
        tv_max_unit = (TextView) $(R.id.tv_max_unit);
        tv_unit = (TextView) $(R.id.tv_unit);
        tv_pay_time = (TextView) $(R.id.tv_pay_time);
        tv_num = (TextView) $(R.id.tv_num);
        tv_limit = (TextView) $(R.id.tv_limit);
        tv_price_cny = (TextView) $(R.id.tv_price_cny);
        name_tv = (TextView) $(R.id.name_tv);
        transaction_num = (TextView) $(R.id.transaction_num);
        success_tv = (TextView) $(R.id.success_tv);
        finish_rate_tv = (TextView) $(R.id.finish_rate_tv);
        is_realName_tv = $(R.id.is_realName_tv);
        iv_otc_bank = $(R.id.iv_otc_bank);
        iv_otc_zfb = $(R.id.iv_otc_zfb);
        iv_userImg = (ImageView) $(R.id.iv_userImg);
        iv_otc_wx = $(R.id.iv_otc_wx);
        buy_tv = (TextView) $(R.id.buy_tv);
        if (trade_type.equals("0")) {
//            et_buy_sell_num.setHint(getString(R.string.hint_want_buy));
            setTitleTx(getString(R.string.OtcBuyOrSellFrag_goumai) + coin.toUpperCase());
        } else {
//            et_buy_sell_num.setHint(getString(R.string.hint_want_sell));
            setTitleTx(getString(R.string.OtcBuyOrSellFrag_chushou) + coin.toUpperCase());
        }
        tv_all_buy_sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (advEntity != null) {
                    if (valuePosition == 0) {
                        et_buy_sell_num.setText(advEntity.getMax_amount());
                    } else {
                        et_buy_sell_num.setText(ToolUtils.division(advEntity.getMax_amount(), advEntity.getPrice(), 2));
                    }
                }

            }
        });
        et_buy_sell_num.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s.toString())) {
                    if (valuePosition==0){
//                        if (trade_type.equals("0")) {
//                            tv_max_num.setText(getString(R.string.max_able_buy) + advEntity.getCoin_name() + ToolUtils.division(s.toString(),advEntity.getPrice(),8));
//                        } else {
//                            tv_max_num.setText(getString(R.string.max_able_sell) + advEntity.getCoin_name() + ToolUtils.division(s.toString(),advEntity.getPrice(),8));
//                        }
                        tv_max_num.setText(ToolUtils.division(s.toString(),advEntity.getPrice(),8));
                    }else {
                        tv_max_num.setText( ToolUtils.trimZero(ToolUtils.multiplyWithScale(s.toString(),advEntity.getPrice(),8)));
                    }

                }else {
                    tv_max_num.setText("0");
                }
            }
                @Override
                public void afterTextChanged (Editable s){

                }
            });
        iv_qiehuan.setOnClickListener(new View.OnClickListener()

            {
                @Override
                public void onClick (View v){
                if (advEntity != null) {
                    valuePosition = valuePosition == 0 ? 1 : 0;
                    et_buy_sell_num.setText("");
                    if (valuePosition == 0) {
                        tv_max_unit.setText(advEntity.getCoin_name());
                        tv_unit.setText(advEntity.getCurrency());
                        if (trade_type.equals("0")) {
//                            et_buy_sell_num.setHint(getString(R.string.hint_want_buy));
                            tv_max_num.setText(getString(R.string.max_able_buy) + " " +advEntity.getCoin_name() + ToolUtils.division(advEntity.getMax_amount(),advEntity.getPrice(),8));
                        } else {
//                            et_buy_sell_num.setHint(getString(R.string.hint_want_sell));
                            tv_max_num.setText(getString(R.string.max_able_sell) + " " +advEntity.getCoin_name() + ToolUtils.division(advEntity.getMax_amount(),advEntity.getPrice(),8));
                        }
                        et_buy_sell_num.setHint(getString(R.string.BuyOrSellAct_xiane)+ " " +advEntity.getMin_amount() + "-" + advEntity.getMax_amount());
                    } else {
                        tv_max_unit.setText(advEntity.getCurrency());
                        tv_unit.setText(advEntity.getCoin_name());
                        et_buy_sell_num.setHint(getString(R.string.max) + ToolUtils.division(advEntity.getMax_amount(),advEntity.getPrice(),8));
//                        if (trade_type.equals("0")) {
////                            et_buy_sell_num.setHint(getString(R.string.hint_want_buy_num));
//                            tv_max_num.setText(getString(R.string.max_able_buy) + advEntity.getCurrency() + advEntity.getMax_amount());
//                        } else {
////                            et_buy_sell_num.setHint(getString(R.string.hint_want_sell_num));
//                            tv_max_num.setText(getString(R.string.max_able_sell) + advEntity.getCurrency() + advEntity.getMax_amount());
//                        }
                        tv_max_num.setText(getString(R.string.BuyOrSellAct_xiane)+advEntity.getMin_amount() + "-" + advEntity.getMax_amount());
                    }
                }
            }
            });
        buy_tv.setOnClickListener(new View.OnClickListener()

            {
                @Override
                public void onClick (View v){
                final String num = et_buy_sell_num.getText().toString().trim();
                if (TextUtils.isEmpty(num)) {
                    if (trade_type.equals("0")) {
                        T.showShort(BuyOrSellAct.this, getString(R.string.hint_want_buy));
                    } else {
                        T.showShort(BuyOrSellAct.this, getString(R.string.hint_want_sell));
                    }
                    return;
                }
                if (ToolUtils.isFastClick(v.getId())){
                    PswPopupView pswPopupView = new PswPopupView(BuyOrSellAct.this, new PswPopupView.SubmitLisnener() {
                        @Override
                        public void doSubmit(String pw) {
                            presenter.postOtcAdvBuy(valuePosition, id, advEntity.getType(), num, pw,true);
                        }
                    });
                    if (advEntity!=null&&advEntity.getLogin_user()!=null){
                        if ( advEntity.getLogin_user().getPwd_type().equals("0")){
                            pswPopupView.showPop(v);
                        }else if (advEntity.getLogin_user().getPwd_type().equals("1")&&!advEntity.getLogin_user().isCheck_pwd()){
                            pswPopupView.showPop(v);
                        }else {
                            presenter.postOtcAdvBuy(valuePosition, id, advEntity.getType(), num, "",false);
                        }
                    }
                }



            }
            });
        }

        @Override
        public void doBusiness () {
            presenter.getAdvInfo(id, trade_type);
        }


        @Override
        public void onClick (View view){
        }


        @Override
        public void buyOrSellSuccese (String id,boolean isCheck){
            if (isCheck){
                advEntity.getLogin_user().setCheck_pwd(true);
                ToolUtils.saveUserInfo(getMContext(),advEntity.getLogin_user());
                }
            T.showShort(this, getString(R.string.BuyOrSellAct_cz_success));
            id = JSON.parseObject(id).getString("id");
            OrderDetailAct.startOtcOrderDetail(BuyOrSellAct.this, id);
            finish();
        }

        @Override
        public void setInfoData ( final AdvEntity entity){
            advEntity = entity;
            GlideUtils.loadUserLogo(getMContext(), entity.getUser().getLogo(), iv_userImg);
            name_tv.setText(entity.getR_name());
            tv_price_cny.setText(entity.getPrice() + " " + entity.getCurrency());
            tv_limit.setText(entity.getMin_amount() + "-" + entity.getMax_amount());
            tv_num.setText(entity.getNum() + entity.getCoin_name());
            tv_pay_time.setText(entity.getPrompt() + getString(R.string.buytypeads_item9_unit));
            tv_unit.setText(entity.getCurrency());
            if (trade_type.equals("0")) {
                tv_max_num.setText(getString(R.string.max_able_buy) + " " +entity.getCoin_name() + ToolUtils.division(entity.getMax_amount(),entity.getPrice(),8));
                tv_all_buy_sell.setText(getString(R.string.all_buy));
            } else {
                tv_max_num.setText(getString(R.string.max_able_sell) + " " +entity.getCoin_name() + ToolUtils.division(entity.getMax_amount(),entity.getPrice(),8));
                tv_all_buy_sell.setText(getString(R.string.all_sell));
            }
            GlideUtils.loadImageDefult(this,entity.getCoin_logo(),iv_coin_log);
            et_buy_sell_num.setHint(getString(R.string.BuyOrSellAct_xiane)+entity.getMin_amount() + "-" + entity.getMax_amount());
            iv_email_renz.setSelected(!entity.getUser().getVerify_email().equals("0"));
            iv_phone_renz.setSelected(!entity.getUser().getVerify_mobile().equals("0"));
            iv_card_renz.setSelected(!entity.getUser().getIs_identity().equals("0"));

            tv_max_unit.setText(entity.getCoin_name());
            if (entity.getUser().getIs_shoper().equals("0")) {
                is_realName_tv.setVisibility(View.GONE);
                vip_logo.setVisibility(View.GONE);
            }

            String payment = entity.getPayment();
//            JSONObject json = JSONObject.parseObject(payment,Feature.OrderedField);
//            for (Map.Entry<String, Object> e : json.entrySet()) {
//                ImageView imageView = new ImageView(this);
//                imageView.setLayoutParams(new LinearLayout.LayoutParams(40, 40));  //设置图片宽高
//                String ss=e.getKey();
//                if (ss.equals("bank")){
//                    imageView.setImageResource(R.mipmap.pay_unionpay_icon); //图片资源
//                }else if (ss.equals("wechat")){
//                    imageView.setImageResource(R.mipmap.pay_weichat_pay); //图片资源
//                }else if (ss.equals("alipay")){
//                    imageView.setImageResource(R.mipmap.pay_zhifubao_icon); //图片资源
//                }
//
//                ll_payment.addView(imageView); //动态添加图片
//            }
            if (payment.contains("bank")) {
                ll_bank.setVisibility(View.VISIBLE);
            }
            if (payment.contains("wechat")) {
                ll_wx.setVisibility(View.VISIBLE);
            }
            if (payment.contains("alipay")) {
                ll_zfb.setVisibility(View.VISIBLE);
            }
            if (entity.getUser().getPayment_list().contains("bank")) {
                iv_otc_bank.setVisibility(View.VISIBLE);
            }
            if (entity.getUser().getPayment_list().contains("wechat")) {
                iv_otc_wx.setVisibility(View.VISIBLE);
            }
            if (entity.getUser().getPayment_list().contains("alipay")) {
                iv_otc_zfb.setVisibility(View.VISIBLE);
            }
            AdvEntity.OrderMsgBean order_msg = entity.getOrder_msg();
            transaction_num.setText(order_msg.getTotal());
            success_tv.setText(order_msg.getFinish());
            finish_rate_tv.setText(String.format("%s%%", order_msg.getFinish_rate()));

        }

    }
