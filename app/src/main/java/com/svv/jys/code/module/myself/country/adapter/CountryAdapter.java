package com.svv.jys.code.module.myself.country.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.entity.CountryEntity;

import java.util.List;

/**
 * Created by LB on 2018/3/2.
 */

public class CountryAdapter extends BaseAdapter implements SectionIndexer {
    private List<CountryEntity> list = null;
    private Context mContext;

    public CountryAdapter(Context mContext, List<CountryEntity> list) {
        this.mContext = mContext;
        this.list = list;
    }

    /**
     * 当ListView数据发生变化时,调用此方法来更新ListView
     *
     * @param list
     */
    public void updateListView(List<CountryEntity> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public int getCount() {
        return this.list.size();
    }

    public Object getItem(int position) {
        return list.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup arg2) {
        ViewHolder viewHolder = null;
        final CountryEntity mContent = list.get(position);
        if (view == null) {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.item_sort, null);
            viewHolder.tvTitle = (TextView) view.findViewById(R.id.title);
            viewHolder.tvLetter = (TextView) view.findViewById(R.id.catalog);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        //根据position获取分类的首字母的Char ascii值
        int section = getSectionForPosition(position);

        //如果当前位置等于该分类首字母的Char的位置 ，则认为是第一次出现
        if (position == getPositionForSection(section)) {
            viewHolder.tvLetter.setVisibility(View.VISIBLE);
            viewHolder.tvLetter.setText(mContent.getFirst_name());
        } else {
            viewHolder.tvLetter.setVisibility(View.GONE);
        }

        viewHolder.tvTitle.setText(this.list.get(position).getChinese()+"("+list.get(position).getName()+")");

        return view;

    }


    final static class ViewHolder {
        TextView tvLetter;
        TextView tvTitle;
    }


    /**
     * 根据ListView的当前位置获取分类的首字母的Char ascii值
     */
    public int getSectionForPosition(int position) {
        return list.get(position).getFirst_name().charAt(0);
    }

    /**
     * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
     */
    public int getPositionForSection(String section) {
        if(section.equals("frequent")){
            return 0;
        }
        for (int i = 0; i < getCount(); i++) {
            String sortStr = list.get(i).getFirst_name();
            if (sortStr.equals(section)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public Object[] getSections() {
        return null;
    }

    @Override
    public int getPositionForSection(int sectionIndex) {
        for (int i = 0; i < getCount(); i++) {
            String sortStr = list.get(i).getFirst_name();
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == sectionIndex) {
                return i;
            }
        }

        return -1;
    }
}
