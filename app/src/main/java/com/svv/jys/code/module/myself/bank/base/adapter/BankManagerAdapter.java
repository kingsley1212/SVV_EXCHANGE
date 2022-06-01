package com.svv.jys.code.module.myself.bank.base.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.MBaseAdapter;
import com.svv.jys.code.common.entity.BankInfoEntity;

import java.util.List;

/**
 * Created by js on 2018/7/11.
 */

public class BankManagerAdapter extends MBaseAdapter<BankManagerAdapter.ViewHolder> {
    private Context context;
    private List<BankInfoEntity> list;
    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onDelete(int position, BankInfoEntity entity);

        void onClick(int position, BankInfoEntity entity);
    }

    public BankManagerAdapter(Context context, List<BankInfoEntity> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_bank, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final BankInfoEntity entity = list.get(position);
        holder.tv_bank_nicname.setText(entity.getBank_user());
        holder.tv_bank_account.setText(entity.getBank_no());
        holder.tv_bank_name.setText(entity.getBank_name());
        holder.tv_bank_address.setText(entity.getBank_address());
        holder.delete_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               listener.onDelete(position,entity);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(position,entity);
            }
        });

        switch (position % 4) {
            case 0:
                holder.bank_ll.setBackgroundResource(R.mipmap.bg_bank_1);
                break;
            case 1:
                holder.bank_ll.setBackgroundResource(R.mipmap.bg_bank_2);
                break;
            case 2:
                holder.bank_ll.setBackgroundResource(R.mipmap.bg_bank_3);
                break;
            case 3:
                holder.bank_ll.setBackgroundResource(R.mipmap.bg_bank_4);
                break;
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_bank_nicname, tv_bank_account, tv_bank_name, tv_bank_address, delete_tv;
        private View bank_ll;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_bank_nicname = itemView.findViewById(R.id.tv_bank_nicname);
            tv_bank_account = itemView.findViewById(R.id.tv_bank_account);
            tv_bank_name = itemView.findViewById(R.id.tv_bank_name);
            tv_bank_address = itemView.findViewById(R.id.tv_bank_address);
            delete_tv = itemView.findViewById(R.id.delete_tv);
            bank_ll = itemView.findViewById(R.id.bank_ll);
        }
    }
}
