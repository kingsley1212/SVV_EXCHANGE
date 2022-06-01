package com.svv.jys.code.module.myself.myasset.jiaoyi.rengourecord.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.MBaseAdapter;
import com.svv.jys.code.common.entity.RenGouRecordEntity;

import java.util.List;

/**
 * Created by js on 2018/10/19.
 */

public class RenGouRecordAdapter extends MBaseAdapter<RenGouRecordAdapter.ViewHolder> {
    private Context context;
    private List<RenGouRecordEntity.RowsBean> list;
    private String type;

    public RenGouRecordAdapter(Context context, List<RenGouRecordEntity.RowsBean> list, String type) {
        this.context = context;
        this.list = list;
        this.type = type;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_rengou_record, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final RenGouRecordEntity.RowsBean rowsBean = list.get(position);
        holder.tv_rengou_coin.setText(rowsBean.getCoin().toUpperCase());
        holder.tv_rengou_time.setText(rowsBean.getAdd_time());
        holder.tv_rengou_statas.setText(rowsBean.getStatus_name());
        holder.tv_rengou_record_num.setText(rowsBean.getNum());
        holder.tv_rengou_record_shiji_num.setText(rowsBean.getReal_num());
        holder.tv_rengou_record_dongji.setText(rowsBean.getFrezze_num());
        holder.tv_rengou_record_sxf.setText(rowsBean.getFee());
        if (rowsBean.getStatus().equals("1")) {
            holder.tv_rengou_statas.setSelected(false);
//            holder.tv_rengou_chexiao.setVisibility(View.GONE);
        } else if (rowsBean.getStatus().equals("0")){
            holder.tv_rengou_statas.setSelected(true);
//            holder.tv_rengou_chexiao.setVisibility(View.VISIBLE);
        }else {
            holder.tv_rengou_statas.setSelected(true);
//            holder.tv_rengou_chexiao.setVisibility(View.GONE);
        }
        if (type.equals("1")) {
            holder.tv_rengou_nuk_weizi.setText(context.getString(R.string.rengou_num));
            holder.tv_shiji_rengou_weizi.setText(context.getString(R.string.shiji_rengou_num));
        } else {
            holder.tv_rengou_nuk_weizi.setText(context.getString(R.string.peishou_num));
            holder.tv_shiji_rengou_weizi.setText(context.getString(R.string.shiji_peishou_num));
        }
        holder.tv_rengou_chexiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.onItemClick(position,list.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_rengou_coin, tv_rengou_time, tv_rengou_statas, tv_rengou_record_num,
                tv_rengou_record_shiji_num, tv_rengou_record_dongji, tv_rengou_record_sxf, tv_rengou_chexiao, tv_rengou_nuk_weizi, tv_shiji_rengou_weizi;
        View view4;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_rengou_coin = itemView.findViewById(R.id.tv_rengou_coin);
            tv_rengou_time = itemView.findViewById(R.id.tv_rengou_time);
            tv_rengou_statas = itemView.findViewById(R.id.tv_rengou_statas);
            tv_rengou_record_num = itemView.findViewById(R.id.tv_rengou_record_num);
            tv_rengou_record_shiji_num = itemView.findViewById(R.id.tv_rengou_record_shiji_num);
            tv_rengou_record_dongji = itemView.findViewById(R.id.tv_rengou_record_dongji);
            tv_rengou_record_sxf = itemView.findViewById(R.id.tv_rengou_record_sxf);
            tv_rengou_chexiao = itemView.findViewById(R.id.tv_rengou_chexiao);
            tv_rengou_nuk_weizi = itemView.findViewById(R.id.tv_rengou_nuk_weizi);
            tv_shiji_rengou_weizi = itemView.findViewById(R.id.tv_shiji_rengou_weizi);
            view4 = itemView.findViewById(R.id.view4);
        }
    }
}
