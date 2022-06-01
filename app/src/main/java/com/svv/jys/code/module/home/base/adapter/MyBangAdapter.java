package com.svv.jys.code.module.home.base.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.MBaseAdapter;
import com.svv.jys.code.common.entity.FBangDataEntity;
import com.svv.jys.code.common.utils.GlideUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 几类排行榜
 * Created by Administrator on 2018/5/5 0005.
 */

public class MyBangAdapter extends MBaseAdapter<MyBangAdapter.BangViewHolder> {

    private Context context;
    private List<FBangDataEntity> allList;
    private LayoutInflater inflater;

    public MyBangAdapter(Context context, List<FBangDataEntity> allList) {
        this.context = context;
        this.allList = allList;
        if (this.allList == null) {
            this.allList = new ArrayList<>();
        }
        inflater = LayoutInflater.from(context);
    }

    public void setData(List<FBangDataEntity> allList){
        this.allList = allList;
        if (this.allList == null) {
            this.allList = new ArrayList<>();
        }
        notifyDataSetChanged();
    }
    @Override
    public BangViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BangViewHolder(inflater.inflate(R.layout.item_bang, null));
    }

    @Override
    public void onBindViewHolder(BangViewHolder holder, int position) {
        holder.bindData(allList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return allList.size();
    }

    class BangViewHolder extends RecyclerView.ViewHolder {

        private ImageView bangImg;
        private TextView bangValue1, bangValue2, bangValue3, bangValue4, bangUpDown;

        public BangViewHolder(View itemView) {
            super(itemView);
            bangImg = itemView.findViewById(R.id.bangImg);
            bangValue1 = itemView.findViewById(R.id.bangValue1);
            bangValue2 = itemView.findViewById(R.id.bangValue2);
            bangValue3 = itemView.findViewById(R.id.bangValue3);
            bangValue4 = itemView.findViewById(R.id.bangValue4);
            bangUpDown = itemView.findViewById(R.id.bangUpDown);
        }

        public void bindData(FBangDataEntity entity, int position) {
            GlideUtils.loadImageDefult(context, "", bangImg);
        }
    }
}
