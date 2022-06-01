package com.svv.jys.code.module.business.barbusiness.base.adapter;

import android.content.Context;
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

public class BarDelegateAdapter extends MBaseAdapter<BarDelegateAdapter.DelegateViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private List<EntrustEntity.RowsBean> list;


    public BarDelegateAdapter.DoTradeDuelInterface doTradeDuelInterface;

    public interface DoTradeDuelInterface {
        void doTradeRevoke(String id);
    }

    public void setDoTradeDuelInterface(BarDelegateAdapter.DoTradeDuelInterface doTradeDuelInterface) {
        this.doTradeDuelInterface = doTradeDuelInterface;
    }

    public BarDelegateAdapter(List<EntrustEntity.RowsBean> list, Context context) {
        this.list = list;
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
    public BarDelegateAdapter.DelegateViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BarDelegateAdapter.DelegateViewHolder(inflater.inflate(R.layout.item_entrust2, null));
    }


    @Override
    public void onBindViewHolder(BarDelegateAdapter.DelegateViewHolder holder, final int position) {
        holder.bindData(list.get(position), position);

    }

    public void remove(EntrustEntity.RowsBean rowsBean) {
        list.remove(rowsBean);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class DelegateViewHolder extends RecyclerView.ViewHolder {
        TextView tv_market1, tv_market2, tv_entrust_price, tv_entrust_num, tv_entrust_total, tv_entrust_yijiaoyi, tv_entrust_type,
                tv_entrust_status, tv_entrust_time, tv_entrust_undo;
        TextView iv_comlete;

        public DelegateViewHolder(View itemView) {
            super(itemView);
            iv_comlete = itemView.findViewById(R.id.iv_comlete);
            tv_market1 = itemView.findViewById(R.id.tv_market1);
            tv_market2 = itemView.findViewById(R.id.tv_market2);
            tv_entrust_price = itemView.findViewById(R.id.tv_entrust_price);
            tv_entrust_num = itemView.findViewById(R.id.tv_entrust_num);
            tv_entrust_total = itemView.findViewById(R.id.tv_entrust_total);
            tv_entrust_yijiaoyi = itemView.findViewById(R.id.tv_entrust_yijiaoyi);
            tv_entrust_type = itemView.findViewById(R.id.tv_entrust_type);
            tv_entrust_status = itemView.findViewById(R.id.tv_entrust_status);
            tv_entrust_time = itemView.findViewById(R.id.tv_entrust_time);
            tv_entrust_undo = itemView.findViewById(R.id.tv_entrust_undo);
        }

        public void bindData(final EntrustEntity.RowsBean bean, final int position) {
            tv_market1.setText(bean.getMarket().toUpperCase().split("_")[0]);
            tv_market2.setText(bean.getMarket().toUpperCase().split("_")[1]);
            tv_entrust_price.setText(context.getString(R.string.barbusinessfragment_price)+bean.getPrice());
            tv_entrust_num.setText(context.getString(R.string.barbusinessfragment_number)+ bean.getNum());
            tv_entrust_total.setText(context.getString(R.string.barbusinessfragment_wt_all)+ bean.getMum());
            tv_entrust_yijiaoyi.setText(context.getString(R.string.barbusinessfragment_yi_business)+ bean.getDeal());
            tv_entrust_type.setText(bean.getType_name());
            if ("0".equals(bean.getStatus())) {
                iv_comlete.setVisibility(View.GONE);
                tv_entrust_undo.setVisibility(View.VISIBLE);
            } else if ("2".equals(bean.getStatus())) {
                tv_entrust_undo.setVisibility(View.GONE);
                iv_comlete.setVisibility(View.GONE);
            } else {
                tv_entrust_undo.setVisibility(View.GONE);
                iv_comlete.setVisibility(View.VISIBLE);
            }
            tv_entrust_undo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    entrustManager.undoEntrust(position,view);
                    if (doTradeDuelInterface != null) {
                        doTradeDuelInterface.doTradeRevoke(bean.getId());
                    }
                }
            });
            tv_entrust_status.setText(context.getString(R.string.barbusinessfragment_yi_state) + bean.getStatus_name());
            tv_entrust_time.setText(context.getString(R.string.barbusinessfragment_yi_time) + ToolUtils.timeStamp2String(bean.getAdd_time(), "yyyy-MM-dd HH:mm:ss"));
        }
    }


}
