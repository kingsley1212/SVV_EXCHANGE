package com.svv.jys.code.common.view.popup;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.utils.ToolUtils;


/**
 * 对话框
 * Created by Administrator on 2018/3/24 0024.
 */

public class PopupOrderSXView {
    private int width, height;
    private Context context;
    private PopupWindow popupWindow;
    public View parentView;

    private String selectType;
    private boolean isShowMarket, isBuy, isSell;

    public PopupOrderSXView(Context context) {
        this.context = context;
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        this.width = wm.getDefaultDisplay().getWidth();
        this.height = wm.getDefaultDisplay().getHeight();

        initPopupWindow();
    }

    private void initPopupWindow() {
        final View popview = LayoutInflater.from(context).inflate(R.layout.pop_entrust_shaixuan, null);
        popupWindow = new PopupWindow(popview, width, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // 设置popwindow出现和消失动画
        popupWindow.setAnimationStyle(R.style.PopMenuAnimation2);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
            }
        });

        final RecyclerView recyclerView = popview.findViewById(R.id.rv_order_entrust);
        final TextView tv_market_select = popview.findViewById(R.id.tv_market_select);
        RelativeLayout rl_market = popview.findViewById(R.id.rl_market);
        final EditText et_coin = popview.findViewById(R.id.et_coin);
        final TextView tv_type_buy = popview.findViewById(R.id.tv_type_buy);
        final TextView tv_type_sell = popview.findViewById(R.id.tv_type_sell);
//        for (int i = 0; i < list.size(); i++) {
//            if (list.get(i).isSelect()) {
//                tv_market_select.setText(list.get(i).getName().toUpperCase());
//            }
//        }
        if (isBuy) {
            tv_type_buy.setSelected(true);
        } else {
            tv_type_buy.setSelected(false);
        }
        if (isSell) {
            tv_type_sell.setSelected(true);
        } else {
            tv_type_sell.setSelected(false);
        }
        tv_type_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectType = "1";
                isBuy = true;
                isSell = false;
                tv_type_buy.setSelected(isBuy);
                tv_type_sell.setSelected(isSell);
            }
        });
        tv_type_sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectType = "2";
                isBuy = false;
                isSell = true;
                tv_type_buy.setSelected(isBuy);
                tv_type_sell.setSelected(isSell);
            }
        });
        if (isShowMarket) {
            recyclerView.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.GONE);
        }
        popview.findViewById(R.id.rl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });
        rl_market.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isShowMarket = !isShowMarket;
                if (isShowMarket) {
                    recyclerView.setVisibility(View.VISIBLE);
                } else {
                    recyclerView.setVisibility(View.GONE);
                }
            }
        });




    }

    /**
     * 显示popWindow
     */
    public void showPop(View parent, String title, String msg, String leftBtnText, String rightBtnText) {
        this.parentView = parent;

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
