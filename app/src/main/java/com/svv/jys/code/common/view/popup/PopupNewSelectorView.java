package com.svv.jys.code.common.view.popup;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.MBaseAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PopupNewSelectorView {
    @BindView(R.id.rv_pop_payment)
    RecyclerView rvPopPayment;
    @BindView(R.id.tv_cancle)
    TextView tvCancle;
    private Context context;
    private PopupWindow popupWindow;
    private int width, height;
    private List<String> list;
    private SelectAdapter adapter;

    public void setDa(boolean da) {
        isDa = da;
    }

    private boolean isDa;
    public PopupNewSelectorView(Context context, List<String> list) {
        this.context = context;
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        this.width = wm.getDefaultDisplay().getWidth();
        this.height = wm.getDefaultDisplay().getHeight();
        this.list=list;

    }

    public void setOnClickItem(OnClickItem onClickItem) {
        this.onClickItem = onClickItem;
    }

    OnClickItem onClickItem;

    public interface OnClickItem {
        void OnSelect(int position,String value);
    }

    public void setValue(String value){
        if(adapter!=null){
            adapter.setValueSelect(value);
        }
    }

    private void initPopupWindow() {
        View popView = LayoutInflater.from(context).inflate(R.layout.pop_payment, null);
        popupWindow = new PopupWindow(popView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ButterKnife.bind(this,popView);
        rvPopPayment.setLayoutManager(new LinearLayoutManager(context));
        adapter = new SelectAdapter(context,list);
        adapter.setDa(isDa);
        adapter.setOnItemClick(new MBaseAdapter.MOnItemClickListener() {
            @Override
            public void onItemClick(int position, Object o) {
                onClickItem.OnSelect(position,list.get(position));
                dismiss();
            }
        });
        rvPopPayment.setAdapter(adapter);
        tvCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
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
        initPopupWindow();
        // 获取弹框的真实高度
        popupWindow.getContentView().measure(View.MeasureSpec.UNSPECIFIED,
                View.MeasureSpec.UNSPECIFIED);
        // 获取popwindow焦点
        popupWindow.setFocusable(true);
        // 设置popwindow如果点击外面区域，便关闭。
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
        if (Build.VERSION.SDK_INT < 24) {
            popupWindow.update();
        } else {
            popupWindow.dismiss();
            popupWindow.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
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
}
