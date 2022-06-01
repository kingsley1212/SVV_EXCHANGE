package com.svv.jys.code.module.business.coinbusiness.base.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.MBaseAdapter;
import com.svv.jys.code.common.entity.EntrustEntity;
import com.svv.jys.code.common.utils.ToolUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 委托列表
 * Created by Administrator on 2018/5/7 0007.
 */

public class CoinDelegateAdapter extends MBaseAdapter<CoinDelegateAdapter.DelegateViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    private List<EntrustEntity.RowsBean> list;
    public DoTradeDuelInterface doTradeDuelInterface;


    public interface DoTradeDuelInterface {
        void doTradeRevoke(String id, int position);
    }

    public void setDoTradeDuelInterface(DoTradeDuelInterface doTradeDuelInterface) {
        this.doTradeDuelInterface = doTradeDuelInterface;
    }

    public CoinDelegateAdapter(Context context) {
        this.list = new ArrayList<>();
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void setData(List<EntrustEntity.RowsBean> list) {
        this.list = list;
    }

    public void addData(List<EntrustEntity.RowsBean> list) {
        list.addAll(list == null ? new ArrayList<EntrustEntity.RowsBean>() : list);
    }

    @Override
    public DelegateViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DelegateViewHolder(inflater.inflate(R.layout.item_coin_entrust, parent, false));
    }


    @Override
    public void onBindViewHolder(DelegateViewHolder holder, final int position) {
        holder.bindData(list.get(position), position);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class DelegateViewHolder extends RecyclerView.ViewHolder {
        TextView time_tv, price_tv, num_tv, a_price_tv, done_num_tv, type_tv, coin_name_tv, wt_type_tv, volume_tv, cx_tv;
        TextView price_title, num_title_tv, done_num_title, a_price_title, volume_title;

        public DelegateViewHolder(View itemView) {
            super(itemView);
            type_tv = itemView.findViewById(R.id.type_tv);
            num_tv = itemView.findViewById(R.id.num_tv);
            done_num_tv = itemView.findViewById(R.id.done_num_tv);
            price_tv = itemView.findViewById(R.id.price_tv);
            a_price_tv = itemView.findViewById(R.id.a_price_tv);
            time_tv = itemView.findViewById(R.id.time_tv);
            wt_type_tv = itemView.findViewById(R.id.wt_type_tv);
            volume_tv = itemView.findViewById(R.id.volume_tv);
            cx_tv = itemView.findViewById(R.id.cx_tv);
            coin_name_tv = itemView.findViewById(R.id.coin_name_tv);
            price_title = itemView.findViewById(R.id.price_title);
            done_num_title = itemView.findViewById(R.id.done_num_title);
            a_price_title = itemView.findViewById(R.id.a_price_title);
            volume_title = itemView.findViewById(R.id.volume_title);
            num_title_tv = itemView.findViewById(R.id.num_title_tv);
        }

        public void bindData(final EntrustEntity.RowsBean bean, final int position) {
            coin_name_tv.setText(bean.getMarket().replace("_", "/").toUpperCase());
            String buyName = bean.getMarket().split("_")[1].toUpperCase(), sellName = bean.getMarket().split("_")[0].toUpperCase();

            price_title.setText(String.format("%s(%s)", context.getString(R.string.entrust_tv5), buyName));
            num_title_tv.setText(String.format("%s(%s)", context.getString(R.string.entrust_tv1), sellName));
            volume_title.setText(String.format("%s(%s)", context.getString(R.string.entrust_tv4), buyName));
            a_price_title.setText(String.format("%s(%s)", context.getString(R.string.entrust_tv7), buyName));
            done_num_title.setText(String.format("%s(%s)", context.getString(R.string.entrust_tv2), sellName));

            price_tv.setText(bean.getPrice());
            num_tv.setText(bean.getNum());
            volume_tv.setText(bean.getDeal_total());
            time_tv.setText(ToolUtils.timeStamp2String(bean.getAdd_time(), "MM/dd HH:mm"));
            a_price_tv.setText(ToolUtils.division(bean.getDeal_total(), bean.getDeal(), 8));
            done_num_tv.setText(bean.getDeal());
            if (Double.parseDouble(bean.getDeal()) > 0) {
//                wt_type_tv.setSelected(false);
                wt_type_tv.setTextColor(Color.parseColor("#1f7bbc"));
                wt_type_tv.setText(context.getString(R.string.deal_has));
            } else {
                if (bean.getType().equals("2")) {
                    wt_type_tv.setTextColor(Color.parseColor("#ff676a"));
                } else {
                    wt_type_tv.setTextColor(Color.parseColor("#04bf85"));
                }
                wt_type_tv.setSelected(true);
                wt_type_tv.setText(context.getString(R.string.deal_yet));
            }
            if (bean.getType().equals("2")) {
                type_tv.setText(context.getString(R.string.sell1));
                type_tv.setSelected(true);
                done_num_tv.setTextColor(ContextCompat.getColor(context,R.color.c_ff5755));
            } else {
                type_tv.setText(context.getString(R.string.buy1));
                type_tv.setSelected(false);
                done_num_tv.setTextColor(ContextCompat.getColor(context,R.color.c_1ede89));
            }

            cx_tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (doTradeDuelInterface != null) {
                        doTradeDuelInterface.doTradeRevoke(bean.getId(), position);
                    }
                }
            });

        }
    }

}
