package com.svv.jys.code.module.myself.myasset.otc.otctrading.otcsetting;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.MBaseAdapter;

import java.util.List;

/**
 * Created by js on 2018/7/25.
 */

public class SelectCoinAdapter extends MBaseAdapter<SelectCoinAdapter.ViewHolder>{
    private Context context;
    private List<String> list;
    public SelectCoinAdapter(Context context,List<String> list){
        this.context=context;
        this.list=list;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_selectcoin,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tv_coin_name.setText(list.get(position));
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
        TextView tv_coin_name;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_coin_name=itemView.findViewById(R.id.tv_coin_name);
        }
    }
}
