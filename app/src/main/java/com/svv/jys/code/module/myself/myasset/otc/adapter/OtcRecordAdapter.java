package com.svv.jys.code.module.myself.myasset.otc.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.MBaseAdapter;
import com.svv.jys.code.common.entity.OtcRecordEntity;
import com.svv.jys.code.common.utils.ToolUtils;

import java.util.List;

/**
 * Created by js on 2018/5/26.
 */

public class OtcRecordAdapter extends MBaseAdapter<OtcRecordAdapter.ViewHolder>{
    private List<OtcRecordEntity.RowsBean> list;
    private Context context;
    public OtcRecordAdapter(Context context, List<OtcRecordEntity.RowsBean> list){
        this.context=context;
        this.list=list;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_account_record,parent,false));
    }
    public void setData(List<OtcRecordEntity.RowsBean> list){
        this.list=list;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        OtcRecordEntity.RowsBean bean=list.get(position);
        holder.tv_record_time.setText(ToolUtils.timeStamp2String(bean.getAdd_time(),"yyyy-MM-dd HH:mm:ss"));
        holder.tv_record_num.setText(context.getString(R.string.num)+"："+bean.getNum());
        holder.tv_record_type.setText(context.getString(R.string.countdetailact_item_type)+"："+bean.getOperation());
        holder.tv_record_mone.setText(bean.getMemo());
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_record_num,tv_record_time,tv_record_type,tv_record_mone;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_record_num=itemView.findViewById(R.id.tv_record_num);
            tv_record_time=itemView.findViewById(R.id.tv_record_time);
            tv_record_type=itemView.findViewById(R.id.tv_record_type);
            tv_record_mone=itemView.findViewById(R.id.tv_record_mone);
        }
    }
}
