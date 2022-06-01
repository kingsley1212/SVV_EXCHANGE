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

/**
 * Created by lzj on 2018/5/26.
 */

public class PswPopupView {
    private Context context;
    private PopupWindow popupWindow;

    private TextView  cancle, sure;
    private EditText aqpass_et;
    private int width, height;

    public interface SubmitLisnener {
        void doSubmit(String pw);
    }

    public PswPopupView.SubmitLisnener submitLisnener;

    public PswPopupView(Context context, PswPopupView.SubmitLisnener submitLisnener) {
        this.context = context;
        this.submitLisnener = submitLisnener;

        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        this.width = wm.getDefaultDisplay().getWidth();
        this.height = wm.getDefaultDisplay().getHeight();

        initPopupWindow();
    }

    private void initPopupWindow() {
        View popView = LayoutInflater.from(context).inflate(R.layout.psw_popup, null);
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



        cancle = popView.findViewById(R.id.cancle);
        sure = popView.findViewById(R.id.sure);
        aqpass_et = popView.findViewById(R.id.aqpass_et);


        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (submitLisnener != null) {
                    String pw = aqpass_et.getText().toString();
                    if (ToolUtils.isNull(pw)) {
                        T.showShort(context, context.getString(R.string.popupBusiness_pwnull));
                        return;
                    }
                    submitLisnener.doSubmit(pw);
                    dismiss();
                }
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
