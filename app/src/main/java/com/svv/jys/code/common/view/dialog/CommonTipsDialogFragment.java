package com.svv.jys.code.common.view.dialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.svv.jys.R;


/**
 * 显示系統提示的对话框，可以自行设置题目，内容，取消，成功
 */
public class CommonTipsDialogFragment extends DialogFragment implements View.OnClickListener {

    private Dialog mDetailDialog;
    private TextView tipsTitle;
    private TextView tipsText;
    private TextView tipsCancel;
    private TextView tipsSucceed;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mDetailDialog = new Dialog(getActivity(), R.style.ccdialog);
        mDetailDialog.setContentView(R.layout.dialog_common_editor);
        mDetailDialog.setCancelable(true);

        tipsTitle = (TextView) mDetailDialog.findViewById(R.id.tips_title);
        tipsText = (TextView) mDetailDialog.findViewById(R.id.tips_text);
        tipsCancel = (TextView) mDetailDialog.findViewById(R.id.tips_cancel);
        tipsSucceed = (TextView) mDetailDialog.findViewById(R.id.tips_succeed);

        tipsTitle.setText(getArguments().getString("tipsTitle"));
        tipsText.setText(getArguments().getString("tipsText"));
        tipsCancel.setText(getArguments().getString("tipsCancel"));
        tipsSucceed.setText(getArguments().getString("tipsSucceed"));

        tipsCancel.setOnClickListener(this);
        tipsSucceed.setOnClickListener(this);

        return mDetailDialog;
    }

    private OnTipsListener onTipsListener;

    public void setOnConfirmListener(OnTipsListener onTipsListener) {
        this.onTipsListener = onTipsListener;
    }

    public interface OnTipsListener {
        void onCancel();

        void onSucceed();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tips_cancel:
                mDetailDialog.dismiss();
                if (onTipsListener != null) {
                    onTipsListener.onCancel();
                }
                break;

            case R.id.tips_succeed:
                mDetailDialog.dismiss();
                if (onTipsListener != null) {
                    onTipsListener.onSucceed();
                }
                break;

            default:

                break;
        }
    }
}

