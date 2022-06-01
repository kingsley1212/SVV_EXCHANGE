package com.svv.jys.code.module.myself.myasset.base.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.MBaseAdapter;
import com.svv.jys.code.common.entity.LtAssatEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/3 0003.
 */

public class MyAssetCount2Adapter extends MBaseAdapter<MyAssetCount2Adapter.MyAssetViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private List<LtAssatEntity.Rows> allList;

    public MyAssetCount2Adapter(Context context, List<LtAssatEntity.Rows> allList) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.allList = allList;
        if (this.allList == null) {
            this.allList = new ArrayList<>();
        }
    }

    public void setData(List<LtAssatEntity.Rows> list) {
        allList = list;
        if (allList == null) {
            allList = new ArrayList<>();
        }
        notifyDataSetChanged();
    }

    public void addData(List<LtAssatEntity.Rows> list) {
        if (list == null) {
            list = new ArrayList<>();
        }
        if (allList == null) {
            allList = new ArrayList<>();
        }
        allList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public MyAssetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyAssetViewHolder(inflater.inflate(R.layout.item_myasset_lt, null));
    }

    @Override
    public void onBindViewHolder(MyAssetViewHolder holder, int position) {
        holder.bindData(position);
    }

    @Override
    public int getItemCount() {
        return allList.size();
    }

    class MyAssetViewHolder extends RecyclerView.ViewHolder {
        TextView countName,able,freeze,zhehe,sell_zhehe,sell_freeze,sell_able;
        public MyAssetViewHolder(View itemView) {
            super(itemView);
            countName=itemView.findViewById(R.id.countName);
            able=itemView.findViewById(R.id.able);
            freeze=itemView.findViewById(R.id.freeze);
            zhehe=itemView.findViewById(R.id.zhehe);
            sell_zhehe=itemView.findViewById(R.id.sell_zhehe);
            sell_freeze=itemView.findViewById(R.id.sell_freeze);
            sell_able=itemView.findViewById(R.id.sell_able);
        }

        public void bindData(final int position) {
            LtAssatEntity.Rows entity=allList.get(position);
            countName.setText(entity.getMarket().toUpperCase().replace("_","/"));
            String[] split=entity.getMarket().split("_");
            able.setText(split[0].toUpperCase());
            freeze.setText(entity.getBuy_coin());
            zhehe.setText(entity.getBuy_coin_debit());
            sell_able.setText(split[1].toUpperCase());
            sell_freeze.setText(entity.getSell_coin());
            sell_zhehe.setText(entity.getSell_coin_debit());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClick != null) {
                        onItemClick.onItemClick(position, "");
                    }
                }
            });
        }
    }


}
