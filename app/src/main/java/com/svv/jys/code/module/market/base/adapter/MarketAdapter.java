package com.svv.jys.code.module.market.base.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
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
    private List<MarketListEntity> newList;
    private String price;
    private int type;
    private boolean isAscending;
    public MarketAdapter(Context context, List<MarketListEntity> list,String price) {
        this.context = context;
        this.allList = list;
        this.price = price;
        newList = new ArrayList<>();
        newList.addAll(allList);
        inflater = LayoutInflater.from(context);
    }

    public void setSort(int type,boolean isAscending){
        this.type = type;
        this.isAscending = isAscending;
        notifyChanged();
    }

    public void setData(List<MarketListEntity> list) {
        this.allList = list;
        if (this.allList == null) {
            this.allList = new ArrayList<>();
        }
        newList = new ArrayList<>();
        newList.addAll(allList);
        notifyChanged();
    }

    public void notifyChanged(){
        switch (type){
            case 0:
                coin(newList,isAscending);
                break;
            case 1:
                price(newList,isAscending);
                break;
            case 2:
                rota(newList,isAscending);
                break;
        }
        notifyDataSetChanged();
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
        newList = new ArrayList<>();
        newList.addAll(allList);
        notifyChanged();
    }

    @Override
    public MarketViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MarketViewHolder(inflater.inflate(R.layout.item_market, parent,false));
    }

    @Override
    public void onBindViewHolder(MarketViewHolder holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ToolUtils.isFastClick(view.getId())){
                    onItemClick.onItemClick(position, newList.get(position));
                }

            }
        });
        MarketListEntity entity = newList.get(position);
        holder.tv_market_buy_name.setText(entity.getBuy_name().toUpperCase());
        holder.tv_market_sell_name.setText("/" + entity.getSell_name().toUpperCase());
        holder.tv_new_price.setText(ToolUtils.SzzcFormat(entity.getNew_price()));
        holder.tv_chengjiaoliang.setText(context.getString(R.string.marketbusiness_liagn) + ToolUtils.SzzcFormat(entity.getVolume_day()));
        entity.setPrice(GFbPrice(entity));
        holder.tv_lixi.setText(String.format("%s%s", ToolUtils.getCurrency(context), entity.getPrice()));

        if (ToolUtils.String2Double(entity.getChange()) >= 0) {
            holder.tv_rote.setSelected(false);
//            holder.tv_new_price.setSelected(false);
            holder.tv_rote.setText("+" + entity.getChange() + "%");
        } else {
            holder.tv_rote.setSelected(true);
//            holder.tv_new_price.setSelected(true);
            holder.tv_rote.setText(entity.getChange() + "%");
        }

    }

    /**
     * 计算大概的法币价格
     */
    public String GFbPrice(MarketListEntity entity) {
        String usdt = getPrice(entity.getSell_name());
        String s = ToolUtils.multiply(ACache.get(MAppliaction.getApp()).getAsString(ACEConstant.PRICE),usdt);
        String ss = ToolUtils.multiplyWithScale(s, entity
                .getNew_price(), 2);
        return ss;

    }

    public void price(List<MarketListEntity> list, final boolean isAscending) {
        Collections.sort(list, new Comparator<MarketListEntity>() {
            @Override
            public int compare(MarketListEntity o1, MarketListEntity o2) {
                if (isAscending) {
                    return Double.valueOf(o1.getNew_price()).compareTo(Double.valueOf(o2.getNew_price()));
                } else {
                    return Double.valueOf(o2.getNew_price()).compareTo(Double.valueOf(o1.getNew_price()));
                }

            }
        });
    }

    public void rota(List<MarketListEntity> list, final boolean isAscending) {
        Collections.sort(list, new Comparator<MarketListEntity>() {
            @Override
            public int compare(MarketListEntity o1, MarketListEntity o2) {
                if (isAscending) {
                    return Double.valueOf(o1.getChange()).compareTo(Double.valueOf(o2.getChange()));
                } else {
                    return Double.valueOf(o2.getChange()).compareTo(Double.valueOf(o1.getChange()));
                }

            }
        });
    }

    public void coin(List<MarketListEntity> list, final boolean isAscending) {
        Collections.sort(list, new Comparator<MarketListEntity>() {
            @Override
            public int compare(MarketListEntity o1, MarketListEntity o2) {
                if (isAscending) {
                    return o1.getBuy_name().charAt(0) >= o2.getBuy_name().charAt(0)?1:-1;
                } else {
                    return o2.getBuy_name().charAt(0) >= o1.getBuy_name().charAt(0)?1:-1;
                }

            }
        });
    }



    public String getPrice(String name){
       return JSON.parseObject(JSON.parseObject(price).getString(name)).getString("usdt_price");
    }



    @Override
    public int getItemCount() {
        return allList == null ? 0 : allList.size();
    }

    class MarketViewHolder extends RecyclerView.ViewHolder {
        TextView tv_market_buy_name, tv_market_sell_name, tv_new_price, tv_chengjiaoliang, tv_lixi, tv_rote;

        public MarketViewHolder(View itemView) {
            super(itemView);
            tv_market_buy_name = itemView.findViewById(R.id.tv_market_buy_name);
            tv_market_sell_name = itemView.findViewById(R.id.tv_market_sell_name);
            tv_new_price = itemView.findViewById(R.id.tv_new_price);
            tv_chengjiaoliang = itemView.findViewById(R.id.tv_chengjiaoliang);
            tv_lixi = itemView.findViewById(R.id.tv_lixi);
            tv_rote = itemView.findViewById(R.id.tv_rote);
        }
    }
}
