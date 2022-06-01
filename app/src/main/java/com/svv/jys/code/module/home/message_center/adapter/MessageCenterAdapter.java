package com.svv.jys.code.module.home.message_center.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.MBaseAdapter;
import com.svv.jys.code.common.entity.MessageCenterEntity;
import com.svv.jys.code.common.utils.ToolUtils;

import java.util.List;

public class MessageCenterAdapter extends MBaseAdapter<MessageCenterAdapter.ViewHolder> {

    private Context mContext;
    private List<MessageCenterEntity> mList;
    public LayoutInflater inflater;

    public MessageCenterAdapter(Context context, List<MessageCenterEntity> list) {
        this.mContext = context;
        this.mList = list;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MessageCenterAdapter.ViewHolder(inflater.inflate(R.layout.item_message_center, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final MessageCenterEntity entity = mList.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ToolUtils.isFastClick(v.getId())){
                    onItemClick.onItemClick(position, entity);
                }

            }
        });
        holder.title.setText(entity.getTitle());
        holder.add_time.setText(entity.getAdd_time());
        if (position == mList.size() - 1) {
            holder.line1.setVisibility(View.GONE);
        } else {
            holder.line1.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, add_time;
        View line1;

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            add_time = itemView.findViewById(R.id.add_time);
            line1 = itemView.findViewById(R.id.line1);
        }
    }

}
