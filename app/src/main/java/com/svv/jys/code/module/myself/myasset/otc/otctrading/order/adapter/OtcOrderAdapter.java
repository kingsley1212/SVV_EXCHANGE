package com.svv.jys.code.module.myself.myasset.otc.otctrading.order.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.MBaseAdapter;
import com.svv.jys.code.common.entity.OtcOrderEntity;
import com.svv.jys.code.common.utils.ToolUtils;

import java.util.List;

/**
 * Created by js on 2018/6/11.
 */

public class OtcOrderAdapter extends MBaseAdapter<OtcOrderAdapter.ViewHolder>{
    private Context context;
    private List<OtcOrderEntity.RowsBean> list;
    public OtcOrderAdapter(Context context,List<OtcOrderEntity.RowsBean> list){
        this.context=context;
        this.list=list;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_otc_order,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        OtcOrderEntity.RowsBean rowsBean=list.get(position);
        if (rowsBean.getType().equals("0")){
            holder.tv_order_type.setSelected(false);
        }else {
            holder.tv_order_type.setSelected(true);
        }
        holder.tv_object_name.setText(rowsBean.getAdv_name());
        holder.tv_time.setText(context.getString(R.string.time)+"："+ ToolUtils.timeStamp2String(rowsBean.getAdd_time(),"yyyy-MM-dd "));
        holder.tv_num.setText(context.getString(R.string.num)+"："+rowsBean.getNum()+rowsBean.getCoin_name());
        holder.tv_total.setText(context.getString(R.string.price)+"："+rowsBean.getAmount()+rowsBean.getCurrency());
        holder.tv_price.setText(context.getString(R.string.total)+"："+rowsBean.getPrice()+rowsBean.getCurrency());
        holder.tv_order_code.setText(rowsBean.getOrder_no());
        holder.tv_order_type.setText(rowsBean.getType_name());
        holder.tv_order_status.setText(rowsBean.getStatus_name());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClick.onItemClick(position,list.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_order_code,tv_object_name,tv_price,tv_num,tv_time,tv_total,tv_order_status,tv_order_type;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_order_code=itemView.findViewById(R.id.tv_order_code);
            tv_object_name=itemView.findViewById(R.id.tv_object_name);
            tv_price=itemView.findViewById(R.id.tv_price);
            tv_num=itemView.findViewById(R.id.tv_num);
            tv_time=itemView.findViewById(R.id.tv_time);
            tv_total=itemView.findViewById(R.id.tv_total);
            tv_order_status=itemView.findViewById(R.id.tv_order_status);
            tv_order_type=itemView.findViewById(R.id.tv_order_type);
        }
    }
}
