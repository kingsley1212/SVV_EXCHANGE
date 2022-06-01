package com.svv.jys.code.module.myself.myasset.base.adapter;

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
import com.svv.jys.code.common.entity.HomeAssatEntity;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.app.MAppliaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/3 0003.
 */

public class MyAssetCount1Adapter extends MBaseAdapter<MyAssetCount1Adapter.MyAssetViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private List<HomeAssatEntity.Rows> allList;

    public MyAssetCount1Adapter(Context context, List<HomeAssatEntity.Rows> allList) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.allList = allList;
        if (this.allList == null) {
            this.allList = new ArrayList<>();
        }
    }

    public void setData(List<HomeAssatEntity.Rows> list) {
        allList = list;
        if (allList == null) {
            allList = new ArrayList<>();
        }
        notifyDataSetChanged();
    }

    public void addData(List<HomeAssatEntity.Rows> list) {
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
        return new MyAssetViewHolder(inflater.inflate(R.layout.item_myasset_count, null));
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
        TextView countName,able,freeze,zhehe;
        public MyAssetViewHolder(View itemView) {
            super(itemView);
            countName=itemView.findViewById(R.id.countName);
            able=itemView.findViewById(R.id.able);
            freeze=itemView.findViewById(R.id.freeze);
            zhehe=itemView.findViewById(R.id.zhehe);
        }

        public void bindData(final int position) {
            HomeAssatEntity.Rows entity=allList.get(position);
            countName.setText(entity.getName());
            able.setText(entity.getAble());
            freeze.setText(entity.getFreeze());
            zhehe.setText(ToolUtils.multiplyWithScale( ACache.get(MAppliaction.getApp()).getAsString(ACEConstant.PRICE), entity
                    .getUsdt_price(), 2));
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
