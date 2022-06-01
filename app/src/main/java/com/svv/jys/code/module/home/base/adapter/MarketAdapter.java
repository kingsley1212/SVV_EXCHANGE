package com.svv.jys.code.module.home.base.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.MBaseAdapter;
import com.svv.jys.code.common.constant.ACEConstant;
import com.svv.jys.code.common.db.nativecache.ACache;
import com.svv.jys.code.common.entity.MarketListEntity;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.app.MAppliaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Administrator on 2018/5/3 0003.
 */

public class MarketAdapter extends MBaseAdapter<MarketAdapter.MarketViewHolder> {

    private Context context;
    private List<MarketListEntity> allList;
    private LayoutInflater inflater;

    public MarketAdapter(Context context) {
        this.context = context;
        this.allList = new ArrayList<>();
        inflater = LayoutInflater.from(context);
    }

    public void setData(List<MarketListEntity> list) {
        this.allList.clear();
        this.allList = list;
        if (this.allList == null) {
            this.allList = new ArrayList<>();
        }
    }

    public List<MarketListEntity> getAllList() {
        return allList;
    }

    public void addData(List<MarketListEntity> list) {
        if (this.allList == null) {
            this.allList = new ArrayList<>();
        }
        if (list == null) {
            list = new ArrayList<>();
        }
        allList.addAll(list);
        notifyDataSetChanged();
    }

    public void rank(final boolean isAscending) {
        Collections.sort(allList, new Comparator<MarketListEntity>() {
            @Override
            public int compare(MarketListEntity o1, MarketListEntity o2) {
                if (isAscending) {
                    return Double.valueOf(o1.getChange()).compareTo(Double.valueOf(o2.getChange()));
                } else {
                    return Double.valueOf(o2.getChange()).compareTo(Double.valueOf(o1.getChange()));
                }

            }
        });
        notifyDataSetChanged();
    }

    @Override
    public MarketViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MarketViewHolder(inflater.inflate(R.layout.item_market2, parent,false));
    }

    @Override
    public void onBindViewHolder(MarketViewHolder holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClick.onItemClick(position, allList.get(position));
            }
        });
        final MarketListEntity entity = allList.get(position);
        holder.tv_market_buy_name.setText(entity.getBuy_name().toUpperCase());
        holder.tv_market_sell_name.setText("/" + entity.getSell_name().toUpperCase());
        holder.tv_new_price.setText(ToolUtils.SzzcFormat(entity.getNew_price()));
     /*   holder.tv_chengjiaoliang.setText(context.getString(R.string.marketbusiness_liagn) + ToolUtils.SzzcFormat(entity.getVolume_day()));
        holder.tv_lixi.setText(String.format("%s  %s", ToolUtils.getCurrency(context), GFbPrice(entity)));*/

        if (ToolUtils.String2Double(entity.getChange()) >= 0) {
            holder.tv_rote.setSelected(false);
            holder.tv_rote.setText("+" + entity.getChange() + "%");
        } else {
            holder.tv_rote.setSelected(true);
            holder.tv_rote.setText(entity.getChange() + "%");
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                TradeChat2Act.startAct(context,entity);
                ToolUtils.startKLineAct(context, entity);
            }
        });
    }

    /**
     * 计算大概的法币价格
     */
    public String GFbPrice(MarketListEntity entity) {
        String s = ToolUtils.multiplyWithScale(ACache.get(MAppliaction.getApp()).getAsString(ACEConstant.PRICE),entity.getNew_price(), 2);
        return s;
    }


    @Override
    public int getItemCount() {
        return allList == null ? 0 : allList.size();
    }

    class MarketViewHolder extends RecyclerView.ViewHolder {
        TextView tv_market_buy_name, tv_market_sell_name, tv_new_price, tv_rote;

        public MarketViewHolder(View itemView) {
            super(itemView);
            tv_market_buy_name = itemView.findViewById(R.id.tv_market_buy_name);
            tv_market_sell_name = itemView.findViewById(R.id.tv_market_sell_name);
            tv_new_price = itemView.findViewById(R.id.tv_new_price);
            tv_rote = itemView.findViewById(R.id.tv_rote);
        }
    }
}
