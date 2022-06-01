package com.svv.jys.code.module.myself.myasset.transfer.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.MBaseAdapter;
import com.svv.jys.code.common.entity.OtcTransferEntity;
import com.svv.jys.code.common.utils.GlideUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by js on 2018/5/26.
 */

public class TransferRecordAdapter extends MBaseAdapter<TransferRecordAdapter.ViewHolder>{
    private Context context;
    private List<OtcTransferEntity> entities;
    public TransferRecordAdapter(Context context){
        this.context=context;
        entities = new ArrayList<>();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_transfer,parent,false));
    }

    public void setEntities(List<OtcTransferEntity> entities) {
        this.entities = entities;
    }

    public void addEntities(List<OtcTransferEntity> entities) {
        this.entities.addAll(entities);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        OtcTransferEntity entity = entities.get(position);
        GlideUtils.loadImageDefult(context,entity.getLogo(),holder.coin_logo_iv);
        holder.num_tv.setText(entity.getNum());
        holder.coin_tv.setText(entity.getCoin().toUpperCase());
        holder.describe_tv.setText(entity.getType());
        holder.time_tv.setText(entity.getAdd_time());
        if (entity.getStatus().equals("0")){
            holder.tv_status.setText(R.string.audit);
            holder.tv_status.setTextColor(context.getResources().getColor(R.color.c_1ede89));
        }else if (entity.getStatus().equals("1")){
            holder.tv_status.setText(R.string.traverse);
            holder.tv_status.setTextColor(context.getResources().getColor(R.color.c_1a456d));
        }else if (entity.getStatus().equals("2")){
            holder.tv_status.setText(R.string.refuse);
            holder.tv_status.setTextColor(context.getResources().getColor(R.color.c_ff5755));
        }
    }

    @Override
    public int getItemCount() {
        return entities.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView coin_logo_iv;
        TextView coin_tv,num_tv,describe_tv,time_tv,tv_status;
        public ViewHolder(View itemView) {
            super(itemView);
            coin_tv=itemView.findViewById(R.id.coin_tv);
            num_tv=itemView.findViewById(R.id.num_tv);
            describe_tv=itemView.findViewById(R.id.describe_tv);
            time_tv=itemView.findViewById(R.id.time_tv);
            coin_logo_iv = itemView.findViewById(R.id.coin_logo_iv);
            tv_status=itemView.findViewById(R.id.tv_status);
        }
    }
}
