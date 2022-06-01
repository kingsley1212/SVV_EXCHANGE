package com.svv.jys.code.common.view.popup;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;

import com.svv.jys.R;
import com.svv.jys.code.common.base.MBaseAdapter;

import java.util.List;

public class PopupSelectItem2 implements OnClickListener {

	public Context context;
	public PopupWindow popupWindow;
	public View parent;

	public List<String> items;

	public OnItemSelect2 onItemSelect;

	public interface OnItemSelect2 {

		/**
		 *
		 * @param position
		 * 从0开始，选择第position+1个选项
		 * @param o
		 */
		void onItemSelet(int position, Object o);

	}

	public PopupSelectItem2(Context context, List<String> items,
                            OnItemSelect2 onItemSelect) {
		this.context = context;
		this.items = items;
		this.onItemSelect = onItemSelect;
		initPopWindow();
	}

	private void initPopWindow() {

		View popView = LayoutInflater.from(context).inflate(
				R.layout.popup_selectitem_2, null);
		RecyclerView rv_select=popView.findViewById(R.id.rv_select);
		rv_select.setLayoutManager(new LinearLayoutManager(context));
		SelectAdapter adapter=new SelectAdapter(context,items);
		adapter.setOnItemClick(new MBaseAdapter.MOnItemClickListener() {
			@Override
			public void onItemClick(int position, Object o) {
				if (onItemSelect != null) {
						onItemSelect.onItemSelet(position, items.get(position));
					}
					popupWindow.dismiss();
			}
		});
		rv_select.setAdapter(adapter);
//		for (int i = 0; i < items.size(); i++) {
//
//			TextView t = new TextView(context);
//			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
//					LinearLayout.LayoutParams.MATCH_PARENT,
//					ResourceUtils.getPixelsFromDp(context, 30));
//			lp.setMargins(5, 5, 5, 5);
//			lp.gravity = Gravity.CENTER;
//			t.setGravity(Gravity.CENTER);
//			t.setPadding(2, 2, 2, 2);
//			t.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13);
//			t.setText(items.get(i));
//			t.setLayoutParams(lp);
//			t.setTag(i);
//			t.setOnClickListener(new OnClickListener() {
//
//				@Override
//				public void onClick(View view) {
//					if (onItemSelect != null) {
//						onItemSelect.onItemSelet((int) view.getTag(),
//								items.get((int) view.getTag()));
//					}
//					popupWindow.dismiss();
//				}
//			});
//
//			((LinearLayout) popView).addView(t);
//
//			View line = new View(context);
//			LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(
//					LinearLayout.LayoutParams.MATCH_PARENT,
//					ResourceUtils.dp2px(context, 1));
//			line.setBackgroundColor(0xffe8e8e8);
//			line.setLayoutParams(lp2);
//
//			((LinearLayout) popView).addView(line);
//		}


		popupWindow = new PopupWindow(popView, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		popupWindow.setBackgroundDrawable(new ColorDrawable());
		// 设置popwindow出现和消失动画
		popupWindow.setAnimationStyle(R.style.PopMenuAnimation2);

		popupWindow.setOnDismissListener(new OnDismissListener() {

			@Override
			public void onDismiss() {
				backgroundAlpha(1f);
			}
		});
	}

	/**
	 * �设置添加屏幕的背景透明度�
	 *
	 * @param bgAlpha
	 */
	public void backgroundAlpha(float bgAlpha) {
		WindowManager.LayoutParams lp = ((Activity) context).getWindow()
				.getAttributes();
		lp.alpha = bgAlpha; // 0.0-1.0
		((Activity) context).getWindow().setAttributes(lp);
	}

	@Override
	public void onClick(View v) {
		int[] arrayOfInt = new int[2];
		// 获取点击按钮的坐标
		v.getLocationOnScreen(arrayOfInt);
		int x = arrayOfInt[0];
		int y = arrayOfInt[1];
		showPop(v);
	}

	/**
	 *  显示popWindow
	 * */
	public void showPop(View parent) {
		int width = parent.getMeasuredWidth();
		if (popupWindow != null && !popupWindow.isShowing()) {
			popupWindow.setWidth(width);
			popupWindow.showAsDropDown(parent);
			// 获取popwindow焦点
			popupWindow.setFocusable(true);
			// 设置popwindow如果点击外面区域，便关闭。
			popupWindow.setTouchable(true);
			popupWindow.setOutsideTouchable(true);
			popupWindow.update();
			backgroundAlpha(0.5f);
		}

	}

}
