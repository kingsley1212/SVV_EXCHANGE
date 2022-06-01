package com.svv.jys.code.module.myself.myasset.jiaoyi.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.MBaseAdapter;
import com.svv.jys.code.common.entity.WalletDataEntity;

import java.util.ArrayList;
import java.util.List;

public class TradingAccountAdapter extends MBaseAdapter<TradingAccountAdapter.ViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    private List<WalletDataEntity.RowsBean> list = new ArrayList<>();

    public TradingAccountAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    public void setList(List<WalletDataEntity.RowsBean> list){
        this.list = list;
    }

    public void addList(List<WalletDataEntity.RowsBean> list){
        this.list.addAll(list);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_coin_info,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        WalletDataEntity.RowsBean rowsBean = list.get(position);
        holder.time_tv.setText(rowsBean.getAdd_time());
        if(rowsBean.getNum().contains("-")) {
            holder.change_num.setText(rowsBean.getNum());
            holder.change_num.setTextColor(Color.parseColor("#ff5755"));
        }else {
            holder.change_num.setText(String.format("+%s", rowsBean.getNum()));
            holder.change_num.setTextColor(Color.parseColor("#1ede89"));
        }
//        if (rowsBean.getOperation_type()==15){
//            holder.ll_status.setVisibility(View.VISIBLE);
//            if (rowsBean.getStatus()==0){
//                holder.tv_status.setText(R.string.audit);
//                holder.tv_status.setTextColor(context.getResources().getColor(R.color.c_1ede89));
//            }else if (rowsBean.getStatus()==1){
//                holder.tv_status.setText(R.string.traverse);
//                holder.tv_status.setTextColor(context.getResources().getColor(R.color.c_1a456d));
//            }else if (rowsBean.getStatus()==2){
//                holder.tv_status.setText(R.string.refuse);
//                holder.tv_status.setTextColor(context.getResources().getColor(R.color.c_ff5755));
//            }
//        }else {
//            holder.ll_status.setVisibility(View.GONE);
//        }

        holder.type_tv.setText(rowsBean.getOperation());
        holder.memo_tv.setText(rowsBean.getMemo());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private LinearLayout ll_status;
        TextView time_tv,memo_tv,change_num,type_tv,tv_status;
        public ViewHolder(View itemView) {
            super(itemView);
            time_tv = itemView.findViewById(R.id.time_tv);
            memo_tv = itemView.findViewById(R.id.memo_tv);
            change_num = itemView.findViewById(R.id.change_num);
            type_tv = itemView.findViewById(R.id.type_tv);
            tv_status = itemView.findViewById(R.id.tv_status);
            ll_status=itemView.findViewById(R.id.ll_status);
        }
    }
}
