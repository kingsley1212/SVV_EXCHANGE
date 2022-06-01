package com.svv.jys.code.module.myself.welfare.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.MBaseAdapter;
import com.svv.jys.code.common.entity.WelfareEntity;
import com.svv.jys.code.common.utils.GlideUtils;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.myasset.jiaoyi.chargemoney.ChargeMoneyAct;

import java.util.List;

public class WelfareAdapter extends MBaseAdapter<WelfareAdapter.ViewHolder> {
    private Context context;
    private List<WelfareEntity.RowsBean> list;
    public WelfareAdapter( Context context,List<WelfareEntity.RowsBean> list){
        this.context=context;
        this.list=list;
    }
    public void setList(List<WelfareEntity.RowsBean> list) {
        this.list = list;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_wekfare,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final WelfareEntity.RowsBean rowsBean=list.get(position);
        GlideUtils.loadImageDefult(context,rowsBean.getImg(),holder.iv_welfare_pic);
        holder.tv_act_name.setText(rowsBean.getName());
        holder.tv_welfare_guize.setText("1"+rowsBean.getHold_coin().toUpperCase()+"："+rowsBean.getScale()+rowsBean.getGain_coin().toUpperCase());
        holder.tv_welfare_time.setText(rowsBean.getEnd_date());
        String format = String.format(context.getString(R.string.distribution_requirements), rowsBean.getMemo());
        holder.tv_welfare_yaoqiu.setText(Html.fromHtml(format));
        if (rowsBean.getStatus().equals("1")){
            holder.tv_welfare_status.setText(context.getString(R.string.the_distribution_of));
            holder.tv_welfare_status.setBackgroundResource(R.drawable.shape_welfare_the);
        }else if (rowsBean.getStatus().equals("2")){
            holder.tv_welfare_status.setText(context.getString(R.string.underway));
            holder.tv_welfare_status.setBackgroundResource(R.drawable.shape_welfare_ing);
        }else if (rowsBean.getStatus().equals("3")){
            holder.tv_welfare_status.setText(context.getString(R.string.finished));
            holder.tv_welfare_status.setBackgroundResource(R.drawable.shape_welfare_end);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ToolUtils.isFastClick(v.getId())){
                    ChargeMoneyAct.StartByCoin(context,rowsBean.getGain_coin());
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView iv_welfare_pic;
        private TextView tv_welfare_status,tv_act_name,tv_welfare_guize,tv_welfare_time,tv_welfare_yaoqiu;
        public ViewHolder(View itemView) {
            super(itemView);
            iv_welfare_pic=itemView.findViewById(R.id.iv_welfare_pic);
            tv_welfare_status=itemView.findViewById(R.id.tv_welfare_status);
            tv_act_name=itemView.findViewById(R.id.tv_act_name);
            tv_welfare_guize=itemView.findViewById(R.id.tv_welfare_guize);
            tv_welfare_time=itemView.findViewById(R.id.tv_welfare_time);
            tv_welfare_yaoqiu=itemView.findViewById(R.id.tv_welfare_yaoqiu);
        }
    }
}
