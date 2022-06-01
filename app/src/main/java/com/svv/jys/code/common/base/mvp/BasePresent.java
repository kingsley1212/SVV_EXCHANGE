package com.svv.jys.code.common.base.mvp;

import android.app.Activity;
import android.content.Context;

import com.svv.jys.code.common.utils.LoadingUtils;
import com.svv.jys.code.common.utils.T;


/**
 * Created by hankkin on 2017/3/29.
 */

public abstract class BasePresent<V extends BaseView, M> {

    public V view;
    public M model;


    public void attach(V view) {
        this.view = view;
    }

    public void detach() {
        //this.view = null;
    }


    /**
     * 显示加载框
     */
    public void showLoading(final Context mContext) {
        if (mContext == null) {
            return;
        }
        ((Activity)mContext).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                LoadingUtils.getLoadingUtils().showLoadingView(mContext);
            }
        });
    }

    /**
     * 隐藏加载框
     */
    public void dismissLoading(final Context mContext) {
        if (mContext == null) {
            return;
        }
        ((Activity) mContext).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    LoadingUtils.getLoadingUtils().hideLoadingView(mContext);
                } catch (Exception e) {

                }
            }
        });
    }



    public void T_Quick(final String msg) {
        try {
            ((Activity) view.getMContext()).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    T.showShort(view.getMContext(), msg);
                }
            });
        } catch (Exception e) {

        }
    }

    public void T_Quick(final int msgid) {
        try {
            ((Activity) view.getMContext()).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    T.showShort(view.getMContext(), view.getMContext().getString(msgid));
                }
            });
        } catch (Exception e) {

        }
    }

}
