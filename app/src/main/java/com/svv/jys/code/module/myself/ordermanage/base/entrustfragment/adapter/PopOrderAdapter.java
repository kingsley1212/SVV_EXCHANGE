package com.svv.jys.code.module.myself.ordermanage.base.entrustfragment.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.MBaseAdapter;
import com.svv.jys.code.common.entity.MarketOrederEntity;

import java.util.List;

/**
 * Created by js on 2018/6/13.
 */

public class PopOrderAdapter extends MBaseAdapter<PopOrderAdapter.ViewHolder> {
    private Context context;
    private List<MarketOrederEntity> list;

    public PopOrderAdapter(Context context, List<MarketOrederEntity> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_pop_entrust, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tv_pop_entrust.setText(list.get(position).getName().toUpperCase());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClick.onItemClick(position, list.get(position));
            }
        });
        if (list.get(position).isSelect()) {
            holder.tv_pop_entrust.setSelected(true);
            holder.tv_pop_entrust.setTextColor(0xffffffff);
        } else {
            holder.tv_pop_entrust.setSelected(false);
            holder.tv_pop_entrust.setTextColor(0xff444444);
        }
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_pop_entrust;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_pop_entrust = itemView.findViewById(R.id.tv_pop_entrust);
        }
    }
}
