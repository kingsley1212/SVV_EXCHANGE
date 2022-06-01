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

public class PayAdapter  extends BaseAdapter {
    private Context context;
    private List<AdvSettingEntity.IncomeBean.RowsBean> entities;
    private String value = "-1";
    public PayAdapter(Context context, List<AdvSettingEntity.IncomeBean.RowsBean> entities) {
        this.context = context;
        this.entities = entities;
    }
    public void setData(List<AdvSettingEntity.IncomeBean.RowsBean> entities) {
        this.entities=entities;
        notifyDataSetChanged();
    }
    public void setValue(String value){
        this.value = value;
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
        if(value.equals(entities.get(position).getCode())){
            viewHolder.item_tv.setSelected(true);
        }else {
            viewHolder.item_tv.setSelected(false);
        }
        if (ACache.get(context).getAsString(ACEConstant.CURRENTLANGUAGE_ID).equals(ACEConstant.LANGUAGE_ENGLISH)){
            viewHolder.item_tv.setText(entities.get(position).getCode());
        }else {
            viewHolder.item_tv.setText(entities.get(position).getName());
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClick!=null){
                    onItemClick.onItemClick(position,entities.get(position).getCode());
                }
            }
        });
        return view;
    }

    private class ViewHolder {
        TextView item_tv;
    }
}