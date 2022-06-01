package com.svv.jys.code.module.market.slidemenu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.MBaseAdapter;
import com.svv.jys.code.common.entity.MarketListEntity;
import com.svv.jys.code.common.utils.ToolUtils;

import java.util.List;

/**
 * Created by js on 2018/5/19.
 */

public class MenuItemAdapter extends MBaseAdapter<MenuItemAdapter.ViewHolder> {
    private Context context;
    private List<MarketListEntity> list;
    private boolean isOptional;

    public MenuItemAdapter(Context context, List<MarketListEntity> list, boolean isOptional) {
        this.context = context;
        this.list = list;
        this.isOptional = isOptional;
    }

    public List<MarketListEntity> getList() {
        return list;
    }

    public void setList(List<MarketListEntity> list, boolean isOptional) {
        this.list = list;
        this.isOptional = isOptional;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_marketmenuitem, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.bindData(position, list.get(position));
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View itemView;
        TextView item_name, item_price, item_p;

        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            item_name = itemView.findViewById(R.id.item_name);
            item_price = itemView.findViewById(R.id.item_price);
            item_p = itemView.findViewById(R.id.item_p);
        }

        public void bindData(final int position, final MarketListEntity entity) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClick != null) {
                        onItemClick.onItemClick(position, entity);
                    }
                }
            });
            if (isOptional) {
                this.item_name.setText(entity.getName().replace("_","/").toUpperCase());
            } else {
                this.item_name.setText(entity.getName().split("_")[0].toUpperCase());
            }

            this.item_price.setText(ToolUtils.SzzcFormat(entity.getNew_price()));
//            this.item_p.setText(entity.getChange() + "%");
            if (ToolUtils.String2Double(entity.getChange()) >= 0) {
                this.item_p.setSelected(true);
                this.item_price.setSelected(true);
                this.item_p.setText("+" + entity.getChange() + "%");
            } else {
                this.item_p.setSelected(false);
                this.item_price.setSelected(false);
                this.item_p.setText(entity.getChange() + "%");
            }
        }

    }
}
