package com.svv.jys.code.module.myself.myasset.jiaoyi.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.MBaseAdapter;
import com.svv.jys.code.common.entity.ChargeRecordEntity;
import com.svv.jys.code.common.utils.ToolUtils;

import java.util.List;

/**
 * Created by js on 2018/5/28.
 */

public class ChargeRecordAdapter extends MBaseAdapter<ChargeRecordAdapter.ViewHolder>{
    private Context context;
    private List<ChargeRecordEntity.RowsBean> list;
    public ChargeRecordAdapter(Context context, List<ChargeRecordEntity.RowsBean> list){
        this.context=context;
        this.list=list;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_mentiion_record,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ChargeRecordEntity.RowsBean entity=list.get(position);
        holder.tv_mention_record_num.setText(context.getString(R.string.num)+"："+entity.getNum());
        holder.tv_mention_record_actual.setText(context.getString(R.string.assat20)+"："+entity.getTotal());
        holder.tv_mention_record_sxf.setText(context.getString(R.string.assat21)+"："+entity.getFee());
        holder.tv_mention_record_time.setText(ToolUtils.timeStamp2String(entity.getAdd_time(),"yyyy-MM-dd HH:mm:ss"));
        holder.tv_mention_record_type.setText(context.getString(R.string.assat22)+"："+entity.getStatus());
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_mention_record_num,tv_mention_record_time,tv_mention_record_sxf,tv_mention_record_type,tv_mention_record_actual;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_mention_record_num=itemView.findViewById(R.id.tv_mention_record_num);
            tv_mention_record_time=itemView.findViewById(R.id.tv_mention_record_time);
            tv_mention_record_sxf=itemView.findViewById(R.id.tv_mention_record_sxf);
            tv_mention_record_type=itemView.findViewById(R.id.tv_mention_record_type);
            tv_mention_record_actual=itemView.findViewById(R.id.tv_mention_record_actual);
        }
    }
}
