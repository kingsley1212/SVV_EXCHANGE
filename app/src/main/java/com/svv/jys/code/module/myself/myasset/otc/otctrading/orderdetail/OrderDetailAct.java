package com.svv.jys.code.module.myself.myasset.otc.otctrading.orderdetail;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.mvp.MvpActivity;
import com.svv.jys.code.common.constant.ACEConstant;
import com.svv.jys.code.common.db.nativecache.ACache;
import com.svv.jys.code.common.entity.OrderDetailEntity;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.common.view.selfview.MaxHeightRecyclerView;
import com.svv.jys.code.module.myself.myasset.otc.otctrading.code.CodeAct;
import com.svv.jys.code.module.myself.myasset.otc.otctrading.orderdetail.adapter.PayAdapter;
import com.svv.jys.code.module.myself.myasset.otc.otctrading.orderdetail.model.OrderDetailModel;
import com.svv.jys.code.module.myself.myasset.otc.otctrading.orderdetail.presenter.OrderDetailPresenter;
import com.svv.jys.code.module.myself.myasset.otc.otctrading.orderdetail.view.OrderDetailView;
import com.svv.jys.code.module.server.chatserver.act.InChatActivity;

import java.lang.ref.WeakReference;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by js on 2018/6/11.
 */

public class OrderDetailAct extends MvpActivity<OrderDetailView, OrderDetailModel,
        OrderDetailPresenter> implements
        OrderDetailView {

    private Calendar calendar;

    public static void startOtcOrderDetail(Context context, String orderid) {
        Intent intent = new Intent(context, OrderDetailAct.class);
        intent.putExtra("id", orderid);
        context.startActivity(intent);
    }


    public static void startOtcOrderDetailForResult(Context context, String orderid) {
        Intent intent = new Intent(context, OrderDetailAct.class);
        intent.putExtra("id", orderid);
        ((Activity) context).startActivityForResult(intent, 200);
    }

    private TextView tv_price, tv_total, tv_status, tv_pay_order, tv_num_buy, tv_bank_address, tv_username,
            tv_pay_info, tv_bank_account, tv_bank_name, currency_tv, coin_name_tv;
    private TextView cancel_tv, commit_tv, dead_time_tv, tips_tv,type_title_tv,tv_lianxi_ren;
    String id;
    private View rl_bank_info, order_info_iv, info_ll,code_ll;
    private RelativeLayout rl_xianli;
    private MaxHeightRecyclerView payment_rv;
    private String deadline, addTime;
    private TimeHandler handler = new TimeHandler(this);
    private boolean switchStatus = false;


    @Override
    public Context getMContext() {
        return this;
    }

    @Override
    public OrderDetailPresenter initPresenter() {
        return new OrderDetailPresenter();
    }

    @Override
    public void baseInitialization() {
        id = getIntent().getStringExtra("id");
        setShowNewsMessageClick(new ShowNewsMessageClick() {
            @Override
            public void doSureClick(String orderid) {
                presenter.getOrderDetail(orderid);
            }
        });
    }

    @Override
    public int setR_Layout() {
        return R.layout.act_order_detail;
    }

    @Override
    public void viewInitialization() {
        tv_lianxi_ren=findViewById(R.id.tv_lianxi_ren);
        order_info_iv = findViewById(R.id.order_info_iv);
        type_title_tv = findViewById(R.id.type_title_tv);
        info_ll = findViewById(R.id.info_ll);
        code_ll = findViewById(R.id.code_ll);
        rl_xianli = findViewById(R.id.rl_xianli);
        currency_tv = findViewById(R.id.currency_tv);
        coin_name_tv = findViewById(R.id.coin_name_tv);
        tv_bank_name = findViewById(R.id.tv_bank_name);
        tv_bank_address = findViewById(R.id.tv_bank_address);
        tv_username = findViewById(R.id.tv_username);
        tv_pay_info = findViewById(R.id.tv_pay_info);
        tv_bank_account = findViewById(R.id.tv_bank_account);
        rl_bank_info = findViewById(R.id.rl_bank_info);
        View backView = findViewById(R.id.leftImg_ly);
        payment_rv = findViewById(R.id.payment_rv);
        tips_tv = findViewById(R.id.tips_tv);
        backView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("OrderDetailEntity", otcOrderEntity);
                setResult(100, intent);
                finish();
            }
        });

        tv_num_buy = findViewById(R.id.tv_num_buy);
        tv_price = findViewById(R.id.tv_price);
        tv_total = findViewById(R.id.tv_total);
        tv_status = findViewById(R.id.tv_status);
        tv_pay_order = findViewById(R.id.tv_pay_order);
        cancel_tv = findViewById(R.id.cancel_tv);
        commit_tv = findViewById(R.id.commit_tv);
        payment_rv.setLayoutManager(new LinearLayoutManager(getMContext()));
        dead_time_tv = findViewById(R.id.dead_time_tv);
        info_ll.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                ViewGroup.LayoutParams layoutParams = order_info_iv.getLayoutParams();
                layoutParams.height = info_ll.getMeasuredHeight();
                order_info_iv.setLayoutParams(layoutParams);
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent();
            intent.putExtra("OrderDetailEntity", otcOrderEntity);
            setResult(100, intent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    private void initView(final OrderDetailEntity otcOrderEntity) {
//        if (otcOrderEntity.getIs_shoper().equals("1")){
//            tv_lianxi_ren.setText(otcOrderEntity.getUser_true_name());
//        }

        addTime = otcOrderEntity.getAdd_time();
        deadline = otcOrderEntity.getPrompt();
        switchStatus = false;
        payment_rv.setAdapter(new PayAdapter(getMContext(), otcOrderEntity.getPayment()));
        tv_price.setText(String.format("%s  %s", getString(R.string.otc_adv_price), otcOrderEntity.getPrice()));
        currency_tv.setText(otcOrderEntity.getCurrency());
        tv_total.setText(otcOrderEntity.getAmount() + "  " + otcOrderEntity.getCurrency());
        tv_status.setText(otcOrderEntity.getStatus_name());
        tv_pay_order.setText(otcOrderEntity.getOrder_no());
        tv_num_buy.setText(String.format("%s  %s", getString(R.string.num), otcOrderEntity.getNum()));
        coin_name_tv.setText(otcOrderEntity.getCoin_name().toUpperCase());
        if (TextUtils.isEmpty(otcOrderEntity.getMemo().getTitle())) {
            dead_time_tv.setVisibility(View.GONE);
        } else {
            dead_time_tv.setText(otcOrderEntity.getMemo().getTitle());
        }
        if (TextUtils.isEmpty(otcOrderEntity.getMemo().getContent())) {
            tips_tv.setVisibility(View.GONE);
        } else {
            tips_tv.setText(otcOrderEntity.getMemo().getContent());
        }
        if (otcOrderEntity.getStatus().equals("2") || otcOrderEntity.getStatus().equals("3") || otcOrderEntity.getStatus().equals("4")) {
            cancel_tv.setVisibility(View.GONE);
            commit_tv.setVisibility(View.GONE);
        }
        if (otcOrderEntity.getStatus().equals("0")) {
            cancel_tv.setVisibility(View.GONE);
            commit_tv.setVisibility(View.GONE);
            if (otcOrderEntity.getType().equals("0")) {
                cancel_tv.setVisibility(View.VISIBLE);
                cancel_tv.setText(getString(R.string.order_detail_quxiao_order));
                cancel_tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        presenter.setStatus(otcOrderEntity.getId(), "3");
                    }
                });
                commit_tv.setVisibility(View.VISIBLE);
                commit_tv.setText(getString(R.string.OrderDetailAct_wo_fk));
                commit_tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        presenter.setStatus(otcOrderEntity.getId(), "1");
                    }

                });
                dead_time_tv.setVisibility(View.VISIBLE);
                switchStatus = true;
                handler.sendEmptyMessage(0);
                type_title_tv.setText(getString(R.string.order_detail_account_number));
            }else if(otcOrderEntity.getType().equals("1")){
                type_title_tv.setText(getString(R.string.OrderDetialAct_tv1));
            }
        } else if (otcOrderEntity.getStatus().equals("1")) {
            if (otcOrderEntity.getType().equals("1")) {
                commit_tv.setVisibility(View.VISIBLE);
                commit_tv.setText(getString(R.string.OrderDetailAct_yi_shoukuan));
                commit_tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        presenter.setStatus(otcOrderEntity.getId(), "2");
                    }
                });
                type_title_tv.setText(getString(R.string.OrderDetialAct_tv2));
            } else {
                commit_tv.setVisibility(View.GONE);
                type_title_tv.setText(getString(R.string.OrderDetialAct_tv2));
            }
            cancel_tv.setVisibility(View.VISIBLE);
            cancel_tv.setText(getString(R.string.OrderDetailAct_yes_shens));
            cancel_tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showPop(v);
                }
            });

        } else if (otcOrderEntity.getStatus().equals("5")) {
            commit_tv.setVisibility(View.GONE);
            if (otcOrderEntity.getAppeal_id().equals(ACache.get(this).getAsString(ACEConstant.ACE_USERINFO_USERID))) {
                cancel_tv.setVisibility(View.VISIBLE);
                cancel_tv.setText(getString(R.string.OrderDetailAct_qux_shen));
                cancel_tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        presenter.setStatus(otcOrderEntity.getId(), "5");
                    }
                });

            } else {
                cancel_tv.setVisibility(View.GONE);
            }
            type_title_tv.setText(getString(R.string.OrderDetialAct_tv4));
        }else if(otcOrderEntity.getStatus().equals("2")){
            type_title_tv.setText(otcOrderEntity.getStatus_name());
        }
        OrderDetailEntity.PaymentBean paymentBean = otcOrderEntity.getPayment().get(0);
        showOrderInfo(paymentBean);
    }

    public void showOrderInfo(OrderDetailEntity.PaymentBean paymentBean) {
        final OrderDetailEntity.PaymentBean.MemoBean memoBean = paymentBean.getMemo();
        tv_username.setText(memoBean.getRealname());
        tv_bank_account.setText(memoBean.getAccount());
        if(!TextUtils.isEmpty(memoBean.getPic())){
            code_ll.setVisibility(View.VISIBLE);
            $(R.id.code_iv).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CodeAct.startByUrl(getMContext(),memoBean.getPic());
                }
            });
        }else {
            code_ll.setVisibility(View.GONE);
        }
        if (paymentBean.getPay_code().equals("bank")) {
            tv_bank_name.setText(memoBean.getBankname());
            tv_bank_address.setText(memoBean.getBankaddress());
            tv_bank_address.setVisibility(View.VISIBLE);
            tv_pay_info.setText(getString(R.string.OrderDetailAct_bank_kah));
            rl_bank_info.setVisibility(View.VISIBLE);
        } else {
            tv_pay_info.setText(paymentBean.getPay_info() + getString(R.string.OrderDetailAct_zhagnhao));
            rl_bank_info.setVisibility(View.GONE);
            tv_bank_address.setVisibility(View.GONE);
        }
    }


    @Override
    public void doBusiness() {
        presenter.getOrderDetail(id);
    }


    String type;

    public void showPop(View view) {
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        View popview = LayoutInflater.from(this).inflate(R.layout.pop_shenshu, null);
        final PopupWindow popupWindow = new PopupWindow(popview, width * 4 / 5, ViewGroup.LayoutParams.WRAP_CONTENT);
        final TextView tv_shenshu1 = popview.findViewById(R.id.tv_shenshu1);
        final TextView tv_shenshu2 = popview.findViewById(R.id.tv_shenshu2);
        final EditText et_shenshu = popview.findViewById(R.id.et_shenshu);
        TextView tv_shenshu_queren = popview.findViewById(R.id.tv_shenshu_queren);
        tv_shenshu1.setSelected(true);
//        tv_shenshu1.setClickable(false);
//        tv_shenshu1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                type = getString(R.string.OrderDetailAct_df_wfx);
//                tv_shenshu1.setSelected(true);
//                tv_shenshu2.setSelected(false);
//            }
//        });
//        tv_shenshu2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                type = getString(R.string.OrderDetailAct_df_wfk);
//                tv_shenshu1.setSelected(false);
//                tv_shenshu2.setSelected(true);
//            }
//        });
        if (otcOrderEntity.getType().equals("0")){
            type = getString(R.string.OrderDetailAct_df_wfx);
        }else {
            type = getString(R.string.OrderDetailAct_df_wfk);
        }
        tv_shenshu1.setText(type);

        tv_shenshu_queren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String memo = et_shenshu.getText().toString().trim();
                if (!ToolUtils.isNull(memo)) {
                    presenter.orderShensu(otcOrderEntity.getId(), type, memo);
                    popupWindow.dismiss();
                }
            }
        });
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x000000));
        popupWindow.setFocusable(true);
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);
        ToolUtils.popupWindowShowCenter(popupWindow, cancel_tv, 0, 0);
        backgroundAlpha(0.5f);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1.0f);
            }
        });
    }

    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow()
                .getAttributes();
        lp.alpha = bgAlpha; // 0.0-1.0
        getWindow().setAttributes(lp);
    }

    @Override
    public void setStatusSuccese() {
        presenter.getOrderDetail(id);
    }

    @Override
    public void shensuSuccese() {
        presenter.getOrderDetail(id);

    }

    OrderDetailEntity otcOrderEntity;

    @Override
    public void getOrderDetail(final OrderDetailEntity rowsBean) {
        otcOrderEntity = rowsBean;
        rl_xianli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("id", rowsBean.id);
                gotoActivity(InChatActivity.class, false, bundle);
//                startActivity(InChatActivity.getIntent(OrderDetailAct.this,otcOrderEntity));
            }
        });
        initView(rowsBean);
    }


    public void updataPayment(OrderDetailEntity.PaymentBean paymentBean) {
        showOrderInfo(paymentBean);
    }


    private void banRequest() {

    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {

        }
    };

    private static class TimeHandler extends Handler {
        WeakReference<OrderDetailAct> act;

        TimeHandler(OrderDetailAct act) {
            this.act = new WeakReference<>(act);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String result;
            if(act.get() == null){
                return;
            }
            if (act.get().calendar == null) {
                act.get().calendar = Calendar.getInstance();
                act.get().calendar.setTime(new Date(Long.parseLong(act.get().addTime) * 1000));
                act.get().calendar.add(Calendar.MINUTE, Integer.valueOf(act.get().deadline));
            }
            Calendar now = Calendar.getInstance();
            now.setTime(new Date());

            if (!act.get().calendar.before(now)) {
                Date newDate = new Date(act.get().calendar.getTime().getTime() - new Date().getTime());
                now.setTime(newDate);

                result = (int)(newDate.getTime()/1000/60) + ":" + now.get(Calendar.SECOND);
                if(act.get().switchStatus)
                act.get().handler.sendMessageDelayed(act.get().handler.obtainMessage(0), 1000);
            } else {
                result = "0:0";
            }
            if (act.get().switchStatus)
            act.get().dead_time_tv.setText(String.format("%s%s%s", act.get().getString(R.string.order_detail_text1), result, act.get().getString(R.string.order_detail_text2)));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (handler != null) {
            handler.removeMessages(0);
        }
        handler = null;
    }
}
