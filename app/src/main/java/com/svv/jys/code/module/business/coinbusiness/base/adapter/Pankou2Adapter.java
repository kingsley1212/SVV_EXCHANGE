package com.svv.jys.code.module.business.coinbusiness.base.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.MBaseAdapter;
import com.svv.jys.code.common.entity.TradeListEntity;
import com.svv.jys.code.common.utils.ResourceUtils;
import com.svv.jys.code.common.utils.ToolUtils;

import java.util.List;

/**
 * 盘口数据列表
 * Created by Administrator on 2018/5/7 0007.
 */

public class Pankou2Adapter extends MBaseAdapter<RecyclerView.ViewHolder> {

    private LayoutInflater inflater;
    private List<TradeListEntity.Trade> list;
    private int width;
    private double totalNum = -1;
    public MOnItemClickListener onItemClick;
    public Context context;

    public Pankou2Adapter(List<TradeListEntity.Trade> list, Context context) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        handleList(list);
    }

    public void setOnItemClick(MOnItemClickListener onItemClick) {
        this.onItemClick = onItemClick;
    }

    public interface MOnItemClickListener {
        void onItemClick(String  num, String  price);
    }
    public List<TradeListEntity.Trade> getList() {
        return list;
    }

    public void setList(List<TradeListEntity.Trade> list) {
        handleList(list);
        totalNum = -1;
    }

    public void handleList(List<TradeListEntity.Trade> list) {
        while (list.size() < 5) {
            TradeListEntity.Trade trade = new TradeListEntity.Trade();
            trade.price = TradeListEntity.nil;
            trade.num = TradeListEntity.nil;
            trade.percent = TradeListEntity.nil;
            list.add(trade);
        }
//        Collections.sort(list);
//        Collections.reverse(list);
        if (list.size() > 5) {
            this.list.clear();
            this.list.addAll(list.subList(0, 5));
        } else {
            this.list = list;
        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Pankou2ViewHolder(inflater.inflate(R.layout.item_pankou2, parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((Pankou2ViewHolder) holder).bindData(list.get(position), position);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    class Pankou2ViewHolder extends RecyclerView.ViewHolder {
        public TextView pankouNo, pankouprice, pankounum;
        public View bg_view;

        public Pankou2ViewHolder(View itemView) {
            super(itemView);
            pankouNo = itemView.findViewById(R.id.pankouNo);
            pankouprice = itemView.findViewById(R.id.pankouprice);
            pankounum = itemView.findViewById(R.id.pankounum);
            bg_view = itemView.findViewById(R.id.bg_view);
        }

        public void bindData(final TradeListEntity.Trade trade, final int position) {
            pankouNo.setText(position + 1 + "");
            if(width <= 0){ width = itemView.getMeasuredWidth();}
            if (!TradeListEntity.nil.equals(trade.getNum())) {
                String num = getNum(position);
                final double matteWidth = getMatteWidth(Double.parseDouble(num));
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) bg_view.getLayoutParams();
                layoutParams.leftMargin = width-(int) (width * matteWidth);
                layoutParams.width = (int) (width * matteWidth);
                layoutParams.alignWithParent=true;
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);

                bg_view.setLayoutParams(layoutParams);
                pankounum.setText(num);
            } else {
                pankounum.setText(TradeListEntity.nil);
                ViewGroup.LayoutParams layoutParams = bg_view.getLayoutParams();
                layoutParams.width = 0;
                bg_view.setLayoutParams(layoutParams);
            }
            if (!TradeListEntity.nil.equals(trade.getPrice())) {
                ViewGroup.LayoutParams layoutParams = pankouprice.getLayoutParams();
                layoutParams.width = width - ResourceUtils.dip2px2(context,102);
                pankouprice.setLayoutParams(layoutParams);
                pankouprice.setText(ToolUtils.SzzcFormat(trade.getPrice()));
            } else {
                pankouprice.setText(TradeListEntity.nil);
            }
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!TradeListEntity.nil.equals(trade.getNum()))
                        onItemClick.onItemClick(pankounum.getText().toString(),pankouprice.getText().toString());
                }
            });
        }
    }

    public String getNum(int position){
        String value = "0";
        for (int i = 0;i<=position;i++){
            value = ToolUtils.add(list.get(i).getNum(),value);
        }
        return value;
    }

    public double getMatteWidth(double num) {
        if (totalNum < 0){
            getTotalNum();
        }
        if(num == 0){
            return 0;
        }
        return num/totalNum;
    }

    public void getTotalNum(){
        String value = "0";
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getNum().equals(TradeListEntity.nil)){
                totalNum = Double.parseDouble(value);
                return;
            }
            value = ToolUtils.add(list.get(i).getNum(), value);
        }
        totalNum = Double.parseDouble(value);
    }
}
