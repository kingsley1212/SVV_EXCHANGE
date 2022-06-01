package com.svv.jys.code.module.fabi.fragment.adapter;

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
import com.svv.jys.code.common.utils.GlideUtils;
import com.svv.jys.code.common.utils.ToolUtils;

import java.util.List;

/**
 * Created by js on 2018/8/29.
 */

public class BuyOrSellAdapter1 extends MBaseAdapter<BuyOrSellAdapter1.ViewHolder> {
    private Context context;
    private List<OtcAdvEntity.RowsBean> list;


    public void setOnClickLinster(OnClickLinsters onClickLinster) {
        this.onClickLinster = onClickLinster;
    }

    private OnClickLinsters onClickLinster;

    public interface OnClickLinsters {
        void toBuy(OtcAdvEntity.RowsBean rowsBean);

        void toQureyUser(OtcAdvEntity.RowsBean rowsBean);
    }

    public void setList(List<OtcAdvEntity.RowsBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void addList(List<OtcAdvEntity.RowsBean> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }


    public BuyOrSellAdapter1(Context context, List<OtcAdvEntity.RowsBean> list) {
        this.context = context;
        this.list = list;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_buy_sell1, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final OtcAdvEntity.RowsBean rowsBean = list.get(position);
        holder.tv_adv_name.setText(rowsBean.getNick_name());
        holder.tv_fabi_limit.setText(rowsBean.getMin_amount() + " -- " + rowsBean.getMax_amount());
        holder.tv_fabi_price.setText(rowsBean.getPrice());
        holder.tv_fabi_num.setText(rowsBean.getNum());
        OtcAdvEntity.RowsBean.OrderMsgBean orderMsgBean = rowsBean.getOrder_msg();
        GlideUtils.loadUserLogo(context, rowsBean.getLogo(), holder.iv_fabi_userImg);
        holder.tv_fabi_jiaoyi.setText(String.format(context.getString(R.string.yijiaoyi), orderMsgBean.getFinish()) + context.getString(R.string.chengjiaolv) + orderMsgBean.getFinish_rate() + "%");
        if (rowsBean.getPayment().contains("bank")) {
            holder.iv_adv_bank.setVisibility(View.VISIBLE);
        } else {
            holder.iv_adv_bank.setVisibility(View.GONE);
        }
        if (rowsBean.getPayment().contains("wechat")) {
            holder.iv_adv_wechat.setVisibility(View.VISIBLE);
        } else {
            holder.iv_adv_wechat.setVisibility(View.GONE);
        }
        if (rowsBean.getPayment().contains("alipay")) {
            holder.iv_adv_alipay.setVisibility(View.VISIBLE);
        } else {
            holder.iv_adv_alipay.setVisibility(View.GONE);
        }
        holder.iv_adv_cash.setVisibility(View.GONE);
        holder.tv_adv_buy.setText(context.getString(R.string.OtcBuyOrSellFrag_goumai));
        holder.tv_adv_buy.setSelected(true);
        holder.price_unit_tv.setText(rowsBean.getCurrency());
        /*if (type.equals("0")){

        }else {
            holder.tv_adv_buy.setText(context.getString(R.string.OtcBuyOrSellFrag_chushou));
            holder.tv_adv_buy.setSelected(false);
        }*/
        holder.tv_adv_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ToolUtils.isFastClick(view.getId())){
                    if (onClickLinster != null) {
                        onClickLinster.toBuy(rowsBean);
                    }

                }

            }
        });
        holder.tv_adv_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ToolUtils.isFastClick(view.getId())) {
                    if (onClickLinster != null) {
                        onClickLinster.toQureyUser(rowsBean);
                    }
                }
            }
        });
        holder.iv_fabi_userImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ToolUtils.isFastClick(v.getId())) {
                    if (onClickLinster != null) {
                        onClickLinster.toQureyUser(rowsBean);
                    }
                }
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ToolUtils.isFastClick(v.getId())){
                    onClickLinster.toBuy(rowsBean);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_adv_name, tv_fabi_price, tv_adv_buy, tv_fabi_num, tv_fabi_limit, tv_fabi_jiaoyi, price_unit_tv;
        ImageView iv_adv_bank, iv_adv_alipay, iv_adv_wechat, iv_adv_cash, iv_fabi_userImg;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_adv_name = itemView.findViewById(R.id.tv_adv_name);
            tv_fabi_price = itemView.findViewById(R.id.tv_fabi_price);
            tv_fabi_num = itemView.findViewById(R.id.tv_fabi_num);
            tv_fabi_limit = itemView.findViewById(R.id.tv_fabi_limit);
            tv_fabi_jiaoyi = itemView.findViewById(R.id.tv_fabi_jiaoyi);
            iv_fabi_userImg = itemView.findViewById(R.id.iv_fabi_userImg);
            tv_adv_buy = itemView.findViewById(R.id.tv_adv_buy);
            iv_adv_bank = itemView.findViewById(R.id.iv_adv_bank);
            iv_adv_alipay = itemView.findViewById(R.id.iv_adv_alipay);
            iv_adv_wechat = itemView.findViewById(R.id.iv_adv_wechat);
            iv_adv_cash = itemView.findViewById(R.id.iv_adv_cash);
            price_unit_tv = itemView.findViewById(R.id.price_unit_tv);
        }
    }
}

