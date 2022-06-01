package com.svv.jys.code.module.business.barbusiness.base.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.MBaseAdapter;
import com.svv.jys.code.common.entity.FPankouEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 盘口数据列表
 * Created by Administrator on 2018/5/7 0007.
 */

public class Pankou3Adapter extends MBaseAdapter<RecyclerView.ViewHolder> {

    private final int IS_TITLE = 1;

    private List<FPankouEntity> allList;
    private Context context;
    private LayoutInflater inflater;

    public Pankou3Adapter(List<FPankouEntity> list, Context context) {
        allList = new ArrayList<>();
        FPankouEntity entity = new FPankouEntity();
        entity.viewholder_type = IS_TITLE;
        allList.add(entity);
        allList.addAll(list == null ? new ArrayList<FPankouEntity>() : list);

        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void setData(List<FPankouEntity> list) {
        FPankouEntity entity = new FPankouEntity();
        entity.viewholder_type = IS_TITLE;
        allList.clear();
        allList.add(entity);
        allList.addAll(list == null ? new ArrayList<FPankouEntity>() : list);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case IS_TITLE:
                return new PankouViewTitleHolder(inflater.inflate(R.layout.item_pankou_title, null));
        }
        return new Pankou3ViewHolder(inflater.inflate(R.layout.item_pankou3, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case IS_TITLE:

                break;
            default:
                ((Pankou3ViewHolder) holder).bindData(allList.get(position), position);
                break;
        }
    }


    @Override
    public int getItemViewType(int position) {
        FPankouEntity entity = allList.get(position);
        return entity.viewholder_type;
    }

    @Override
    public int getItemCount() {
        return allList.size();
    }


    class Pankou3ViewHolder extends RecyclerView.ViewHolder {
        private TextView pankouNo;

        public Pankou3ViewHolder(View itemView) {
            super(itemView);
            pankouNo = itemView.findViewById(R.id.pankouNo);
        }

        public void bindData(FPankouEntity entity, int position) {
            pankouNo.setText(position + "");
        }
    }

    class PankouViewTitleHolder extends RecyclerView.ViewHolder {

        public PankouViewTitleHolder(View itemView) {
            super(itemView);

        }
    }
}
