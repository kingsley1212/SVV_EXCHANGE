package com.svv.jys.code.module.business.tradechat.deep.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.MBaseAdapter;
import com.svv.jys.code.common.entity.TradeListEntity;
import com.svv.jys.code.common.utils.ResourceUtils;
import com.svv.jys.code.common.utils.ToolUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 盘口买出
 * Created by Administrator on 2018/5/7 0007.
 */

public class PankouAdapter extends MBaseAdapter<PankouAdapter.ViewHolder> {

    private LayoutInflater layoutInflater;
    private List<TradeListEntity.Trade> list;
    public MOnItemClickListener onItemClick;
    private int width;
    private double totalNum = -1;
    private Context mContext;

    public PankouAdapter(Context context) {
        this.layoutInflater = LayoutInflater.from(context);
        mContext = context;
        list = new ArrayList<>();
    }


    public interface MOnItemClickListener {
        void onItemClick(String num, String price);
    }

    public void setOnItemClick(MOnItemClickListener onItemClick) {
        this.onItemClick = onItemClick;
    }

    public List<TradeListEntity.Trade> getList() {
        return list;
    }

    public void setList(List<TradeListEntity.Trade> list) {
        handleList(list);
        totalNum = -1;
    }

    public void handleList(List<TradeListEntity.Trade> list) {
        Collections.sort(list);
        if (list.size() > 9) {
            this.list.clear();
            this.list.addAll(list.subList(0, 9));
        } else {
            this.list = list;
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(layoutInflater.inflate(R.layout.item_trade_deep, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        ((ViewHolder) holder).bindData(position, list.get(position));
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView pankouNo, pankouprice, pankounum;
        View bg_view;

        public ViewHolder(View itemView) {
            super(itemView);
            pankouNo = itemView.findViewById(R.id.pankouNo);
            pankouprice = itemView.findViewById(R.id.pankouprice);
            pankounum = itemView.findViewById(R.id.pankounum);
            bg_view = itemView.findViewById(R.id.bg_view);
        }

        public void bindData(final int position, final TradeListEntity.Trade trade) {


            if (!TradeListEntity.nil.equals(trade.getNum())) {
                String num = getNum(position);

                final double matteWidth = getMatteWidth(Double.parseDouble(num));
                if (width <= 0) {
                    width = itemView.getMeasuredWidth();
                }
                ViewGroup.LayoutParams layoutParams = bg_view.getLayoutParams();
                layoutParams.width = (int) (width * matteWidth);
                bg_view.setLayoutParams(layoutParams);
                pankounum.setText(num);
            } else {
                ViewGroup.LayoutParams layoutParams = bg_view.getLayoutParams();
                layoutParams.width = 0;
                bg_view.setLayoutParams(layoutParams);
                pankounum.setText(TradeListEntity.nil);
            }
            pankouNo.setText(String.valueOf(position + 1));
            if (!TradeListEntity.nil.equals(trade.getPrice())) {
                ViewGroup.LayoutParams layoutParams = pankouprice.getLayoutParams();
                layoutParams.width = width - ResourceUtils.dip2px2(mContext, 102);
                pankouprice.setLayoutParams(layoutParams);
                pankouprice.setText(ToolUtils.SzzcFormat(trade.getPrice()));
            } else {
                pankouprice.setText(TradeListEntity.nil);
            }

        }

    }


    public String getNum(int position) {
        String value = "0";
        for (int i = 0; i <= position; i++) {
            value = ToolUtils.add(list.get(i).getNum(), value);
        }
        return value;
    }


    public double getMatteWidth(double num) {
        if (totalNum < 0) {
            getTotalNum();
        }
        if (num == 0) {
            return 0;
        }
        return num / totalNum;
    }

    public void getTotalNum() {
        String value = "0";
        for (int i = list.size() - 1; i >= 0; i--) {
            if (list.get(i).getNum().equals(TradeListEntity.nil)) {
                totalNum = Double.parseDouble(value);
                return;
            }
            value = ToolUtils.add(list.get(i).getNum(), value);
        }
        totalNum = Double.parseDouble(value);
    }
}
