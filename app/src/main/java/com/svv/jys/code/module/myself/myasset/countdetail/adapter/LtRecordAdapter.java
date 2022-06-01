package com.svv.jys.code.module.myself.myasset.countdetail.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.MBaseAdapter;
import com.svv.jys.code.common.entity.LtRecordEntity;
import com.svv.jys.code.common.utils.ToolUtils;

import java.util.List;

/**
 * Created by js on 2018/5/26.
 */

public class LtRecordAdapter extends MBaseAdapter<LtRecordAdapter.ViewHolder>{
    private List<LtRecordEntity.RowsBean> list;
    private Context context;
    public LtRecordAdapter(Context context, List<LtRecordEntity.RowsBean> list){
        this.context=context;
        this.list=list;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_trade_record,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        LtRecordEntity.RowsBean bean=list.get(position);
        holder.tv_record_time.setText(ToolUtils.timeStamp2String(bean.getAdd_time(),"yyyy-MM-dd HH:mm:ss"));
        holder.tv_record_name.setText(context.getString(R.string.myassetact_list_item1)+"："+bean.getCoin().toUpperCase());
        holder.tv_record_num.setText(context.getString(R.string.num)+"："+bean.getNum());
        holder.tv_record_type.setText(context.getString(R.string.countdetailact_item_type)+"："+bean.getOperation());
        holder.tv_record_mone.setText(bean.getMemo());
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_record_name,tv_record_num,tv_record_time,tv_record_type,tv_record_mone;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_record_name=itemView.findViewById(R.id.tv_record_name);
            tv_record_num=itemView.findViewById(R.id.tv_record_num);
            tv_record_time=itemView.findViewById(R.id.tv_record_time);
            tv_record_type=itemView.findViewById(R.id.tv_record_type);
            tv_record_mone=itemView.findViewById(R.id.tv_record_mone);
        }
    }
}
