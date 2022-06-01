package com.svv.jys.code.module.business.barbusiness.base.entrust.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.svv.jys.R;
import com.svv.jys.code.common.base.MBaseAdapter;

/**
 * Created by js on 2018/5/10.
 */

public class CurrentEntrustAdapter extends MBaseAdapter<CurrentEntrustAdapter.ViewHolder> {
    private Context context;
    public CurrentEntrustAdapter(Context context){
        this.context=context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_current_entrust,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 3;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
