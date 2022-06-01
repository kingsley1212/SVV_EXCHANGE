package com.svv.jys.code.common.view.popup;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.utils.ToolUtils;


/**
 * 对话框
 * Created by Administrator on 2018/3/24 0024.
 */

public class PopupDialogView {
    private Context context;
    private PopupWindow popupWindow;
    private int width, height;
    public View parentView;

    private TextView dialogTitle, dialogContent, leftBtn, rightBtn;

    public interface MClickLisnener {
        void leftBtn();

        void rightBtn();
    }

    public MClickLisnener mClickLisnener;

    public PopupDialogView(Context context, MClickLisnener clickLisnener) {
        this.context = context;
        this.mClickLisnener = clickLisnener;

        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        this.width = wm.getDefaultDisplay().getWidth();
        this.height = wm.getDefaultDisplay().getHeight();

        initPopupWindow();
    }

    private void initPopupWindow() {
        View popView = LayoutInflater.from(context).inflate(R.layout.popup_dialogview, null);
        popupWindow = new PopupWindow(popView, width, ViewGroup.LayoutParams.WRAP_CONTENT);

        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // 设置popwindow出现和消失动画
        popupWindow.setAnimationStyle(R.style.PopMenuAnimation2);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
            }
        });

        dialogTitle = popView.findViewById(R.id.dialogTitle);
        dialogContent = popView.findViewById(R.id.dialogContent);
        leftBtn = popView.findViewById(R.id.leftBtn);
        rightBtn = popView.findViewById(R.id.rightBtn);

        leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mClickLisnener != null) {
                    mClickLisnener.leftBtn();
                    dismiss();
                }
            }
        });
        rightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mClickLisnener != null) {
                    mClickLisnener.rightBtn();
                    dismiss();
                }
            }
        });
    }

    /**
     * 显示popWindow
     */
    public void showPop(View parent, String title, String msg, String leftBtnText, String rightBtnText) {
        this.parentView = parent;
        dialogTitle.setText(title);
        dialogContent.setText(msg);
        leftBtn.setText(leftBtnText);
        rightBtn.setText(rightBtnText);

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
