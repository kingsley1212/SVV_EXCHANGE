package com.svv.jys.code.module.myself.coin.coinlist.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.coin.coinlist.CoinListAct;

import java.util.List;

/**
 * Created by LB on 2018/3/2.
 */

public class CoinListAdapter extends BaseAdapter implements SectionIndexer {
    private List<String> list = null;
    private Context mContext;

    public CoinListAdapter(Context mContext, List<String> list) {
        this.mContext = mContext;
        this.list = list;
    }

    /**
     * 当ListView数据发生变化时,调用此方法来更新ListView
     *
     * @param list
     */
    public void updateListView(List<String> list) {
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
         String mContent = list.get(position).toUpperCase();
        if (view == null) {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.item_coin_list, null);
            viewHolder.title = (TextView) view.findViewById(R.id.title);
            viewHolder.content = (TextView) view.findViewById(R.id.content);
            viewHolder.view = view.findViewById(R.id.view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        //根据position获取分类的首字母的Char ascii值
        int section = getSectionForPosition(position);

        //如果当前位置等于该分类首字母的Char的位置 ，则认为是第一次出现
        if (position == getPositionForSection(section)) {
            viewHolder.title.setVisibility(View.VISIBLE);
            viewHolder.title.setText(String.valueOf(mContent.charAt(0)));
        } else {
            viewHolder.title.setVisibility(View.GONE);
        }

        if (IsAtLastPosition(position)){
            viewHolder.view.setVisibility(View.GONE);
        }else {
            viewHolder.view.setVisibility(View.VISIBLE);
        }
        viewHolder.content.setText(list.get(position).toUpperCase());
        viewHolder.content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ToolUtils.isFastClick(v.getId())){
                    ((CoinListAct) mContext).OnItemClick(position);
                }

            }
        });
        return view;

    }


    final static class ViewHolder {
        TextView title;
        TextView content;
        View view;
    }


    /**
     * 根据ListView的当前位置获取分类的首字母的Char ascii值
     */
    public int getSectionForPosition(int position) {
        return list.get(position).toUpperCase().charAt(0);
    }

    /**
     * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
     */
    public int getPositionForSection(int section) {
        for (int i = 0; i < getCount(); i++) {
            char firstChar =  list.get(i).toUpperCase().charAt(0);
            if (firstChar == section) {
                return i;
            }
        }

        return -1;
    }

    public boolean IsAtLastPosition(int position){
        if(position == list.size() -1){
            return true;
        }
        if(getPositionForSection(getSectionForPosition(position +1)) == position+1){
            return true;
        }
        return false;
    }


    @Override
    public Object[] getSections() {
        return null;
    }
}
