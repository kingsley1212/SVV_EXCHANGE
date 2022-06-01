package com.svv.jys.code.module.myself.myasset.otc.otctrading.fragment.userinfo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.MBaseAdapter;
import com.svv.jys.code.common.entity.BusinessInfoEntity;
import com.svv.jys.code.common.utils.GlideUtils;
import com.svv.jys.code.common.utils.ToolUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by js on 2018/6/20.
 */

public class BusinessInfoAdapter extends MBaseAdapter<BusinessInfoAdapter.ViewHolder>{
    private Context context;
    private List<BusinessInfoEntity.RowsBean> list;
    private int type;
    private boolean isAdvUser;
    public BusinessInfoAdapter(Context context,int type,boolean isAdvUser){
        this.context=context;
        this.type=type;
        this.list = new ArrayList<>();
        this.isAdvUser=isAdvUser;
    }

    public void setList(List<BusinessInfoEntity.RowsBean> list) {
        this.list = list;
    }

    public void addList(List<BusinessInfoEntity.RowsBean> list) {
        this.list.addAll(list);
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_business_adv,parent,false));
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        BusinessInfoEntity.RowsBean bean=list.get(position);
        GlideUtils.loadUserLogo(context,bean.getLogo(),holder.coin_logo);
        holder.coin_name_tv.setText(bean.getCoin_name());
        holder.limit_tv.setText(String.format("%s  %s--%s", context.getString(R.string.UserInfoAct_xiane), bean.getMin_amount(), bean.getMax_amount()));
        holder.coin_num_tv.setText(String.format("%s  %s%s", context.getString(R.string.num), bean.getNum(), bean.getCoin_name()));
        holder.price_tv.setText(bean.getPrice());
        holder.currency_tv.setText(String.format("(%s)",bean.getCurrency()));
//        holder.tv_limit.setText(context.getString(R.string.UserInfoAct_xiane)+bean.getMin_amount()+"--"+bean.getMax_amount());
        holder.tv_adv.setText(bean.getPrice()+bean.getCurrency()+"/"+bean.getCoin_name());
        if (isAdvUser){
            holder.tv_buy.setVisibility(View.GONE);
        }
        if (type==0){
            holder.tv_buy.setText(context.getString(R.string.UserInfoAct_chushou));
        }else {
            holder.tv_buy.setText(context.getString(R.string.UserInfoAct_goumai));
        }
        if (bean.getPayment().contains("bank")){
            holder.iv_bank.setVisibility(View.VISIBLE);
        }else {
            holder.iv_bank.setVisibility(View.GONE);
        }
        if (bean.getPayment().contains("wechat")){
            holder.iv_wechat.setVisibility(View.VISIBLE);
        }else {
            holder.iv_wechat.setVisibility(View.GONE);
        }
        if (bean.getPayment().contains("alipay")){
            holder.iv_alipay.setVisibility(View.VISIBLE);
        }else {
            holder.iv_alipay.setVisibility(View.GONE);
        }
        if (bean.getPayment().contains("cash")){
            holder.iv_cash.setVisibility(View.VISIBLE);
        }else {
            holder.iv_cash.setVisibility(View.GONE);
        }
        holder.tv_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ToolUtils.isFastClick(view.getId())){
                    onItemClick.onItemClick(position,list.get(position));
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_buy,tv_adv,tv_limit,coin_name_tv,limit_tv,coin_num_tv,price_tv,currency_tv;
        ImageView iv_bank,iv_alipay,iv_wechat,iv_cash,coin_logo;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_buy=itemView.findViewById(R.id.tv_buy);
            tv_adv=itemView.findViewById(R.id.tv_adv);
            tv_limit=itemView.findViewById(R.id.tv_limit);
            iv_bank=itemView.findViewById(R.id.iv_bank);
            iv_alipay=itemView.findViewById(R.id.iv_alipay);
            iv_wechat=itemView.findViewById(R.id.iv_wechat);
            iv_cash=itemView.findViewById(R.id.iv_cash);
            coin_logo = itemView.findViewById(R.id.coin_logo);
            coin_name_tv = itemView.findViewById(R.id.coin_name_tv);
            limit_tv = itemView.findViewById(R.id.limit_tv);
            coin_num_tv = itemView.findViewById(R.id.coin_num_tv);
            price_tv = itemView.findViewById(R.id.price_tv);
            currency_tv = itemView.findViewById(R.id.currency_tv);
        }
    }
}
