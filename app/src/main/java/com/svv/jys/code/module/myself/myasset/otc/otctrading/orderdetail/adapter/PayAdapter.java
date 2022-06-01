package com.svv.jys.code.module.myself.myasset.otc.otctrading.orderdetail.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.MBaseAdapter;
import com.svv.jys.code.common.entity.OrderDetailEntity;
import com.svv.jys.code.module.myself.myasset.otc.otctrading.orderdetail.OrderDetailAct;

import java.util.List;

/**
 * Created by js on 2018/6/12.
 */

public class PayAdapter extends MBaseAdapter<PayAdapter.ViewHolder> {
    private Context context;
    private List<OrderDetailEntity.PaymentBean> list;
    private int s_position;

    public PayAdapter(Context context, List<OrderDetailEntity.PaymentBean> list) {
        this.context = context;
        this.list = list;
        this.list.get(0).setSelector(true);
        s_position = 0;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.view_payment_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final OrderDetailEntity.PaymentBean paymentBean = list.get(position);
        switch (paymentBean.getPay_code()) {
            case "alipay":
                holder.pay_logo_iv.setImageDrawable(context.getResources().getDrawable(R.mipmap.pay_zhifubao_icon));
                holder.pay_name_tv.setText(context.getString(R.string.OtcBuyOrSellFrag_zfb));
                break;
            case "bank":
                holder.pay_logo_iv.setImageDrawable(context.getResources().getDrawable(R.mipmap.pay_unionpay_icon));
                holder.pay_name_tv.setText(context.getString(R.string.OtcBuyOrSellFrag_bank_zhuanz));
                break;
            case "wechat":
                holder.pay_logo_iv.setImageDrawable(context.getResources().getDrawable(R.mipmap.pay_weichat_pay));
                holder.pay_name_tv.setText(context.getString(R.string.OtcBuyOrSellFrag_vchact));
                break;
        }
        holder.bank_selector.setSelected(paymentBean.isSelector());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.get(s_position).setSelector(false);
                list.get(position).setSelector(true);
                s_position = position;
                ((OrderDetailAct)context).updataPayment(paymentBean);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView pay_logo_iv, bank_selector;
        private TextView pay_name_tv;

        public ViewHolder(View itemView) {
            super(itemView);
            pay_logo_iv = itemView.findViewById(R.id.pay_logo_iv);
            bank_selector = itemView.findViewById(R.id.bank_selector);
            pay_name_tv = itemView.findViewById(R.id.pay_name_tv);
        }
    }
}
