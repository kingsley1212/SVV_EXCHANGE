package com.svv.jys.code.module.myself.inviterecord.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.MBaseAdapter;
import com.svv.jys.code.common.entity.InviteRecordEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by js on 2018/5/26.
 */

public class InviteRecordAdapter extends MBaseAdapter<InviteRecordAdapter.ViewHolder>{
    private Context context;
    private List<InviteRecordEntity> entities;
    public InviteRecordAdapter(Context context){
        this.context=context;
        entities = new ArrayList<>();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_invite,parent,false));
    }

    public void setEntities(List<InviteRecordEntity> entities) {
        this.entities = entities;
    }

    public void addEntities(List<InviteRecordEntity> entities) {
        this.entities.addAll(entities);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        InviteRecordEntity entity = entities.get(position);
        holder.time_tv.setText(entity.getReg_time());
        holder.phone_tv.setText(entity.getUser_name());
        holder.status_tv.setText(entity.getStatus_name());
    }

    @Override
    public int getItemCount() {
        return entities.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView phone_tv,status_tv,time_tv;
        public ViewHolder(View itemView) {
            super(itemView);
            phone_tv = itemView.findViewById(R.id.phone_tv);
            status_tv = itemView.findViewById(R.id.status_tv);
            time_tv = itemView.findViewById(R.id.time_tv);
        }
    }
}
