package com.svv.jys.code.common.view.popup;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.utils.T;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.net.req.POST_COINBUSINESS_SUBMIT_REQ;

/**
 * Created by lzj on 2018/5/26.
 */

public class PopupBusinessSubmitView {
    private Context context;
    private PopupWindow popupWindow;

    private TextView price_tx, number_tx, cancle, sure;
    private TextView price_tv_1, num_tv_1;
    private EditText aqpass_et;
    private View price_rl,num_rl;

    private int width, height;
    private String price, num;
    private int type;

    public interface SubmitLisnener {
        void doCancle();
        void doSubmit(String price, String num, String pw,int type);
    }

    public PopupBusinessSubmitView.SubmitLisnener submitLisnener;

    public PopupBusinessSubmitView(Context context, PopupBusinessSubmitView.SubmitLisnener submitLisnener) {
        this.context = context;
        this.submitLisnener = submitLisnener;

        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        this.width = wm.getDefaultDisplay().getWidth();
        this.height = wm.getDefaultDisplay().getHeight();

        initPopupWindow();
    }

    private void initPopupWindow() {
        View popView = LayoutInflater.from(context).inflate(R.layout.popup_business_submit, null);
        popupWindow = new PopupWindow(popView, width, ViewGroup.LayoutParams.WRAP_CONTENT);

        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // 设置popwindow出现和消失动画
        popupWindow.setAnimationStyle(R.style.PopMenuAnimation2);
        popupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
            }
        });

        price_tv_1 = popView.findViewById(R.id.price_tv_1);
        num_tv_1 = popView.findViewById(R.id.num_tv_1);

        price_tx = popView.findViewById(R.id.price_tx);
        number_tx = popView.findViewById(R.id.number_tx);

        cancle = popView.findViewById(R.id.cancle);
        sure = popView.findViewById(R.id.sure);
        aqpass_et = popView.findViewById(R.id.aqpass_et);

        num_rl = popView.findViewById(R.id.num_rl);
        price_rl = popView.findViewById(R.id.price_rl);

        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (submitLisnener != null) {
                    submitLisnener.doCancle();
                    dismiss();
                }
            }
        });
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (submitLisnener != null) {
                    String pw = aqpass_et.getText().toString().toString();
                    if (ToolUtils.isNull(pw)) {
                        T.showShort(context, context.getString(R.string.popupBusiness_pwnull));
                        return;
                    }
                    submitLisnener.doSubmit(price, num, pw,type);
                    dismiss();
                }
            }
        });
    }
    public void showPop(View parent, int type, String price, String number){
//        num_rl.setVisibility(View.GONE);
//        price_rl.setVisibility(View.GONE);
        showPop(parent,type,price,number,"","");
    }


    /**
     * 显示popWindow
     */
    public void showPop(View parent, int type, String price, String number,String buycoin,String sellcoin) {
        if (ToolUtils.isNull(price)) {
            price = "0";
        }
        aqpass_et.setText("");
        this.price = price;
        this.num = number;
        this.type = type;
        switch (type) {
            case POST_COINBUSINESS_SUBMIT_REQ.P_BUY:
                price_tv_1.setText(context.getString(R.string.popupBusinessSubmit_tx1_buy));
                if (!price.equals(context.getString(R.string.business_zuiyoujia2))) {//限价
                    num_tv_1.setText(context.getString(R.string.popupBusinessSubmit_tx3_buy));
                    price_tx.setText(price+sellcoin.toUpperCase());
                    number_tx.setText(number);
                } else {//市价
                    num_tv_1.setText(context.getString(R.string.popupBusinessSubmit_tx5));
                    price_tx.setText(price);
                    number_tx.setText(number);
                }

                break;
            case POST_COINBUSINESS_SUBMIT_REQ.P_SELL:
                price_tv_1.setText(context.getString(R.string.popupBusinessSubmit_tx1_sell));
                if (!price.equals(context.getString(R.string.business_zuiyoujia2))) {//限价
                    num_tv_1.setText(context.getString(R.string.popupBusinessSubmit_tx3_sell));
                    price_tx.setText(price+buycoin.toUpperCase());
                    number_tx.setText(number);
                } else {//市价
                    num_tv_1.setText(context.getString(R.string.popupBusinessSubmit_tx3_sell));
                    price_tx.setText(price);
                    number_tx.setText(number);
//                    num_tv_1.setText(context.getString(R.string.popupBusinessSubmit_tx5));
                }
                break;
        }
//        price_tx.setText(price);
//        number_tx.setText(number);

        // 获取弹框的真实高度
        popupWindow.getContentView().measure(View.MeasureSpec.UNSPECIFIED,
                View.MeasureSpec.UNSPECIFIED);
        // 获取popwindow焦点
        popupWindow.setFocusable(true);
        // 设置popwindow如果点击外面区域，便关闭。
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);
        ToolUtils.popupWindowShowCenter(popupWindow, parent, 0, 0);
        backgroundAlpha(0.5f);
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = ((Activity) context).getWindow()
                .getAttributes();
        lp.alpha = bgAlpha; // 0.0-1.0
        ((Activity) context).getWindow().setAttributes(lp);
    }

    public void dismiss() {
        if (popupWindow != null && popupWindow.isShowing()) {
            popupWindow.dismiss();
        }
    }
}
