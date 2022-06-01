package com.svv.jys.code.module.myself.subscription.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.MBaseAdapter;
import com.svv.jys.code.common.entity.SubListEntity;
import com.svv.jys.code.common.utils.ToolUtils;

import java.util.ArrayList;
import java.util.List;

public class SubAdapter extends MBaseAdapter<SubAdapter.ViewHolder> {
    private List<SubListEntity.RowsBean> list;
    private Context context;
    public SubAdapter(Context context){
        this.context=context;
        list=new ArrayList<>();
    }

    public void setList(List<SubListEntity.RowsBean> rows){
        this.list = rows;
    }

    public void addList(List<SubListEntity.RowsBean> rows){
        this.list.addAll(rows);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_subscription,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SubListEntity.RowsBean rowsBean=list.get(position);
        if (rowsBean.getType().equals("1")){
            holder.type_tv.setText(context.getResources().getString(R.string.subscription));
            holder.type_tv.setBackground(context.getResources().getDrawable(R.drawable.btn_sell_in));
        }else {
            holder.type_tv.setText(context.getResources().getString(R.string.placement));
            holder.type_tv.setBackground(context.getResources().getDrawable(R.drawable.btn_sell_out));
        }
        holder.coin_tv.setText(rowsBean.getCoin().toUpperCase());
        holder.num_tv.setText(rowsBean.getNum());
        holder.price_tv.setText(rowsBean.getRate());
        holder.tv_total.setText(rowsBean.getSell_coin_num()+"USDT");
        holder.time_tv.setText(ToolUtils.timeStamp2String(rowsBean.getAdd_time(),"yyyy-MM-dd HH:mm:ss"));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView type_tv,coin_tv,num_tv,price_tv,time_tv,tv_total;
        public ViewHolder(View itemView) {
            super(itemView);
            type_tv=itemView.findViewById(R.id.type_tv);
            coin_tv=itemView.findViewById(R.id.coin_tv);
            num_tv=itemView.findViewById(R.id.num_tv);
            price_tv=itemView.findViewById(R.id.price_tv);
            time_tv=itemView.findViewById(R.id.time_tv);
            tv_total=itemView.findViewById(R.id.tv_total);
        }
    }
}
