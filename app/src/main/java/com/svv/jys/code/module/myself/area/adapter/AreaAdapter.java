package com.svv.jys.code.module.myself.area.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.MBaseAdapter;
import com.svv.jys.code.common.entity.CountryEntity;

import java.util.List;

/**
 * Created by js on 2018/9/8.
 */

public class AreaAdapter extends MBaseAdapter<AreaAdapter.ViewHolder>{
    private List<CountryEntity> list = null;
    private Context mContext;

    public AreaAdapter(Context mContext, List<CountryEntity> list) {
        this.mContext = mContext;
        this.list = list;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_area,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tv_area_code.setText(list.get(position).getChinese()+"("+list.get(position).getCode()+")");
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
        private TextView tv_area_code;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_area_code=itemView.findViewById(R.id.tv_area_code);
        }
    }
}
