package com.svv.jys.code.common.utils;

import android.app.Activity;
import android.os.Bundle;

import com.svv.jys.code.common.entity.C2cRecordEntity;
import com.svv.jys.code.common.view.dialog.CommonTipsDialogFragment;
import com.svv.jys.code.common.view.dialog.LookC2CDialogFragment;


/**
 * 弹出框工具
 */

public class DialogUtil {
    private static String date;

    /* 标题，内容，确认按钮，取消按钮，按钮监听器 */
    public static void showCommonDialog(Activity activity, String title, String content, String cancel, String sure, CommonTipsDialogFragment.OnTipsListener onTipsListener) {
        CommonTipsDialogFragment dialogFragment = new CommonTipsDialogFragment();
        Bundle args = new Bundle();
        args.putString("tipsTitle", title);
        args.putString("tipsText", content);
        args.putString("tipsCancel", cancel);
        args.putString("tipsSucceed", sure);

        dialogFragment.setArguments(args);
        dialogFragment.setCancelable(false);
        dialogFragment.setOnConfirmListener(onTipsListener);
        dialogFragment.show(activity.getFragmentManager(), "");
    }

    public static void showLookDialog(Activity activity, String title, C2cRecordEntity C2cRecordEntity, LookC2CDialogFragment.OnTipsListener onTipsListener) {
        LookC2CDialogFragment dialogFragment = new LookC2CDialogFragment();
        Bundle args = new Bundle();
        args.putString("tipsTitle", title);
        args.putSerializable("C2cRecordEntity",  C2cRecordEntity);
        dialogFragment.setArguments(args);
        dialogFragment.setCancelable(false);
        dialogFragment.setOnConfirmListener(onTipsListener);
        dialogFragment.show(activity.getFragmentManager(), "");
    }
}
