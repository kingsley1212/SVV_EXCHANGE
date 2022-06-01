package com.svv.jys.code.module.myself.welfarerecord.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.MBaseAdapter;
import com.svv.jys.code.common.entity.WelfareRecordEntity;

import java.util.List;

public class WelfareRecordAdapter extends MBaseAdapter<WelfareRecordAdapter.ViewHolder> {
    private Context context;
    private List<WelfareRecordEntity.RowsBean> list;
    public WelfareRecordAdapter(Context context,List<WelfareRecordEntity.RowsBean> list){
        this.context=context;
        this.list=list;
    }
    public void setList(List<WelfareRecordEntity.RowsBean> list) {
        this.list = list;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_welfare_record,parent,false));
    }

    public void setOnLingquListener(OnLingquListener onLingquListener) {
        this.onLingquListener = onLingquListener;
    }

    OnLingquListener onLingquListener;
    public interface OnLingquListener{
        void onLingqu(int pos,WelfareRecordEntity.RowsBean rowsBean);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final WelfareRecordEntity.RowsBean rowsBean=list.get(position);
        holder.tv_wel_record_name.setText(rowsBean.getName().toUpperCase());
        holder.tv_wel_record_stasus.setText(context.getString(R.string.welfare_record_welfare)+rowsBean.getStatus_name());
        holder.tv_wel_record_chiyou_coin.setText(context.getString(R.string.welfare_record_hold_coin)+rowsBean.getHold_coin().toUpperCase());
        holder.tv_wel_record_jiangli_coin.setText(context.getString(R.string.welfare_record_award_coin)+rowsBean.getGain_coin().toUpperCase());
        holder.tv_wel_record_chiyou_num.setText(context.getString(R.string.welfare_record_hold_count)+rowsBean.getHold_num());
        holder.tv_wel_record_jiangli_num.setText(context.getString(R.string.welfare_record_award_count)+rowsBean.getGain_num());
        holder.tv_wel_record_time.setText(context.getString(R.string.welfare_record_draw_time)+rowsBean.getReward_time());
        if (rowsBean.getStatus().equals("1")){
            holder.tv_status.setText(context.getString(R.string.welfare_record_get));
            holder.tv_status.setBackgroundResource(R.drawable.shape_welfare_lingqu);
            holder.tv_status.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onLingquListener!=null){
                        onLingquListener.onLingqu(position,rowsBean);
                    }
                }
            });
        }else {
            holder.tv_status.setBackgroundResource(R.drawable.shape_welfare_unlingqu);
            holder.tv_status.setText(rowsBean.getStatus_name());
        }

    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_wel_record_name,tv_wel_record_stasus,tv_wel_record_chiyou_coin,tv_wel_record_jiangli_coin
                ,tv_wel_record_chiyou_num,tv_wel_record_jiangli_num,tv_wel_record_time,tv_status;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_wel_record_name=itemView.findViewById(R.id.tv_wel_record_name);
            tv_wel_record_stasus=itemView.findViewById(R.id.tv_wel_record_stasus);
            tv_wel_record_chiyou_coin=itemView.findViewById(R.id.tv_wel_record_chiyou_coin);
            tv_wel_record_jiangli_coin=itemView.findViewById(R.id.tv_wel_record_jiangli_coin);
            tv_wel_record_chiyou_num=itemView.findViewById(R.id.tv_wel_record_chiyou_num);
            tv_wel_record_jiangli_num=itemView.findViewById(R.id.tv_wel_record_jiangli_num);
            tv_wel_record_time=itemView.findViewById(R.id.tv_wel_record_time);
            tv_status=itemView.findViewById(R.id.tv_status);
        }
    }
}
