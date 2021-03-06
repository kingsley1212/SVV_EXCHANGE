package com.svv.jys.code.common.base;

import android.support.v7.widget.RecyclerView;

/**
 * Created by lzj on 2017/8/24.
 */

public abstract class MBaseAdapter<T extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<T> {

    public interface MOnItemClickListener {
         void onItemClick(int position, Object o);
    }

    public MOnItemClickListener onItemClick;

    public MOnItemClickListener getOnItemClick() {
        return onItemClick;
    }

    public void setOnItemClick(MOnItemClickListener onItemClick) {
        this.onItemClick = onItemClick;
    }

    public void fullData(){

    }
}
