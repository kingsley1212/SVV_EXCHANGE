package com.svv.jys.code.module.myself.ordermanage.base.entrustfragment.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.MBaseAdapter;
import com.svv.jys.code.common.entity.EntrustEntity;
import com.svv.jys.code.common.utils.ToolUtils;

import java.util.List;

/**
 * Created by js on 2018/5/25.
 */

public class EntrustAdapter extends MBaseAdapter<EntrustAdapter.ViewHolder> {
    private String type;
    private Context context;
    private List<EntrustEntity.RowsBean> list;

    public EntrustAdapter(Context context, List<EntrustEntity.RowsBean> list, String type) {
        this.context = context;
        this.list = list;
        this.type = type;
    }

    public void setEntrustManager(EntrustManager entrustManager) {
        this.entrustManager = entrustManager;
    }

    private EntrustManager entrustManager;

    public interface EntrustManager {
        void undoEntrust(int position, View view);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_entrust2, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        EntrustEntity.RowsBean bean = list.get(position);
        holder.tv_market1.setText(bean.getMarket().toUpperCase().split("_")[0]);
        holder. tv_market2.setText(bean.getMarket().toUpperCase().split("_")[1]);
        holder.tv_entrust_price.setText(context.getString(R.string.EntrustFrag_price) + bean.getPrice());
        holder.tv_entrust_num.setText(context.getString(R.string.EntrustFrag_number) + bean.getNum());
        holder.tv_entrust_total.setText(String.format("%s:%s", context.getString(R.string.userinfo_jyje), bean.getMum()));
        holder.tv_entrust_yijiaoyi.setText(context.getString(R.string.EntrustFrag_yes_business) + bean.getDeal());
        holder.tv_entrust_type.setText(bean.getType_name());
        holder.iv_comlete.setText(bean.getStatus_name());
        if (bean.getType().equals("2")){
            holder.tv_entrust_type.setSelected(true);
        }else {
            holder.tv_entrust_type.setSelected(false);
        }
        if ("0".equals(bean.getStatus())){
            holder.iv_comlete.setVisibility(View.GONE);
            holder.tv_entrust_undo.setVisibility(View.VISIBLE);
        }else if ("2".equals(bean.getStatus())){
            holder.tv_entrust_undo.setVisibility(View.GONE);
            holder.iv_comlete.setVisibility(View.VISIBLE);
        }else {
            holder.tv_entrust_undo.setVisibility(View.GONE);
            holder.iv_comlete.setVisibility(View.VISIBLE);
        }
        holder.tv_entrust_undo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                entrustManager.undoEntrust(position, view);
            }
        });
        holder.tv_entrust_status.setText(context.getString(R.string.EntrustFrag_business_state) + bean.getStatus_name());
        holder.tv_entrust_time.setText(context.getString(R.string.EntrustFrag_time) + ToolUtils.timeStamp2String(bean.getAdd_time(), "yyyy-MM-dd HH:mm:ss"));
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_market1,tv_market2, tv_entrust_price, tv_entrust_num, tv_entrust_total, tv_entrust_yijiaoyi, tv_entrust_type,
                tv_entrust_status, tv_entrust_time, tv_entrust_undo;
        TextView iv_comlete;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_comlete = itemView.findViewById(R.id.iv_comlete);
            tv_market1 = itemView.findViewById(R.id.tv_market1);
            tv_market2 = itemView.findViewById(R.id.tv_market2);
            tv_entrust_price = itemView.findViewById(R.id.tv_entrust_price);
            tv_entrust_num = itemView.findViewById(R.id.tv_entrust_num);
            tv_entrust_total = itemView.findViewById(R.id.tv_entrust_total);
            tv_entrust_yijiaoyi = itemView.findViewById(R.id.tv_entrust_yijiaoyi);
            tv_entrust_type = itemView.findViewById(R.id.tv_entrust_type);
            tv_entrust_status = itemView.findViewById(R.id.tv_entrust_status);
            tv_entrust_time = itemView.findViewById(R.id.tv_entrust_time);
            tv_entrust_undo = itemView.findViewById(R.id.tv_entrust_undo);
        }
    }
}
