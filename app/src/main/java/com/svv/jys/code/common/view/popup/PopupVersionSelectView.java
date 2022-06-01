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
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;

import com.svv.jys.R;


/**
 * 是否更新app对话框
 */
public class PopupVersionSelectView {
	public Context context;
	public PopupWindow popupWindow;
	public ISelectUpdate selectUpdate;

	private TextView disagreeUpdate, msg;
	public int width, height;

	/**
	 * 选择是否更新
	 * @author Administrator
	 *
	 */
	public interface ISelectUpdate {
		/**
		 * 同意更新
		 */
        void agreeUpdate();

		/**
		 * 拒绝更新
		 */
        void disagreeUpdate();
	}


	public PopupVersionSelectView(Activity activity, ISelectUpdate selectUpdate) {
		this.context = activity;
		this.selectUpdate = selectUpdate;
		WindowManager windowManager = activity.getWindowManager();
		DisplayMetrics metrics = new DisplayMetrics();
		windowManager.getDefaultDisplay().getMetrics(metrics);
		this.width = metrics.widthPixels;
		this.height = metrics.heightPixels;
		initPopupView();
	}

	/**
	 * 设置新版app更新內容
	 * @param msgContent
	 */
	public void setContentMsg(String msgContent){
		msg.setText(msgContent);
	}


	private void initPopupView() {
		View popView = LayoutInflater.from(context).inflate(
				R.layout.popup_selectversion, null);
		popupWindow = new PopupWindow(popView, width, LayoutParams.MATCH_PARENT);
		msg = popView.findViewById(R.id.msg);
		disagreeUpdate = popView.findViewById(R.id.disagreeUpdate);

		popView.findViewById(R.id.agreeUpdate).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						selectUpdate.agreeUpdate();
						dismiss();
					}
				});
		disagreeUpdate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				selectUpdate.disagreeUpdate();
				dismiss();
			}
		});


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
	}

	/**
	 * 控制界面只展示强制更新
	 */
	public void displayConstrantUpdate(){
		disagreeUpdate.setVisibility(View.GONE);
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
	 * */
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
