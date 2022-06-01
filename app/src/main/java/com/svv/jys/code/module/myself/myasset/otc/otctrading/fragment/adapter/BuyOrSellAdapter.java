package com.svv.jys.code.module.myself.myasset.otc.otctrading.fragment.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.MBaseAdapter;
import com.svv.jys.code.common.entity.OtcAdvEntity;

import java.util.List;

/**
 * Created by js on 2018/6/8.
 */

public class BuyOrSellAdapter extends MBaseAdapter<BuyOrSellAdapter.ViewHolder>{
    private Context context;
    private List<OtcAdvEntity.RowsBean> list;
    private String type;


    public void setOnClickLinster(OnClickLinsters onClickLinster) {
        this.onClickLinster = onClickLinster;
    }

    private OnClickLinsters onClickLinster;
    public interface OnClickLinsters{
        void toBuy(int position);
        void toQureyUser(int position);
    }

    public void setList(List<OtcAdvEntity.RowsBean> list){
        this.list=list;
        notifyDataSetChanged();
    }

    public BuyOrSellAdapter(Context context,List<OtcAdvEntity.RowsBean> list,String type){
        this.context=context;
        this.list=list;
        this.type=type;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_buy_sell,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        OtcAdvEntity.RowsBean rowsBean=list.get(position);
        holder.tv_adv_name.setText(rowsBean.getTrue_name());
        holder.tv_adv_limit.setText(context.getString(R.string.OtcBuyOrSellFrag_xiane)+rowsBean.getMin_amount()+" -- "+rowsBean.getMax_amount());
        holder.tv_adv_unit.setText(rowsBean.getPrice()+rowsBean.getCurrency());
        holder.tv_adv_num.setText(context.getString(R.string.OtcBuyOrSellFrag_number)+rowsBean.getNum());
        OtcAdvEntity.RowsBean.OrderMsgBean orderMsgBean= rowsBean.getOrder_msg();
        holder.tv_adv_evaluation.setText(orderMsgBean.getFinish_rate()+"%");
        holder.tv_adv_volume.setText(orderMsgBean.getThree());
        if (rowsBean.getPayment().contains("bank")){
            holder.iv_adv_bank.setVisibility(View.VISIBLE);
        }else {
            holder.iv_adv_bank.setVisibility(View.GONE);
        }
        if (rowsBean.getPayment().contains("wechat")){
            holder.iv_adv_wechat.setVisibility(View.VISIBLE);
        }else {
            holder.iv_adv_wechat.setVisibility(View.GONE);
        }
        if (rowsBean.getPayment().contains("alipay")){
            holder.iv_adv_alipay.setVisibility(View.VISIBLE);
        }else {
            holder.iv_adv_alipay.setVisibility(View.GONE);
        }
        if (rowsBean.getPayment().contains("cash")){
            holder.iv_adv_cash.setVisibility(View.VISIBLE);
        }else {
            holder.iv_adv_cash.setVisibility(View.GONE);
        }
        if (type.equals("0")){
            holder.tv_adv_buy.setText(context.getString(R.string.OtcBuyOrSellFrag_chushou));
        }else {
            holder.tv_adv_buy.setText(context.getString(R.string.OtcBuyOrSellFrag_goumai));

        }
        holder.tv_adv_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onClickLinster!=null){
                    onClickLinster.toBuy(position);
                }

            }
        });
        holder.tv_adv_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onClickLinster!=null){
                    onClickLinster.toQureyUser(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_adv_name,tv_adv_volume,tv_adv_evaluation,tv_adv_num,tv_adv_limit,tv_adv_unit,tv_adv_buy;
        ImageView iv_adv_bank,iv_adv_alipay,iv_adv_wechat,iv_adv_cash;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_adv_name=itemView.findViewById(R.id.tv_adv_name);
            tv_adv_volume=itemView.findViewById(R.id.tv_adv_volume);
            tv_adv_evaluation=itemView.findViewById(R.id.tv_adv_evaluation);
            tv_adv_num=itemView.findViewById(R.id.tv_adv_num);
            tv_adv_limit=itemView.findViewById(R.id.tv_adv_limit);
            tv_adv_unit=itemView.findViewById(R.id.tv_adv_unit);
            tv_adv_buy=itemView.findViewById(R.id.tv_adv_buy);
            iv_adv_bank=itemView.findViewById(R.id.iv_adv_bank);
            iv_adv_alipay=itemView.findViewById(R.id.iv_adv_alipay);
            iv_adv_wechat=itemView.findViewById(R.id.iv_adv_wechat);
            iv_adv_cash=itemView.findViewById(R.id.iv_adv_cash);
        }
    }
}
