package com.svv.jys.code.module.myself.myasset.base.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.MBaseAdapter;
import com.svv.jys.code.common.entity.OtcAssatEntity;
import com.svv.jys.code.common.utils.ToolUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/3 0003.
 */

public class MyAssetCount3Adapter extends MBaseAdapter<MyAssetCount3Adapter.MyAssetViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private List<OtcAssatEntity.RowsBean> allList;

    public void setToOtcInterface(ToOtcInterface toOtcInterface) {
        this.toOtcInterface = toOtcInterface;
    }

    private ToOtcInterface toOtcInterface;
    public MyAssetCount3Adapter(Context context, List<OtcAssatEntity.RowsBean> allList) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.allList = allList;
        if (this.allList == null) {
            this.allList = new ArrayList<>();
        }
    }

    public void setData(List<OtcAssatEntity.RowsBean> list) {
        allList = list;
        if (allList == null) {
            allList = new ArrayList<>();
        }
        notifyDataSetChanged();
    }

    public void addData(List<OtcAssatEntity.RowsBean> list) {
        if (list == null) {
            list = new ArrayList<>();
        }
        if (allList == null) {
            allList = new ArrayList<>();
        }
        allList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public MyAssetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyAssetViewHolder(inflater.inflate(R.layout.item_otcasset_count, null));
    }

    @Override
    public void onBindViewHolder(MyAssetViewHolder holder, int position) {
        holder.bindData(position);
    }

    @Override
    public int getItemCount() {
        return allList.size();
    }

    class MyAssetViewHolder extends RecyclerView.ViewHolder {
        TextView countName,able,freeze,zhehe,to_otc_transfer;
        public MyAssetViewHolder(View itemView) {
            super(itemView);
            countName=itemView.findViewById(R.id.countName);
            able=itemView.findViewById(R.id.able);
            freeze=itemView.findViewById(R.id.freeze);
            zhehe=itemView.findViewById(R.id.zhehe);
//            to_otc_transfer=itemView.findViewById(R.id.to_otc_transfer);
        }

        public void bindData(final int position) {
            OtcAssatEntity.RowsBean entity=allList.get(position);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClick != null) {
                        onItemClick.onItemClick(position, "");
                    }
                }
            });
            countName.setText(entity.getEnglish());
            able.setText(entity.getAble());
            freeze.setText(entity.getFreeze());
            zhehe.setText(ToolUtils.multiplyWithScale(ToolUtils.add(entity.getAble(),entity.getFreeze()),entity.getCny_price(),2));
//            to_otc_transfer.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if (toOtcInterface!=null){
//                        toOtcInterface.toOtc(position);
//                    }
//                }
//            });
        }
    }
    interface ToOtcInterface{
        void toOtc(int position);
    }

}
