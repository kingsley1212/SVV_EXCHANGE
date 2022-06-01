package com.svv.jys.code.module.fabi.screen.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.constant.ACEConstant;
import com.svv.jys.code.common.db.nativecache.ACache;
import com.svv.jys.code.common.entity.AdvSettingEntity;

import java.util.List;

/**
 * Created by js on 2018/5/19.
 */

public class CountryAdapter extends BaseAdapter {
    private Context context;
    private List<AdvSettingEntity.CountryBean> entities;
    private String value = "-1";

    public void setValue(String value) {
        this.value = value;
        notifyDataSetChanged();
    }

    public CountryAdapter(Context context, List<AdvSettingEntity.CountryBean> entities) {
        this.context = context;
        this.entities = entities;
    }

    public void setData(List<AdvSettingEntity.CountryBean> entities) {
        this.entities = entities;
        notifyDataSetChanged();
    }

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
        View view;
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
        if(value.equals(entities.get(position).getId())){
            viewHolder.item_tv.setSelected(true);
        }else {
            viewHolder.item_tv.setSelected(false);
        }
        if (ACache.get(context).getAsString(ACEConstant.CURRENTLANGUAGE_ID).equals(ACEConstant.LANGUAGE_ENGLISH)){
            viewHolder.item_tv.setText(entities.get(position).getName());
        }else {
            viewHolder.item_tv.setText(entities.get(position).getChinese());
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClick != null) {
                    onItemClick.onItemClick(position, entities.get(position).getId());
                }
            }
        });
        return view;
    }

    private class ViewHolder {
        TextView item_tv;
    }
}
