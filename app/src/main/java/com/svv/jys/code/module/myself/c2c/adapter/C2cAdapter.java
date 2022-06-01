package com.svv.jys.code.module.myself.c2c.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.MBaseAdapter;
import com.svv.jys.code.common.entity.C2CListEntity;
import com.svv.jys.code.common.view.popup.PopupDialogView;
import com.svv.jys.code.module.myself.c2c.C2cAct;

import java.util.ArrayList;
import java.util.List;

public class C2cAdapter extends MBaseAdapter<C2cAdapter.ViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    private List<C2CListEntity.RowsBean> list;

    public C2cAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        list = new ArrayList<>();
    }

    public void setList(List<C2CListEntity.RowsBean> rows){
        this.list = rows;
    }

    public void addList(List<C2CListEntity.RowsBean> rows){
        this.list.addAll(rows);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_c2c_tranfer,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final C2CListEntity.RowsBean entity = list.get(position);
        holder.status_tv.setText(entity.getStatus_name());
        switch (entity.getStatus()){
            case "0":
                holder.status_tv.setTextColor(Color.parseColor("#21d08a"));
                holder.cx_tv.setVisibility(View.VISIBLE);
                break;
            case "1":
                holder.status_tv.setTextColor(Color.parseColor("#2183c9"));
                holder.cx_tv.setVisibility(View.GONE);
                break;
            case "2":
            case "3":
                holder.cx_tv.setVisibility(View.GONE);
                holder.status_tv.setTextColor(Color.parseColor("#ff5755"));
                break;
        }

        holder.coin_tv.setText(entity.getCoin().toUpperCase());
        if(entity.getType().equals("1")) {
            holder.type_tv.setBackground(context.getResources().getDrawable(R.drawable.btn_sell_in));
            holder.type_tv.setText(context.getString(R.string.tradechat_buy));
            holder.num_tv.setSelected(false);
            holder.price_tv.setSelected(false);
        }else {
            holder.type_tv.setBackground(context.getResources().getDrawable(R.drawable.btn_sell_out));
            holder.type_tv.setText(context.getString(R.string.tradechat_sell));
            holder.num_tv.setSelected(true);
            holder.price_tv.setSelected(true);
        }
        holder.time_tv.setText(entity.getAdd_time());
        holder.num_tv.setText(entity.getNum());
        holder.price_tv.setText(entity.getPrice());
        holder.tv_bank_name.setText(entity.getBank_name());
        holder.bank_code_tv.setText(entity.getBank_no());
        holder.tv_user_name.setText(entity.getTrue_name());
        holder.tv_total.setText(entity.getTotal());
        holder.number_tv.setText(entity.getOrder_sn());
        holder.cx_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupDialogView popupView = new PopupDialogView(context, new PopupDialogView.MClickLisnener() {
                    @Override
                    public void leftBtn() {

                    }

                    @Override
                    public void rightBtn() {
                        ((C2cAct)context).doCancel(entity.getId());
                    }
                });
                popupView.showPop(view, context.getString(R.string.entrust_chexiao), context.getString(R.string.c2c_cx), context.getString
                        (R.string
                                .myselffragment_left_text), context.getString(R.string.myselffragment_right_text));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView status_tv,cx_tv,coin_tv,type_tv,time_tv,num_tv;
        private TextView price_tv,tv_bank_name,bank_code_tv,tv_user_name,tv_total,number_tv;
        public ViewHolder(View itemView) {
            super(itemView);
            status_tv = itemView.findViewById(R.id.status_tv);
            cx_tv = itemView.findViewById(R.id.cx_tv);
            coin_tv = itemView.findViewById(R.id.coin_tv);
            type_tv = itemView.findViewById(R.id.type_tv);
            time_tv = itemView.findViewById(R.id.time_tv);
            num_tv = itemView.findViewById(R.id.num_tv);
            price_tv = itemView.findViewById(R.id.price_tv);
            tv_bank_name = itemView.findViewById(R.id.tv_bank_name);
            bank_code_tv = itemView.findViewById(R.id.bank_code_tv);
            tv_user_name = itemView.findViewById(R.id.tv_user_name);
            tv_total = itemView.findViewById(R.id.tv_total);
            number_tv = itemView.findViewById(R.id.number_tv);
        }
    }
}
