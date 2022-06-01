package com.svv.jys.code.module.myself.myaddress.base.coin.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.MBaseAdapter;
import com.svv.jys.code.common.entity.CoinEntity;
import com.svv.jys.code.common.utils.ToolUtils;

import java.util.List;

/**
 * Created by js on 2018/5/19.
 */

public class CoinAdapter extends MBaseAdapter<CoinAdapter.ViewHolder>{
    private Context context;
    private List<CoinEntity> list;
    public CoinAdapter(Context context,List<CoinEntity> list){
        this.context=context;
        this.list=list;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_coin,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tv_coin_name.setText(list.get(position).getName().toUpperCase());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ToolUtils.isFastClick(view.getId())){
                    onItemClick.onItemClick(position,list.get(position));
                }

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
