package com.svv.jys.code.module.myself.businessorder2.fragment.adapter;

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
 * Created by js on 2018/9/4.
 */

public class BusinessOrderAdapter2 extends MBaseAdapter<BusinessOrderAdapter2.ViewHolder> {
    private Context context;
    private List<OtcOrderEntity.RowsBean> list;
    public BusinessOrderAdapter2(Context context, List<OtcOrderEntity.RowsBean> list){
        this.context=context;
        this.list=list;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_otc_order2,parent,false));
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
        holder.tv_total.setText(rowsBean.getAmount()+rowsBean.getCurrency());
        holder.tv_order_code.setText(rowsBean.getOrder_no());
        holder.tv_order_type.setText(rowsBean.getType_name()+rowsBean.getCoin_name());
        holder.tv_order_status.setText(rowsBean.getStatus_name());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ToolUtils.isFastClick(view.getId())){
                    onItemClick.onItemClick(position,list.get(position));
                }

            }
        });
        holder.tv_order_userImg.setText(rowsBean.getR_name());
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_order_code,tv_object_name,tv_total,tv_order_status,tv_order_type,tv_order_userImg;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_order_code=itemView.findViewById(R.id.tv_order_code);
            tv_object_name=itemView.findViewById(R.id.tv_object_name);
            tv_total=itemView.findViewById(R.id.tv_total);
            tv_order_status=itemView.findViewById(R.id.tv_order_status);
            tv_order_type=itemView.findViewById(R.id.tv_order_type);
            tv_order_userImg=itemView.findViewById(R.id.tv_order_userImg);
        }
    }
}
