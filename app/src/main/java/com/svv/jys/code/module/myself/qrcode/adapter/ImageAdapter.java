package com.svv.jys.code.module.myself.qrcode.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.MBaseAdapter;
import com.svv.jys.code.common.utils.GlideUtils;

import java.util.List;

/**
 * Created by js on 2018/5/22.
 */

public class ImageAdapter extends MBaseAdapter<ImageAdapter.ViewHolder> {
    private Context context;
    private List<String> list;
    public OnRecyclerViewLongItemClickListener mOnLongItemClickListener;
    public ImageAdapter(Context context,List<String> list){
        this.context=context;
        this.list=list;
    }
    public interface OnRecyclerViewLongItemClickListener {
        void onLongItemClick(View view, int position);
    }
    public void setOnLongItemClickListener(OnRecyclerViewLongItemClickListener listener) {
        this.mOnLongItemClickListener = listener;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_img,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        GlideUtils.loadImageDefult(context,list.get(position),holder.image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnLongItemClickListener != null) {
                    mOnLongItemClickListener.onLongItemClick(view, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        public ViewHolder(View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.image);
        }
    }
}
