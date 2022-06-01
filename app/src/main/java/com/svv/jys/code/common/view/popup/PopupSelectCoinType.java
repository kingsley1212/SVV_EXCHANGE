package com.svv.jys.code.common.view.popup;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.entity.FCoinType;
import com.svv.jys.code.common.utils.ResourceUtils;
import com.svv.jys.code.common.utils.T;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lzj on 2017/11/17.
 */

public class PopupSelectCoinType {
    public boolean isGettingData = true;

    public Context context;
    public int width, height;
    private List<FCoinType> fCoinTypeList = new ArrayList<>();
    public PopupWindow popupWindow;
    public PopupSelectCoinType.DoConfirm doConfirm;

    //实现回调
    public interface DoConfirm {

        void cancle();

        void confirm(FCoinType coinType);


        void loadFinish(FCoinType coinType);
    }

    public PopupSelectCoinType(Context context, PopupSelectCoinType.DoConfirm doConfirm) {
        super();
        this.context = context;
        width = ResourceUtils.getWindowsWidth((Activity) context);
        height = ResourceUtils.getWindowsHeight((Activity) context);
        this.doConfirm = doConfirm;
//        initPopWindow();
        initData(context);
    }

    public void initData(Context context) {
        loadNetData(context);
    }

    public void loadNetData(final Context context) {
//        API_Factory.API_GetBussinessCoinType(new GET_BUSINESS_COINTYPE_REQ()).doOnSubscribe(new Action0() {
//            @Override
//            public void call() {
//            }
//        }).subscribeOn(AndroidSchedulers.mainThread())
//                .observeOn(Schedulers.io()).map(new Func1<BaseResponse, List<FCoinType>>() {
//            @Override
//            public List<FCoinType> call(BaseResponse baseResponse) {
//                List<FCoinType> list = (List<FCoinType>) FCoinType.fromJSONListAuto
//                        (baseResponse.datas,
//                                FCoinType.class);
//                ACache.get(context).put(ACEConstant.ACE_COINTYPE_KEY, baseResponse.datas, ACache.TIME_DAY);
//                return list;
//            }
//        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<List<FCoinType>>() {
//            @Override
//            public void onCompleted() {
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                ToolUtils.doNetErroMsg(context, e, true);
//                if (mOnLoadFinish != null) {
//                    mOnLoadFinish.loadFail();
//                }
//            }
//
//            @Override
//            public void onNext(List<FCoinType> list) {
//                setfCoinTypeList(list);
//            }
//        });
    }

    /**
     * 初始化弹出的pop
     */
    private void initPopWindow(final List<FCoinType> items) {
        View popView = LayoutInflater.from(context).inflate(
                R.layout.popup_selectitem_1, null);

        for (int i = 0; i < items.size(); i++) {
            final FCoinType bean = items.get(i);
            TextView t = new TextView(context);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    ResourceUtils.getPixelsFromDp(context, 30));
            lp.setMargins(5, 5, 5, 5);
            lp.gravity = Gravity.CENTER;
            t.setGravity(Gravity.CENTER);
            t.setPadding(2, 2, 2, 2);
            t.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13);
            t.setText(bean.fullname);
            t.setLayoutParams(lp);
            t.setTag(i);
            t.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if (doConfirm != null) {
                        doConfirm.confirm(bean);
                    }
                    popupWindow.dismiss();
                }
            });

            ((LinearLayout) popView).addView(t);

            View line = new View(context);
            LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    ResourceUtils.dp2px(context, 1));
            line.setBackgroundColor(0xffe8e8e8);
            line.setLayoutParams(lp2);

            ((LinearLayout) popView).addView(line);
            if (i == 0 && doConfirm != null) {
                doConfirm.loadFinish(bean);
            }
        }
        popupWindow = new PopupWindow(popView, ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
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
        if (isGettingData) {
            T.showShort(context, context.getString(R.string.getting_data));
            return;
        }
        // 获取弹框的真实高度
        popupWindow.getContentView().measure(View.MeasureSpec.UNSPECIFIED,
                View.MeasureSpec.UNSPECIFIED);
        popupWindow.showAtLocation(parent, Gravity.CENTER, 0, 0);
        // 获取popwindow焦点
        popupWindow.setFocusable(true);
        // 设置popwindow如果点击外面区域，便关闭。
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);
        if (Build.VERSION.SDK_INT < 24) {
            popupWindow.update();
        } else {
            popupWindow.dismiss();
            popupWindow.showAtLocation(parent, Gravity.CENTER, 0, 0);
        }
        backgroundAlpha(0.5f);
    }

    //返回popupwindow 是否展示
    public boolean isShowing() {
        return popupWindow.isShowing();
    }

    //隐藏popupwindow
    public void dismiss() {
        if (popupWindow != null && popupWindow.isShowing()) {
            popupWindow.dismiss();
        }
    }

    public FCoinType selectDCoin(String coinname) {
        if (fCoinTypeList == null || fCoinTypeList.size() == 0) {
            return null;
        }
        for (int i = 0; i < fCoinTypeList.size(); i++) {
            FCoinType temp = fCoinTypeList.get(i);
            if (temp.getFullname().equals(coinname)) {
                return temp;
            }
        }
        return fCoinTypeList.get(0);
    }
}
