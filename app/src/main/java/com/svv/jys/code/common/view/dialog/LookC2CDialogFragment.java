package com.svv.jys.code.common.view.dialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.entity.C2cRecordEntity;


/**
 * 显示系統提示的对话框，可以自行设置题目，内容，取消，成功
 */
public class LookC2CDialogFragment extends DialogFragment implements View.OnClickListener {

    private Dialog mDetailDialog;
    private TextView tv_c2c_title;
    private LinearLayout ll_c2c_cancel;

    private C2cRecordEntity mC2cRecordEntity;
    private TextView tv_c2c_item_sn, tv_c2c_item_all_money, tv_c2c_item_bank_user, tv_c2c_item_bank_cart, tv_c2c_item_bank_name;




    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mDetailDialog = new Dialog(getActivity(), R.style.ccdialog);
        mDetailDialog.setContentView(R.layout.dialog_look_c2c);
        mDetailDialog.setCancelable(true);

        tv_c2c_title = (TextView) mDetailDialog.findViewById(R.id.tv_c2c_title);
        ll_c2c_cancel = (LinearLayout) mDetailDialog.findViewById(R.id.ll_c2c_cancel);
        tv_c2c_item_bank_name = mDetailDialog.findViewById(R.id.tv_c2c_item_bank_name);
        tv_c2c_item_bank_cart = mDetailDialog.findViewById(R.id.tv_c2c_item_bank_cart);
        tv_c2c_item_bank_user = mDetailDialog.findViewById(R.id.tv_c2c_item_bank_user);
        tv_c2c_item_all_money = mDetailDialog.findViewById(R.id.tv_c2c_item_all_money);
        tv_c2c_item_sn = mDetailDialog.findViewById(R.id.tv_c2c_item_sn);
        mC2cRecordEntity= (C2cRecordEntity) getArguments().getSerializable("C2cRecordEntity");
        
        
        tv_c2c_title.setText(getArguments().getString("tipsTitle"));
       tv_c2c_item_bank_name.setText(mC2cRecordEntity.getBank_name());
       tv_c2c_item_bank_cart.setText(mC2cRecordEntity.getBank_no());
       tv_c2c_item_bank_user.setText(mC2cRecordEntity.getTrue_name());
       tv_c2c_item_all_money.setText(mC2cRecordEntity.getTotal());
       tv_c2c_item_sn.setText(mC2cRecordEntity.getOrder_sn());
        ll_c2c_cancel.setOnClickListener(this);

        return mDetailDialog;
    }

    private OnTipsListener onTipsListener;

    public void setOnConfirmListener(OnTipsListener onTipsListener) {
        this.onTipsListener = onTipsListener;
    }

    public interface OnTipsListener {
        void onCancel();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_c2c_cancel:
                mDetailDialog.dismiss();
                if (onTipsListener != null) {
                    onTipsListener.onCancel();
                }
                break;

            default:

                break;
        }
    }
}

