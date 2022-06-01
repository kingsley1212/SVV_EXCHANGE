package com.svv.jys.code.module.myself.myasset.otc.otctrading.fragment.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.MBaseAdapter;
import com.svv.jys.code.common.entity.PopEntity;

import java.util.List;

/**
 * Created by js on 2018/6/9.
 */

public class PopAdapter extends MBaseAdapter<PopAdapter.ViewHolder>{
    private List<PopEntity> list;
    private Context context;
    public PopAdapter( List<PopEntity> list, Context context){
        this.context=context;
        this.list=list;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_pop,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tv_pop_pay.setText(list.get(position).getName());
        if (list.get(position).isSelect()){
            holder.tv_pop_pay.setTextColor(Color.parseColor("#4969f2"));
        }else {
            holder.tv_pop_pay.setTextColor(Color.parseColor("#000000"));
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClick.onItemClick(position,list.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_pop_pay;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_pop_pay=itemView.findViewById(R.id.tv_pop_pay);
        }
    }
}
