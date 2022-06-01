package com.svv.jys.code.common.view.popup;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.view.selfview.ProgressPercentView;


/**
 * 是否更新app对话框
 */
public class PopupDownloadView {
    public Context context;
    public PopupWindow popupWindow;

    public int width, height;

    private TextView downLoadValue, dismissPop;
    private ProgressPercentView downloadBar;

    private boolean downloadEnd;
    private DoAction doAction;

    public PopupDownloadView(Activity activity) {
        this.context = activity;
        WindowManager windowManager = activity.getWindowManager();
        DisplayMetrics metrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(metrics);
        this.width = metrics.widthPixels;
        this.height = metrics.heightPixels;
        initPopupView();
    }

    /**
     * 设置进度条和内容
     *
     * @param percent
     */
    public void setContentMsg(int percent) {
        if (!downloadEnd) {
            downLoadValue.setText(percent + "%");
            downloadBar.setPercent(percent);
        }
    }

    public interface DoAction {
        void openPath();
    }
    /**
     * 下载成功并显示信息
     *
     * @param content
     * @param doAction
     */
    public void downLoadSuccessSetMsg(String content, final DoAction doAction) {
        this.doAction = doAction;
        downloadEnd = true;
        downloadBar.setPercent(100);
        dismissPop.setVisibility(View.VISIBLE);
        dismissPop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (doAction != null) {
                    doAction.openPath();
                }
            }
        });
        downLoadValue.setText(content);
    }

    private void initPopupView() {
        View popView = LayoutInflater.from(context).inflate(
                R.layout.popup_download, null);
        popupWindow = new PopupWindow(popView, width, LayoutParams.MATCH_PARENT);

        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // 设置popwindow出现和消失动画
        popupWindow.setAnimationStyle(R.style.PopMenuAnimation2);

        popupWindow.setBackgroundDrawable(BitmapDrawable.createFromPath(null));
        popupWindow.setOnDismissListener(new OnDismissListener() {

            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
            }
        });
        popupWindow.setOutsideTouchable(true);
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return event.getAction() == MotionEvent.ACTION_OUTSIDE;
            }
        });

        downLoadValue = popView.findViewById(R.id.downLoadValue);
        downloadBar = popView.findViewById(R.id.downloadBar);
        downloadBar.setPercent(0);
        dismissPop = popView.findViewById(R.id.dismissPop);
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

    /**
     * 显示popWindow
     */
    public void showPop(View parent) {
        // 获取弹框的真实高度
        popupWindow.getContentView().measure(View.MeasureSpec.UNSPECIFIED,
                View.MeasureSpec.UNSPECIFIED);
        popupWindow.showAtLocation(parent, Gravity.CENTER, 0, 0);
        // 获取popwindow焦点
        popupWindow.setFocusable(true);
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(false);
        if (Build.VERSION.SDK_INT < 24) {
            popupWindow.update();
        } else {
            popupWindow.dismiss();
            popupWindow.showAtLocation(parent, Gravity.CENTER, 0, 0);
        }
        backgroundAlpha(0.5f);
    }

    public void dismiss() {
        if (popupWindow != null && popupWindow.isShowing()) {
            popupWindow.dismiss();
        }
    }

}
