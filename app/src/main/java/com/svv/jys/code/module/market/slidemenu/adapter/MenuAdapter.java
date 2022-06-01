package com.svv.jys.code.module.market.slidemenu.adapter;

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
 * Created by js on 2018/5/19.
 */

public class MenuAdapter extends MBaseAdapter<MenuAdapter.ViewHolder> {
    private Context context;
    private List<String> list;
    private int selectPosition = 0;

    public MenuAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_menu, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.tv_item_menu.setText(list.get(position).toUpperCase());

        if (selectPosition == position) {
            holder.itemView.setSelected(true);
        } else {
            holder.itemView.setSelected(false);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectPosition = position;
                onItemClick.onItemClick(position, list.get(position));
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_item_menu;
        View select_v;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_item_menu = itemView.findViewById(R.id.tv_item_menu);
            select_v = itemView.findViewById(R.id.select_v);
        }
    }
}
