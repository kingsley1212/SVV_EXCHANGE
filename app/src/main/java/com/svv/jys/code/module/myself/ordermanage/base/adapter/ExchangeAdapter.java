package com.svv.jys.code.module.myself.ordermanage.base.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.svv.jys.R;

import java.util.List;

/**
 * Created by js on 2018/5/19.
 */

public class ExchangeAdapter extends BaseAdapter {
    private Context context;
    private List<String> entities;
    private String value = "-1";

    public void setValue(String value) {
        this.value = value;
        notifyDataSetChanged();
    }

    public ExchangeAdapter(Context context, List<String> entities) {
        this.context = context;
        this.entities = entities;
    }

    public void setData(List<String> entities) {
        this.entities = entities;
        notifyDataSetChanged();
    }

    public interface MOnItemClickListener {
        void onItemClick(int position, String s);
    }

    public MOnItemClickListener onItemClick;

    public MOnItemClickListener getOnItemClick() {
        return onItemClick;
    }

    public void setOnItemClick(MOnItemClickListener onItemClick) {
        this.onItemClick = onItemClick;
    }


    @Override
    public int getCount() {
        return entities.size();
    }

    @Override
    public Object getItem(int position) {
        return entities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final View view;
        ViewHolder viewHolder = null;
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_screen, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.item_tv = (TextView) view.findViewById(R.id.item_tv);

            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        if(value.equals(entities.get(position))){
            viewHolder.item_tv.setSelected(true);
        }else {
            viewHolder.item_tv.setSelected(false);
        }

        viewHolder.item_tv.setText(entities.get(position).toUpperCase());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setValue(entities.get(position));
                if (onItemClick != null) {
                    onItemClick.onItemClick(position, entities.get(position));
                }
            }
        });
        return view;
    }

    private class ViewHolder {
        TextView item_tv;
    }
}
