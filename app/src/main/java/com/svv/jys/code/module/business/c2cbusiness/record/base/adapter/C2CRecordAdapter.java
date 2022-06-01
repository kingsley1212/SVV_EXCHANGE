package com.svv.jys.code.module.business.c2cbusiness.record.base.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.MBaseAdapter;
import com.svv.jys.code.common.entity.C2cRecordEntity;
import com.svv.jys.code.common.utils.ToolUtils;

import java.util.List;

/**
 * Created by 74099 on 2018/7/11.
 */

public class C2CRecordAdapter extends MBaseAdapter<C2CRecordAdapter.C2CRecordViewHolder> {
    private List<C2cRecordEntity> mList;
    private Context mContext;
    private OnRecordClick mOnRecordClick;

    public C2CRecordAdapter(List<C2cRecordEntity> list, Context context) {
        mList = list;
        mContext = context;
    }


    public void setOnRecordClick(OnRecordClick onRecordClick) {
        mOnRecordClick = onRecordClick;
    }

    public interface OnRecordClick {
        void doCancel(String id, View v);

        void doLook(C2cRecordEntity c2cRecordEntity);
    }

    @Override
    public C2CRecordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new C2CRecordViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_c2c_record, parent, false));
    }

    @Override
    public void onBindViewHolder(final C2CRecordViewHolder holder, final int position) {
        if ("1".equals(mList.get(position).getType())) {
            holder.tv_c2c_record_buy_sell_type.setText(R.string.buy1);
            holder.tv_c2c_record_buy_sell_type.setSelected(false);
        } else if ("2".equals(mList.get(position).getType())) {
            holder.tv_c2c_record_buy_sell_type.setText(R.string.sell1);
            holder.tv_c2c_record_buy_sell_type.setSelected(true);
        }

        holder.tv_c2c_record_market.setText(mList.get(position).getCoin());
        holder.tv_c2c_record_time.setText(ToolUtils.timeStamp2String(mList.get(position).getAdd_time(), "yyyy-MM-dd"));
        holder.tv_c2c_record_state.setText(mList.get(position).getStatus_name());

        if ("2".equals(mList.get(position).getStatus())) {
            holder.ll_c2c_gone.setVisibility(View.GONE);
        } else if ("0".equals(mList.get(position).getStatus())) {
            holder.tv_c2c_record_remove.setVisibility(View.VISIBLE);
            holder.tv_c2c_record_look.setVisibility(View.VISIBLE);
        } else if ("1".equals(mList.get(position).getStatus())) {
            holder.tv_c2c_record_remove.setVisibility(View.GONE);
            holder.tv_c2c_record_look.setVisibility(View.VISIBLE);
        } else if ("3".equals(mList.get(position).getStatus())) {
            holder.ll_c2c_gone.setVisibility(View.GONE);
        }
        holder.tv_c2c_record_num.setText(mList.get(position).getNum());
        holder.tv_c2c_record_price.setText(mList.get(position).getPrice());
        //撤单
        holder.tv_c2c_record_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnRecordClick != null) {
                    mOnRecordClick.doCancel(mList.get(position).getId(), holder.tv_c2c_record_remove);
                }
            }
        });

        //查看
        holder.tv_c2c_record_look.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnRecordClick != null) {
                    mOnRecordClick.doLook(mList.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class C2CRecordViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_c2c_record_buy_sell_type, tv_c2c_record_market, tv_c2c_record_time, tv_c2c_record_state,
                tv_c2c_record_remove, tv_c2c_record_look, tv_c2c_record_price, tv_c2c_record_num;
        private LinearLayout ll_c2c_gone;

        public C2CRecordViewHolder(View itemView) {
            super(itemView);
            tv_c2c_record_buy_sell_type = itemView.findViewById(R.id.tv_c2c_record_buy_sell_type);
            tv_c2c_record_market = itemView.findViewById(R.id.tv_c2c_record_market);
            tv_c2c_record_time = itemView.findViewById(R.id.tv_c2c_record_time);
            tv_c2c_record_state = itemView.findViewById(R.id.tv_c2c_record_state);
            tv_c2c_record_remove = itemView.findViewById(R.id.tv_c2c_record_remove);
            tv_c2c_record_look = itemView.findViewById(R.id.tv_c2c_record_look);
            tv_c2c_record_num = itemView.findViewById(R.id.tv_c2c_record_num);
            tv_c2c_record_price = itemView.findViewById(R.id.tv_c2c_record_price);
            ll_c2c_gone = itemView.findViewById(R.id.ll_c2c_gone);
        }
    }
}
