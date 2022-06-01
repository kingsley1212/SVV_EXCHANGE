package com.svv.jys.code.module.myself.myasset.jiaoyi.chargerecord.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.MBaseAdapter;
import com.svv.jys.code.common.entity.MentionRecordEntity;

import java.util.List;

public class ChargeRecordAdapter extends MBaseAdapter<ChargeRecordAdapter.ViewHolder> {
    private Context context;
    private List<MentionRecordEntity.RowsBean> list;
    public ChargeRecordAdapter(Context context, List<MentionRecordEntity.RowsBean> list){
        this.list=list;
        this.context=context;
    }
    public void setList(List<MentionRecordEntity.RowsBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_mentionrecor,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final MentionRecordEntity.RowsBean rowsBean=list.get(position);
        holder.tv_mentionrecord_num.setText(rowsBean.getNum());
        holder.tv_mentionrecord_sxf.setText(rowsBean.getFee());
        holder.tv_mentionrecord_address.setText(rowsBean.getAddress());
        holder.cancel_tv.setVisibility(View.GONE);
        switch (rowsBean.getStatus()){
            case "0":
                holder.tv_mentionrecord_status.setTextColor(Color.parseColor("#ff9a1f"));
                break;
            case "1":
                holder.tv_mentionrecord_status.setTextColor(Color.parseColor("#1ed189"));
                break;
            case "2":
                holder.tv_mentionrecord_status.setTextColor(Color.parseColor("#ff5755"));
                break;
            case "3":
                holder.tv_mentionrecord_status.setTextColor(Color.parseColor("#999999"));
                break;
        }
        holder.tv_mentionrecord_time.setText(rowsBean.getAdd_time());
        holder.tv_mentionrecord_status.setText(rowsBean.getStatus_name());

    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_mentionrecord_num,tv_mentionrecord_sxf,tv_mentionrecord_address,tv_mentionrecord_time,tv_mentionrecord_status,cancel_tv;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_mentionrecord_num=itemView.findViewById(R.id.tv_mentionrecord_num);
            tv_mentionrecord_sxf=itemView.findViewById(R.id.tv_mentionrecord_sxf);
            tv_mentionrecord_address=itemView.findViewById(R.id.tv_mentionrecord_address);
            tv_mentionrecord_time=itemView.findViewById(R.id.tv_mentionrecord_time);
            tv_mentionrecord_status=itemView.findViewById(R.id.tv_mentionrecord_status);
            cancel_tv = itemView.findViewById(R.id.cancel_tv);
        }
    }
}
