package com.svv.jys.code.module.business.tradechat.transaction.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.MBaseAdapter;
import com.svv.jys.code.common.entity.TransactionEntity;
import com.svv.jys.code.common.utils.ToolUtils;

import java.util.ArrayList;
import java.util.List;

public class TransactionAdapter extends MBaseAdapter<TransactionAdapter.ViewHolder> {
    private List<TransactionEntity> list;
    private Context mContext;
    private LayoutInflater inflater;

    public TransactionAdapter(Context mContext) {
        this.inflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        list = new ArrayList<>();
    }

    public void setList(List<TransactionEntity> list) {
        handleList(list);
    }

    public void handleList(List<TransactionEntity> list) {
        if (list.size() > 20) {
            this.list.clear();
            this.list.addAll(list.subList(0, 20));
        } else {
            this.list = list;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return  new ViewHolder(inflater.inflate(R.layout.item_transaction, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
      TransactionEntity entity = list.get(position);
      holder.num_tv.setText(entity.getAmount());
      holder.price_tv.setText(entity.getPrice());
      switch (entity.getType()){
          case "sell":
              holder.type_tv.setText(mContext.getString(R.string.tradechat_sell));
              holder.type_tv.setSelected(true);
              break;
          case "buy":
              holder.type_tv.setText(mContext.getString(R.string.tradechat_buy));
              holder.type_tv.setSelected(false);
              break;
      }
      holder.time_tv.setText(ToolUtils.timeStamp2String(entity.getTime(),"HH:mm:ss"));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView num_tv,price_tv,type_tv,time_tv;
        public ViewHolder(View itemView) {
            super(itemView);
            num_tv = itemView.findViewById(R.id.num_tv);
            price_tv = itemView.findViewById(R.id.price_tv);
            type_tv = itemView.findViewById(R.id.type_tv);
            time_tv = itemView.findViewById(R.id.time_tv);
        }
    }
}
