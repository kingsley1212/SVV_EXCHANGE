package com.svv.jys.code.module.myself.myadv.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.MBaseAdapter;
import com.svv.jys.code.common.entity.AdvListEntity;

import java.util.List;

/**
 * Created by js on 2018/7/27.
 */

public class MyAdvAdapter extends MBaseAdapter<MyAdvAdapter.ViewHolder> {
    private Context context;
    private List<AdvListEntity.RowsBean> list;

    public void setOnAdvCaoZuo(OnAdvCaoZuo onAdvCaoZuo) {
        this.onAdvCaoZuo = onAdvCaoZuo;
    }

    private OnAdvCaoZuo onAdvCaoZuo;

    public interface OnAdvCaoZuo {
        void setBianji(int position, AdvListEntity.RowsBean rowsBean);

        void setShangjia(int position, AdvListEntity.RowsBean rowsBean);
    }

    public MyAdvAdapter(Context context, List<AdvListEntity.RowsBean> list) {
        this.context = context;
        this.list = list;
    }

    public void setList(List<AdvListEntity.RowsBean> list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_adv, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        AdvListEntity.RowsBean rowsBean = list.get(position);
        holder.order_number.setText(context.getString(R.string.order_no)+rowsBean.getId());
        holder.adv_coin.setText(rowsBean.getType_name() + rowsBean.getCoin_name());
        if(rowsBean.getType().equals("0")){
            holder.adv_coin.setSelected(false);
        }else {
            holder.adv_coin.setSelected(true);
        }
        holder.adv_limit.setText(context.getString(R.string.otc_adv_limit) + rowsBean.getMin_amount() + "-" + rowsBean.getMax_amount());
        holder.adv_price.setText(rowsBean.getPrice() + rowsBean.getCurrency());
        holder.adv_num.setText(context.getString(R.string.otc_adv_num) + rowsBean.getNum());
        holder.adv_time.setText(context.getString(R.string.otc_adv_time) + rowsBean.getAdd_time());
        holder.adv_bianji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onAdvCaoZuo != null) {
                    onAdvCaoZuo.setBianji(position, list.get(position));
                }
            }
        });
        if (rowsBean.getStatus().equals("0")) {
            holder.adv_status.setText(R.string.otc_adv_status_xiajia);
            holder.adv_status.setSelected(false);
        } else {
            holder.adv_status.setText(R.string.otc_adv_status_shangjia);
            holder.adv_status.setSelected(true);
        }

        if (rowsBean.getStatus().equals("0")) {
            holder.adv_status1.setText(R.string.otc_adv_status_shangjia);

        } else {
            holder.adv_status1.setText(R.string.otc_adv_status_xiajia);
        }
        holder.adv_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAdvCaoZuo.setShangjia(position, list.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView adv_coin, adv_limit, adv_price, adv_num, adv_time, adv_status, adv_bianji, adv_status1, order_number;

        public ViewHolder(View itemView) {
            super(itemView);
            adv_coin = itemView.findViewById(R.id.adv_coin);
            adv_limit = itemView.findViewById(R.id.adv_limit);
            adv_price = itemView.findViewById(R.id.adv_price);
            adv_num = itemView.findViewById(R.id.adv_num);
            adv_time = itemView.findViewById(R.id.adv_time);
            adv_status = itemView.findViewById(R.id.adv_status);
            adv_status1 = itemView.findViewById(R.id.adv_status1);
            adv_bianji = itemView.findViewById(R.id.adv_bianji);
            order_number = itemView.findViewById(R.id.order_number);
        }
    }
}
