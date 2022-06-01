package com.svv.jys.code.common.view.popup;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.module.myself.ordermanage.base.adapter.ExchangeAdapter;

import java.util.List;

public class PopupFilterView {
    private Context context;
    private PopupWindow popupWindow;
    private GridView coin_grid_view;
    private ExchangeAdapter adapter;
    private View drop_iv, buy_tv, sell_tv, commit_tv, reset_tv;
    private TextView exchange_b_tv;
    private EditText coin_et;
    private String type = "0", coin = "", unit = "";

    public void setOnClickItem(OnResultDismiss listener) {
        this.listener = listener;
    }

    OnResultDismiss listener;

    public interface OnResultDismiss {
        void OnSelect(String market, String type);
    }

    public PopupFilterView(Context context) {
        this.context = context;
        initPopupWindow();
    }

    private void initPopupWindow() {
        View popView = LayoutInflater.from(context).inflate(R.layout.item_order_filter, null);
        coin_grid_view = popView.findViewById(R.id.coin_grid_view);
        drop_iv = popView.findViewById(R.id.drop_iv);
        exchange_b_tv = popView.findViewById(R.id.exchange_b_tv);
        buy_tv = popView.findViewById(R.id.buy_tv);
        sell_tv = popView.findViewById(R.id.sell_tv);
        coin_et = popView.findViewById(R.id.coin_et);
        commit_tv = popView.findViewById(R.id.commit_tv);
        reset_tv = popView.findViewById(R.id.reset_tv);
        buy_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buy_tv.setSelected(!buy_tv.isSelected());
                sell_tv.setSelected(false);
                if (buy_tv.isSelected())
                    type = "1";
                else
                    type = "0";
            }
        });
        sell_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sell_tv.setSelected(!sell_tv.isSelected());
                buy_tv.setSelected(false);
                if (sell_tv.isSelected())
                    type = "2";
                else
                    type = "0";
            }
        });
        reset_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                coin_et.setText("");
                exchange_b_tv.setText("");
                unit = "";
                adapter.setValue("");
                switch (type) {
                    case "1":
                        buy_tv.performClick();
                        break;
                    case "2":
                        sell_tv.performClick();
                        break;
                }
            }
        });
        commit_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String market;
                coin = coin_et.getText().toString();
                if (TextUtils.isEmpty(coin) || TextUtils.isEmpty(unit)) {
                    market = null;
                } else {
                    market = coin + "_" + unit;
                }
                dismiss();
                listener.OnSelect(market,type);
            }
        });
        popView.findViewById(R.id.select_rl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drop_iv.setSelected(!drop_iv.isSelected());
                if (drop_iv.isSelected()) {
                    coin_grid_view.setVisibility(View.VISIBLE);
                } else {
                    coin_grid_view.setVisibility(View.GONE);
                }
            }
        });
        popupWindow = new PopupWindow(popView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // 设置popwindow出现和消失动画
        popupWindow.setAnimationStyle(R.style.PopMenuAnimation2);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
            }
        });

    }


    /**
     * 显示popWindow
     */
    public void showPop(View parent) {

        // 获取弹框的真实高度
        popupWindow.getContentView().measure(View.MeasureSpec.UNSPECIFIED,
                View.MeasureSpec.UNSPECIFIED);
        // 获取popwindow焦点
        popupWindow.setFocusable(true);
        // 设置popwindow如果点击外面区域，便关闭。
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAtLocation(parent, Gravity.TOP, 0, 0);
        if (Build.VERSION.SDK_INT < 24) {
            popupWindow.update();
        } else {
            popupWindow.dismiss();
            popupWindow.showAtLocation(parent, Gravity.TOP, 0, 0);
        }
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

    public void setGridData(List<String> list) {
        if (adapter == null) {
            adapter = new ExchangeAdapter(context, list);
            coin_grid_view.setAdapter(adapter);
            adapter.setOnItemClick(new ExchangeAdapter.MOnItemClickListener() {
                @Override
                public void onItemClick(int position, String s) {
                    exchange_b_tv.setText(s.toUpperCase());
                    unit = s;
                }
            });
        } else {
            adapter.setData(list);
        }
    }
}
