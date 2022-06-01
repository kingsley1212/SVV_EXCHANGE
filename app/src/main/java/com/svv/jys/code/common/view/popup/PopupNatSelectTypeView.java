package com.svv.jys.code.common.view.popup;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;

import com.svv.jys.R;
import com.svv.jys.code.common.utils.ToolUtils;


public class PopupNatSelectTypeView {

	public Context context;
	public View parentView;
	public PopupWindow popupWindow;

	public int width, height;
	public int type;
	public interface NatSelectType {
		void toBuy();

		void toSell();
	}
	public NatSelectType natSelectType;

	public PopupNatSelectTypeView(Context context, NatSelectType natSelectType) {
		super();
		this.context = context;
		this.natSelectType = natSelectType;
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		this.width = wm.getDefaultDisplay().getWidth();
		this.height = wm.getDefaultDisplay().getHeight();
		initPopWindow();
	}

	/**
	 * 初始化弹出的pop
	 * */
	private void initPopWindow() {

		View popView = LayoutInflater.from(context).inflate(
				R.layout.popup_natselecttype, null);
		popupWindow = new PopupWindow(popView, width, LayoutParams.WRAP_CONTENT);

		popView.findViewById(R.id.tobuy_ll).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						natSelectType.toBuy();
						dismiss();
					}
				});

		popView.findViewById(R.id.tosell_ll).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						natSelectType.toSell();
						dismiss();

					}
				});

		popView.findViewById(R.id.canle).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						if (popupWindow.isShowing()) {
							popupWindow.dismiss();
						}
					}
				});

		popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		// 设置popwindow出现和消失动画
		popupWindow.setAnimationStyle(R.style.PopMenuAnimation2);
		// uninster_sure = (TextView) popView.findViewById(R.id.uninster_sure);

		popupWindow.setOnDismissListener(new OnDismissListener() {

			@Override
			public void onDismiss() {
				backgroundAlpha(1f);
			}
		});
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
		this.parentView = parent;
		// 获取弹框的真实高度
		popupWindow.getContentView().measure(View.MeasureSpec.UNSPECIFIED,
				View.MeasureSpec.UNSPECIFIED);
		// 获取popwindow焦点
		popupWindow.setFocusable(true);
		// 设置popwindow如果点击外面区域，便关闭。
		popupWindow.setTouchable(true);
		popupWindow.setOutsideTouchable(true);
		ToolUtils.popupWindowShowCenter(popupWindow, parent, 0,
				(height / 2 - popupWindow.getContentView()
						.getMeasuredHeight() / 2));
		backgroundAlpha(0.5f);

	}

	public boolean isShowing() {

		return popupWindow.isShowing();

	}

	public void dismiss() {

		if (popupWindow != null && popupWindow.isShowing()) {
			popupWindow.dismiss();
		}

	}

}
