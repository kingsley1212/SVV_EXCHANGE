package com.svv.jys.code.common.view.popup;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.MBaseAdapter;
import com.svv.jys.code.common.entity.AdvSettingEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by js on 2018/8/18.
 */

public class PopPaymentAdapter extends MBaseAdapter<PopPaymentAdapter.ViewHolder>{
    private Context context;
    private List<AdvSettingEntity.IncomeBean.RowsBean> list;
    private List<AdvSettingEntity.IncomeBean.RowsBean> select=new ArrayList<>();
    public PopPaymentAdapter(Context context, List<AdvSettingEntity.IncomeBean.RowsBean> list){
        this.context=context;
        this.list=list;
    }

    public void setOnSelectPay(OnSelectPay onSelectPay) {
        this.onSelectPay = onSelectPay;
    }

    OnSelectPay onSelectPay;
    public interface OnSelectPay{
        void onSelect(String payname,String payment);
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_pop_payment,parent,false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
            holder.tv_select.setText(list.get(position).getName());
            if (list.size()==position+1){
                holder.view.setVisibility(View.GONE);
            }
            holder.tv_select.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    holder.tv_select.setSelected(!list.get(position).isSelect());
                    list.get(position).setSelect(!list.get(position).isSelect());
                    if (list.get(position).isSelect()){
                        select.add(list.get(position));
                    }else {
                        select.remove(list.get(position));
                    }
                   if (select.size()!=0){
                        StringBuffer sb=new StringBuffer();
                        for (int i=0;i<select.size();i++){
                            if (i==0){
                                sb.append(select.get(i).getName());
                            }else {
                                sb.append(",").append(select.get(i).getName());
                            }
                        }
                       onSelectPay.onSelect(sb.toString(),getPayMethod(select));
                   }else {
                        onSelectPay.onSelect("","");
                   }
                    notifyDataSetChanged();
                }
            });
    }

    public String getPayMethod(List<AdvSettingEntity.IncomeBean.RowsBean> currencyListBeans) {
        StringBuffer paymethod = new StringBuffer();
        for (int i = 0; i < currencyListBeans.size(); i++) {
            if (i == 0) {
                paymethod.append("{\"" + currencyListBeans.get(i).getCode() + "\"").append(":\"" + currencyListBeans
                        .get(i).getName() + "\"");
            } else {
                paymethod.append(",\"" + currencyListBeans.get(i).getCode() + "\"").append(":\"" + currencyListBeans
                        .get(i).getName() + "\"");
            }
        }
        paymethod.append("}");
        return paymethod.toString();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_select;
        private View view;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_select=itemView.findViewById(R.id.tv_select);
            view=itemView.findViewById(R.id.view);
        }
    }
}
