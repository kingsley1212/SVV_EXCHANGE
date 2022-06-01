package com.svv.jys.code.module.myself.myasset.jiaoyi.suocangrecord.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.MBaseAdapter;
import com.svv.jys.code.common.entity.CunFangRecordEntity;
import com.svv.jys.code.common.utils.ToolUtils;

import java.util.List;

/**
 * Created by js on 2018/8/11.
 */

public class SuoFangRecordAdapter extends MBaseAdapter<SuoFangRecordAdapter.ViewHolder> {
    private Context context;
    private List<CunFangRecordEntity.RowsBean> list;
    public SuoFangRecordAdapter(Context context, List<CunFangRecordEntity.RowsBean> list){
        this.context=context;
        this.list=list;
    }
    public void setList(List<CunFangRecordEntity.RowsBean> list){
        this.list=list;
        notifyDataSetChanged();
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_cunfang_record,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CunFangRecordEntity.RowsBean bean=list.get(position);
        holder.tv_cf_record_coin.setText(bean.getCoin().toUpperCase());
        holder.tv_cf_record_num.setText(bean.getNum());
        holder.tv_cf_record_yg.setText(bean.getGain_num());
        holder.tv_cf_record_yf.setText(bean.getDeal_num());
        holder.tv_cf_record_cun_time.setText(ToolUtils.timeStamp2String(bean.getAdd_time(),"yyyy-MM-dd"));
        holder.tv_cf_record_zhouqi.setText(bean.getRevolution());
        holder.tv_cf_record_fan_time.setText(ToolUtils.timeStamp2String(bean.getEnd_time(),"yyyy-MM-dd"));
        holder.tv_cf_record_status.setText(bean.getStatus_name());
        holder.tv_sy_record_coin.setText(bean.getR_coin().toUpperCase());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_cf_record_coin,tv_cf_record_num,tv_cf_record_yg,tv_cf_record_yf,tv_cf_record_cun_time,tv_cf_record_zhouqi,tv_cf_record_fan_time,tv_cf_record_status,tv_sy_record_coin;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_cf_record_coin=itemView.findViewById(R.id.tv_cf_record_coin);
            tv_cf_record_num=itemView.findViewById(R.id.tv_cf_record_num);
            tv_cf_record_yg=itemView.findViewById(R.id.tv_cf_record_yg);
            tv_cf_record_yf=itemView.findViewById(R.id.tv_cf_record_yf);
            tv_cf_record_cun_time=itemView.findViewById(R.id.tv_cf_record_cun_time);
            tv_cf_record_zhouqi=itemView.findViewById(R.id.tv_cf_record_zhouqi);
            tv_cf_record_fan_time=itemView.findViewById(R.id.tv_cf_record_fan_time);
            tv_cf_record_status=itemView.findViewById(R.id.tv_cf_record_status);
            tv_sy_record_coin = itemView.findViewById(R.id.tv_sy_record_coin);
        }
    }
}
