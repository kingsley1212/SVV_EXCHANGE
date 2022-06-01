package com.svv.jys.code.module.myself.usercenter.base.pay.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.MBaseAdapter;
import com.svv.jys.code.common.entity.PayMethodEntity;

import java.util.List;

/**
 * Created by js on 2018/6/16.
 */

public class PayAdapter extends MBaseAdapter<PayAdapter.ViewHolder> {
    private Context context;
    private List<PayMethodEntity.RowsBean> list;

    public PayAdapter(Context context, List<PayMethodEntity.RowsBean> list) {
        this.context = context;
        this.list = list;
    }

    public void setPayManenger(PayManenger payManenger) {
        this.payManenger = payManenger;
    }

    public void setdata(List<PayMethodEntity.RowsBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public PayManenger payManenger;

    public interface PayManenger {
        void Updatepay(int position, PayMethodEntity.RowsBean rowsBean);

        void delete(int position, View view, PayMethodEntity.RowsBean rowsBean);

        void setStatus(int position, PayMethodEntity.RowsBean rowsBean);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_pay_methed, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        PayMethodEntity.RowsBean bean = list.get(position);
        PayMethodEntity.MemoBean memoBean = bean.getMemo();
        holder.tv_payment_name.setText(memoBean.getRealname());
        holder.tv_pay_zhanghao.setText(memoBean.getAccount());
        if (bean.getPay_code().equals("bank")) {
            holder.rl_pay_bg.setBackgroundResource(R.mipmap.bank_bg);
            holder.iv_pay_logo.setImageResource(R.mipmap.ic_pay_bank);
        } else if (bean.getPay_code().equals("alipay")) {
            holder.rl_pay_bg.setBackgroundResource(R.mipmap.zfb_bg);
            holder.iv_pay_logo.setImageResource(R.mipmap.ic_pay_zfb);

        } else if (bean.getPay_code().equals("wechat")) {
            holder.rl_pay_bg.setBackgroundResource(R.mipmap.wx_bg);
            holder.iv_pay_logo.setImageResource(R.mipmap.ic_pay_wx);
        }
//        holder.tv_xiugai.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                payManenger.Updatepay(position,list.get(position));
//            }
//        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                payManenger.delete(position, view, list.get(position));
                return false;
            }
        });
        if (bean.getStatus().equals("1")) {
            holder.iv_pay_kaiguan.setImageResource(R.mipmap.ic_pay_kai_);
        } else {
            holder.iv_pay_kaiguan.setImageResource(R.mipmap.ic_pay_guan);
        }
        holder.iv_pay_kaiguan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                payManenger.setStatus(position, list.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_payment_name, tv_pay_zhanghao;
        ImageView iv_pay_logo, iv_pay_kaiguan;
        RelativeLayout rl_pay_bg;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_payment_name = itemView.findViewById(R.id.tv_payment_name);
            tv_pay_zhanghao = itemView.findViewById(R.id.tv_pay_zhanghao);
            iv_pay_logo = itemView.findViewById(R.id.iv_pay_logo);
            iv_pay_kaiguan = itemView.findViewById(R.id.iv_pay_kaiguan);
            rl_pay_bg = itemView.findViewById(R.id.rl_pay_bg);
        }
    }
}
