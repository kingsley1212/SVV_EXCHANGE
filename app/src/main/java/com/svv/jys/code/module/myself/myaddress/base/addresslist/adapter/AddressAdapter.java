package com.svv.jys.code.module.myself.myaddress.base.addresslist.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.MBaseAdapter;
import com.svv.jys.code.common.entity.AddressEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by js on 2018/5/21.
 */

public class AddressAdapter extends MBaseAdapter<AddressAdapter.ViewHolder>{
    private Context context;
    private List<AddressEntity> list;
    private AddressNanager addressNanager;

    public void setAddressNanager(AddressNanager addressNanager) {
        this.addressNanager = addressNanager;
    }

    public AddressAdapter(Context context){
        this.context=context;
        this.list=new ArrayList<>();
    }

    public void setList(List<AddressEntity> list) {
        this.list = list;
    }


    public void addList(List<AddressEntity> list) {
        this.list .addAll( list);
    }

    public void remove(int position){
        this.list.remove(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_address,parent,false));
    }

    public interface AddressNanager{
        void deleteAddress(AddressEntity addressEntity,int position);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final AddressEntity entity=list.get(position);
        holder.tv_address.setText(context.getString(R.string.AddressManagerAct_ti_address)+entity.getAddress());
        holder.tv_address_name.setText(entity.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClick.onItemClick(position,list.get(position));
            }
        });

        holder.delete_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addressNanager.deleteAddress(entity,position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_address,tv_address_name,delete_tv;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_address=itemView.findViewById(R.id.tv_address);
            tv_address_name=itemView.findViewById(R.id.tv_address_name);
            delete_tv=itemView.findViewById(R.id.delete_tv);
        }
    }
}
