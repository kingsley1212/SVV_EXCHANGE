package com.svv.jys.code.module.myself.inviterecord.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.MBaseAdapter;
import com.svv.jys.code.common.entity.RewardEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by js on 2018/5/26.
 */

public class CommissionAdapter extends MBaseAdapter<CommissionAdapter.ViewHolder>{
    private Context context;
    private List<RewardEntity> entities;
    public CommissionAdapter(Context context){
        this.context=context;
        entities = new ArrayList<>();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_reward,parent,false));
    }

    public void setEntities(List<RewardEntity> entities) {
        this.entities = entities;
    }

    public void addEntities(List<RewardEntity> entities) {
        this.entities.addAll(entities);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RewardEntity entity = entities.get(position);
        holder.user_name_tv.setText(entity.getFrom_name());
        holder.coin_tv.setText(entity.getCoin().toUpperCase());
        holder.jine_tv.setText(entity.getNum());
        holder.time_tv.setText(entity.getAdd_time());
    }

    @Override
    public int getItemCount() {
        return entities.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView user_name_tv,coin_tv,jine_tv,time_tv;
        public ViewHolder(View itemView) {
            super(itemView);
            coin_tv=itemView.findViewById(R.id.coin_tv);
            user_name_tv=itemView.findViewById(R.id.user_name_tv);
            jine_tv=itemView.findViewById(R.id.jine_tv);
            time_tv=itemView.findViewById(R.id.time_tv);
        }
    }
}
