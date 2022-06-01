package com.svv.jys.code.module.myself.coinshow.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.MBaseAdapter;
import com.svv.jys.code.common.entity.CoinShowEntity;
import com.svv.jys.code.common.utils.ToolUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/3 0003.
 */

public class CoinShowAdapter extends MBaseAdapter<CoinShowAdapter.MyAssetViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private List<CoinShowEntity> allList;
    private boolean saveCoin;
    private String code;
    public CoinShowAdapter(Context context,boolean saveCoin,String code) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        allList = new ArrayList();
        this.saveCoin=saveCoin;
        this.code=code;
    }

    public void setData(List<CoinShowEntity> list) {
        allList = list;
        if (allList == null) {
            allList = new ArrayList<>();
        }
        notifyDataSetChanged();
    }


    @Override
    public MyAssetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyAssetViewHolder(inflater.inflate(R.layout.item_coinshow, null));
    }

    @Override
    public void onBindViewHolder(MyAssetViewHolder holder, final int position) {
        final CoinShowEntity entity = allList.get(position);
        holder.language_tv.setText(entity.getCurrency_code());
        String coin = ToolUtils.getCurrency(context);
        if (saveCoin){
            if (entity.getCurrency_code().toUpperCase().equals(code.toUpperCase())){
                holder.choose_iv.setVisibility(View.VISIBLE);
            }else {
                holder.choose_iv.setVisibility(View.GONE);
            }
        }else {
            if (entity.getCurrency_code().toUpperCase().equals(coin.toUpperCase())){
                holder.choose_iv.setVisibility(View.VISIBLE);
            }else {
                holder.choose_iv.setVisibility(View.GONE);
            }
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClick.onItemClick(position,entity);
                if (!saveCoin){
                    ToolUtils.putCurrency(context,entity.getCurrency_code());
                }

//                notifyDataSetChanged();

            }
        });
    }

    @Override
    public int getItemCount() {
        return allList.size();
    }

    class MyAssetViewHolder extends RecyclerView.ViewHolder {
        private ImageView choose_iv;
        private TextView language_tv;
        public MyAssetViewHolder(View itemView) {
            super(itemView);
            choose_iv = itemView.findViewById(R.id.choose_iv);
            language_tv = itemView.findViewById(R.id.language_tv);
        }

        public void bindData( int position) {

        }
    }


}
