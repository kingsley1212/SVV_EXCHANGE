package com.svv.jys.code.module.myself.myasset.countdetail.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.MBaseAdapter;
import com.svv.jys.code.common.entity.LtBorrowRecord;
import com.svv.jys.code.common.utils.ToolUtils;

import java.util.List;

/**
 * Created by js on 2018/5/26.
 */

public class LtBorrowRecordAdapter extends MBaseAdapter<LtBorrowRecordAdapter.ViewHolder>{
    private List<LtBorrowRecord.RowsBean> list;
    private Context context;

    public void setReimbursement(ReimbursementImpl reimbursement) {
        this.reimbursement = reimbursement;
    }

    private ReimbursementImpl reimbursement;
    public LtBorrowRecordAdapter(Context context, List<LtBorrowRecord.RowsBean> list){
        this.context=context;
        this.list=list;
    }
    public interface ReimbursementImpl{
        void toReimbursement(int position);
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_borrow_record,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        LtBorrowRecord.RowsBean bean=list.get(position);
        holder.tv_borrow_time.setText(ToolUtils.timeStamp2String(bean.getAdd_time(),"yyyy-MM-dd HH:mm:ss"));
        holder.tv_borrow_name.setText(context.getString(R.string.myassetact_list_item1)+"："+bean.getCoin().toUpperCase());
        holder.tv_borrow_num.setText(context.getString(R.string.num)+"："+bean.getNum());
        holder.tv_borrow_rote.setText(context.getString(R.string.assat15)+"："+bean.getAccrual());
        String yihuan="0.00000000";
        if (ToolUtils.String2Double(bean.getAccrual_deal())==0.00&&ToolUtils.String2Double(bean.getDeal())==0.00){
            yihuan="0.00000000";
        }else if (ToolUtils.String2Double(bean.getAccrual_deal())==0.00&&ToolUtils.String2Double(bean.getDeal())!=0.00){
            yihuan=bean.getAccrual_deal();
        }else if (ToolUtils.String2Double(bean.getAccrual_deal())!=0.00&&ToolUtils.String2Double(bean.getDeal())==0.00){
            yihuan=bean.getDeal();
        }else if (ToolUtils.String2Double(bean.getAccrual_deal())!=0.00&&ToolUtils.String2Double(bean.getDeal())!=0.00){
            yihuan=ToolUtils.add(bean.getAccrual_deal(),bean.getDeal());
        }
        holder.tv_borrow_already.setText(context.getString(R.string.assat16)+"："+yihuan);
        if (bean.getStatus().equals("1")){
            holder.tv_borrowtorefund.setText(context.getString(R.string.assat16));
        }else {
            holder.tv_borrowtorefund.setText(context.getString(R.string.assat8));
            holder.tv_borrowtorefund.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    reimbursement.toReimbursement(position);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_borrowtorefund,tv_borrow_time,tv_borrow_num,tv_borrow_name,tv_borrow_rote,tv_borrow_already;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_borrowtorefund=itemView.findViewById(R.id.tv_borrowtorefund);
            tv_borrow_time=itemView.findViewById(R.id.tv_borrow_time);
            tv_borrow_num=itemView.findViewById(R.id.tv_borrow_num);
            tv_borrow_name=itemView.findViewById(R.id.tv_borrow_name);
            tv_borrow_rote=itemView.findViewById(R.id.tv_borrow_rote);
            tv_borrow_already=itemView.findViewById(R.id.tv_borrow_already);
        }
    }
}
