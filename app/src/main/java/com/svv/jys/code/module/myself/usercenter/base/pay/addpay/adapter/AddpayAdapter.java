package com.svv.jys.code.module.myself.usercenter.base.pay.addpay.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.MBaseAdapter;
import com.svv.jys.code.common.entity.OtcPayEntity;

import java.util.List;

public class AddpayAdapter extends MBaseAdapter<AddpayAdapter.ViewHolder> {
    private Context context;
    private List<OtcPayEntity.RowsBean> list;
    public AddpayAdapter(Context context,List<OtcPayEntity.RowsBean> list){
        this.context=context;
        this.list=list;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_addpay,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
//        holder.tv_pay_name.setText(list.get(position).getName());
        if (list.get(position).getCode().equals("bank")){
            holder.tv_pay_name.setText(context.getResources().getString(R.string.AddPayAct_bank_zz));

            holder.iv_pay_logo.setImageResource(R.mipmap.pay_unionpay_icon);
        }else if (list.get(position).getCode().equals("alipay")){
            holder.tv_pay_name.setText(context.getResources().getString(R.string.AddPayAct_alpaly));
            holder.iv_pay_logo.setImageResource(R.mipmap.pay_zhifubao_icon);
        }else if (list.get(position).getCode().equals("wechat")){
            holder.tv_pay_name.setText(context.getResources().getString(R.string.AddPayAct_vchact_paly));
            holder.iv_pay_logo.setImageResource(R.mipmap.pay_weichat_pay);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.onItemClick(position,list.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView iv_pay_logo;
        private TextView tv_pay_name;
        public ViewHolder(View itemView) {
            super(itemView);
            iv_pay_logo=itemView.findViewById(R.id.iv_pay_logo);
            tv_pay_name=itemView.findViewById(R.id.tv_pay_name);
        }
    }
}
