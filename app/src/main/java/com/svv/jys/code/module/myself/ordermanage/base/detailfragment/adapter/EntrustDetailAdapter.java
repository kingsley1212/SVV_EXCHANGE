package com.svv.jys.code.module.myself.ordermanage.base.detailfragment.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.MBaseAdapter;
import com.svv.jys.code.common.entity.EntrustDetailEntity;
import com.svv.jys.code.common.utils.ToolUtils;

import java.util.List;

/**
 * Created by js on 2018/5/25.
 */

public class EntrustDetailAdapter extends MBaseAdapter<EntrustDetailAdapter.ViewHolder> {
    private Context context;
    private List<EntrustDetailEntity.RowsBean> list;

    public EntrustDetailAdapter(Context context, List<EntrustDetailEntity.RowsBean> list) {
        this.context = context;
        this.list = list;
    }

    public void setList(List<EntrustDetailEntity.RowsBean> list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_entrust3, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        EntrustDetailEntity.RowsBean bean = list.get(position);
        holder.tv_market1.setText(bean.getMarket().toUpperCase().split("_")[0]);
        holder. tv_market2.setText(bean.getMarket().toUpperCase().split("_")[1]);
        holder.tv_entrust_price.setText( bean.getPrice());
        holder.tv_entrust_num.setText(bean.getNum());
        holder.tv_entrust_type.setText(bean.getType_name());
        if (bean.getType().equals("1")) {
            holder.tv_entrust_type.setSelected(false);
            holder.tv_name.setText(context.getResources().getString(R.string.sell_name)+bean.getOther_name());
        } else {
            holder.tv_entrust_type.setSelected(true);
            holder.tv_name.setText(context.getResources().getString(R.string.buy_name)+bean.getOther_name());
        }
        holder.tv_entrust_time.setText(ToolUtils.timeStamp2String(bean.getAdd_time(), "MM-dd HH:mm"));
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_market1,tv_market2, tv_entrust_price, tv_entrust_num, tv_entrust_type, tv_entrust_status, tv_entrust_time,tv_name;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_name= itemView.findViewById(R.id.tv_name);
            tv_market1 = itemView.findViewById(R.id.tv_market1);
            tv_market2 = itemView.findViewById(R.id.tv_market2);
            tv_entrust_price = itemView.findViewById(R.id.tv_entrust_price);
            tv_entrust_num = itemView.findViewById(R.id.tv_entrust_num);
            tv_entrust_type = itemView.findViewById(R.id.tv_entrust_type);
            tv_entrust_status = itemView.findViewById(R.id.tv_entrust_status);
            tv_entrust_time = itemView.findViewById(R.id.tv_entrust_time);
        }
    }
}
