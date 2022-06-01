package com.svv.jys.code.common.view.popup;

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
 * Created by js on 2018/8/18.
 */

public class SelectAdapter extends MBaseAdapter<SelectAdapter.ViewHolder> {
    private Context context;
    private List<String> list;
    private int index = -1;

    public void setDa(boolean da) {
        isDa = da;
    }

    private boolean isDa;
    public SelectAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    public void setValueSelect(String value){
        int i = 0;
        for (String s : list){
            if (value.equals(s)){
                index = i;
                break;
            }
            i++;
        }
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_pop_payment, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if (!isDa){
            holder.tv_select.setText(list.get(position).toUpperCase());
        }else {
            holder.tv_select.setText(list.get(position));
        }

        holder.tv_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index = position;
                onItemClick.onItemClick(position, list.get(position));
                notifyDataSetChanged();
            }
        });
        if (index == position){
            holder.tv_select.setSelected(true);
        }else {
            holder.tv_select.setSelected(false);
        }
        if (position == list.size()-1){
            holder.view.setVisibility(View.GONE);
        }else {
            holder.view.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_select;
        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_select = itemView.findViewById(R.id.tv_select);
            view = itemView.findViewById(R.id.view);
        }
    }
}
